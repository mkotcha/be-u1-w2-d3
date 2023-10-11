package functional_interfaces;

import Shop.Order;

import java.util.List;

public interface OrdersFilter {
    public List<Order> getOrders(List<Order> orders);
}
