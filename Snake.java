package snake;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;


public class Snake extends JFrame
{
    public Snake()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        setResizable(false);
        
        frame();
        
    }
    
    public void frame()
    {
        setLayout(new GridLayout(1,1,0,0));
        Window w = new Window();
        add(w);
        
        
        pack();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Snake();
        
    }
    
}