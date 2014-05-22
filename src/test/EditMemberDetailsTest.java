package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import system.MemberManager;



public class EditMemberDetailsTest {
	
		
	@Test
	public void editMemberDetailsTest(){
		MemberManager member = new MemberManager(); 	
		member.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232", "199309245151");	
		member.getMember("199309245151").setName("John Nilsson");
		assertEquals(member.getMember("199309245151").getName(), "John Nilsson" );
		
					
			
		}
	
	
	
	
	
	
	
	

}
