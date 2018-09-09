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

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dajlab.jcontrollab.core.exception.SerialException;
import org.dajlab.jcontrollab.core.serial.SerialPortFactory;

/**
 * Control lab interfaces manager.
 * 
 * @author Erik Amzallag
 *
 */
public class ControlLabManager {

	/**
	 * List of Control Lab interfaces.
	 */
	private List<ControlLabInterface> controlLabs;

	/**
	 * Logger.
	 */
	private Logger logger = LogManager.getLogger(ControlLabManager.class);

	/**
	 * Constructor.
	 */
	public ControlLabManager() {
		controlLabs = new ArrayList<>();
	}

	/**
	 * Connect the control lab interfaces.
	 */
	public void connect() {

		List<String> ports = SerialPortFactory.getAvailablePorts();
		if (ports != null) {
			for (String port : ports) {
				ControlLabInterface controlLab = new ControlLabImpl(port);
				try {
					controlLab.connect();
					controlLabs.add(controlLab);
				} catch (SerialException e) {
					// NA The device is not a Control Lab Interface
				}
			}
			logger.debug("Number of Control Lab connected : [{}]", controlLabs.size());
		}
	}

	/**
	 * Disconnect the control lab interfaces.
	 */
	public void disconnect() {

		for (ControlLabInterface controlLabInterface : controlLabs) {
			controlLabInterface.disconnect();
		}
	}

	/**
	 * @return the controlLabs
	 */
	public final List<ControlLabInterface> getControlLabs() {
		return controlLabs;
	}

}
