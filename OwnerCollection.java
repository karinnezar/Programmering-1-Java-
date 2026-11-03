//Karin Mustafa 
import java.util.ArrayList;

public class OwnerCollection {
    private Owner[] owners = new Owner[0]; 
    private int size; 

    public boolean addOwner(Owner owner) {
        if (!containsOwner(owner.getName())) {
            if (size == owners.length) {
                    growOwnerArray(); 
            }
            owners[size++] = owner;
            return true;
        }
        return false;
    }

    public boolean removeOwner(String nameOfOwner) {
        for (int i = 0; i < size; i++) {
            if (owners[i] != null && owners[i].getName().equalsIgnoreCase(nameOfOwner)) {
                
                if (!owners[i].getDogs().isEmpty()) {
                    return false; 
                }
                owners[i] = owners[size - 1];
                owners[size - 1] = null; 
                size--; 
    
                return true; 
            }
        }
        return false; 
    }

    public boolean removeOwner(Owner owner) {
        if (owner == null) {
            return false;
        }
        return removeOwner(owner.getName());
    }

    public boolean containsOwner(String name) {
        for (int i = 0; i < size; i++) {
            if (owners[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsOwner(Owner ownerName) {
        return containsOwner(ownerName.getName());
    }

    public Owner getOwner(String name) {
        for (int i = 0; i < size; i++) {
            if (owners[i] != null && owners[i].getName().equalsIgnoreCase(name)) {
                return owners[i];
            }
        }
        return null;
    } 
           
    public ArrayList<Owner> getOwners() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (owners[j].getName().compareTo(owners[minIndex].getName()) < 0) {
                    minIndex = j;
                }
            }
            Owner temp = owners[minIndex];
            owners[minIndex] = owners[i];
            owners[i] = temp;
        }
        
        ArrayList<Owner> ownerList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ownerList.add(owners[i]);
        }
        return ownerList;
    }

    private void growOwnerArray() {
        Owner[] newOwners = new Owner[owners.length == 0 ? 1 : owners.length * 2];
        System.arraycopy(owners, 0, newOwners, 0, owners.length);
        owners = newOwners;
    }
    
}
