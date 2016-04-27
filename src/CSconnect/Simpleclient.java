/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSconnect;
import java.io.*;
import java.net.*;
/**
 *
 * @author anuj
 */
public class Simpleclient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     //String host="10.208.23.105";
       String host="10.237.23.150";
     int port=5555;
     try{
         System.out.println("Connecting host at host = "+ host+ " port = "+port);
         Socket skt=new  Socket(host, port);
         //BufferedReader myInput= new BufferedReader(new InputStreamReader(skt.getInputStream()));
         //PrintStream myOutput= new PrintStream(skt.getOutputStream());
         //DataInputStream din=new DataInputStream(skt.getInputStream());
         //DataOutputStream dout=new DataOutputStream(skt.getOutputStream());
         //myOutput.print("Hello Server");
         //for (int i=0;i<10;i++){
         //dout.writeUTF(""+i);
         //}
         //handshake protocol
         //dout.writeUTF("exit");
         //String buf= myInput.readLine();
        // String buf;
         //while((buf=din.readUTF()) !=null){
           // System.out.println("Server said "+ buf);
            //if(buf.equals("exit")){
             //   skt.close();
              //  System.out.println("client exiting");
            //}
            //}
         System.out.println(skt.getLocalPort());
         skt.close();
         System.out.println("Exited Server");
         
         //handle SocketException
     }
     catch(IOException ex){
         //ex.printStackTrace();
     }
    } 
}
