/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;
import org.paho.broker.mqttv3.MqttExceptions.MqttException;




import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ARUN YADAV
 */
public class SocketRead extends Thread
{
            WireMessage wm=null;
            private ServerSocket ss=null;
            private Socket clientSocket=null;
            private int fl=0;
            //public QueueOutputStream qs=new QueueOutputStream() ;
            public Create_wire cw=null;
            public SocketRead(){  }
    
    public SocketRead(Socket socket)
        {
             clientSocket=socket;
             cw=new Create_wire();
           //System.out.println("SocketRead class Construct");
        
     }
    
    public void run()
    {
      while(true)
        {
            try
            {
                //System.out.println("SocketRead class ,Run");
                if(cw.isCon==false){
                       wm=cw.createWireMessage(clientSocket.getInputStream(), clientSocket);  
                     //}
             if(wm==null){
                 //new MqttException(" read NULL value ");
                 System.out.println(" Read NULL value  ,class :SocketRead  ,Run===Socket Closed");
                 break;
                 
                  }
               }}
            catch(IOException ex)
            {
                  System.out.println("IOEx in SocketRead ,Run");
                  break;
              
            }
        }
    }
} 