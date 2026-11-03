//Karin Mustafa 

import java.util.Comparator;

public class DogNameComparator implements Comparator<Dog>{

     @Override
        public int compare(Dog dogA, Dog dogB) {
            return dogA.getName().compareTo(dogB.getName());
        }
}
