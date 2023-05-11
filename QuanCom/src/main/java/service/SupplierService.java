package service;

import bus.SupplierRepository;
import model.Supplier;
import java.util.List;

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

    //search list
    public List<Supplier> getAllSearchResult(String searchTxt, String optSearch, String optSort){
    	 SupplierRepository supplierRepository = new SupplierRepository();
        return supplierRepository.searchByOption(searchTxt, optSearch, optSort);
    }
    public int totalMaterialOfASupplier(int supplierId){
        SupplierRepository repository = new SupplierRepository();
        return repository.totalMaterialOfASupplier(supplierId);
    }

    public int totalMaterialPriceOfASupplier(int supplierId){
        SupplierRepository repository = new SupplierRepository();
        return repository.totalMaterialPriceOfASupplier(supplierId);
    }
    
}