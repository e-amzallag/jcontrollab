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
package org.dajlab.jcontrollab.gui.model;

import org.dajlab.jcontrollab.core.OutputPortEnum;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model for an output.
 * 
 * @author Erik Amzallag
 *
 */
public class OutputModel {

	/**
	 * Output port.
	 */
	private ObjectProperty<OutputPortEnum> port = new SimpleObjectProperty<>();

	/**
	 * Label.
	 */
	private StringProperty label = new SimpleStringProperty();

	/**
	 * On / off.
	 */
	private BooleanProperty onOff = new SimpleBooleanProperty();

	/**
	 * Power.
	 */
	private IntegerProperty power = new SimpleIntegerProperty();

	/**
	 * Reverse.
	 */
	private BooleanProperty reverse = new SimpleBooleanProperty();
	/**
	 * Start on. Beginning of the working range.
	 */
	private IntegerProperty startOn = new SimpleIntegerProperty();
	/**
	 * Stop on. End of the working range.
	 */
	private IntegerProperty stopOn = new SimpleIntegerProperty();

	/**
	 * Start off. Beginning of the stop range.
	 */
	private IntegerProperty startOff = new SimpleIntegerProperty();

	/**
	 * Stop off. Stop of the stop range.
	 */
	private IntegerProperty stopOff = new SimpleIntegerProperty();
	/**
	 * Control lab number.
	 */
	private IntegerProperty consoleNumber = new SimpleIntegerProperty();

	/**
	 * Constructor.
	 * 
	 * @param port
	 *            output port
	 */
	public OutputModel(final OutputPortEnum port) {

		this.port.set(port);
		label.set(port.getLabel());
		onOff.set(false);
		reverse.set(false);
		power.set(5);
		startOn.set(10);
		stopOn.set(60);
		startOff.set(10);
		stopOff.set(60);
	}

	public final ObjectProperty<OutputPortEnum> portProperty() {
		return this.port;
	}

	public final org.dajlab.jcontrollab.core.OutputPortEnum getPort() {
		return this.portProperty().get();
	}

	public final void setPort(final org.dajlab.jcontrollab.core.OutputPortEnum port) {
		this.portProperty().set(port);
	}

	public final StringProperty labelProperty() {
		return this.label;
	}

	public final java.lang.String getLabel() {
		return this.labelProperty().get();
	}

	public final void setLabel(final java.lang.String label) {
		this.labelProperty().set(label);
	}

	public final BooleanProperty onOffProperty() {
		return this.onOff;
	}

	public final boolean isOnOff() {
		return this.onOffProperty().get();
	}

	public final void setOnOff(final boolean onOff) {
		this.onOffProperty().set(onOff);
	}

	public final IntegerProperty powerProperty() {
		return this.power;
	}

	public final int getPower() {
		return this.powerProperty().get();
	}

	public final void setPower(final int power) {
		this.powerProperty().set(power);
	}

	public final BooleanProperty reverseProperty() {
		return this.reverse;
	}

	public final boolean isReverse() {
		return this.reverseProperty().get();
	}

	public final void setReverse(final boolean reverse) {
		this.reverseProperty().set(reverse);
	}

	public final IntegerProperty startOnProperty() {
		return this.startOn;
	}

	public final int getStartOn() {
		return this.startOnProperty().get();
	}

	public final void setStartOn(final int startOn) {
		this.startOnProperty().set(startOn);
	}

	public final IntegerProperty stopOnProperty() {
		return this.stopOn;
	}

	public final int getStopOn() {
		return this.stopOnProperty().get();
	}

	public final void setStopOn(final int stopOn) {
		this.stopOnProperty().set(stopOn);
	}

	public final IntegerProperty startOffProperty() {
		return this.startOff;
	}

	public final int getStartOff() {
		return this.startOffProperty().get();
	}

	public final void setStartOff(final int startOff) {
		this.startOffProperty().set(startOff);
	}

	public final IntegerProperty stopOffProperty() {
		return this.stopOff;
	}

	public final int getStopOff() {
		return this.stopOffProperty().get();
	}

	public final void setStopOff(final int stopOff) {
		this.stopOffProperty().set(stopOff);
	}

	public final IntegerProperty consoleNumberProperty() {
		return this.consoleNumber;
	}

	public final int getConsoleNumber() {
		return this.consoleNumberProperty().get();
	}

	public final void setConsoleNumber(final int consoleNumber) {
		this.consoleNumberProperty().set(consoleNumber);
	}

}
