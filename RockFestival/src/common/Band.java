package common;

import java.util.ArrayList;

public class Band {

	private String name;
	private String info;
	private String country;
	private String contactPerson;
	private ArrayList<String> members = new ArrayList<String>();
	
	public Band(String info, ArrayList<String> members) {
		this.info = info;
		this.members = members;
	}
	
	public Band(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public void removeMember(String member) {
		if(members.contains(member))
			members.remove(member);
	}
	
	public void addMember(String member) {
		if(!members.contains(member))
			members.add(member);
	}
	
	public ArrayList<String> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
}
