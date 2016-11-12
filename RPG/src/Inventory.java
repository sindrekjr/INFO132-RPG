import java.util.ArrayList;
import java.util.HashMap;

/**
 * Inventory-klassen representerer ryggsekken til en spiller i et enkelt RPG-spill.
 * 
 * @author skj006
 */
public class Inventory extends HashMap<String, ArrayList<Item>> {    
    public Inventory() {
        
    }

    /**
     * Metode for å sjekke om en gjenstand finnes i inventory. Brukes av alle andre lignende metoder. 
     * @param key navnet på gjenstand, key i HashMap
     * @return boolean true hvis gjenstanden finnes
     */
    public boolean contains(String key) {
        return super.containsKey(key.trim().toLowerCase());
    }
    
    public boolean contains(Item item) {
        if(item != null) {
            return this.contains(item.getName());
        } else {
            return false; 
        }
    }
    
    /**
     * Aksessmetode for bestemt gjenstand. 
     * @param key navnet på gjenstand. 
     * @return Item
     */
    public Item get(String key) {
        if(super.get(key.trim().toLowerCase()) != null) {
            return super.get(key.trim().toLowerCase()).get(0);
        } else {
            return null;
        }
    }

    /**
     * Aksessmetode som henter alle gjenstander av typen Potion
     * @return ArrayList<Potion>
     */
    public ArrayList<Potion> getAllPotions() {
        ArrayList<Potion> potions = new ArrayList<>();
        for(ArrayList<Item> list : values()) {
            for(Item item : list) {
                if(item instanceof Potion) {
                    Potion potion = (Potion) item;
                    potions.add(potion);
                }
            }
        }
        return potions; 
    }
    
    /**
     * Aksessmetode som henter alle gjenstander av typen Weapon
     * @return ArrayList<Weapon>
     */
    public ArrayList<Weapon> getAllWeapons() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        for(ArrayList<Item> list : values()) {
            for(Item item : list) {
                if(item instanceof Weapon) {
                    Weapon weapon = (Weapon) item;
                    weapons.add(weapon);
                }
            }
        }
        return weapons;
    }
    
    /**
     * Mutasjonsmetode for å legge til en gjenstand i inventory. 
     * @param item gjenstanden som skal legges til
     */
    public void add(Item item) {
        String key = item.getName().toLowerCase();
        if(this.contains(key)) {
            super.get(key).add(item);
        } else {
            ArrayList<Item> list = new ArrayList<>();
            super.put(key, list);
            list.add(item);
        }
    }
    
    /**
     * Mutasjonsmetode for å fjerne en gjenstand fra inventory. Benyttes av alle lignende metoder. 
     * @param item navnet på gjenstanden som skal fjernes.
     */
    public void remove(String item) {
        if(this.contains(item)) {
            super.get(item.trim().toLowerCase()).remove(0);
            if(super.get(item.trim().toLowerCase()).size() <= 0) {
                super.remove(item.trim().toLowerCase());
            }
        }
    }
    
    public void remove(String item, int amount) {
        for(int x = 0; x < amount; x++) {
            this.remove(item);
        }
    }
    
    public void remove(Item item) {
        this.remove(item.getName());
    }
    
    public void remove(Item item, int amount) {
        this.remove(item.getName(), amount);
    }
    
    /**
     * Mutasjonsmetode for å fjerne alle metoder av en type. Fjerner selve listen som er value. 
     * @param item navnet på typen gjenstander man ønsker å fjerne
     */
    public void removeAllSuchItems(String item) {
        if(this.contains(item)) {
            super.remove(item.trim().toLowerCase());
        }
    }
    
    public void removeAllSuchItems(Item item) {
        this.removeAllSuchItems(item.getName());
    }
    
    /**
     * Mutasjonsmetode for å tømme hele inventory. 
     */
    public void empty() {
        super.clear();
    }
    
    /**
     * Regner ut den samlede vekten på alle gjenstandene i inventory. 
     * @return int totalvekten
     */
    public int totalWeight() {
        int weight = 0;
        for(ArrayList<Item> list : values()) {
            for(Item item : list) {
                weight += item.getWeight();
            }
        }
        return weight;
    }
    
    public String toString() {
        String info = "";
        for(ArrayList<Item> list : values()) {
            info += list.get(0).getName();
            if(list.size() > 1) {
                info += " (" + list.size() + ")";
            }
            info += ", ";
        }
        return info;
    }
}