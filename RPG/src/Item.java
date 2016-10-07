
/**
 * Item er en klasse for gjenstander i et rollespill.
 * 
 * @author skj006 
 * @version 29.09.2016
 */
public class Item
{
    private String name;
    private int value;
    private int weight;
    private String action;
    private String description;

    /**
     * Konstruktør for objekter av klassen Item. Objektene som kan lages er predefinerte, og
     * for å lage et slikt objekt må man kalle på riktig navn som parameter. 
     * 
     * @param name          Item-navn/type
     * @param value         Item-verdi
     * @param weight        Item-vekt
     * @param action        Item-handling
     * @param description   Item-beskrivelse
     */
    public Item(String name, int value, int weight, String action, String description) {
        setName(name);
        setValue(value);
        setWeight(weight);
        setAction(action);
        setDescription(description);
    }
    
    /**
     * Mutasjonsmetode for feltet name.
     * 
     * @param newName       nytt navn for Item.
     */
    public void setName(String newName) {
        this.name = Checkers.checkString(newName);
    }
    
    /**
     * Mutasjonsmetode for feltet value.
     * 
     * @param newValue      ny verdi for Item.
     */
    public void setValue(int newValue) {
        this.value = Checkers.checkIfPositive(newValue);
    }
    
    /**
     * Mutasjonsmetode for feltet weight.
     * 
     * @param newWeight     ny vekt for Item.
     */
    public void setWeight(int newWeight) {
        this.weight = Checkers.checkIfPositive(newWeight);
    }
    
    /**
     * Mutasjonsmetode for feltet action.
     * 
     * @param newAction     ny action for Item.
     */
    public void setAction(String newAction) {
        this.action = Checkers.checkString(newAction);
    }
    
    /**
     * Mutasjonsmetode for feltet description.
     * 
     * @param newDescription    ny beskrivelse for Item.
     */
    public void setDescription(String newDescription) {
        this.description = Checkers.checkString(newDescription);
    }
    
    /**
     * Aksessmetode for feltet name.
     * 
     * @return String       navn på Item. 
     */
    public String getName() {
        return name;
    }
    
    /**
     * Aksessmetode for feltet value.
     * 
     * @return int          heltall for verdi på Item.
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Aksessmetode for feltet weight.
     * 
     * @return int          heltall for vekt på Item.
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Aksessmetode for feltet action.
     * 
     * @return String       handling for Item.
     */
    public String getAction() {
        return action;
    }
    
    /**
     * Aksessmetode for feltet description.
     * 
     * @return String       beskrivelse av Item.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Skriver ut informasjon om Item til terminalen.
     */
    public void print() {
        System.out.println(name.toUpperCase());
        System.out.println("Value: " + value + " gp");
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Action: " + action);
        System.out.println("Description: " + description);
        System.out.println();
    }
}
