/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;
import org.paho.broker.mqttv3.MqttExceptions.MqttException;



import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author ARUN YADAV
 */
public class SocketWrite  extends Thread
{
    
              Create_wire cw=null;
              QueueOutputStream qs=null;
              WireMessage mq=null;
             //Vector<WireMessage> VM=null;
              DataOutputStream dos=null;
     public SocketWrite(QueueOutputStream qs,Socket socket)
          {   
          try
           {
             dos = new DataOutputStream(socket.getOutputStream());
             }
           catch(IOException ex)
           {
                System.out.println(" IOEx in Socket Write "); 
                new MqttException(ex);  
             // System.out.println(" IOEx in Socket Write "); 
             }
          
              //cw=new Create_wire();
              this.qs=qs;
          
             // this.VM= qs1.VM;
        }
     
    public  WireMessage remove()
         {
           //.........Wait util Queue have at least one element 
            while(true)
              {     if((qs.VM.isEmpty()))
                        continue;
                  else{
                     return qs.VM.remove(0); 
                     }
                  }
         }
   
   
   public void run()
   {
       
       
       
       
       while(true)
       {
          
                mq=remove(); 
       
                System.out.println("Write packet type to client : "+mq.type);
              try{ 
                   if(mq.type==3)
                   {
                      Iterator it;
                      String topicName=mq.GetTopic();
                      System.out.println(" Broadcast Topic_Name msg= "+topicName);
                      it = StaticClasses.TopicClientsMap.get(topicName).iterator();
                       if(it.equals(null))
                            continue;
                        while (it.hasNext()) 
                             {
                               try 
                                  {
                                     dos = new DataOutputStream((OutputStream) it.next());
                                      System.out.println(Arrays.toString(mq.getHeader()));
                                       System.out.println(Arrays.toString(mq.getPayload()));
	                               dos.write(mq.getHeader(),0,mq.getHeader().length);
                                        dos.write(mq.getPayload(),0,mq.getPayload().length);
                                          dos.flush();
                                   }
                                catch(IOException ex)
                                 {
                                  System.out.println("SockeWrite ,Run while it ");
                                 }
                               }
                     }       
               else{
              
              
              
                      System.out.println(Arrays.toString(mq.getHeader()));
                      System.out.println(Arrays.toString(mq.getPayload()));
                      dos.write(mq.getHeader(),0,mq.getHeader().length);
                      dos.write(mq.getPayload(),0,mq.getPayload().length);
                      dos.flush();
                    }
                  }
            catch(IOException ex){
                new MqttException("ClassNAme ::SocketWrite ,Run ");  
                break;
               // System.out.println("IOEx SocketWrite in run");
                
                }
             
               }
          
      }
}
