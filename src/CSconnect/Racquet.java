/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

/**
 * Implements a racquet
 * @author anuj
 */
import static CSconnect.Sprites.random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
        private int score;
        public int numLife;
        public int playerID;//0 and 1 will move in X axis and 2,3 will move in Y
	public int Y;//fixed coordinate correspondin pen drawing
	public static final int WIDTH = 60;
	public static final int HEIGHT = 10;
        public Color color;
	int x;///moving coordinate
	int xa = 0;
	public Sprites parent;

	public Racquet(Sprites parent,  int playerID) {
		this.parent = parent;
                //change for multiplayer
                x=parent.getWidth()/2-WIDTH/2;
                this.playerID=playerID;
                color=new Color(random(255), random(255), random(255));
                score=0;
                numLife=3;
	}
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
        public  void decrementLife(){
            numLife--;
        }
        public void incrementLife(){
            numLife++;
        }
        public Boolean isDead(){
            return numLife>0;
        }
        public void align(){
            if(playerID==0){
                Y=parent.getHeight()-HEIGHT;
                x=parent.getWidth()/2-WIDTH/2;
            }
            if(playerID==1){
                Y=0;
                x=parent.getWidth()/2-WIDTH/2;
            }
            if(playerID==2){
                Y=0;
                x=parent.getHeight()/2-WIDTH/2;
            }
            if(playerID==3){
                Y=parent.getWidth()-HEIGHT;
                x=parent.getHeight()/2-WIDTH/2;
            }
            
            System.out.println("Player "+ playerID + " X: " +x+" Y: "+Y);
        }
        //gives center of paddle top
        public Point location(){
            Point loc =new Point();
            if(playerID==0){
                loc.x=x+WIDTH/2;
                loc.y=Y;
                }
            if(playerID==1){
                loc.y=HEIGHT;
                loc.x=x+WIDTH/2;
            }
            if(playerID==2){
                loc.x=HEIGHT;
                loc.y=x+WIDTH/2;
            }
            if(playerID==3){
                loc.x=Y;
                loc.y=x+WIDTH/2;
            }
            return loc;
        }

	public void move() {
            //compare the corresponding bounds
            if(playerID<2){
		if (x + xa > 0 && x + xa < parent.getWidth() - WIDTH)
			x = x + xa;}
            else{
                if (x + xa > 0 && x + xa < parent.getHeight()- WIDTH)
			x = x + xa;}
            }
	
        public Color getColor(){
            return color;
        }
	public void paint(Graphics2D g) {
            g.setColor(getColor());
            if(playerID==0){
		g.fillRect(x, Y, WIDTH, HEIGHT);
            }
            if(playerID==1){
                g.fillRect(x, Y, WIDTH, HEIGHT);
            }
            if(playerID==2){
                g.fillRect(Y, x, HEIGHT ,WIDTH);
            }
            if(playerID==3){
                g.fillRect(Y, x, HEIGHT ,WIDTH);
            }              
        }

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -parent.paddlespeed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = parent.paddlespeed;
	}

	public Rectangle getBounds() {
            if(playerID<2){
		return new Rectangle(x, Y, WIDTH, HEIGHT);
            }
            else{
                return new Rectangle(Y, x ,HEIGHT, WIDTH);
            }
	}

	public int getTopY() {
            if(playerID==0){
		return Y;
            }
            if(playerID==1){
		return Y+HEIGHT;
            }
            if(playerID==2){
		return Y+HEIGHT;
            }
            if(playerID==3){
		return Y;
            }
            return 0;
	}
        public int getScore(){
            return score;
        }
        public int incrementScore(int k){
            return score+=k;
        }
}
