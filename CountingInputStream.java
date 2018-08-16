/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;


import java.io.InputStream;
import java.io.IOException;
/**
 *
 * @author ARUN YADAV
 */
public class CountingInputStream extends InputStream{
    
             private InputStream in;
	     private int counter;

	/**
	 * Constructs a new <code>CountingInputStream</code> wrapping the supplied
	 * input stream.
	 * @param in The {@link InputStream}
	 */
	public CountingInputStream(InputStream in) {
		this.in = in;
		this.counter = 0;
	}
	
	public int read() throws IOException {
		int i = in.read();
		if (i != -1) {
			counter++;
		}
		return i;
	}

	/**
	 * @return  the number of bytes read since the last reset.
	 */
	public int getCounter() {
		return counter;
	}
	
	/**
	 * Resets the counter to zero.
	 */
	public void resetCounter() {
		counter = 0;
	}
}
