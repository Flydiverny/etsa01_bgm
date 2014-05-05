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
}
