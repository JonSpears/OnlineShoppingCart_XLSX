package model;

import comon.User;
import enums.Gender;
import enums.Role;
import lombok.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class Customer extends User {
    private double wallet;
    private Map<Product, Integer> cart;
    private boolean checkOut = false;
    private double totalGoodsPrice;


    public Customer(int userID, String userName, Gender gender, String email, Role role, double wallet, boolean checkOut, double totalGoodsPrice) {
        super(userID, userName, gender, email, role);
        this.wallet = wallet;
        this.checkOut = checkOut;
        this.totalGoodsPrice = totalGoodsPrice;
        this.cart = new HashMap<>();
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Customer{Name: " + getUserName() +
                "wallet=" + wallet +
                ", cart=" + cart +
                ", checkOut=" + checkOut +
                ", totalGoodsPrice=" + totalGoodsPrice +
                '}';
    }
}
