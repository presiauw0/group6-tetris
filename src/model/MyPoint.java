package model;
/**
 * Represents a 2D Point with x and y coordinates.
 * Point objects are immutable.
 * (Compare to java.awt.Point which are mutable)
 *
 */
public interface MyPoint {
    /**
     * Returns the X coordinate.
     *
     * @return the X coordinate of the Point.
     */
    int x();
    /**
     * Returns the Y coordinate.
     *
     * @return the Y coordinate of the Point.
     */
    int y();
    /**
     * Creates a new point transformed by x and y.
     *
     * @param theX the X factor to transform by.
     * @param theY the Y factor to transform by.
     */
    void transform(int theX, int theY);
    /**
     * Creates a new point transformed by another Point.
     *
     * @param thePoint the Point to transform with.
     */
    void transform(MyPoint thePoint);

}
