package models;

import java.util.Random;

public enum Discount {
    NONE(0),
    FIVE_PERCENT(5),
    TEN_PERCENT(10),
    FIFTEEN_PERCENT(15),
    TWENTY_PERCENT(20);

    private int value;

    Discount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Discount getRandomDiscount() {
        Discount[] values = Discount.values();
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }
}
