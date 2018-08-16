/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;

import org.paho.broker.mqttv3.MqttExceptions.MqttException;




import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author ARUN YADAV
 */
public class PubComp extends Ack {
	public PubComp(byte info, byte[] data) throws IOException {
		super(WireMessage.MESSAGE_TYPE_PUBCOMP);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		dis.close();
	}
	
	public PubComp(Publish publish) {
		super(WireMessage.MESSAGE_TYPE_PUBCOMP);
		//this.msgId = publish.getMessageId();
	}
	
	public PubComp(int msgId) {
		super(WireMessage.MESSAGE_TYPE_PUBCOMP);
		this.msgId = msgId;
	}
	
	protected byte[] getVariableHeader()// throws MqttException 
        {
		return encodeMessageId();
	}
}
