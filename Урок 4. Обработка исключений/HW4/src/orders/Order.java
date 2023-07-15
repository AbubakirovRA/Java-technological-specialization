package orders;

import java.util.ArrayList;
import java.util.List;
import exceptions.TooMuchSaleException;
import models.Product;
import models.Discount;

public class Order {
    private List<Product> products;
    private double totalCost;

    public Order() {
        products = new ArrayList<>();
        totalCost = 0;
    }

    public void addProduct(Product product) {
        products.add(product);
        totalCost += product.getPrice();
    }

    public void calculateDiscounts() throws TooMuchSaleException {
        double totalDiscount = 0;
        for (Product product : products) {
            totalDiscount += Discount.getRandomDiscount().getValue();
        }
        if (totalDiscount > 50) {
            throw new TooMuchSaleException();
        }
        double discountPercentage = totalDiscount / 100.0;
        double discountAmount = totalCost * discountPercentage;
        totalCost -= discountAmount;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
