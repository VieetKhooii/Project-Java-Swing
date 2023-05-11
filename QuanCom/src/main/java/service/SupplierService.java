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

    public int totalMaterialOfASupplier(int supplierId){
        SupplierRepository repository = new SupplierRepository();
        return repository.totalMaterialOfASupplier(supplierId);
    }

    public int totalMaterialPriceOfASupplier(int supplierId){
        SupplierRepository repository = new SupplierRepository();
        return repository.totalMaterialPriceOfASupplier(supplierId);
    }

    //Quick Sort
    public void quickSort(List<Supplier> arr, String sortBy) {
        quickSortHelper(arr, 0, arr.size() - 1, sortBy);
    }

    private static void quickSortHelper(List<Supplier> arr, int low, int high, String sortBy) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, sortBy);
            quickSortHelper(arr, low, pivotIndex - 1, sortBy);
            quickSortHelper(arr, pivotIndex + 1, high, sortBy);
        }
    }

    private static int partition(List<Supplier> arr, int low, int high, String sortBy) {
        Supplier pivot = arr.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            int compareResult;
            switch (sortBy) {
                case "id":
                    compareResult = Integer.compare(arr.get(j).getId(), pivot.getId());
                    break;
                case "name":
                    compareResult = arr.get(j).getName().compareToIgnoreCase(pivot.getName());
                    break;
                case "address":
                    compareResult = arr.get(j).getAddress().compareToIgnoreCase(pivot.getAddress());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid sorting criteria: " + sortBy);
            }

            if (compareResult < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Supplier> arr, int i, int j) {
        Supplier temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
