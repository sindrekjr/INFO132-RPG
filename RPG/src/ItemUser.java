
/**
 * Interface som tar seg av å identifisere hvem som bruker en gjenstand. 
 * 
 * @author skj006
 */
public interface ItemUser {
    String getName();
    Item findItem(String search);
    boolean drink(Potion potion);
    boolean equip(Weapon weapon);
}