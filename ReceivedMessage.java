/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;

/**
 *
 * @author ARUN YADAV
 */
public class ReceivedMessage extends Message {
	
	public void setMessageId(int msgId) {
        	super.setId(msgId);
	}
	
	public int getMessageId() {
		return super.getId();
	}
	
	 // This method exists here to get around the protected visibility of the
	// super class method.
	public void setDuplicate(boolean value) {
		super.setDuplicate(value);
	}
}
