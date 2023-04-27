package service;

import model.Roles;
import model.User;
import bus.UserRepository;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    public List<User> getAllUsers(){
        UserRepository userRepository = new UserRepository();
        List<User> list = userRepository.getAllUser();
        return list;
    }
    public boolean login(String userName,String password){
        UserRepository repository = new UserRepository();
        return repository.login(userName,password) >= 1;
    }

    public boolean addUser(String userName,
                           String email,
                           String password,
                           int roleId){
        UserRepository userRepository = new UserRepository();
        return userRepository.addUser(
                userName,
                email,
                password,
                roleId) >= 1;
    }

    public boolean deleteUser(int id){
        UserRepository userRepository = new UserRepository();
        return userRepository.deleteUser(id) >= 1;
    }

    public boolean modifyUser(
            int id,
            String userName,
            String email,
            String password,
            int roleId
    ){
        UserRepository userRepository = new UserRepository();
        return  userRepository.modifyUser(
                id,
                userName,
                email,
                password,
                roleId
        ) >= 1;
    }
    
    //search by name
    public List<User> searchByName(String name, List<User> userList){
    	List<User> searchList = new ArrayList<>();  
    	for(User i : userList) {
    		Pattern pattern = Pattern.compile("(?i).*"+name+".*");
    		String search = Normalizer.normalize(i.getName(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");           
            Matcher matcher = pattern.matcher(search);
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //search by id
    public List<User> searchById(String id, List<User> userList){
    	List<User> searchList = new ArrayList<>();  
    	for(User i : userList) {
    		Pattern pattern = Pattern.compile(".*"+id+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getId()));
            
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //search by role
    public List<User> searchByRole(String role, List<User> userList){
    	List<User> searchList = new ArrayList<>(); 
    	RoleService roles = new RoleService();
    	List<Roles> roleList = roles.getAllRoles();
    	for(User i : userList) {
    		for(Roles j : roleList) {
    			if(i.getRoleId() == j.getId()) {
    				Pattern pattern = Pattern.compile("(?i).*"+role+".*");
    	    		String vietnameseString = Normalizer.normalize(j.getName(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    	    		String search = vietnameseString.toLowerCase().replaceAll("[đ]", "d")                          
    	                    .replaceAll("[ư]", "u")                                      
    	                    .replaceAll("[ô]", "o")
    	                    .replaceAll("[ơ]", "o")
    	                    .replaceAll("[ê]", "e")
    	                    .replaceAll("[ă]", "a")
    	                    .replaceAll("[â]", "a");                
    	            Matcher matcher = pattern.matcher(search);
    	            if(matcher.find()) {
    	            	searchList.add(i);
    	        	}
    	            break;
    			}
    		}   		     
    	}
    	return searchList;
    }
    //sort by name
    public List<User> sortByName(List<User> userList){
    	List<User> sortList = userList;  
    	Collections.sort(sortList, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
			}
    		
    	});
    	return sortList;
    }
    //sort by ID
    public List<User> sortById(List<User> userList){
    	List<User> sortList = userList;  
    	Collections.sort(sortList, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}
    		
    	});
    	return sortList;
    }
    //sort by role
    public List<User> sortByRole(List<User> userList){
    	List<User> sortList = new ArrayList<>();  
    	RoleService roles = new RoleService();
    	List<Roles> roleSortList = roles.getAllRoles();
    	Collections.sort(roleSortList, new Comparator<Roles>() {

			@Override
			public int compare(Roles o1, Roles o2) {
				// TODO Auto-generated method stub
				return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
			}
   		
    	});    	
    	for(Roles j : roleSortList) {
    		for(User i : userList) {
    			if(i.getRoleId() == j.getId()) {
    				sortList.add(i);    				
    			}
        	}			
		}
    	return sortList;
    }	
}
