package org.example;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final int MONTHLY_DISCOUNT_AMOUNT = 1000;

    public static final List<ShippingInfo> SHIPPING_PRICES = new ArrayList<>() {{
        add(new ShippingInfo("LP", 'S', 150));
        add(new ShippingInfo("LP", 'M', 490));
        add(new ShippingInfo("LP", 'L', 690));
        add(new ShippingInfo("MR", 'S', 200));
        add(new ShippingInfo("MR", 'M', 300));
        add(new ShippingInfo("MR", 'L', 400));
    }};
}
