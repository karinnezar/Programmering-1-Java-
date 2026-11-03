//Karin Mustafa 
import java.util.ArrayList;
import java.util.Comparator;

public class DogRegister {
    private InputReader input = new InputReader();
    private DogCollection dogCollection = new DogCollection();
    private OwnerCollection ownerCollection = new OwnerCollection();
    private boolean running = true;

    private void start() {
        initialize();
        runCommandLoop();
        shutDown();
    }
    

    private void initialize() {
        System.out.println("Welcome to the dog register!");
        System.out.println("The following commands are available:");
        System.out.println("- Register new owner");
        System.out.println("- Remove owner");
        System.out.println("- Register new dog");
        System.out.println("- Remove dog");
        System.out.println("- List dogs");
        System.out.println("- List owners");
        System.out.println("- Increase age of a dog");
        System.out.println("- Give dog to owner");
        System.out.println("- Remove dog from owner");
        System.out.println("- Exit");
    }

    private void runCommandLoop() {
        while (running) {
            String command = input.handleText("Enter command").trim().toLowerCase();
            handleCommand(command);
        }
    }

    private void handleCommand(String command) {
        switch (command) {
            case "register new owner":
                registerNewOwner();
                break;
            case "remove owner":
                removeOwner();
                break;
            case "register new dog":
                registerNewDog();
                break;
            case "remove dog":
                removeDog();
                break;
            case "list dogs":
                listDogs();
                break;
            case "list owners":
                listOwners();
                break;
            case "increase age":
                increaseAge();
                break;
            case "give dog to owner":
                giveDogToOwner();
                break;
            case "remove dog from owner":
                removeDogFromOwner();
                break;
            case "exit":
                System.out.println("Dog Register shut down.");
                shutDown();
                break;
            default:
                System.out.println("Error! Unknown command. Try again.");
                break;
        }
    }

    private void shutDown() {
        running = false;
    }

    private void registerNewOwner() {
        String name = input.handleText("Enter owner name");

        while (name.isEmpty()) {
            System.out.println("Error: A blank string is not allowed, try again");
            name = input.handleText("Enter owner name");
        }

        Owner owner = new Owner(name);
        if (ownerCollection.addOwner(owner)) {
            System.out.println("The owner " + owner.getName() + " has been added to the register.");
        } else {
            System.out.println("Error: The owner " + owner.getName() + " already exists.");
        }
    }

    private void removeOwner(){
        if(ownerCollection.getOwners().size() == 0){  
            System.out.println("Error: No owners in register");
            return;
        }
        String ownerName = input.handleText("Name");

        while(ownerName.isBlank()){   
            System.out.println("Error: Name cannot be blank. Please enter a valid name.");
            ownerName = input.handleText("Enter owner name");
        }
    

         Owner ownerToRemove = ownerCollection.getOwner(ownerName); 
        if (ownerToRemove == null) {
            System.out.println("Error: The owner " + ownerName + " doesn't exist");
            return;
        }
        for(Dog dog : ownerToRemove.getDogs()){ 
            dog.setOwner(null);
            dogCollection.removeDog(dog);
        }

        ownerCollection.removeOwner(ownerToRemove);
        System.out.println("The owner " + ownerToRemove.getName() + " has been removed from the register");
    
    }

    private void registerNewDog() {
        String dogName = input.handleText("Enter dog name");
        while (dogName.isEmpty()) {
            System.out.println("Error: A blank string is not allowed, try again");
            dogName = input.handleText("Enter dog name");
        }

        if (dogCollection.containsDog(dogName)) {
            System.out.println("Error: The dog " + dogName + " already exists.");
            return;
        }

        String breed = input.handleText("Enter dog breed");

        while (breed.isEmpty()) {
            System.out.println("Error: Breed cannot be blank.");
            breed = input.handleText("Enter dog breed");
        }

        int age = input.handleInt("Enter dog's age:");
        int weight = input.handleInt("Enter dog's weight:");

        Dog dog = new Dog(dogName, breed, age, weight);
        if (dogCollection.addDog(dog)) {
            System.out.println("The dog " + dogName + " has been added to the register.");
        } 
    }

