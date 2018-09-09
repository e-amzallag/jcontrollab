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
 * Direction of rotation, for Rotation Sensor.
 * 
 * @author Erik Amzallag
 *
 */
public enum RotationDirectionEnum {

	/**
	 * Clockwise.
	 */
	CLOCKWISE(4),
	/**
	 * Counter clockwise.
	 */
	COUNTERCW(0),

	/**
	 * Uninterpreted value.
	 */
	UNINTERPRETED(-1);

	/**
	 * Value.
	 */
	private int value;

	/**
	 * Private constructor.
	 * 
	 * @param aValue
	 *            value
	 */
	private RotationDirectionEnum(int aValue) {
		value = aValue;
	}

	/**
	 * Get enum from value.
	 * 
	 * @param value
	 *            value
	 * @return direction of rotation
	 */
	public static RotationDirectionEnum fromValue(int value) {

		RotationDirectionEnum rot = UNINTERPRETED;
		for (RotationDirectionEnum en : RotationDirectionEnum.values()) {
			if (value == en.value) {
				rot = en;
				break;
			}
		}
		return rot;
	}
}
