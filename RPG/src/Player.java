import java.util.HashMap;

/**
 * Klassen representerer en spiller i et RPG-spill. Subklasse av Creature. 
 * 
 * @author skj006
 */
public class Player extends Creature {
    
    private String type;
    private int carryWeight;
    private final HashMap<String, Item> inventory = new HashMap<>();

    /**
     * Konstruktør for spilleren. Denne konstruktøren setter helsen og makshelsen til 100, gullbeholdningen til 500 og vekten spilleren kan bære til 50kg. Styrke settes tilfeldig, men minst 10.
     * @param name      Navnet på spilleren
     * @param type      Spillerens type. Spilleren kan bare være en av følgende [Mage, Warrior, Rogue, Ranger]. Hvis annet er spesifisert blir typen satt til Unspecified
     */
    public Player(String name, String type) {
        super(name, (Utilities.rollDice(2,6) + 10), 100, 100, 500);
        setType(type);
        setCarryWeight(50);
    }

    /**
     * Konstruktør for spilleren. Denne konstruktøren lar deg selv definere hvor sterk spilleren er, hvor mye gull spilleren har, og hvor mange kg spilleren kan bære.
     * @param name      Navnet på spilleren
     * @param type      Spillerens type. Spilleren kan bare være en av følgende [Mage, Warrior, Rogue, Ranger]. Hvis annet er spesifisert blir typen satt til Unspecified
     * @param strength  Spillerens styrke
     * @param weight    Antall kg spilleren kan bære
     * @param gold      Antall gull spilleren har
     */
    public Player(String name, String type, int strength, int weight, int gold) {
        super(name, strength, 100, 100, gold);
        setType(type);
        setCarryWeight(weight);
    }

    /**
     * Metode for å kjøpe en gjenstand.
     * @param item      gjenstanden som spilleren vil kjøpe
     * @return boolean  true hvis spilleren får kjøpt gjenstanden, false hvis ikke
     */
    public boolean buyItem(Item item) {
       boolean bought;
       if(bought = (canCarryWeight(item) && hasEnoughMoneyToBuy(item))) {
            this.inventory.put(item.getName().toLowerCase(), item);
            setGold(getGold() - item.getValue());
       }
       return bought;
    }

    /**
     * Metode for å finne en gjenstand som spilleren har kjøpt
     * @param search    Navnet på gjenstanden du ønsker å finne
     * @return Item     referanse til gjenstanden hvis den blir funnet, null hvis ikke
     */
    public Item findItem(String search) {
        return inventory.get(search.toLowerCase().trim());
    }

    /**
     * Metode for å selge en gjenstand
     * @param item      gjenstanden som vil bli solgt
     * @return boolean  true hvis spilleren får solgt gjenstanden, false hvis ikke
     */
    public boolean sellItem(Item item) {
        boolean sold;
        if(sold = (findItem(item.getName()) != null)) {
            setGold(getGold() + item.getValue());
            inventory.remove(item.getName().toLowerCase());
        }
        return sold;
    }
    
    /**
     * Metode for å bruke et item. Hvis gjenstanden kun kan brukes en gang blir den også fjernet fra inventory. 
     * @param item      gjenstanden som skal brukes
     * @return boolean  true hvis gjenstand ble funnet og brukt, false hvis ikke
     */
    public boolean useItem(Item item) {
        boolean used;
        if(used = (findItem(item.getName()) != null)) {
            System.out.println(getName() + " " + item.getAction() + " a " + item.getName().toLowerCase() + ".");
            if(item.getExpendable()) {
                this.inventory.remove(item.getName().toLowerCase());
            }
        }
        return used;
    }

    /**
     * Hjelpemetode for å sjekke om spilleren kan bære en gjenstand.
     * @param item      gjenstanden vi sjekker mot
     * @return boolean  true hvis spilleren har kapasitet til å bære gjenstanden, false hvis ikke
     */
    public boolean canCarryWeight(Item item) {
        return (totalWeight() + item.getWeight()) <= this.carryWeight;
    }

    /**
     * Hjelpemetode for å sjekke om spilleren har nok penger til å kjøpe gjenstanden
     * @param item      gjenstanden spilleren ønsker å kjøpe
     * @return boolean  true hvis spilleren har nok penger, false hvis ikke
     */
    public boolean hasEnoughMoneyToBuy(Item item) {
        return getGold() > item.getValue();
    }

    /**
     * Metode for å returnere totalvekten spilleren bærer på det. Dvs. alle gjenstandene til spilleren.
     * @return int  totalvekt som spilleren bærer
     */
    public int totalWeight() {
        int weight = 0;
        for(Item item : inventory.values()) {
            weight += item.getWeight();
        }
        return weight;
    }

    /**
     * Metode for å sjekke at riktig type blir satt på spilleren.
     * @param type      Spillerens type. Spilleren kan bare være en av følgende [Mage, Warrior, Rogue, Ranger]. Hvis annet er spesifisert blir typen satt til Unspecified
     * @return String   typen til spilleren. Hvis feil type blir sendt som parameter, da blir typen returnert som Unspecified
     */
    public String checkType(String type) {
        switch(type.toLowerCase().trim()) {
            case "warrior":
                return "Warrior";
            case "rogue":
                return "Rogue";
            case "mage":
                return "Mage";
            case "ranger":
                return "Ranger";
            default:
                return "Unspecified";
        }
    }

    public String getType() {
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

    public HashMap<String, Item> getInventory() {
        return inventory;
    }
    
    /**
     * Aksess for all informasjon om spilleren. Gjenstandene til spilleren blir også printet, hvis spilleren har noen gjenstander.
     * @return String type, vekt, og inventory i strengformat
     */
    public String toString() {
        return super.toString() 
                + "Type: " + getType()+ "\n"
                + getName() + " can carry " + getCarryWeight() + " kg. They are currently carrying " + this.totalWeight() + " kg.\n"
                + "\n"
                + "Inventory: " + inventoryToString() + "\n"
                + "------------------------------";
    }

    /**
     * Aksessmetode for en spesifikk gjenstand i inventory.
     * @param itemName navnet på item det gjelder 
     * @return String informasjon om gjenstanden i strengformat
     */
    public String itemToString(String itemName) {
        return findItem(itemName).toString();
    }
    
    /**
     * Aksessmetode for alle gjenstandene til spilleren.
     * @return String navn på alle items i inventory i strengformat
     */
    private String inventoryToString() {
        String inventoryList = "";
        for(Item item : inventory.values()) {
            inventoryList += item.getName() + ", ";
        }
        return inventoryList;
    }
}
