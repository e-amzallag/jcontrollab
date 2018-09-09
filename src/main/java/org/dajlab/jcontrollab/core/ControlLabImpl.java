/*
 * Copyright 2018 Erik Amzallag
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.dajlab.jcontrollab.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dajlab.jcontrollab.core.exception.SerialException;
import org.dajlab.jcontrollab.core.serial.SerialPortFactory;
import org.dajlab.jcontrollab.core.serial.SerialPortInterface;

/**
 * Control Lab implementation.
 * 
 * @author Erik Amzallag
 * 
 */
public class ControlLabImpl implements ControlLabInterface {
	/**
	 * Logger.
	 */
	private Logger logger = LogManager.getLogger(ControlLabImpl.class.getName());

	/**
	 * Communication port.
	 */
	private String port = null;

	/**
	 * Input stream
	 */
	private InputStream is = null;

	/**
	 * Data input stream;
	 */
	private DataInputStream dis = null;

	/**
	 * Output stream.
	 */
	private OutputStream os = null;

	/**
	 * Sensors' values array.
	 */
	private int[] sensorValues = new int[8];

	/**
	 * Sensors' status array.
	 */
	private int[] sensorStatus = new int[8];
	/**
	 * Connection state.
	 */
	private boolean connected = false;

	/**
	 * Serial port.
	 */
	private SerialPortInterface serialPort;

	/**
	 * Keep alive thread.
	 */
	private HelloThread hello;

	private Map<OutputPortEnum, Output> outputsMap;

	private Map<InputPortEnum, Input> inputsMap;

