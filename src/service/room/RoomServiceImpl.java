package service.room;

import models.Room;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import ultils.compare.RoomCompare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RoomServiceImpl implements RoomService {

    private static final String PATH_FILE_ROOM = "src/data/Room.csv";
    private static final String[] FILE_HEADER_OF_ROOM = {"id", "nameService", "area", "rentalPrice"
            , "maxNumberOfPeople", "typeRent", "freeService"};

    @Override
    public void addNewRoom() {
        Scanner scanner = new Scanner(System.in);
        Room room = new Room();
        room.setId(UUID.randomUUID().toString().replace("-", ""));
        System.out.println("Input Room Name: ");
        room.setName(scanner.nextLine());
        System.out.println("Input Area: ");
        room.setArea(scanner.nextDouble());
        System.out.println("Input Max People");
        room.setMaxPeople(scanner.nextInt());
        System.out.println("Input Price: ");
        room.setRentPrice(scanner.nextDouble());
        scanner = new Scanner(System.in);
        System.out.println("Input Type For Rent: ");
        room.setTypeForRent(scanner.nextLine());
        System.out.println("Input Free Service: ");
        room.setFreeService(scanner.nextLine());
        //Gọi phương thức ghi file csv
        writeCsvFile(room);
    }

    @Override
    public void showAllRoom() {
        List<Room> listRoom = readCsvFile();
        for(int i = 0; i < listRoom.size(); i++){
            System.out.print((i+1) + ". \t");
            System.out.print(listRoom.get(i).showInfor());
            System.out.println();
            System.out.println("==============================================");
        }
    }

    @Override
    public Room getRoomById(int id) {
        List<Room> roomList = readCsvFile();
        Room room = roomList.get(id-1);
        return room;
    }

    @Override
    public void showAllRoomNotDuplicate() {
        TreeSet<Room> rooms = readCsvFileNotDuplicate();
        int count = 1;
        for(Room room: rooms){
            System.out.print(count + ". \t");
            System.out.print(room.showInfor());
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
    public void writeCsvFile(Room room) {
        try {
            List<Room> oldRoom = readCsvFile();
            Writer writer = new PrintWriter(PATH_FILE_ROOM);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_ROOM));
            if (oldRoom != null) {
                for (Room room1 : oldRoom) {
                    csvPrinter.printRecord(Arrays.asList(room1.getId(), room1.getName(), room1.getArea(), room1.getRentPrice()
                            , room1.getMaxPeople(), room1.getTypeForRent(), room1.getFreeService()));
                }
            }

            csvPrinter.printRecord(Arrays.asList(room.getId(), room.getName(), room.getArea(), room.getRentPrice()
                    , room.getMaxPeople(), room.getTypeForRent(), room.getFreeService()));
            csvPrinter.flush();
            System.out.println("Write csv file by using new Apache lib successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> readCsvFile() {
        List<Room> oldRoom = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_FILE_ROOM));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_ROOM).withIgnoreHeaderCase().withTrim());
            //Biến i để check vòng lặp
            int i = 0;
            for (CSVRecord csvRecords : csvParser) {
                //Vòng lặp đầu tiên sẽ bỏ qua vì đấy là header
                if (i == 0) {
                    i++;
                } else {
                    Room room = new Room();
                    room.setId(csvRecords.get(0));
                    room.setName(csvRecords.get(1));
                    room.setArea(Double.parseDouble(csvRecords.get(2)));
                    room.setRentPrice(Double.parseDouble(csvRecords.get(3)));
                    room.setMaxPeople(Integer.parseInt(csvRecords.get(4)));
                    room.setTypeForRent(csvRecords.get(5));
                    room.setFreeService(csvRecords.get(6));
                    oldRoom.add(room);
                }
            }
            return oldRoom;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TreeSet<Room> readCsvFileNotDuplicate() {
        TreeSet<Room> oldRoom = new TreeSet<>(new RoomCompare());
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_FILE_ROOM));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(FILE_HEADER_OF_ROOM).withIgnoreHeaderCase().withTrim());
            //Biến i để check vòng lặp
            int i = 0;
            for (CSVRecord csvRecords : csvParser) {
                //Vòng lặp đầu tiên sẽ bỏ qua vì đấy là header
                if (i == 0) {
                    i++;
                } else {
                    Room room = new Room();
                    room.setId(csvRecords.get(0));
                    room.setName(csvRecords.get(1));
                    room.setArea(Double.parseDouble(csvRecords.get(2)));
                    room.setRentPrice(Double.parseDouble(csvRecords.get(3)));
                    room.setMaxPeople(Integer.parseInt(csvRecords.get(4)));
                    room.setTypeForRent(csvRecords.get(5));
                    room.setFreeService(csvRecords.get(6));
                    oldRoom.add(room);
                }
            }
            return oldRoom;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}