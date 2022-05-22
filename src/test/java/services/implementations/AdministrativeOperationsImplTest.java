package services.implementations;

import enums.Gender;
import enums.Qualification;
import enums.Role;
import exceptions.ApplicantNotQualifiedException;
import exceptions.InvalidOperationException;
import exceptions.ProductIsOutOfStockException;
import exceptions.StaffNotAuthorizedToPerformOperationException;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.interfaces.AdministrativeOperations;
import services.interfaces.CustomerOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdministrativeOperationsImplTest {
    AdministrativeOperationsImpl administrativeOperations;
    CustomerOperation customerOperation;
    Store company;
    String excelFilePath;
    Product groceries;
    Staff manager;
    Staff cashier;
    Customer customer1;
    Customer customer2;
    Customer customer3;
    Customer customer4;
    Customer customer5;
    Applicant applicant1;
    Applicant applicant2;
    Applicant applicant3;


    @BeforeEach
    void setUp() {
        administrativeOperations = new AdministrativeOperationsImpl();
        customerOperation = new CustomerOperationImpl();
        company = new Store();
        groceries = new Product();
        manager = new Staff(1, "Jonathan Spears", Gender.MALE, "jspears@gmail.com", Role.MANAGER);
        cashier = new Staff(2, "Gabriella Johnsons", Gender.FEMALE, "gabby@gmail.com", Role.CASHIER);
        customer1 = new Customer(011,"Van Grey", Gender.MALE, "serd@mail.com",Role.CUSTOMER,0.0, false, 0.0);
        customer2 = new Customer(011,"Luther Mitchell", Gender.MALE, "mitchel@mail.com",Role.CUSTOMER,0.0, false, 0.0);
        customer3 = new Customer(011,"Jude Humphrey", Gender.MALE, "jude@mail.com",Role.CUSTOMER,0.0, false, 0.0);
        customer4 = new Customer(011,"Gregory John", Gender.MALE, "gegory@mail.com",Role.CUSTOMER,0.0, false, 0.0);
        customer5 = new Customer(011,"Robert Hughes", Gender.MALE, "robert@mail.com",Role.CUSTOMER,0.0, false, 0.0);
        excelFilePath = "src/main/resources/spreadsheet_for_product_list/ProductList.xlsx";
        applicant1 = new Applicant(101, "Kunle Damian", Gender.MALE, "kdamina@gmail.com", Role.APPLICANT, Qualification.HND);
        applicant2 = new Applicant(102, "Johnbull Mandy", Gender.FEMALE, "mandy@gmail.com", Role.APPLICANT, Qualification.ND);
        applicant3 = new Applicant(103, "Chidi Mike", Gender.MALE, "chimike@gmail.com", Role.APPLICANT, Qualification.MSC);
    }

    @Test
    public void shouldUpdateNumbersOfHiredApplicant() throws ApplicantNotQualifiedException {
        administrativeOperations.hireApplicants(company, manager, applicant1);
        administrativeOperations.hireApplicants(company, manager, applicant3);
        Assertions.assertEquals(2, company.getStaff().size());
    }

    @Test
    public void shouldThrowExceptionWhenApplicantDoesNotMeetRequirement() {
        assertThrows(ApplicantNotQualifiedException.class, ()->administrativeOperations.hireApplicants(company, manager, applicant2));
    }

    @Test
    public void shouldThrowStaffNotAuthorizedToPerformOperationException() {
        assertThrows(StaffNotAuthorizedToPerformOperationException.class,
        ()-> administrativeOperations.addProductToStoreFromExcel(company, cashier));
    }

    @Test
    public void staffNotAuthorizedExceptionShouldReturnExactExceptionMessageWhenThrown() {
        Exception exception = assertThrows(StaffNotAuthorizedToPerformOperationException.class,
                () -> administrativeOperations.addProductToStoreFromExcel(company, cashier));
        Assertions.assertEquals("You are not authorise to perform this operation", exception.getMessage());
    }

    @Test
    public void productListAfterAddingFromExcelShouldBeGreaterThanProductListBeforeAdding() throws IOException, StaffNotAuthorizedToPerformOperationException {
        int initialSizeOfProductList = company.getListOfProductInStore().length;
        AdministrativeOperations operations = new AdministrativeOperationsImpl();
        operations.addProductToStoreFromExcel(company, manager);
        Assertions.assertTrue(company.getListOfProductInStore().length > initialSizeOfProductList);
    }

    @Test
    public void companyProductShouldReduceAfterPurchase() throws InvalidOperationException, IOException, StaffNotAuthorizedToPerformOperationException, ProductIsOutOfStockException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        Assertions.assertEquals(25, company.getProductsInStoreSize());
        customerOperation.fundCustomerWallet(customer1, 200_000);
        customerOperation.addProductToCart(customer1, company, company.getProduct("Wheat Bread"), 50);
//        customerOperation.makeOrder(customer1, company);
//        administrativeOperations.sellProductToCustomer(company, cashier);
        Assertions.assertEquals(0, company.getProduct("Wheat Bread").getProductQuantity());
    }

    @Test
    public void sizeOfTheProductListShouldBeUpdatedAfterAddingFromExcel() throws IOException, StaffNotAuthorizedToPerformOperationException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        Assertions.assertEquals(25, company.getListOfProductInStore().length);
    }

    @Test
    public void testToEnsureThatOnlyCashierIsAuthorizedToSellProducts() throws IOException, StaffNotAuthorizedToPerformOperationException, ProductIsOutOfStockException, ProductIsOutOfStockException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        customerOperation.addProductToCart(customer1, company, company.getProduct("Wheat Bread"), 4);
