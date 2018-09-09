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

import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * Describe an input.
 * 
 * @author Erik Amzallag
 *
 */
public abstract class Input extends AbstractPort implements InputInterface {

	/**
	 * Input port.
	 */
	private InputPortEnum port;

	/**
	 * List of listeners.
	 */
	private final EventListenerList listeners = new EventListenerList();

	/**
	 * Previous value.
	 */
	private short previousValue;

	/**
	 * Current value.
	 */
	private short value;

	/**
	 * Status.
	 */
	private int status;

	/**
	 * Constructor.
	 * 
	 * @param controllab
	 *            control lab
	 * @param port
	 *            input port
	 */
	protected Input(final ControlLabInterface controllab, final InputPortEnum port) {

		setControlLab(controllab);
		this.port = port;
	}

	/**
	 * Set the value of the sensor.
	 * 
	 * @param aValue
	 *            the new value.
	 */
	protected void setValue(short aValue) {

		previousValue = this.value;
		this.value = aValue;
		fireValueChanged();
	}

	/**
	 * Set the status of the sensor.
	 * 
	 * @param aStatus
	 *            status
	 */
	protected void setStatus(final int aStatus) {

		this.status = aStatus;
	}

	/**
	 * Fire value to sensor listeners.
	 */
	private void fireValueChanged() {
		if (value != previousValue) {
			SensorEvent event = null;
			for (int i = 0; i < getSensorListeners().length; i++) {
				SensorListener sl = (SensorListener) getSensorListeners()[i];
				if (event == null) {
					event = new SensorEvent(this, getSensorType(), previousValue, value);
				}
				sl.valueChanged(event);
			}
		}
	}

	/**
	 * @return the port
	 */
	public final InputPortEnum getPort() {
		return port;
	}

	/**
	 * Add a listener.
	 * 
	 * @param aListener
	 *            the listener to add
	 */
	public void addSensorListener(SensorListener aListener) {

		listeners.add(SensorListener.class, aListener);
	}

	/**
	 * Remove a listener.
	 * 
	 * @param aListener
	 *            the listener to remove.
	 */
	public void removeSensorListener(SensorListener aListener) {

		listeners.remove(SensorListener.class, aListener);
	}

	/**
	 * 
	 * @return the list of the sensor listeners.
	 */
	public EventListener[] getSensorListeners() {

		return listeners.getListeners(SensorListener.class);
	}

	/**
	 * @return the value
	 */
	public final short getValue() {
		return value;
	}

	/**
	 * @return the status
	 */
	public final int getStatus() {
		return status;
	}

	/**
	 * Return the type of sensor.
	 * 
	 * @return the type
	 */
	public abstract SensorTypeEnum getSensorType();

}
