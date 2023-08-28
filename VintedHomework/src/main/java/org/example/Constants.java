package org.example;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Constants {
    public static final float MONTHLY_DISCOUNT_AMMOUNT = 10.00f;

    public static List<ShippingPrices> SHIPPING_PRICES = new ArrayList<>() {{
        add(new ShippingPrices("LP", 'S', 1.50f));
        add(new ShippingPrices("LP", 'M', 4.90f));
        add(new ShippingPrices("LP", 'L', 6.90f));
        add(new ShippingPrices("MR", 'S', 2f));
        add(new ShippingPrices("MR", 'M', 3f));
        add(new ShippingPrices("MR", 'L', 4f));
    }};
}
