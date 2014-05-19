package system;

import interfaces.ILog;
import interfaces.IMember;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Bicycle implements Serializable, interfaces.IBicycle {

	private static final long serialVersionUID = 7848377332727864003L;
	private String barcode;
	private boolean checkedIn;
	private List<ILog> logEntries;
	private IMember owner;
	private String description;
	private Date registrationDate;
	
	public Bicycle (IMember owner, String description){
		this.owner = owner;
		this.description = description;
		this.registrationDate = new Date();
		assignBarcode();
		logEntries = new LinkedList<ILog>();		
	}
	
	/**
	 * Assigns a barcode to the bicycle
	 */
	private void assignBarcode(){
		//TODO implement assignBarcode
	}
	
	@Override
	public String getBarcode() {
		return barcode;
	}

	@Override
	public List<ILog> getLogEntries() {
		// TODO go through the list and remove all logs that do not match 5.1.2.19 (as of SRS1.0)
		return logEntries;
	}

	@Override
	public Date getRegistrationDate() {
		return registrationDate;
	}

	@Override
	public void setDescription(String desc) {
		this.description = desc;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public IMember getOwner() {
		return owner;
	}

	@Override
	public boolean isCheckedIn() {
		return checkedIn;
	}

	@Override
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;		
		logEntries.add(0, new Log(new Date(),"grejs"));
		//TODO add logentry for checkins/checkouts
		//add at start of list
	}

}
