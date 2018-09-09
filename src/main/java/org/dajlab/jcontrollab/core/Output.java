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

/**
 * Describe an output.
 * 
 * @author Erik Amzallag
 *
 */
public class Output extends AbstractPort implements OutputInterface {

	/**
	 * Output port.
	 */
	private OutputPortEnum port;

	/**
	 * Constructor.
	 * 
	 * @param controllab
	 *            control lab
	 * @param port
	 *            output port
	 */
	protected Output(ControlLabInterface controllab, OutputPortEnum port) {

		setControlLab(controllab);
		this.port = port;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void forwardAndRun() {
		getControlLab().sendMessage(CLIProtocol.encodeMode(port.getPort(), CLIProtocol.OUTPUT_SINGLE_ON_FORWARD));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void backwardAndRun() {
		getControlLab().sendMessage(CLIProtocol.encodeMode(port.getPort(), CLIProtocol.OUTPUT_SINGLE_ON_BACKWARD));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reverseDirection() {
		getControlLab()
				.sendMessage(CLIProtocol.encodeMode(port.getPort(), CLIProtocol.OUTPUT_SINGLE_REVERSE_DIRECTION));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		getControlLab()
				.sendMessage(CLIProtocol.encodeMode(port.getPort(), CLIProtocol.OUTPUT_SINGLE_ON_CURRENT_DIRECTION));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop() {
		getControlLab().sendMessage(CLIProtocol.encodeMode(port.getPort(), CLIProtocol.OUTPUT_SINGLE_OFF));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void backward() {
		getControlLab()
				.sendMessage(CLIProtocol.encodeMode(port.getPort(), CLIProtocol.OUTPUT_SINGLE_BACKWARD_DIRECTION));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void forward() {
		getControlLab()
				.sendMessage(CLIProtocol.encodeMode(port.getPort(), CLIProtocol.OUTPUT_SINGLE_FORWARD_DIRECTION));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPower(final int power) {

		int aPower = Math.abs(power) % 9;
		getControlLab().sendMessage(CLIProtocol.encodeModePower(port.getPort(), (short) aPower));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runFor(final int time) {
		getControlLab().sendMessage(CLIProtocol.encodeModeDuration(port.getPort(), time));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCyclicMode(final int on, final int off) {
		getControlLab().sendMessage(CLIProtocol.encodeCycle(port.getPort(), on, off));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startCycle() {
		char[] tab = { port.getPort() };
		getControlLab().sendMessage(CLIProtocol.encodeMode(tab, CLIProtocol.OUTPUT_MULTIPLE_FLASHING));
	}

	/**
	 * @return the port
	 */
	public final OutputPortEnum getPort() {
		return port;
	}

}
