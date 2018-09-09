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

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a group of outputs, as the ControlLab Interface protocol allows
 * orders on several outputs simultaneously.
 * 
 * @author Erik Amzallag
 * 
 */
public class OutputsGroup implements OutputInterface {
	/**
	 * Map of different control lab interfaces with the associated outputs.
	 */
	private Map<String, Object[]> controlLabsMap;

	/**
	 * Constructor.
	 */
	public OutputsGroup() {

		controlLabsMap = new HashMap<String, Object[]>(4);
	}

	/**
	 * Add an output to the group. Outputs can be plugged to different control
	 * lab interfaces. <br>
	 * No order is guaranteed between the control lab interfaces.
	 * 
	 * @param output
	 *            the output to add to the group.
	 */
	public void addOutput(Output output) {

		String portController = output.getControlLab().getPort();
		if (!controlLabsMap.containsKey(portController)) {
			Object[] objs = new Object[2];
			objs[0] = output.getControlLab();
			StringBuffer buff = new StringBuffer(8);
			buff.append(output.getPort());
			objs[1] = buff;
			controlLabsMap.put(portController, objs);
		} else {
			Object[] objs = (Object[]) controlLabsMap.get(portController);
			StringBuffer buff = (StringBuffer) objs[1];
			buff.append(output.getPort());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void backwardAndRun() {

		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeMode(ports, CLIProtocol.OUTPUT_MULTIPLE_BACKWARD_DIRECTION));
			clc.sendMessage(CLIProtocol.encodeMode(ports, CLIProtocol.OUTPUT_MULTIPLE_ON));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void backward() {

		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeMode(ports, CLIProtocol.OUTPUT_MULTIPLE_BACKWARD_DIRECTION));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void forward() {

		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeMode(ports, CLIProtocol.OUTPUT_MULTIPLE_FORWARD_DIRECTION));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void forwardAndRun() {

		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeMode(ports, CLIProtocol.OUTPUT_MULTIPLE_FORWARD_DIRECTION));
			clc.sendMessage(CLIProtocol.encodeMode(ports, CLIProtocol.OUTPUT_MULTIPLE_ON));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void reverseDirection() {

		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeMode(ports, CLIProtocol.OUTPUT_MULTIPLE_REVERSE_DIRECTION));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {

		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeMode(ports, CLIProtocol.OUTPUT_MULTIPLE_ON));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void runFor(int time) {

		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeModeDuration(ports, time));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void setCyclicMode(int on, int off) {

		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeCycle(ports, on, off));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPower(final int power) {

		int aPower = Math.abs(power) % 9;
		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeModePower(ports, (short) aPower));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void startCycle() {

		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeMode(ports, CLIProtocol.OUTPUT_MULTIPLE_FLASHING));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void stop() {

		for (Object[] objs : controlLabsMap.values()) {
			ControlLabImpl clc = (ControlLabImpl) objs[0];
			char[] ports = ((StringBuffer) objs[1]).toString().toCharArray();
			clc.sendMessage(CLIProtocol.encodeMode(ports, CLIProtocol.OUTPUT_MULTIPLE_OFF));
		}
	}
}
