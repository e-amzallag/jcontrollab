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

import org.dajlab.core.DaJLabModule;
import org.dajlab.jcontrollab.core.ControlLabInterface;
import org.dajlab.jcontrollab.core.Output;
import org.dajlab.jcontrollab.gui.model.OutputModel;

/**
 * Module for random time.
 * 
 * @author Erik Amzallag
 *
 */
public class SimpleRandomModule extends DaJLabModule {

	/**
	 * Output model.
	 */
	private OutputModel outputModel;

	/**
	 * Ouput.
	 */
	private Output output;

	/**
	 * Thread.
	 */
	private Thread thread;

	/**
	 * Constructor.
	 * 
	 * @param controlLab
	 *            control lab interface
	 * @param outputModel
	 *            output model
	 */
	public SimpleRandomModule(final ControlLabInterface controlLab, final OutputModel outputModel) {

		this.outputModel = outputModel;
		output = controlLab.getOuput(outputModel.getPort());
		output.setPower(outputModel.getPower());
		if (!outputModel.isReverse()) {
			output.forward();
		} else {
			output.backward();
		}
		if (outputModel.isOnOff()) {
			thread = launch();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void codeThread() {
		int timeOn;
		int timeOff;

		timeOn = calculateTime(outputModel.getStartOn(), outputModel.getStopOn());
		System.out.println("temps de marche calcule = " + timeOn);
		output.run();
		try {
			Thread.sleep(timeOn);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		timeOff = calculateTime(outputModel.getStartOff(), outputModel.getStopOff());
		System.out.println("temps d'arret calcule = " + timeOff);
		if (timeOff > 0) {
			output.stop();
			try {
				Thread.sleep(timeOff);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

	}

	/**
	 * Pick a random value inside a range.
	 * 
	 * @param lowValue
	 *            low value
	 * @param highValue
	 *            high value
	 * @return a random value between low and high value
	 */
	private int calculateTime(final int lowValue, final int highValue) {

		if (lowValue == highValue) {
			return lowValue;
		}
		int time = 0;
		int rand = ((int) (Math.random() * 1000));
		System.out.println("rand = " + rand);
		time = lowValue + rand % (highValue - lowValue);
		return time * 1000;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLaunch() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStop() {
		output.stop();
		if (isRunning()) {
			thread.interrupt();
		}
	}

	/**
	 * Restart a new tread.
	 */
	public void startMotor() {

		thread = launch();
	}

	/**
	 * Set power.
	 * 
	 * @param power
	 *            power
	 */
	public void setPower(final int power) {
		output.setPower(power);
	}

	/**
	 * Reverse the ouput.
	 */
	public void reverse() {
		output.reverseDirection();
	}

	/**
	 * Update range.<br>
	 * In this implementation, no action on change. The change will be taken in
	 * account at the next iteration of start or stop.
	 */
	public void updateRange() {

	}

}
