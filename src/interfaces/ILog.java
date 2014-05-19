package interfaces;

import java.util.Date;

public interface ILog {
	/**
	 * Returns the description of the log entry.
	 * @return description
	 */
	String getDesc();
	
	/**
	 * Returns the date of the log entry.
	 * @return date
	 */
	Date getDate();
}
