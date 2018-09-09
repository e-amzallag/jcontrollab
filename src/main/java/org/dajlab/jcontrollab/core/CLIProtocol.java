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
 * Communication protocol with the Dacta interface.
 * 
 * @author Erik Amzallag.
 * 
 */
public class CLIProtocol {
	/**
	 * NOP message.
	 */
	protected final static short NOP = 2;

	/**
	 * OUTPUT_SINGLE_ON_BACKWARD message.
	 */
	protected static final int OUTPUT_SINGLE_ON_BACKWARD = 16;

	/**
	 * OUTPUT_SINGLE_ON_FORWARD message.
	 */
	protected static final int OUTPUT_SINGLE_ON_FORWARD = 24;

	/**
	 * OUTPUT_SINGLE_REVERSE_DIRECTION message.
	 */
	protected final static int OUTPUT_SINGLE_REVERSE_DIRECTION = 32;

	/**
	 * OUTPUT_SINGLE_ON message.
	 */
	protected final static int OUTPUT_SINGLE_ON_CURRENT_DIRECTION = 40;

	/**
	 * OUTPUT_SINGLE_OFF message.
	 */
	protected final static int OUTPUT_SINGLE_OFF = 48;

	/**
	 * OUTPUT_SINGLE_BACKWARD message.
	 */
	protected final static int OUTPUT_SINGLE_BACKWARD_DIRECTION = 64;

	/**
	 * OUTPUT_SINGLE_FORWARD message.
	 */
	protected final static int OUTPUT_SINGLE_FORWARD_DIRECTION = 72;

	/**
	 * CLOSE message.
	 */
	protected final static byte CLOSE = (byte) 112;

	/**
	 * OUTPUT_MULTIPLE_OFF_BREAK message.
	 */
	protected final static int OUTPUT_MULTIPLE_OFF_BREAK = 128;

	/**
	 * OUTPUT_MULTIPLE_FLASHING message.
	 */
	protected final static int OUTPUT_MULTIPLE_FLASHING = 129;

	/**
	 * OUTPUT_MULTIPLE_OFF message.
	 */
	protected final static int OUTPUT_MULTIPLE_OFF = 144;

	/**
	 * OUTPUT_MULTIPLE_ON message.
	 */
	protected final static int OUTPUT_MULTIPLE_ON = 145;

	/**
	 * OUTPUT_MULTIPLE_POWER0 message.
	 */
	protected final static int OUTPUT_MULTIPLE_POWER0 = 146;

	/**
	 * OUTPUT_MULTIPLE_FORWARD message.
	 */
	protected final static int OUTPUT_MULTIPLE_FORWARD_DIRECTION = 147;

	/**
	 * OUTPUT_MULTIPLE_BACKWARD message.
	 */
	protected final static int OUTPUT_MULTIPLE_BACKWARD_DIRECTION = 148;

	/**
	 * OUTPUT_MULTIPLE_REVERSE_DIRECTION message.
	 */
	protected final static int OUTPUT_MULTIPLE_REVERSE_DIRECTION = 149;

	/**
	 * OUTPUT_MULTIPLE_POWER message.
	 */
	protected final static int OUTPUT_MULTIPLE_POWER = 176;

	/**
	 * OUTPUT_MULTIPLE_ON_TIME message.
	 */
	protected final static int OUTPUT_MULTIPLE_ON_TIME = 192;

	/**
	 * CYCLE_TIME_OFF message.
	 */
	protected final static int CYCLE_TIME_OFF = 224;

	/**
	 * CYCLE_TIME_ON message.
	 */
	protected final static int CYCLE_TIME_ON = 232;

	/**
	 * HELLO message.
	 */
	protected final static byte HELLO = 0x02;

	/**
	 * DELAY to wait between HELLO messages.
	 */
	protected final static int DELAY = 2000;

	/**
	 * First output.
	 */
	private final static char FIRST_OUTPUT_PORT = OutputPortEnum.A.getPort();

	/**
	 * Last output.
	 */
	private final static char LAST_OUTPUT_PORT = OutputPortEnum.H.getPort();

	/**
	 * KNOCK_KNCOK message.
	 */
	protected final static String KNOCK_KNCOK = "p\0###Do you byte, when I knock?$$$";

	/**
	 * Encode a simple message.
	 * 
	 * @param output
	 *            an output port
	 * @param mode
	 *            a mode
	 * @return the encoded message
	 */
	protected static byte encodeMode(char output, int mode) {
		return (byte) (mode + output - FIRST_OUTPUT_PORT);
	}

	/**
	 * Encode a simple message with power.
	 * 
	 * @param output
	 *            an output port
	 * @param power
	 *            a power
	 * @return the encoded message
	 */
	protected static byte[] encodeModePower(char output, short power) {
		byte[] code = new byte[2];
		if (power > 0) {
			code[0] = (byte) (OUTPUT_MULTIPLE_POWER + power - 1);
		} else {
			code[0] = (byte) OUTPUT_MULTIPLE_POWER0;
		}
		code[1] = (byte) Math.pow(2, (output - FIRST_OUTPUT_PORT));
		return code;
	}

