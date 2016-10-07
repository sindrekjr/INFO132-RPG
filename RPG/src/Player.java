import java.util.ArrayList;

/**
 * Player er en karakter i et rollespill.
 * 
 * Ingen setters for inventory og capacity da disse er final. 
 * 
 * @author skj006 
 * @version 29.09.2016
 */
public class Player
{
    private String name;
    private String type;
    private int health;
    private int gold;
    private final ArrayList<Item> inventory = new ArrayList<>();
    private final int capacity = 50;

    /**
     * Klassens konstruktør. Players health vil alltid settes til 100. Gold settes til 200. 
     * 
     * @param name      Player-navn
     * @param type      Player-type
     */
    public Player (String name, String type) {
        setName(name);
        setType(type);
        setHealth(100);
        setGold(200);
    }

    /**
     * Mutasjonsmetode for feltet name. 
     * 
     * @param String      nytt navn for Player. 
     */
    public void setName(String name) {
        this.name = Checkers.checkString(name);
    }

    /**
     * Mutasjonsmetode for feltet type. 
     * 
     * @param String      ny type for Player. 
     */
    public void setType(String type) {
        switch(type.toLowerCase()) {
            case "mage":
                this.type = "Mage";
                break;
            case "ranger":
                this.type = "Ranger";
                break;
            case "rogue":
                this.type = "Rogue";
                break;
            case "warrior":
                this.type = "Warrior";
                break;
            default:
                this.type = "Unspecified";
                break;
        }
    }

    /**
     * Mutasjonsmetode for feltet health.
     * 
     * @param int       ny health for Player.
     */
    public void setHealth(int health) {
        this.health = Checkers.checkIfPositive(health);
        
        if(health > 100) {
            this.health = 100;
        }
    }
    
    /**
     * Mutasjonsmetode for feltet gold. 
     * 
     * @param int       ny mengde gull for Player.
     */
    public void setGold(int gold) {
        this.gold = Checkers.checkIfPositive(gold);
    }
    
    /**
     * Endrer helsen for Player ved å legge til helse ved positivt heltall i parameter, 
     * eller trekke fra ved negativt heltall i parameter.
     * 
     * @param healthPoints  positivt eller negativt heltall som skal trekkes fra Players health. 
     */
    public void changeHealth(int healthPoints) {
        this.health += healthPoints;
        
        if(health < 0) {
            health = 0;
        } else if(health > 100) {
            health = 100;
        }
    }
    
    /**
     * Endrer gullbeholdningen for Player ved å legge til gull ved positivt heltall i parameter,
     * eller trekke fra ved negativt heltall i parameter. Dersom det skal trekkes fra mer gull enn 
     * det som gjenstår i Players gullbeholdning, stopper metoden.
     * 
     * @parameter goldPieces    positivt eller negativt heltall som skal legger til Players gullbeholdning. 
     */
    public void changeGold(int goldPieces) {
        this.gold += goldPieces;
        
        this.gold = Checkers.checkIfPositive(gold);
    }
    
    /**
     * Mutasjonsmetode for å kjøpe et item. Sjekker at spiller har nok gold og capacity. 
     * 
     * @param Item    det Item man vil kjøpe.
     */
    public void buyItem(Item item) {
        int playerGold = getGold();
        int inWeight = totalWeight();

        int itemCost = item.getValue();
        int itemWeight = item.getWeight();
        
        if((playerGold >= itemCost) && ((inWeight + itemWeight) <= capacity)) { //Sjekker om spiller har nok gold og capacity.
            changeGold(-itemCost);
            inventory.add(item);
        }
    }
    
    /**
     * Mutasjonsmetode for å selge et item.
     * 
     * @param Item    det Item man vil selge.
     */
    public void sellItem(Item item) {
        int itemValue = item.getValue();
        
        if(inventory.contains(item)) {
            changeGold(itemValue);
            inventory.remove(item);
        }
    }
        
    /**
     * Aksessmetode for å finne et item.
     * 
     * @return String   navn på Item man søker på.
     * @return null     hvis Item-navn ikke funnet.
     */
    public Item findItem(String itemSearch) {
        for(Item item : inventory) {
            if(item.getName().equalsIgnoreCase(itemSearch)) {
                return item;
            }
        }
        
        return null;
    }

    /**
     * Aksessmetode for weight. 
     * 
     * @return int  samlet vekt av items i inventory.
     */
    public int totalWeight() {
        int weightSum = 0;
        
        for(Item item : inventory) {
            weightSum += item.getWeight();
        }
        
        return weightSum;
    }
    
    /**
     * Sjekker om Player er død.
     * 
     * @return true     hvis død.
     * @return false    hvis levende.
     */
    public boolean isDead() {
        if (health <= 0) {
            return true;
        } else {
            return false;
        }       
    }
    
    /**
     * Aksessmetode for name.
     * 
     * @return String   navn på Player.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Aksessmetode for type.
     * 
     * @return String   type Player.
     */
    public String getType() {
        return type;
    }

    /**
     * Aksessmetode for name.
     * 
     * @return int      heltall som representerer helsen for Player.
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Aksessmetode for gold.
     * 
     * @return int      heltall som representerer gullbeholdningen til Player.
     */
    public int getGold() {
        return gold;
    }
    
    /**
     * Aksessmetode for inventory. 
     * 
     * @return ArrayList        returnerer ArrayList-objektet inventory. 
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    
    /**
     * Aksessmetode for capacity. 
     * 
     * @return int      heltall som representerer Player sin capacity.
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Skriver ut informasjon om Player til terminalen.
     */
    public void print() {
        System.out.println("---");
        System.out.println(name.toUpperCase());
        System.out.println("Type: " + type);
        System.out.println("Health: " + health + "/100");
        System.out.println("Gold: " + gold + " gp");
        System.out.println("Currently carrying: " + totalWeight() + " of " + capacity + " kg");
        if (isDead()) {
            System.out.println("Status: DEAD");
        } else {
            System.out.println("Status: ALIVE");
        }
        System.out.println("Inventory:");
        for(Item item : inventory) {
            item.print();
        }
        System.out.println();
    }
}
