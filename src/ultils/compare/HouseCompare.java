package ultils.compare;

import models.House;

import java.util.Comparator;

public class HouseCompare implements Comparator <House> {
    @Override
    public int compare(House o1, House o2) {
        if (o1.getId().equals(o2.getId()) || o1.getName().equals(o2.getName())){
            return 0;
        }
        return 1;
    }
}
