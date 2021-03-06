/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import static java.lang.Math.random;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author eugenio
 */
public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    public LinkedList<Bullet> bullets; // to store the bullets
    public LinkedList<Alien> aliens;  // to store the aliens
    public LinkedList<Bomb> bombs; // to store the bombs
    public LinkedList<Explosion> explosions; // to store the explosions
    private KeyManager keyManager;  // to manage the keyboard
    private boolean ingame = true;  // flag for game state
    private boolean win = false;    // flag for game won
    private int probability;
    private SaveLoad saveLoad;
    
    
    /**
     * Constructor of game 
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        aliens = new LinkedList<>();
        bullets = new LinkedList<>();
        bombs = new LinkedList<>();
        explosions = new LinkedList<>();
        saveLoad = new SaveLoad(this);
    }
    
    /**
     * gets the width
     * @return 
     */
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    public Player getPlayer(){
        return player;
    }
    public int getAliens(){
        return aliens.size();
    }
    public int getBombs(){
        return bombs.size();
    }
    
    /**
     * Initializes the display window of the game, player and aliens
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
        
         player = new Player(270, 280, 1, 15, 10, this);
         
         for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Alien alien = new Alien(150 + 18 * j, 5 + 18 * i,12,12,this);
                aliens.add(alien);
            }
         }
         display.getJframe().addKeyListener(keyManager);
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta --;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    private void tick() {            
        keyManager.tick();

        if (keyManager.save) {
            saveLoad.saveGame();
        }
        if (keyManager.load) {
            saveLoad.loadGame();
        }
        
        // Restart Game
                if (keyManager.restart) {
                    win = false;
                    ingame = true;
                    player = new Player(270, 280, 1, 15, 10, this);
                    aliens.clear();
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 6; j++) {
                            Alien alien = new Alien(150 + 18 * j, 5 + 18 * i, 12, 12, this);
                            aliens.add(alien);
                        }
                    }
                    keyManager.restart = false;
                }
                
        if (!keyManager.pause) {

            if (ingame || win) {

//                // Restart Game
//                if (keyManager.restart) {
//                    win = false;
//                    ingame = true;
//                    player = new Player(270, 280, 1, 15, 10, this);
//                    aliens.clear();
//                    for (int i = 0; i < 4; i++) {
//                        for (int j = 0; j < 6; j++) {
//                            Alien alien = new Alien(150 + 18 * j, 5 + 18 * i, 12, 12, this);
//                            aliens.add(alien);
//                        }
//                    }
//                    keyManager.restart = false;
//                }

                // Updating player
                player.tick();

                // Enables shooting if no bullet exist
                if (keyManager.space) {
                    if (bullets.isEmpty()) {
                        bullets.add(new Bullet(player.x + player.getWidth() / 2 - 1, player.y - 5, 3, 10, this));
                    }
                }

                // Updating every alien
                for (int i = 0; i < aliens.size(); i++) {
                    Alien alien = aliens.get(i);

                    // Change direction for all aliens when colliding
                    if (alien.getX() >= getWidth() - 30 && alien.getXDir() != -1) { // Collides with right border
                        for (int j = 0; j < aliens.size(); j++) {

                            aliens.get(j).setXDir(-1);
                            aliens.get(j).setY(aliens.get(j).getY() + 15);
                        }
                    }

                    if (alien.getX() <= 5 && alien.getXDir() != 1) { // Collides with left border
                        for (int j = 0; j < aliens.size(); j++) {

                            aliens.get(j).setXDir(1);
                            aliens.get(j).setY(aliens.get(j).getY() + 15);
                        }
                    }

                    // Check if alien reached ground level
                    if (alien.getY() + alien.getHeight() >= 290) {
                        ingame = false;
                    }

                    // If bullet collides with alien, remove alien
                    if (!bullets.isEmpty()) // A bullet exists
                    {
                        if (alien.intersects(bullets.getFirst())) {

                            explosions.add(new Explosion(aliens.get(i).x, aliens.get(i).y, 12, 12, this));
                            aliens.remove(i);

                            // Check if player has won the game
                            if (aliens.isEmpty()) {
                                win = true;
                                // ________
                                ingame = false;
                            }
                            bullets.removeFirst();
                        }
                    }

                    // Creates randomly alien's bomb
                    if (((int) (Math.random() * 15)) == 5 && alien.getBomb().isDead()) {
                        alien.getBomb().isDead(false);
                        alien.getBomb().setX(alien.getX() + alien.getWidth() / 2);
                        alien.getBomb().setY(alien.getY() + alien.getHeight());
                    }
                    alien.tick();
                    alien.getBomb().tick();

                }

                // Update every bullet
                for (int i = 0; i < bullets.size(); i++) {
                    Bullet bullet = bullets.get(i);
                    bullet.tick();

                }

                // Update explosions
                for (int i = 0; i < explosions.size(); i++) {
                    Explosion explosion = explosions.get(i);
                    explosion.tick();

                    if (explosions.get(i).getTimer() <= 0) {
                        explosions.remove(i);
                    }

                }
                
                // Look up for collisions between bomb and player
                for (Alien a1: aliens) {
                    if (player.intersects(a1.getBomb()) && player.getY()+player.getHeight()-1 > a1.getBomb().getPerimeter().getY()) {
                        explosions.add(new Explosion(player.getX()+1,player.getY(), 12, 12, this));
                        win = false;
                        ingame = false;
                    }
                }
            }

        }

    }
    
    /**
     * renders every instance in the game
     */
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            g.setColor(Color.green);
            g.drawLine(0,290,width,290);
            player.render(g);
//>>>>>>>>>>>>>>>            
//            // Render all aliens
//            for (int i = 0; i < aliens.size(); i++) {
//                Alien alien =  aliens.get(i);
//                alien.render(g);
//            }
//===============
            for (Alien alien: aliens) {
                alien.render(g);
            }
//>>>>>>>>>>>>>>>
           
//>>>>>>>>>>>>>>>            
//            // Render all bombs
//            for(int i = 0; i < bombs.size(); i++){
//                Bomb bomb = bombs.get(i);
//                bomb.render(g);
//            }
//==============            
//            // Render all bombs
//            for (Bomb bomb : bombs) {
//                bomb.render(g);
//            }
//>>>>>>>>>>>>>>>    
            
//>>>>>>>>>>>>            
//            // Render bullets (1)
//            for(int i = 0; i < bullets.size(); i++){
//                Bullet bullet = bullets.get(i);
//                if (bullet.getY() < -bullet.getHeight()){
//                    bullets.remove(i);
//                } else {
//                    bullet.render(g);
//                }
//            }
// ==========

            for (Bullet bullet : bullets) {
                if (bullet.getY() < -bullet.getHeight()) {
                    bullets.remove(bullets.getFirst());
                } else {
                    bullet.render(g);
                }
            }
//>>>>>>>>>>>>>>>>>> 
            for(Explosion explosion : explosions){
                explosion.render(g);
            }
            
            /*g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.setColor(Color.red);
            g.drawString("Lives: "+lives, 20, 40);
            g.drawString("Score: "+score, width/2, 40);*/
            g.setFont(new Font("helvetica",1,25));
            if(!ingame){
               g.drawString("INVASION HAS STARTED!!!", 15, height/2);
            }
            if(win){
               g.drawString("YOU SAVE THE EARTH!!!", 25, height/2);
            }
            bs.show();
            g.dispose();
        }
       
    }
    
    /**
     * Setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * Stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }
}