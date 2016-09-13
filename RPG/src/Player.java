

/**
 * The Player class is created for a simple RPG program, called "RPG." 
 * 
 * To play, create an instance of the class and set your desired name and 
 * type. Every player begins play with 100 health. Name and type may be 
 * changed at any time, while health is changed by adding or subtracting from 
 * the value. You are dead once left with 0 health. 
 * 
 * If a player dies, an alert is printed onto the console. 
 * 
 * @author skj006 (Sindre S. Kjær)
 * @version 13.09.2016
 */

public class Player // This is a class
{
	/**
	 * Following are the fields for the Player class.
	 */
	private String name;
	private String type;
	private int health;
	
	
	/** 
	 * Constructor to create a new player object. Both name and type is chosen 
	 * immediately, while health is set to 100 (max) by default.
	 * 
	 * @param giveName The name of the player
	 * @param giveType The type of the player
	 */
	public Player(String giveName, String giveType) // This is the constructor
	{
		name = giveName;
		type = giveType;
		health = 100; 
	}
		
	/**
	 * The main method, which creates three unique player objects. Each player's 
	 * details will be printed onto the console. 
	 */
	public static void main(String[] args) // This is the main method
	{
		Player raph = new Player("Raphael", "Warrior"); // Player Raphael is created
		Player laur = new Player("Laura", "Cleric"); // Player Laura is created
		Player mirz = new Player("Mirzendier", "Mage"); // Player Mirzendier is created
		
		// All players' info is printed to console
		raph.print();
		laur.print();
		mirz.print();
	}
	
	
	/**
	 * Method to change player name. Overwrites existing name. 
	 * 
	 * @param newName Sets a new name for player
	 */
	public void setName(String newName) // This is the setName method
	{
		name = newName;
	}
	
	/**
	 * Method to change player type. Overwrites existing type. 
	 * 
	 * @param newType Sets a new type for player
	 */
	public void setType(String newType) // This is the setType method
	{
		type = newType;
	}
	
	/**
	 * This method returns a boolean value that depends on whether or 
	 * not the player is dead.  
	 */
	public boolean isDead() // This is the isDead method
	{
		if(health <= 0) {
			return true;
		}else{
			return false;
		}
	}
		
	/**
	 * Method to increase or decrease player health. The health value 
	 * can't go below 0 or beyond 100. A decrease in health may result 
	 * in the player being declared dead.
	 * 
	 * @param amount Insert a positive or negative integer to increase or decrease health
	 */
	public void changeHealth(int amount) // This is the changeHealth method
	{
		health += amount; // Adds the amount parameter to current health
		if(health <= 0) {
			// If health falls below 0, set to 0
		    health = 0;
			System.out.println("Oh no! " + name + " has died!");
		}else if(health >= 100) {
			// If health goes above 100, set to 100
		    health = 100;
		}	
	}
	
	/**
	 * Method used for printing available player information to console. 
	 * 
	 * Player information is printed as a sheet, starting with a 
	 * separation line for better readability when several players are 
	 * printed at the same time. Beneath the line, player name and 
	 * type is presented as a title. 
	 */
	public void print() // This is the print method
	{
		System.out.println("---");
		System.out.println(name + ", the " + type);
		System.out.println();
		System.out.println("Current Health: " + health + "/100");
		if(isDead()) {
			System.out.println("Status: DEAD");
		}else{
			System.out.println("Status: Still alive");
		}
		System.out.println();
		System.out.println();
	}
	
	
	/**
	 * Method returns the name of the player.
	 */
	public String getName() // This is the getter for the name variable
	{
		return name;
	}
	
	/**
	 * Method returns the type of the player. 
	 */
	public String getType() // This is the getter for the type variable
	{
		return type;
	}
	
	/**
	 * Method returns the health value of the player. 
	 */
	public int getHealth() // This is the getter for the health variable
	{
		return health;
	}
}
