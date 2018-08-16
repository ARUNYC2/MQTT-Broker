/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;


import org.paho.broker.mqttv3.MqttExceptions.MqttException;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 *
 * @author ARUN YADAV
 */
public class PubRec extends Ack {
	public PubRec(byte info, byte[] data) throws IOException {
		super(WireMessage.MESSAGE_TYPE_PUBREC);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		dis.close();
	}
	
	public PubRec(Publish publish) {
		super(WireMessage.MESSAGE_TYPE_PUBREC);
		//msgId = publish.getMessageId();
	}
	
	protected byte[] getVariableHeader() //throws MqttException 
        {
		return encodeMessageId();
	}
}
