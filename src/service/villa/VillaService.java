package service.villa;

import models.Villa;
import repository.FuncWriteAndReadFileCSV;

public interface VillaService extends FuncWriteAndReadFileCSV<Villa> {
    void addNewVilla();
    void showAllVilla();
    Villa getVillaById(int id);
    void showAllVillaNotDuplicate();
}
