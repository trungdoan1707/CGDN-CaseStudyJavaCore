package service.booking;

import models.Customer;
import models.Service;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BookingServiceImpl implements BookingService {
    private static final String PATH_FILE_BOOKING= "src/data/Booking.csv";
    private static final String[] FILE_HEADER_OF_BOOKING = {"id", "name", "birthday", "gender"
            , "idCard", "phone", "email", "customerType"
            , "address", "useService"};

    @Override
    public void addNewBooking(Customer customer, String serviceId) {
        customer.setUseService(serviceId);
        writeCsvFile(customer);
    }

    @Override
    public void showAllBooking() {
        List<Customer> customers = readCsvFile();
        for(int i = 0; i < customers.size(); i++){
            System.out.print(
                    i+". "
                    + "\t id: " + customers.get(i).getId()
                    + "\t name: " + customers.get(i).getName()
                    + "\t gender: " + customers.get(i).getGender()
                    + "\t phone: " + customers.get(i).getPhone()
                    + "\t email: " + customers.get(i).getEmail()
                    + "\t customer type: " + customers.get(i).getCustomerType()
                    + "\t use service: " + customers.get(i).getUseService());
            System.out.println();
            System.out.println("==============================================");
        }
    }

    @Override
    public Set<String> getNameServiceFromFileCSV(String path) {
        return null;
    }

    @Override
    public void writeCsvFile(Customer customer) {
        try {
            List<Customer> customers = readCsvFile();
            Writer writer = new PrintWriter(PATH_FILE_BOOKING);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_BOOKING));
            if (customers != null) {
                for (Customer customer1 : customers) {
                    csvPrinter.printRecord(Arrays.asList(customer1.getId(), customer1.getName(), customer1.getBirthday(), customer1.getGender()
                            , customer1.getIdCard(), customer1.getPhone(), customer1.getEmail(), customer1.getCustomerType(),
                            customer1.getAddress(), customer1.getUseService()));
                }
            }
            csvPrinter.printRecord(Arrays.asList(customer.getId(), customer.getName(), customer.getBirthday(), customer.getGender()
                    , customer.getIdCard(), customer.getPhone(), customer.getEmail(), customer.getCustomerType(),
                    customer.getAddress(), customer.getUseService()));
            csvPrinter.flush();
            System.out.println("Write csv file by using new Apache lib successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> readCsvFile() {
        List<Customer> customers = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_FILE_BOOKING));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_BOOKING).withIgnoreHeaderCase().withTrim());
            //Biến i để check vòng lặp
            int i = 0;
            for (CSVRecord csvRecords : csvParser) {
                //Vòng lặp đầu tiên sẽ bỏ qua vì đấy là header
                if (i == 0) {
                    i++;
                } else {
                    Customer customer = new Customer();
                    customer.setId(csvRecords.get(0));
                    customer.setName(csvRecords.get(1));
                    customer.setBirthday(csvRecords.get(2));
                    customer.setGender(csvRecords.get(3));
                    customer.setIdCard(csvRecords.get(4));
                    customer.setPhone(csvRecords.get(5));
                    customer.setEmail(csvRecords.get(6));
                    customer.setCustomerType(csvRecords.get(7));
                    customer.setAddress(csvRecords.get(8));
                    customer.setUseService(csvRecords.get(9));
                    customers.add(customer);
                }
            }
            return customers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TreeSet<Customer> readCsvFileNotDuplicate() {
        return null;
    }
}
