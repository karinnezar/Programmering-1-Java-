//Karin Mustafa 
import java.util.ArrayList;

public class Owner implements Comparable<Owner>{
    private String name;
    private ArrayList<Dog> dogsOwned;

    public Owner(String name){
        this.name = name.toUpperCase();
        this.dogsOwned = new ArrayList<Dog>();
    }

    public String getName(){
        return name;
    }

    public int compareTo(Owner other){
        return this.getName().compareTo(other.getName());
    }

    public boolean addDog(Dog dogA){
        if(this != dogA.getOwner()){
            return false;
        }if (dogsOwned.contains(dogA)){
            return false;
        }
            dogsOwned.add(dogA);
            return true;
    }

    public boolean removeDog(Dog dogA){
      return dogsOwned.remove(dogA);
    }

    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> copyOfDogs = new ArrayList<>(dogsOwned);
        DogSorter.sortDogs(new DogNameComparator(), copyOfDogs);
        return copyOfDogs;
    }

    @Override
    public String toString(){
        return this.name + " " + this.dogsOwned.toString();
    }

}