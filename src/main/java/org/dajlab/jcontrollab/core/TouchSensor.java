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
 * Touch sensor element.
 * 
 * @author Erik Amzallag
 *
 */
public class TouchSensor extends Input {

	/**
	 * Default value.
	 */
	protected final static int SWITCH = 300;

	/**
	* 
	 */
	private int sensibilityIn = SWITCH;

	/**
	* 
	 */
	private int sensibilityOut = SWITCH;

	/**
	 * Constructor.
	 * 
	 * @param controllab
	 *            control lab
	 * @param port
	 *            port
	 */
	public TouchSensor(ControlLabInterface controllab, InputPortEnum port) {
		super(controllab, port);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SensorTypeEnum getSensorType() {

		return SensorTypeEnum.LIGHT_SENSOR;
	}

	/**
	 * 
	 * @return true if pressed
	 */
	public final boolean isPressed() {
		return (getValue() < sensibilityIn);
	}

	/**
	 * 
	 * @return true if released
	 */
	public final boolean isReleased() {
		return (getValue() >= sensibilityOut);
	}

	/**
	 * @return the sensibilityIn
	 */
	public final int getSensibilityIn() {
		return sensibilityIn;
	}

	/**
	 * @param sensibilityIn
	 *            the sensibilityIn to set
	 */
	public final void setSensibilityIn(final int sensibilityIn) {
		this.sensibilityIn = sensibilityIn;
	}

	/**
	 * @return the sensibilityOut
	 */
	public final int getSensibilityOut() {
		return sensibilityOut;
	}

	/**
	 * @param sensibilityOut
	 *            the sensibilityOut to set
	 */
	public final void setSensibilityOut(final int sensibilityOut) {
		this.sensibilityOut = sensibilityOut;
	}

}
