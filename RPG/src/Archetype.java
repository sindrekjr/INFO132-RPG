
/**
 * Enumerasjon for alle gyldige typer i et enkelt RPG-spill.
 * 
 * @author skj006
 */
public enum Archetype {
    Alchemist("Alchemist"), 
    Barbarian("Barbarian"), Bard("Bard"), 
    Cavalier("Cavalier"), Cleric("Cleric"), Commoner("Commoner"), 
    Druid("Druid"),
    Inquisitor("Inquisitor"), 
    Monk("Monk"), 
    Ninja("Ninja"), 
    Paladin("Paladin"), 
    Ranger("Ranger"), Rogue("Rogue"), 
    Samurai("Samurai"), Sorcerer("Sorcerer"), Summoner("Summoner"), 
    Warrior("Warrior"), Witch("Witch"), Wizard("Wizard"),
    Unspecified("Unspecified");
    
    private String archetype;
    
    Archetype(String archetype) {
        this.archetype = archetype;
    }
    
    public String toString() {
        return this.archetype;
    }
}