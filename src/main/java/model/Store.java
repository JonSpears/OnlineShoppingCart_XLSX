package model;

import lombok.*;

import java.util.*;

@Data
public class Store {
    private String companyName;
    private String companyAddress;
    private double companyAccountBalance;
    private List<Applicant>applicants;
    private List<Staff> staff;
    private Product[] listOfProductInStore = new Product[0];
    private List<CustomerOrder> orderList;
    private Map<Integer, List<Transaction>> transactionHistory;

    public Store() {
        this.companyAccountBalance = 0;
        this.applicants = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.transactionHistory = new HashMap<>();
    }

    private Product[] listOfProductInStore(String productName) {
        for (Product product : listOfProductInStore) {
            if (product.getProductID().equals(productName)) return listOfProductInStore;
            }
            return null;
        }


    public boolean containsProduct(String productName){
        for(Product good : listOfProductInStore)
            if(good.getProductName().equals(productName)) return true;
        return false;
    }

    public Product getProduct(String productName){
        for (Product product : listOfProductInStore){
            if(product.getProductName().equals(productName)) return product;
            }
        return null;
    }

    public Product getProduct(int index){
        return listOfProductInStore[index];
    }

    public int getProductsInStoreSize(){
        return listOfProductInStore.length;
    }

}
