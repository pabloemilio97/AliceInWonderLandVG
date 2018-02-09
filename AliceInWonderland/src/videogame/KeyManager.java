
package videogame;
/**
 * KeyManager Class
 * 
 * Detects input from keyboard
 * 
 * @author Pablo Andrade A01193740
 * @date 01/18/2018
 * @version 1.0
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    
    public boolean upLeft;      // flag to move up-left the player
    public boolean upRight;    // flag to move up-right the player
    public boolean downLeft;    // flag to move down-left the player
    public boolean downRight;   // flag to move down-right the player
    private boolean keys[];  // to store all the flags for every key
    public boolean pause;      // flag to move up-left the player
    
    public KeyManager() {
        keys = new boolean[256];
        pause = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
        if (e.getKeyCode() == KeyEvent.VK_P) {
            pause = !pause;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        upLeft = keys[KeyEvent.VK_Q];
        upRight = keys[KeyEvent.VK_E];
        downLeft = keys[KeyEvent.VK_A];
        downRight = keys[KeyEvent.VK_D];
    }
}
