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
public class Unsubscribe  extends WireMessage
{
 
    private String[] names;
	private int count;
        Socket sock=null;
        private QueueOutputStream qs=null;
	//String name;
	/*public MqttUnsubscribe(String[] names) {
		super(MqttWireMessage.MESSAGE_TYPE_UNSUBSCRIBE);
		this.names = names;
	}
        public Unsubscribe(String name) {
		super(WireMessage.MESSAGE_TYPE_UNSUBSCRIBE);
		this.name = name;
	}*/
	
	
	public Unsubscribe(byte info, byte[] data,Socket sock,QueueOutputStream qs) throws IOException {
		super(WireMessage.MESSAGE_TYPE_UNSUBSCRIBE);
		this.sock=sock;
                this.qs=qs;
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
               
		count = 0;
		names = new String[10];
		boolean end = false;
		/*while (!end) {
			try {
				names[count] = decodeUTF8(dis);
                                System.out.println("unsubscribe topic :: "+names[count]);
                                
			} catch (Exception e) {
				end = true;
			}}*/
                names[count] = decodeUTF8(dis);
	         System.out.println("unsubscribe topic :: "+names[count]);
                
                remove();
                submitRespo();
		dis.close();
	}
     private void remove()
     {
        try {
            System.out.println("in remove unsubscribe topic : "+names[0]);
            StaticClasses.TopicClientsMap.get(names[0]).remove(sock.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Unsubscribe.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
     }
     private void submitRespo()
     {
        try {
            qs.SubmitInQueue(new UnsubAck(msgId));
        } catch (InterruptedException ex) {
            Logger.getLogger(Unsubscribe.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
	
	/*public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append(" names:[");
		for (int i = 0; i < count; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append("\"" + names[i] + "\"");
		}
		sb.append("]");
		return sb.toString();
	}*/

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
                     System.out.println("IOex Unsubcribe get var");
			//throw new MqttException(ex);
                        return null;
		}
	}

	public byte[] getPayload() //throws MqttException 
        {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			//for (int i=0; i<names.length; i++) {
				encodeUTF8(dos, names[0]);
			//}
			dos.flush();
			return baos.toByteArray();
		} catch (IOException ex) {
			System.out.println("IOex Unsubcribe getPayload");
			//throw new MqttException(ex);
                        //getPayload();
                        return null;
		}
	}

	public boolean isRetryable() {
		return true;
	}
    
    
    
    
    
}
