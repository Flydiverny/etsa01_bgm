package system;

import interfaces.Bicycle;
import interfaces.Member;
import interfaces.MemberManager;
import interfaces.hardware.BarcodePrinter;
import interfaces.hardware.ElectronicLock;
import interfaces.hardware.PinCodeTerminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BicycleGarageManager implements interfaces.BicycleGarageManager {
	private BarcodePrinter printer;
	private ElectronicLock entryLock, exitLock;
	private PinCodeTerminal entryTerm, exitTerm;
	
	private MemberManager mm;
	
	private String operatorPassword;
	private String operatorPIN;
	
	private int monthlyFee;
	private int bicycleFee;
	
	private int unlockDuration; // Duration door remains unlocked
	private int garageSize; // Limit max amount of checked in bicycles.
	
	
	
	public BicycleGarageManager() {
		//mm = new MemberManager();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitBarcode(String bicycleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entryCharacter(char c) {
		entryTerm.lightLED(1, 1);
	}

	@Override
	public void exitCharacter(char c) {
		exitTerm.lightLED(0,1);
	}

	@Override
	public void printBarcode(Bicycle bicycle) {
		printer.printBarcode(bicycle.getBarcode());
	}

	@Override
	public String genereateOperatorPIN() {
		Random rng = new Random();
		StringBuilder newPIN = new StringBuilder();
		
		for(int i = 0; i < 8; i++) {
			newPIN.append(rng.nextInt(10));
		}	
		
		operatorPIN = newPIN.toString();
		
		return operatorPIN;
	}

	@Override
	public boolean setOperatorPassword(String oldPasswd, String newpasswd,
			String newpasswd2) {
		if(!oldPasswd.equals(this.operatorPassword) || !newpasswd.equals(newpasswd2))
			return false;
		
		this.operatorPassword = newpasswd;
		
		return true;
	}

	@Override
	public void setUnlockDuration(int time) {
		this.unlockDuration = time;
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
	public List<Bicycle> getCheckedInBicycles() {
		List<Bicycle> checkedIn = new ArrayList<Bicycle>();
		List<Member> members = mm.listMembers();
		
		for(Member m : members) {
			for(Bicycle b : m.getBicycles())
				if(b.isCheckedIn())
					checkedIn.add(b);
		}
		
		return checkedIn;
	}

	@Override
	public int getAmountOfCheckedInBicycles() {
		return this.getCheckedInBicycles().size();
	}

	@Override
	public boolean checkInBicycleByBarcode(String barcode) {
		List<Member> members = mm.listMembers();
		
		for(Member m : members) {
			for(Bicycle b : m.getBicycles())
				if(b.getBarcode().equals(barcode)) {
					b.setCheckedIn(true);
					return true;
				}
		}

		return false;
	}

	@Override
	public void setMonthlyFee(int fee) {
		this.monthlyFee = monthlyFee;
	}

	@Override
	public void setBikeFee(int fee) {
		this.bicycleFee = bicycleFee;
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
	public Map<Member, Integer> getPaymentInfo() {
		Map<Member,Integer> map = new HashMap<Member, Integer>();
		List<Member> members = mm.listMembers();
		
		for(Member m : members) {
			map.put(m, this.getMonthlyFee() + m.getBicycles().size() * this.getBikeFee());
		}
		
		return map;
	}

}
