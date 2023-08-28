package org.example;

import java.util.List;

public class ShippingPrices {
    private String provider;
    private char size;
    private float price;

    ShippingPrices(String provider, char size, float price) {
        this.provider = provider;
        this.size = size;
        this.price = price;
    }

    public String getProvider() {
        return provider;
    }

    public char getSize() {
        return size;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PROVIDER : " + provider +
                ", SIZE : " + size +
                ", PRICE : " + price;
    }
}
