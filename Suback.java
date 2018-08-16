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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ARUN YADAV
 */
public class Suback extends Ack 
  {
	private int[] grantedQos;
        int msgId;
	
	public Suback(byte info, byte[] data) throws IOException {
		super(WireMessage.MESSAGE_TYPE_SUBACK);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		int index = 0;
		grantedQos = new int[data.length-2];
		int qos = dis.read();
		while (qos != -1) {
			grantedQos[index] = qos;
			index++;
			qos = dis.read();
		}
		dis.close();
                
	}
        
        public Suback(int msgId)
        {
           super(WireMessage.MESSAGE_TYPE_SUBACK); 
           this.msgId=msgId;
        }
        
	
	protected byte[] getVariableHeader() //throws MqttException
        {     byte byte1=0x03;
            
            try 
            {
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
           // byte1=(byte) (byte1 & 0xff);
             //dos.write(byte1);
             
                  //byte1=(byte) (byte1&0x00);
                  dos.writeShort(msgId);
              
                 // byte1=(byte) (byte1|0x0a);
                  //dos.write(byte1);
                  dos.flush();
            return baos.toByteArray();
            } catch (IOException ex) {
                //Logger.getLogger(Suback.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
		
                
	}
        public byte[] getPayload()
        {   
            byte byte1=0x00;
            try //throws MqttException
            {
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            byte1=(byte) (byte1 & 0x00);
          
            dos.write(byte1);
            dos.flush();
            return baos.toByteArray();
            } catch (IOException ex) {
                //Logger.getLogger(Suback.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
        }
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString()).append(" granted Qos");
		for (int i = 0; i < grantedQos.length; ++i) {
			sb.append(" ").append(grantedQos[i]);
		}
		return sb.toString();
	}
	
	public int[] getGrantedQos() {
		return grantedQos;
	}
	
}
