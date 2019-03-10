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
    public static BufferedImage alien;      // to store alien's image
    public static BufferedImage bullet;     // to store player's bullet image
    public static BufferedImage bomb;       // to store alien's bomb
    public static BufferedImage explosion[];  // to store the explosion
    //public static SoundClip bomb;
    
    /**
     * initialize game images
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/backgroundB.jpg");
        player = ImageLoader.loadImage("/images/tank.png");
        alien = ImageLoader.loadImage("/images/alien.png");
<<<<<<< HEAD
        bullet = ImageLoader.loadImage("/images/bullet1.png");
        bomb = ImageLoader.loadImage("/images/laser.png");
=======
        bullet = ImageLoader.loadImage("/images/bullet.png");
        bomb = ImageLoader.loadImage("/images/bomb.png");
>>>>>>> eugenio
        
        explosion = new BufferedImage[6];
        explosion[0] = ImageLoader.loadImage("/images/explosion1.png");
        explosion[1] = ImageLoader.loadImage("/images/explosion2.png");
        explosion[2] = ImageLoader.loadImage("/images/explosion3.png");
        explosion[3] = ImageLoader.loadImage("/images/explosion4.png");
        explosion[4] = ImageLoader.loadImage("/images/explosion5.png");
        explosion[5] = ImageLoader.loadImage("/images/explosion6.png");
        
        //bomb = new SoundClip("/sounds.blip.wav");
    }
    
}
