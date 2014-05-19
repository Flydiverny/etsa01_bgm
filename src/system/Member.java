package system;

import interfaces.IBicycle;
import interfaces.IMember;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Member implements Serializable, IMember {
	private static final long serialVersionUID = 1015358464126121453L;

	private String name, adderss, phone, ssn, pin;
	private boolean enabled = true;
	
	private List<IBicycle> bicycles;
	
	public Member(String name, String addr, String phone, String ssn, String pin) {
		bicycles = new LinkedList<IBicycle>();
	}
	
	@Override
	public void enable(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void setPIN(String pin) {
		this.pin = pin;
	}

	@Override
	public int amountOfBicycles() {
		return bicycles.size();
	}

	@Override
	public void registerBicycle(String desc) {
		bicycles.add(new Bicycle(this, desc));
	}

	@Override
	public void removeBicycle(String barcodeId) {
		Iterator<IBicycle> itr = bicycles.iterator();
		
		while(itr.hasNext()) {
			IBicycle b = itr.next();
			
			if(b.getBarcode().equals(barcodeId)) {
				itr.remove();
				break;
			}
		}
	}

	@Override
	public List<interfaces.IBicycle> getBicycles() {
		return bicycles;
	}

	@Override
	public void setAddress(String addr) {
		this.adderss = addr;
	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAddress() {
		return this.adderss;
	}

	@Override
	public String getPhone() {
		return this.phone;
	}

	@Override
	public String getSSN() {
		return this.ssn;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPIN() {
		return this.pin;
	}

	@Override
	public boolean isDisabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
