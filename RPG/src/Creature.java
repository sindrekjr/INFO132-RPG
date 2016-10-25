
/**
 * Superklasse for forskjellige skapninger i et RPG-spill. 
 * 
 * @author skj006
 */
public class Creature {

    private String name;
    private int strength, maxDamage, health, maxHealth, gold;
    
    /**
     * Konstruktør for Creature.
     * @param name      hvilket navn skapningen skal ha.
     * @param strength  hvor mye styrke skapningen skal ha.
     * @param health    hvor mye helse skapningen skal ha. 
     * @param maxHealth hvor høy hels skapning KAN ha. Settes normalt til samme som starthelse i subklasser.
     * @param gold      hvor mye gull skapningen skal ha.
     */
    public Creature(String name, int strength, int health, int maxHealth, int gold) {
        setName(name);
        setStrength(strength);
        setMaxDamage(strength + 12);
        setHealth(health);
        setMaxHealth(maxHealth);
        setGold(gold);
    }

    /**
     * Metode for å endre helsen til skapningen. Helsen kan bli endret, både med positive og negative heltall.
     * @param newHealth hvor mye helsen skal endres.
     * @return int      hvor mye helse som gjenstår etter endring.
     */
    public int changeHealth(int newHealth) {
        setHealth(this.health + newHealth);
        if(this.health > this.maxHealth) {
            setHealth(this.maxHealth);
        }
        return getHealth();
    }
    
    /**
     * Metode for å angripe en annen skapning. 
     * @param target    skapning som skal angripes.
     * @return boolean  forteller om target døde eller ikke.
     */
    public boolean attack(Creature target) {
        int damage = (this.getStrength() + Utilities.rollDice(2, 6));
        if(damage > this.maxDamage) {
            damage = this.maxDamage;
        }
        int currentHealth = target.changeHealth(-damage);
        
        System.out.println(this.getName() + " attacks " + target.getName() + " for " + damage + " damage. " + target.getName() + " has " + currentHealth + " health remaining.");
        
        return target.isDead();
    }

    /**
     * Metode for å sjekke om spilleren er død.
     * @return true hvis helsen til spillern er <= 0, false hvis ikke
     */
    public boolean isDead() {
        return this.health <= 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = Utilities.checkString(name.trim());
    }
    
    public int getStrength() {
        return this.strength;
    }
    
    public void setStrength(int strength) {
        this.strength = Utilities.cleanNegative(strength);
    }

    public int getMaxDamage() {
        return this.maxDamage;
    }
    
    public void setMaxDamage(int maxDamage){
        this.maxDamage = Utilities.cleanNegative(maxDamage);
    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Utilities.cleanNegative(health);
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }
    
    private void setMaxHealth(int maxHealth) {
        this.maxHealth = Utilities.cleanNegative(maxHealth);
    }
    
    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = Utilities.cleanNegative(gold);
    }

    /**
     * Aksessmetode for grunleggende informasjon om en skapning.
     * @return String navn, helse, styrke, og gull i strengformat
     */
    public String toString() {
        return "-----------------------------\n"
                + "Name: " + getName() + "\n"
                + "Health: " + getHealth() + "/" + getMaxHealth() + "\n"
                + "Strength: " + getStrength() + "\n"
                + "Gold: " + getGold() + " gold pieces\n";
    }
}
