package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import interfaces.IBicycleGarageManager;
import interfaces.IMember;
import interfaces.IMemberManager;

import java.util.Map;

import org.junit.Test;

import system.BicycleGarageManager;
import system.MemberManager;
//import interfaces.Bicycle;
//import interfaces.Member;
//import interfaces.MemberManager;

public class TestTest {
	/* Test 1
	 * User register as a member of the bicycle garage and registers a bike
	 */
	@Test
	public void userReg() {
		//insert code to press "create new member" button
		System.out.println("TEST 1 ------------");
		MemberManager manager = new MemberManager();
		manager.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232", "199309245151");
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
		assertTrue("Fel l�senord", password );
		assertEquals("fel garagestorlek i b�rjan", new Integer(0), new Integer(bicycleMan.getGarageSize()));
		bicycleMan.setGarageSize(5);
		assertEquals("fel garagestorlek i b�rjan", new Integer(5), new Integer(bicycleMan.getGarageSize()));
	}
	
	/* Test 11
	 * Operator wants to change his password.
	 */
	@Test
	public void changePassword() {
		BicycleGarageManager bicycleMan = new BicycleGarageManager();
		bicycleMan.setOperatorPassword("", "aaaaa55555", "aaaaa55555");
		boolean password = bicycleMan.loginOperator("aaaaa55555");
		assertTrue("L�senordet �r ej aaaaa55555", password );
		
		bicycleMan.setOperatorPassword("aaaaa55555", "aaaaa66666", "aaaaa66666");
		password = bicycleMan.loginOperator("aaaaa66666");
		assertTrue("L�senordet �r ej aaaaa66666", password );
		
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
		System.out.println("TEST 23 --------");
		BicycleGarageManager bicycleMan = new BicycleGarageManager();
		bicycleMan.setOperatorPassword("", "operatorpass567", "operatorpass567");
		bicycleMan.genereateOperatorPIN();
		String operatorPIN = bicycleMan.getOperatorPIN();
		System.out.println(operatorPIN);
		System.out.println("TEST 23 --------");
	}
	
	/* Test 24
	 * Operator wants to change the number of seconds the garage door is unlocked to 7 seconds.
	 */
	@Test
	public void changeUnlockTime() {
		BicycleGarageManager bicycleMan = new BicycleGarageManager();
		assertEquals("Door should be unlocked for 10 seconds.", new Integer(10), new Integer(bicycleMan.getUnlockDuration()));
		bicycleMan.setUnlockDuration(7);
		assertEquals("Door should be unlocked for 7 seconds.", new Integer(7), new Integer(bicycleMan.getUnlockDuration()));
	}
	
	/* Test 26
	 * Operator manually checks in a bicycle. 
	 */
	@Test
	public void operatoreChecksInBicycle() {
		System.out.println("test 26---------");
		IBicycleGarageManager bicycleMan = new BicycleGarageManager();
		IMemberManager manager = bicycleMan.getMemberManager();
		manager.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232", "199309245151");
		manager.getMember("199309245151").registerBicycle("Yellow ladies bicycle");
		String barcode = manager.getMember("199309245151").getBicycles().get(0).getBarcode();
		assertFalse("cykel borde ej vara incheckad", manager.getMember("199309245151").getBicycles().get(0).isCheckedIn() );
		boolean apa = bicycleMan.checkInBicycleByBarcode(barcode);
		System.out.println(barcode);
		System.out.println(apa);
		System.out.println("test 26---------");
		assertTrue("cykel borde vara incheckad", manager.getMember("199309245151").getBicycles().get(0).isCheckedIn() );
		
	}
	
	/* Test 28
	 * Operator generates new PIN-code for the user.
	 */
	@Test
	public void newPINForUser() {
		System.out.println("test 28------------");
		IBicycleGarageManager bgm = new BicycleGarageManager();
		IMemberManager manager = bgm.getMemberManager();
		manager.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232", "199309245151");
		String barcodeOld = manager.getMember("199309245151").getPIN();
		System.out.println("old PIN: " + barcodeOld);
		String barcodeNew = manager.createNewPIN(manager.getMember("199309245151"));
		System.out.println("new PIN: " + barcodeNew);
		assertFalse("Borde inte vara samma PIN", barcodeOld.compareTo(barcodeNew) == 0);
		System.out.println("test 28------------");
	}
	
