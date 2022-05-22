package services.implementations;

import enums.*;
import exceptions.*;
import model.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.interfaces.AdministrativeOperations;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AdministrativeOperationsImpl implements AdministrativeOperations {

    private boolean isApplicantQualified(Applicant applicant) {
        return applicant.getQualification().equals(Qualification.HND) ||
                applicant.getQualification().equals(Qualification.BSC) ||
                applicant.getQualification().equals(Qualification.MSC);
    }

    private Staff makeApplicantACashier(Applicant applicant) {
        return new Staff(
                applicant.getUserID(),
                applicant.getUserName(),
                applicant.getGender(),
                applicant.getEmail(),
                Role.CASHIER);
    }

    @Override
    public void hireApplicants(Store store, Staff staff, Applicant applicant) throws ApplicantNotQualifiedException {
        if (!isApplicantQualified(applicant)) throw new ApplicantNotQualifiedException("You do not meet the criteria for the position.");
        store.getStaff().add(makeApplicantACashier(applicant));
    }

    @Override
    public Product[] addProductToStoreFromExcel(Store store, Staff staff) throws StaffNotAuthorizedToPerformOperationException, IOException {
        Product[] listOfProductInStore = store.getListOfProductInStore();
        if (!staff.getRole().equals(Role.MANAGER)) {
            throw new StaffNotAuthorizedToPerformOperationException("You are not authorise to perform this operation");
        } else {
                String path = "src/main/resources/spreadsheet_for_product_list/ProductList.xlsx";

                FileInputStream inputStream = new FileInputStream(path);
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                XSSFSheet xssfSheet = workbook.getSheetAt(0);
                Product[] listOfProductFromFile = new Product[xssfSheet.getLastRowNum()];
                for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
                    XSSFRow xssfRow = xssfSheet.getRow(i);
                    listOfProductFromFile[i-1] = new Product(
                            xssfRow.getCell(0).getStringCellValue(),
                            xssfRow.getCell(1).getStringCellValue(),
                            xssfRow.getCell(2).getStringCellValue(),
                            xssfRow.getCell(4).getStringCellValue(),
                            xssfRow.getCell(5).getNumericCellValue(),
                            (int) xssfRow.getCell(6).getNumericCellValue()
                    );

                }
                store.setListOfProductInStore(listOfProductFromFile);
            }
        return listOfProductInStore;
    }

//    @Override
//    public void sellProductToCustomer(Store store, Staff staff) throws StaffNotAuthorizedToPerformOperationException, CustomerOutOfFundException {
////        List<CustomerOrder> orderList = this.sortOrders(store.getOrderList());
//        List<CustomerOrder> settledOrders = new ArrayList<>();
//        if (!staff.getRole().equals(Role.CASHIER)) throw new StaffNotAuthorizedToPerformOperationException("Only Cashiers can sell and dispense receipts to customers!");
//        else {
//            for (CustomerOrder order: orderList){
//                Customer customer = order.getCustomer();
//                Product product = order.getProduct();
//                System.out.println(product);
//                Integer quantity = order.getProductQuantity();
//                double totalPrice = product.getProductPrice() * quantity;
//
//                if (customer.getWallet()<totalPrice) {
//                    throw new InvalidOperationException("Customer: " + customer.getUserName() + "does not have sufficient funds in wallet");
//                } else {
//                   if (product.getProductQuantity()>=quantity){
//                       store.setCompanyAccountBalance(store.getCompanyAccountBalance() + totalPrice);
//                       customer.setWallet(customer.getWallet() - totalPrice);
//                       product.setProductQuantity(product.getProductQuantity() - quantity);
//                       Map<Product, Integer> cart = customer.getCart();
//
//                       cart.clear();
//                       customer.setCart(cart);
//
//                       Map<Integer, List<Transaction>> transactionData = store.getTransactionHistory();
//
//                       if (transactionData.get(customer.getUserID())!= null){
//                           List<Transaction> customerListOfTransactions = transactionData.get(customer.getUserID());
//                           Transaction transaction = new Transaction(customer.getUserName(), LocalDateTime.now(), totalPrice);
//                           customerListOfTransactions.add(transaction);
//                           transactionData.put(customer.getUserID(), customerListOfTransactions);
//                           store.setTransactionHistory(transactionData);
//                       } else {
//                           List<Transaction> newCustomerListOfTransactions = new ArrayList<>();
//                           Transaction transaction = new Transaction(customer.getUserName(), LocalDateTime.now(), totalPrice);
//                           newCustomerListOfTransactions.add(transaction);
//                           transactionData.put(customer.getUserID(), newCustomerListOfTransactions);
//                           store.setTransactionHistory(transactionData);
//                       }
//                       printReceipt(customer.getUserID(), store);
//                       settledOrders.add(order);
//                   }
//                }
//            }
//        }
//    }

    @Override
    public LinkedList<CustomerOrder> sortOrders(Customer customer, Store store, CustomerOrder customerOrder){
        CustomerOperationImpl customerOperation = new CustomerOperationImpl();
        List<CustomerOrder> customerOrders = customerOperation.makeOrder(customer, store, customerOrder);
        LinkedList<CustomerOrder> processedOrderList = new LinkedList<>();

        List<CustomerOrder> compared = new ArrayList<>();
        for (int i = 0 ; i < customerOrders.size(); i++) {
            for (int j = customerOrders.size()-1; j > 0; j--){
                if (!customerOrders.get(i).getProduct().getProductName().equals(customerOrders.get(j).getProduct().getProductName())){
                    processedOrderList.add(customerOrders.get(i));
                }
                else if (customerOrders.get(i).getProduct().getProductQuantity() > customerOrders.get(j).getProduct().getProductQuantity()){
                    processedOrderList.add(customerOrders.get(j));
                }
            }

//            List<CustomerOrder> similarOrders = orderList.stream().filter(p-> !Objects.equals(p.getCustomer(), order.getCustomer()) & Objects.equals(p.getProduct(),
//                    order.getProduct()) & !compared.contains(order))
//                    .sorted(Comparator.comparingInt(CustomerOrder::getProductQuantity))
//                    .collect(Collectors.toList());
//
//            compared.addAll(similarOrders);
//            System.out.println(similarOrders + ",,,,,,,,,,,,,,,,,,,,,");
//            if (!similarOrders.isEmpty()){
//                for (int i = 0; i < similarOrders.size(); i++){
//                    CustomerOrder sortedOrder = similarOrders.get(i);
//
//                    if (order.getProductQuantity() < sortedOrder.getProductQuantity()){
//                        processedOrderList.add(order);
//                    } else {
//                        processedOrderList.add(sortedOrder);
//
//                        if (i == similarOrders.size()-1){
//                            processedOrderList.add(order);
//                        }
//                    }
//                }
//            } else {
//                if (!compared.contains(order)){
//                    processedOrderList.add(order);
//                }
//            }
        }

        return processedOrderList;
    }

    private void reduceCompanyProduct(Product companyProduct, int quantityBought){
        companyProduct.setProductQuantity(companyProduct.getProductQuantity() - quantityBought);
    }

    @Override
    public void printReceipt(Integer customerId, Store store) {
        Map<Integer, List<Transaction>> storeTransactionHistory = store.getTransactionHistory();
        List<Transaction> customerListOfTransactions = storeTransactionHistory.get(customerId);
        Transaction customerLastTransaction = customerListOfTransactions.get(customerListOfTransactions.size() - 1);
        System.out.println(customerLastTransaction);
    }
}


