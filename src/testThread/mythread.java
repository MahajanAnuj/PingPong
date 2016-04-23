/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testThread;

/**
 *
 * @author anuj
 */
public class mythread extends Thread{
    int interval;
    int repeats;
    
    public mythread(int interval,int repeats) {
        this.interval=interval;
        this.repeats=repeats;
    }
    @Override
    public void run(){
       for (int i=0;i<repeats;i++){ 
       try  {
         Thread.sleep(interval*1000);
         System.out.println("Thread "+ interval +" woke up!");//1000 milliseconds is one second.
            } 
       catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
            }
    }
    }
    
}
