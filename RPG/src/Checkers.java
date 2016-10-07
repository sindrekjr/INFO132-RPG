
/**
 * Klasse som inneholder metoder brukt av andre klasser.
 * 
 * @author skj006
 * @version 04.01.2016
 */
public class Checkers
{
    /**
     * Sjekker at gitt streng ikke er tom.
     * 
     * @param String    gitt streng.
     * @return String   returnerer streng.
     */
    public static String checkString(String input) {
        if(input.equals("")) {
            return "Unspecified";
        }
        
        return input;
    }
    
    /**
     * Sjekker at gitt heltall ikke er positivt.
     * 
     * @param int       gitt heltall.
     * @return int      returnerer heltall.
     */
    public static int checkIfPositive(int number) {
        if(number < 0) {
            return 0;
        }
        
        return number;
    }
}
