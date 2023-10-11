import Shop.Product;
import functional_interfaces.ProductsFilter;

import java.util.function.Predicate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Predicate<Product> isBook = product -> product.getCategory().equals("Books");
        Predicate<Product> isOverTon = product -> product.getPrice() > 100;
        ProductsFilter booksOverTon = products -> products.stream().filter(product -> isBook.test(product) && isOverTon.test(product)).toList();


    }


}