package videogame;


import java.awt.image.BufferedImage;

/**
 * Animation Class
 * 
 * Animates sprites
 * 
 * @author Pablo Andrade A01193740
 * @date 01/30/2018
 * @version 1.0
 */
public class Animation {
    private int speed; // for the speed of every frame
    private int index; //to get the index of next frame to paint
    private long lastTime; //to save the previous time of animation
    private long timer; //to accummulate the time of animation
    private BufferedImage[] frames; //to store every image - frame
    
    /**
     * Creating the animation with all the frames and the speed for each
     * @param frames an array of images
     * @param speed an int value for the speed of every frame
     */
    public Animation(BufferedImage[] frames, int speed){
        this.frames = frames; //storing frmaes
        this.speed = speed; // storing speed
        index = 0; //initializing index
        timer = 0; //initializing timer
        lastTime = System.currentTimeMillis(); //getting the initial time 
    }
    
    /**
     * Getting the current frame to paint
     * @return the BufferedImage to the corresponding frame or image
     */
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    
    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        //updating the lastTime for the next tick
        lastTime = System.currentTimeMillis();
        //check the timer to increase  the index
        if(timer > speed){

        }   //accumulating time from the previous tick to this one 
        timer += System.currentTimeMillis() - lastTime;
        //updating the lastTime for the next tick
        lastTime = System.currentTimeMillis();
        //check the timer to increase  the index
        if(timer > speed){
            index++;
            timer = 0;
            //check index not to get out of bounds
            if(index >= frames.length){
                index = 0;
            }
        }
    }
}
