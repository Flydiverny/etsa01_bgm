package interfaces;

import interfaces.hardware.PinCodeTerminal;

public interface TerminalNotifier {
	/**
	 * Green light blinks three times.
	 * @param terminal - terminal to blink at.
	 */
	void NF1(PinCodeTerminal terminal);
	
	/**
	 * Green light as long as the door is unlocked.
	 * @param terminal - terminal to blink at.
	 */
	void NF2(PinCodeTerminal terminal);
	
	/**
	 * Red light blinks three times.
	 * @param terminal - terminal to blink at.
	 */
	void NF3(PinCodeTerminal terminal);
	
	/**
	 * Red and Green lights blinks intermittently 3 times.
	 * @param terminal - terminal to blink at.
	 */
	void NF4(PinCodeTerminal terminal);
	
	/**
	 * Green light is on for 3 seconds.
	 * @param terminal - terminal to blink at.
	 */
	void NF5(PinCodeTerminal terminal);
	
	/**
	 * Red light is on for 3 seconds.
	 * @param terminal - terminal to blink at.
	 */
	void NF6(PinCodeTerminal terminal);
}
