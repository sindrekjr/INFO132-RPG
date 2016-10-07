/**
 * App har en statisk metode main som oppretter tre ulike Player-objekt og skriver ut informasjon om hvert objekt.
 * 
 * @author skj006
 * @date 29.09.2016
 * 
 */

public class App {
    public static void main(String[] args) {
        Player laura = new Player("Laura", "Ranger");
        
        Item chainmail = new Item("Chainmail", 150, 18, "dons", "A chainmail is made up of thousands of interconnected metal rings.");
        Item spear = new Item("Spear", 2, 2, "thrusts", "A regular spear about 1.5 meters in length and made to be thrown or thrusted.");
        Item waterskin = new Item ("Waterskin", 1, 1, "drinks", "A waterskin made of a sheep's bladder, containing about a litre of water.");
        
        laura.buyItem(chainmail);
        laura.buyItem(spear);
        laura.buyItem(waterskin);
        
        laura.print();
    }
}