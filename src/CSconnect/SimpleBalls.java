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
                Sprites ballsnpaddles = new Sprites(1,2,0);
                frame.add(ballsnpaddles);
                frame.setSize(400, 400);
                frame.setVisible(true);
                System.out.println("Width = "+ ballsnpaddles.getWidth()+" Height = "+ballsnpaddles.getHeight());
                //start the display thread
                BounceEngine bengine=new BounceEngine(ballsnpaddles);
                ballsnpaddles.bengine=bengine;
                AI ai1=new AI(1,ballsnpaddles);
                new Thread(bengine).start();
                new Thread(ai1).start();
            }
        });
    }
     
}

