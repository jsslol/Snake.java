/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author jared
 */
public class Window extends JPanel implements Runnable {
    
    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 800, HEIGHT = 800;
    private Thread thread;
    private boolean running = false;
    
    private Player p;
    private ArrayList<Player> snake;
    
    private Apple apple;
    private ArrayList<Apple> apples;
    
    private int x = 10, y = 10;
    private int size = 5;
    
    public int appleCount = -1;
    
    private boolean right = true, left = false, up = false, down = false;
    
    private int ticks = 0;
    
    private Random r;
    
    private Key key;
    
    private String score = "Score: " + appleCount;
     
    public Window(){
        setFocusable(true);
        key = new Key();
        addKeyListener(key);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        
        r = new Random();
        
        snake = new ArrayList<Player>();
        apples = new ArrayList<Apple>();
        start();
    }
    
    public void tick(){
        if(snake.size() == 0){
            p = new Player(x,y,10);
            snake.add(p);
        }
        
        if(apples.size() == 0){
            int x = r.nextInt(79);
            int y = r.nextInt(79);
            
            apple = new Apple(x,y,10);
            apples.add(apple);
            appleCount++;
            
        }
        
        for(int i = 0; i < apples.size(); i++){
            if(x == apples.get(i).getx() && y == apples.get(i).gety()){
                size++;
                apples.remove(i);
                i--;
            }
        }
        
        for(int i = 0; i < snake.size(); i++){
            if(x == snake.get(i).getx() && y==snake.get(i).gety()){
                if(i != snake.size()-1){
                    stop();
                }
            }
        }
        
        if(x < 0 || x > 79 || y < 0 || y > 79){
            stop();
        }
        
        ticks++;
        
        if(ticks > 650000){
            if(right) x++;
            if(left) x--;
            if(up) y--;
            if(down)y++;
            
            ticks = 0;
            
            p = new Player(x,y,10);
            snake.add(p);
            
            if(snake.size() > size){
                snake.remove(0);
            }
        }
    }
    
   
    public void paint(Graphics g){
       g.clearRect(0, 0, WIDTH, HEIGHT);

       for(int i = 0; i < snake.size(); i++){
           snake.get(i).draw(g);
       } 
       for(int i = 0; i < apples.size(); i++){
           apples.get(i).draw(g);
       }
    }
    
    public void start(){
        running = true;
        thread = new Thread(this, "Game Loop");
        thread.start();
    }       
             
    public void stop(){
        System.out.println("Score: " + appleCount);
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void run(){
        while(running){
            tick();
            repaint();
        }
    }
     
    private class Key implements KeyListener {
        
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_RIGHT && !left) {
                up = false;
                down = false;
                right = true;
            }
            if(key == KeyEvent.VK_LEFT && !right) {
                up = false;
                down = false;
                left = true;
            }
            if(key == KeyEvent.VK_UP && !down) {
                left = false;
                up = true;
                right = false;
            }
            if(key == KeyEvent.VK_DOWN && !up) {
                left = false;
                right = false;
                down = true;
            }

            
        }

        @Override
        public void keyTyped(KeyEvent e) {
           
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
    }

}
