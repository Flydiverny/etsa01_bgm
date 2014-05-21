package test;

import static org.junit.Assert.*;

import org.junit.Test;

import system.MemberManager;

public class UnregisterMemberTest {

	@Test
	public void unregisterMemberTest() {
		MemberManager member = new MemberManager();
		member.createMember("Jacob Nilsson", "Jupitergatan 2", "070315232",
				"199309245151");
		
		member.getMember("199309245151");
		
		assertTrue("user removed", member.removeMember("199309245151"));
		assertNull("user not registered", member.getMember("199309245151"));
	}

}
