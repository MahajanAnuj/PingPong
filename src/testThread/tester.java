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
public class tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       mythread t1= new mythread(5, 8);
       mythread t2= new mythread(3, 3);
       mythread t3= new mythread(7, 11);
       String s="/10.237#/11.18#/13.4";
       s=s.replace("*", "");
       String[] k;
       k=s.split("/");
       
        System.out.println(s);
         System.out.println(k[1]);
       t1.start();
       t2.start();
       t3.start();
        System.out.println("I am an idependent thread");
        int z=100;
        int x=90;
        System.out.println(""+(int)Math.signum(x-z));
        int i=10;
        for (;i<15;i++){
            System.out.println(""+i);}
    
      }
    
}