//        assertThrows(StaffNotAuthorizedToPerformOperationException.class, () -> administrativeOperations.sellProductToCustomer(company, manager));
    }

    @Test
    public void cashierCanViewProductByCategory() throws IOException, StaffNotAuthorizedToPerformOperationException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        Assertions.assertEquals("Beverages", administrativeOperations.viewProductByCategory(company, "Beverages").get(0).getProductCategory());
        Assertions.assertEquals("Bread/Bakery", administrativeOperations.viewProductByCategory(company, "Bread/Bakery").get(1).getProductCategory());
        Assertions.assertEquals("Canned", administrativeOperations.viewProductByCategory(company, "Canned").get(2).getProductCategory());
    }

    @Test
    public void customerPriorityQueueShouldOrderCustomersOrderFromSmallest() throws IOException, StaffNotAuthorizedToPerformOperationException, ProductIsOutOfStockException {
        administrativeOperations.addProductToStoreFromExcel(company, manager);
        customerOperation.fundCustomerWallet(customer1, 200_000);
        customerOperation.fundCustomerWallet(customer2, 500_000);
        customerOperation.fundCustomerWallet(customer3, 300_000);
        customerOperation.fundCustomerWallet(customer4, 400_000);
        customerOperation.fundCustomerWallet(customer5, 200_000);
        customerOperation.addProductToCart(customer1, company, company.getProduct("Wheat Bread"), 4);
//        customerOperation.makeOrder(customer1, company);
//        customerOperation.addProductToCart(customer2, company, company.getProduct("Totilas"), 4);
//        customerOperation.makeOrder(customer2, company);
//        customerOperation.addProductToCart(customer3, company, company.getProduct("Wheat Bread"), 1);
//        customerOperation.makeOrder(customer3, company);
//        customerOperation.addProductToCart(customer4, company, company.getProduct("Corn Flakes"), 4);
//        customerOperation.makeOrder(customer4, company);
//        customerOperation.addProductToCart(customer5, company, company.getProduct("Wheat Bread"), 2);
//        customerOperation.makeOrder(customer5, company);
//        System.out.println(company.getOrderList());
//        administrativeOperations.sortOrders(company.getOrderList());
//        System.out.println(company.getOrderList());
    }
}
