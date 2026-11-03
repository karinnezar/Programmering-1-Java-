//Karin Mustafa 
import java.util.ArrayList;

public class DogCollection{
    private ArrayList<Dog> dogs = new ArrayList<>();

    public boolean addDog(Dog uniqueDog) {
        if (uniqueDog == null || containsDog(uniqueDog.getName())) {
            return false; 
        }
        dogs.add(uniqueDog);
        return true;
    }

    public boolean removeDog(String nameOfDog) {
        Dog dog = getDog(nameOfDog);
        if (dog != null && dog.getOwner() == null) {
            dogs.remove(dog);
            return true;
        }
        return false;
    }
    
    public boolean removeDog(Dog dogA) {
        if (dogA != null && dogA.getOwner() == null) {
            return dogs.remove(dogA);
        }
        return false;
    }

    public boolean containsDog(String nameOfDogs) {
        for (Dog dog : dogs) {
            if (dog.getName().equalsIgnoreCase(nameOfDogs)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDog(Dog dogA){
        return dogs.contains(dogA);
    }

    public Dog getDog(String name) {
        for (Dog dog : dogs) {
            if (dog.getName().equalsIgnoreCase(name)) {
                return dog;
            }
        }
        return null;
    }

    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> copyOfDogs = new ArrayList<>(dogs);
        DogNameComparator comparator = new DogNameComparator();

       DogSorter.sortDogs(comparator, copyOfDogs);
       return copyOfDogs;
    }

    public ArrayList<Dog> getDogsByTailLength(double minTailLength) {
        ArrayList<Dog> filteredDogs = new ArrayList<>();
        for (Dog dog : dogs) {
            if (dog.getTailLength() >= minTailLength) {
                filteredDogs.add(dog);
            }
        }
        DogTailNameComparator comparator = new DogTailNameComparator();
        DogSorter.sortDogs(comparator, filteredDogs);
        return filteredDogs;
    }  
}