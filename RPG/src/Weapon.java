
/**
 * Subklasse for gjenstander av typen våpen.
 * 
 * @author skj006
 */
public class Weapon extends Item {
    private int damage;
    
    /**
     * Konstruktør for Weapon. 
     * @param name
     * @param description
     * @param action
     * @param value
     * @param weight
     * @param damage
     */
    public Weapon(String name, String description, String action, int value, int weight, int damage) {
        super(name, description, action, value, weight);
        setDamage(damage);
    }
    
    /**
     * Metode for å bruke en gjenstand.
     * @param user personen som skal bruke gjenstanden
     */
    public void use(ItemUser user) {
        if(user.equip(this)) {
            System.out.println(user.getName() + " " + this.getAction() + " a " + this.getName().toLowerCase() + " and is ready to fight!");
        } else if(!user.equip(this)) {
            System.out.println(user.getName() + " sheathes their " + this.getName() + ".");
        }
    }
    
    public int getDamage() {
        return this.damage;
    }
    
    public void setDamage(int damage) {
        this.damage = damage; 
    }
    
    public String toString() {
        return super.toString() +
                "\nDamage: " + damage;
    }
}
