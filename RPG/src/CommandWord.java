
/**
 * Enumerasjon for alle kommandoord som eksisterer i et enkelt RPG-spill.
 * 
 * @author skj006
 */
public enum CommandWord {
    ATTACK("attack", "When you type \"attack\", you attack the monster before the monster attacks you."), 
    BUY("buy", "To buy an item, type \"buy\" followed by the name of the item you wish to buy."), 
    CONTINUE("continue", "To start over in the battleground, type \"continue\"."),
    DRINK("drink", "To drink a potion, type \"drink\" followed by the name of the potion you wish to drink."), 
    ENTER("enter", "To enter an area, type \"enter\" followed by the name of the area you wish to enter."), 
    EQUIP("equip", "To equip a weapon, type \"equip\" followed by the name of the weapon you wish to equip."), 
    EXIT("exit", "Type \"exit\" to leave the area."), 
    FIGHT("fight", "Type \"fight\" to accept an upcoming battle."), 
    HELP("help", "Type \"help\" to show all valid commands for this area."), 
    RUN("run", "When you type \"run\", the game ends, the monsters wins and you lose."), 
    SEARCH("search", "Type \"search\" followed by the name of the item that you're looking for, or \"all\" to show all items in the store."), 
    SELL("sell", "To sell and item, type \"sell\" followed by the name of the item you wish to sell."), 
    UNEQUIP("unequip", "To unequip your weapon, type \"unequip\"."),
    USE("use", "When you type \"use\" followed by the name of an item in your inventory, you use that item."),
    UNKNOWN("?", "?");
    
    private String commandString;
    private String description;
    
    /**
     * Initialiserer med kommandostrengen.
     * @param commandString kommandostrengen
     * @param description beskrivelse av kommandoen
     */
    CommandWord(String commandString, String description) {
        this.commandString = commandString;
        this.description = description;
    }
    
    /**
     * @return Kommandostreng i strengformat
     */
    public String toString() {
        return commandString;
    }
    
    /**
     * @return Beskrivelse i strengformat
     */
    public String getDescription() {
        return description;
    }
}
