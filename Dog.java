//Karin Mustafa 
public class Dog {
    private static final double DASHSHUND_TAIL_LENGTH = 3.7;
    private String name;
    private int age;
    private String breed;
    private int weight;
    private Owner owner;

    public Dog(String name, String breed, int age, int weight) {
        this.name = name.toUpperCase();
        this.breed = breed.toUpperCase();
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean setOwner(Owner owner) {
        if (this.owner == owner) {
            return false;
        }

        if (this.owner != null && owner != null && !this.owner.equals(owner)) {
            return false;
        }

        if (this.owner != null) {
            this.owner.removeDog(this);
        }

        this.owner = owner;
        if (owner != null && !owner.getDogs().contains(this)) {
            owner.addDog(this);
        }
        
        return true;
    }

    public void updateDogAge() {
        if (age < Integer.MAX_VALUE) {
            age++;
        }
    }

    public double getTailLength() {
        if (breed.equalsIgnoreCase("TAX") || breed.equalsIgnoreCase("DACHSHUND")) {
            return DASHSHUND_TAIL_LENGTH;
        }
        return (age * weight) / 10.0;
    }

    @Override
    public String toString() {
        return name + " " + breed + " " + age + " " + weight + " " + getTailLength()
                + (owner != null ? " Owner: " + owner.getName() : "");
    }
}
