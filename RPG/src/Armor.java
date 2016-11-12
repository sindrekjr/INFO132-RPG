
/**
 * Subklasse for gjenstander av typen rustning. 
 * 
 * @author skj006
 */
public class Armor extends Item {
    private int protection;
    
    /**
     * Konstruktør for Armor. 
     * @param name
     * @param description
     * @param action
     * @param value
     * @param weight
     * @param protection
     */
    public Armor(String name, String description, String action, int value, int weight, int protection) {
        super(name, description, action, value, weight);
        setProtection(protection);
    }
    
    public int getProtection(int protection) {
        return this.protection;
    }
    
    public void setProtection(int protection) {
        this.protection = protection;
    }
    
    public String toString() {
        return super.toString() +
                "\nProtection: " + protection;
    }
}