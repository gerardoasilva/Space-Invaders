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
 * @author antoniomejorado
 */
public class Player extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
