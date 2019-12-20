package service.house;

import models.House;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import ultils.compare.HouseCompare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HouseServiceImpl implements HouseService {
    private static final String PATH_FILE_HOUSE = "src/data/House.csv";
    private static final String[] FILE_HEADER_OF_HOUSE = {"id", "nameService", "area", "rentalPrice"
            , "maxNumberOfPeople", "typeRent", "roomType", "convenient"
            , "numberOfFloors"};

    @Override
    public void addNewHouse() {
        Scanner scanner = new Scanner(System.in);
        House house = new House();
        house.setId(UUID.randomUUID().toString().replace("-", ""));
        System.out.println("Input House Name: ");
        house.setName(scanner.nextLine());
        System.out.println("Input Area: ");
        house.setArea(scanner.nextDouble());
        System.out.println("Input Number of floor: ");
        house.setNumberOfFloor(scanner.nextInt());
        scanner = new Scanner(System.in);
        System.out.println("Input Convenient: ");
        house.setConvennient(scanner.nextLine());
        System.out.println("Input Room Type: ");
        house.setRoomType(scanner.nextLine());
        System.out.println("Input Max People");
        house.setMaxPeople(scanner.nextInt());
        System.out.println("Input Price: ");
        house.setRentPrice(scanner.nextDouble());
        scanner = new Scanner(System.in);
        System.out.println("Input Type For Rent: ");
        house.setTypeForRent(scanner.nextLine());
        //Gọi phương thức ghi file csv
        writeCsvFile(house);
    }

    @Override
    public void showAllHouse() {
        List<House> listHouse = readCsvFile();
        for(int i = 0; i < listHouse.size(); i++){
            System.out.print((i+1) + ". \t");
            System.out.print(listHouse.get(i).showInfor());
            System.out.println();
            System.out.println("==============================================");
        }
    }

    @Override
    public House getHouseById(int id) {
        List<House> houseList = readCsvFile();
        House house = houseList.get(id-1);
        return house;
    }

    @Override
    public void showAllHouseNotDuplicate() {
        TreeSet<House> houses = new TreeSet<>(new HouseCompare());
        int count = 1;
        for(House house: houses){
            System.out.print(count + ". \t");
            System.out.print(house.showInfor());
            System.out.println();
            System.out.println("==============================================");
            count++;
        }
    }

    @Override
    public Set<String> getNameServiceFromFileCSV(String path) {
        return null;
    }

    @Override
    public void writeCsvFile(House house) {
        try {
            List<House> oldHouse = readCsvFile();
            Writer writer = new PrintWriter(PATH_FILE_HOUSE);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_HOUSE));
            if (oldHouse != null) {
                for (House house1 : oldHouse) {
                    csvPrinter.printRecord(Arrays.asList(house1.getId(), house1.getName(), house1.getArea(), house1.getRentPrice()
                            , house1.getMaxPeople(), house1.getTypeForRent(), house1.getRoomType(), house1.getConvennient(),
                            house1.getNumberOfFloor()));
                }
            }

            csvPrinter.printRecord(Arrays.asList(house.getId(), house.getName(), house.getArea(), house.getRentPrice()
                    , house.getMaxPeople(), house.getTypeForRent(), house.getRoomType(), house.getConvennient(),
                    house.getNumberOfFloor()));
            csvPrinter.flush();
            System.out.println("Write csv file by using new Apache lib successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<House> readCsvFile() {
        List<House> oldHouse = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_FILE_HOUSE));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_HOUSE).withIgnoreHeaderCase().withTrim());
            //Biến i để check vòng lặp
            int i = 0;
            for (CSVRecord csvRecords : csvParser) {
                //Vòng lặp đầu tiên sẽ bỏ qua vì đấy là header
                if (i == 0) {
                    i++;
                } else {
                    House house = new House();
                    house.setId(csvRecords.get(0));
                    house.setName(csvRecords.get(1));
                    house.setArea(Double.parseDouble(csvRecords.get(2)));
                    house.setRentPrice(Double.parseDouble(csvRecords.get(3)));
                    house.setMaxPeople(Integer.parseInt(csvRecords.get(4)));
                    house.setTypeForRent(csvRecords.get(5));
                    house.setRoomType(csvRecords.get(6));
                    house.setConvennient(csvRecords.get(7));
                    house.setNumberOfFloor(Integer.parseInt(csvRecords.get(8)));
                    oldHouse.add(house);
                }
            }
            return oldHouse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TreeSet<House> readCsvFileNotDuplicate() {
        TreeSet<House> oldHouse = new TreeSet<>(new HouseCompare());
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_FILE_HOUSE));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_HOUSE).withIgnoreHeaderCase().withTrim());
            //Biến i để check vòng lặp
            int i = 0;
            for (CSVRecord csvRecords : csvParser) {
                //Vòng lặp đầu tiên sẽ bỏ qua vì đấy là header
                if (i == 0) {
                    i++;
                } else {
                    House house = new House();
                    house.setId(csvRecords.get(0));
                    house.setName(csvRecords.get(1));
                    house.setArea(Double.parseDouble(csvRecords.get(2)));
                    house.setRentPrice(Double.parseDouble(csvRecords.get(3)));
                    house.setMaxPeople(Integer.parseInt(csvRecords.get(4)));
                    house.setTypeForRent(csvRecords.get(5));
                    house.setRoomType(csvRecords.get(6));
                    house.setConvennient(csvRecords.get(7));
                    house.setNumberOfFloor(Integer.parseInt(csvRecords.get(8)));
                    oldHouse.add(house);
                }
            }
            return oldHouse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

