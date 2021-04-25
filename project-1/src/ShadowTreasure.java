import bagel.*;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * An example Bagel game. (The Shadow Treasure Game!)
 */
public class ShadowTreasure extends AbstractGame {

    // Constant variables
    private final static String ENVIRONMENT_FILE = "res/IO/environment.csv";
    private final static String BACKGROUND_IMG_FILE = "res/images/background.png";
    private final static int A_TICK = 10;
    private final static int INDEX_ENTITY_TYPE =0;
    private final static int INDEX_X = 1;
    private final static int INDEX_Y = 2;
    private final static int INDEX_ENERGY = 3;

    // Instantiating background image
    private final Image background = new Image(BACKGROUND_IMG_FILE);

    private boolean sandwichNotEaten = true;
    private boolean zombieNotMet = true;
    private int tickCounter = 0;

    private Player player;
    private Entity zombie;
    private Entity sandwich;

    public ShadowTreasure() throws IOException {
        this.loadEnvironment(ENVIRONMENT_FILE);
        /* Add code to initialize other attributes as needed (added all of them in their
         respective classes) */
    }

    /**
     * Load from input file
     */
    private void loadEnvironment(String filename) {

        String splitBy = ",";

        try (BufferedReader br = new BufferedReader((new FileReader(filename)))) {
            String data;

            // Reads the CSV data line by line
            while ((data = br.readLine()) != null) {
                String[] info = data.split(splitBy);
                double xPoint = Double.parseDouble(info[INDEX_X]);
                double yPoint = Double.parseDouble(info[INDEX_Y]);

                // reading in each entity details
                if(info[INDEX_ENTITY_TYPE].equals("Zombie")){
                    zombie = new Zombie(xPoint, yPoint);
                } else if(info[INDEX_ENTITY_TYPE].equals("Sandwich")){
                    sandwich = new Sandwich(xPoint, yPoint);
                } else {
                    int energyPoint = Integer.parseInt(info[INDEX_ENERGY]);
                    player = new Player(xPoint, yPoint, energyPoint);
                }
             }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Method to check if the player has met an entity
     */
    private void algorithmOne(){
        String entity_type;
        // Checks whether the player met the sandwich entity
        if (sandwich.meetPlayer(player.getPosition()) && sandwichNotEaten) {
            sandwichNotEaten = false;
            entity_type = "sandwich";
            player.updateEnergy(entity_type);
        }

        // Checks whether the player met the zombie entity
        if (zombie.meetPlayer(player.getPosition()) && zombieNotMet) {
            zombieNotMet = false;
            entity_type = "zombie";
            player.updateEnergy(entity_type);
        }
    }

    /**
     * Performs a state update.
     */
    @Override
    public void update(Input input) {

        tickCounter++;

        /* Setting the image on the game screen */
        background.drawFromTopLeft(0, 0);

        /* Initializing the static entities */
        // Zombie image does not disappear (continuation for project 2: fighting the player)
        zombie.displayImage();

        if (sandwichNotEaten) {
            sandwich.displayImage();
        }

        // updating in-game simulation change every 10 frames (one tick = 10 frames)
        if (tickCounter % A_TICK == 0) {

            algorithmOne();

            // Closes the game screen when player meets the zombie
            if(!zombieNotMet){
                Window.close();
            }

            // Initializing the player's image and re-updating it and printing its coordinates and energy level
            player.displayImage();
            player.printPosition();

            // Setting the player's direction in every update and directing the player's movement
            player.setPlayerDirectionTo(zombie.getPosition(), sandwich.getPosition());
            player.playerMovement();


        } else {
            player.displayImage();
        }

        // Displaying the player's energy on the game's screen
        player.displayEnergy();
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) throws IOException {
        ShadowTreasure game = new ShadowTreasure();
        game.run();
    }
}
