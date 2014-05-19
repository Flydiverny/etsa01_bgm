package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import interfaces.*;
import interfaces.hardware.BarcodePrinter;
import interfaces.hardware.ElectronicLock;
import interfaces.hardware.PinCodeTerminal;
import system.BicycleGarageManager;
import system.MemberManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TestTest {

	IMemberManager mm;
	
	@Before
	public void setUp() throws Exception {
		String operatorPassword = null;
		int userPIN = 0;
		int operatorPIN = 0;
		boolean userStatus = false;
		boolean userRegistered = false;
		mm = new MemberManager();
	}
	

	@Test
	/* 
	 * User register as a member of the bicycle garage and registers a bike
	 */
	public void userReg() {
		//insert code to press "create new member" button
		mm.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232", "199309245151");
		//assertEquals(mm.getAddress()
		fail("Not yet implemented");
	}

}
