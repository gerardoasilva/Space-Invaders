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
    
    /**
     * consturctor of the alien class
     * @param x to set horizontal initial position of alien
     * @param y to set vertical initial position of alien
     * @param width to set width of alien
     * @param height to set height of alien
     * @param game access to the game
     */
    public Alien(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        bomb = new Bomb(getX()+getWidth()/2-5, getY(), 5, 5, game);
        explosion = new Animation(Assets.explosion, 500);
        canShoot = true;
    }

    // Getters and setters fot the provate attributes
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getXDir() {
        return xDir;
    }
    
    public Bomb getBomb() {
        return bomb;
    }
    
    public int getBombX() {
        return bomb.getX();
    }
    
    public int getBombY() {
        return bomb.getY();
    }
    
    public boolean canShoot() {
        return canShoot;
    }
    
    public void setCanShoot(boolean cS){
        canShoot = cS;
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
    
    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }
    
    public void setBombX(int bombX) {
        bomb.setX(bombX);
    }
    
    public void setBombY(int bombY) {
        bomb.setY(bombY);
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
        
        if (!getBomb().isDead()) {
            bomb.tick();
        }

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
        bomb.render(g);
    }
}
