package test;

import static org.junit.Assert.assertTrue;
import interfaces.IBicycleGarageManager;

import org.junit.Test;

import system.BicycleGarageManager;


public class OperatorAccessMembersTest {

	
	@Test
	public void operatorAccessMembersTest(){
		
		IBicycleGarageManager garage = new BicycleGarageManager();
		
		
		garage.setOperatorPassword("", "operatorpass567", "operatorpass567");
		assertTrue("operator logged in" ,garage.loginOperator("operatorpass567"));
	}
	
	
	
	
}
