
package videogame;
/**
 * Enemy Class
 * 
 * Defines characteristics and actions of enemy, including collisions
 * 
 * @author Pablo Andrade A01193740
 * @date 01/18/2018
 * @version 1.0
 */
import java.awt.Graphics;

/**
 *
 * @author antoniomejorado
 */
public class Enemy extends Item{

    private Game game;
    private int velocity;
    private int direction;
    private Animation animationUp; //to store the animation for going up
    private Animation animationLeft; //to store the animation for going left
    private Animation animationDown; //to store the animation for going down
    private Animation animationRight; //to store the animation for going Right
    
    public Enemy(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        velocity = (int)(Math.random() * 2 + 3);
        direction = (int)(Math.random() * 10);
        
        this.animationUp = new Animation(Assets.enemyUp, 75);
        this.animationLeft = new Animation(Assets.enemyLeft, 75);
        this.animationDown = new Animation(Assets.enemyDown, 75);
        this.animationRight = new Animation(Assets.enemyRight, 75);
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public void invertDirection() {
        if (getDirection() % 2 > 0){ // si es numero impar
            setDirection(0);
        }
        else{
            setDirection(1);
        }
    }
    
    public void randomize(){
        //reset x position
        setX((int) ((int) (Math.random() * (game.getWidth() * 2) + game.getWidth())));
        //reset Y position
        setY((int) (Math.random() * (game.getHeight() - 75)));
        //reset velocity to random value from 3 to 5
        setVelocity((int)(Math.random() * 2 + 3));
        //set direction randomly up or down diagonally
        setDirection((int)(Math.random() * 0));
    }
    @Override
    public void tick() {
       setX(getX() - getVelocity());
       if(getDirection() % 2 > 0){ //si es impar avanza abajo
           setY(getY() + getVelocity());
       }
       else{
           setY(getY() - getVelocity()); //si es par avanza arriba
       }
       //animation
       this.animationLeft.tick();
       /*
        if (game.getKeyManager().right) {
            this.animationRight.tick();
        } 
        else if (game.getKeyManager().up) {
            this.animationUp.tick();
        }
        else if (game.getKeyManager().left) {
            this.animationLeft.tick();
        } 
        else if (game.getKeyManager().down) {
            this.animationDown.tick();
        }*/
    }

    @Override
    public void render(Graphics g) {
        /*
        if (game.getKeyManager().right) {
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        } else if (game.getKeyManager().up) {
            g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        } else if (game.getKeyManager().left) {
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        } else if (game.getKeyManager().down) {
            g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        */
        g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        g.drawRect((int) getBounds().getX(), (int) getBounds().getY(), (int) getBounds().getWidth(), (int) getBounds().getHeight());
    }
}
