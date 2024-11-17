package model;
/**
 * Interface representing a 2D point with integer coordinates.
 * Provides methods to retrieve the coordinates of the point
 * and transform the point's position.
 *
 * @author Abdulrahman Hassan
 * @version Autumn 2024
 *
 */
public interface MyPoint {

    /**
     * Returns the x-coordinate of the point.
     *
     * @return the x-coordinate of the point.
     */
    int x();

    /**
     * Returns the y-coordinate of the point.
     *
     * @return the y-coordinate of the point.
     */
    int y();

    /**
     * Transforms the point to a new position specified by the given x and y coordinates.
     *
     * @param theX the new x-coordinate of the point.
     * @param theY the new y-coordinate of the point.
     * @return the updated Point object, with the new coordinates.
     */
    Point transform(int theX, int theY);
    /**
     * Transforms the point to the position of another Point object.
     * This method updates the current point's coordinates to match those of the given Point.
     *
     * @param thePoint the Point object whose coordinates will be copied.
     * @return the updated Point object, with the coordinates from the provided Point.
     */
    Point transform(Point thePoint);
}
