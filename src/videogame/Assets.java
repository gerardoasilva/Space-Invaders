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
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage enemy;
    public static BufferedImage bomb;
    //public static SoundClip bomb;
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/backgroundB.jpg");
        player = ImageLoader.loadImage("/images/tank.png");
        enemy = ImageLoader.loadImage("/images/alien.png");
        bomb = ImageLoader.loadImage("/images/bomb.png");

        //bomb = new SoundClip("/sounds.blip.wav");
    }
    
}
