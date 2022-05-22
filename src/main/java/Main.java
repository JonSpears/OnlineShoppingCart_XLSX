import enums.Gender;
import enums.Qualification;
import enums.Role;
import exceptions.ApplicantAlreadyAppliedException;
import exceptions.ApplicantNotQualifiedException;
import exceptions.ProductIsOutOfStockException;
import exceptions.StaffNotAuthorizedToPerformOperationException;
import model.*;
import services.implementations.AdministrativeOperationsImpl;
import services.implementations.ApplicationImpl;
import services.implementations.CustomerOperationImpl;
import services.interfaces.AdministrativeOperations;
import services.interfaces.CustomerOperation;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ApplicantNotQualifiedException, ApplicantAlreadyAppliedException, IOException, StaffNotAuthorizedToPerformOperationException, ProductIsOutOfStockException {

        AdministrativeOperationsImpl administrativeOperations = new AdministrativeOperationsImpl();
        CustomerOperation customerOperation = new CustomerOperationImpl();
        ApplicationImpl applicationImpl = new ApplicationImpl();
        CustomerOrder customerOrder = new CustomerOrder();

        Store company;

        Applicant applicant1;
        Applicant applicant2;
        Applicant applicant3;
        Applicant applicant4;
        String excelFilePath;
        Product groceries;
        Staff manager;
        Staff cashier;
        Customer customer1;
        Customer customer2;
        Customer customer3;
        Customer customer4;
        Customer customer5;

        company = new Store();
        manager = new Staff(1, "Jonathan Spears", Gender.MALE, "jspears@gmail.com", Role.MANAGER);
        cashier = new Staff(2, "Gabriella Johnsons", Gender.FEMALE, "gabby@gmail.com", Role.CASHIER);
        applicant1 = new Applicant(101, "Kunle Damian", Gender.MALE, "kdamina@gmail.com", Role.APPLICANT, Qualification.HND);
        applicant2 = new Applicant(102, "Johnbull Mandy", Gender.FEMALE, "mandy@gmail.com", Role.APPLICANT, Qualification.ND);
        applicant3 = new Applicant(103, "Chidi Mike", Gender.MALE, "chimike@gmail.com", Role.APPLICANT, Qualification.MSC);
        applicant4 = new Applicant(004, "Brooks Gideon", Gender.MALE, "brooksg@mail.globe", Role.APPLICANT, Qualification.GSE);

        applicationImpl.apply(applicant1, company);
        applicationImpl.apply(applicant2, company);
        applicationImpl.apply(applicant3, company);
        applicationImpl.apply(applicant4, company);


        administrativeOperations.hireApplicants(company, manager, applicant1);

        int initialSizeOfProductList = company.getListOfProductInStore().length;
        AdministrativeOperations operations = new AdministrativeOperationsImpl();
        operations.addProductToStoreFromExcel(company, manager);



        System.out.println(company.getProduct(6));

//        Product product = new Product("WB001","Wheat Bread", "Bakery and Pastry", "Pcs", 400,50);
//        Product product1 = new Product("WB001","Corn Flakes", "Cereals", "Kgs", 3000,40);
//        Product product2 = new Product("WB001","Fantasia", "Bakery and Pastry", "Pcs", 400,50);
//        administrativeOperations.addProductToStoreFromExcel(company, manager);
//        customerOperation.fundCustomerWallet(customer1, 200_000);
//        customerOperation.fundCustomerWallet(customer2, 200_000);
//        customerOperation.fundCustomerWallet(customer3, 200_000);
//        customerOperation.fundCustomerWallet(customer4, 200_000);
//        customerOperation.fundCustomerWallet(customer5, 200_000);
//        customerOperation.addProductToCart(customer1, company, company.getProduct("Wheat Bread"), 5);
//        customerOperation.addProductToCart(customer2, company, company.getProduct("Corn Flakes"), 5);
//        customerOperation.addProductToCart(customer3, company, company.getProduct("Wheat Bread"), 1);
//        customerOperation.addProductToCart(customer4, company, company.getProduct("Fantasia"), 2);
//        customerOperation.addProductToCart(customer5, company, company.getProduct("Wheat Bread"), 3);

//        System.out.println("\nCustomer has " +
//                "added " + customer1.getCart() + " to his/her cart \n");
        customer1 = new Customer(021,"Van Grey", Gender.MALE, "serd@mail.com",Role.CUSTOMER,0.0, false, 0.0);
        customer2 = new Customer(022,"Brian", Gender.MALE, "serd@mail.com",Role.CUSTOMER,0.0, false, 0.0);
        customer3 = new Customer(042,"Jakes", Gender.MALE, "serd@mail.com",Role.CUSTOMER,0.0, false, 0.0);
        customer4 = new Customer(042,"Mandy", Gender.MALE, "serd@mail.com",Role.CUSTOMER,0.0, false, 0.0);
        customer5 = new Customer(042,"Briggs", Gender.MALE, "serd@mail.com",Role.CUSTOMER,100, false, 20);


//        CustomerOrder customerOrder1 = new CustomerOrder(customer1, product, 100);
//        CustomerOrder customerOrder2 = new CustomerOrder(customer2, product, 13);
//        CustomerOrder customerOrder3 = new CustomerOrder(customer3, product, 15);

        customerOperation.makeOrder(customer1, company, customerOrder);
        customerOperation.makeOrder(customer2, company, customerOrder);
        customerOperation.makeOrder(customer3, company, customerOrder);
        customerOperation.makeOrder(customer4, company, customerOrder);
        customerOperation.makeOrder(customer5, company, customerOrder);


//        System.out.println(customerOperation.makeOrder(customer1, company, customerOrder));
//        System.out.println(administrativeOperations.sortOrders(customer1, company, customerOrder) + ",,,,,,,,,");
//        administrativeOperations.sellProductToCustomer(company, cashier);




//        System.out.println(company.getApplicants());
//        System.out.println(company.getStaff());
//
//        System.out.println(initialSizeOfProductList);
//        System.out.println(company.getListOfProductInStore().length);

    }
}
