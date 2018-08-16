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
public class PubRel extends WireMessage {
 public PubRel(PubRec pubRec) {
		super(WireMessage.MESSAGE_TYPE_PUBREL);
		this.setMessageId(pubRec.getMessageId());
	}
	
	/**
	 * Creates a pubrel based on a pubrel set of bytes read fro the network
	 * @param info the info byte
	 * @param data the byte array
	 * @throws IOException if an exception occurs whilst reading from the input stream
	 */
	public PubRel(byte info, byte[] data) throws IOException {
		super(WireMessage.MESSAGE_TYPE_PUBREL);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		msgId = dis.readUnsignedShort();
		dis.close();
	}

	protected byte[] getVariableHeader() //throws MqttException
        {
		return encodeMessageId();
	}
	
	protected byte getMessageInfo() {
		return (byte)( 2 | (this.duplicate?8:0));
	}

	public String toString() {
    	return super.toString() + " msgId " + msgId;
	}

}
