/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author eugenio
 */
public class Animation {
    
    private int speed;      // To store the speed for the animation
    private int index;      // To store the index of the animation
    private long lastTime;  // To store last time of the animation
    private long timer;     // To store the timer
    private BufferedImage[] frames; // To store the frames for the animation
    
    /**
     * Constructor of animation
     * @param frames
     * @param speed 
     */
    public Animation(BufferedImage[]frames, int speed){
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    /**
     * To get the current frame of the animation
     * @return frames[index]
     */
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    
    /**
     * Runs the animation
     */
    public void tick(){
       timer += System.currentTimeMillis() - lastTime;
       lastTime = System.currentTimeMillis();
       
       if(timer > speed){
           index++;
           timer = 0;
           if(index >= frames.length){
               index = 0;
           }
       }
    }
}
