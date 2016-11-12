import java.util.Random;

/**
 * En character representer en type i spillet. Den typen kan enten være Player eller Monster.
 * Vi har samlet de fellestrekkene som gjelder for Player og Monster i denne klassen
 * 
 * @author skj006
 */
public abstract class Character implements ItemUser {
    private String name;
    private int health, gold, minDamage, maxDamage;
    private Weapon equippedWeapon;

    /**
     * Konstruktør for Character
     * @param name navnet på karakteren
     * @param health helsen til karakteren
     * @param gold gullbeholdningen til karakteren
     * @param minDamage minimumsskaden karakteren kan gjøre
     * @param maxDamage maksimumsskaden karakteren kan gjøre
     */
    public Character(String name, int health, int gold, int minDamage, int maxDamage) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    /**
     * Metode for å angripe en karakter
     * @param character Karakteren man vil angripe
     * @return true hvis karakteren dør, false hvis ikke
     */
    public boolean attack(Character character) {
        Random random = new Random();
        int damage = random.nextInt(getMaxDamage() - getMinDamage()) + getMinDamage();
        if(this.hasEquippedWeapon()) {
            damage += this.getEquippedWeapon().getDamage();
        }
        character.changeHealth(-damage);
        System.out.println(this.name + " attacks " + character.getName() + " for " + damage + " damage. " + character.getName() + " now has " + character.getHealth() + " health remaining");
        return character.isDead();
    }
    
    /**
     * Metode for å drikke en helsedrikk. Sjekker at den finnes i inventory, og trekkes fra inventory 
     * etterpå. Gjort private slik at metoden kun brukes av Player-klassen.
     * @param potion drikken som skal drikkes
     * @return boolean true hvis det går fint å drikke
     */
    public boolean drink(Potion potion) {
        int heal = potion.getPotency();
        this.changeHealth(heal);
        return true;
    }
    
    public boolean equip(Weapon weapon) {
        if(this.equippedWeapon == weapon) {
            setEquippedWeapon(null);
            return false;
        } else { 
            setEquippedWeapon(weapon);
            return true; 
        }
    }
    
    /**
     * Metode for å endre helsen til en karakter
     * @param newHealth den nye helsen til karakteren.
     */
    public void changeHealth(int amount) {
        this.health += amount;
        if (this.health > 100) {
            this.health = 100;
        } else if(this.health < 0) {
            this.health = 0;
        }
    }

    /**
     * Metode for å øke gullbeholdningen til karakteren
     * @param gold gullet vi vil øke med
     */
    public void addGold(int gold) {
        this.gold += gold;
    }

    /**
    * Metode for å sjekke om karakteren er død
    * @return true hvis helsen til karakteren er <= 0, false hvis ikke
    */
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * Returnerer navnet på karakteren
     * @return navnet på karakteren
     */
    public String getName() {
        return name;
    }

    /**
     * Sett et nytt navn på karakteren
     * @param name det nye navnet på karakteren
     */
    public void setName(String name) {
        this.name = Utilities.checkString(name);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Utilities.cleanNegative(health);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = Utilities.cleanNegative(gold);
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = Utilities.cleanNegative(minDamage);
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = Utilities.cleanNegative(maxDamage);
    }
    
    public Weapon getEquippedWeapon() {
        return this.equippedWeapon;
    }
    
    public void setEquippedWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public boolean hasEquippedWeapon() {
        return (getEquippedWeapon() != null);
    }
    
    public void printCurrentStats() {
        System.out.println("--------- Current Stats -----------");
        System.out.println(toString());
    }

    public void changeGold(int gold) {
        this.gold += gold;
    }
    
    /**
     * ToString-metode som returnerer informasjon om character
     * @return informasjon om character
     */
    @Override
    public String toString() {
        String info = "Name: " + name;
        info += "\nHealth: " + health;
        info += "\nGold: " + gold;
        info += "\nMinimum damage: " + minDamage;
        info += "\nMaximum damage: " + maxDamage;
        return info;
    }
}
