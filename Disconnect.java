/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ARUN YADAV
 */
public class Disconnect extends WireMessage {
	public static final String KEY="Disc";
	
	public Disconnect() {
		super(WireMessage.MESSAGE_TYPE_DISCONNECT);
	}

	public Disconnect(byte info, byte[] variableHeader,Socket socket) //throws IOException 
        {
		super(WireMessage.MESSAGE_TYPE_DISCONNECT);
            try {
                    isCon=true;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Disconnect.class.getName()).log(Level.SEVERE, null, ex);
                    }
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Disconnect.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
	protected byte getMessageInfo() {
		return (byte) 0;
	}

	protected byte[] getVariableHeader() //throws MqttException 
        {
		return new byte[0];
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
}
