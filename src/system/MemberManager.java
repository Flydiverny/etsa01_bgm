package system;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import interfaces.IBicycle;
import interfaces.IMember;
import interfaces.IMemberManager;

public class MemberManager implements IMemberManager {

	private LinkedList<IMember> members;
	
	public MemberManager() {
		members = new LinkedList<IMember>();
	}
	
	@Override
	public List<IMember> listMembers() {
		return members;
	}

	@Override
	public IMember getMember(String ssn) {
		for(IMember m : members) {
			if(m.getSSN().equals(ssn))
				return m;
		}
		
		return null;
	}

	@Override
	public List<IMember> findMembers(String name) {
		List<IMember> found = new LinkedList<IMember>();
		
		for(IMember m : members) {
			if(m.getName().contains(name))
				found.add(m);
		}
		
		return found;
	}

	@Override
	public void enableMember(IMember member, boolean enabled) {
		member.enable(enabled);
	}

	@Override
	public boolean createMember(String name, String addr, String phone,
			String ssn) {
		
		for(IMember m : members) {
			if(m.getSSN().equals(ssn))
				return false;
		}
		
		IMember m = new Member(name, addr, phone, ssn, this.createNewPIN(ssn));
		this.members.add(m);
						
		return true;
	}

	@Override
	public boolean removeMember(String ssn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateSSN(String ssn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String createNewPIN(IMember member) {
		return this.createNewPIN(member.getSSN());
	}
	
	private String createNewPIN(String ssn) {
		StringBuilder sb = new StringBuilder();
		sb.append(ssn.substring(ssn.length() - 4)); //Last four;
		
		Random rng = new Random();
		for(int i = 0; i < 4; i++) {
			sb.append(rng.nextInt(10));
		}		
		
		return sb.toString();
	}

	@Override
	public int amountOfMembers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IMember getMemberByPin(String pin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBicycle getBicycle(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

}
