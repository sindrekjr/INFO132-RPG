import java.util.ArrayList;
import java.util.HashSet;

/**
 * En klasse for butikker i et enkelt RPG-spill.
 * 
 * @author skj006
 */
public class Store implements Area {
    private Inventory stock = new Inventory();
    private String name; 
    private Player player;
    private InputReader reader; 
    private Output output;
    
    /**
     * Konstruktør for butikk. Tar i mot en liste med gjenstander og sorterer dem. 
     * @param items liste over gjenstander som butikken har
     * @param player spilleren som besøker butikken
     * @param reader en leser som lar spilleren skrive kommandoer til programmet
     */
    public Store(String name, ArrayList<Item> items, InputReader reader) {
        sortItems(items);
        
        setName(name);
        this.reader = reader;
        this.output = new ConsoleWriter();
    }

    /**
     * MMetode for å entre butikken.
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
            case BUY:
                if(this.sellItem(this.findItem(command.getSecondary()))) {
                    output.write("\n\"Here you go! Pleasure doing business.\"\n");
                }
                break;
            case SELL:
                if(this.buyItem(player.findItem(command.getSecondary()))) {
                    output.write("\n\"Thank you, friend. A fair transaction.\"\n");
                }
                break;
            case SEARCH:
                if(hasItem(this.findItem(command.getSecondary()))) {
                    output.write("\n\"Oh aye, we have that. Here, have a look...\"\n");
                    showItem(this.findItem(command.getSecondary()));
                } else if(command.getSecondary().equalsIgnoreCase("all")) {
                    showAllItems();
                } else {
                    output.write("\n\"Hmm, no. Apologies, friend, buy we don't seem to have that in stock.\"\n");
                }
                break;
            case EXIT:
                finished = leave();
                break;
            case HELP:
                printValidCommands();
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
     * Metode for når spilleren ønsker å forlate butikken.
     * @return boolean true
     */
    private boolean leave() {
        output.write("\n" + player.getName() + " has left " + this.getName() + ".\n");
        setPlayer(null);
        return true; 
    }
    
    /**
     * Skriver ut en velkomst til spillere når de entrer butikken. 
     */
    private void printWelcome() {
        output.write("\n\"Welcome to " + this.getName() + "! How can I help you today?\"\n");
    }
    
    /**
     * Skriver ut gyldige kommandoer som kan brukes i butikken.
     */
    private void printValidCommands() {
        HashSet<String> valid = new HashSet<>();
        valid.add("buy"); valid.add("sell"); valid.add("search"); valid.add("help"); valid.add("exit");
        output.write("Valid Commands:\n");
        for(String command : reader.getCommands().keySet()) {
            if(valid.contains(command)) {
                output.write(command.toUpperCase() + " -- " + reader.getCommands().get(command).getDescription() + "\n");
            }
        }
    }

    /**
     * Metode for å kjøpe gjenstander fra spilleren.
     * @param item gjenstanden som butikken kjøper
     * @return boolean true hvis kjøpet går fint. 
     */
    private boolean buyItem(Item item) {
        boolean bought = false;
        if(bought = this.player.sellItem(item)) {
            this.stock.add(item);
            output.write("\n" + player.getName() + " sells " + item.getName() + " for " + item.getValue() + " gold pieces to " + this.getName() + ".\n");
        }
        return bought;
    }
    
    /**
     * Metode for å selge gjenstander til spilleren.
     * @param item gjenstanden som butikken selger
     * @return boolean true hvis salget går fint
     */
    private boolean sellItem(Item item) {
        boolean sold = false; 
        if(sold = (this.hasItem(item))) {
            if(sold = (player.buyItem(item))) {
                this.stock.remove(item);
                output.write("\n" + player.getName() + " buys " + item.getName() + " from " + this.getName() + " for " + item.getValue() + " gold pieces.\n");
            }
        }
        return sold; 
    }
    
    /**
     * Metode for å finne en gjenstand man ønsker å kjøpe eller se nærmere på. 
     * @param item navnet på gjenstanden
     * @return Item selve gjenstanden om den finnes
     */
    private Item findItem(String search) {
        return stock.get(search);
    }
    
    /**
     * Metode for å vise grunnleggende informasjon om en gjenstand.
     * @param item
     */
    private void showItem(Item item) {
        output.write("\n" + item.toString() + "\n");
    }
    
    /**
     * Metode som skrive ut alle gjenstandene i butikken.
     */
    private void showAllItems() {
        if(stock.isEmpty()) {
            output.write("\"Apologies, but we're all sold out.\"\n");
        } else {
            output.write(stock.toString() + "\n");
        }
    }
    
    /**
     * Sjekker om butikken har et bestemt item.
     * @param item navnet på gjenstanden man leter etter
     * @return true hvis gjenstanden er tilgjengelig
     */
    private boolean hasItem(Item item) {
        return stock.contains(item);
    }
    
    /**
     * Metode som sorterer items mottatt i butikken. 
     * @param items
     */
    private void sortItems(ArrayList<Item> items) {
        for(Item item : items) {
            stock.add(item);
        }
    }

    /**
     * @return String
     */
    public String toString() {
        return "store";
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