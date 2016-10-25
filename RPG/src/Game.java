/**
 * Main-klasse for spillet. Her oppretter vi en spiller og en del gjenstander.
 * Spilleren prøver å kjøpe gjenstandene, og til slutt printer vi info om spilleren.
 * 
 * @author skj006
 */
public class Game {
    /**
     * Hovedmetoden lager en spiller, kjøper utstyr, og initialiserer Battleground. Resten skjer der. 
     */
    public static void main(String[] args) {
        Player laura = new Player("Laura", "Ranger");
        Item chainmail = new Item("Chainmail", "A suit made up of thousands of interlocking metal rings.", "dons", 200, 18);
        Item spear = new Item("Spear", "A regular spear about 1.5 meters in length, made for thrusting or throwing.", "throws", 2, 2);
        Item longsword = new Item("Longsword", "A double-bladed sword with a cruciform hilt, made to be handled with either one or two hands.", "slashes", 15, 4);
        Item potion = new Item("Potion", "A health potion.", "drinks", 25, 1, true);
        Item bomb = new Item("Bomb", "A bomb made of mysterious alchemical ingredients.", "throws", 20, 1, true);
        
        laura.buyItem(chainmail);
        laura.buyItem(spear);
        laura.buyItem(longsword);
        laura.buyItem(potion);
        laura.buyItem(bomb);

        Battleground battle = new Battleground(laura);
        battle.startBattle();
    }
    
    
}