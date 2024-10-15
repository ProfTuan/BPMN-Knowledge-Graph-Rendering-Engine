/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuan
 */
public class ConfigProperties { 
    
    static private ConfigProperties INSTANCE = null;
    
    final private String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    
    final private String properties_file = rootPath + "config.properties";
    
    private Properties config = null;
    
    private ConfigProperties(){
        
        try {
            
            config = new Properties();
            config.load(new FileInputStream(properties_file));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    static private ConfigProperties getInstance(){
        
        if(INSTANCE == null){
            INSTANCE = new ConfigProperties();
        }
        
        return INSTANCE;
    }
    
    public static void main(String[] args) {
        
    }
    
}
