package system;

import interfaces.IBicycle;
import interfaces.ILog;
import interfaces.IMember;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Bicycle implements Serializable, IBicycle {

	private static final long serialVersionUID = 7848377332727864003L;
	private String barcode;
	private boolean checkedIn;
	private LinkedList<ILog> logEntries;
	private IMember owner;
	private String description;
	private Date registrationDate;
	
	private static List<String> USED_BARCODES = new ArrayList<String>();
	
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
		Random rng = new Random();
		boolean barcodeGenerated = false;
		
		StringBuilder sb;
		
		do {
			sb = new StringBuilder();
			
			for(int i = 0; i < 5; i++) {
				sb.append(rng.nextInt(10));
			}
			
			String generatedCode = sb.toString();
			boolean unique = true;
			
			for(String code : Bicycle.USED_BARCODES) {
				if(code.equals(generatedCode)) {
					unique = false;
					break;
				}
			}
			
			if(unique) {
				this.barcode = generatedCode;
				barcodeGenerated = true;
			} else {
				continue;
			}
		} while(!barcodeGenerated);
	}
	
	@Override
	public String getBarcode() {
		return barcode;
	}

	@Override
	public List<ILog> getLogEntries() {
		Iterator<ILog> itr = logEntries.iterator();
		int index = 0;
		Date ayearago = new Date();
		//31536000000L is the amount of milliseconds in a year
		ayearago.setTime(ayearago.getTime() - 31536000000L);
		while(itr.hasNext()){
			ILog logentry = itr.next();
			if(index>=30 && logentry.getDate().before(ayearago))
				itr.remove();
			index++;
		}
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
		if(checkedIn)
			logEntries.push(new Log(new Date(),"Bicycle " + barcode + " checked in"));
		else
			logEntries.push(new Log(new Date(),"Bicycle " + barcode + " checked out"));
	}

}
