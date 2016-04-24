/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;
import java.io.*;
import java.net.*;
import javafx.scene.paint.Color;
/**
 * @author anuj
 */
public class Simpleserver {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
       int i=1;
   try{
       
    System.out.println("Server Ip is "+InetAddress.getByName("127.0.0.1"));   
    ServerSocket myServerSocket=new ServerSocket(62372, 2, InetAddress.getByName("127.0.0.1"));
    //myServerSocket.setSoTimeout(100000);
    while (true) {               
    System.out.println("Server Waiting on host = "+ InetAddress.getLocalHost().getCanonicalHostName()+ " port = "+myServerSocket.getLocalPort());
    Socket skt = myServerSocket.accept();
    System.out.println("Connected to client at" + skt.getLocalAddress());
        System.out.println("This is client no "+i);
    //BufferedReader myInput= new BufferedReader(new InputStreamReader(skt.getInputStream()));
    //PrintStream myOutput= new PrintStream(skt.getOutputStream());
    //DataInputStream din=new DataInputStream(skt.getInputStream());
    //DataOutputStream dout=new DataOutputStream(skt.getOutputStream());
    //read
    //String buf= myInput.readLine();
    //String buf;
    //System.out.println(buf);
    //while((buf=din.readUTF()) !=null){
     //   System.out.println("Server read "+ buf);
      //  dout.writeUTF("got it");
       // if(buf.equals("exit")){
         //       dout.writeUTF("exit");
          //      System.out.println("Server exiting");
            //   skt.close();
            //}
        //myOutput.print("got it");
    skt.close();
        System.out.println("Terminated from client no "+i);
        i++;
    }
    }
   catch(IOException ex){
      // ex.printStackTrace();
    }}
    }

