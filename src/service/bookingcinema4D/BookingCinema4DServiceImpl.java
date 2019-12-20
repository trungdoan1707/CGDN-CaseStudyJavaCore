package service.bookingcinema4D;

import models.Customer;

import java.util.LinkedList;
import java.util.Queue;

public class BookingCinema4DServiceImpl implements BookingCinema4DService {
    private static Queue<Customer> customers = new LinkedList<>();
    static {
        customers.add(new Customer("001", "Trung", null,null,null,null,null,null,null,null));
        customers.add(new Customer("002", "Kien", null,null,null,null,null,null,null,null));
        customers.add(new Customer("003", "Duc", null,null,null,null,null,null,null,null));
        customers.add(new Customer("004", "Hai", null,null,null,null,null,null,null,null));
    }


    @Override
    public Queue<Customer> getAllBookingCinema() {
        return customers;
    }
}
