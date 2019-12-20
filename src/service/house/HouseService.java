package service.house;

import models.House;
import repository.FuncWriteAndReadFileCSV;

public interface HouseService extends FuncWriteAndReadFileCSV <House>{
    void addNewHouse();
    void showAllHouse();
    House getHouseById(int id);
    void showAllHouseNotDuplicate();
}