	/**
	 * Encode a message of type ON with a duration.
	 * 
	 * @param output
	 *            an ouput port
	 * @param time
	 *            time to on
	 * @return the encoded message
	 */
	protected static byte[] encodeModeDuration(char output, int time) {
		byte[] code = new byte[2];
		code[0] = (byte) (CLIProtocol.OUTPUT_MULTIPLE_ON_TIME + (output - FIRST_OUTPUT_PORT));
		code[1] = (byte) time;
		return code;
	}

	/**
	 * Encode a message of type ON with a duration.
	 * 
	 * @param outputs
	 *            outputs array
	 * @param time
	 *            time to on
	 * @return the encoded message
	 */
	protected static byte[] encodeModeDuration(char[] outputs, int time) {
		byte[] code = new byte[2 * outputs.length];
		for (int i = 0; i < outputs.length; i++) {
			code[i * 2] = (byte) (CLIProtocol.OUTPUT_MULTIPLE_ON_TIME + (outputs[i] - FIRST_OUTPUT_PORT));
			code[i * 2 + 1] = (byte) time;
		}
		return code;
	}

	/**
	 * Encode a message for multiple outputs.
	 * 
	 * @param outputs
	 *            outputs array
	 * @param mode
	 *            mode
	 * @return the encoded message
	 */
	protected static byte[] encodeMode(char[] outputs, int mode) {
		byte[] code = new byte[2];
		int code2 = 0;
		for (int i = 0; i < outputs.length; i++) {
			if ((FIRST_OUTPUT_PORT <= outputs[i]) && (outputs[i] <= LAST_OUTPUT_PORT)) {
				code2 += (int) Math.pow(2, (outputs[i] - FIRST_OUTPUT_PORT));
			}
		}
		code[0] = (byte) mode;
		code[1] = (byte) code2;
		return code;
	}

	/**
	 * Encode a message for multiple outputs with power.
	 * 
	 * @param outputs
	 *            outputs array
	 * @param power
	 *            power
	 * @return the encoded message
	 */
	protected static byte[] encodeModePower(char[] outputs, short power) {
		byte[] code = new byte[2];
		if (power > 0) {
			code[0] = (byte) (OUTPUT_MULTIPLE_POWER + power - 1);
		} else {
			code[0] = (byte) OUTPUT_MULTIPLE_POWER0;
		}
		int code2 = 0;
		for (int i = 0; i < outputs.length; i++) {
			if ((FIRST_OUTPUT_PORT <= outputs[i]) && (outputs[i] <= LAST_OUTPUT_PORT)) {
				code2 += (int) Math.pow(2, (outputs[i] - FIRST_OUTPUT_PORT));
			}
		}
		code[1] = (byte) code2;
		return code;
	}

	/**
	 * Encode a cycle message.
	 * 
	 * @param output
	 *            output port
	 * @param cycleOn
	 *            time the output is on (in 1/10 seconds)
	 * @param cycleOff
	 *            time the output is off (in 1/10 seconds)
	 * @return the encoded message
	 */
	protected static byte[] encodeCycle(char output, int cycleOn, int cycleOff) {
		byte[] code = new byte[6];
		// TODO ??
		int code2 = 0;
		if ((FIRST_OUTPUT_PORT <= output) && (output <= LAST_OUTPUT_PORT)) {
			code2 += (int) Math.pow(2, (output - FIRST_OUTPUT_PORT));
		}
		code[0] = (byte) (CLIProtocol.CYCLE_TIME_ON + (output - FIRST_OUTPUT_PORT));
		code[1] = (byte) cycleOn;
		code[2] = (byte) (CLIProtocol.CYCLE_TIME_OFF + (output - FIRST_OUTPUT_PORT));
		code[3] = (byte) cycleOff;
		return code;
	}

	/**
	 * Encode a cycle message for multiple outputs.
	 * 
	 * @param outputs
	 *            outputs array
	 * @param cycleOn
	 *            time the output is on (in 1/10 seconds)
	 * @param cycleOff
	 *            time the output is off (in 1/10 seconds)
	 * @return the encoded message
	 */
	protected static byte[] encodeCycle(char[] outputs, int cycleOn, int cycleOff) {
		byte[] code = new byte[4 * outputs.length];
		for (int i = 0; i < outputs.length; i++) {
			code[i * 4] = (byte) (CLIProtocol.CYCLE_TIME_ON + (outputs[i] - FIRST_OUTPUT_PORT));
			code[i * 4 + 1] = (byte) cycleOn;
			code[i * 4 + 2] = (byte) (CLIProtocol.CYCLE_TIME_OFF + (outputs[i] - FIRST_OUTPUT_PORT));
			code[i * 4 + 3] = (byte) cycleOff;
		}
		return code;
	}
}
