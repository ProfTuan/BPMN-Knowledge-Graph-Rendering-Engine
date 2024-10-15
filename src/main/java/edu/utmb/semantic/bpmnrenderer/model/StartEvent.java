/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.model;

import edu.utmb.semantic.bpmnrenderer.ontology.BBOController;
import edu.utmb.semantic.bpmnrenderer.ontology.BPMN_IRI;
import org.semanticweb.owlapi.model.OWLClass;

/**
 *
 * @author tuan
 */
public class StartEvent extends AbstractBPMNThing{
    
    
    public StartEvent(){
        this.owl_class = BBOController.getInstance().getOWLClass(BPMN_IRI.getInstance().START_EVENT());

    }
    
    
    
}
