/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.util;

import edu.utmb.semantic.bpmnrenderer.model.AbstractBPMNPropertyLink;
import edu.utmb.semantic.bpmnrenderer.model.AbstractBPMNThing;
import edu.utmb.semantic.bpmnrenderer.ontology.BPMN_IRI;
import org.semanticweb.owlapi.model.IRI;

/**
 *
 * @author tuan
 */
public class Utility {
    
    public Utility(){
        
    }
    

    public static boolean isAFlowElement(AbstractBPMNPropertyLink link){
        BPMN_IRI bi = BPMN_IRI.getInstance();
        
        IRI link_iri = link.getProperty();
        
        if(link_iri.equals(bi.TASK()) ||
                link_iri.equals(bi.GATEWAY()) ||
                link_iri.equals(bi.START_EVENT()) ||
                link_iri.equals(bi.END_EVENT()) ||
                link_iri.equals(bi.TIMER_EVENT()) ||
                link_iri.equals(bi.SEQUENCE_FLOW()) ||
                link_iri.equals(bi.SUBPROCESS())
                )
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean isAFlowElement(IRI element_iri){
        BPMN_IRI bi = BPMN_IRI.getInstance();

        if(element_iri.equals(bi.TASK()) ||
                element_iri.equals(bi.GATEWAY()) ||
                element_iri.equals(bi.START_EVENT()) ||
                element_iri.equals(bi.END_EVENT()) ||
                element_iri.equals(bi.TIMER_EVENT()) ||
                element_iri.equals(bi.SEQUENCE_FLOW()) ||
                element_iri.equals(bi.SUBPROCESS())
                )
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean isMiscEntity(IRI entity_iri){
        
        BPMN_IRI bi = BPMN_IRI.getInstance();
        
        
        
        if(entity_iri.equals(bi.TEXTUAL_ENTITY())){
            return true;
        }
        else if(entity_iri.equals(bi.DENOTES())){
            return true;
        }
        
        return false;
    }
    
    
    public static boolean isAFlowElement(AbstractBPMNThing element){
        if(element == null) return false;
        
        BPMN_IRI bi = BPMN_IRI.getInstance();
        
        IRI element_iri = element.getOWLClass().getIRI();
        
        if(element_iri.equals(bi.TASK()) ||
                element_iri.equals(bi.GATEWAY()) ||
                element_iri.equals(bi.START_EVENT()) ||
                element_iri.equals(bi.END_EVENT()) ||
                element_iri.equals(bi.TIMER_EVENT()) ||
                element_iri.equals(bi.SEQUENCE_FLOW()) ||
                element_iri.equals(bi.SUBPROCESS())
                )
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean isAResourceElement(AbstractBPMNThing element){
        if(element == null) return false;
        
        BPMN_IRI bi = BPMN_IRI.getInstance();
        
        IRI element_iri = element.getOWLClass().getIRI();
        
        if(element_iri.equals(bi.RESOURCE()))
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean isMiscEntity(AbstractBPMNThing element){
        if(element == null) return false;
        
        BPMN_IRI bi = BPMN_IRI.getInstance();
        
        IRI element_iri = element.getOWLClass().getIRI();
        
        if(element_iri.equals(bi.TEXTUAL_ENTITY())){
            return true;
        }
        else if(element_iri.equals(bi.DENOTES())){
            return true;
        }
        
        return false;
    }
}
