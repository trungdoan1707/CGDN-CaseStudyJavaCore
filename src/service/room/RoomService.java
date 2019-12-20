package service.room;

import models.Room;
import repository.FuncWriteAndReadFileCSV;

public interface RoomService extends FuncWriteAndReadFileCSV<Room> {
    void addNewRoom();
    void showAllRoom();
    Room getRoomById(int id);
    void showAllRoomNotDuplicate();
}
