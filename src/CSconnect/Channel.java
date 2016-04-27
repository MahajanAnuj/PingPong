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
    long numCycles;
    Socket socket;
    DataInputStream din;
    DataOutputStream dout;
    String p2pIPString;
    Boolean State;
    int ID;//connection queue ID caution not player ID
    //implement communication logic here
    public Channel(Socket socket,int ID,Communicator parent) {
        try {
            this.socket=socket;
            this.ID=ID;
            this.parent=parent;
            din=new DataInputStream(socket.getInputStream());
            dout=new DataOutputStream(socket.getOutputStream());
            numCycles=1;
            State=false;
            p2pIPString="dummy";
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @Override
    public void run() {
        try {
            dout.writeUTF("hello");
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){            
            //System.out.println("Channel Started");
            try {                
                //write self paddle position
                String message= din.readUTF();
                process(message);
                String send="Player"+"#"+parent.parent.parent.r2c+"#"+"X"+"#"+parent.parent.parent.racquets.get(parent.parent.parent.r2c).x+"#"+"Balls";
                for(Ball ball :parent.parent.parent.getBalls()){
                    send=send+"#"+ball.getLocation().x+"#"+ball.getLocation().y+"#"+ball.speed.x+"#"+ball.speed.y;
                }
                System.out.println(send+" Sent");
                dout.writeUTF(send);
                if(State){
                    if(!p2pIPString.equals("dummy"))
                    {
                        dout.writeUTF(p2pIPString);
                        //sent now no need to send again
                        State=false;
                    }
                }
                
                //reead position of paddle at other end
            } catch (IOException ex) {
                Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            }
            //catch(NullPointerException ex){}
            try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                }
        }
    }
    //can move this elsewhere
    public void process(String message){
        System.out.println(message+" Received");
        String[] tokens =message.split("#");
        if (tokens.length>4){
            int paddle=Integer.parseInt(tokens[1]);
            int x=Integer.parseInt(tokens[3]);
            int i=5;
            parent.parent.parent.racquets.get(paddle).setX(x);
            //if((paddle==1))
            {
                for(Ball ball :parent.parent.parent.getBalls()){
                    ball.location.x=Integer.parseInt(tokens[i]);
                    ball.location.y=Integer.parseInt(tokens[i+1]);
                    ball.speed.x=Integer.parseInt(tokens[i+2]);
                    ball.speed.y=Integer.parseInt(tokens[i+3]);
                    i=i+4;
            }    
            }
            numCycles++;
        }
        if(tokens.length==4){//IP exchange
            parent.IPs=tokens;
        }
    }
    public void SendIPs(String IPs){
        try {
            dout.writeUTF(IPs);
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
