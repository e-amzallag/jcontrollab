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
 * Event of a sensor.
 * 
 * @author Erik Amzallag
 * 
 */
public class SensorEvent {
	/**
	 * Input source of the event.
	 */
	private Input input = null;

	/**
	 * Type of the source sensor.
	 */
	private SensorTypeEnum sensorType;

	/**
	 * Old value of the sensor.
	 */
	private int oldValue = 0;

	/**
	 * New value of the sensor.
	 */
	private int newValue = 0;

	/**
	 * Constructor.
	 * 
	 * @param anInput
	 *            the input
	 * @param aType
	 *            type of sensor
	 * @param anOldValue
	 *            the old value
	 * @param aNewValue
	 *            the new value
	 */
	public SensorEvent(Input anInput, SensorTypeEnum aType, int anOldValue, int aNewValue) {

		this.input = anInput;
		this.sensorType = aType;
		this.oldValue = anOldValue;
		this.newValue = aNewValue;
	}

	/**
	 * 
	 * @return the source of the event.
	 */
	public Input getSource() {
		return input;
	}

	/**
	 * 
	 * @return the new value
	 */
	public int getNewValue() {
		return newValue;
	}

	/**
	 * 
	 * @return the old value
	 */
	public int getOldValue() {
		return oldValue;
	}

	/**
	 * @return the sensorType
	 */
	public final SensorTypeEnum getSensorType() {
		return sensorType;
	}

}
