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
public class Explosion extends Item{
    private Animation explode;
    private int timer;
    private int width;
    private int height;
    private Game game;

    public Explosion(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.explode = new Animation(Assets.explosion,100);
        this.timer = 20;
    }
    
    public int getTimer(){
        return timer;
    }

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
