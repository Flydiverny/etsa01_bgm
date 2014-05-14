package system;

import interfaces.Bicycle;
import interfaces.ITerminal;
import interfaces.Member;
import interfaces.hardware.PinCodeTerminal;

public class EntryTerminal implements ITerminal {

	private State state = State.AWAITING_OP;
	
	private char[] buffer = new char[8];
	
	private BicycleGarageManager bgm;
	private PinCodeTerminal terminal;
	
	public EntryTerminal(BicycleGarageManager bgm, PinCodeTerminal terminal) {
		this.bgm = bgm;
		this.terminal = terminal;
	}
	
	@Override
	public void input(char c) {
		buffer(c);
				
		switch(state) {
		case AWAITING_OP:
			checkOpCode();
			break;
		case AWAITING_OPERATOR:
			break;
		case AWAITING_PIN:
			checkPIN();
			break;
		case AWAITING_SCAN:
			break;
		default:
			break;
//			throw new InvalidActivityException("Invalid state of entry terminal, please investigate.");
		}
	}

	private void checkPIN() {
		if(!isFull())
			return;
		
		StringBuilder pin = new StringBuilder();
		
		for(int i = buffer.length; i > 0; i++) {
			pin.append(buffer[i-1]);
		}
		
		Member m = mm.getMemberByPin(pin.toString());
		
		if(m == null) {
			// NF Fail :D
			led.NF3(entryTerm);
			clear();
			state = State.AWAITING_OP;
			return;
		}
		
		for(Bicycle b : m.getBicycles()) {
			//if(b.isCheckedIn()) {
				led.NF2(entryTerm);
				entryLock.open(this.unlockDuration); //TODO use method access getUnlockDuration()
				entryState = State.AWAITING_OP;
			//}
		}
		// Check if user has checked in bicycles -> open door.
		// as in use case 9 
	}
	
	private void buffer(char c) {
		for(int i = buffer.length-1; i > 0; i--) {
			char t = buffer[i];
			buffer[i] = buffer[i-1];
			buffer[i-1] = t;
		}
		
		buffer[0] = c;
	}
	
	private boolean isFull() {
		for(int i = 0; i < buffer.length; i++) {
			if(buffer[i] == '\u0000') //null character
				return false;
		}
		
		return true;
	}
	
	private void checkOpCode() {
		if(buffer[0] != '*')
			return;
		
		switch(buffer[1]) {
		case '1': // op code 1
			state = State.AWAITING_SCAN;
			led.NF5(entryTerm);
			break;
		case '2':
			state = State.AWAITING_PIN;
			led.NF5(entryTerm);
			break;
		case '9':
			state = State.AWAITING_OPERATOR;
			led.NF5(entryTerm);
			break;
		default:
			// State remains the same.
			led.NF3(entryTerm);
			break;
		}
		
		clear();
	}
	
	private void clear() {
		buffer = new char[buffer.length];
	}

}
