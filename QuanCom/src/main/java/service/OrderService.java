package service;

import bus.OrderRepository;
import model.Orders;

import java.util.List;

public class OrderService {
    public List<Orders> displayOrdersByStatus(String status){
        OrderRepository orderRepository = new OrderRepository();
        return orderRepository.displayOrdersByStatus(status);
    }

    public boolean markCompleteOrder(int id){
        OrderRepository orderRepository = new OrderRepository();
        System.out.println(id);
        return orderRepository.markCompleteOrder(id) >= 1;
    }
}
