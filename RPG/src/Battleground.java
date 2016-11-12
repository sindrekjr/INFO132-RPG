import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Battleground er et område hvor man kan slåss med monstre og bruke gjenstander.
 * 
 * @author skj006
 */
public class Battleground implements Area {
    private ArrayList<Monster> graveyard = new ArrayList<Monster>(); 
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private int monstersBeaten;
    private String name; 
    private Player player; 
    private InputReader reader; 
    private Output output;
        
    /**
     * Konstruktør for Battleground. Den tar en liste med monstre, en player og inputreader
     * @param name navnet på battleground
     * @param monsters En samling med monstre
     * @param reader En reader som lar brukeren skrive inn kommandoer til programmet
     */
    public Battleground(String name, ArrayList<Monster> monsters, InputReader reader) {
        sortMonsters(monsters);
        setName(name);
        this.reader = reader;
        this.output = new ConsoleWriter();
    }

    /**
     * Metode for å gå inn i battleground. 
     * @param player spilleren som entrer
     */
    public void enter(Player player) {
        this.player = player;
        printStats();
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
            case FIGHT:
                if(monsters.size() == 0) {
                    output.write("\n\"All the monsters appear to be defeated already.\"");
                    output.write("\nType \"continue\" if you wish to fetch another batch of monsters.\n");
                } else {
                    finished = fight(command.getSecondary());
                }
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
            case CONTINUE:
                reset();
                break;
            case EXIT:
                finished = leave();
                break;
            case HELP:
                printValidCommands();
                break;
            case ATTACK:
                output.write("You'll have to accept a battle by typing \"fight\" first.\n");
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
     * Metode for å starte kamp.
     * @param playerChoice streng som viser hva spillere skrev etter "fight"
     * @return boolean true hvis spilleren har tapt og må forlate battleground
     */
    private boolean fight(String playerChoice) {
        Monster monster = fetchMonster(playerChoice);
        boolean lost = false; 
        freePotion();
        output.write("\nGet ready to meet the mighty " + monster.getName() + "!\n");
        if(Battle.fight(player, monster)) {
            graveyard.add(monster);
            monsters.remove(monster);
            output.write("\n" + ++monstersBeaten + " monsters defeated so far.\n");
        } else {
            return leave();
        }
        checkMonsters();
        return lost; 
    }

    /**
     * Metode for å forlate området. 
     * @return true
     */
    private boolean leave() {
        output.write("\n" + player.getName() + " has left the battleground.\n");
        this.player = null;
        return true; 
    }
    
    /**
     * Metode for å resette monsterlisten man henter fra. 
     */
    private void reset() {
        if(monsters.isEmpty()) {
            for(Monster monster : graveyard) {
                monster.changeHealth(1000);
            }
            sortMonsters(graveyard);
            graveyard.clear();
            checkMonsters();
        } else {
            output.write("\nYou'll have to defeat the remaining monsters before you can start over.\n");
        }
    }
    
    /**
     * Metode som sjekker hvor mange monstre som gjenstår, og skriver ut beskjeder avhengig av dette. 
     */
    private void checkMonsters() {
        if(monsters.size() > 0) {
            output.write("\nThere are " + monsters.size() + " monsters left to kill. Type \"fight\" to continue to the next one.\n");
            output.write("If you're looking for a challenge, type \"fight stronger\" to meet the strongest monster available.\n");
        } else if(monsters.isEmpty()) {
            output.write("\nCONGRATULATIONS! You have beaten every single monster in the game. The bards will sing of this triumph for decades to come!\n");
            printStats();
            output.write("\nIf you wish to fight again, type \"continue\".\n");
        }
    }
    
    /**
     * Metode for å skrive velkomstmelding.
     */
    private void printWelcome() {
        output.write("\nWelcome to the battleground, a dangerous place packed with the stench of death and despair. Are you ready for what lies ahead?");
        output.write("\nIf you wish to battle, type \"fight\", and a random monster will be chosen for you.\n");
    }
    
    /**
     * Metode for å skrive ut spillerstatistikk. 
     */
    private void printStats() {
        output.write("\n-------- Player Stats ---------\n");
        output.write(this.player.toString() + "\n");
    }
        
    /**
     * Metode for å printe gyldige kommandoer i området.
     */
    private void printValidCommands() {
        HashSet<String> valid = new HashSet<>();
        valid.add("fight"); valid.add("equip"); valid.add("drink"); valid.add("use"); valid.add("help"); valid.add("exit");
        output.write("Valid Commands:\n");
        for(String command : reader.getCommands().keySet()) {
            if(valid.contains(command)) {
                output.write(command.toUpperCase() + " -- " + reader.getCommands().get(command).getDescription() + "\n");
            }
        }
    }

    /**
     * Metode som gir spiller gratis potion dersom de har mistet helse. 
     */
    private void freePotion() {
        if(player.getHealth() != 100) {
            output.write("\nLooks like you've taken some damage. Here, drink this free potion before your next battle.\n");
            player.getInventory().add(new Potion("Potion", "A health potion", "drinks", 1, 1, 50));
            player.useItem(player.findItem("potion"));
        }
    }
    
    /**
     * Metode som returnerer tilfeldig monster.
     * @param playerChoice relevant dersom spilleren velger å spille mot farligste monster
     * @return Monster
     */
    private Monster fetchMonster(String playerChoice) {
        if(monsters.size() > 0) {
            if(playerChoice.equalsIgnoreCase("harder") || playerChoice.equalsIgnoreCase("better") || playerChoice.equalsIgnoreCase("faster") || playerChoice.equalsIgnoreCase("stronger")) {
                return monsters.get(0);
            } else {
                Random random = new Random();
                return monsters.get(random.nextInt(monsters.size()));
            }
        }
        return null;
    }

    /**
     * Tar imot en liste med monstre og oppdaterer tilstand med den. Deretter sorteres monstrene
     * i listen etter helse og maks-skade.
     * @param monsterList liste med monstre
     */
    private void sortMonsters(ArrayList<Monster> monsterList) {
        for(Monster monster : monsterList) {
            monsters.add(monster);
        }
        Collections.sort(monsters, new Comparator<Monster>() {
            public int compare(Monster mon1, Monster mon2) {
                return Integer.compare((mon2.getMaxDamage() + mon2.getHealth()), (mon1.getMaxDamage() + mon1.getHealth()));
            }
        });
    }
    
    /**
     * @return String
     */
    public String toString() {
        return "battleground";
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