import Shop.Customer;
import Shop.Order;
import Shop.Product;
import functional_interfaces.Imprecate;
import functional_interfaces.OrderedProducts;
import functional_interfaces.OrdersFilter;
import functional_interfaces.ProductsFilter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Predicate<Product> isBook = product -> product.getCategory().equals("Books");
        Predicate<Product> isOverTon = product -> product.getPrice() > 100;
        ProductsFilter booksOverTon = products -> products.stream().filter(product -> isBook.test(product)
                && isOverTon.test(product)).toList();

        Predicate<Product> isBaby = product -> product.getCategory().equals("Baby");
        Predicate<Order> orderHasBaby = order -> order.getProducts().stream().anyMatch(isBaby);
        OrdersFilter ordersWithBaby = orders -> orders.stream().filter(orderHasBaby).toList();

        Imprecate is = (product, category) -> product.getCategory().equals(category);
        ProductsFilter boyDiscount = products -> {
            List<Product> list = products.stream().filter(product -> is.category(product, "Boy")).toList();
            list.forEach(product -> product.setPrice(product.getPrice() * 0.9));
            return list;
        };

        Predicate<Customer> tier2 = customer -> customer.getTier() == 2;
        Predicate<LocalDate> between = date -> date.isAfter(LocalDate.of(2001, 2, 1))
                && date.isBefore(LocalDate.of(2021, 4, 1));
        OrderedProducts tier2Between = orders -> {
            Stream<Order> orderList = orders.stream().filter(order -> tier2.test(order.getCustomer()) && between.test(order.getOrderDate()));
            List<Product> products = new ArrayList<>();
            orderList.forEach(order -> products.addAll(order.getProducts()));
            return products;
        };

    }


}