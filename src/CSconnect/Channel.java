/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anuj
 */
public class Channel extends Thread{
    Communicator parent;
    Socket socket;
    DataInputStream din;
    DataOutputStream dout;
    int ID;//player ID
    //implement communication logic here
    public Channel(Socket socket,int ID,Communicator parent) {
        try {
            this.socket=socket;
            this.ID=ID;
            this.parent=parent;
            din=new DataInputStream(socket.getInputStream());
            dout=new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @Override
    public void run() {
        while(true){
            try {
                //write self paddle position
                String send="Player"+"#"+ID+"#"+"X"+"#"+parent.parent.parent.racquets.get(ID).x+"#"+"Balls";
                for(Ball ball :parent.parent.parent.getBalls()){
                    send=send+"#"+ball.getLocation().x+"#"+ball.getLocation().y;
                }
                dout.writeUTF(send);
                //reead position of paddle at othr end
                String message= din.readUTF();
                process(message);
            } catch (IOException ex) {
                Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                }
        }
    }
    //can move this elsewhere
    public void process(String message){
        System.out.println(message);
        String[] tokens =message.split("#");
        int paddle=Integer.parseInt(tokens[1]);
        int x=Integer.parseInt(tokens[3]);
        parent.parent.parent.racquets.get(paddle).setX(x);
        int i=5;
        /*
        for(Ball ball :parent.parent.parent.getBalls()){
            ball.location.x=Integer.parseInt(tokens[i]);
            ball.location.y=Integer.parseInt(tokens[i+1]);
            i=i+2;
        }
        */
    }
    
}
