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
public class Message {
 
	private boolean mutable = true;
	private byte[] payload;
	private int qos = 0;
	private boolean retained = false;
	private boolean dup = false;
	private int messageId;

	/**
	 * Utility method to validate the supplied QoS value.
	 * @param qos The QoS Level
	 * @throws IllegalArgumentException if value of QoS is not 0, 1 or 2.
	 */
	public static void validateQos(int qos) {
		if ((qos < 0) || (qos > 2)) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Constructs a message with an empty payload, and all other values
	 * set to defaults.
	 *
	 * The defaults are:
	 * <ul>
	 *    <li>Message QoS set to 1</li>
	 *    <li>Message will not be "retained" by the server</li>
	 * </ul>
	 */
	public Message() {
		setPayload(new byte[]{});
	}

	/**
	 * Constructs a message with the specified byte array as a payload,
	 * and all other values set to defaults.
	 * @param payload The Bytearray of the payload
	 */
	public Message(byte[] payload) {
		setPayload(payload);
	}

	/**
	 * Returns the payload as a byte array.
	 *
	 * @return the payload as a byte array.
	 */
	public byte[] getPayload() {
		return payload;
	}

	/**
	 * Clears the payload, resetting it to be empty.
	 * @throws IllegalStateException if this message cannot be edited
	 */
	public void clearPayload() {
		checkMutable();
		this.payload = new byte[] {};
	}

	/**
	 * Sets the payload of this message to be the specified byte array.
	 *
	 * @param payload the payload for this message.
	 * @throws IllegalStateException if this message cannot be edited
	 * @throws NullPointerException if no payload is provided
	 */
	public void setPayload(byte[] payload) {
		checkMutable();
		if (payload == null) {
			throw new NullPointerException();
		}
		this.payload = payload;
	}

	
	public boolean isRetained() {
		return retained;
	}

	
	public void setRetained(boolean retained) {
		checkMutable();
		this.retained = retained;
	}

	/**
	 * Returns the quality of service for this message.
	 * @return the quality of service to use, either 0, 1, or 2.
	 * @see #setQos(int)
	 */
	public int getQos() {
		return qos;
	}

	
	public void setQos(int qos) {
		checkMutable();
		validateQos(qos);
		this.qos = qos;
	}

	/**
	 * Returns a string representation of this message's payload.
	 * Makes an attempt to return the payload as a string. As the
	 * MQTT client has no control over the content of the payload
	 * it may fail.
	 * @return a string representation of this message.
	 */
	public String toString() {
		return new String(payload);
	}

	/**
	 * Sets the mutability of this object (whether or not its values can be
	 * changed.
	 * @param mutable <code>true</code> if the values can be changed,
	 * <code>false</code> to prevent them from being changed.
	 */
	protected void setMutable(boolean mutable) {
		this.mutable = mutable;
	}

	protected void checkMutable() throws IllegalStateException {
		if (!mutable) {
			throw new IllegalStateException();
		}
	}

	protected void setDuplicate(boolean dup) {
		this.dup = dup;
	}

	/**
	 * Returns whether or not this message might be a duplicate of one which has
	 * already been received.  This will only be set on messages received from
	 * the server.
	 * @return <code>true</code> if the message might be a duplicate.
	 */
	public boolean isDuplicate() {
		return this.dup;
	}
	
	/**
	 * This is only to be used internally to provide the MQTT id of a message
	 * received from the server.  Has no effect when publishing messages.
	 * @param messageId The Message ID
	 */
	public void setId(int messageId) {
		this.messageId = messageId;
	}

	/**
	 * Returns the MQTT id of the message.  This is only applicable to messages
	 * received from the server.
	 * @return the MQTT id of the message
	 */
	public int getId() {
		return this.messageId;
	}
	
	
	
}
