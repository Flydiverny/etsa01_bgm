package test;

import static org.junit.Assert.*;
import interfaces.*;
import system.BicycleGarageManager;
import system.MemberManager;
import java.util.List;
import org.junit.Test;

public class CheckInCheckedInBicycleTest {
	
		
		
	@Test
	public void noreg(){
		MemberManager member = new MemberManager(); 	
		BicycleGarageManager garage = new BicycleGarageManager(member);
		assertTrue("Couldnt create member", member.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232", "199309245151"));
		
		IMember mb = member.getMember("199309245151");
		mb.registerBicycle("Fin cykel");
		mb.registerBicycle("Ful cykel");
		
		List<IBicycle> bikes = mb.getBicycles();
		
		assertTrue("bicycle checked in", garage.checkInBicycleByBarcode(bikes.get(0).getBarcode()));
		assertTrue("bicycle checked in", garage.checkInBicycleByBarcode(bikes.get(1).getBarcode()));
		
		assertFalse("bicycle already checked in", garage.checkInBicycleByBarcode(bikes.get(1).getBarcode()));
		assertEquals(2, garage.getAmountOfCheckedInBicycles());
	};

}
