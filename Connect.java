/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.paho.broker.mqttv3.MqttExceptions.MqttException;

/**
 *
 * @author ARUN YADAV
 */
public class Connect extends WireMessage 
{
      public  QueueOutputStream qs=null;
 public static final String KEY = "Con";
         static byte byte4=0;       
 
	private String clientId;
	private boolean cleanSession;
	private Message willMessage;
	private String userName;
	private char[] password;
	private int keepAliveInterval;
	private String willDestination;
	private int MqttVersion;
	
        
     //.............   
        
        int protocol_version ; 
        byte connect_flags ;
        String protocol_name;
	/**
	 * Constructor for an on the wire MQTT connect message
	 * 
	 * @param info The info byte
	 * @param data the data byte array
	 * @throws IOException thrown if an exception occurs when reading the input streams
	 * @throws MqttException thrown if an exception occurs when decoding UTF-8
	 */
	public Connect(byte info, byte[] data,QueueOutputStream qs) throws IOException {
		super(WireMessage.MESSAGE_TYPE_CONNECT);
                this.qs=qs;
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
                protocol_name = decodeUTF8(dis);
		protocol_version = dis.readByte();
		connect_flags = dis.readByte();
		keepAliveInterval = dis.readUnsignedShort();
		clientId = decodeUTF8(dis);
		dis.close();
                
               StaticClasses.Clients.put(clientId,new Date());
           System.out.println("new client is  : "+clientId);
                
             SubmitRespo();
	}

        public void SubmitRespo()
        {
            
           try {
                qs.SubmitInQueue(new Connack( protocol_name, protocol_version, connect_flags));
        } catch (InterruptedException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
           
        }
          
           
       /* try {
            qs.SubmitInQueue(con);
        } catch (InterruptedException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
           
        }*/
                    
        }
        
        
        
        
        
        
        
        
        
        
        
       protected byte[] getVariableHeader(){return null;} 
        protected byte getMessageInfo() {
		return (byte) 0;
	}
        
        
      /*  
	public Connect(String clientId, int MqttVersion, boolean cleanSession, 
                       int keepAliveInterval, String userName, char[] password,
                       Message willMessage, String willDestination) 
           {
		super(WireMessage.MESSAGE_TYPE_CONNECT);
		this.clientId = clientId;
		this.cleanSession = cleanSession;
		this.keepAliveInterval = keepAliveInterval;
		this.userName = userName;
		this.password = password;
		this.willMessage = willMessage;
		this.willDestination = willDestination;
		this.MqttVersion = MqttVersion;
	   }

	public String toString() {
		String rc = super.toString();
		rc += " clientId " + clientId + " keepAliveInterval " + keepAliveInterval;
		return rc;
	}
	
	protected byte getMessageInfo() {
		return (byte) 0;
	}

	public boolean isCleanSession() {
		return cleanSession;
	}
	
	protected byte[] getVariableHeader() //throws MqttException 
        {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			
			if (MqttVersion == 3) {
				encodeUTF8(dos,"MQIsdp");			
			}
			else if (MqttVersion == 4) {
				encodeUTF8(dos,"MQTT");			
			}
			dos.write(MqttVersion);

			byte connectFlags = 0;
			
			if (cleanSession) {
				connectFlags |= 0x02;
			}
			
			if (willMessage != null ) {
				connectFlags |= 0x04;
				connectFlags |= (willMessage.getQos()<<3);
				if (willMessage.isRetained()) {
					connectFlags |= 0x20;
				}
			}
			
			if (userName != null) {
				connectFlags |= 0x80;
				if (password != null) {
					connectFlags |= 0x40;
				}
			}
			dos.write(connectFlags);
			dos.writeShort(keepAliveInterval);
			dos.flush();
			return baos.toByteArray();
		} catch(IOException ioe) {
		    new MqttException(ioe);
                       return null;
                }
	}
	
	public byte[] getPayload() //throws MqttException 
        {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			encodeUTF8(dos,clientId);
			
			if (willMessage != null) {
				encodeUTF8(dos,willDestination);
				dos.writeShort(willMessage.getPayload().length);
				dos.write(willMessage.getPayload());
			}
			
			if (userName != null) {
				encodeUTF8(dos,userName);
				if (password != null) {
					encodeUTF8(dos,new String(password));
				}
			}
			dos.flush();
			return baos.toByteArray();
		} catch (IOException ex) {
			new MqttException(ex);
                        return null;
		}
	}
	*/
	/**
	 * Returns whether or not this message needs to include a message ID.
	 */
	public boolean isMessageIdRequired() {
		return false;
	}
	
	public String getKey() {
		return KEY;
	}
}
