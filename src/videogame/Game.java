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
import java.util.LinkedList;

/**
 *
 * @author antoniomejorado
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
    private LinkedList<Bullet> bullets;
    private LinkedList<Enemy> enemies;                // to use a bad guy
    private LinkedList<Bomb> bombs;
    private KeyManager keyManager;  // to manage the keyboard
    private boolean ingame = true;
    private boolean win = true;
    
    
    /**
     * to create title, width and height and set the game is still not running
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
        // creating my enemy list
        enemies = new LinkedList<>();
        bullets = new LinkedList<>();
        bombs = new LinkedList<>();
    }

    /**
     * To get the width of the game window
     * @return an int value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an int value with the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         player = new Player(270, 280, 1, 15, 10, this);
         for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Enemy enemy = new Enemy(150 + 18 * j, 5 + 18 * i,12,12,this);
                enemies.add(enemy);
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
        if(ingame || !win){
            keyManager.tick();
            // Updating player
            player.tick();
            if(keyManager.space){
                if(bullets.isEmpty())
                    bullets.add(new Bullet(player.x+player.getWidth()/2, player.y-1, 5,5,this));
            }
            // Updating every enemy
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy =  enemies.get(i);
                enemy.tick();
                
                // Check if enemy reached ground level
                if(enemy.y > 290){
                    ingame = false;
                }
                // Check border collison 
                if(enemy.x <= 0){
                    // For all enemies hange move left to true
                    for(int j = 0; j < enemies.size(); j++){
                        enemies.get(j).setMoveLeft(true);
                        enemies.get(j).setY(enemies.get(j).getY()+20);
                    }
                }
                if(enemy.x + enemy.getWidth() >= width){
                    // For all enemies hange move left to false
                    for(int j = 0; j < enemies.size(); j++){
                        enemies.get(j).setMoveLeft(false);
                        enemies.get(j).setY(enemies.get(j).getY()+20);
                    }
                }

                if(!bullets.isEmpty())
                    if(enemy.intersects(bullets.getFirst())){
                        enemies.remove(i);
                        if(enemies.isEmpty()){
                            win = true;
                        }
                        bullets.removeFirst();
                    }

            }
            // Update every bomb
            for (int i = 0; i < bombs.size(); i++){
                Bomb bomb = bombs.get(i);
                bomb.tick();

            }
            // Update every bullet
            for(int i = 0; i < bullets.size(); i++){
                Bullet bullet = bullets.get(i);
                bullet.tick();

            }
        }
    }
    
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
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy =  enemies.get(i);
                enemy.render(g);
            }
            for(int i = 0; i < bombs.size(); i++){
                Bomb bomb = bombs.get(i);
                bomb.render(g);
            }
            for(int i = 0; i < bullets.size();i++){
                Bullet bullet = bullets.get(i);
                if(bullet.y < 0){
                    bullets.remove(i);
                } else {
                    bullet.render(g);
                }
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
     * setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
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
