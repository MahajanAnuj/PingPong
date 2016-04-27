/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anuj
 */
public class Communicator {
    public BounceEngine parent;
    public int port=5555;
    public ArrayList<Channel> connections;
    public String[] IPs;
    public Communicator(BounceEngine parent){
        this.parent=parent;
        connections=new ArrayList<>();
        port=parent.parent.r2c+5555;
        connect();
    }
    public void connect(){
        //complete connectivity tbd
        ServerSocket serverSocket;
        try {
        serverSocket = new ServerSocket(port);
        System.out.println("Server up and listenning");
        int i=parent.parent.r2c+1;
        int numCon=parent.parent.numracquets;
        Boolean isStarter=(parent.parent.r2c==0);
        //connect to player 0 first
        if(!isStarter){
            Socket socket=new  Socket("10.237.23.150",5555);//IP of player 0
            Channel channel=new Channel(socket,0,this);
            connections.add(channel);
            channel.start();
            Thread.sleep(1000);
        }
        //connect players with greater ID
        for(;i<numCon;i++){//replace limit by playerID-1 
            Socket socket=serverSocket.accept();
            Channel channel=new Channel(socket,i,this);
            connections.add(channel);
            channel.start();
            i++;
        }
        if(isStarter){//tell other IPs to other players
            String p2pHS=""+connections.get(0).socket.getLocalAddress();
            for(Channel c:connections){
                p2pHS=p2pHS+"#"+c.socket.getInetAddress();
            }
            for(Channel c:connections){
              //c.SendIPs(p2pHS);
            }
        }
        //p2p
        System.out.println("Connecting to other players");
        for(i=1;i<parent.parent.r2c;i++){//replace limit by numplayer-PlayerID 
            //Socket socket=new  Socket("10.237.23.150",5555+i);
            Socket socket=new  Socket(IPs[i],5555+i);
            Channel channel=new Channel(socket,i,this);
            connections.add(channel);
            channel.start();
            i++;
            //Thread.sleep(1000);
        }
        } catch (IOException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void BroadcastMessage(int m ){
        for(Channel channel:connections){
            //write in channels
        }
    }
    public void handleMessage(){
        //handle incoming messages
    }
}
