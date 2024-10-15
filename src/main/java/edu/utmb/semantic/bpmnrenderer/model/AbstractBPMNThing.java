/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.model;

import edu.utmb.semantic.bpmnrenderer.util.IDGenerator;
import org.semanticweb.owlapi.model.OWLClass;

/**
 *
 * @author tuan
 */
public abstract class AbstractBPMNThing {
    
    private String identifier = "";
    private String label = "";
    private String assigned_identifier = "";
    
    private String table_id = "";
    protected OWLClass owl_class = null;
    
    public AbstractBPMNThing(){
        
        identifier = IDGenerator.getInstance().getGeneratedID();
        
    }
    
    public String getAssignedIdentifier(){
        return this.assigned_identifier;
    }
    
    public String getTableID(){
        return table_id;
    }
    
    public String getIdentifier(){
        return identifier;
    }
    
    public String getLabel(){
        
        if(label.isBlank()) return assigned_identifier;
        
        return label;
    }
    
    public void setLabel(String description){
        
        if(description != null || !description.isEmpty()) this.label = description;
    }
    
    public void setTableID(String table_id){
        this.table_id = table_id;
    }
    
    public void setAssignedIdentifier(String assigned_id){
        this.assigned_identifier = assigned_id;
    }
    
    public OWLClass getOWLClass(){
        return owl_class; 
    }
    
}
