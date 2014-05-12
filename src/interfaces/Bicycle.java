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
		
	/**
	 * Sets the bicycle description
	 * @param desc
	 */
	void setDescription(String desc);
	
	/**
	 * Gets the bicycle description
	 * @return desc
	 */
	String getDescription();
	
	/**
	 * Sets the amount of bicycle gears
	 * @param gears
	 */
	void setGears(String gears);
	
	/**
	 * Gets the amount of bicycle gears.
	 * @return gears
	 */
	String getGears();
	
	/**
	 * Sets the bicycle model
	 * @param model
	 */
	void setModel(String model);
	
	/**
	 * Gets the bicycle model.
	 * @return model
	 */
	String getModel();
	
	/**
	 * Returns the owner of the bicycle.
	 * @return
	 */
	Member getOwner();
}
