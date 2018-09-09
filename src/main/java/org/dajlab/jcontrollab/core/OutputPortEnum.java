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
 * Enumeration for output ports.
 * 
 * @author Erik Amzallag
 * 
 */
public enum OutputPortEnum {

	A('A'), B('B'), C('C'), D('D'), E('E'), F('F'), G('G'), H('H');

	/**
	 * Label.
	 */
	private char label;

	/**
	 * Private constructor.
	 * 
	 * @param label
	 *            label
	 */
	private OutputPortEnum(final char label) {
		this.label = label;
	}

	/**
	 * @return the label
	 */
	public final String getLabel() {

		return String.valueOf(label);
	}

	/**
	 * 
	 * @return the port
	 */
	public final char getPort() {

		return label;
	}

	/**
	 * Convert the value to the enum.
	 * 
	 * @param codeEnum
	 *            the value
	 * @return the enum
	 */
	public static OutputPortEnum fromValue(final String codeEnum) {

		OutputPortEnum value = null;
		if (codeEnum != null) {
			for (OutputPortEnum item : OutputPortEnum.values()) {
				if (item.getLabel().equals(codeEnum)) {
					value = item;
					break;
				}
			}
		}
		return value;

	}
}
