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
package org.dajlab.jcontrollab.core.serial.rxtx;
// Uncomment the following code if you want use RXTX.
// If you want use an other API implement Javax Communication, just change the
// import gnu.io.*

// import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.util.Enumeration;
// import java.util.TooManyListenersException;
//
// import org.dajlab.jcontrollab.core.exception.SerialException;
// import org.dajlab.jcontrollab.core.serial.SerialPortInterface;
// import org.dajlab.jcontrollab.core.serial.SerialPortListener;
//
// import gnu.io.CommPortIdentifier;
// import gnu.io.SerialPort;
// import gnu.io.SerialPortEventListener;
//
/// **
// * Implementation of SerialPort
// *
// * @author Erik Amzallag
// *
// */
// public class SerialPortImpl implements SerialPortInterface {
//
// /**
// * Serial port.
// */
// private SerialPort serialPort;
//
// /**
// * Constructor.
// *
// * @param port
// * the port
// */
// public SerialPortImpl(String port) {
//
// Enumeration<CommPortIdentifier> portList =
// CommPortIdentifier.getPortIdentifiers();
// while (portList.hasMoreElements()) {
// CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
// if ((portId.getPortType() == CommPortIdentifier.PORT_SERIAL) &&
// (portId.getName().equals(port))) {
// try {
// serialPort = (SerialPort) portId.open("DACTA Interface B", 2000);
// } catch (Exception e) {
// // System.err.println("echec");
// throw new SerialException(e.getMessage());
// }
// try {
// serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
// SerialPort.STOPBITS_1,
// SerialPort.PARITY_NONE);
// serialPort.notifyOnDataAvailable(true);
// } catch (Exception e) {
// throw new SerialException(e.getMessage());
// }
// }
// }
// }
//
// /**
// * {@inheritDoc}
// */
// public InputStream getInputStream() {
//
// try {
// return serialPort.getInputStream();
// } catch (IOException e) {
// e.printStackTrace();
// }
// return null;
// }
//
// /**
// * {@inheritDoc}
// */
// public OutputStream getOutputStream() {
//
// try {
// return serialPort.getOutputStream();
// } catch (IOException e) {
// e.printStackTrace();
// }
// return null;
// }
//
// /**
// * {@inheritDoc}
// */
// public void addEventListener(SerialPortListener eventListener) {
//
// try {
// serialPort.addEventListener((SerialPortEventListener) eventListener);
// } catch (TooManyListenersException e) {
// e.printStackTrace();
// }
// }
//
// /**
// * {@inheritDoc}
// */
// public void close() {
// serialPort.close();
// }
//
// }
