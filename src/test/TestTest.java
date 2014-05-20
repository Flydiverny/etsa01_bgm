package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import system.BicycleGarageManager;
import system.MemberManager;
//import interfaces.Bicycle;
//import interfaces.Member;
//import interfaces.MemberManager;

public class TestTest {

	@Before
	public void setUp() {
		String operatorPassword = null;
		int userPIN = 0;
		int operatorPIN = 0;
		boolean userStatus = false;
		MemberManager manager = new MemberManager();
		//boolean userRegistered = false;
	}
	 @After
	 public void tearDown() {
		 
	 }
	
	/* Test 1
	 * User register as a member of the bicycle garage and registers a bike
	 */
	@Test
	public void userReg() {
		//insert code to press "create new member" button
		System.out.println("TEST 1 ------------");
		MemberManager manager = new MemberManager();
		manager.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232",
				"199309245151");
		assertEquals("Name is not the same", "Jacob Nilsson", manager.getMember("199309245151").getName() );
		assertEquals("Address is not the same", "Jupitergatan 2", manager.getMember("199309245151").getAddress() );
		assertEquals("Phone is not the same", "070315232", manager.getMember("199309245151").getPhone() );
		
		assertEquals("should be 0 bicycle registered", new Integer(0), new Integer(manager.getMember("199309245151").amountOfBicycles()) );
		manager.getMember("199309245151").registerBicycle("Yellow ladies bicycle");
		assertEquals("should be 1 bicycle registered", new Integer(1), new Integer(manager.getMember("199309245151").amountOfBicycles()) );
		String barcode = manager.getMember("199309245151").getBicycles().get(0).getBarcode();
		assertNotNull( barcode);
		System.out.println("barcode: " + barcode);
		//system clock?
		System.out.println("register date: " + manager.getMember("199309245151").getBicycles().get(0).getRegistrationDate());
		System.out.println("TEST 1 ------------");
		
	}
	/* Test 4
	 * Member retrieves bicycle from the garage.
	 */
	@Test
	public void userCheckOut() {
		MemberManager manager = new MemberManager();
		manager.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232", "199309245151");
		manager.getMember("199309245151").registerBicycle("Yellow ladies bicycle");
		boolean sant = true;
		manager.getMember("199309245151").getBicycles().get(0).setCheckedIn(sant);
		assertTrue("cykel ej incheckad", manager.getMember("199309245151").getBicycles().get(0).isCheckedIn() );
		sant = false;
		manager.getMember("199309245151").getBicycles().get(0).setCheckedIn(sant);
		assertFalse("cykel ej utcheckad", manager.getMember("199309245151").getBicycles().get(0).isCheckedIn() );
		
	}
	

	/* Test 6
	 * Operator logs into the system and wants to change the amount of bicycles allowed to be stored in the system.
	 */
	@Test
	public void changeAllowedBicycles() {
		BicycleGarageManager bicycleMan = new BicycleGarageManager();
		bicycleMan.setOperatorPassword("", "operatorpass567", "operatorpass567");
		boolean password = bicycleMan.loginOperator("operatorpass567");
		assertTrue("Fel lösenord", password );
		assertEquals("fel garagestorlek i början", new Integer(0), new Integer(bicycleMan.getGarageSize()));
		bicycleMan.setGarageSize(5);
		assertEquals("fel garagestorlek i början", new Integer(5), new Integer(bicycleMan.getGarageSize()));
	}
	
	/* Test 11
	 * Operator wants to change his password.
	 */
	@Test
	public void changePassword() {
		BicycleGarageManager bicycleMan = new BicycleGarageManager();
		bicycleMan.setOperatorPassword("", "aaaaa55555", "aaaaa55555");
		boolean password = bicycleMan.loginOperator("aaaaa55555");
		assertTrue("Lösenordet är ej aaaaa55555", password );
		
		bicycleMan.setOperatorPassword("aaaaa55555", "aaaaa66666", "aaaaa66666");
		password = bicycleMan.loginOperator("aaaaa66666");
		assertTrue("Lösenordet är ej aaaaa66666", password );
		
	}
	/* Test 14
	 * Removal of bicycle.
	 */
	@Test
	public void removeBicycle() {
		MemberManager manager = new MemberManager();
		manager.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232",
				"199309245151");
		manager.getMember("199309245151").registerBicycle("Yellow ladies bicycle");
		assertEquals("Bicycle has been removed", new Integer(1), new Integer(manager.getMember("199309245151").amountOfBicycles()) );
		String barcode = manager.getMember("199309245151").getBicycles().get(0).getBarcode();
		manager.getMember("199309245151").removeBicycle(barcode);
		assertEquals("Bicycle has not been removed", new Integer(0), new Integer(manager.getMember("199309245151").amountOfBicycles()) );
		
	}
	
	/* Test 21
	 * Operator enters a social security number that does not exist.
	 */
	@Test
	public void ssnExistNot() {
		MemberManager manager = new MemberManager();
		assertNull(manager.getMember("1778455543"));
	}
	/* Test 23
	 * Operator wants to view the Operator PIN-code.
	 */
	@Test
	public void checkPassword() {
		BicycleGarageManager bicycleMan = new BicycleGarageManager();
		bicycleMan.setOperatorPassword("", "operatorpass567", "operatorpass567");
	}


	/* Test 35
	 * Operator changes the amount of time the door will be open. Enters too long time.
	 */
	@Test
	public void tooLongTimeEntered() {
		System.out.println("TEST 35 ------------");
		BicycleGarageManager manager = new BicycleGarageManager();
		manager.setUnlockDuration(5);
		assertEquals("Door should be unlocked for 5 seconds.", new Integer(5), new Integer(manager.getUnlockDuration()));
		manager.setUnlockDuration(60);
		assertEquals("Door should be unlocked for 5 sec, duration > 32 sec not permitted.", new Integer(5), new Integer(manager.getUnlockDuration()));
		System.out.println("TEST 35 ------------");
	}
	
	/* Test 36
	 * Operator changes the amount of time the door will be open. Enters too short time.
	 */
	@Test
	public void tooShortTimeEntered() {
		System.out.println("TEST 36 ------------");
		BicycleGarageManager manager = new BicycleGarageManager();
		manager.setUnlockDuration(5);
		assertEquals("Door should be unlocked for 5 seconds.", new Integer(5), new Integer(manager.getUnlockDuration()));
		manager.setUnlockDuration(2);
		assertEquals("Door should be unlocked for 5 sec, duration < 5 sec not permitted.", new Integer(5), new Integer(manager.getUnlockDuration()));
		System.out.println("TEST 36 ------------");
	}
	
	
	

}