	/* Test 30
	 * Operator retrieves payment information.
	 */
	@Test
	public void retrievePaymentInfo() {
		System.out.println("test 30------------");
		BicycleGarageManager bicycleMan = new BicycleGarageManager();
		MemberManager manager = new MemberManager();
		manager.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232", "199309245151");
		manager.createMember("Daniel Myrhman", "Jupitergatan 3", "070555555", "199309242020");
		manager.getMember("199309245151").registerBicycle("Yellow ladies bicycle");
		manager.getMember("199309242020").registerBicycle("Blue MAN bicycle");
		Map<IMember, Integer> payInfo = bicycleMan.getPaymentInfo();
		
		for(int i = 0; i < payInfo.size(); i++ ) {
			payInfo.values();
			System.out.println(payInfo.get(i));
		}
		System.out.println("test 30------------");
	}
	
	/* Test 34
	 * Operator enters password containing a non-alphanumerical character.
	 */
	@Test
	public void passIsAlphanumeric() {
		BicycleGarageManager manager = new BicycleGarageManager();
		assertEquals("Password may not consist of non-aplhanumerical characters.", false, manager.setOperatorPassword("aaaaa55555", "operatorpassword(567", "operatorpassword(567"));
	}
	/* Test 29
	 * Operator disables member. 
	 */
	@Test
	public void memberGetsDisabled() {
		MemberManager manager = new MemberManager();
		manager.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232",
				"199309245151");
		assertFalse("Member has not been enabled", 
				manager.getMember("199309245151").isDisabled());
		manager.getMember("199309245151").enable(false);
		assertTrue("Member has not been disabled", 
		manager.getMember("199309245151").isDisabled());
	}
	
	/* Test 32
	 * Operator enters password shorter than 10 alphanumerical characters.
	 */
	@Test
	public void tooShortPwEntered() {
		BicycleGarageManager manager = new BicycleGarageManager();
		assertFalse("< 10 characters is not permitted.", 
		manager.setOperatorPassword("aaaaa55555", "operatorpasswordoperatorpassword123", "operatorpasswordoperatorpassword123"));
	}
	
	/* Test 33
	 * Operator enters password longer than 32 aplhanumerical characters.
	 */
	@Test
	public void tooLongPwEntered() {
		BicycleGarageManager manager = new BicycleGarageManager();
		assertFalse("> 32 characters is not permitted.", 
		manager.setOperatorPassword("aaaaa55555", "operatorpasswordoperatorpassword123", "operatorpasswordoperatorpassword123"));
	}
	
	/* Test 34
	 * Operator enters password containing a non-alphanumerical character.
	 */
	@Test
	public void passNotAlphanumeric() {
		BicycleGarageManager manager = new BicycleGarageManager();
		assertFalse("Password may not consist of non-aplhanumeric characters.", 
		manager.setOperatorPassword("", "operatorpassword(567", "operatorpassword(567"));
	}

	/* Test 35
	 * Operator changes the amount of time the door will be open. Enters too long time.
	 */
	@Test
	public void tooLongTimeEntered() {
		BicycleGarageManager manager = new BicycleGarageManager();
		manager.setUnlockDuration(5);
		assertEquals("Door should be unlocked for 5 seconds.", new Integer(5), new Integer(manager.getUnlockDuration()));
		manager.setUnlockDuration(60);
		assertEquals("Door should be unlocked for 5 sec, duration > 32 sec not permitted.", new Integer(5), new Integer(manager.getUnlockDuration()));
	}
	
	/* Test 36
	 * Operator changes the amount of time the door will be open. Enters too short time.
	 */
	@Test
	public void tooShortTimeEntered() {
		BicycleGarageManager manager = new BicycleGarageManager();
		manager.setUnlockDuration(5);
		assertEquals("Door should be unlocked for 5 seconds.", new Integer(5), new Integer(manager.getUnlockDuration()));
		manager.setUnlockDuration(2);
		assertEquals("Door should be unlocked for 5 sec, duration < 5 sec not permitted.", new Integer(5), new Integer(manager.getUnlockDuration()));
	}
}
