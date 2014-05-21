/**
 * 
 */
package system;

import interfaces.ILog;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable, ILog {
	
	private static final long serialVersionUID = -4143413494230432641L;
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
