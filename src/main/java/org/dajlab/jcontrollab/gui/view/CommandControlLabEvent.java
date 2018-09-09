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
package org.dajlab.jcontrollab.gui.view;

import org.dajlab.core.CommandEvent;
import org.dajlab.jcontrollab.gui.model.ControlLabCommandEnum;
import org.dajlab.jcontrollab.gui.model.OutputModel;

/**
 * Event for a Control Lab command.
 * 
 * @author Erik Amzallag
 *
 */
public class CommandControlLabEvent implements CommandEvent {

	/**
	 * Output model.
	 */
	private OutputModel model;
	/**
	 * Command.
	 */
	private ControlLabCommandEnum command;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            model
	 * @param command
	 *            command
	 */
	public CommandControlLabEvent(final OutputModel model, final ControlLabCommandEnum command) {
		super();
		this.model = model;
		this.command = command;
	}

	/**
	 * @return the model
	 */
	public final OutputModel getModel() {
		return model;
	}

	/**
	 * @return the command
	 */
	public final ControlLabCommandEnum getCommand() {
		return command;
	}

}
