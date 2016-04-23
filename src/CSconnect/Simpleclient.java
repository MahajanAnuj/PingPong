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
       String host="127.0.0.1";
     int port=55653;
     try{
         System.out.println("Connecting host at host = "+ host+ " port = "+port);
         Socket skt=new  Socket(host, port);
         //BufferedReader myInput= new BufferedReader(new InputStreamReader(skt.getInputStream()));
         //PrintStream myOutput= new PrintStream(skt.getOutputStream());
         DataInputStream din=new DataInputStream(skt.getInputStream());
         DataOutputStream dout=new DataOutputStream(skt.getOutputStream());
         //myOutput.print("Hello Server");
         dout.writeUTF("Hello Server");
         //String buf= myInput.readLine();
         String buf=din.readUTF();
         if(buf !=null){
            System.out.println("Server said "+ buf);
            }
         skt.close();
         System.out.println("client exiting");
     }
     catch(IOException ex){
         ex.printStackTrace();
     }
    }
    
}
