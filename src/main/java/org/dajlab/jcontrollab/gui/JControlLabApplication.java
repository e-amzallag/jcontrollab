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
package org.dajlab.jcontrollab.gui;

import org.dajlab.gui.AbstractDajlabApplication;
import org.dajlab.gui.AbstractDajlabTab;

/**
 * JControlLab Application.
 * 
 * @author Erik Amzallag
 *
 */
public class JControlLabApplication extends AbstractDajlabApplication {

	/**
	 * Constructor.
	 */
	public JControlLabApplication() {
		JControlLabController controller = new JControlLabController();
		registerPlugin(controller);
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            args
	 */
	public static void main(String[] args) {
		startApplication(JControlLabApplication.class, "JControlLab");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractDajlabTab selectDefaultTab(final AbstractDajlabTab[] tabsList) {

		return null;
	}

}
