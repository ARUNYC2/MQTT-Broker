/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author ARUN YADAV
 */
public class PubAck extends Ack {
    
	public PubAck(byte info, byte[] data) throws IOException 
        {
		super(WireMessage.MESSAGE_TYPE_PUBACK);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		dis.close();
	}
	
	public PubAck(Publish publish) {
		super(WireMessage.MESSAGE_TYPE_PUBACK);
		//msgId = publish.getMessageId();
	}
	
	public PubAck(int messageId) {
		super(WireMessage.MESSAGE_TYPE_PUBACK);
		msgId = messageId;
	}
	
	protected byte[] getVariableHeader() //throws MqttException 
        {
		return encodeMessageId();
	}
}