    private void removeDog() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register.");
            return;
        }

        String dogName = input.handleText("Enter dog name");
        if (!dogCollection.containsDog(dogName)) {
            System.out.println("Error: The dog " + dogName + " doesn't exist.");
            return;
        }

        Dog dog = dogCollection.getDog(dogName);
        if (dog != null && dog.getOwner() != null) {
            dog.getOwner().removeDog(dog);
            dog.setOwner(null);
        }

        dogCollection.removeDog(dogName);
        System.out.println("The dog " + dogName + " has been removed from the register.");
    }

    private void listDogs() {
        ArrayList<Dog> dogs = dogCollection.getDogs();
        if (dogs.isEmpty()) {
            System.out.println("Error! No dogs registered.");
            return;
        }

        double minTailLength = input.handleDouble("Enter the minimum tail length");
        dogs = dogCollection.getDogsByTailLength(minTailLength);

        for (Dog dog : dogs) {
            System.out.println(dog);
        }
    }

    private void listOwners() {
        ArrayList<Owner> owners = ownerCollection.getOwners();
        if (owners.isEmpty()) {
            System.out.println("Error: No owners in register.");
            return;
        }
    
        owners.sort(Comparator.comparing(Owner::getName));
        for (Owner owner : owners) {
            String dogsOwned = "";
            for (Dog dog : owner.getDogs()) {
                dogsOwned += dog.getName() + " ";
            }
            System.out.println(owner.getName() + ": " + dogsOwned);
        }
    }

    private void increaseAge() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs are registered.");
            return;
        }
        String dogName = input.handleText("Enter dog name");
        Dog dog = dogCollection.getDog(dogName);
        if (dog == null) {
            System.out.println("Error: The dog " + dogName + " doesn't exist");
            return;
        }
    
        dog.updateDogAge();
        System.out.println("The dog " + dog.getName() + " is now " + dog.getAge() + " years old.");
    }

    private void giveDogToOwner() {
        if (!ensureDogsAndOwners()) {
            return;
        }
    
        Dog dog = getTheRightDog();
        if (dog == null) {
            return; 
        }
    
        if (dog.getOwner() != null) {
            System.out.println("Error: This dog already has an owner.");
            return;
        }
    
        Owner owner = getTheRightOwner();
        if (owner == null) {
            return; 
        }
    
        dog.setOwner(owner);
        owner.addDog(dog);
        dogCollection.removeDog(dog.getName());
        System.out.println("Dog " + dog.getName() + " is now owned by " + owner.getName() + ".");
    }

    private void removeDogFromOwner() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs are registered.");
            return;
        }
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners are registered.");
            return;
        }
        String dogName = input.handleText("Enter dog's name");
        Dog dog = dogCollection.getDog(dogName);
        if (dog == null) {
            System.out.println("Error: The dog " + dogName + " doesn't exist");
            return;
        }
        if (dog.getOwner() == null) {
            System.out.println("Error: This dog does not have an owner.");
            return;
        }
        Owner owner = dog.getOwner();
        owner.removeDog(dog); 
        dog.setOwner(null);
        dogCollection.addDog(dog);
        System.out.println("Dog " + dog.getName() + " removed from owner " + owner.getName() + ".");
    }    
    
    private boolean ensureDogsAndOwners() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs are registered.");
            return false;
        }
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners are registered.");
            return false;
        }
        return true;
    }
    
    private Dog getTheRightDog() {
        String dogName = input.handleText("Enter the dog's name");
        Dog dog = dogCollection.getDog(dogName);
        if (dog == null) {
            System.out.println("Error: No dog with that name found.");
            return null;
        }
        return dog;
    }
    
    private Owner getTheRightOwner() {
        String ownerName = input.handleText("Enter the owner's name");
        Owner owner = ownerCollection.getOwner(ownerName);
        
        if (owner == null) {
            System.out.println("Error: The owner \"" + ownerName + "\" doesn't exist.");
            return null;
        }
        return owner;
    }

    public static void main(String[] args) {
        new DogRegister().start();
    }
}