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

    private int width;
    private int height;
    private Game game;

    public Bullet(int x, int y, int width, int height, Game game) {
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



    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    

    @Override
    public void tick() {
        --y;
    }

    public Rectangle getPerimeter() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bomb, getX(), getY(), getWidth(), getHeight(), null);
    }
}
