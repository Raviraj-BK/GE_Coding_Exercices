package com.ge.exercise1;

import java.util.Collection;
import java.util.HashMap;

public class LDAPApplication extends Application{
	private HashMap<String,User> users;
	private HashMap<String,Group> groups;
	
	
	public void setUsers(HashMap<String,User> users) {
		this.users = users;
	}

	public void setGroups(HashMap<String,Group> groups) {
		this.groups = groups;
	}

	public LDAPApplication(String id, String name) {
		super(id, name);
		
	}

	@Override
	public Collection<User> getUsers() {
		
		return users.values();
	}

	@Override
	public User getUser(String userId) {
		
		return users.get(userId);
	}

	@Override
	public Collection<Group> getGroups() {
		
		return groups.values();
	}

	@Override
	public Group getGroup(String groupId) {
		
		return groups.get(groupId);
	}



	

}
