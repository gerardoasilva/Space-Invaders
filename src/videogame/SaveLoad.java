/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.util.LinkedList;

/**
 *
 * @author eugenio
 */
public class SaveLoad {

    private Game game;

    SaveLoad(Game game) {
        this.game = game;
    }

    void saveGame() {
        try {
            //Creates new file to save the game data
            FileWriter fw = new FileWriter("save.txt");
            //Saves every value of each object in the game
            fw.write(String.valueOf(game.getPlayer().getX()) + "\n");
            fw.write(String.valueOf(game.getPlayer().getY()) + "\n");
            
            // Save bullets
            fw.write(String.valueOf(game.bullets.size()) + "\n");
            if (!game.bullets.isEmpty()) {
                fw.write(String.valueOf(game.bullets.getFirst().getX()) + "\n");
                fw.write(String.valueOf(game.bullets.getFirst().getY()) + "\n");
            }
            
            // Saves aliens
            fw.write(String.valueOf(game.getAliens()) + "\n");
            for (int i = 0; i < game.getAliens(); i++) {
                fw.write(String.valueOf(game.aliens.get(i).getX()) + "\n");
                fw.write(String.valueOf(game.aliens.get(i).getY()) + "\n");
                fw.write(String.valueOf(game.aliens.get(i).getXDir()) + "\n");
                fw.write(String.valueOf(game.aliens.get(i).canShoot()) + "\n");
            }
            
            // Save exlposions
            fw.write(String.valueOf(game.explosions.size()) + "\n");
            for (int i = 0; i < game.explosions.size(); i++) {
                fw.write(String.valueOf(game.explosions.get(i).getX()) + "\n");
                fw.write(String.valueOf(game.explosions.get(i).getY()) + "\n");
            }
            
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadGame() {
        try {
            //Loads file where every value of the game was saved
            BufferedReader br = new BufferedReader(new FileReader("save.txt"));
            //Read every value in the file so it can be loaded
            game.getPlayer().setX(Integer.parseInt(br.readLine()));
            game.getPlayer().setY(Integer.parseInt(br.readLine()));
            
            //Loads bullet
            int bullet = Integer.parseInt(br.readLine());
            game.bullets.clear();
            if (bullet > 0) {
                int xBullet = Integer.parseInt(br.readLine());
                int yBullet = Integer.parseInt(br.readLine());
                game.bullets.addFirst(new Bullet(xBullet, yBullet, 3, 10, game));
            }
            
            // Loads aliens from previous save
            int aliens = Integer.parseInt(br.readLine());
            game.aliens.clear();
            for (int i = 0; i < aliens; i++) {
                int x = Integer.parseInt(br.readLine());
                int y = Integer.parseInt(br.readLine());
                int xDir = Integer.parseInt(br.readLine());
                boolean canShoot = Boolean.parseBoolean(br.readLine());
                
                game.aliens.addFirst(new Alien(x, y, 12, 12, game));
                game.aliens.getFirst().setXDir(xDir);
                game.aliens.get(i).setCanShoot(canShoot);
                
            }

            // Load explosions from previous game
            game.explosions.clear();
            int explosions = Integer.parseInt(br.readLine());
            for (int i = 0; i < explosions; i++) {
                // Read data
                int x = Integer.parseInt(br.readLine());
                int y = Integer.parseInt(br.readLine());
                // create object from data
                game.explosions.addFirst(new Explosion(x, y, 12, 12, game));
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(SaveLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
