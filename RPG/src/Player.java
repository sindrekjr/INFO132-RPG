import java.util.ArrayList;
import java.util.stream.*;

/**
 * Klassen representerer en spiller i et RPG-spill
 * 
 * @author skj006
 */
public class Player extends Character {
    private Archetype type;
    private int carryWeight;
    protected static Inventory inventory;

    /**
     * Konstruktør for spilleren. Denne konstruktøren setter helsen til 100, gullbeholdningen til 1000 og vekten spilleren kan bære til 50kg.
     * @param name Navnet på spilleren
     * @param type Spillerens type. Spilleren kan bare være en av følgende [Mage, Warrior, Rogue, Ranger]. Hvis annet er spesifisert blir typen satt til unspecified.
     */
    public Player(String name, String type) {
        super(name, 100, 500, 20, 70);
        setType(type);
        setGold(1000);
        setCarryWeight(500);
        this.inventory = new Inventory();
    }

    /**
     * Konstruktør for spilleren. Denne konstruktøren lar deg selv definere hvor mye gull spilleren har og hvor mange kg spilleren kan bære
     * @param name Navnet på spilleren
     * @param type Spillerens type. Spilleren kan bare være en av følgende [Mage, Warrior, Rogue, Ranger]. Hvis annet er spesifisert blir typen satt til unspecified.
     * @param weight Antall kg spilleren kan bære
     * @param gold Antall gull spilleren har
     */
    public Player(String name, String type, int weight, int gold, int minDamage, int maxDamage) {
        super(name, 100, gold, minDamage, maxDamage);
        setType(type);
        setCarryWeight(weight);
        this.inventory = new Inventory();
    }

    /**
     * Metode for å bruk en gjenstand
     * @param item the item to use
     */
    public boolean useItem(Item item) {
        boolean used = false;
        if(used = getInventory().contains(item)) {
            item.use(this);
            if(item.getExpendable()) {
                inventory.remove(item);
            }
        }
        return used; 
    }

    /**
     * Metode for å finne en gjenstand som spilleren har kjøpt
     * @param search Navnet på gjenstanden du ønsker å finne
     * @return gjenstanden hvis den blir funnet, null hvis ikke
     */
    public Item findItem(String search) {
        return inventory.get(search);
    }

    /**
     * Metode for å kjøpe en gjenstand.
     * @param item gjenstanden som spilleren vil kjøpe
     * @return true hvis spilleren får kjøpt gjenstanden, false hvis ikke
     */
    public boolean buyItem(Item item) {
       boolean bought = false; 
       if(bought = (canCarryWeight(item) & hasEnoughMoney(item))) {
            this.inventory.add(item);
            super.setGold(super.getGold() - item.getValue());
       } 
       return bought;
    }

    /**
     * Metode for å selge en gjenstand
     * @param item gjenstanden som vil bli solgt
     * @return boolean true hvis det går fint å selge
     */
    public boolean sellItem(Item item) {
        boolean sold = false;
        if(sold = (inventory.contains(item))) {
            this.inventory.remove(item);
            super.setGold(super.getGold() + item.getValue());
        }
        return sold; 
    }

    /**
     * Hjelpemetode for å sjekke om spilleren kan bære en gjenstand.
     * @param item gjenstanden vi sjekker mot
     * @return true hvis spilleren har kapasitet til å bære gjenstanden, false hvis ikke
     */
    public boolean canCarryWeight(Item item) {
        return (totalWeight() + item.getWeight()) <= this.carryWeight;
    }

    /**
     * Hjelpemetode for å sjekke om spilleren har nok penger til å kjøpe gjenstanden
     * @param item gjenstanden spilleren ønsker å kjøpe
     * @return true hvis spilleren har nok penger, false hvis ikke
     */
    public boolean hasEnoughMoney(Item item) {
        return super.getGold() > item.getValue();
    }

    /**
     * Metode for å returnere totalvekten spilleren bærer på det. Dvs. alle gjenstandene til spilleren.
     * @return
     */
    public int totalWeight() {
        return inventory.totalWeight();
    }

    /**
     * Hjelpemetode for å trimme og returnere en string som lowercase (små bokstaver)
     * @param value Verdien du vil trimme og konvertere til små bokstaver
     * @return verdien, etter at trim og små bokstaver er lagt til
     */
    private String trimAndLowerCase(String value) {
        return value.trim().toLowerCase();
    }
    
    public Archetype checkType(String type) {
        for(Archetype archetype : Archetype.values()) {
            if(trimAndLowerCase(type).equalsIgnoreCase(archetype.toString())) {
                return archetype;
            }
        }
        return Archetype.Unspecified;
    }

    public Archetype getType() {
        return type;
    }

    public void setType(String type) {
        this.type = checkType(type);
    }

    public int getCarryWeight() {
        return carryWeight;
    }

    public void setCarryWeight(int carryWeight) {
        this.carryWeight = carryWeight;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * ToString returnerer informasjon om player
     * @return informasjon om player
     */
    @Override
    public String toString() {
        String info =  super.toString();
        info += "\nType: " + type.toString();
        info += "\nWeight the player can carry: " + carryWeight;
        info += "\nEquipped Weapon: ";
        if(hasEquippedWeapon()) {
            info += getEquippedWeapon().getName();
        } else {
            info += "None";
        }
        
        info += "\nInventory: ";
        info += inventory.toString();

        return info;
    }
}
