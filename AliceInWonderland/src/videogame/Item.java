/**
 * Item Class
 * 
 * Manages coordinates and boundaries for items
 * 
 * @author Pablo Andrade A01193740
 * @date 02/02/2018
 * @version 1.0
 */
package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Item {
    protected int width;
    protected int height;
    protected int x;        // to store x position
    protected int y;        // to store y position
    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Get x value
     * @return x 
     */
    public int getX() {
        return x;
    }

    /**
     * Get y value
     * @return y 
     */
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    /**
     * Set x value
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y value
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Checks if two item objects collide
     * @param obj as input
     * @return boolean true if intersects
     */
    public boolean intersects(Object obj){
        return obj instanceof Item && 
        this.getBounds().intersects(((Item)obj).getBounds());
    }
    
    /**
     * Checks if there's collision in lower boundary of item
     * @param obj as input
     * @return boolean true if intersects
     */
    public boolean intersectsDown(Object obj) { //checa intersección con área inferior 
        return obj instanceof Item
                && this.getLowerBounds().intersects(((Item) obj).getBounds());
    }
    
    public Rectangle getBounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    /**
     * Makes rectangle to determine which area is important for collsion
     * @return Rectangle with tiny dimensions, below the item's rectangle
     */
    public Rectangle getLowerBounds() {
        return new Rectangle(getX() + getWidth() / 4, getY() + getHeight(), 
                getWidth() / 2, getHeight() / 75); //little thin rectangle on lower bound
    }
    
    /**
     * To update positions of the item for every tick
     */
    public abstract void tick();
    
    /**
     * To paint the item
     * @param g <b>Graphics</b> object to paint the item
     */
    public abstract void render(Graphics g);
}
