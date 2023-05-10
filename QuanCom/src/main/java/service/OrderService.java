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
    public int billOfStaff(int staffId){
        OrderRepository orderRepository = new OrderRepository();
        return orderRepository.numberOfBillOfAStaff(staffId);
    }
}
