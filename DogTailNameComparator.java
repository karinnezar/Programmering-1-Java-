//Karin Mustafa 
import java.util.Comparator;

public class DogTailNameComparator implements Comparator<Dog> {

    @Override
    public int compare(Dog dogA, Dog dogB) {
        if(dogA.getTailLength() < dogB.getTailLength()){
            return -1;
        }
        if(dogA.getTailLength() > dogB.getTailLength()){
            return 1;
        }
        return dogA.getName().compareTo(dogB.getName());
    }
}