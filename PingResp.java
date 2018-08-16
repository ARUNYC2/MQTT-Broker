/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;

import org.paho.broker.mqttv3.MqttExceptions.MqttException;

/**
 *
 * @author ARUN YADAV
 */
public class PingResp  extends Ack {
	public static final String KEY = "Ping";
	
	public PingResp(byte info, byte[] variableHeader) {
		super(WireMessage.MESSAGE_TYPE_PINGRESP);
	}
	
        public PingResp() {
		super(WireMessage.MESSAGE_TYPE_PINGRESP);
                
        }
        
        
	protected byte[] getVariableHeader() //hrows MqttException 
        {
		// Not needed, as the client never encodes a PINGRESP
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
