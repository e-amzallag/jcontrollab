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
 * Light sensor element.
 * 
 * @author Erik Amzallag
 * 
 */
public class LightSensor extends Input {

	/**
	 * Initial light.
	 */
	private int initialLight = 0;

	/**
	 * Tolerance.
	 */
	private int tolerance = 0;

	/**
	 * Constructor.
	 * 
	 * @param controllab
	 *            control lab
	 * @param port
	 *            input port
	 */
	public LightSensor(ControlLabInterface controllab, InputPortEnum port) {
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
	 * Get the light.
	 * 
	 * @return the light value.
	 */
	public int getLight() {
		return getValue();
	}

	/**
	 * Reset the initial light.
	 * 
	 */
	public void reset() {
		initialLight = getLight();
	}

	/**
	 * 
	 * @return true if the current light is stronger than the initial one,
	 *         including tolerance.
	 */
	public boolean hasMoreLight() {

		return getLight() < (initialLight - tolerance);
	}

	public boolean hasMoreLight(int aValue) {

		return (getLight() < (aValue - tolerance));
	}

	public boolean hasLessLight() {

		return getLight() > (initialLight + tolerance);
	}

	public boolean hasLessLight(int aValue) {

		return getLight() > (aValue + tolerance);
	}

	/**
	 * Return the initial light.
	 * 
	 * @return the initial light
	 */
	public int getInitialLight() {
		return initialLight;
	}

	/**
	 * Set the initial light.
	 * 
	 * @param initialLight
	 *            the initial light
	 */
	public void setInitialLight(int initialLight) {
		this.initialLight = initialLight;
	}

	/**
	 * Return the tolerance.
	 * 
	 * @return the tolerance
	 */
	public int getTolerance() {
		return tolerance;
	}

	/**
	 * Set the tolerance.
	 * 
	 * @param tolerance
	 *            the tolerance
	 */
	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}
}
