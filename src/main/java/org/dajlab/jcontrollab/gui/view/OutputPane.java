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

import javax.swing.event.EventListenerList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.RangeSlider;
import org.dajlab.core.CommandListener;
import org.dajlab.gui.MessagesUtil;
import org.dajlab.jcontrollab.gui.model.ControlLabCommandEnum;
import org.dajlab.jcontrollab.gui.model.OutputModel;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Output pane. <br>
 * Composed by :
 * <ul>
 * <li>a label</li>
 * <li>a checkbox to start or stop the output</li>
 * <li>a power slider</li>
 * <li>a checkbox to reverse the output</li>
 * <li>a slider to set a working range. A random value will be picked into this
 * range.</li>
 * <li>a slider to set a stop range. A random value will be picked into this
 * range.</li>
 * </ul>
 * To run an output without stopping, set the stop range to (0,0).
 * 
 * @author Erik Amzallag
 *
 */
public class OutputPane extends GridPane {

	/**
	 * Logger.
	 */
	private static final Logger logger = LogManager.getLogger(OutputPane.class);
	/**
	 * Command listeners.
	 */
	private EventListenerList commandListeners = new EventListenerList();

	/**
	 * Label currently edited.
	 */
	private boolean editLabel = false;
	/**
	 * Mouse over label.
	 */
	private boolean overLabel = false;
	/**
	 * Previous label value.
	 */
	private String previousLabel = "";

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            output model
	 */
	public OutputPane(final OutputModel model) {
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(5));
		setAlignment(Pos.CENTER);

		// Label
		Text label = new Text();
		label.textProperty().bind(Bindings.concat(model.labelProperty(), " (", model.getPort().getLabel(), ")"));

		TextField textField = new TextField();
		textField.setVisible(false);
		textField.setPrefWidth(150);
		textField.textProperty().bindBidirectional(model.labelProperty());
		label.setOnMouseEntered(e -> {
			if (!editLabel && !overLabel) {
				// Mouse over label: we display the input text
				overLabel = true;
				label.setVisible(false);
				textField.setVisible(true);
				previousLabel = textField.getText();
			}
			e.consume();
		});
		textField.setOnMouseExited(e -> {
			if (!editLabel && overLabel) {
				label.setVisible(true);
				textField.setVisible(false);
			}
			overLabel = false;
			e.consume();
		});
		textField.setOnMouseEntered(e -> {
			overLabel = true;
		});

		textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (newPropertyValue) {
					editLabel = true;
				} else {
					// Click outside the input to commit change
					editLabel = false;
					previousLabel = textField.getText();
					label.setVisible(true);
					textField.setVisible(false);

				}
			}
		});
		textField.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				// Press ESC to undo the current edit
				editLabel = false;
				textField.setText(previousLabel);
				label.setVisible(true);
				textField.setVisible(false);
			}
		});
		textField.setOnAction(e -> {
			// Press ENTER to commit change
			label.setVisible(true);
			textField.setVisible(false);
			previousLabel = textField.getText();
			e.consume();
			editLabel = false;

		});
		add(label, 0, 0, 1, 1);
		add(textField, 0, 0, 1, 1);

		// On/Off
		CheckBox onOff = new CheckBox();
		onOff.selectedProperty().bindBidirectional(model.onOffProperty());
		model.onOffProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				logger.debug("Event [ON/OFF] for ControlLab [{}] [{}] : [{}]", model.getConsoleNumber(),
						model.getLabel(), newValue);
				fireEvent(model, ControlLabCommandEnum.ONOFF);
			}

		});
		add(onOff, 1, 0, 1, 1);

		// Power
		Slider power = new Slider();
		power.setMin(0);
		power.setMax(8);
		power.setValue(5);
		power.setShowTickLabels(true);
		power.setShowTickMarks(true);
		power.setMajorTickUnit(1);
		power.setMinorTickCount(0);
		power.setBlockIncrement(1);
		power.setSnapToTicks(true);
		power.valueProperty().bindBidirectional(model.powerProperty());
		power.setPrefWidth(200);
		model.powerProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				logger.debug("Event [POWER] for ControlLab [{}] [{}] : [{}]", model.getConsoleNumber(),
						model.getLabel(), newValue);
				fireEvent(model, ControlLabCommandEnum.POWER);
			}
		});
		add(power, 2, 0, 1, 2);

		// Reverse output
		CheckBox rev = new CheckBox();
		rev.selectedProperty().bindBidirectional(model.reverseProperty());
		model.reverseProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				logger.debug("Event [REV.] for ControlLab [{}] [{}] : [{}]", model.getConsoleNumber(), model.getLabel(),
						newValue);
				fireEvent(model, ControlLabCommandEnum.REV);
			}

		});
		add(rev, 3, 0, 1, 1);

		// Slider ON
		RangeSlider onSlider = new RangeSlider(0, 600, 60, 60);
		onSlider.setBlockIncrement(60);
		onSlider.setPrefWidth(240);
		onSlider.lowValueProperty().bindBidirectional(model.startOnProperty());
		model.startOnProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				logger.debug("Event [START ON] for ControlLab [{}] [{}] : [{}]", model.getConsoleNumber(),
						model.getLabel(), newValue);
				fireEvent(model, ControlLabCommandEnum.STARTON);
			}
		});
		onSlider.highValueProperty().bindBidirectional(model.stopOnProperty());
		model.stopOnProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				logger.debug("Event [STOP ON] for ControlLab [{}] [{}] : [{}]", model.getConsoleNumber(),
						model.getLabel(), newValue);
				fireEvent(model, ControlLabCommandEnum.STOPON);
			}
		});
		add(onSlider, 4, 0, 1, 1);
		Label textOn = new Label();
		textOn.textProperty().bind(Bindings.format(MessagesUtil.getString("jcontrollab.betweenRange"),
				onSlider.lowValueProperty(), onSlider.highValueProperty()));
		add(textOn, 4, 1, 1, 1);

		// Slider OFF
		RangeSlider offSlider = new RangeSlider(0, 600, 60, 60);
		offSlider.setBlockIncrement(60);
		offSlider.setPrefWidth(240);
		offSlider.lowValueProperty().bindBidirectional(model.startOffProperty());
		model.startOffProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				logger.debug("Event [START OFF] for ControlLab [{}] [{}] : [{}]", model.getConsoleNumber(),
						model.getLabel(), newValue);
				fireEvent(model, ControlLabCommandEnum.STARTOFF);
			}
		});
		offSlider.highValueProperty().bindBidirectional(model.stopOffProperty());
		model.stopOffProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				logger.debug("Event [STOP OFF] for ControlLab [{}] [{}] : [{}]", model.getConsoleNumber(),
						model.getLabel(), newValue);
				fireEvent(model, ControlLabCommandEnum.STOPOFF);
			}
		});
		add(offSlider, 5, 0, 1, 1);
		Label textOff = new Label();
		textOff.textProperty().bind(Bindings.format(MessagesUtil.getString("jcontrollab.betweenRange"),
				offSlider.lowValueProperty(), offSlider.highValueProperty()));
		add(textOff, 5, 1, 1, 1);
	}

	/**
	 * Add command listener.
	 * 
	 * @param listener
	 *            a listener
	 */
	public void addCommandListeners(CommandListener listener) {
		commandListeners.add(CommandListener.class, listener);
	}

	/**
	 * Fire event to listeners.
	 * 
	 * @param model
	 *            model
	 * @param cmd
	 *            command
	 */
	private void fireEvent(final OutputModel model, final ControlLabCommandEnum cmd) {
		if (commandListeners != null) {
			CommandControlLabEvent event = new CommandControlLabEvent(model, cmd);
			for (CommandListener listener : commandListeners.getListeners(CommandListener.class)) {
				listener.newCommand(event);
			}
		}

	}
}
