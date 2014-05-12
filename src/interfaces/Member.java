package interfaces;

import java.util.List;

public interface Member {
	/**
	 * 
	 * @param enabled
	 */
	void enable(boolean enabled);
	
	/**
	 * Sets the specified PIN as PIN
	 * @param pin
	 */
	void setPIN(String pin);

	/**
	 * Returns the amount of bicycles.
	 * @return amount of bicycles.
	 */
	int amountOfBicycles();
	
	/**
	 * Registers a new bicycle for this member.
	 * @param model
	 * @param color
	 * @param gears
	 * @param desc
	 */
	void registerBicycle(String model, String color, String gears, String desc);
	
	/**
	 * Removes the specified bicycle from this member.
	 * @param barcodeId
	 */
	void removeBicycle(String barcodeId);
	
	/**
	 * Returns a list of the users bicycles.
	 * @return list of bicycles
	 */
	List<Bicycle> getBicycles();
	
	/**
	 * Sets the members address.
	 * @param addr
	 */
	void setAddress(String addr);
	
	/**
	 * Sets the members phone number.
	 * @param phone
	 */
	void setPhone(String phone);
	
	/**
	 * Sets the members SSN
	 * @param ssn
	 */
	void setSSN(String ssn);
	
	/**
	 * Sets the members name.
	 * @param name
	 */
	void setName(String name);
	
	/**
	 * Gets the members address.
	 * @return addr
	 */
	String getAddress();
	
	/**
	 * Get the members phone number.
	 * @return phone
	 */
	String getPhone();
	
	/**
	 * Get the members SSN.
	 * @return
	 */
	String getSSN();
	
	/**
	 * Get the members name.
	 * @return
	 */
	String getName();
}
