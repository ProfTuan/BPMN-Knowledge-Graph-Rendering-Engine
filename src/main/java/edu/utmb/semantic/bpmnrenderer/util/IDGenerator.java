/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author tuan
 */
public class IDGenerator {
    
    static private IDGenerator INSTANCE = null;
    
    private String PREFIX = "TEST_";
    
    private static AtomicLong id = new AtomicLong(0);
    
    private IDGenerator(){
        
    }
    
    static public IDGenerator getInstance(){
        
        if(INSTANCE == null){
            INSTANCE = new IDGenerator();
        }
        
        return INSTANCE;
        
    }
    
    public void setInitialGeneratedID(long value){
        
        id = new AtomicLong(value);
        
    }
    
    public String getGeneratedID(){
        
        String number = String.format("%07d", id.incrementAndGet());
        
        return PREFIX+number;
    }
    
    
    public void setPREFIX(String prefix){
        
        PREFIX = prefix;
    }
    
    public String getPREFIX(){
        return PREFIX;
    }
    
    public static void main(String[] args) {
        
        
        IDGenerator g = new IDGenerator();
        System.out.println(g.getGeneratedID());
        
    }
    
}
