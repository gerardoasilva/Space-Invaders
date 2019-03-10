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
public class Player extends Item{

    private int direction;  // To store direction for player
    private int width;      // To store width of player
    private int height;     // To store height of player
    private Game game;      // To store game for player
    
    /**
     * 
     * @param x to set initial x position
     * @param y to set initial y position
     * @param direction to set initial direction
     * @param width to set width of player
     * @param height to set height of player
     * @param game to set gam efor player
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    /**
     * To get direction of player
     * @return direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * To get widht of player
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get height of player
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To set directon of player
     * @param direction 
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * To set width of player
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set height of player
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * To move player left and right and keep inside game area
     */
    @Override
    public void tick() {

        if (game.getKeyManager().left) {
           setX(getX() - 2);
        }
        if (game.getKeyManager().right) {
           setX(getX() + 2);
        }
        
        // reset x position and y position if colision
        if (getX() + 15 >= game.getWidth()) {
            setX(game.getWidth() - 15);
        }
        else if (getX() <= 0) {
            setX(0);
        }
    }
    
    /**
     * Method to get perimeter of instance
     * @return Rectangle
     */
    public Rectangle getPerimeter() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Validates intersection
     * @param obj
     * @return
     */
    public boolean intersects(Object obj) {
        return obj instanceof Bomb && getPerimeter().intersects(((Bomb) obj).getPerimeter());
    }

    /**
     * To draw player
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
