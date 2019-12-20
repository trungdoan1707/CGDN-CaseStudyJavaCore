package Views;

import controllers.MainController;
import models.House;
import models.Room;
import models.Villa;

public class Main {
    public static void main(String[] args) {
//        Villa villa1 = new Villa("villa1", "Villa 01", 400, 20000, 5
//                , "For Week", "VIP", "GymRoom", 300, 3);
//
//        House house1 = new House("house1", "House 01", 400, 20000, 5
//                , "For Week", "VIP", "GymRoom", 3);
//
//        Room room1 = new Room("room1", "Room 01", 400, 20000, 5
//                , "For Week", "GymRoom");
//        System.out.println(villa1.showInfor());
//        System.out.println("-----------------------------------------------");
//        System.out.println(house1.showInfor());
//        System.out.println("-----------------------------------------------");
//        System.out.println(room1.showInfor());
        MainController mainController = new MainController();
        mainController.displayMainMenu();
    }
}
