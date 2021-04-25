import bagel.util.Point;

import java.text.DecimalFormat;

public class Entity implements PrintImage{

    // Constant variables
    private static final int ENTITY_DIST = 50;

    protected double xCoordinate, yCoordinate;

    /**
     * for rounding double number; use this to print the location of the player
     */
    private static DecimalFormat df = new DecimalFormat("0.00");

    public static void printInfo(double x, double y, int e) {
        System.out.println(df.format(x) + "," + df.format(y) + "," + e);
    }

    /**
     * Constructor for Entity class
     */
    public Entity(double xCoordinate, double yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * To print the position of the entity (if needed for the other entities, perhaps in the future)
     */
    public void printPosition(){
        System.out.print(xCoordinate);
        System.out.print(", ");
        System.out.print(yCoordinate);
        System.out.print(", ");
        System.out.println();
    }

    /**
     * To get the position of the entity
     */
    public Point getPosition(){
        return new Point (xCoordinate, yCoordinate);
    }

    /**
     * Setting the entity image
     * */
    public void displayImage(){
    }

    /**
     * Checks the Euclidean distance when the player meets an entity
     */
    public boolean meetPlayer(Point player){

        double playerDistance = new Point(xCoordinate, yCoordinate).distanceTo(player);

        return playerDistance < ENTITY_DIST;
    }
}
