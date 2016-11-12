
/**
 * Subklasse for gjenstander av typen Potion.
 * 
 * @author skj006
 */
public class Potion extends Item {
    private int potency; 
    
    /**
     * Konstruktør for Potion. expendable settes som standard til true for alle potions. 
     * @param name
     * @param description
     * @param action
     * @param value
     * @param weight
     * @param potency
     */
    public Potion(String name, String description, String action, int value, int weight, int potency) {
        super(name, description, action, value, weight, true);
        setPotency(potency);
    }
    
    /**
     * Metode for å bruke en gjenstand.
     * @param user personen som bruker gjenstanden.
     */
    public void use(ItemUser user) {
        if(user.drink(this)) {
            System.out.println(user.getName() + " " + this.getAction() + " a " + this.getName().toLowerCase() + " and regains " + this.getPotency() + " health.");
        }
    }
    
    public int getPotency() {
        return this.potency;
    }
    
    public void setPotency(int potency) {
        this.potency = potency; 
    }
    
    public String toString() {
        return super.toString() +
                "\nPotency: " + potency;
    }
}