package service;

import bus.MaterialRepository;
import model.Material;
import model.Material;
import model.Material;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaterialService {
    public List<Material> getAllMaterial(){
        MaterialRepository materialRepository = new MaterialRepository();
        return materialRepository.getAllMaterial();
    }

    public boolean addMaterial(String name, String unit, int price, int amount){
        MaterialRepository materialRepository = new MaterialRepository();
        return  materialRepository.addMaterial(name,unit,price,amount) >= 1;
    }

    public boolean deleteMaterial(int id){
        MaterialRepository materialRepository = new MaterialRepository();
        return materialRepository.deleteMaterial(id) >= 1;
    }

    public boolean modifyMaterial(String name, String unit, int price, int amount, int id){
        MaterialRepository materialRepository = new MaterialRepository();
        return materialRepository.modifyMaterial(id, name, unit, price, amount) >= 1;
    }
    
    
    //Search by id
    public List<Material> searchById(String id, List<Material> materialList){
    	List<Material> searchList = new ArrayList<>();  
    	for(Material i : materialList) {
    		Pattern pattern = Pattern.compile(".*"+id+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getId()));
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //Search by name
    public List<Material> searchByName(String name, List<Material> materialList){
    	List<Material> searchList = new ArrayList<>();  
    	for(Material i : materialList) {
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
    //search by quality
    public List<Material> searchByQuality(String quality, List<Material> materialList){
    	List<Material> searchList = new ArrayList<>();  
    	for(Material i : materialList) {
    		Pattern pattern = Pattern.compile(".*"+quality+".*");              
            Matcher matcher = pattern.matcher(String.valueOf(i.getAmount()));
            if(matcher.find()) {
            	searchList.add(i);
        	}           
    	}
    	return searchList;
    }
    //sort by id
    public List<Material> sortById(List<Material> materialList){
    	List<Material> sortList = materialList;  
    	Collections.sort(sortList, new Comparator<Material>() {

			@Override
			public int compare(Material o1, Material o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}
    		
    	});
    	return sortList;
    }
    //sort by name
    public List<Material> sortByName(List<Material> materialList){
    	List<Material> sortList = materialList;  
    	Collections.sort(sortList, new Comparator<Material>() {

			@Override
			public int compare(Material o1, Material o2) {
				// TODO Auto-generated method stub
				return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
			}
    		
    	});
    	return sortList;
    }
    //sort by quality asc
    public List<Material> sortByQualityAsc(List<Material> materialList){
    	List<Material> sortList = materialList;  
    	Collections.sort(sortList, new Comparator<Material>() {

			@Override
			public int compare(Material o1, Material o2) {
				// TODO Auto-generated method stub
				return o1.getAmount() - o2.getAmount();
			}
    		
    	});
    	return sortList;
    }
    //sort by quality des
    public List<Material> sortByQualityDes(List<Material> materialList){
    	List<Material> sortList = materialList;  
    	Collections.sort(sortList, new Comparator<Material>() {

			@Override
			public int compare(Material o1, Material o2) {
				// TODO Auto-generated method stub
				return o2.getAmount() - o1.getAmount();
			}
    		
    	});
    	return sortList;
    }
    // sort by price asc
    public List<Material> sortByPriceAsc(List<Material> materialList){
    	List<Material> sortList = materialList;  
    	Collections.sort(sortList, new Comparator<Material>() {

			@Override
			public int compare(Material o1, Material o2) {
				// TODO Auto-generated method stub
				return o1.getPrice() - o2.getPrice();
			}
    		
    	});
    	return sortList;
    }
    // sort by price asc
    public List<Material> sortByPriceDes(List<Material> materialList){
    	List<Material> sortList = materialList;  
    	Collections.sort(sortList, new Comparator<Material>() {

			@Override
			public int compare(Material o1, Material o2) {
				// TODO Auto-generated method stub
				return o2.getPrice() - o1.getPrice();
			}
    		
    	});
    	return sortList;
    }
}