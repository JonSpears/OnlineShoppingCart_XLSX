package services.interfaces;

import exceptions.*;
import model.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public interface AdministrativeOperations extends MiscellaneousOperations{
    void hireApplicants (Store store, Staff staff, Applicant applicant) throws ApplicantNotQualifiedException;

    Product[] addProductToStoreFromExcel(Store store, Staff staff) throws StaffNotAuthorizedToPerformOperationException, IOException;

//    void sellProductToCustomer(Store store, Staff staff) throws StaffNotAuthorizedToPerformOperationException, CustomerOutOfFundException;

    LinkedList<CustomerOrder> sortOrders(Customer customer, Store store, CustomerOrder customerOrder);


    void printReceipt(Integer customerId, Store store);
}
