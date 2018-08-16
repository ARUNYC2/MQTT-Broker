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
public abstract  class Ack extends WireMessage{
      
    public Ack(byte type) {
		super(type);
	}
	
	    protected byte getMessageInfo() {
		return 0;
	    }
         public Ack(){}
	/**
	 * @return String representation of the wire message
	 */
	public String toString() {
		return super.toString() + " msgId " + msgId;
	}
    
}
