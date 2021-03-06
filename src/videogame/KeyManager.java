/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author eugenio
 */
public class KeyManager implements KeyListener {

    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean space;   // flag to shoot bullet
    public boolean restart = false; // flag to restar the game
    public boolean save;
    public boolean load;
    public boolean pause = false;


    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        
        // Change pause's state
        if (e.getKeyCode() == KeyEvent.VK_P){
            pause = !pause;
        }
        // Read imput if not paused
        if (!pause){
            keys[e.getKeyCode()] = true;
        }
        
        
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
        
        // Restart Game
        if (e.getKeyCode() == KeyEvent.VK_R){
            restart = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        save = keys[KeyEvent.VK_G];
        load = keys[KeyEvent.VK_C];
    }
}
