/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.ontology;

import edu.utmb.semantic.bpmnrenderer.model.AbstractBPMNPropertyLink;
import edu.utmb.semantic.bpmnrenderer.model.AbstractBPMNThing;
import edu.utmb.semantic.bpmnrenderer.model.BPMNProcess;
import edu.utmb.semantic.bpmnrenderer.model.IOSpecification;
import edu.utmb.semantic.bpmnrenderer.model.InputOutputLink;
import edu.utmb.semantic.bpmnrenderer.model.Task;
import java.util.Set;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Triple;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 *
 * @author tuan
 */
public abstract class BBOEncoder {
    
    
    public BBOEncoder(){
        
    }
    /*
    public void createTaskInstance(Task task){
        
        OWLClass taskClass = BBOController.getInstance().getOWLClass(BPMN_IRI.getInstance().TASK());
        
        OWLNamedIndividual task_instance = BBOController.getInstance().instantiateClassInstance(IRI.create(task.getIdentifier()));
        
        OWLAnnotation task_label = BBOController.getInstance().getOWLAnnotation(task.getLabel());
        
        BBOController.getInstance().encodeClassAssertion(task_instance, taskClass);
        
        BBOController.getInstance().encodeAnnotationAssertion(task_label, task_instance);
        
        
        
    }
    */
    public void createHasProcessFlowElements(BPMNProcess process, AbstractBPMNThing element){
        
        String prefix = BPMN_IRI.getInstance().getPrefix();
        //OWLNamedIndividual element_instance = BBOController.getInstance().instantiateClassInstance(IRI.create(prefix + element.getIdentifier()));
        OWLNamedIndividual element_instance = BBOController.getInstance().getOWLNamedIndividual(IRI.create(prefix + element.getIdentifier()));
        
        OWLObjectProperty object_property = BBOController.getInstance().getOWLObjectProperty(BPMN_IRI.getInstance().HAS_FLOW_ELEMENTS());

        //OWLClass process_class = BBOController.getInstance().getOWLClass(BPMN_IRI.getInstance().PROCESS());
        OWLNamedIndividual process_instance = BBOController.getInstance().getOWLNamedIndividual(IRI.create(prefix + process.getIdentifier()));

        //link process
        BBOController.getInstance().encodeObjectPropertyAssertion(process_instance, object_property, element_instance);
        
    }
    
    public void createProcess(BPMNProcess process){
        
        String prefix = "#";
        OWLClass process_class = BBOController.getInstance().getOWLClass(BPMN_IRI.getInstance().PROCESS());
        OWLNamedIndividual process_instance = BBOController.getInstance().instantiateClassInstance(IRI.create(prefix + process.getIdentifier()));
        OWLAnnotation process_label = BBOController.getInstance().getOWLAnnotation(process.getLabel());
        
        BBOController.getInstance().encodeClassAssertion(process_instance, process_class);
        BBOController.getInstance().encodeAnnotationAssertion(process_label, process_instance);
    }
    
    public void createFlowElements(AbstractBPMNThing element){
        
        String prefix = "#";
        
        OWLClass owlClass = element.getOWLClass();
        String definition = element.getLabel();
        OWLNamedIndividual element_instance = BBOController.getInstance().instantiateClassInstance(IRI.create(prefix + element.getIdentifier()));
        
        OWLAnnotation element_label = BBOController.getInstance().getOWLAnnotation(element.getAssignedIdentifier()+"_"+element.getClass().getSimpleName().toString());
        
        BBOController.getInstance().encodeClassAssertion(element_instance, owlClass);
        
        BBOController.getInstance().encodeAnnotationAssertion(element_label, element_instance);
        
        BBOController.getInstance().encodeInstanceDefinition(definition, element_instance);
       
        
    }
    
