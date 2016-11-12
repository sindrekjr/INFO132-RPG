/**
 * Denne klassen representer en gjenstand spilleren kan kjøpe
 * 
 * @author skj006
 */
public class Item {
    private String name, description, action;
    private int value, weight;
    private boolean expendable;

    /**
     * Konstruktør for Item
     * @param name navnet på gjenstanden
     * @param description beskrivelse av gjenstanden
     * @param action hva skjer når spilleren bruker gjenstanden
     * @param value verdien på gjenstanden i gull
     * @param weight vekten på gjenstanden
     */
    public Item(String name, String description, String action, int value, int weight) {
        this.name = name;
        this.description = description;
        this.action = action;
        setValue(value);
        setWeight(weight);
        setExpendable(false);
    }

    /**
     * Konstruktør for Item
     * @param name navnet på gjenstanden
     * @param description beskrivelse av gjenstanden
     * @param action hva skjer når spilleren bruker gjenstanden
     * @param value verdien på gjenstanden i gull
     * @param weight vekten på gjenstanden
     */
    public Item(String name, String description, String action, int value, int weight, boolean expendable) {
        this.name = name;
        this.description = description;
        this.action = action;
        setValue(value);
        setWeight(weight);
        setExpendable(expendable);
    }

    /**
     * Supermetode for bruken av alle items. 
     * @param user personen som bruker gjenstanden
     */
    public void use(ItemUser user) {
        System.out.println(user.getName() + " " + this.getAction() + " a " + this.getName());
    }
    
    public String getName() {
        return name;
    }

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
        return this.expendable;
    }
    
    public void setExpendable(boolean expendable) {
        this.expendable = expendable;
    }
        
    /**
     * ToString returnerer informasjon om item.
     * @return informasjon om item
     */
    @Override
    public String toString() {
        String info = "";
        info += "Name: " + name;
        info += "\nDescription: " + description;
        info += "\nAction: " + action;
        info += "\nValue: " + value;
        info += "\nWeight: " + weight;
        if(expendable) {
            info += "\nUse: Single";
        }
        return info;
    }
}
