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

import org.dajlab.core.CommandListener;
import org.dajlab.gui.AbstractDajlabTab;
import org.dajlab.jcontrollab.core.OutputPortEnum;
import org.dajlab.jcontrollab.gui.model.ConsoleModel;
import org.dajlab.jcontrollab.gui.model.OutputModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

/**
 * Control lab tab.<br>
 * A tab is composed by a table title and 8 OuputPane, one for each ouput.
 * 
 * @author Erik Amzallag
 *
 */
public class ControlLabTab extends AbstractDajlabTab {

	/**
	 * Model.
	 */
	private ConsoleModel model;

	/**
	 * Constructor.
	 * 
	 * @param consoleModel
	 *            control lab model
	 * @param listener
	 *            listener
	 */
	public ControlLabTab(ConsoleModel consoleModel, CommandListener listener) {

		model = consoleModel;

		textProperty().bind(model.titleProperty());

		Tooltip tooltip = new Tooltip();
		tooltip.textProperty().bind(model.portProperty());
		setTooltip(tooltip);
		enableRenaming("/jcontrollab32.png");
		setClosable(false);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setPadding(new Insets(10));

		// Titles
		grid.add(new TitlePane(), 0, 0);

		// Ouputs
		int i = 1;
		for (OutputPortEnum outEnum : OutputPortEnum.values()) {
			OutputModel outputModel = model.getOutputModelsMap().get(outEnum);
			OutputPane pane = new OutputPane(outputModel);
			outputModel.consoleNumberProperty().bind(model.numberProperty());
			pane.addCommandListeners(listener);
			grid.add(pane, 0, i);
			i++;
		}

		setContent(grid);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateTitle(final String title) {
		// As the tab title is bound to the model, just update the the model
		model.setTitle(title);
	}

}