    public void createSequenceFlows(AbstractBPMNPropertyLink sequence){
        String prefix = "#";
        
        //OWLObjectProperty object_property = sequence.getObjectProperty();
        OWLClass sequence_flow_class = BBOController.getInstance().getOWLClass(BPMN_IRI.getInstance().SEQUENCE_FLOW());
        OWLNamedIndividual sequence_instance = BBOController.getInstance().instantiateClassInstance(IRI.create(prefix + sequence.getIdentifier()));
        OWLAnnotation sequence_label = BBOController.getInstance().getOWLAnnotation("Sequence-" + sequence.getIdentifier());
        
        BBOController.getInstance().encodeClassAssertion(sequence_instance, sequence_flow_class);
        BBOController.getInstance().encodeAnnotationAssertion(sequence_label, sequence_instance);
        
    }
    
    public void createIOSpecificationReference(OWLNamedIndividual io_instance, AbstractBPMNThing element){
        String prefix = BPMN_IRI.getInstance().getPrefix();
        OWLNamedIndividual element_instance = BBOController.getInstance().getOWLNamedIndividual(IRI.create(prefix + element.getIdentifier()));
        OWLObjectProperty has_io_specification = BBOController.getInstance().getOWLObjectProperty(BPMN_IRI.getInstance().HAS_IO_SPECIFICATION());
        
        BBOController.getInstance().encodeObjectPropertyAssertion(element_instance, 
                has_io_specification, io_instance);
        
        
        
        //add label to io specification
        OWLAnnotation io_label = BBOController.getInstance().getOWLAnnotation("IO_Spec_"+io_instance.getIRI().getFragment() ); 
        BBOController.getInstance().encodeAnnotationAssertion(io_label, io_instance);
        
        //link specification and element together
        
        
    }
    
    public void createSequenceReferences(AbstractBPMNPropertyLink sequence)
    {
        ImmutablePair<? extends AbstractBPMNThing,? extends AbstractBPMNThing> pair = sequence.getNodes();
        //
        String prefix = BPMN_IRI.getInstance().getPrefix();
        AbstractBPMNThing source = pair.getLeft();
        AbstractBPMNThing target = pair.getRight();
        
        OWLNamedIndividual source_instance = BBOController.getInstance().getOWLNamedIndividual(IRI.create(prefix+source.getIdentifier()));
        OWLNamedIndividual target_instance = BBOController.getInstance().getOWLNamedIndividual(IRI.create(prefix+target.getIdentifier()));
        OWLNamedIndividual sequence_instance = BBOController.getInstance().getOWLNamedIndividual(IRI.create(prefix + sequence.getIdentifier()));
        
        OWLObjectProperty source_ref_property = BBOController.getInstance().getOWLObjectProperty(BPMN_IRI.getInstance().HAS_SOURCE_REF());
        OWLObjectProperty target_ref_property = BBOController.getInstance().getOWLObjectProperty(BPMN_IRI.getInstance().HAS_TARGET_REF());
        
        BBOController.getInstance().encodeObjectPropertyAssertion(sequence_instance, source_ref_property, source_instance);
        BBOController.getInstance().encodeObjectPropertyAssertion(sequence_instance, target_ref_property, target_instance);
    }
    
    public void createResourceReferences(OWLNamedIndividual io_instance, AbstractBPMNPropertyLink io_reference){
        
        AbstractBPMNThing resource = ((InputOutputLink)io_reference).getResource();
        AbstractBPMNThing flowElement = ((InputOutputLink)io_reference).getFlowElement();
        String prefix = BPMN_IRI.getInstance().getPrefix();
        
        
        OWLNamedIndividual resource_instance = BBOController.getInstance().getOWLNamedIndividual(IRI.create(prefix + resource.getIdentifier()));
        OWLNamedIndividual flow_element_instance = BBOController.getInstance().getOWLNamedIndividual(IRI.create(prefix + flowElement.getIdentifier()));
        

        
        OWLObjectProperty property_reference = BBOController.getInstance().getOWLObjectProperty(io_reference.getProperty());
        
        
        BBOController.getInstance().encodeObjectPropertyAssertion(io_instance, property_reference, resource_instance);
        
        
    }
    
    
}
