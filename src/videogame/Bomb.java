/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author eugenio
 */
public class Bomb extends Item {

    private int width;      // to store width
    private int height;     // to store height
    private Game game;      // to store the game 
    private boolean dead;   // to know if the bomb exists in the game
    private boolean probability; // to store the probability of shooting
    
    /**
     * Constructor of Bomb object
     * @param x for horizontal initial position
     * @param y for vertical initial position 
     * @param width to set width of object
     * @param height to set height of object
     * @param game the game where it'll run
     */
    public Bomb(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        dead = true;
    }

    /**
     * To get the width of bomb
     * @return 
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get height of bomb
     * @return 
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * To know if bomb exists in the game
     * @return 
     */
    public boolean isDead() {
        return dead;
    }
    
    /**
     * To set width of bomb
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set height of bomb
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * To determine if bomb exists in the game
     * @param dead 
     */
    public void isDead(boolean dead) {
        this.dead = dead;
    }
    
    // Loop that runs all the time
    @Override
    public void tick() {
        if (!isDead()) {
            
            if (getY()+getHeight() >= 290) {
                isDead(true);
                setY(300);
            }
            // Moves the bomb constantly
            setY(getY()+1);
        }
    }

    // Creates a rectangle of the object
    public Rectangle getPerimeter() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    // Loop that draws object bomb
    @Override
    public void render(Graphics g) {
        if (!isDead()) {
            g.drawImage(Assets.bomb, getX(), getY(), getWidth(), getHeight(), null);
        } 
    }
}
