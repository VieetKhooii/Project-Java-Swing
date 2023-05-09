package service;

import bus.OrderRepository;
import model.Orders;
import java.sql.Date;
import java.util.List;

public class OrderService {
    public List<Orders> displayOrders(){
        OrderRepository orderRepository = new OrderRepository();
        return orderRepository.displayOrders();
    }

    public boolean markCompleteOrder(int id){
        OrderRepository orderRepository = new OrderRepository();
        return orderRepository.markCompleteOrder(id) >= 1;
    }

    public boolean addOrder(int staffId, int totalPrice, Date date){
        OrderRepository orderRepository = new OrderRepository();
        return orderRepository.addOrder(staffId, totalPrice, date) >= 1;
    }
    public boolean delOrder(int orderId){
        OrderRepository orderRepository = new OrderRepository();
        return orderRepository.delOrder(orderId) >= 1;
    }
    //Search list
    public List<Orders> getAllSearchResult(String searchTxt, String optSearch, String optSort, String priceFrom, String priceTo
    		,java.util.Date dateFrome, java.util.Date dateTo ){
    	OrderRepository orderRepository = new OrderRepository();
        return orderRepository.searchByOption(searchTxt, optSearch, optSort, priceFrom, priceTo, dateFrome, dateTo);
    }
}