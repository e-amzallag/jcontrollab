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

import java.io.IOException;
import java.io.OutputStream;

/**
 * Keep alive thread. Send regularly the HELLO message to the interface to check
 * its presence.
 * 
 * @author Erik Amzallag
 */
public class HelloThread extends Thread {
	/**
	 * Output stream.
	 */
	private OutputStream os;

	/**
	 * Connected status.
	 */
	private boolean connected = false;

	/**
	 * Construct a new hello thread.
	 * 
	 * @param os
	 *            output stream.
	 */
	public HelloThread(final OutputStream os) {

		this.os = os;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {

		connected = true;
		while (connected) {
			try {
				synchronized (os) {
					os.write(CLIProtocol.HELLO);
				}
			} catch (IOException e) {
				disconnect();
			}
			try {
				sleep(CLIProtocol.DELAY);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Disconnect.
	 */
	public void disconnect() {

		connected = false;
	}
}
