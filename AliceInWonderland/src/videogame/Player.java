
package videogame;

import java.awt.Graphics;
/**
 * Player Class
 * 
 * Defines characteristics and actions of player, including collisions
 * 
 * @author Pablo Andrade A01193740
 * @date 01/18/2018
 * @version 1.0
 */
public class Player extends Item{

    private int direction;
    private Game game;
    private Animation animationUp; //to store the animation for going up
    private Animation animationLeft; //to store the animation for going left
    private Animation animationDown; //to store the animation for going down
    private Animation animationRight; //to store the animation for going Right
    
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        
        this.animationUp = new Animation(Assets.playerUp, 150);
        this.animationLeft = new Animation(Assets.playerLeft, 150);
        this.animationDown = new Animation(Assets.playerDown, 150);
        this.animationRight = new Animation(Assets.playerRight, 150);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void tick() {
        //animation
        if(game.hasSuperPower() && (game.getKeyManager().upRight || 
                game.getKeyManager().upLeft || game.getKeyManager().downLeft || 
                game.getKeyManager().downRight)){
            this.animationRight.tick();
        }
        else if(!game.hasSuperPower() && (game.getKeyManager().upRight || 
                game.getKeyManager().upLeft || game.getKeyManager().downLeft || 
                game.getKeyManager().downRight)){
            this.animationLeft.tick();
        }
        // moving player depending on flags
        if (game.getKeyManager().upLeft) {
           setX(getX() - 10);
           setY(getY() - 10);
        }
        if (game.getKeyManager().upRight) {
           setX(getX() + 10);
           setY(getY() - 10);
        }
        if (game.getKeyManager().downLeft) {
           setX(getX() - 10);
           setY(getY() + 10);
        }
        if (game.getKeyManager().downRight) {
           setX(getX() + 10);
           setY(getY() + 10);
        }
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setY(-20);
        }
    }

    @Override
    public void render(Graphics g) {
        if(game.hasSuperPower()){
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else{
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        g.drawRect((int)getBounds().getX(), (int)getBounds().getY(), (int)getBounds().getWidth(), (int)getBounds().getHeight());
    }
}
