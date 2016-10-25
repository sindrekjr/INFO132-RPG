
/**
 * Klassen representerer et monster i et RPG-spill. Subklasse av Creature.
 * 
 * @author skj006
 */
public class Monster extends Creature {

    private String description;
    
    /**
     * Bred konstruktør for alskens monstre. I denne utgaven av spillet har alle monstre 20 strength. 
     * @param name      navnet på monsteret. 
     * @param health    helsen og makshelsen til monsteret.
     * @param gold      gullbeholdningen til monsteret.
     */
    public Monster(String name, int health, int gold, String description) {
        super(name, 20, health, health, gold);
        setDescription(description);
    }
    
    /**
     * Mutasjonsmetode for feltet description.
     * @param description beskrivelse av monsteret.
     */
    public void setDescription(String description) {
        this.description = Utilities.checkString(description.trim());
    }
    
    /**
     * Aksessmetode for feltet description.
     * @return String beskrivelse av monsteret. 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Metode for å printe informasjon om et monster. 
     * @return String samlet streng med all info om monsteret. 
     */
    public String toString() {
        return super.toString() 
                + "\n"
                + "Description: " + getDescription();
    }
}
