package interfaces;
import java.util.List;


public interface IMemberManager {
	/**
	 * Returns a list of all registered members.
	 * @return List of Members
	 */
	List<IMember> listMembers();
	
	/**
	 * Returns the member with the specified SSN.
	 * Returns null if no member found.
	 * @param ssn
	 * @return member
	 */
	IMember getMember(String ssn);
	
	/**
	 * Returns a list of all members whose name matches the search string.
	 * @param name
	 * @return List of Members
	 */
	List<IMember> findMembers(String name);
	
	/**
	 * Flags a member as enabled/disabled.
	 * @param member
	 * @param enabled
	 */
	void enableMember(IMember member, boolean enabled);
	
	/**
	 * Creates a new Member and registers it in the system.
	 * 
	 * Returns false if member with specified SSN already exists.
	 * 
	 * @param name
	 * @param addr
	 * @param phone
	 * @param ssn
	 * @return true if success, else false
	 */
	boolean createMember(String name, String addr, String phone, String ssn);
	
	/**
	 * Removes the member from the system, if all preconditions are met.
	 * 
	 * Returns false:
	 * 		- if member doesn't exist.
	 *		- if member has a checked in bicycle.
	 * @param ssn
	 * @return true if success, else false
	 */
	boolean removeMember(String ssn);
	
	/**
	 * Validates the Social Security Number
	 * @param ssn
	 * @return true if valid, else false
	 */
	boolean validateSSN(String ssn);
	
	/**
	 * Creates and sets the newly generated pin for specified member.
	 * @param member
	 * @return new PIN
	 */
	String createNewPIN(IMember member);
	
	/**
	 * Returns the amount of members.
	 * @return int amount of members
	 */
	int amountOfMembers();
	
	/**
	 * Returns a member which has the specified PIN 
	 * @param pin
	 * @return null if not found.
	 */
	IMember getMemberByPin(String pin);
	
	/**
	 * Finds a bicycle by its barcode.
	 * @param barcode
	 * @return null if not found.
	 */
	IBicycle getBicycle(String barcode);
}
