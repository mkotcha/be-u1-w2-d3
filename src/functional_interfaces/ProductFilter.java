package functional_interfaces;

import Shop.Product;

import java.util.List;

@FunctionalInterface
public interface ProductFilter {
    public List<Product> getProducts(List<Product> products);
}
