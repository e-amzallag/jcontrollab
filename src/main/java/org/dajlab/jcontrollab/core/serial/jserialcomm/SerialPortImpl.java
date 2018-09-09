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
package org.dajlab.jcontrollab.core.serial.jserialcomm;

import java.io.InputStream;
import java.io.OutputStream;

import org.dajlab.jcontrollab.core.serial.SerialPortInterface;
import org.dajlab.jcontrollab.core.serial.SerialPortListener;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortPacketListener;

/**
 * Implementation of SerialPortInterface using jSerialComm.
 * 
 * @author Erik Amzallag
 *
 */
public class SerialPortImpl implements SerialPortInterface {

	/**
	 * Serial port.
	 */
	private SerialPort sp = null;

	/**
	 * Constructor.
	 * 
	 * @param port
	 *            port
	 */
	public SerialPortImpl(final String port) {

		sp = SerialPort.getCommPort(port);
		sp.setBaudRate(9600);
		sp.setNumDataBits(8);
		sp.setNumStopBits(SerialPort.ONE_STOP_BIT);
		sp.setParity(SerialPort.NO_PARITY);
		sp.openPort(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OutputStream getOutputStream() {

		return sp.getOutputStream();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream getInputStream() {

		return sp.getInputStream();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addEventListener(SerialPortListener dataListener) {

		sp.addDataListener((SerialPortPacketListener) dataListener);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		sp.closePort();

	}

}
