/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class BounceEngine implements Runnable {

        public int mode;//0 for normal; 1 for realistic; 2 psychedelic;
        private Sprites parent;
        public int ScoreMultiplier=1;
        public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
        }
        
        public BounceEngine(Sprites parent) {
            this.parent = parent;
            mode=0;
            ScoreMultiplier=1;
        }

        @Override
        public void run() {
        /*
            ->x
        |y
        v  _ _ _ 1 _ _ _ 
         |       _      |
         |              |
        2||            ||3
         |       _      |
         |_ _ _ _ _ _ _ |
                0
        */       
            //start from center
            int width = getParent().getWidth()/2;
            int height = getParent().getHeight()/2;
            //System.out.println(width+ " "+ height);
            // Randomize the starting position...
            for (Ball ball : getParent().getBalls()) {
                //int x = random(width);
                //int y = random(height);
                int x=width;
                int y= height;
                Dimension size = ball.getSize();

                if (x + size.width > width) {
                    x = width - size.width;
                }
                if (y + size.height > height) {
                    y = height - size.height;
                }

                ball.setLocation(new Point(x, y));
            }
            for(Racquet racquet:parent.racquets){
                racquet.align();
            }
             
            timedmessage("Game starting in 5 seconds", "Attention Game Starting!",5);
            while (getParent().isVisible()) {

                // Repaint the balls pen...
                    SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        getParent().repaint();
                    }
                });

                // This is a little dangrous, as it's possible
                // for a repaint to occur while we're updating...
                for (Ball ball : getParent().getBalls()) {
                    move(ball);
                }

                // Some small delay...
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                }

            }

        }
        public void keyReleased(KeyEvent e) {
		parent.racquets.get(parent.r2c).xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			parent.racquets.get(parent.r2c).xa = -parent.paddlespeed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			parent.racquets.get(parent.r2c).xa = parent.paddlespeed;
                parent.racquets.get(parent.r2c).move();
	}
        
        public void externalMove(int dir,int playerNo){
            if(dir==0){
                parent.racquets.get(playerNo).xa = 0;
            }
            else{
                if(dir==1){
                    parent.racquets.get(playerNo).xa = parent.paddlespeed;
                }
                else{//-1
                    parent.racquets.get(playerNo).xa = -parent.paddlespeed;
                }
                parent.racquets.get(playerNo).move();
            }
        } 
        
        public Sprites getParent() {
            return parent;
        }

        public void move(Ball ball) {
        /*
            ->x
        |y
        v  _ _ _ 1 _ _ _ 
         |       _      |
         |              |
        2||            ||3
         |       _      |
         |_ _ _ _ _ _ _ |
                0
        */
            //this changes for each of the cases due to multiple raquets
            //change lives and stuff
            int numr=parent.numracquets;
            int colpad=ball.collision();
            Point p = ball.getLocation();
            Point speed = ball.getSpeed();
            Dimension size = ball.getSize();
            Boolean collided=false;
            int vx = speed.x;
            int vy = speed.y;

            int x = p.x;
            int y = p.y;
            //speed reversal on colliding walls
            if (x + vx < 0) {//player 2 fault
                vx *= -1;
                if(numr>2){
                    parent.racquets.get(2).decrementLife();
                }
            }
            else if (x + size.width + vx > getParent().getWidth()) {//player 3 fault
                vx *= -1;
                if(numr>3){
                    parent.racquets.get(3).decrementLife();
                }
            }
            else if (y + vy < 0 ){//player 1 fault
                vy *= -1;
                if(numr>1){
                    parent.racquets.get(1).decrementLife();
                }
            }
            else if(y + size.height + vy > getParent().getHeight()){//player 0 fault
                vy *= -1;
                if(numr>0){
                    parent.racquets.get(0).decrementLife();
                }
            }
            //increase speed on collision for increasing game difficulty
            else if(colpad>-1){//collision
                if (colpad==0){
                    vy *= -1;
                    y = parent.racquets.get(colpad).getTopY() - (int)ball.getSize().getHeight();
                }
                if (colpad==1){
                    vy *= -1;
                    y = parent.racquets.get(colpad).getTopY();
                }
                if (colpad==2){
                    vx *= -1;
                    x = parent.racquets.get(colpad).getTopY() ;
                }
                if (colpad==3){
                    vx *= -1;
                    x = parent.racquets.get(colpad).getTopY() - (int)ball.getSize().getHeight();
                }
                collided=true;
            }
            Boolean allover=true;
            for(Racquet racquet:parent.racquets){
                allover=allover && racquet.isDead();
            }
            if(allover){
                parent.gameOver();
            }
            //lets play with the physics here
            //probailistic accelaration
            if (mode==2){
                if(Math.random()<0.2){
                    vx+=random(2)-1;
                    vy+=random(2)-1;
                }
            }
            x += vx;
            y += vy;

            ball.setSpeed(new Point(vx, vy));
            if (collided){
                ball.incrementSpeed(1);
                parent.paddlespeed+=2;
                parent.racquets.get(colpad).incrementScore(parent.paddlespeed);
            }
            ball.setLocation(new Point(x, y));

        }
        public void timedmessage(String m1,String m2,int t){
            JOptionPane opt = new JOptionPane(m1, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});
            final JDialog dlg = opt.createDialog(m2);
            new Thread(new Runnable()
            {
                public void run(){
                    try
                    {
                        Thread.sleep(t*1000);
                        dlg.dispose();
                    }
                    catch ( Throwable th )
                    {}
                }
            }).start();
            dlg.setVisible(true);}
            }

