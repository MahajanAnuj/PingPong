/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author anuj
 * code credits http://stackoverflow.com/questions/13022754/java-bouncing-ball
 */
public class SimpleBalls {

    public static void main(String[] args) {
        new SimpleBalls();
    }

    public SimpleBalls() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(SimpleBalls.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Our JFrame
                JFrame frame = new JFrame("Pong Table");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                //Panel for display
                Sprites ballsnpaddles = new Sprites(1);
                frame.add(ballsnpaddles);
                frame.setSize(400, 400);
                frame.setVisible(true);
                System.out.println("Width = "+ ballsnpaddles.getWidth()+" Height = "+ballsnpaddles.getHeight());
                //start the display thread
                BounceEngine bengine=new BounceEngine(ballsnpaddles);
                ballsnpaddles.bengine=bengine;
                new Thread(bengine).start();
                /*
                while (true) {
                    try {
                        ballsnpaddles.repaint();
                        ballsnpaddles.movePaddles();
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SimpleBalls.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}*/
            }
        });
    }
     
}