	/**
	 * Constructor.
	 * 
	 * @param aPort
	 *            the serial port (by example COM1, /dev/ttys1)
	 */
	protected ControlLabImpl(final String aPort) {
		port = aPort;
		outputsMap = new HashMap<>(8);
		inputsMap = new HashMap<>(8);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connect() {

		try {
			logger.debug("Try to connect to port [{}]", port);
			serialPort = SerialPortFactory.getNewSerialPort(port);
			os = serialPort.getOutputStream();
			is = serialPort.getInputStream();
			dis = new DataInputStream(is);
			serialPort.addEventListener(SerialPortFactory.getNewSerialPortListener(this));

			// Start the HELLO thread
			if (os != null) {
				hello = new HelloThread(os);
				hello.start();
			}

			sendMessage(CLIProtocol.KNOCK_KNCOK.getBytes());
			Thread.sleep(1000);
			if (!connected) {
				os.close();
				is.close();
				dis.close();
				serialPort.close();
				hello.disconnect();
				throw new SerialException("Error of connection on port " + port);
			}

			for (OutputPortEnum portEnum : OutputPortEnum.values()) {
				Output ouput = new Output(this, portEnum);
				outputsMap.put(portEnum, ouput);
			}

		} catch (Exception e) {
			logger.error("Error while initializing ControlLab on port [{}]", port);
			throw new SerialException(e.getMessage());
		}
		logger.info("ControlLab initialized on port [{}]", port);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disconnect() {

		sendMessage(CLIProtocol.CLOSE);
		try {
			if (hello != null) {
				hello.disconnect();
			}
			os.close();
			is.close();
			dis.close();
			serialPort.close();
		} catch (Exception e) {
			throw new SerialException(e.getMessage());
		}
		logger.info("[{}] disconnected.", port);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void readData() {

		byte[] buffer = new byte[19];
		boolean correctData = true;
		try {

			if (dis.available() > 19) {
				for (int i = 0; i < 19; i++) {
					buffer[i] = dis.readByte();
				}
				// Without the following line, jserialcomm impl doesn't work ??
				String datas = byteToHex(buffer);
				logger.trace("Received data = {}", datas);
				if (!connected) {
					// It is the first reply from the interface
					for (int i = 0; i < buffer.length; i++) {
						if (buffer[i] == (byte) 0x24) {
							connected = true;
						}
					}
				}
				if ((buffer[0] != 0) || (buffer[1] != 0)) {
					if (dis.available() > 1) {
						dis.readByte();
					}
					correctData = false;
				}
				// Decode the packet
				int chksum = 0;
				for (int i = 0; i < buffer.length; i++) {
					int unsignedbyte = ((int) buffer[i] & 0xff);
					chksum = chksum + unsignedbyte;
				}
				if ((chksum & 0xff) != 0xff) {
					correctData = false;
				}
				if (correctData) {
					sensorValues[0] = decodeValue(buffer[14], buffer[15]);
					sensorValues[1] = decodeValue(buffer[10], buffer[11]);
					sensorValues[2] = decodeValue(buffer[6], buffer[7]);
					sensorValues[3] = decodeValue(buffer[2], buffer[3]);
					sensorValues[4] = decodeValue(buffer[16], buffer[17]);
					sensorValues[5] = decodeValue(buffer[12], buffer[13]);
					sensorValues[6] = decodeValue(buffer[8], buffer[9]);
					sensorValues[7] = decodeValue(buffer[4], buffer[5]);

					sensorStatus[0] = (byte) ((byte) buffer[15] & 0x3f);
					sensorStatus[1] = (byte) ((byte) buffer[11] & 0x3f);
					sensorStatus[2] = (byte) ((byte) buffer[7] & 0x3f);
					sensorStatus[3] = (byte) ((byte) buffer[3] & 0x3f);
					sensorStatus[4] = (byte) ((byte) buffer[17] & 0x3f);
					sensorStatus[5] = (byte) ((byte) buffer[13] & 0x3f);
					sensorStatus[6] = (byte) ((byte) buffer[9] & 0x3f);
					sensorStatus[7] = (byte) ((byte) buffer[5] & 0x3f);

					fireValuesChanged();
				}
			}
		} catch (IOException e) {
			logger.error("Error while reading the data : {}", e.getMessage());
		}
	}

	/**
	 * Decode the value of a sensor.
	 * 
	 * @param b1
	 *            the highbyte of the encoded value
	 * @param b2
	 *            the lowbyte of the encoded value
	 * @return the decoded value.
	 */
	private int decodeValue(byte b1, byte b2) {

		int highbits = (b1 & 0xff) << 2;
		int lowbits = (b2 & 0xff) >> 6;
		return highbits + lowbits;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int[] getSensorValues() {
		return sensorValues;
	}

	/**
	 * Throws the event to the listeners.
	 */
	private void fireValuesChanged() {

		for (Input input : inputsMap.values()) {
			input.setValue((short) sensorValues[input.getPort().getPort() - 1]);
			input.setStatus(sensorStatus[input.getPort().getPort() - 1]);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendMessage(byte[] message) {

		try {
			synchronized (os) {
				os.write(message);
			}
		} catch (IOException e) {
			logger.error("Error while writting data : {}", e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendMessage(byte message) {

		try {
			synchronized (os) {
				os.write(message);
			}
		} catch (IOException e) {
			logger.error("Error while writting data : {}", e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPort() {

		return port;
	}

	/**
	 * Format a byte array to a String for debug purpose.
	 * 
	 * @param data
	 *            data
	 * @return a formatted string
	 */
	public final static String byteToHex(byte[] data) {

		StringBuilder mess = new StringBuilder();
		for (int i = 0; i < data.length - 1; i++) {
			byte b = data[i];
			mess.append(String.format("%02X ", b));
		}
		mess.append(String.format("%02X", data[data.length - 1]));
		return mess.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Output getOuput(final OutputPortEnum outputPort) {

		return outputsMap.get(outputPort);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S extends Input> S getInput(Class<S> clazz, final InputPortEnum inputPort) {

		S ret = null;
		Input input = inputsMap.get(inputPort);
		try {
			if (input == null) {
				// Not yet created, we create the instance
				ret = clazz.getConstructor(ControlLabInterface.class, InputPortEnum.class).newInstance(this, inputPort);
				inputsMap.put(inputPort, ret);
			} else if (clazz.equals(input.getClass())) {
				// Instance already created
				ret = (S) input;
			} else {
				// Previously an other type of sensor, we create a new instance
				// and erase the previous
				ret = clazz.getConstructor(ControlLabInterface.class, InputPortEnum.class).newInstance(this, inputPort);
				inputsMap.put(inputPort, ret);
			}
		} catch (Exception e) {
			// N/A
		}
		return ret;
	}
}
