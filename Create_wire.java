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
public class Create_wire extends WireMessage
{
    
    
    public Create_wire(){}
        protected byte[] getVariableHeader(){return null;} 
        protected byte getMessageInfo() {
		return (byte) 0;
	}
    
        
        
        
        
}
