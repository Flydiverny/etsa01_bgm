package interfaces;

import java.util.List;

public interface IMember {
	/**
	 * Sets whether the member is flagged as enabled or disabled.
	 * @param enabled
	 */
	void enable(boolean enabled);
	
	/**
	 * Returns true if member is disabled.
	 * @return
	 */
	boolean isDisabled();
	
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
	 * @param desc
	 */
	void registerBicycle(String desc);
	
	/**
	 * Removes the specified bicycle from this member.
	 * @param barcodeId
	 */
	void removeBicycle(String barcodeId);
	
	/**
	 * Returns a list of the users bicycles.
	 * @return list of bicycles
	 */
	List<IBicycle> getBicycles();
	
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
	 * @return ssn
	 */
	String getSSN();
	
	/**
	 * Get the members name.
	 * @return name
	 */
	String getName();
	
	/**
	 * Returns the users PIN
	 * @return PIN
	 */
	String getPIN();
}
