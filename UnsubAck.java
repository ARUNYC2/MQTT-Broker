/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author ARUN YADAV
 */
public class UnsubAck extends Ack 
{
	
    
    private int MsgId;
    
	public UnsubAck(byte info, byte[] data) throws IOException 
        {
		super(WireMessage.MESSAGE_TYPE_UNSUBACK);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		dis.close();
	}
	
        
        
        
        public UnsubAck(int msgID)
        {super(WireMessage.MESSAGE_TYPE_UNSUBACK);
           this.MsgId=msgID ;
          }
	
        protected byte[] getVariableHeader() //throws MqttException 
        {
		
                try{
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
		       
			 dos.writeShort(MsgId);
                         dos.flush();  
                         return baos.toByteArray();
                 }
                catch(IOException ex)
                {
                    System.out.println("  unsubACK IOEX ");
                    return null;
                }
          }
  }
