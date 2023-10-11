package functional_interfaces;

import Shop.Order;
import Shop.Product;

import java.util.List;

public interface OrderedProducts {
    public List<Product> getOrderedProducts(List<Order> orders);
}
