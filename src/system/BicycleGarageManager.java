package system;

import interfaces.IBicycle;
import interfaces.IBicycleGarageManager;
import interfaces.IMember;
import interfaces.IMemberManager;
import interfaces.ITerminalNotifier;
import interfaces.hardware.BarcodePrinter;
import interfaces.hardware.ElectronicLock;
import interfaces.hardware.PinCodeTerminal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BicycleGarageManager implements Serializable, IBicycleGarageManager {
	private static final long serialVersionUID = 6325212317583026360L;
	
	private transient BarcodePrinter printer;
	private transient ElectronicLock entryLock, exitLock;
	private transient PinCodeTerminal entryTerm, exitTerm;
	
	private IMemberManager mm;
	private transient ITerminalNotifier led;
	
	private String operatorPassword = "";
	private String operatorPIN;
	
	private int monthlyFee;
	private int bicycleFee;
	
	private int unlockDuration = 5; // Duration door remains unlocked
	private int garageSize = 0; // Limit max amount of checked in bicycles, value set at installation.
	
	private transient IBicycle exitingBicycle = null;
	
	private long entryResetTime = 0;
	private long exitResetTime = 0;
	
	enum State {
		AWAITING_PIN,
		AWAITING_SCAN,
		AWAITING_OP,
		AWAITING_OPERATOR
	}
	
	private char[] entryBuffer = new char[8];
	private char[] exitBuffer = new char[8];
	
	private State entryState = State.AWAITING_OP;
	private State exitState = State.AWAITING_OP;
	
	public BicycleGarageManager() {
		this.mm = new MemberManager();
		led();
	}
	
	private ITerminalNotifier led() {
		if(led == null)
			led = new TerminalNotifier(this);
		
		return led;
	}
	
	@Override
	public void registerHardwareDrivers(BarcodePrinter printer,
			ElectronicLock entryLock, ElectronicLock exitLock,
			PinCodeTerminal entryTerm, PinCodeTerminal exitTerm) {
		this.printer = printer;
		this.entryLock = entryLock;
		this.exitLock = exitLock;
		this.entryTerm = entryTerm;
		this.exitTerm = exitTerm;
	}

	@Override
	public void entryBarcode(String bicycleId) {
		checkEntryResetTime();
		
		if(entryState != State.AWAITING_SCAN) //TODO Perhaps a LED NF3 or something
			return;
		
		IBicycle b = mm.getBicycle(bicycleId);
		
		if(b == null) {
			led().NF3(entryTerm);
			return;
		}
		b.setCheckedIn(true);
		
		entryLock.open(this.getUnlockDuration());
		led().NF2(entryTerm);
		
		entryState = State.AWAITING_OP;
	}

	@Override
	public void exitBarcode(String bicycleId) {
		checkExitResetTime();
		
		if(exitState != State.AWAITING_SCAN) //TODO Perhaps a LED NF3 or something
			return;
		
		IBicycle b = mm.getBicycle(bicycleId);
		if(!b.isCheckedIn()) {
			led().NF4(exitTerm);
			return;
		}
		
		exitingBicycle = b;
		
		led().NF5(exitTerm);
		exitState = State.AWAITING_PIN;
	}

	@Override
	public void entryCharacter(char c) {
		checkEntryResetTime();	
		
		bufferInput(c, entryBuffer);
		
		switch(entryState) {
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
	
	private void checkEntryResetTime() {
		if(System.currentTimeMillis() > entryResetTime) {
			entryState = State.AWAITING_OP;
			clearEntryBuffer();
		}
		
		entryResetTime = System.currentTimeMillis() + 5000;
	}
	
	private void checkExitResetTime() {
		if(System.currentTimeMillis() > exitResetTime) {
			exitState = State.AWAITING_SCAN;
			clearExitBuffer();
		}
		
		exitResetTime = System.currentTimeMillis() + 5000;
	}
	
	private void checkPIN() {
		if(entryBuffer[0] == '#') {
			clearEntryBuffer();
			return;
		}
		
		if(!bufferIsFull(entryBuffer))
			return;
		
		StringBuilder pin = new StringBuilder();
		
		for(int i = entryBuffer.length; i > 0; i--) {
			pin.append(entryBuffer[i-1]);
		}
		
		IMember m = mm.getMemberByPin(pin.toString());

		if(m == null) {
			led().NF3(entryTerm);
			clearEntryBuffer();
			entryState = State.AWAITING_OP;
			return;
		}
		
		for(IBicycle b : m.getBicycles()) {
			if(b.isCheckedIn()) {
				led().NF2(entryTerm);
				entryLock.open(this.getUnlockDuration());
				entryState = State.AWAITING_OP;
				return;
			}
		}
		
		// No checked in bicycle
		led().NF4(entryTerm);
		entryState = State.AWAITING_OP;
	}
	
	private boolean bufferIsFull(char[] buffer) {
		for(int i = 0; i < buffer.length; i++) {
			if(buffer[i] == '\u0000') //null character
				return false;
		}
		
		return true;
	}
	
	private void checkOpCode() {
		System.out.println(entryBuffer);
		if(entryBuffer[0] != '*')
			return;
		
		switch(entryBuffer[1]) {
		case '1': // op code 1
			entryState = State.AWAITING_SCAN;
			led().NF5(entryTerm);
			break;
		case '2':
			entryState = State.AWAITING_PIN;
			led().NF5(entryTerm);
			break;
		case '9':
			entryState = State.AWAITING_OPERATOR;
			led().NF5(entryTerm);
			break;
		default:
			// State remains the same.
			led().NF3(entryTerm);
			break;
		}
		
		clearEntryBuffer();
	}
	
	private void clearEntryBuffer() {
		entryBuffer = new char[entryBuffer.length];
	}
	
	private void clearExitBuffer() {
		exitBuffer = new char[exitBuffer.length];
	}
	
	private void bufferInput(char c, char[] buffer) {
		for(int i = buffer.length-1; i > 0; i--) {
			char t = buffer[i];
			buffer[i] = buffer[i-1];
			buffer[i-1] = t;
		}
		
		buffer[0] = c;
	}

	@Override
	public void exitCharacter(char c) {
		checkExitResetTime();
		
		// TODO Check for OP Code 9* (operator wants to leave the building)
		
		if(exitState != State.AWAITING_PIN) // TODO Perhaps a LED NF3 or something
			return;
		
		if(exitBuffer[0] == '#') {
			clearExitBuffer();
			return;
		}
		
		if(!bufferIsFull(exitBuffer))
			return;
		
		StringBuilder pin = new StringBuilder();
		
		for(int i = exitBuffer.length; i > 0; i--) {
			pin.append(exitBuffer[i-1]);
		}
		
		IMember m = exitingBicycle.getOwner();
		
		if(m.getPIN().equals(pin.toString())) {
			exitingBicycle.setCheckedIn(false);
			exitLock.open(this.getUnlockDuration());
			led().NF2(exitTerm);
			exitState = State.AWAITING_SCAN;
			return;
		}
		
		// Entered PIN doesn't match the bicycle owners PIN		
		led().NF4(exitTerm);
		exitState = State.AWAITING_SCAN;
	}

	@Override
	public void printBarcode(IBicycle bicycle) {
		printer.printBarcode(bicycle.getBarcode());
	}

	@Override
	public String genereateOperatorPIN() {
		Random rng = new Random();
		StringBuilder newPIN = new StringBuilder();
		
		for(int i = 0; i < 10; i++) {
			newPIN.append(rng.nextInt(10));
		}	
		
		operatorPIN = newPIN.toString();
		
		return operatorPIN;
	}

	@Override
	public boolean setOperatorPassword(String oldPasswd, String newpasswd,
			String newpasswd2) {
		if(!oldPasswd.equals(this.operatorPassword) || !newpasswd.equals(newpasswd2) || newpasswd.length() < 10 || newpasswd.length() > 32)
			return false;
		
		this.operatorPassword = newpasswd;
		
		return true;
	}
	//Lägga till tillåtna värden i user interface?
	@Override
	public void setUnlockDuration(int time) {
		if(!(time > 32 || time < 5)){
		this.unlockDuration = time;
		}
	}

	@Override
	public void setGarageSize(int size) {
		this.garageSize = size;
	}

	@Override
	public boolean loginOperator(String password) {
		return operatorPassword.equals(password);
	}

	@Override
	public List<IBicycle> getCheckedInBicycles() {
		List<IBicycle> checkedIn = new ArrayList<IBicycle>();
		List<IMember> members = mm.listMembers();
		
		for(IMember m : members) {
			for(IBicycle b : m.getBicycles()) {
				if(b.isCheckedIn())
					checkedIn.add(b);
			}
		}
		
		return checkedIn;
	}

	@Override
	public int getAmountOfCheckedInBicycles() {
		return this.getCheckedInBicycles().size();
	}

	@Override
	public boolean checkInBicycleByBarcode(String barcode) {
		List<IMember> members = mm.listMembers();
		
		for(IMember m : members) {
			for(IBicycle b : m.getBicycles())
				if(b.getBarcode().equals(barcode)) {
					b.setCheckedIn(true);
					return true;
				}
		}

		return false;
	}

	@Override
	public void setMonthlyFee(int fee) {
		this.monthlyFee = fee;
	}

	@Override
	public void setBikeFee(int fee) {
		this.bicycleFee = fee;
	}

	@Override
	public int getMonthlyFee() {
		return monthlyFee;
	}

	@Override
	public int getBikeFee() {
		return bicycleFee;
	}

	@Override
	public Map<IMember, Integer> getPaymentInfo() {
		Map<IMember,Integer> map = new HashMap<IMember, Integer>();
		List<IMember> members = mm.listMembers();
		
		for(IMember m : members) {
			map.put(m, this.getPaymentInfo(m));
		}
		
		return map;
	}

	public int getUnlockDuration() {
		return this.unlockDuration;
	}

	public int getPaymentInfo(IMember m) {
		return this.getMonthlyFee() + m.getBicycles().size() * this.getBikeFee();
	}

	public int getGarageSize() {
		return this.garageSize;
	}

	public IMemberManager getMemberManager() {
		return this.mm;
	}
	
	public String getOperatorPIN() {
		return operatorPIN;
	}
}
