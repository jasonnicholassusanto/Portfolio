import bagel.Image;

public class Sandwich extends Entity {

    // Constant variables
    private final static String SANDWICH_IMG_FILE = "res/images/sandwich.png";

    // Initialising the sandwich image
    private final Image sandwich = new Image(SANDWICH_IMG_FILE);

    /**
     * Constructor for the Sandwich class
     */
    public Sandwich(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    /**
     * Displaying the sandwich image
     */
    @Override
    public void displayImage() {
        sandwich.draw(xCoordinate, yCoordinate);
    }

    // Code to be implemented for Project 2

}
