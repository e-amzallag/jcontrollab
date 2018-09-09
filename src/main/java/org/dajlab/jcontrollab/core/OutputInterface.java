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
 * Output interface.
 * 
 * @author Erik Amzallag.
 * 
 */
public interface OutputInterface {
	/**
	 * Forward and start the output.
	 * 
	 */
	abstract void forwardAndRun();

	/**
	 * Backward and start the output.
	 * 
	 */
	abstract void backwardAndRun();

	/**
	 * Reverse direction of the output.
	 * 
	 */
	abstract void reverseDirection();

	/**
	 * Power on the output.
	 * 
	 */
	abstract void run();

	/**
	 * Power off the output.
	 * 
	 */
	abstract void stop();

	/**
	 * Backward but not start the output.
	 * 
	 */
	abstract void backward();

	/**
	 * Forward but not start the output.
	 * 
	 */
	abstract void forward();

	/**
	 * Set the power. <br>
	 * Value should be included between 0 and 8. Outside this range, absolute
	 * value modulo 9 is applied. <br>
	 * Caution : 0 value stops the output. <tt>Run</tt> instruction is required
	 * to restart.
	 * 
	 * @param power
	 *            a power.
	 */
	abstract void setPower(int power);

	/**
	 * Power on the output for the given time.
	 * 
	 * @param time
	 *            time to power on (in 1/10 seconds)
	 */
	abstract void runFor(int time);

	/**
	 * Set cyle mode. <br>
	 * The cycle has to be started programatically.
	 * 
	 * @param on
	 *            : time to power on (in 1/10s).
	 * @param off
	 *            : time to power off (in 1/10s).
	 * @see #startCycle()
	 */
	abstract void setCyclicMode(int on, int off);

	/**
	 * Start cycle mode. Ouput has to be started programatically.
	 * 
	 * @see #run()
	 * @see #forwardAndRun()
	 * @see #backwardAndRun()
	 */
	abstract void startCycle();
}
