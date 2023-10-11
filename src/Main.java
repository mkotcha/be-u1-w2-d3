import Shop.Customer;
import Shop.Order;
import Shop.Product;
import functional_interfaces.Imprecate;
import functional_interfaces.OrderedProducts;
import functional_interfaces.OrdersFilter;
import functional_interfaces.ProductsFilter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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


        Product book1 = new Product("libro1", "Books", 120);
        Product book2 = new Product("libro2", "Books", 10);
        Product book3 = new Product("libro3", "eBooks", 10);
        Product book4 = new Product("libro4", "Books", 120);
        Product baby1 = new Product("baby1", "Baby", 120);
        Product baby2 = new Product("baby2", "Baby", 10);
        Product baby3 = new Product("baby3", "Babys", 10);
        Product baby4 = new Product("baby4", "Baby", 120);


        List<Product> products = Arrays.asList(book1, book2, book3, book4);
        Customer customer1 = new Customer("aldo", 2);
        Customer customer2 = new Customer("giovanni", 1);
        Customer customer3 = new Customer("giacomo", 2);
        List<Product> cart1 = Arrays.asList(book1, baby2, book3, baby4);
        List<Product> cart2 = Arrays.asList(baby1, book2, baby3, book4);
        List<Product> cart3 = Arrays.asList(baby1, book2, book3, book4);
        Order order1 = new Order("new", LocalDate.of(2020, 1, 1), cart1, customer1);
        Order order2 = new Order("new", LocalDate.of(2020, 1, 1), cart2, customer2);
        Order order3 = new Order("new", LocalDate.of(2020, 1, 1), cart3, customer3);
        List<Order> orders = Arrays.asList(order1, order2, order3);
//        System.out.println(booksOverTon.getProducts(products));
        System.out.println(ordersWithBaby.getOrders(orders));
    }


}