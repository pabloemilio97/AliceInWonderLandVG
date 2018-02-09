package videogame;
import java.awt.image.BufferedImage;

/**
 * Assets Class
 *
 * Defines the necessary assets for the game
 *
 * @author Pablo Andrade A01193740
 * @date 01/18/2018
 * @version 1.0
 */
/**
 * initializing the images of the game
 */
public class Assets {

    public static BufferedImage background; //to store background image

    //player sprites
    public static BufferedImage spritesPlayer; //to store the sprites
    public static BufferedImage playerUp[];//pictures to go up
    public static BufferedImage playerLeft[];//pictures to go left
    public static BufferedImage playerDown[];//pictures to go down
    public static BufferedImage playerRight[];//pictures to go right
    //enemy sprites
    public static BufferedImage spritesEnemy; //to store the sprites
    public static BufferedImage enemyUp[];//pictures to go up
    public static BufferedImage enemyLeft[];//pictures to go left
    public static BufferedImage enemyDown[];//pictures to go down
    public static BufferedImage enemyRight[];//pictures to go right

    public static BufferedImage enemy; //to store enemy image
    public static BufferedImage gameOver; //game over display
    public static BufferedImage victory; //victory img
    public static BufferedImage pauseImg; //pause img

    /**
     * Initialize objects with their respective paths
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/garden.png");
        //getting the sprites from for player and enemy
        spritesPlayer = ImageLoader.loadImage("/images/aliceSprite.png");
        spritesEnemy = ImageLoader.loadImage("/images/catSprite.png");
        //creating array of images before animations
        SpreadSheet spritesheetPlayer = new SpreadSheet(spritesPlayer);
        playerUp = new BufferedImage[3];
        playerLeft = new BufferedImage[3];
        playerDown = new BufferedImage[3];
        playerRight = new BufferedImage[3];

        SpreadSheet spritesheetEnemy = new SpreadSheet(spritesEnemy);
        enemyUp = new BufferedImage[3];
        enemyLeft = new BufferedImage[3];
        enemyDown = new BufferedImage[3];
        enemyRight = new BufferedImage[3];
        //cropping the pictures from the sheet into the array 
        for (int i = 0; i < 3; i++) {
            //player 
            playerDown[i] = spritesheetPlayer.crop(i * 32, 0, 32, 32);
            playerLeft[i] = spritesheetPlayer.crop(i * 32, 32, 32, 32);
            playerRight[i] = spritesheetPlayer.crop(i * 32, 64, 32, 32);
            playerUp[i] = spritesheetPlayer.crop(i * 32, 96, 32, 32);
            //enemy
            enemyDown[i] = spritesheetEnemy.crop(i * 32, 0, 32, 32);
            enemyLeft[i] = spritesheetEnemy.crop(i * 32, 32, 32, 32);
            enemyRight[i] = spritesheetEnemy.crop(i * 32, 64, 32, 32);
            enemyUp[i] = spritesheetEnemy.crop(i * 32, 96, 32, 32);
        }
        enemy = ImageLoader.loadImage("/images/catSprite.png");
        gameOver = ImageLoader.loadImage("/images/failure.png");
        victory = ImageLoader.loadImage("/images/victory.png");
        pauseImg = ImageLoader.loadImage("/images/pauseImg.png");
    }
}
