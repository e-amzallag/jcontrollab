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
package org.dajlab.jcontrollab.core.serial.rxtx;

// Uncomment the following code if you want use RXTX.
// If you want use an other API implement Javax Communication, just change the
// import gnu.io.*

// import java.util.ArrayList;
// import java.util.Enumeration;
// import java.util.List;
//
// import gnu.io.CommPortIdentifier;
//
// public class SerialUtils {
//
// /**
// *
// * @return la liste des ports disponibles
// */
// public static List<String> getAvailablePorts() {
//
// Enumeration<CommPortIdentifier> portList =
// CommPortIdentifier.getPortIdentifiers();
// List<String> ports = new ArrayList<String>();
// while (portList.hasMoreElements()) {
// CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
// if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
// ports.add(portId.getName());
// }
// }
// return ports;
// }
// }
