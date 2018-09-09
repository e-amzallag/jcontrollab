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
 * Temperature sensor element.
 * 
 * @author Erik Amzallag
 *
 */
public class TemperatureSensor extends Input {

	/**
	 * Constructor.
	 * 
	 * @param controllab
	 *            control lab
	 * @param port
	 *            port
	 */
	public TemperatureSensor(final ControlLabInterface controllab, final InputPortEnum port) {
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
	 * @return the current value in Degrees Fahrenheit.
	 */
	public double getDegreesFahrenheit() {
		return (760 - getValue()) / 4.4 + 32;
	}

	/**
	 * 
	 * @return the current value in Degrees Celsius.
	 */
	public double getDegreesCelsius() {
		return (getDegreesFahrenheit() - 32) / 1.8;
	}

}
