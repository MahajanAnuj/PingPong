/*
 * Implements balls
 */
package CSconnect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class Ball {
        public Sprites parent; 
        private Color color;
        private Point location;
        private Dimension size;
        private Point speed;
        
        public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
        }
        public Ball(Color color,Sprites parent) {
            this.parent=parent;
            setColor(color);

            speed = new Point(10 - random(20), 10 - random(20));
            size = new Dimension(30, 30);

        }

        public Dimension getSize() {
            return size;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setLocation(Point location) {
            this.location = location;
        }

        public Color getColor() {
            return color;
        }

        public Point getLocation() {
            return location;
        }

        public Point getSpeed() {
            return speed;
        }

        public void setSpeed(Point speed) {
            this.speed = speed;
        }
        public void incrementSpeed(int k){
            if(speed.x>0){
                speed.x=speed.x+k;
            }
            else{
                speed.x=speed.x-k;
            }
            if(speed.y>0){
                speed.y=speed.y+k;
            }
            else{
                speed.y=speed.y-k;
            }
        }
        protected void paint(Graphics2D g2d) {

            Point p = getLocation();
            if (p != null) {
                g2d.setColor(getColor());
                Dimension size = getSize();
                g2d.fillOval(p.x, p.y, size.width, size.height);
            }

        }
    }