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

/**
 * Interface of ControlLab.
 * 
 * @author Erik Amzallag
 * 
 */
public interface ControlLabInterface {

	/**
	 * Connect to the serial port and start the keepalive thread.
	 */
	public void connect();

	/**
	 * Send the message for closing the communication and close the serial port.
	 */
	public void disconnect();

	/**
	 * Read the data when available.
	 */
	public void readData();

	/**
	 * Get the sensors' values array.
	 * 
	 * @return the sensors' values array
	 */
	int[] getSensorValues();

	/**
	 * Send a message.
	 * 
	 * @param message
	 *            a byte
	 */
	public void sendMessage(byte message);

	/**
	 * Send a message.
	 * 
	 * @param message
	 *            a byte array.
	 */
	public void sendMessage(byte[] message);

	/**
	 * Returns the port.
	 * 
	 * @return the port
	 */
	public String getPort();

	/**
	 * Get the ouput for this port.
	 * 
	 * @param outputPort
	 *            outputport
	 * @return the output
	 */
	Output getOuput(final OutputPortEnum outputPort);

	/**
	 * Get the input type declared by clazz for this port. <br>
	 * <ul>
	 * <li>If the instance was already created, then is returned.</li>
	 * <li>If no instance, a new one is created.</li>
	 * <li>If the instance is a different class, the a new one is created and
	 * the previous erased</li>
	 * </ul>
	 * 
	 * @param clazz
	 *            wanted class instance of the input
	 * @param inputPort
	 *            input port
	 * @param <S>
	 *            class which extends Input
	 * @return an new instance if not yet created.
	 */
	<S extends Input> S getInput(Class<S> clazz, final InputPortEnum inputPort);
}
