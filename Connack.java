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
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.paho.broker.mqttv3.MqttExceptions.MqttException;

/**
 *
 * @author ARUN YADAV
 */
public class Connack extends Ack 
  {     
     QueueOutputStream qs=null;
    private byte returncode;
    public static final String KEY = "Con";
    private int returnCode;
    private boolean sessionPresent;
	
     public Connack(byte info, byte[] variableHeader,QueueOutputStream qs) throws IOException 
        {
		super(WireMessage.MESSAGE_TYPE_CONNACK);
                this.qs=qs;
		ByteArrayInputStream bais = new ByteArrayInputStream(variableHeader);
		DataInputStream dis = new DataInputStream(bais);
		sessionPresent = (dis.readUnsignedByte() & 0x01) == 0x01;
		returnCode = dis.readUnsignedByte();
		dis.close();
           }
        
     public Connack(){}
     public Connack(String protocol_name,int protocol_version,byte connect_flags)
     {
            super(WireMessage.MESSAGE_TYPE_CONNACK);
             if(protocol_version==3){
                if(protocol_name=="MQIsdp"){returncode &=(0x00);}
                        else{returncode |=(0x01);}
                         }
                else if(protocol_version==4){
                           if(protocol_name=="MQTT") {returncode &=(0x00);}
                           else {returncode |=(0x01);}           
                      }
                
                if((connect_flags & 0x80)==1 )
                {
                    if((connect_flags & 0x40)==1)
                    {
                       returncode &=(0x00); 
                    }
                   else returncode|=(0x04);
                }
            //Connack con=new Connack();
               
        
        }
        
        
        
        
       
	public int getReturnCode() 
        {
		return returncode;
	}

	protected byte[] getVariableHeader() //throws MqttException 
        {
		 byte byte3=0;
                try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
		       
			dos.write(byte3);
                       
                       // dos.write((returncode)&0xff);
			dos.write((0)&0xff);
                        dos.flush();
			return baos.toByteArray();
		} catch(IOException ioe) {
		    new MqttException(ioe);
                       return null;
                }
	}
	
                
                
                
		
	
	/**
	 * Returns whether or not this message needs to include a message ID.
	 */
	public boolean isMessageIdRequired() {
		return false;
	}
	
	public String getKey() {
		return KEY;
	}
	
	public String toString() {
        return super.toString() + " session present:" + sessionPresent + " return code: " + returnCode;
	}
	
	public boolean getSessionPresent() {
		return sessionPresent;
	}
    
}
