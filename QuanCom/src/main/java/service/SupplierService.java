package service;

import bus.SupplierRepository;
import model.Supplier;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierService {
    public List<Supplier> getAllSupplier(){
        SupplierRepository supplierRepository = new SupplierRepository();
        List<Supplier> list = supplierRepository.getAllSupplier();
        return list;
    }

    public boolean addSupplier(String name, String address, String phone){
        SupplierRepository repository = new SupplierRepository();
        return repository.addSupplier(name, address, phone) >= 1;
    }

    public boolean supplierDetele(int id){
        SupplierRepository repository = new SupplierRepository();
        return repository.deleteSupplier(id) >= 1;
    }

    public boolean supplierModify(int id, String name, String address, String phone){
        SupplierRepository repository = new SupplierRepository();
        return repository.modifySupplier(id, name, address, phone) >= 1;
    }

    //Search by id
    public List<Supplier> searchById(String id, List<Supplier> suppList){
    	List<Supplier> searchList = new ArrayList<>();  
    	for(Supplier i : suppList) {
    		Pattern pattern = Pattern.compile(".*"+id+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getId()));
            
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //Search by name
    public List<Supplier> searchByName(String name, List<Supplier> suppList){
    	List<Supplier> searchList = new ArrayList<>();  
    	for(Supplier i : suppList) {
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
    //Search by phonenumber
    public List<Supplier> searchByPhoneNumber(String phoneNumber, List<Supplier> suppList){
    	List<Supplier> searchList = new ArrayList<>();  
    	for(Supplier i : suppList) {
    		Pattern pattern = Pattern.compile("(?i).*"+phoneNumber+".*");
    		String search = Normalizer.normalize(i.getPhone(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");           
            Matcher matcher = pattern.matcher(search);
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //Sort by id
    public List<Supplier> sortById(List<Supplier> suppList){
    	List<Supplier> sortList = suppList;  
    	Collections.sort(sortList, new Comparator<Supplier>() {

			@Override
			public int compare(Supplier o1, Supplier o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}
    		
    	});
    	return sortList;
    }
    //Sort by name
    public List<Supplier> sortByName(List<Supplier> suppList){
    	List<Supplier> sortList = suppList;  
    	Collections.sort(sortList, new Comparator<Supplier>() {

			@Override
			public int compare(Supplier o1, Supplier o2) {
				// TODO Auto-generated method stub
				return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
			}
    		
    	});
    	return sortList;
    }
    
    
    
    
    
    
    
    
    
    
    //Quick Sort
//    public void quickSort(List<Supplier> arr, String sortBy) {
//        quickSortHelper(arr, 0, arr.size() - 1, sortBy);
//    }
//
//    private static void quickSortHelper(List<Supplier> arr, int low, int high, String sortBy) {
//        if (low < high) {
//            int pivotIndex = partition(arr, low, high, sortBy);
//            quickSortHelper(arr, low, pivotIndex - 1, sortBy);
//            quickSortHelper(arr, pivotIndex + 1, high, sortBy);
//        }
//    }
//
//    private static int partition(List<Supplier> arr, int low, int high, String sortBy) {
//        Supplier pivot = arr.get(high);
//        int i = low - 1;
//
//        for (int j = low; j < high; j++) {
//            int compareResult;
//            switch (sortBy) {
//                case "id":
//                    compareResult = Integer.compare(arr.get(j).getId(), pivot.getId());
//                    break;
//                case "name":
//                    compareResult = arr.get(j).getName().compareToIgnoreCase(pivot.getName());
//                    break;
//                case "address":
//                    compareResult = arr.get(j).getAddress().compareToIgnoreCase(pivot.getAddress());
//                    break;
//                default:
//                    throw new IllegalArgumentException("Invalid sorting criteria: " + sortBy);
//            }
//
//            if (compareResult < 0) {
//                i++;
//                swap(arr, i, j);
//            }
//        }
//
//        swap(arr, i + 1, high);
//        return i + 1;
//    }
//
//    private static void swap(List<Supplier> arr, int i, int j) {
//        Supplier temp = arr.get(i);
//        arr.set(i, arr.get(j));
//        arr.set(j, temp);
//    }
    
}