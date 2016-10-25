/**
 * Denne klassen representer en gjenstand spilleren kan kjøpe.
 * 
 * @author skj006
 */
public class Item {
    
    private String name, description, action;
    private int value, weight;
    private boolean expendable;

    /**
     * Konstruktør for Item
     * @param name          navnet på gjenstanden
     * @param description   beskrivelse av gjenstanden
     * @param action        hva skjer når spilleren bruker gjenstanden
     * @param value         verdien på gjenstanden i gull
     * @param weight        vekten på gjenstanden
     */
    public Item(String name, String description, String action, int value, int weight) {
        setName(name);
        setDescription(description);
        setAction(action);
        setValue(value);
        setWeight(weight);
        setExpendable(false);
    }

    /**
     * Konstruktør for Item
     * @param name          navnet på gjenstanden
     * @param description   beskrivelse av gjenstanden
     * @param action        hva skjer når spilleren bruker gjenstanden
     * @param value         verdien på gjenstanden i gull
     * @param weight        vekten på gjenstanden
     * @param expendable    boolean som sier om gjenstanden blir oppbrukt
     */
        public Item(String name, String description, String action, int value, int weight, boolean expendable) {
        setName(name);
        setDescription(description);
        setAction(action);
        setValue(value);
        setWeight(weight);
        setExpendable(expendable);
    }

    /**
     * Aksessmetode for navnet på gjenstanden.
     * @return String returnerer navnet på gjenstanden
     */
    public String getName() {
        return name;
    }

    /**
     * Mutasjonsmetode for navnet på gjenstanden.
     * @param name det nye navnet på gjenstanden
     */
    public void setName(String name) {
        this.name = Utilities.checkString(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Utilities.checkString(description);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = Utilities.checkString(action);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = Utilities.cleanNegative(value);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = Utilities.cleanNegative(weight);
    }
    
    public boolean getExpendable() {
        return expendable;
    }
        
    public void setExpendable(boolean expendable) {
        this.expendable = expendable;
    }

    /**
     * Aksessmetode for informasjon om gjenstanden.
     * @return String navn, beskrivelse, action, verdi, og vekt i strengformat
     */
    public String toString() {
        return "Name: " + getName() + "\n"
                + "Description: " + getDescription() + "\n"
                + "Action: " + getAction() + "\n"
                + "Value: " + getValue() + "\n"
                + "Weight: " + getWeight() + "\n";
    }
}
