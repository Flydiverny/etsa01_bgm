package test;

import static org.junit.Assert.assertEquals;
import system.MemberManager;
import org.junit.Test;



public class EditMemberDetailsTest {
	
		
	@Test
	public void noreg(){
		MemberManager member = new MemberManager(); 	
		member.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232", "199309245151");	
		member.getMember("199309245151").setName("John Nilsson");
		assertEquals(member.getMember("199309245151").getName(), "John Nilsson" );
		
					
			
		}
	
	
	
	
	
	
	
	

}
