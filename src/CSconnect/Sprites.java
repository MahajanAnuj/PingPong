/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author anuj
 */
 public class Sprites extends JPanel {
        private List<Ball> ballsUp;
        public int numballs;
        public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
        }
        public Sprites(int numballs) {
            ballsUp = new ArrayList<Ball>(25);
            this.numballs=numballs;
            for (int index = 0; index < numballs; index++) {
                ballsUp.add(new Ball(new Color(random(255), random(255), random(255)),this));
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (Ball ball : ballsUp) {
                ball.paint(g2d);
            }
            g2d.dispose();
        }

        public List<Ball> getBalls() {
            return ballsUp;
        }

    }
 