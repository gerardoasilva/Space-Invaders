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
public class Enemy extends Item{

    private int width;
    private int height;
    private Game game;
    private int xDir = 2;
    private boolean moveLeft = true;
    
    public Enemy(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void setMoveLeft(boolean state){
        moveLeft = state;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick() {
        if(moveLeft){
            x++;
        } else {
            x--;
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
        return obj instanceof Bullet && getPerimeter().intersects(((Bullet) obj).getPerimeter());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}
