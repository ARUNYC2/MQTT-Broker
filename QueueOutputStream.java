/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

/**
 *
 * @author ARUN YADAV
 */
public class QueueOutputStream 
{
          private int flag=0;
         //private WireMessage MWM=null;
     
        
     public Vector<WireMessage> VM= new Vector<WireMessage>();
         //int i=0;
    
    public QueueOutputStream() {}
     
   
    
    public void   SubmitInQueue(WireMessage WM) throws InterruptedException
    {
        
             VM.add(WM);
        /* if(flag==0){
          Thread t1=new SubmitPingReq();
           Thread t2=new SocketWrite(socket,VM);
           Thread t3=new SocketRead(socket);
               t1.start();
               t2.start();
               t3.start();
               flag=1;
               }*/
        
         System.out.println(" "+VM.size());
           
    }
     
       
    
 
          
       
   
   
      
       
       
   
     /*  public void Write()
        {  
          try
              {
                dos = new DataOutputStream(socket.getOutputStream());
                System.out.println("hiii");
                  MqttWireMessage mq=null;
                    if(VM.size()>0)
                       mq=remove();
                  
                System.out.println(Arrays.toString(mq.getHeader()));
                System.out.println(Arrays.toString(mq.getPayload()));
            
                dos.write(mq.getHeader(),0,mq.getHeader().length);
                dos.write(mq.getPayload(),0,mq.getPayload().length);
                dos.flush();
              }
        catch(IOException ex)
           {
              System.out.println("IOEx  in Write QueueoutputStream ");
            
           }
                   
        }*/
   
    }


