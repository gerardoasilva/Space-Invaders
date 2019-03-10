/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;
import static java.lang.Math.random;

/**
 *
 * @author eugenio
 */
public class Alien extends Item{

    private int width;
    private int height;
    private Game game;
    private int xDir = -1;
//    private boolean moveLeft = true;
    private Bomb bomb;
    private Animation explosion;
    private boolean canShoot;
    
    public Alien(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        explosion = new Animation(Assets.explosion, 500);
        canShoot = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getXDir() {
        return xDir;
    }
    
    public boolean canShoot() {
        return canShoot;
    }
    
//    public void setMoveLeft(boolean state){
//        moveLeft = state;
//    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setXDir(int xDir) {
        this.xDir = xDir;
    }
    
    public void canShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }
    
    @Override
    public void tick() {
//>>>>>>>>>>>>>>>        
//        if(moveLeft){
//            x--;
//        } else {
//            x++;
//        }
//===========
        
        setX(getX() + xDir);
//>>>>>>>>>>>>>>>
    
    // Randomly create bomb
    


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
        g.drawImage(Assets.alien, getX(), getY(), getWidth(), getHeight(), null);
    }
}
