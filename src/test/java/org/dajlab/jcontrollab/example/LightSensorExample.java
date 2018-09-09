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

import org.dajlab.jcontrollab.core.ControlLabInterface;
import org.dajlab.jcontrollab.core.ControlLabManager;
import org.dajlab.jcontrollab.core.InputPortEnum;
import org.dajlab.jcontrollab.core.LightSensor;
import org.dajlab.jcontrollab.core.SensorEvent;
import org.dajlab.jcontrollab.core.SensorListener;

/**
 * Light sensor example.
 * 
 * @author Erik Amzallag
 *
 */
public class LightSensorExample {

	public static void main(String[] args) {
		try {
			ControlLabManager manager = new ControlLabManager();
			manager.connect();
			if (manager.getControlLabs().size() > 0) {
				ControlLabInterface controller = manager.getControlLabs().get(0);
				// New light sensor
				LightSensor ls = controller.getInput(LightSensor.class, InputPortEnum.I5);
				// Initialise the default light value with the current value.
				ls.reset();
				// Adding a listener on it
				ls.addSensorListener(new SensorListener() {
					public void valueChanged(SensorEvent anEvent) {
						System.out.println("Light = " + ((LightSensor) anEvent.getSource()).getLight());
						if (((LightSensor) anEvent.getSource()).hasLessLight()) {
							System.out.println("Less light.");
						} else if (((LightSensor) anEvent.getSource()).hasMoreLight()) {
							System.out.println("More light.");
						}
					}
				});
				Thread.sleep(15000);
			}
			manager.disconnect();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
