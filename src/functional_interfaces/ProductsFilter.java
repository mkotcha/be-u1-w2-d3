package functional_interfaces;

import Shop.Product;

import java.util.List;

@FunctionalInterface
public interface ProductsFilter {
    public List<Product> getProducts(List<Product> products);


}
