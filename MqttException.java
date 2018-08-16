/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3.MqttExceptions;

import javax.swing.JOptionPane;
import java.io.IOException;

/**
 *
 * @author ARUN YADAV
 */
public class MqttException extends Throwable 
{
    
   public MqttException()
   {
        JOptionPane.showMessageDialog(null,"!!  Exception  MQTT !!   \n "); 
    }
     public MqttException(IOException ex)
   {
        JOptionPane.showMessageDialog(null,"!!  Exception  MQTT !!   \n "+ex); 
    } 
    
   public MqttException(String msg )
    {
       JOptionPane.showMessageDialog(null,"!!  Exception  IN !!   \n "+msg); 
    }
    
}
