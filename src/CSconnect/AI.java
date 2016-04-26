/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

import java.awt.Point;

/**
 *
 * @author anuj
 */
//public class AI implements Runnable{
public class AI{
    public static final int MAXDISTANCE=10000;
    public int playerID; //to control
    public Sprites parent;
    private int mode;
    AI(int playerID, Sprites parent){
        this.parent=parent;
        this.playerID=playerID;
        mode=0;
    }
    //@Override
    public void run(){
        while(parent.isVisible()){
            parent.bengine.externalMove(directionToMove(), playerID); 
            // Some small delay...
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
            }
        }
    }
    public int directionToMove(){
        int dir=0;
        int temp;
        int dist=MAXDISTANCE;
        Point loc=parent.racquets.get(playerID).location();
        for (Ball ball : parent.getBalls()) {
            if(playerID<2){
                temp=Math.abs(ball.getLocation().y-loc.y);
                if(temp<dist){
                    dist=temp;
                    dir=(int)Math.signum(ball.getLocation().x-loc.x); 
                }
            }
            else{
                temp=Math.abs(ball.getLocation().x-loc.x);
                if(temp<dist){
                    dist=temp;
                    dir=(int)Math.signum(ball.getLocation().y-loc.y); 
                }
            }
            }
        if(dist<parent.racquets.get(playerID).WIDTH/2){
            return 0;
        }
        return dir;
    }
}
