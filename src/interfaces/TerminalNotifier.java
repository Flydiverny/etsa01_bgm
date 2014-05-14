package interfaces;

import interfaces.hardware.PinCodeTerminal;

public interface TerminalNotifier {
	/**
	 * Green light blinks three times.
	 * @param terminal - terminal to blink at.
	 */
	void NF1();
	
	/**
	 * Green light as long as the door is unlocked.
	 * @param terminal - terminal to blink at.
	 */
	void NF2();
	
	/**
	 * Red light blinks three times.
	 * @param terminal - terminal to blink at.
	 */
	void NF3();
	
	/**
	 * Red and Green lights blinks intermittently 3 times.
	 * @param terminal - terminal to blink at.
	 */
	void NF4();
	
	/**
	 * Green light is on for 3 seconds.
	 * @param terminal - terminal to blink at.
	 */
	void NF5();
	
	/**
	 * Red light is on for 3 seconds.
	 * @param terminal - terminal to blink at.
	 */
	void NF6();
}
