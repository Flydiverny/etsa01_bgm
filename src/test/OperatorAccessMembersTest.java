package test;

import static org.junit.Assert.*;
import interfaces.IBicycleGarageManager;
import interfaces.IMemberManager;
import system.BicycleGarageManager;
import system.MemberManager;

import org.junit.Test;


public class OperatorAccessMembersTest {

	
	@Test
	public void operatorAccessMembersTest(){
		
		IBicycleGarageManager garage = new BicycleGarageManager();
		
		
		garage.setOperatorPassword("", "operatorpass567", "operatorpass567");
		assertTrue("operator logged in" ,garage.loginOperator("operatorpass567"));
	}
	
	
	
	
}
