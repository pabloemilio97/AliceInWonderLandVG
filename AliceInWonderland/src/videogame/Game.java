package videogame;

/**
 * Game Class
 * 
 * Has main logic for game running and initializes player
 * 
 * @author Pablo Andrade A01193740
 * @date 01/18/2018
 * @version 1.0
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private boolean gameOver;       //to stop the game
    private Player player;          // to use a player
    private KeyManager keyManager;  // to manage the keyboard
    private ArrayList<Enemy> enemies; //to store enemies
    private int lives; //lives of the player
    private int score; //score of the player
    private int missed; //counter for missed enemies
    private SoundClip collisionClip; //to manage sound when player collides with power
    private SoundClip badCollisionClip; //to manage sound when player collides without power
    private SoundClip powerClip; //when power is acquired
    private int timeCont; //time counter
    private int timeCont2;
    private boolean hasSuperPower; //true if player has power each 10 secs
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        gameOver = false;
        lives = 5;
        score = 0;
        collisionClip = new SoundClip("/sounds/success.wav");
        badCollisionClip = new SoundClip("/sounds/fail.wav");
        powerClip = new SoundClip("/sounds/power.wav");
        missed = 0;
        timeCont = 0;
        timeCont2 = 0;
    }

    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public int getMissed() {
        return missed;
    }

    public boolean hasSuperPower() {
        return hasSuperPower;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public void setMissed(int missed) {
        this.missed = missed;
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());  
        Assets.init();
        player = new Player(getWidth() / 2 - 75, getHeight() / 2 - 75, 1, 150, 150, this);
        display.getJframe().addKeyListener(keyManager);
        enemies = new ArrayList();
        //creating enemies
        for(int i = 0; i < 10; i++){
            enemies.add(new Enemy((int)(Math.random() * (getWidth() * 2) + getWidth() ), 
            (int)(Math.random() * (getHeight() - 75)), 75, 75, this)); // 75 deducted to getHeight() to avoid image cutting
        }
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running && !gameOver) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            //pause the game
            // if delta is positive we tick the game
            if (delta >= 1) {
                if(timeCont == 600 && timeCont2 == 0){ //10 secs
                    hasSuperPower = true;
                    powerClip.play();
                    timeCont = 0;
                }
                if(timeCont2 == 200){ // 3 secs
                    timeCont2 = 0;
                    hasSuperPower = false;
                }
                tick();
                render();
                delta--;
            }
        }
        render();
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    private void tick() {
        if (!keyManager.pause) {
            if (enemies.size() >= 30 || enemies.size() <= 0) {
                gameOver = true;
            }
            if(!hasSuperPower){
                timeCont++;
            }
            else{
                timeCont2++;
            }
            // advancing player with colision
            player.tick();

            //getting every enemy by using iterator
            Iterator itr = enemies.iterator();
            while (itr.hasNext()) {
                //getting specific enemy
                Enemy enemy = (Enemy) itr.next();
                //moving the enemy
                enemy.tick();

                //if the enemy is out of the screen
                if (enemy.getX() < 0) {
                    //updates counter for lost enemies
                    setMissed(getMissed() + 1);
                    enemy.randomize();
                    //deduct 20 points
                    setScore(getScore() - 20);
                    //reduce a life if 10 enemies have been lost
                    if (getMissed() > 0 && getMissed() % 10 == 0) {
                        setLives(getLives() - 1);
                        //make objects move faster
                        enemy.setVelocity(enemy.getVelocity() + 1);
                    }
                }

                //if enemy crashes with upper or lower bound
                if (enemy.getY() < 20 || enemy.getY() > getHeight() - 20) {
                    enemy.invertDirection();
                }
                // if player crashed with the lower bound of enemy
                if (enemy.intersects(player)) {
                    if(!hasSuperPower){
                        //play sound
                        badCollisionClip.play();
                        //enemy returns to spawn if touched
                        enemy.randomize();
                        //add new enemy
                        enemies.add(new Enemy((int) (Math.random() * (getWidth() * 2) + getWidth()),
                                (int) (Math.random() * (getHeight() - 75)), 75, 75, this));
                    }
                    else{
                        collisionClip.play();
                        enemies.remove(enemy);
                    }
                    itr = enemies.iterator();   
                }
            }
        }
        keyManager.tick();
    }   
    
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            if(!gameOver){
                if (keyManager.pause) {
                    g.drawImage(Assets.pauseImg, 0, 0, width, height, null);
                } else {
                    g.drawImage(Assets.background, 0, 0, width, height, null);
                    player.render(g);
                    //set font
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                    g.setColor(Color.white);
                    g.drawString("Enemies: " + Integer.toString(enemies.size()),
                            getWidth() - 300, 50);
                    //enemies
                    Iterator itr = enemies.iterator();
                    while (itr.hasNext()) {
                        Enemy enemy = (Enemy) itr.next();
                        enemy.render(g);
                    }
                }
            }
            else{
                if(enemies.size() > 0){
                    g.drawImage(Assets.gameOver, 0, 0, width, height, null);
                }
                else{
                    g.drawImage(Assets.victory, 0, 0, width, height, null);
                }
            }
            
            
            bs.show();
            g.dispose();
        }
       
    }
    
    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }

 
    


}
