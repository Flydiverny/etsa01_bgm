/**
 * 
 */
package system;

import interfaces.Log;
import interfaces.Member;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Bicycle implements Serializable, interfaces.Bicycle {

	private static final long serialVersionUID = 7848377332727864003L;
	private String barcode;
	private boolean checkedIn;
	private List<Log> logEntries;
	private Member owner;
	private String description;
	private Date registrationDate;
	
	public Bicycle (Member owner, String description){
		this.owner = owner;
		this.description = description;
		this.registrationDate = new Date();
	}
	
	@Override
	public String getBarcode() {
		return barcode;
	}

	@Override
	public List<Log> getLogEntries() {
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
	public Member getOwner() {
		return owner;
	}

	@Override
	public boolean isCheckedIn() {
		return checkedIn;
	}

	@Override
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;		
		
		//TODO add logentry for checkins/checkouts
	}

}
