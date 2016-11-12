import java.util.HashSet;

/**
 * Abstract class Battle - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Battle {
    private static boolean victory;
    private static Player player;
    private static Character opponent;
    private static InputReader reader = new InputReader(); 
    private static Output output = new ConsoleWriter();
    
    public static boolean fight(Player char1, Character char2) {
        player = char1;
        opponent = char2; 
        
        boolean endBattle = false; 
        while(!endBattle) {
            endBattle = processCommand(reader.getInput());
        }
        return victory;
    }
    
    private static boolean processCommand(Command command) {
        boolean endBattle = false;
        switch(command.getCommandWord()) {
            case ATTACK:
                endBattle = redRum();
                break;
            case DRINK:
                if(player.getInventory().getAllPotions().contains(player.findItem(command.getSecondary()))) {
                    player.useItem(player.findItem(command.getSecondary()));
                } else {
                    output.write("\nYou can't drink that.\n");
                }
                break;
            case EQUIP:
                if(player.getInventory().getAllWeapons().contains(player.findItem(command.getSecondary()))) {
                    player.useItem(player.findItem(command.getSecondary()));
                } else {
                    output.write("\nYou can't wield that.\n");
                }
                break;
            case UNEQUIP:
                player.setEquippedWeapon(null);
                break;
            case USE: 
                player.useItem(player.findItem(command.getSecondary()));
                break;
            case HELP: 
                printValidCommands();
                break;
            case FIGHT:
                output.write("No no, you'll have to defeat the current monster first. You don't want to fight two monsters at the same time!\n");
                break;
            case UNKNOWN:
                output.write("\nNo such command.\n");
                break;
            default:
                output.write("\nYou may not do that now. Type \"help\" to see all valid commands.\n");
                break;
        }
        return endBattle;
    }
    
    private static void printValidCommands() {
        HashSet<String> valid = new HashSet<>();
        valid.add("attack"); valid.add("equip"); valid.add("drink"); valid.add("use"); valid.add("run"); valid.add("help"); valid.add("exit");
        output.write("Valid Commands:\n");
        for(String command : reader.getCommands().keySet()) {
            if(valid.contains(command)) {
                output.write(command.toUpperCase() + " -- " + reader.getCommands().get(command).getDescription() + "\n");
            }
        }
    }
    
    private static boolean redRum() {
        boolean endBattle = false; 
        if(endBattle = player.attack(opponent)) {
            playerWins();
        } else if(endBattle = opponent.attack(player)) {
            playerLoses();
        }
        return endBattle;
    }
    
    private static void playerWins() {
        player.addGold(opponent.getGold());
        output.write(victoryMessage());
        victory = true;
    }
    
    private static void playerLoses() {
        output.write(defeatMessage());
        output.write("Our arena cleric have brought you back to full health.\n");
        player.changeHealth(100);
        victory = false; 
    }
    
    private static void playerRuns() {
        output.write("\nCoward. You lose 50 gold pieces for fleeing.\n");
        player.changeGold(-50);
        victory = false; 
    }
    
    private static String victoryMessage() {
        return "\nCongratulations! You beat " + opponent.getName() + "!\n"; 
    }
    
    private static String defeatMessage() {
        return "\nYou lost...\n";
    }
}
