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
package org.dajlab.jcontrollab.example;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.dajlab.jcontrollab.core.ControlLabInterface;
import org.dajlab.jcontrollab.core.ControlLabManager;
import org.dajlab.jcontrollab.core.Output;
import org.dajlab.jcontrollab.core.OutputPortEnum;

/**
 * Output example.
 * 
 * @author Erik Amzallag
 *
 */
public class OutputExample {

	public static void main(String[] args) {

		// Create the manager
		ControlLabManager manager = new ControlLabManager();
		// Detect and connect the devices
		manager.connect();
		// Get the devices
		List<ControlLabInterface> controllabs = manager.getControlLabs();
		if (CollectionUtils.isNotEmpty(controllabs)) {
			try {
				ControlLabInterface controlLab = controllabs.get(0);
				Output output = controlLab.getOuput(OutputPortEnum.A);
				output.setPower(8);
				output.forwardAndRun();
				Thread.sleep(2000);
				output.setPower(2);
				Thread.sleep(2000);
				output.stop();
				Thread.sleep(2000);
				output.reverseDirection();
				output.setPower(8);
				output.setCyclicMode(20, 20);
				output.startCycle();
				output.run();
				Thread.sleep(10000);
				output.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		manager.disconnect();
	}

}
