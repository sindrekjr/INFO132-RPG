import java.util.Random;

/**
 * En hjelpeklasse for statiske metoder
 * Created by Sindre on 23.09.2016.
 */
public class Utilities {

    private static Random rand = new Random();
    
    /**
     * Metode for å rense et negativ til til 0
     * @param value et tall
     * @return 0 hvis value < 0, hvis ikke tallet selv
     */
    public static int cleanNegative(int value) {
        if(value < 0) {
            return 0;
        }
        return value;
    }

    /**
     * Metode for å sjekke at en string ikke er tom.
     * @param string stringen vi vil sjekke
     * @return unspecified hvis stringen er tom, hvis ikke verdien til stringen.
     */
    public static String checkString(String string) {
        if(string.isEmpty()) {
            return "Unspecified";
        } else {
            return string;
        }
    }

    /**
     * Metode for tilfeldig resultat via terningrull.
     * @param numberOfDice  antall terninger som trilles.
     * @param sides         antall sider på hver terning. 
     * @return              summen av alle terningene. 
     */
    public static int rollDice(int numberOfDice, int sides) {
        int sum = 0;
        for(int i = 0; i < numberOfDice; ++i) {
            sum += (rand.nextInt(sides) + 1);
        }
        return sum;
    }
}
