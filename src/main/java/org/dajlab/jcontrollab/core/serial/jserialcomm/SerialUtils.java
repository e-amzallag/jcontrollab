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

import java.util.ArrayList;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Utility class using jSerialComm.
 * 
 * @author Erik Amzallag
 *
 */
public final class SerialUtils {

	/**
	 * Utility method to return the list of all serial ports.
	 * 
	 * @return a list with all serial ports names
	 */
	public static List<String> getAvailablePorts() {

		SerialPort[] sports = SerialPort.getCommPorts();
		List<String> ports = new ArrayList<String>(sports.length);
		for (SerialPort serialPort : sports) {
			ports.add(serialPort.getSystemPortName());
		}

		return ports;
	}
}
