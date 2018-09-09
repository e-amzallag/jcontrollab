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
import org.dajlab.jcontrollab.core.RotationSensor;
import org.dajlab.jcontrollab.core.SensorEvent;
import org.dajlab.jcontrollab.core.SensorListener;

/**
 * Rotation sensor example.
 * 
 * @author Erik Amzallag
 *
 */
public class RotationSensorExample {

	public static void main(String[] args) {
		try {
			ControlLabManager manager = new ControlLabManager();
			manager.connect();
			if (manager.getControlLabs().size() > 0) {
				ControlLabInterface controller = manager.getControlLabs().get(0);
				// New touch sensor
				RotationSensor rs = controller.getInput(RotationSensor.class, InputPortEnum.I8);
				// Adding a listener on it
				rs.addSensorListener(new SensorListener() {
					public void valueChanged(SensorEvent anEvent) {
						RotationSensor rs = (RotationSensor) anEvent.getSource();
						System.out.format("Rotation : raw value = %s ; %s ; angle = %2d\n", rs.getValue(),
								rs.getDirection(), rs.getAngle());
					}
				});
				Thread.sleep(15000);
				// reset of the angle.
				System.out.println("Reset of the angle");
				rs.setAngle(0);
				Thread.sleep(15000);
			}
			manager.disconnect();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
