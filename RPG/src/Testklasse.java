import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Testklasse.
 *
 * @author  skj006
 */
public class Testklasse
{
    /**
     * Default constructor for test class Testklasse
     */
    public Testklasse() {        
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        Player player1 = new Player("Marwyn", "Mage");
        Item item1 = new Item("Breastplate", "Metal plate armor covering the upper torso.", "wears", 200, 15);
        Item item2 = new Item("Potion of Awesome", "The best potion.", "drinks", 200, 1, true);
        player1.buyItem(item1);
        player1.buyItem(item2);
    }

    @Test
    public void positiveSellItem() {
        Player player1 = new Player("Marwyn", "Mage");
        Item item1 = new Item("Breastplate", "Metal plate armor covering the upper torso.", "wears", 200, 15);
        player1.buyItem(item1);
        assertEquals(true, player1.sellItem(item1));
    }
    
    @Test
    public void negativeSellItem() {
        Player player2 = new Player("Henrik", "Rogue");
        Item item2 = new Item("Potion of Awesome", "The best potion.", "drinks", 200, 1, true);
        assertEquals(false, player2.sellItem(item2));
    }

    @Test
    public void negativeBuyItem() {
        Player player3 = new Player("Grums", "Warrior", 50, 500, 1);
        Item item1 = new Item("Breastplate", "Metal plate armor covering the upper torso.", "wears", 200, 15);
        assertEquals(false, player3.buyItem(item1));
    }
    
    @Test
    public void testingChangeHealth() {
        Player player3 = new Player("Grums", "Warrior", 50, 500, 1);
        assertEquals(0, player3.changeHealth(-150));
        assertEquals(100, player3.changeHealth(10023243));
    }
    
    @Test
    public void negativeSetNameAndType() {
        Player player4 = new Player("Evil Wizard of Badness", "Wizard");
        player4.setName("");
        assertEquals("Unspecified", player4.getName());
        assertEquals("Unspecified", player4.getType());
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testPrint()
    {
        Player player5 = new Player("Max von Sydow", "Rogue");
        Item item1 = new Item("Some thing", "Yep.", "things itself.", 50, 10);
        Item item2 = new Item("Another", "Thing.", "is thinging.", 100, 2, true);
        assertEquals(true, player5.buyItem(item1));
        assertEquals(true, player5.buyItem(item2));
        assertEquals(true, player5.buyItem(item2));
        System.out.println(player5.toString());
    }
}

