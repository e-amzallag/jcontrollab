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

import org.dajlab.jcontrollab.core.ControlLabInterface;
import org.dajlab.jcontrollab.core.serial.SerialPortListener;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

/**
 * Implementation of SerialPortListener using jSerialComm.
 * 
 * @author Erik Amzallag
 *
 */
public class SerialPortEventListenerImpl implements SerialPortListener, SerialPortPacketListener {

	/**
	 * JControlLab controller.
	 */
	private ControlLabInterface controller;

	/**
	 * Constructor.
	 * 
	 * @param controller
	 *            controller
	 */
	public SerialPortEventListenerImpl(final ControlLabInterface controller) {
		this.controller = controller;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getListeningEvents() {
		// Listening for new data
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void serialEvent(final SerialPortEvent arg0) {
		controller.readData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPacketSize() {
		// The protocol send packet of bytes
		return 20;
	}

}
