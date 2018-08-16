/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;

import org.paho.broker.mqttv3.MqttExceptions.MqttException;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ARUN YADAV
 */

public class Subscribe extends WireMessage
{
    
       private  QueueOutputStream qs=null;
        private String[] names;
	private int[] qos;
	private int count;
        private Socket sock=null;
	/**
	 * Constructor for an on the wire MQTT subscribe message
	 * 
	 * @param info the info byte
	 * @param data the data byte array
	 * @throws IOException if an exception occurs whilst reading the input stream
	 */
	public Subscribe(byte info, byte[] data,Socket sock,QueueOutputStream qs) throws IOException {
		super(WireMessage.MESSAGE_TYPE_SUBSCRIBE);
                  this.sock=sock;
                  this.qs=qs;
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
                 
		count = 0;
		names = new String[10];
		qos = new int[10];
		/*boolean end = false;
		while (!end) {
			try {
				names[count] = decodeUTF8(dis);
				qos[count++] = dis.readByte();
			} catch (Exception e) {
				end = true;
			}
		}*/
                        names[count] = decodeUTF8(dis);
	                qos[count] = dis.readByte();
                         dis.close();
                
               
                store();
                submitRespo();
                 
	}
        
        
        
        
        
        public void store()
        {
            
          // StaticClasses.TopicClientsMap.put(names[0],sock.getOutputStream());
        // synchronized(this){
               if (StaticClasses.TopicClientsMap.get(names[0]) == null) { //gets the value in key topic)
                   StaticClasses.TopicClientsMap.put(names[0], new ArrayList<OutputStream>());
                  }
               
                   try {
                       StaticClasses.TopicClientsMap.get(names[0]).add((sock.getOutputStream())) ;
                   } catch (IOException ex) {
                       Logger.getLogger(Subscribe.class.getName()).log(Level.SEVERE, null, ex);
                   }
                  
            //   }
        }

        
        public void submitRespo()
        {
           try {
               //System.out.println("submitRespo here");
               qs.SubmitInQueue(new Suback(msgId));
           } catch (InterruptedException ex) {
               Logger.getLogger(Subscribe.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("InterrExcep submitRespo subscribe");
           }
            
        }
        
        
        
        
        
	/**
	 * Constructor for an on the wire MQTT subscribe message
	 * @param names - one or more topics to subscribe to 
	 * @param qos - the max QoS that each each topic will be subscribed at 
	 */
	public Subscribe(String[] names, int[] qos) {
		super(WireMessage.MESSAGE_TYPE_SUBSCRIBE);
		this.names = names;
		this.qos = qos;
		
		if (names.length != qos.length) {
		throw new IllegalArgumentException();
		}
		this.count = names.length;
		
		for (int i=0;i<qos.length;i++) {
			Message.validateQos(qos[i]);
		}
	}

	/**
	 * @return string representation of this subscribe packet
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append(" names:[");
		for (int i = 0; i < count; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append("\"").append(names[i]).append("\"");
		}
		sb.append("] qos:[");
		for (int i = 0; i < count; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append(qos[i]);
		}
		sb.append("]");

		return sb.toString();
	}
	
	protected byte getMessageInfo() {
		return (byte) (2 | (duplicate ? 8 : 0));
	}
	
	protected byte[] getVariableHeader() //throws MqttException 
        {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			dos.writeShort(msgId);
			dos.flush();
			return baos.toByteArray();
		} catch (IOException ex) {
			//throw new MqttException(ex);
                        
                      new MqttException("subscribe IOEx");  
                      return null;
		}
	}
	
	public byte[] getPayload() //throws MqttException 
        {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			for (int i=0; i<names.length; i++) {
				encodeUTF8(dos,names[i]);
				dos.writeByte(qos[i]);
			}
			dos.flush();
			return baos.toByteArray();
		} catch (IOException ex) {
			 new MqttException(ex); return null;
		}
	}
	
	public boolean isRetryable() {
		return true;
	}
}
