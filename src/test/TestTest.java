package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		
		assertEquals("should be 1 bicycle registered", new Integer(0), new Integer(manager.getMember("199309245151").amountOfBicycles()) );
		manager.getMember("199309245151").registerBicycle("Yellow ladies bicycle");
		assertEquals("should be 1 bicycle registered", new Integer(1), new Integer(manager.getMember("199309245151").amountOfBicycles()) );
		String barcode = manager.getMember("199309245151").getBicycles().get(0).getBarcode();
		assertNotNull( barcode);
		System.out.println("barcode: " + barcode);
		//system clock?
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
		boolean apa = true;
		manager.getMember("199309245151").getBicycles().get(0).setCheckedIn(apa);
	}
	

}
