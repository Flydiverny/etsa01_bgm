/**
 * 
 */
package system;

import java.util.Date;

public class Log implements interfaces.Log {
	
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
