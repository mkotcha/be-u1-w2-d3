import Shop.Order;
import Shop.Product;
import functional_interfaces.Imprecate;
import functional_interfaces.OrdersFilter;
import functional_interfaces.ProductsFilter;

import java.util.List;
import java.util.function.Predicate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Predicate<Product> isBook = product -> product.getCategory().equals("Books");
        Predicate<Product> isOverTon = product -> product.getPrice() > 100;
        ProductsFilter booksOverTon = products -> products.stream().filter(product -> isBook.test(product) && isOverTon.test(product)).toList();

        Predicate<Product> isBaby = product -> product.getCategory().equals("Baby");
        Predicate<Order> orderHasBaby = order -> order.getProducts().stream().anyMatch(isBaby);
        OrdersFilter ordersWithBaby = orders -> orders.stream().filter(orderHasBaby).toList();

        Imprecate is = (product, category) -> product.getCategory().equals(category);
        ProductsFilter boyDiscount = products -> {
            List<Product> list = products.stream().filter(product -> is.category(product, "Boy")).toList();
            list.forEach(product -> product.setPrice(product.getPrice() * 0.9));
            return list;
        };


    }


}