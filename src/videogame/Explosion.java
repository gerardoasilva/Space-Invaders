/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author eugenio
 */
public class Explosion extends Item {
    private Animation explode;  // To store the animation
    private int timer;  // To store the timer for animation
    private int width;  // To store width of animation
    private int height; // To store height of animation
    private Game game;  // To store game for aimation

    /**
     * Constructor for animaton
     * @param x sets initial x
     * @param y sets initial y
     * @param width sets width
     * @param height sets height 
     * @param game sets game
     */
    public Explosion(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.explode = new Animation(Assets.explosion,100);
        this.timer = 20;
    }
    
    /**
     * Getter for timer
     * @return 
     */
    public int getTimer(){
        return timer;
    }

    /**
     * Runs animation
     */
    @Override
    public void tick() {
        explode.tick();
        timer--;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(explode.getCurrentFrame(), x, y, null);
    }
    
}
