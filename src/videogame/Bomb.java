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

    private int width;  // to store width
    private int height; // to store height
    private Game game;  // to store the game 
    
    /**
     * Constructor of Bomb oject
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
    }

    // Getters and setters
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

    // Loop that runs all the time
    @Override
    public void tick() {
        setY(getY()+1);
    }

    // Creates a rectangle of the object
    public Rectangle getPerimeter() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    // Loop that draws object bomb
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bomb, getX(), getY(), getWidth(), getHeight(), null);
    }
}
