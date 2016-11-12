import java.util.ArrayList;

/**
 * Main-klasse for spillet. Her oppretter vi en spiller og en verden som man kan interagere med. 
 * 
 * @author skj006
 */
public abstract class Main {
    private final static InputReader reader = new InputReader();
    private static Area world; 
    
    /**
     * Hovedmetode for RPG-spill. Starter spillet. 
     */
    public static void main(String[] args) {
        Player player = new Player("Thorrogg", "Barbarian");
        player.buyItem(new Potion("Potion", "A potion that restores 50 health.", "drinks", 20, 1, 50));
        player.buyItem(new Weapon("Greathammer", "A great hammer.", "draws", 50, 12, 20));
        
        generate();
        world.enter(player);
    }
    
    /**
     * Genererer områder, monstre og gjenstander. 
     */
    private static void generate() {
        world = getCity("Thistletown", getAreas(getItems(), getMonsters()));
    }
    
    /**
     * Genererer en by. 
     * @param name navn på byen
     * @param areas områder som byen skal inneholde
     * @return City
     */
    private static City getCity(String name, ArrayList<Area> areas) {
        return new City("Thistletown", areas, reader);
    }
    
    /**
     * Genererer områder. 
     * @param items gjenstander til områdene
     * @param monsters monstre til områdene
     * @return ArrayList<Area>
     */
    private static ArrayList<Area> getAreas(ArrayList<Item> items, ArrayList<Monster> monsters) {
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(new Store("Bartering Bartholomew's", items, reader));
        areas.add(new Battleground("Arena", monsters, reader));
        return areas;
    }
    
    /**
     * Genererer monstre.
     * @return ArrayList<Monster>
     */
    private static ArrayList<Monster> getMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster("Castiel", 100));
        monsters.add(new Monster("Demogorgon", 80));
        monsters.add(new Monster("Dragon", 65));
        monsters.add(new Monster("Goblin", 25));
        monsters.add(new Monster("Ogre", 45));
        monsters.add(new Monster("Sentient Ball of Fire", 40));
        return monsters;
    }
    
    /**
     * Genererer gjenstander.
     * @return ArrayList<Item>
     */
    private static ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Potion("PotionX", "The best potion.", "drinks", 50, 1, 100));
        items.add(new Potion("Potion", "A potion that restores 50 health.", "drinks", 20, 1, 50));
        items.add(new Potion("Potion", "A potion that restores 50 health.", "drinks", 20, 1, 50));
        items.add(new Weapon("Longsword", "A long sword.", "draws", 30, 5, 12));
        items.add(new Armor("Chainmail", "A full-body amored suit made up of thousands of interlocking metal rings.", "dons", 200, 16, 22));
        items.add(new Armor("Leather", "A leather suit capable of deflecting some damage.", "dons", 15, 6, 10));
        return items; 
    }
}
