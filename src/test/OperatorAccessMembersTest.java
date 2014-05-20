package test;

import static org.junit.Assert.*;
import system.BicycleGarageManager;
import system.MemberManager;

import org.junit.Test;


public class OperatorAccessMembersTest {

	
	@Test
	public void noreg(){
		MemberManager mm = new MemberManager();
		BicycleGarageManager garage = new BicycleGarageManager(mm);
		garage.setOperatorPassword("", "operatorpass567", "operatorpass567");
		assertTrue("operator logged in" ,garage.loginOperator("operatorpass567"));
		
		
		
		
		
		
		
	}
	
	
	
	
}
