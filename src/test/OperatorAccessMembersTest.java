package test;

import static org.junit.Assert.*;
import system.BicycleGarageManager;
import org.junit.Test;


public class OperatorAccessMembersTest {

	
	@Test
	public void noreg(){
		BicycleGarageManager garage = new BicycleGarageManager();
		garage.setOperatorPassword("", "operatorpass567", "operatorpass567");
		assertTrue("operator logged in" ,garage.loginOperator("operatorpass567"));
		
		
		
		
		
		
		
	}
	
	
	
	
}
