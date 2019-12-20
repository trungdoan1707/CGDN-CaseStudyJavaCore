package ultils.sort;

import models.Customer;

import java.util.Comparator;

public class NameCustomerComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        if(o1.getName().compareTo(o2.getName())==0) {
            return o1.getYearBirthday()-o2.getYearBirthday();
        }
        return o1.getName().compareTo(o2.getName());
    }

}
