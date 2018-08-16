/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;


import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.paho.broker.mqttv3.MqttExceptions.MqttException;

/**
 *
 * @author ARUN YADAV
 */
public class Publish extends WireMessage {
        private Message message;
	private String topicName;
	String Message;
       // String Topic;
	private byte[] encodedPayload = null;
	
	/*public Publish(String name, String message) {
		super(WireMessage.MESSAGE_TYPE_PUBLISH);
		topicName = name;
		Message = message;
	}*/
	
	/**
	 * Constructs a new MqttPublish object.
	 * @param info the message info byte
	 * @param data the variable header and payload bytes
	 * @throws MqttException if an exception occurs creating the publish
	 * @throws IOException if an exception occurs creating the publish
	 */
	public Publish(byte info, byte[] data) throws  IOException  {
		super(WireMessage.MESSAGE_TYPE_PUBLISH);
                boolean checkRetain=false;
		message = new ReceivedMessage();
		message.setQos((info >> 1) & 0x03);
		if ((info & 0x01) == 0x01) {
			message.setRetained(true);
                        checkRetain=true;
                    System.out.println("Retained Msg");   
		}
		if ((info & 0x08) == 0x08) {
			((ReceivedMessage) message).setDuplicate(true);
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		CountingInputStream counter = new CountingInputStream(bais);
		DataInputStream dis = new DataInputStream(counter);
		topicName = decodeUTF8(dis);
		if (message.getQos() > 0) {
			msgId = dis.readUnsignedShort();
		}
		byte[] payload = new byte[data.length-counter.getCounter()];
		dis.readFully(payload);
		dis.close();
		   message.setPayload(payload);
                //  submitRespo(topicName,message.toString());
                if(checkRetain==true)
                    StaticClasses.RetainMsg.put(topicName, new String(message.toString()));
                System.out.println("Topic name = "+topicName);
                System.out.println("Message = "+message.toString());
	}
        
        
        
   
            
           
            
        
        
       /* public void submitRespo(String topic,String Message)
        {
            try {
                //this.Topic=topic;
                //this.Message=Messag;
                qs.SubmitInQueue(new Publish(topic,Message));
            } catch (InterruptedException ex) {
                Logger.getLogger(Publish.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      */        

	/*public String toString() {

		// Convert the first few bytes of the payload into a hex string
		StringBuffer hex = new StringBuffer();
		byte[] payload = message.getPayload();
		int limit = Math.min(payload.length, 20);
		for (int i = 0; i < limit; i++) {
			byte b = payload[i];
			String ch = Integer.toHexString(b);
			if (ch.length() == 1) {
				ch = "0" + ch;
			}
			hex.append(ch);
		}

		// It will not always be possible to convert the binary payload into
		// characters, but never-the-less we attempt to do this as it is often
		// useful
		String string = null;
		try {
			string = new String(payload, 0, limit, "UTF-8");
		} catch (Exception e) {
			string = "?";
		}

		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append(" qos:").append(message.getQos());
		if (message.getQos() > 0) {
			sb.append(" msgId:").append(msgId);
		}
		sb.append(" retained:").append(message.isRetained());
		sb.append(" dup:").append(duplicate);
		sb.append(" topic:\"").append(topicName).append("\"");
		sb.append(" payload:[hex:").append(hex);
		sb.append(" utf8:\"").append(string).append("\"");
		sb.append(" length:").append(payload.length).append("]");

		return sb.toString();
	}*/
	
	protected byte getMessageInfo() {
		byte info = (byte) (message.getQos() << 1);
		if (message.isRetained()) {
			info |= 0x01;
		}
		if (message.isDuplicate() || duplicate ) {
			info |= 0x08;
		}
		//byte info=0x00;
		return info;
	}
	
	public String getTopicName() {
		return this.topicName;
	}
	public String GetTopic()
        {
            return this.topicName;
        }
	public Message getMessage() {
		return message;
	}
	
	protected  byte[] encodePayload(Message message)
        {
		return message.getPayload();
	}

	public byte[] getPayload() //throws MqttException 
        {
		/*if (encodedPayload == null) {
			encodedPayload = encodePayload(message);
		}
		return encodedPayload;
                */
                 try{
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			  DataOutputStream dos = new DataOutputStream(baos);
			   encode_UTF8(dos,new String(message.getPayload()));
                            dos.flush();
                        return baos.toByteArray();
                        
                    }
             catch(IOException ex)
             { new MqttException("Class Name :MqttPublish ,getPayload ");
                 //System.out.println(" IOEx payload ");
                 return null;
             }
        
                
	}
         private void encode_UTF8(DataOutputStream dos, String stringToEncode)// throws MqttException
	{
		try {

			byte[] encodedString = stringToEncode.getBytes("UTF-8");
			  dos.write(encodedString);
		}
		catch(UnsupportedEncodingException ex)
		{   
			 new MqttException("\nUnsupportedEncodingException ");
		} catch (IOException ex) {
			//throw new MqttException(ex);
                         new MqttException("ClassName :: MqttPublish ,encodeUTF8");  
                        
		}
	}
    

	/*public int getPayloadLength() {
		       int length = 0;
		        length = getPayload().length;
                       return length;
	}
	
	public void setMessageId(int msgId) {
		super.setMessageId(msgId);
		if (message instanceof ReceivedMessage) {
			((ReceivedMessage)message).setMessageId(msgId);
		}
	}*/
	
	protected byte[] getVariableHeader()// throws MqttException 
        {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			encodeUTF8(dos, topicName);
			if (message.getQos() > 0) {
				dos.writeShort(msgId);
			}
			dos.flush();
			return baos.toByteArray();
		} catch (IOException ex) {
			 new MqttException(ex);
                         return null;
		}
	}
	
	public boolean isMessageIdRequired() {
		// all publishes require a message ID as it's used as the key to the token store
		return true;
	}
}