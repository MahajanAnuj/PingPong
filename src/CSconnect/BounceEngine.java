/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

public class BounceEngine implements Runnable {

        public int mode;//0 for normal; 1 for realistic; 2 psychedelic;
        private Sprites parent;

        public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
        }
        
        public BounceEngine(Sprites parent) {
            this.parent = parent;
            mode=0;
        }

        @Override
        public void run() {
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
            parent.racquet.x=width-parent.racquet.WIDTH/2; 

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
		parent.racquet.xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			parent.racquet.xa = -parent.paddlespeed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			parent.racquet.xa = parent.paddlespeed;
                parent.racquet.move();
	}
        

        public Sprites getParent() {
            return parent;
        }

        public void move(Ball ball) {

            Point p = ball.getLocation();
            Point speed = ball.getSpeed();
            Dimension size = ball.getSize();
            Boolean collided=false;
            int vx = speed.x;
            int vy = speed.y;

            int x = p.x;
            int y = p.y;
            //speed reversal on colliding walls
            if (x + vx < 0 || x + size.width + vx > getParent().getWidth()) {
                vx *= -1;
            }
            else if (y + vy < 0 ){
                vy *= -1;
            }
            else if(y + size.height + vy > getParent().getHeight()){
            //lost life for this player
                parent.gameOver();
            }
            //increase speed on collision for increasing game difficulty
            else if(ball.collision()){
                vy *= -1;
                y = parent.racquet.getTopY() - (int)ball.getSize().getHeight();
                collided=true;
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
            }
            ball.setLocation(new Point(x, y));

        }
    }

