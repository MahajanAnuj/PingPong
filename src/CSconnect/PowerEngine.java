/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

/**
 *
 * @author anuj
 */
//public class PowerEngine implements Runnable {
public class PowerEngine{
    public Sprites parent;
    
    public PowerEngine(Sprites parent) {
        this.parent=parent;
    }
    public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
    //@Override
    public void run() {
        while(parent.isVisible()){
            powerUP();
            try {
                Thread.sleep(60);
            } catch (InterruptedException ex) {
            }
        }
       }
    //randomly changes game state to make it more fun  :)
    public void powerUP(){
        if(Math.random()<0.001){
           int powerToGive=random(2);
           switch(powerToGive){
           //increase paddle speed    
           case 0:
               parent.paddlespeed++;
               break;
           //Score multipier    
           case 1:
               parent.bengine.ScoreMultiplier++;
               break;
           //slow down the balls    
           //case 2:
           //    int b=random(parent.numballs-1);
           //    parent.getBalls().get(b).decrementSpeed(2);
           //    break;
           //enlarge paddle    
           case 2:
               int r= random(parent.numracquets-1);
               parent.racquets.get(r).WIDTH=(int)Math.round(parent.racquets.get(r).WIDTH*1.5);
           }
        }
    }
}
