/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.paho.broker.mqttv3.MqttServerUiMain.MQTTBrokerUiMain;



/**
 *
 * @author ARUN YADAV
 */
public class ServerSocketHandler extends Thread 
{
   
                 Socket socket=null;
                 ServerSocket ss=null; 
    
    public ServerSocketHandler()
    {
        
        try {
               ss=new ServerSocket(1883);
              } 
        catch (IOException ex) {
            Logger.getLogger(ServerSocketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
  public void run()
    {    
      try
        {
           while(true)
           {
                if(ss.isClosed()==false && MQTTBrokerUiMain.sclose==true)
                {
                    ss.close();
                    break;
                }
                 socket= ss.accept();
                 //ClientsStream.add(socket.getOutputStream());
             
             
                 Thread t=new Thread(new SocketRead(socket));
                        t.start();
                 System.out.println("accept");
           }
         }
         catch(IOException ex)
         {
             System.out.println(" socket error");
         }
      }
 }
