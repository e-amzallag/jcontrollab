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

import java.util.HashMap;
import java.util.Map;

import org.dajlab.jcontrollab.core.OutputPortEnum;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model for a control lab.
 * 
 * @author Erik Amzallag
 *
 */
public class ConsoleModel {

	/**
	 * Control lab number.
	 */
	private IntegerProperty number = new SimpleIntegerProperty();
	/**
	 * Serial Port.
	 */
	private StringProperty port = new SimpleStringProperty();
	/**
	 * Tab title.
	 */
	private StringProperty title = new SimpleStringProperty();

	/**
	 * Map for each output model.
	 */
	private Map<OutputPortEnum, OutputModel> outputModelsMap = new HashMap<>(OutputPortEnum.values().length);

	/**
	 * Constructor.
	 * 
	 * @param number
	 *            number
	 * @param title
	 *            title
	 * @param port
	 *            port
	 */
	public ConsoleModel(int number, String title, String port) {
		this.number.set(number);
		this.title.set(title);
		this.port.set(port);

		for (OutputPortEnum portEnum : OutputPortEnum.values()) {
			OutputModel outModel = new OutputModel(portEnum);
			outputModelsMap.put(portEnum, outModel);
		}
	}

	/**
	 * @return the outputModelsMap
	 */
	public final Map<OutputPortEnum, OutputModel> getOutputModelsMap() {
		return outputModelsMap;
	}

	/**
	 * @param outputModelsMap
	 *            the outputModelsMap to set
	 */
	public final void setOutputModelsMap(Map<OutputPortEnum, OutputModel> outputModelsMap) {
		this.outputModelsMap = outputModelsMap;
	}

	public final IntegerProperty numberProperty() {
		return this.number;
	}

	public final int getNumber() {
		return this.numberProperty().get();
	}

	public final void setNumber(final int number) {
		this.numberProperty().set(number);
	}

	public final StringProperty portProperty() {
		return this.port;
	}

	public final java.lang.String getPort() {
		return this.portProperty().get();
	}

	public final void setPort(final java.lang.String port) {
		this.portProperty().set(port);
	}

	public final StringProperty titleProperty() {
		return this.title;
	}

	public final java.lang.String getTitle() {
		return this.titleProperty().get();
	}

	public final void setTitle(final java.lang.String title) {
		this.titleProperty().set(title);
	}

}
