/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anuj
 */
public class debugger {
    static int playerID=1;

    /**
     * @param args the command line arguments
     */
    public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
    public static void main(String[] args) {
        try {
            System.out.println("Connecting debugger for communication");
            Socket socket=new  Socket("10.237.23.150",5555);
            DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
            int i=1;
            while(true){
                Thread.sleep(1000);
                String send="Player"+"#"+playerID+"#"+"X"+"#"+random(300)+"#";
                dout.writeUTF(send);
                System.out.println(send+" Sent "+i );
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(debugger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(debugger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
