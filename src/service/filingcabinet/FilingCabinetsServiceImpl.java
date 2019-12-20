package service.filingcabinet;

import models.Employee;

import java.util.Stack;

public class FilingCabinetsServiceImpl implements FilingCabinetsService {
    private static Stack<Employee> employees;

    static {
        employees = new Stack<>();
        employees.push(new Employee("001", "Doan Phuoc Trung 1", 33, "Da Nang"));
        employees.push(new Employee("002", "Doan Phuoc Trung 2", 33, "Da Nang"));
        employees.push(new Employee("003", "Doan Phuoc Trung 3", 33, "Da Nang"));
        employees.push(new Employee("004", "Doan Phuoc Trung 4", 33, "Da Nang"));
        employees.push(new Employee("005", "Doan Phuoc Trung 5", 33, "Da Nang"));
    }

    @Override
    public Employee findEmployeeById(String id) {
        Stack<Employee> employees1 = employees;
        while (true) {
            Employee employee = employees1.pop();
//            System.out.println("employee : " + employee.getNameEmployee());
            if (employee == null) {
                break;
            }
            if (id.equals(employee.getIdEmployee())) {
                return employee;
            }
        }
        return null;
    }
}
