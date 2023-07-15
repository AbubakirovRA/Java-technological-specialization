import orders.Order;
import exceptions.TooMuchSaleException;
import models.Category;
import models.Product;

public class Main {
    public static void main(String[] args) {
        Order order = new Order();
        order.addProduct(new Product("Laptop", 1000, Category.ELECTRONICS));
        order.addProduct(new Product("T-Shirt", 30, Category.CLOTHING));
        order.addProduct(new Product("Book", 20, Category.BOOKS));

        try {
            order.calculateDiscounts();
        } catch (TooMuchSaleException e) {
            System.out.println("Exception: " + e.getMessage());
            return;
        }

        double totalCost = order.getTotalCost();
        System.out.println("Total cost after discounts: " + totalCost);
    }
}
