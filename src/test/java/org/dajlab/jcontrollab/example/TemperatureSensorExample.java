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
import org.dajlab.jcontrollab.core.TemperatureSensor;

/**
 * Temperature sensor example.
 * 
 * @author Erik Amzallag
 *
 */
public class TemperatureSensorExample {

	public static void main(String[] args) {
		try {
			ControlLabManager manager = new ControlLabManager();
			manager.connect();
			if (manager.getControlLabs().size() > 0) {
				ControlLabInterface controller = manager.getControlLabs().get(0);
				// New temp sensor
				TemperatureSensor ts = controller.getInput(TemperatureSensor.class, InputPortEnum.I2);
				// Adding a listener on it
				ts.addSensorListener(new SensorListener() {
					public void valueChanged(SensorEvent anEvent) {
						TemperatureSensor source = (TemperatureSensor) anEvent.getSource();
						System.out.format("Temperature : raw value = %s <=> %.2f °C <=> %.2f °F\n",
								anEvent.getNewValue(), source.getDegreesCelsius(), source.getDegreesFahrenheit());
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
