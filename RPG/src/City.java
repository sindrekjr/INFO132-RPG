import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * En klasse for byer i et enkelt RPG-spill
 * 
 * @author skj006
 */
public class City implements Area {
    private HashMap<String, Area> areas = new HashMap<>();
    private String name; 
    private Player player; 
    private InputReader reader;
    private Output output;
    
    /**
     * Konstruktør for City.
     * @param name
     * @param player
     * @param reader
     */
    public City(String name, ArrayList<Area> areas, InputReader reader) {
        setName(name);
        this.reader = reader;
        this.output = new ConsoleWriter();
        
        mapAreas(areas);
    }
    
    /**
     * Metode for å entre byen.
     * @param player spilleren som kommer inn
     */
    public void enter(Player player) {        
        setPlayer(player);
        printWelcome();
        
        boolean finished = false;
        while(!finished) {
            finished = processCommand(reader.getInput());
        }
    }
    
    /**
     * Metode for å prosessere mottatt kommando og gjennomføre den.
     * @param command kommandoobjektet
     * @return boolean true hvis man er ferdig i denne klassen
     */
    private boolean processCommand(Command command) {
        boolean finished = false; 
        switch(command.getCommandWord()) {
            case ENTER:
                enterArea(command.getSecondary());
                break;
            case HELP:
                printValidCommands();
                break;
            case EXIT:
                finished = leave();
                break;
            case UNKNOWN:
                output.write("\nNo such command.\n");
                break;
            default:
                output.write("\nYou may not do that now. Type \"help\" to see all valid commands.\n");
                break;
        }
        return finished;
    }

    /**
     * Metode for å forlate området. 
     * @return true
     */
    private boolean leave() {
        System.out.println(player.getName() + " has left " + this.getName() + ".");
        setPlayer(null);
        return true; 
    }

    /**
     * Metode for å skrive velkomstmelding.
     */
    private void printWelcome() {
        System.out.println("Welcome to " + this.getName() + "! From here you can enter the battleground or the store.");
        System.out.println();
    }
    
    /**
     * Metode for å printe gyldige kommandoer i området.
     */
    private void printValidCommands() {
        HashSet<String> valid = new HashSet<>();
        valid.add("enter"); valid.add("help"); valid.add("exit");
        output.write("Valid Commands:\n");
        for(String command : reader.getCommands().keySet()) {
            if(valid.contains(command)) {
                output.write(command.toUpperCase() + " -- " + reader.getCommands().get(command).getDescription() + "\n");
            }
        }
    }
    
    /**
     * Metode for å entre området innenfor City-objektet. 
     * @param area området man ønsker å besøke. 
     */
    private void enterArea(String area) {
        if(area.isEmpty() || !(areas.containsKey(area))) {
            System.out.println("Please specify with a valid area.");
        } else {
            areas.get(area).enter(this.player);
        }
    }

    /**
     * Metode som sorterer områder i et hashmap. 
     * @param areas områdene som skal mappes
     */
    private void mapAreas(ArrayList<Area> areas) {
        for(Area area : areas) {
            this.areas.put(area.toString(), area);
        }
    }
    
    /**
     * @return String
     */
    public String toString() {
        return "city";
    }

    /**
     * Aksessmetode for navn.
     * @return String
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Mutasjonsmetode for navn.
     * @param name
     */
    public void setName(String name) {
        this.name = Utilities.checkString(name);
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
}