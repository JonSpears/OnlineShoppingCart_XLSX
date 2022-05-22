package services.interfaces;

import exceptions.CustomerOutOfFundException;
import exceptions.ProductIsOutOfStockException;
import exceptions.StaffNotAuthorizedToPerformOperationException;
import model.Customer;
import model.CustomerOrder;
import model.Product;
import model.Store;

import java.util.List;


public interface CustomerOperation extends MiscellaneousOperations {
    void fundCustomerWallet(Customer customer, double amount);
//    void addProductToCart(Customer customer, Store store, String productName, int quantity) throws ProductIsOutOfStockException;

//    void addProductToCart(Customer customer, Store store, String productName, Integer quantity) throws ProductIsOutOfStockException;

//    void addProductToCart(Customer customer, Store store, Product productName, Integer quantity) throws ProductIsOutOfStockException;

//    void addProductToCart(Customer customer, Store store, Product productName, int quantity) throws ProductIsOutOfStockException;

    void addProductToCart(Customer customer, Store store, Product productName, int quantity) throws ProductIsOutOfStockException;

    List<CustomerOrder> makeOrder(Customer customer, Store store, CustomerOrder customerOrder) throws CustomerOutOfFundException;
}
