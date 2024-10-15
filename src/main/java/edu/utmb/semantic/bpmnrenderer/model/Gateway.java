/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.model;

import edu.utmb.semantic.bpmnrenderer.ontology.BBOController;
import edu.utmb.semantic.bpmnrenderer.ontology.BPMN_IRI;

/**
 *
 * @author tuan
 */
public class Gateway extends AbstractBPMNThing {
    
    public Gateway(){
        owl_class = BBOController.getInstance().getOWLClass(BPMN_IRI.getInstance().GATEWAY());
    }
    
    public static void main(String[] args) {
        
    }
}
