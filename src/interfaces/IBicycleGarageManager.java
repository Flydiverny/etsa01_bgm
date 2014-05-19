package interfaces;
import java.util.List;
import java.util.Map;

import interfaces.hardware.BarcodePrinter;
import interfaces.hardware.ElectronicLock;
import interfaces.hardware.PinCodeTerminal;

public interface IBicycleGarageManager {
	
	
	/**
	 * Register hardware so that BicycleGarageManager
	 * knows which drivers to access. 
	 * @param printer
	 * @param entryLock
	 * @param exitLock
	 * @param entryTerm
	 * @param exitTerm
	 */
	void registerHardwareDrivers(BarcodePrinter printer, ElectronicLock entryLock, ElectronicLock exitLock, PinCodeTerminal entryTerm, PinCodeTerminal exitTerm);
	
	/**
	 * Barcode received from the entry terminal.
	 * @param bicycleId
	 */
	void entryBarcode(String bicycleId);
	
	/**
	 * Barcode received from the exit terminal.
	 * @param bicycleId
	 */
	void exitBarcode(String bicycleId);
	
	/**
	 * Character received from entry terminal.
	 * @param c
	 */
	void entryCharacter(char c);
	
	/**
	 * Character received from exit terminal.
	 * @param c
	 */
	void exitCharacter(char c);

	/**
	 * Prints the barcode for the specified bicycle.
	 * @param bicycle
	 */
	void printBarcode(IBicycle bicycle);
	
	/**
	 * Generates a new random PIN for the operator.
	 * @return
	 */
	String genereateOperatorPIN();
	
	/**
	 * Sets a new operator password.
	 * @param oldPasswd
	 * @param newpasswd
	 * @param newpasswd2
	 * @return true if success, else false. (new passwd mismatch)
	 */
	boolean setOperatorPassword(String oldPasswd, String newpasswd, String newpasswd2);
	
	/**
	 * Sets the duration for which the door shall remain unlocked.
	 * @param time
	 */
	void setUnlockDuration(int time);
	
	/**
	 * Sets the garage size.
	 * @param size
	 */
	void setGarageSize(int size);
	
	/**
	 * Gets the garage size.
	 * @return size
	 */
	int getGarageSize();
	
	/**
	 * Logins the operator, if the password matches the operator password.
	 * @param password
	 * @return true if success, else false
	 */
	boolean loginOperator(String password);
	
	/**
	 * Returns a list of checked in bicycles.
	 * @return List of Bicycles
	 */
	List<IBicycle> getCheckedInBicycles();
	
	/**
	 * Returns the amount of checked in bicycles.
	 * @return count
	 */
	int getAmountOfCheckedInBicycles();
	
	/**
	 * Checks in a bicycle by using its barcode as entry data.
	 * @param barcode
	 * @return false if failed.
	 */
	boolean checkInBicycleByBarcode(String barcode);
	
	/**
	 * Sets the monthly fee.
	 * @param fee
	 */
	void setMonthlyFee(int fee);
	
	/**
	 * Sets the fee per bike per month.
	 * @param fee
	 */
	void setBikeFee(int fee);
	
	/**
	 * Gets the monthly fee.
	 * @return fee
	 */
	int getMonthlyFee();
	
	/**
	 * Gets the bike fee.
	 * @return fee
	 */
	int getBikeFee();
	
	/**
	 * Returns a map containing all members paired with their calculated fee.
	 * Key = Member
	 * Value = Fee
	 * @return Map<Member, Integer>
	 */
	Map<IMember, Integer> getPaymentInfo();
	
	/**
	 * Returns the cost the specified member should pay on a monthly basis.
	 * Based on the members current amount of registered bicycles.
	 * @param m
	 * @return
	 */
	int getPaymentInfo(IMember m);
	
	/**
	 * Returns the duration for which the doors should remain unlocked.
	 * @return duration
	 */
	int getUnlockDuration();
}
