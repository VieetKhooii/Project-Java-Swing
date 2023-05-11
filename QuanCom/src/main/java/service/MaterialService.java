package service;

import bus.MaterialRepository;
import model.Material;

import java.util.List;

public class MaterialService {
    public List<Material> getAllMaterial(){
        MaterialRepository materialRepository = new MaterialRepository();
        return materialRepository.getAllMaterial();
    }

    public boolean addMaterial(String name, String unit, int price, int amount){
        MaterialRepository materialRepository = new MaterialRepository();
        return  materialRepository.addMaterial(name,unit,price,amount) >= 1;
    }

    public boolean addMaterial(int id, String name, String unit, int price, int amount){
        MaterialRepository materialRepository = new MaterialRepository();
        return  materialRepository.addMaterial(id,name,unit,price,amount) >= 1;
    }

    public boolean deleteMaterial(int id){
        MaterialRepository materialRepository = new MaterialRepository();
        return materialRepository.deleteMaterial(id) >= 1;
    }

    public boolean modifyMaterial(String name, String unit, int price, int amount, int id){
        MaterialRepository materialRepository = new MaterialRepository();
        return materialRepository.modifyMaterial(id, name, unit, price, amount) >= 1;
    }

    //search list
    public List<Material> getAllSearchResult(String searchTxt, String optSearch, String optSort){
        MaterialRepository materialRepository = new MaterialRepository();
        return materialRepository.searchByOption(searchTxt, optSearch, optSort);
    }

    public int totalMaterialReceived(int materialId){
        MaterialRepository materialRepository = new MaterialRepository();
        return  materialRepository.totalMaterialReceived(materialId);
    }

    public int totalReceivePriceOfAMaterial(int materialId){
        MaterialRepository materialRepository = new MaterialRepository();
        return  materialRepository.totalReceivePriceOfAMaterial(materialId);
    }
}
