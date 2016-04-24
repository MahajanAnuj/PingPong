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
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	public static final int Y = 340;
	public static final int WIDTH = 60;
	public static final int HEIGHT = 10;
        //public int speed;
	int x;
	int xa = 0;
	private Sprites parent;

	public Racquet(Sprites parent) {
		this.parent = parent;
                //change for multiplayer
                x=parent.getWidth()/2-WIDTH/2;
	}

	public void move() {
		if (x + xa > 0 && x + xa < parent.getWidth() - WIDTH)
			x = x + xa;
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, Y, WIDTH, HEIGHT);
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
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return Y - HEIGHT;
	}
}
