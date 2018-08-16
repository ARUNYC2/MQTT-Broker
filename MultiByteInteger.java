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
public class MultiByteInteger {
        private long value;
	private int length;
	
	public MultiByteInteger(long value) {
		this(value, -1);
	}
	
	public MultiByteInteger(long value, int length) {
		this.value = value;
		this.length = length;
	}
	
	/**
	 * @return the number of bytes read when decoding this MBI.
	 */
	public int getEncodedLength() {
		return length;
	}

	/**
	 * @return the value of this MBI.
	 */
	public long getValue() {
		return value;
	}
}
