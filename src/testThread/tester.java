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
       t1.start();
       t2.start();
       t3.start();
      }
    
}
