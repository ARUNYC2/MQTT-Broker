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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PingReq extends WireMessage
{  QueueOutputStream qs;
   public static final String KEY = "Ping";

	public PingReq() 
         {
		super(WireMessage.MESSAGE_TYPE_PINGREQ);
	    }

	public PingReq(byte info, byte[] variableHeader,QueueOutputStream qs) throws IOException {
		super(WireMessage.MESSAGE_TYPE_PINGREQ);
                this.qs=qs;
                submitRespo();
	}
	
        public void  submitRespo()
        {
         try 
               {
               qs.SubmitInQueue(new PingResp());
              }
            catch (InterruptedException ex)
            {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        
        
        
        
        
        
        
	/**
	 * Returns <code>false</code> as message IDs are not required for MQTT
	 * PINGREQ messages.
	 */
	public boolean isMessageIdRequired() {
		return false;
	}

	protected byte[] getVariableHeader() //throws MqttException 
        {
		return new byte[0];
	}
	
	protected byte getMessageInfo() {
		return 0;
	}
	
	public String getKey() {
		return KEY;
	}
}

