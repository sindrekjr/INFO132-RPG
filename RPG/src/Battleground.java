import java.util.ArrayList;
import java.util.Random;

/**
 * En klasse for slåssing i RPG-spill. 
 * 
 * @author skj006
 */
public class Battleground {
    
    private InputReader reader;
    private Random rand;
    private ArrayList<Monster> monsterList;
    private Player player;
    private Monster monster;
    private int roundCount;
    
    /**
     * Konstruktør uten parameter. Initialiserer kun viktigste felter. 
     */
    public Battleground() {
        reader = new InputReader();
        rand = new Random();
        monsterList = new ArrayList<>();
    }
    
    /**
     * Konstruktør som tar imot en spiller. Fyller også en liste med monstre og skriver ut velkomst.
     * @param Player spilleren som entrer arenaen
     */
    public Battleground(Player player) {
        reader = new InputReader();
        rand = new Random();
        monsterList = new ArrayList<>();
        fillMonsterList();
        
        setPlayer(player);
        printWelcome();
    }

    /**
     * Fyller en liste med monstre som kan brukes i Battleground. 
     */
    public void fillMonsterList() {
        monsterList.add(new Monster("Bugbear", 80, 40, "A humanoid creature with flared black and brown fur."));
        monsterList.add(new Monster("Dire Cat", 10, 1, "A house cat with abnormally fierce whiskers."));
        monsterList.add(new Monster("Dragon", 200, 1000, "A reptile-like creature with large wings and the ability to breathe fire."));
        monsterList.add(new Monster("Erroneous Algorithm", 30, 5, "Bad request."));
        monsterList.add(new Monster("Giant Spider", 90, 6, "A monstrous spider as large as a wolf."));
        monsterList.add(new Monster("Goblin", 50, 12, "A small humanoid with a wide head and clumsy demeanour."));
        monsterList.add(new Monster("Lamia", 150, 500, "A monstrously large female humanoid with a body like a horse or a snake."));
        monsterList.add(new Monster("Ogre", 140, 50, "An angry humanoid the size of four people."));
        monsterList.add(new Monster("Orc", 80, 20, "An ugly and stinking humanoid made for war."));
        monsterList.add(new Monster("Sandworm", 120, 100, "A gigantic worm normally seen only in the desert."));
        monsterList.add(new Monster("Werewolf", 100, 100, "A creature that loses its control whenever its lycantrophy takes effect."));
        monsterList.add(new Monster("Zombie", 30, 1, "A dead person reanimated by a necromancer."));
    }

    /**
     * Starter kamp med et tilfeldig monster. 
     */
    public void startBattle() {
        roundCount = 1;
        boolean endBattle = false;
        setMonster(fetchMonster());
        
        printEncounter(monster);
        while(!endBattle) {
            System.out.print("Round " + roundCount + ": ");
            GivenCommand input = reader.getInput();
            endBattle = processBattleCommand(input);
        }
    }
    
    /**
     * Utfører kommando gitt av spilleren.
     * @param command   kommando hentet av reader
     * @return boolean  forteller om kampen etterpå er over eller ikke
     */
    public boolean processBattleCommand(GivenCommand command) {
        boolean endBattle = false;
        switch(command.getCommandWord()) {
            case ATTACK:
                endBattle = battleRound();
                break;
            case RUN:
                player.setGold(player.getGold() - 50);
                System.out.println();
                System.out.println(player.getName() + " has decided they've had enough, and runs away. 50 gold pieces is debited from their account.");
                endBattle = true;
                break;
            case UNKNOWN:
                System.err.println("No such command.");
                break;
            default:
                System.out.println("You may not do that now.");
                break;
        }
        return endBattle;
    }
    
    /**
     * Kjører en kamprunde hvor spiller angriper først, og monster angriper etterpå. 
     * @return boolean forteller om kampen er over (hvis spiller eller monster dør)
     */
    public boolean battleRound() {
        boolean endBattle = false;
        if(endBattle = player.attack(monster)) {
            player.setGold(player.getGold() + monster.getGold());
            System.out.println();
            System.out.println(player.getName() + " has defeated the " + monster.getName().toLowerCase() + " and gains " + monster.getGold() + " gold pieces.");
        } else if(endBattle = monster.attack(player)) {
            System.out.println();
            System.out.println("Oh no! " + player.getName() + " has been killed by the " + monster.getName().toLowerCase() + ". The battle is lost.");
        }
        roundCount++;
        return endBattle;
    }

    /**
     * Aksessmetode for monsterList.
     * @return ArrayList liste over eksisterende monstre
     */
    public ArrayList<Monster> getMonsterList() {
        return this.monsterList;
    }
    
    public void setMonsterList(ArrayList<Monster> monsterList) {
        this.monsterList = monsterList;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Monster getMonster() {
        return monster;
    }
    
    public void setMonster(Monster monster) {
        this.monster = monster;
    }
        
    /**
     * Private metode som velger tilfeldig monster.
     * @return Monster
     */
    private Monster fetchMonster() {
        int index = rand.nextInt(monsterList.size());
        return monsterList.get(index);
    }
    
    /**
     * Printer en velkomst til brukeren.
     */
    private void printWelcome() {
        System.out.println(player.getName() + " enters the battleground. Valid commands are ATTACK and RUN.");
        System.out.println();
    }
    
    /**
     * Privat metode som forteller brukeren at et monster har dukket opp.
     * @param monster monsteret som dukker opp til kamp
     */
    private void printEncounter(Monster monster) {
        System.out.println("A wild " + monster.getName().toLowerCase() + " appears, and the battle is joined!");
    }
}
