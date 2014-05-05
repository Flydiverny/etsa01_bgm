package interfaces;
import java.util.List;

import interfaces.hardware.BarcodePrinter;
import interfaces.hardware.ElectronicLock;
import interfaces.hardware.PinCodeTerminal;

public interface BicycleGarageManager {
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
	void printBarcode(Bicycle bicycle);
	
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
	 * @return
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
	 * Returns a list of checked in bicycles.
	 * @return List of Bicycles
	 */
	List<Bicycle> getCheckedInBicycles();
	
	/**
	 * Returns the amount of checked in bicycles.
	 * @return count
	 */
	int getAmountOfCheckedInBicycles();
}
