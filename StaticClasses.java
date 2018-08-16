/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paho.broker.mqttv3;



import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/**
 *
 * @author ARUN YADAV
 */
public class StaticClasses {
    
 public static ConcurrentMap<String,ArrayList<OutputStream>> TopicClientsMap=new ConcurrentHashMap
                                                                 <String, ArrayList<OutputStream>>(); 
 
     public static Map<String,Date> Clients=new ConcurrentHashMap<String,Date>();
     public static Map<String,String> RetainMsg=new ConcurrentHashMap<String ,String>(); 
 
 
 
 
 
 //public static Map<String ,String> publishMsg=new HashMap<String ,String>();
 
 
 
   /* static class OutputStreams
    {
        static Map <String ,Object > OutputMap;
        
        
     }*/
    
    
   /*  public  class Subscriber
    {
      public static Map<String,List<Object>> TopicMap=new Map<String,List<Object>>(); 
     
    }*/
}