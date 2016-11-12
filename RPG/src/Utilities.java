/**
 * En hjelpeklasse for statiske metoder
 * Created by Sindre on 23.09.2016.
 */
public abstract class Utilities {
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
        if(string == null || string.isEmpty()) {
            return "Unspecified";
        } else {
            return string;
        }
    }
}
