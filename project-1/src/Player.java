import bagel.DrawOptions;
import bagel.Font;
import bagel.Image;
import bagel.util.Colour;
import bagel.util.Point;

public class Player extends Entity implements PrintImage{

    // Constant variables
    private static final double STEP_SIZE = 10;
    private static final int ENERGY_DISPLAY_X = 20;
    private static final int ENERGY_DISPLAY_Y = 760;
    private static final int ENERGY_FONT_SIZE = 20;
    private static final int ENERGY_THRESHOLD = 3;
    private static final int SANDWICH_ENERGY_UPDATE = 5;
    private static final int ZOMBIE_ENERGY_UPDATE = 3;
    private final static String PLAYER_IMG_FILE = "res/images/player.png";
    private final static String ENERGY_DISPLAY_FONT = "res/font/DejaVuSans-Bold.ttf";

    private double playerDirectionX, playerDirectionY;
    private int energy;

    // Initializing the player image
    private final Image player = new Image(PLAYER_IMG_FILE);


    /**
     * Constructor for the Player class
     */
    public Player(double xCoordinate, double yCoordinate, int energy) {
        super(xCoordinate, yCoordinate);
        this.energy = energy;
    }

    /**
     * Displaying the player image
     */
    @Override
    public void displayImage() {
        player.draw(xCoordinate, yCoordinate);
    }

    /**
     * To print the player position and energy level
     */
    @Override
    public void printPosition() {
        printInfo(xCoordinate, yCoordinate, energy);
    }

    /**
     * Updating the player's energy after encountering an entity in-game
     */
    public void updateEnergy(String entity_type){
        if (entity_type.equals("sandwich")){
            energy += SANDWICH_ENERGY_UPDATE;
        } else if (entity_type.equals("zombie")){
            energy -= ZOMBIE_ENERGY_UPDATE;
        }
    }

    /**
     * Displaying the energy level on the game screen
     */
    public void displayEnergy(){
        Font font = new Font(ENERGY_DISPLAY_FONT, ENERGY_FONT_SIZE);

        DrawOptions fontColor = new DrawOptions();
        fontColor.setBlendColour(Colour.BLACK);

        font.drawString("energy: " + energy, ENERGY_DISPLAY_X, ENERGY_DISPLAY_Y, fontColor);
    }

    /**
     * Initializing the player's direction to approach an entity
     */
    public void setPlayerDirectionTo(Point zombie, Point sandwich){
        double zombieDistance = new Point(xCoordinate, yCoordinate).distanceTo(zombie);
        double sandwichDistance = new Point(xCoordinate, yCoordinate).distanceTo(sandwich);

        // Player approaches an entity depending on its energy level
        if (energy>=ENERGY_THRESHOLD){
            // Player decides to approach zombie
            playerDirectionX = (zombie.x - xCoordinate)/zombieDistance;
            playerDirectionY = (zombie.y - yCoordinate)/zombieDistance;
        } else {
            // Player decides to approach sandwich
            playerDirectionX = (sandwich.x - xCoordinate)/sandwichDistance;
            playerDirectionY = (sandwich.y - yCoordinate)/sandwichDistance;
        }
    }

    /**
     * Enables player movement in game
     */
    public void playerMovement(){
        xCoordinate += STEP_SIZE * playerDirectionX;
        yCoordinate += STEP_SIZE * playerDirectionY;
    }
}
