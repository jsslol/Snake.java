/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jared
 */
public class Player {
    
    private int x,y,width,height;
    
    public Player(int x, int y, int tileSize){
        this.x = x;
        this.y = y;
        width = tileSize;
        height = tileSize;
    }
    
    public void tick(){
    
    }
    
    public void draw(Graphics g){
       g.setColor(Color.black);
       g.fillRect(x*width, y*height, width, height);
       g.setColor(Color.GREEN);
       g.fillRect(x*width+2, y*height+2, width-4, height-4);
    }

   public int getx() {
        return x;
    }

   public int gety() {
        return y;
    }
   
   public void setx(int x){
       this.x = x;
   }
   
   public void sety(int y){
       this.y = y;
   }
}
