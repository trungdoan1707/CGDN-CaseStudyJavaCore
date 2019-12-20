package service.customer;

import models.Customer;
import repository.FuncWriteAndReadFileCSV;

public interface CustomerService extends FuncWriteAndReadFileCSV<Customer> {
    void addNewCustomer();
    void showAllCustomer();
    void showAllCustomerNoSort();
    Customer getCustomerByChoice(int choice);
}
