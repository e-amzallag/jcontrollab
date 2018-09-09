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
 * Describe a generic port. A port can be an input or an output.
 * 
 * @author Erik Amzallag
 * 
 */
public abstract class AbstractPort {
	/**
	 * The controlLab.
	 */
	private ControlLabInterface controlLab;

	/**
	 * 
	 * @return the controlLab.
	 */
	public ControlLabInterface getControlLab() {
		return controlLab;
	}

	/**
	 * 
	 * @param controlLab
	 *            the controlLab
	 */
	protected void setControlLab(ControlLabInterface controlLab) {
		this.controlLab = controlLab;
	}
}
