package ultils.compare;

import models.Villa;

import java.util.Comparator;

public class VillaCompare implements Comparator<Villa> {
    @Override
    public int compare(Villa o1, Villa o2) {
        if (o1.getId().equals(o2.getId()) || o1.getName().equals(o2.getName())){
            return 0;
        }
        return 1;
    }
}
