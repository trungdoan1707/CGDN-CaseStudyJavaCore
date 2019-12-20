package service.villa;


import models.Villa;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import repository.FuncWriteAndReadFileCSV;
import ultils.compare.VillaCompare;
import ultils.validation.ServiceValidate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class VillaServiceImpl implements VillaService, FuncWriteAndReadFileCSV<Villa>, Comparator<Villa>{

    private static final String PATH_FILE_VILLA = "src/data/Villa.csv";
    private static final String[] FILE_HEADER_OF_VILLA = {"id", "nameService", "area", "rentalPrice"
            , "maxNumberOfPeople", "typeRent", "roomType", "convenient", "areaPool"
            , "numberOfFloors"};

    @Override
    public void addNewVilla() {
        Scanner scanner = new Scanner(System.in);
        Villa villa = new Villa();
        villa.setId(UUID.randomUUID().toString().replace("-", ""));

        //Nhập Villa Name và validate
        do{
            System.out.println("Input Villa Name: ");
            villa.setName(scanner.nextLine());
        }
        while (!ServiceValidate.validateName(villa.getName()));

        //Nhập Area và validate
        do {
            try {
                scanner = new Scanner(System.in);
                System.out.println("Input Area: ");
                villa.setArea(scanner.nextDouble());
            }catch (Exception ex){
                System.out.println("Dữ liệu nhập vào không đúng định dạng");
            }

        }while (!ServiceValidate.validateAreaOrAreaPool(villa.getArea()));


        //Nhập AreaPool và Validate
        do {
            try {
                scanner = new Scanner(System.in);
                System.out.println("Input AreaPool: ");
                villa.setAreaPool(scanner.nextDouble());
            }catch (Exception ex){
                System.out.println("Dữ liệu nhập vào không đúng định dạng");
            }

        }while (!ServiceValidate.validateAreaOrAreaPool(villa.getAreaPool()));


        //Nhập Floor và validate
        do {
            try {
                scanner = new Scanner(System.in);
                System.out.println("Input Number of floor: ");
                villa.setNumberOfFloor(scanner.nextInt());
            }catch (Exception ex){
                System.out.println("Dữ liệu nhập vào không đúng định dạng");
            }

        }while (!ServiceValidate.validateFloor(villa.getNumberOfFloor()));

        //Nhập convenient
        do {
            scanner = new Scanner(System.in);
            System.out.println("Input Convenient: ");
            villa.setConvennient(scanner.nextLine());
        }while (!ServiceValidate.validateConvenient(villa.getConvennient()));

        do{
            scanner = new Scanner(System.in);
            System.out.println("Input Room Type: ");
            villa.setRoomType(scanner.nextLine());
        }
        while (!ServiceValidate.validateName(villa.getRoomType()));

        do{
            try {
                scanner = new Scanner(System.in);
                System.out.println("Input Max People: ");
                villa.setMaxPeople(scanner.nextInt());
            }catch (Exception ex){
                System.out.println("Dữ liệu nhập vào không đúng định dạng");
            }

        }
        while (!ServiceValidate.validateMaxPeople(villa.getMaxPeople()));


        do{
            try {
                scanner = new Scanner(System.in);
                System.out.println("Input Price: ");
                villa.setRentPrice(scanner.nextDouble());
            }catch (Exception ex){
                System.out.println("Dữ liệu nhập vào không đúng định dạng");
            }

        }
        while (!ServiceValidate.validatePrice(villa.getRentPrice()));


        do{
            scanner = new Scanner(System.in);
            System.out.println("Input Type For Rent: ");
            villa.setTypeForRent(scanner.nextLine());

        }
        while (!ServiceValidate.validateName(villa.getTypeForRent()));

//        Gọi phương thức ghi file csv
        writeCsvFile(villa);
    }

    @Override
    public void showAllVilla() {
        List<Villa> listVilla = readCsvFile();
        for(int i = 0; i < listVilla.size(); i++){
            System.out.print((i+1) + ". \t");
            System.out.print(listVilla.get(i).showInfor());
            System.out.println();
            System.out.println("==============================================");
        }
    }

    @Override
    public Villa getVillaById(int id) {
        List<Villa> villaList = readCsvFile();
        Villa villa = villaList.get(id-1);
        return villa;
    }


    @Override
    public Set<String> getNameServiceFromFileCSV(String path) {
        return null;
    }

    @Override
    public List<Villa> readCsvFile() {
        List<Villa> oldVillas = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_FILE_VILLA));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_VILLA).withIgnoreHeaderCase().withTrim());
            //Biến i để check vòng lặp
            int i = 0;
            for (CSVRecord csvRecords : csvParser) {
                //Vòng lặp đầu tiên sẽ bỏ qua vì đấy là header
                if (i == 0) {
                    i++;
                } else {
                    Villa villa = new Villa();
                    villa.setId(csvRecords.get(0));
                    villa.setName(csvRecords.get(1));
                    villa.setArea(Double.parseDouble(csvRecords.get(2)));
                    villa.setRentPrice(Double.parseDouble(csvRecords.get(3)));
                    villa.setMaxPeople(Integer.parseInt(csvRecords.get(4)));
                    villa.setTypeForRent(csvRecords.get(5));
                    villa.setRoomType(csvRecords.get(6));
                    villa.setConvennient(csvRecords.get(7));
                    villa.setAreaPool(Double.parseDouble(csvRecords.get(8)));
                    villa.setNumberOfFloor(Integer.parseInt(csvRecords.get(9)));
                    oldVillas.add(villa);
                }
            }
            return oldVillas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TreeSet<Villa> readCsvFileNotDuplicate() {
        TreeSet<Villa> oldVillas = new TreeSet<>(new VillaCompare());
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_FILE_VILLA));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_VILLA).withIgnoreHeaderCase().withTrim());
            //Biến i để check vòng lặp
            int i = 0;
            for (CSVRecord csvRecords : csvParser) {
                //Vòng lặp đầu tiên sẽ bỏ qua vì đấy là header
                if (i == 0) {
                    i++;
                } else {
                    Villa villa = new Villa();
                    villa.setId(csvRecords.get(0));
                    villa.setName(csvRecords.get(1));
                    villa.setArea(Double.parseDouble(csvRecords.get(2)));
                    villa.setRentPrice(Double.parseDouble(csvRecords.get(3)));
                    villa.setMaxPeople(Integer.parseInt(csvRecords.get(4)));
                    villa.setTypeForRent(csvRecords.get(5));
                    villa.setRoomType(csvRecords.get(6));
                    villa.setConvennient(csvRecords.get(7));
                    villa.setAreaPool(Double.parseDouble(csvRecords.get(8)));
                    villa.setNumberOfFloor(Integer.parseInt(csvRecords.get(9)));
                    oldVillas.add(villa);
                }
            }
            return oldVillas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeCsvFile(Villa villa) {
        try {
            List<Villa> oldVilla = readCsvFile();
            Writer writer = new PrintWriter(PATH_FILE_VILLA);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_VILLA));
            if (oldVilla != null) {
                for (Villa villa1 : oldVilla) {
                    csvPrinter.printRecord(Arrays.asList(villa1.getId(), villa1.getName(), villa1.getArea(), villa1.getRentPrice()
                            , villa1.getMaxPeople(), villa1.getTypeForRent(), villa1.getRoomType(), villa1.getConvennient(),
                            villa1.getAreaPool(), villa1.getNumberOfFloor()));
                }
            }
            csvPrinter.printRecord(Arrays.asList(villa.getId(), villa.getName(), villa.getArea(), villa.getRentPrice()
                    , villa.getMaxPeople(), villa.getTypeForRent(), villa.getRoomType(), villa.getConvennient(),
                    villa.getAreaPool(), villa.getNumberOfFloor()));
            csvPrinter.flush();
            System.out.println("Write csv file by using new Apache lib successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAllVillaNotDuplicate() {
        TreeSet<Villa> villas = readCsvFileNotDuplicate();
        int count = 1;
        for(Villa villa: villas){
            System.out.print(count + ". \t");
            System.out.print(villa.showInfor());
            System.out.println();
            System.out.println("==============================================");
            count++;
        }
    }

    @Override
    public int compare(Villa o1, Villa o2) {
        return o1.getId().compareTo(o2.getId());
    }
}

