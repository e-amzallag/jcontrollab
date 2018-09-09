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
import org.dajlab.jcontrollab.core.SensorEvent;
import org.dajlab.jcontrollab.core.SensorListener;
import org.dajlab.jcontrollab.core.TouchSensor;

/**
 * Touch sensor example.
 * 
 * @author Erik Amzallag
 *
 */
public class TouchSensorExample {

	public static void main(String[] args) {
		try {
			ControlLabManager manager = new ControlLabManager();
			manager.connect();
			if (manager.getControlLabs().size() > 0) {
				ControlLabInterface controller = manager.getControlLabs().get(0);
				// New touch sensor
				TouchSensor ts = controller.getInput(TouchSensor.class, InputPortEnum.I1);
				ts.addSensorListener(new SensorListener() {

					@Override
					public void valueChanged(SensorEvent anEvent) {
						TouchSensor ts = (TouchSensor) anEvent.getSource();
						if (ts.isPressed()) {
							System.out.format("Touch : raw value = %s ; Sensor is pressed.\n", anEvent.getNewValue());
						}
						if (ts.isReleased()) {
							System.out.format("Touch : raw value = %s ; Sensor is released.\n", anEvent.getNewValue());
						}
					}
				});

				Thread.sleep(5000);
				// Changing the sensibility for pressing in. By default, 300
				ts.setSensibilityIn(50);
				System.out.println("Press stronger !");
				Thread.sleep(5000);
			}
			manager.disconnect();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
