import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * @author skj006
 */
public class PlayerTest {
    private Player player;
    private Item knife, bulldozer, dragonHelmet, potion, axe;

    @Before
    public void setUp() throws Exception {
        this.player = new Player("Sindre", "Warrior", 100, 100, 20, 80);
        this.knife = new Item("Knife", "Used to cut throats", "slashes", 15, 1);
        this.bulldozer = new Item("Bulldozer", "Used to bulldoze things", "runs over", 150, 80);
        this.potion = new Potion("Potion", "Heals health", "drinks", 20, 1, 50);
        this.dragonHelmet = new Item("Helmet", "Is used when slaying dragons", "wears", 1000, 10000);
        this.player.buyItem(knife);
        this.player.buyItem(bulldozer);
    }

    @Test
    public void isDead() throws Exception {
        assertFalse(this.player.isDead());
        this.player.setHealth(-100);
        assertTrue(this.player.isDead());
    }

    @Test
    public void changeHealth() throws Exception {
        assertEquals(100, this.player.getHealth());
        this.player.changeHealth(-200);
        assertEquals(0, this.player.getHealth());
        this.player.changeHealth(50);
        assertEquals(50, this.player.getHealth());
        this.player.changeHealth(400);
        assertEquals(100, this.player.getHealth());
    }

    @Test
    public void negativeSetNameTest() {
        this.player.setName("");
        assertEquals("Unspecified", this.player.getName());
        this.player.setName(null);
        assertEquals("Unspecified", this.player.getName());
    }

    @Test
    public void positiveBuyItem() {
        assertTrue(this.player.buyItem(this.potion));
    }

    @Test
    public void negativeBuyItem() {
        assertFalse(this.player.buyItem(this.bulldozer));
    }

    @Test
    public void buyItem() throws Exception {
        assertTrue(this.player.buyItem(this.knife));
        assertEquals(this.knife, player.getInventory().get("Knife"));
        assertFalse(this.player.buyItem(this.bulldozer));
    }


    @Test
    public void findItem() {
        this.player.buyItem(this.knife);
        assertEquals(this.knife, this.player.findItem("Knife"));
        assertNull(this.player.findItem("Axe"));
    }


    @Test
    public void positiveSellItem() {
        this.player.buyItem(this.knife);
        assertTrue(this.player.sellItem(this.knife));
    }

    @Test
    public void negativeSellItem() {
        assertFalse(this.player.sellItem(this.potion));
    }

    @Test
    public void checkType() {
        String warrior = "WarrioR    ";
        String dragonborn = "Dragonborn";
        String conan = "    rANger    ";
        assertEquals(Archetype.Warrior, this.player.checkType(warrior));
        assertEquals(Archetype.Unspecified, this.player.checkType(dragonborn));
        assertEquals(Archetype.Ranger, this.player.checkType(conan));
    }

    @Test
    public void useItem() {
        this.player.getInventory().add(this.potion);
        assertTrue(this.player.useItem(this.potion));
        assertFalse(this.player.useItem(this.axe));
    }

    @Test
    public void checkString() {
        String name = "Sindre";
        String unknown = "";
        assertEquals(name, Utilities.checkString(name));
        assertEquals("Unspecified", Utilities.checkString(unknown));
    }

    @Test
    public void cleanNegative() {
        int number1 = 10;
        assertEquals(10, Utilities.cleanNegative(number1));
        int number2 = -10;
        assertEquals(0, Utilities.cleanNegative(number2));
    }

    @Test
    public void canCarryWeight() {
        assertTrue(this.player.canCarryWeight(this.knife));
        assertFalse(this.player.canCarryWeight(this.dragonHelmet));
    }

    @Test
    public void hasEnoughMoney() {
        assertTrue(this.player.hasEnoughMoney(this.knife));
        this.bulldozer.setValue(10000);
        assertFalse(this.player.hasEnoughMoney(this.bulldozer));
    }

    @Test
    public void totalWeight() throws Exception {
        this.player.getInventory().empty();
        this.player.getInventory().add(this.knife);
        assertEquals(1, this.player.totalWeight());
    }
}