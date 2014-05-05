package interfaces;

import java.util.Date;
import java.util.List;

public interface Bicycle {
	/**
	 * Returns the barcode.
	 * @return barcodeId
	 */
	String getBarcode();
	
	/**
	 * Returns a list of log entries.
	 * @return List of Log
	 */
	List<Log> getLogEntries();
	
	/**
	 * Returns the registration date.
	 * @return date
	 */
	Date getRegistrationDate();
}
