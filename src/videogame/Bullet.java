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
public class Bullet extends Item{
    private int width;  // to store width 
    private int height; // to store height 
    private Game game;  // 

    /**
     * Constructor of Bullet object
     * @param x for horizontal initial position
     * @param y for vertical initial position
     * @param width to set width of object
     * @param height to set height of object
     * @param game the game where it'll run
     */
    public Bullet(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
    }

    // Getters and setters of attributes
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    

    /**
     * Loop that runs all the time
     */
    @Override
    public void tick() {
        // moves the bullet up all the time
        y -= 4;
    }

    public Rectangle getPerimeter() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * renders images in the game
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bullet, getX(), getY(), getWidth(), getHeight(), null);
    }
}
