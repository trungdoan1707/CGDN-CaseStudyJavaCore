package service.employee;

import models.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    private static Map<String,Employee> employeeMap;

    static {
        employeeMap=new HashMap<>();
        employeeMap.put("001", new Employee("001","Nguyen Tuan Anh",22,"Thai Binh"));
        employeeMap.put("002", new Employee("002","Nguyen Hung Dung",25,"Ha Noi"));
        employeeMap.put("003", new Employee("003","Phan Van Duc",22,"Nghe An"));
        employeeMap.put("004", new Employee("004","Dang Van Lam",27,"Nga"));
        employeeMap.put("005", new Employee("005","Que Ngoc Hai",24,"Ho Chi Minh"));
        employeeMap.put("006", new Employee("006","Tran Thanh Son",20,"Gia Lai"));
        employeeMap.put("007", new Employee("007","Ha Duc Chinh",22,"Da Nang"));
        employeeMap.put("008", new Employee("008","Nguyen Dinh Trong",23,"Ha Noi"));
        employeeMap.put("009", new Employee("009","Doan Van Hau",22,"Thai Binh"));
        employeeMap.put("010", new Employee("010","Nguyen Quang Hai",22,"Ha Noi"));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return new ArrayList<>(employeeMap.values());
    }
}
