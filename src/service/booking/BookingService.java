package service.booking;

import models.Customer;
import models.Service;
import repository.FuncWriteAndReadFileCSV;

public interface BookingService extends FuncWriteAndReadFileCSV<Customer> {
    void addNewBooking(Customer customer, String serviceId);
    void showAllBooking();
}
