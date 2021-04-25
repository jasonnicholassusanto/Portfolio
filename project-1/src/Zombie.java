import bagel.Image;

public class Zombie extends Entity implements PrintImage{

    // Constant variables
    private final static String ZOMBIE_IMG_FILE = "res/images/zombie.png";

    // Initialising the zombie image
    private final Image zombie = new Image(ZOMBIE_IMG_FILE);

    /**
     * Constructor for the Zombie class
     */
    public Zombie(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    /**
     * Displaying the zombie image
     */
    @Override
    public void displayImage() {
        zombie.draw(xCoordinate, yCoordinate);
    }

    // Code to be implemented for Project 2

}
