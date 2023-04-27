package service;

import model.Roles;
import bus.RoleRepository;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoleService {
    public List<Roles> getAllRoles(){
        RoleRepository roleRepository = new RoleRepository();
        List<Roles> list = roleRepository.getAllRole();
        return list;
    }

    public boolean addRole(String roleName, String description){
        RoleRepository repository = new RoleRepository();
        return repository.addRole(roleName,description) >= 1;
    }

    public boolean roleDetele(int id){
        RoleRepository repository = new RoleRepository();
        return repository.deleteRole(id) >= 1;
    }

    public boolean roleModify(int id, String roleName, String description){
        RoleRepository repository = new RoleRepository();
        return repository.modifyRole(id, roleName, description) >= 1;
    }

    //Quick Sort

//    public void quickSort(List<Roles> arr, boolean sortByRoleId) {
//        quickSortHelper(arr, 0, arr.size() - 1, sortByRoleId);
//    }
//
//    private static void quickSortHelper(List<Roles> arr, int low, int high, boolean sortByRoleId) {
//        if (low < high) {
//            int pivotIndex = partition(arr, low, high, sortByRoleId);
//            quickSortHelper(arr, low, pivotIndex - 1, sortByRoleId);
//            quickSortHelper(arr, pivotIndex + 1, high, sortByRoleId);
//        }
//    }
//
//    private static int partition(List<Roles> arr, int low, int high, boolean sortByRoleId) {
//        Roles pivot = arr.get(high);
//        int i = low - 1;
//
//        for (int j = low; j < high; j++) {
//            if (sortByRoleId) {
//                if (arr.get(j).getId() < pivot.getId()) {
//                    i++;
//                    swap(arr, i, j);
//                }
//            } else {
//                if (arr.get(j).getName().compareToIgnoreCase(pivot.getName()) < 0) {
//                    i++;
//                    swap(arr, i, j);
//                }
//            }
//        }
//
//        swap(arr, i + 1, high);
//        return i + 1;
//    }
//
//    private static void swap(List<Roles> arr, int i, int j) {
//        Roles temp = arr.get(i);
//        arr.set(i, arr.get(j));
//        arr.set(j, temp);
//    }

    public List<String> getAllNameRole(List<Roles> list){
        List<String> stringList =  new ArrayList<>();
        for (Roles roles : list){
            stringList.add(roles.getName());
        }
        return stringList;
    }
    
    //Search by Id
    public List<Roles> searchById(String id, List<Roles> roleList){
    	List<Roles> searchList = new ArrayList<>();  
    	for(Roles i : roleList) {
    		Pattern pattern = Pattern.compile(".*"+id+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getId()));
            
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //Search by name
    public List<Roles> searchByName(String name, List<Roles> roleList){
    	List<Roles> searchList = new ArrayList<>();  
    	for(Roles i : roleList) {
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
    //Sort by id
    public List<Roles> sortById(List<Roles> roleList){
    	List<Roles> sortList = roleList;  
    	Collections.sort(sortList, new Comparator<Roles>() {

			@Override
			public int compare(Roles o1, Roles o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}
    		
    	});
    	return sortList;
    }
    //Sort by name
    public List<Roles> sortByName(List<Roles> roleList){
    	List<Roles> sortList = roleList;  
    	Collections.sort(sortList, new Comparator<Roles>() {

			@Override
			public int compare(Roles o1, Roles o2) {
				// TODO Auto-generated method stub
				return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
			}
    		
    	});
    	return sortList;
    }
}
