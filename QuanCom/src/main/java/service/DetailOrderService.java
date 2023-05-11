package service;

import bus.DetailOrderRepo;
import model.DetailOrder;

import java.util.List;

public class DetailOrderService {
    public List<DetailOrder> getAllDetailOrder(){
        DetailOrderRepo repo = new DetailOrderRepo();
        return repo.getAllDetailOrder();
    }
    public boolean addDetailOrder(int orderId, int productId, String name, int amount, int price){
        DetailOrderRepo repo = new DetailOrderRepo();
        return  repo.addDetailOrder(orderId, productId, name, amount, price) >= 1;
    }
    public boolean delDetailOrder(int orderId){
        DetailOrderRepo repo = new DetailOrderRepo();
        return repo.delDetailOrder(orderId) >= 1;
    }
}