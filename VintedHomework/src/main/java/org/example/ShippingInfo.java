package org.example;

public class ShippingInfo {
    private String provider;
    private char size;
    private int price;

    ShippingInfo(String provider, char size, int price) {
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

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PROVIDER : " + provider +
                ", SIZE : " + size +
                ", PRICE : " + price;
    }
}
