package models;

public class House extends Service {
    private String roomType;
    private String convennient;
    private int numberOfFloor;

    public House() {
    }

    public House(String id, String nameService, double areaUsed, double rentPrice, int maxNumberOfPeople
            , String typeRent, String roomType, String convenientDescription, int numberOfFloors) {
        super(id, nameService, areaUsed, rentPrice, maxNumberOfPeople, typeRent);
        this.roomType = roomType;
        this.convennient = convenientDescription;
        this.numberOfFloor = numberOfFloors;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getConvennient() {
        return convennient;
    }

    public void setConvennient(String convennient) {
        this.convennient = convennient;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    @Override
    public String showInfor() {
        return " Id Service: " + super.getId() +
                "\t Name Service: " + super.getName() +
                "\t Area: " + super.getArea() +
                "\t Rent Price: " + super.getRentPrice() +
                "\t Type For Rent: " + super.getTypeForRent() +
                "\t Max People: " + super.getMaxPeople() +
                "\t RoomType: " + this.getRoomType() +
                "\t Convenient: " + this.getConvennient() +
                "\t Number Of Floor: " + this.getNumberOfFloor()
                ;
    }
}
