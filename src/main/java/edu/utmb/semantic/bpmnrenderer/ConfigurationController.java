/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer;

import edu.utmb.semantic.bpmnrenderer.util.IDGenerator;

/**
 *
 * @author tuan
 */
public class ConfigurationController {
    
    final private String PREFIX = "ADW_";
    
    private String file_name = "AD_Workflows.xlsx";
    
    final long initial_number =1000;
    
    private IDGenerator id_generator = null;

    
    public ConfigurationController(){
        
        id_generator = IDGenerator.getInstance();
        
        
    }
    
    public void initConfiguration(){
        
        this.setPREFIX();
        this.setStartingNumber();
    }
    
    public void setPREFIX(){
        
        id_generator.setPREFIX(PREFIX);
   
    }
    
    public void setRuntimePREFIX(String prefix){
        id_generator.setPREFIX(prefix);
    }
    
    public void setStartingNumber(){
        
        id_generator.setInitialGeneratedID(initial_number);
        
    }
    
    public void setRuntimeStartingNumber(long value){
        id_generator.setInitialGeneratedID(value);
    }
    
    
    
    public void setDataFileAtRuntime(String file_name){
        this.file_name = file_name;
    }
    
    public String getDataFile(){
        return this.file_name;
    }
    
    public static void main(String[] args) {
        
    }
    
}
