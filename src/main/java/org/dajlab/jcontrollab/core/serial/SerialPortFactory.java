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
package org.dajlab.jcontrollab.core.serial;

import java.util.List;

import org.dajlab.jcontrollab.core.ControlLabInterface;
import org.dajlab.jcontrollab.core.serial.jserialcomm.SerialPortEventListenerImpl;
import org.dajlab.jcontrollab.core.serial.jserialcomm.SerialPortImpl;
import org.dajlab.jcontrollab.core.serial.jserialcomm.SerialUtils;

/**
 * Factory for serial port. <br>
 * Use this factory to choose the implementation of serial port API.<br>
 * Currently, the jSerialComm API is used.
 * 
 * @author Erik Amzallag
 *
 */
public class SerialPortFactory {

	/**
	 * Create an instance of SerialPortInterface implementation.
	 * 
	 * @param port
	 *            port
	 * @return a new instance
	 */
	public static SerialPortInterface getNewSerialPort(String port) {

		return new SerialPortImpl(port);
	}

	/**
	 * Create an instance of SerialPortListener implementation.
	 * 
	 * @param controller
	 *            controller
	 * @return a new instance
	 */
	public static SerialPortListener getNewSerialPortListener(ControlLabInterface controller) {

		return new SerialPortEventListenerImpl(controller);
	}

	/**
	 * Utility method to return the list of all serial ports.
	 * 
	 * @return a list with all serial ports names
	 */
	public static List<String> getAvailablePorts() {

		return SerialUtils.getAvailablePorts();
	}
}
