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
 * Rotation sensor element.
 * 
 * @author Erik Amzallag
 * 
 */
public class RotationSensor extends Input {

	/**
	 * Angle.
	 */
	private int angle = 0;

	/**
	 * Constructor.
	 * 
	 * @param controllab
	 *            control lab
	 * @param port
	 *            input port
	 */
	public RotationSensor(ControlLabInterface controllab, InputPortEnum port) {
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
	 * Return the current angle, a value between 0 and 15.
	 * 
	 * @return return the current angle
	 */
	public int getAngle() {

		getValue();
		int turnAmount = (getStatus() & 0x03);
		switch (turnAmount) {
		case 0x01:
		case 0x02:
		case 0x03:
			if (getDirection() == RotationDirectionEnum.CLOCKWISE) {
				angle = (angle + turnAmount) % 16;
			} else if (getDirection() == RotationDirectionEnum.COUNTERCW) {
				angle = (16 + angle - turnAmount) % 16;
			}
			break;
		default:
			break;
		}
		return angle;
	}

	/**
	 * Set the initial value.
	 * 
	 * @param anAngle
	 *            the initial value.
	 */
	public void setAngle(int anAngle) {

		angle = anAngle % 16;
	}

	/**
	 * Set the initial value to 0.
	 * 
	 */
	public void reset() {

		setAngle(0);
	}

	/**
	 * Return the direction, CLOCKWISE or COUNTERCW.
	 * 
	 * @return returns the direction.
	 */
	public RotationDirectionEnum getDirection() {

		return RotationDirectionEnum.fromValue(super.getStatus() & 0x04);
	}
}
