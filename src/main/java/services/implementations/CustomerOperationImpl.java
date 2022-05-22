package services.implementations;

import exceptions.CustomerOutOfFundException;
import exceptions.ProductIsOutOfStockException;
import model.Customer;
import model.CustomerOrder;
import model.Product;
import model.Store;
import services.interfaces.CustomerOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerOperationImpl implements CustomerOperation {

    @Override
    public void fundCustomerWallet(Customer customer, double amount) {
        customer.setWallet(amount + customer.getWallet());
    }

    @Override
    public void addProductToCart(Customer customer, Store store, Product productName, int quantity) throws ProductIsOutOfStockException {
        for (Product eachProduct : store.getListOfProductInStore()) {
            if (eachProduct.getProductName().equalsIgnoreCase(productName.getProductName())) {
                if (eachProduct.getProductQuantity() < quantity) throw new ProductIsOutOfStockException("We are currently low on this. Quantity in stock is " + quantity);
                else if (eachProduct.getProductQuantity() <= 0) throw new ProductIsOutOfStockException("We are currently out of stock on " + eachProduct.getProductName());
                customer.getCart().put(store.getProduct(eachProduct.getProductName()),quantity);
            }
        }
    }

//    @Override
//    public void addProductToCart(Customer customer, Store store, Product productName, Integer quantity) throws ProductIsOutOfStockException {
//        Map<Product, Integer> cartItems = customer.getCart();
//        System.out.println(customer);
//        Product[] listOfProductFromCart = cartItems.keySet().toArray(new Product[0]);
//        Product[] listOfProductFromStore = store.getListOfProductInStore();
//        Product foundProductInStore = Arrays.stream(listOfProductFromStore)
//                .filter(product -> Objects.equals(product.getProductName(), productName))
//                .findFirst().orElse(null);
//        if (foundProductInStore == null || foundProductInStore.getProductQuantity() == 0){
//            throw new InvalidOperationException("Product does not exist in or might be out of stock");
//        }
//        Product foundProductInCart = Arrays.stream(listOfProductFromCart)
//                .filter(product -> Objects.equals(product.getProductName(), productName))
//                .findFirst().orElse(null);
//        if (foundProductInCart != null){
//            if (foundProductInCart.getProductQuantity() <= foundProductInStore.getProductQuantity()){
//                int initialQuantity = cartItems.get(foundProductInCart);
//                int newQuantity = initialQuantity + quantity;
//                if ((newQuantity <= foundProductInStore.getProductQuantity())){
//                    cartItems.put(foundProductInStore, newQuantity);
//                }
//                else {
//                    throw new InvalidOperationException("The quantity you request for is more than the quantity we have in stock");
//                }
//            }
//        }else {
//            if (quantity <= foundProductInStore.getProductQuantity()){
//                cartItems.put(foundProductInStore, quantity);
//            }else {
//                throw new InvalidOperationException("The quantity you request for is more than the quantity we have in stock");
//            }
//        }
//    }

    @Override
    public List<CustomerOrder> makeOrder(Customer customer, Store store, CustomerOrder customerOrder) throws CustomerOutOfFundException {
        List <CustomerOrder> unsortedOrderList;
        if (customer.getTotalGoodsPrice() > customer.getWallet()){
            throw new CustomerOutOfFundException("Customer does not have sufficient fund!");
        }else {
            unsortedOrderList = new ArrayList<>();
            unsortedOrderList.add(customerOrder);
        }

//        else for (Map.Entry<Product, Integer> item: customer.getCart().entrySet()){
//
//            Product productFromCart = item.getKey();
//            Integer quantity = item.getValue();
//            CustomerOrder newOrder = new CustomerOrder(customer, productFromCart, quantity);
//            store.getOrderList().add(newOrder);
//        }
//        System.out.println(unsortedOrderList);
        return unsortedOrderList;
//    }
}}
