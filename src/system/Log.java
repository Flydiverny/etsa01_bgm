/**
 * 
 */
package system;

import interfaces.ILog;

import java.util.Date;

public class Log implements ILog {
	
	private Date date;
	private String description;
	public Log(Date date, String description){
		this.date = date;
		this.description = description;
	}

	@Override
	public String getDesc() {
		return description;
	}

	@Override
	public Date getDate() {
		return date;
	}

}