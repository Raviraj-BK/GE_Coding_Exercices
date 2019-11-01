package com.ge.exercise1;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Raviraj BK
 *Class to Parse the text and create application object
 */
public class MyParser implements Parser{
	/**
	 * Method to parse the tokens line by line by comparing the keywords
	 * @param data(Text to parse)
	 * @return Application object containing appropriate users and groups information
	 */
	@Override
	public Application parseApplicationData(String data) {
		LDAPApplication apps = new LDAPApplication(null,null);		
		HashMap<String,User> userMap= new HashMap<>();
		HashMap<String,Group> groupMap= new HashMap<>();
		String[] tokens= data.split("\\,");

		//Text is splitted and tokens are parsed line by line
		if(tokens != null){
			for(int i=0;i<tokens.length;i++) {
				if(tokens[i].startsWith("Application(id:")) {
					apps.setId(tokens[i].substring(tokens[i].indexOf(":")+1).trim() );
					i++;
					apps.setName(tokens[i].substring(tokens[i].indexOf(":")+1).trim() );
				}					
				else if(tokens[i].contains("User(id:")) {
					while(!tokens[i].startsWith("groups:[") ) {
						User usr= new LdapUser(null, null);	
						String usrId=tokens[i].substring(tokens[i].lastIndexOf(":")+1).trim() ;
						usr.setId(usrId);
						i++;
						usr.setName(tokens[i].substring(tokens[i].indexOf(":")+1,tokens[i].indexOf(")")).trim() );
						userMap.put(usrId, usr);
						i++;
					}
					i--;
					apps.setUsers(userMap);
				}
				else if(tokens[i].contains("groups:[")) {
					Group grp = null;
					Collection<User> usersIngrp=new ArrayList<>();
					while(!tokens[i].endsWith("])])") ) {
						if(tokens[i].contains("Group(id:")) {
							if(usersIngrp.size()!=0) {
								grp.setUsers(usersIngrp);
								grp.size=usersIngrp.size();
								groupMap.put(grp.getId(), grp);
								usersIngrp=new ArrayList<>();
							}
							grp= new LdapGroup(null, null);	
							grp.setId(tokens[i].substring(tokens[i].lastIndexOf(":")+1).trim());
							i++;
							grp.setName(tokens[i].substring(tokens[i].indexOf(":")+1).trim());
							i++;
						}
						else if(tokens[i].contains("User(id:")) {
							String userId=tokens[i].substring(tokens[i].lastIndexOf(":")+1).trim();
							usersIngrp.add(userMap.get(userId));
							i++;
						}	
						else {
							i++;
						}
					}
					//if there is only one user in the group, user will be added in the group here
					grp.setUsers(usersIngrp);
					grp.size=usersIngrp.size();
					groupMap.put(grp.getId(), grp);
					apps.setGroups(groupMap);
				}
			}
		}
		System.out.println("Application Object\n"+apps);
		return apps;
	}
}
