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
 * Enumeration for input ports.
 * 
 * @author Erik Amzallag
 *
 */
public enum InputPortEnum {

	I1(1), I2(2), I3(3), I4(4), I5(5), I6(6), I7(7), I8(8);

	/**
	 * Port.
	 */
	private int port;

	/**
	 * Private constructor.
	 * 
	 * @param port
	 *            port
	 */
	private InputPortEnum(final int port) {
		this.port = port;
	}

	/**
	 * @return the port
	 */
	public final int getPort() {

		return port;
	}

	/**
	 * Convert the port value to the enum.
	 * 
	 * @param codeEnum
	 *            value
	 * @return the enum
	 */
	public static InputPortEnum fromValue(final int codeEnum) {

		InputPortEnum value = null;
		for (InputPortEnum item : InputPortEnum.values()) {
			if (item.getPort() == codeEnum) {
				value = item;
				break;
			}
		}
		return value;
	}
}
