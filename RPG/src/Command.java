
/**
 * Command mottar input-informasjon om lager et objekt som holder på teksten skrevet inn. 
 * 
 * @author skj006
 */
public class Command {
    private CommandWord commandWord;
    private String secondary; 
    
    /**
     * Konstruktør for objekter av klassen Command. 
     * @param commandWord oppdaget kommando til programmet fra bruker/spiller
     * @param secondary ordet etter kommandoen, kan brukes for å identifisere
     *                  hva som skal håndteres; ikke begrenset til bestemt type
     */
    public Command(CommandWord commandWord, String secondary) {
        setCommandWord(commandWord);
        setSecondary(secondary);
    }
    
    /**
     * Aksessmetode for kommandoordet.
     * @return CommandWord
     */
    public CommandWord getCommandWord() {
        return this.commandWord;
    }
    
    /**
     * Aksessmetode for å returnere kommandoordet i strengformat.
     * @return String
     */
    public String getCommandWordString() {
        return this.getCommandWord().toString();
    }
    
    /**
     * Mutasjonsmetode for kommandoordet. Settes til UNKNOWN hvis null.
     * @param input kommandoordet som skal settes
     */
    public void setCommandWord(CommandWord input) {
        if((this.commandWord = input) == null) {
            this.commandWord = CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Aksessmetode for sekundærordet.
     * @return String
     */
    public String getSecondary() {
        return secondary;
    }
    
    public void setSecondary(String input) {
        if((this.secondary = input) == null) {
            this.secondary = "";
        }
    }
    
    /**
     * Sjekker om kommandoordet er ukjent. 
     * @return boolean true hvis kommandoordet er designert UNKWNON av enum
     */
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }
    
    /**
     * Sjekker om der er et ord etter kommandoordet. 
     * @return boolean true hvis objektet har et sekundærord
     */
    public boolean hasSecondary() {
        return (secondary != null);
    }
}