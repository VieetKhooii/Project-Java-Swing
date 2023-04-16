package service;

import bus.RoleRepository;
import bus.SupplierRepository;
import model.Roles;
import model.Supplier;

import java.util.List;

public class SupplierService {
    public List<Supplier> getAllSupplier(){
        SupplierRepository supplierRepository = new SupplierRepository();
        List<Supplier> list = supplierRepository.getAllSupplier();
        return list;
    }

    public boolean addSupplier(String name, String address){
        SupplierRepository repository = new SupplierRepository();
        return repository.addSupplier(name, address) >= 1;
    }

    public boolean supplierDetele(int id){
        SupplierRepository repository = new SupplierRepository();
        return repository.deleteSupplier(id) >= 1;
    }

    public boolean supplierModify(int id, String name, String address){
        SupplierRepository repository = new SupplierRepository();
        return repository.modifySupplier(id, name, address) >= 1;
    }
}
