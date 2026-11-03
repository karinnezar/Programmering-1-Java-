//Karin Mustafa 
import java.util.ArrayList;
import java.util.Comparator;

public class DogSorter {

    public static int sortDogs(Comparator<Dog> comparator, ArrayList<Dog> dogs) {
        int swapCount = 0; 
        for (int i = swapCount; i < dogs.size() - 1; i++) {
            int minDogIndex = nextDog(comparator, dogs, i);

            if (minDogIndex != i) {
                swapDogs(dogs, i, minDogIndex);
                swapCount++;
            }
        }
        return swapCount; 
    }

    private static void swapDogs(ArrayList<Dog> dogs, int indexDogA, int indexDogB) {
        Dog temp = dogs.get(indexDogA);
        dogs.set(indexDogA, dogs.get(indexDogB));
        dogs.set(indexDogB, temp);
    }
    
    private static int nextDog(Comparator<Dog> comparator, ArrayList<Dog> dogs, int initialDogIndex) {
        int minDogIndex = initialDogIndex; 
        for (int i = initialDogIndex + 1; i < dogs.size(); i++) {
            if (comparator.compare(dogs.get(i), dogs.get(minDogIndex) ) < 0) {
                minDogIndex = i;
            }
        }
        return minDogIndex;
    }
}