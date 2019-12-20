package controllers;

import models.*;
import service.booking.BookingService;
import service.booking.BookingServiceImpl;
import service.bookingcinema4D.BookingCinema4DService;
import service.bookingcinema4D.BookingCinema4DServiceImpl;
import service.customer.CustomerService;
import service.customer.CustomerServiceImpl;
import service.employee.EmployeeService;
import service.employee.EmployeeServiceImpl;
import service.filingcabinet.FilingCabinetsService;
import service.filingcabinet.FilingCabinetsServiceImpl;
import service.house.HouseService;
import service.house.HouseServiceImpl;
import service.room.RoomService;
import service.room.RoomServiceImpl;
import service.villa.VillaService;
import service.villa.VillaServiceImpl;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MainController {

    private VillaService villaService = new VillaServiceImpl();
    private HouseService houseService = new HouseServiceImpl();
    private RoomService roomService = new RoomServiceImpl();
    private CustomerService customerService = new CustomerServiceImpl();
    private BookingService bookingService = new BookingServiceImpl();
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private BookingCinema4DService bookingCinema4DService = new BookingCinema4DServiceImpl();
    private FilingCabinetsService filingCabinetsService = new FilingCabinetsServiceImpl();
    /**
     * Xây dựng phương thức DisplayMainMenu để hiển thị
     * các trình menu cho phép người dùng lựa chọn các chức năng trên menu
     */
    public void displayMainMenu() {
        System.out.println("-----------FURAMA MANAGEMENT SYSTEM------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "\n 1. Add new Services" +
                        "\n 2. Show Services" +
                        "\n 3. Add New Customer" +
                        "\n 4. Show Information Customer" +
                        "\n 5. Add new booking" +
                        "\n 6. Show Information Employee" +
                        "\n 7. Show Booking Cinema 4D" +
                        "\n 8. Search Filing Cabinets of Employee" +
                        "\n 9. Exit" +
                        "");
        System.out.println("Input your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addNewService();
                break;
            case 2:
                ShowServices();
                break;
            case 3:
                customerService.addNewCustomer();
                displayMainMenu();
                break;
            case 4:
                customerService.showAllCustomer();
                displayMainMenu();
                break;
            case 5:
                addNewBooking();
                break;
            case 6:
                displayEmployee();
                displayMainMenu();
                break;
            case 7:
                showBookingCinema();
                displayMainMenu();
                break;
            case 8:
                searchFilingCabinetsOfEmployee();
                displayMainMenu();
                break;
            case 9:
                System.exit(0);
                break;
            default:
                displayMainMenu();
        }
    }


    /**
     * Phương thức hiển thị menu add 1 service mới
     */
    private void addNewService() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add New Villa\n" +
                "2. Add New House\n" +
                "3. Add New Room\n" +
                "4. Back to menu\n" +
                "6. Exit");
        System.out.print("Input your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                villaService.addNewVilla();
                displayMainMenu();
                break;
            case 2:
                houseService.addNewHouse();
                displayMainMenu();
                break;
            case 3:
                roomService.addNewRoom();
                displayMainMenu();
                break;
            case 4:
                displayMainMenu();
                break;
            default:
                System.exit(0);

        }
    }

    private void ShowServices() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show All Villa \n" +
                "2. Show All House \n" +
                "3. Show All Room \n" +
                "4. Show All Villa Not Duplicate \n" +
                "5. Show All House Not Duplicate \n" +
                "6. Show All Room Not Duplicate \n" +
                "7. Back to menu\n");
        System.out.print("Input your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                villaService.showAllVilla();
                break;
            case 2:
                houseService.showAllHouse();
                ShowServices();
                break;
            case 3:
                roomService.showAllRoom();
                ShowServices();
                break;
            case 4:
                villaService.showAllVillaNotDuplicate();
                ShowServices();
                break;
            case 5:
                houseService.showAllHouseNotDuplicate();
                ShowServices();
                break;
            case 6:
                roomService.showAllRoomNotDuplicate();
                ShowServices();
                break;
            case 7:
                displayMainMenu();
                break;
            default:
                ShowServices();

        }
    }

    private void addNewBooking(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Booking Villa\n" +
                "2. Booking House\n" +
                "3. Booking Room\n" +
                "4. Back to menu\n");
        System.out.print("Input your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                bookingVilla();
                addNewBooking();
                break;
            case 2:
                bookingHouse();
                addNewBooking();
                break;
            case 3:
                bookingRoom();
                ShowServices();
                break;
            case 4:
                displayMainMenu();
                break;
            default:
                System.exit(0);

        }
    }

    private void bookingVilla() {
        Scanner scanner;
        villaService.showAllVilla();
        scanner = new Scanner(System.in);
        System.out.println("Choice Service: ");
        int serviceId = scanner.nextInt();
        //Get Service by Id
        Villa villa = villaService.getVillaById(serviceId);
        //Hiển thị danh sách customer
        customerService.showAllCustomerNoSort();
        System.out.println("Choice Customer: ");
        int choiceCustomer = scanner.nextInt();
        Customer customer = customerService.getCustomerByChoice(choiceCustomer);
        bookingService.addNewBooking(customer,villa.getId());
    }

    private void bookingHouse() {
        Scanner scanner;
        houseService.showAllHouse();
        scanner = new Scanner(System.in);
        System.out.println("Choice Service: ");
        int choiceHouse = scanner.nextInt();
        //Get Service by Id
        House house = houseService.getHouseById(choiceHouse);
        //Hiển thị danh sách customer
        customerService.showAllCustomerNoSort();
        System.out.println("Choice Customer: ");
        int choiceCustomer = scanner.nextInt();
        Customer customer = customerService.getCustomerByChoice(choiceCustomer);
        bookingService.addNewBooking(customer,house.getId());
    }

    private void bookingRoom() {
        Scanner scanner;
        roomService.showAllRoom();
        scanner = new Scanner(System.in);
        System.out.println("Choice Service: ");
        int choiceRoom = scanner.nextInt();
        //Get Service by Id
        Room room = roomService.getRoomById(choiceRoom);
        //Hiển thị danh sách customer
        customerService.showAllCustomerNoSort();
        System.out.println("Choice Customer: ");
        int choiceCustomer = scanner.nextInt();
        Customer customer = customerService.getCustomerByChoice(choiceCustomer);
        bookingService.addNewBooking(customer,room.getId());
    }

    private void displayEmployee(){
        List<Employee> employees = employeeService.getAllEmployee();
        for (Employee employee: employees){
            System.out.println("===========================================================================================");
            System.out.println(employee.toString());
        }
    }
    private void showBookingCinema() {
        Queue<Customer> customers = bookingCinema4DService.getAllBookingCinema();
        while (true) {
            Customer customer = customers.poll();
            if (customer == null) {
                break;
            }
            System.out.println(customer.showInfor());
        }
    }

    private void searchFilingCabinetsOfEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id of Employee: ");
        String idEmployee = scanner.nextLine();
        Employee employee = filingCabinetsService.findEmployeeById(idEmployee);
        if (employee == null) {
            System.out.println("Not found Filing Cabinets!");
        } else {
            System.out.println("----------------------------------------------------------------");
            System.out.println(employee.toString());
            System.out.println("----------------------------------------------------------------");
        }
    }

}
