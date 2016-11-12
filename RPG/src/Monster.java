/**
 * Denne klassen representerer et monster i spillet. Klassen er en subklasse av Character
 * 
 * @author skj006
 */
public class Monster extends Character {    
    /**
     * Konstruktør for Monster
     * Konstruktøren setter minDamage for monster til 20 siden det står spesifisert i oppgaven
     * @param name navnet på monsteret
     * @param maxDamage maksimumsskaden monsteret kan gjøre.
     */
    public Monster(String name, int maxDamage) {
        super(name, 100, 100, 20, maxDamage);
    }
    
    public boolean useItem(Item item) {
        return false;
    }
    
    public Item findItem(String search) {
        if(hasEquippedWeapon()) {
            if(getEquippedWeapon().getName().toLowerCase() == search.trim().toLowerCase()) {
                return getEquippedWeapon();
            }
        } 
        return null;
    }
}
