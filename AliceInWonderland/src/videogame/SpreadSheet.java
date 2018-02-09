package videogame;


import java.awt.image.BufferedImage;

/**
 * Spreadsheet Class
 * 
 * Defines spreadsheet for use in sprite animation
 * 
 * @author Pablo Andrade A01193740
 * @date 01/30/2018
 * @version 1.0
 */
public class SpreadSheet {
    private BufferedImage sheet; //to store the spritesheet
    
    /**
     * Create a new spritesheet
     * @param sheet the <code> image </code> with the sprites
     */
    public SpreadSheet(BufferedImage sheet){
    this.sheet = sheet;
    }
    
    /**
     * Crop a sprite from the spritesheet
     * @param x an int value with the x coordinate   
     * @param y an int value with the y coordinate
     * @param width an int value with the width of the sprite
     * @param height an int value with the height of the sprite
     * @return 
     */
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
    
}
