package service;

import model.Staff;
import bus.StaffRepository;

import java.sql.Date;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffService {
    public List<Staff> getAllStaff(){
        StaffRepository repository = new StaffRepository();
        return repository.getAllStaff();
    }

    public boolean addStaff(String name, String address, String phone, Date birthDate, String gender){
        StaffRepository repository = new StaffRepository();
        return repository.addStaff(name, address, phone, birthDate, gender) >= 1;
    }

    public boolean modifyStaff(int id, String name, String address, String phone, Date birthDate, String gender){
        StaffRepository repository = new StaffRepository();
        return repository.modifyStaff(id, name, address, phone, birthDate, gender) >= 1;
    }

    public boolean deleteStaff(int id){
        StaffRepository repository = new StaffRepository();
        return repository.deleteStaff(id) >= 1;
    }
    //search by name
    public List<Staff> searchByName(String name, List<Staff> staffList){
    	List<Staff> searchList = new ArrayList<>();  
    	for(Staff i : staffList) {
    		Pattern pattern = Pattern.compile("(?i).*"+name+".*");
    		String vietnameseString = Normalizer.normalize(i.getName(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
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
    	}
    	return searchList;
    }
    //search by id
    public List<Staff> searchById(String id, List<Staff> staffList){
    	List<Staff> searchList = new ArrayList<>();  
    	for(Staff i : staffList) {
    		Pattern pattern = Pattern.compile(".*"+id+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getId()));
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //sort by name
    public List<Staff> sortByName(List<Staff> staffList){
    	List<Staff> sortList = staffList;  
    	Collections.sort(sortList, new Comparator<Staff>() {

			@Override
			public int compare(Staff o1, Staff o2) {
				// TODO Auto-generated method stub
				return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
			}
    		
    	});
    	return sortList;
    }
    //sort by ID
    public List<Staff> sortById(List<Staff> staffList){
    	List<Staff> sortList = staffList;  
    	Collections.sort(sortList, new Comparator<Staff>() {

			@Override
			public int compare(Staff o1, Staff o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}
    		
    	});
    	return sortList;
    }
    
}
