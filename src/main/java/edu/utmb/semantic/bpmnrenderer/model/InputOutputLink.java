/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.model;

import edu.utmb.semantic.bpmnrenderer.ontology.BBOController;
import edu.utmb.semantic.bpmnrenderer.ontology.BPMN_IRI;
import edu.utmb.semantic.bpmnrenderer.util.IDGenerator;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

/**
 *
 * @author tuan
 */
public class InputOutputLink extends AbstractBPMNPropertyLink{
    
    
    private OWLNamedIndividual io_specification_instance;
    
    public InputOutputLink(){
        
        
        
    }
    
    public OWLNamedIndividual getIOSpecificationInstance(){
        
        this.generateIOSpecificationInstance();
        
        return io_specification_instance;
    }
    
    private void generateIOSpecificationInstance(){
        
        String prefix = BPMN_IRI.getInstance().getPrefix();
        
        OWLClass io_specification_class = BBOController.getInstance().getOWLClass(BPMN_IRI.getInstance().IO_SPECIFICATION());
        
         io_specification_instance = BBOController.getInstance().instantiateClassInstance(IRI.create(prefix + IDGenerator.getInstance().getGeneratedID()));
        
        BBOController.getInstance().encodeClassAssertion(io_specification_instance, io_specification_class);
        
    }
    
    public AbstractBPMNThing getResource(){
        
        if(getSource() instanceof Resource){
            return getSource();
        }
        else{
            return getTarget();
        }
        
        
    }
    
    public AbstractBPMNThing getFlowElement() {

        if (getSource() instanceof Resource) {

            return getTarget();

        } else {
            return getSource();
        }

    }
    
    
   

    @Override
    public void setNodes(AbstractBPMNThing source, AbstractBPMNThing target) {
        
        this.setSource(source);
        this.setTarget(target);
    }
}
