/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.ontology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 *
 * @author tuan
 */
public class BBOController extends BBOEncoder{
    
    static private BBOController INSTANCE = null;
    
    private OWLOntology ontology;
    private OWLDataFactory factory;
    private OWLOntologyManager manager;
    
    private BBOController (){
        
      manager =  OWLManager.createConcurrentOWLOntologyManager();
      
        try {
           ontology = manager.loadOntology(BPMN_IRI.getInstance().getIRI_BPMN());
           
           factory = ontology.getOWLOntologyManager().getOWLDataFactory();
           
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(BBOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    static public BBOController getInstance(){
        
        if(INSTANCE == null){
            INSTANCE = new BBOController();
        }
        
        
        return INSTANCE;
    }
    
    public OWLClass getOWLClass(IRI iri){
        
        OWLClass owlClass = factory.getOWLClass(iri);
        
        return owlClass;
        
    }
    
    public OWLObjectProperty getOWLObjectProperty (IRI iri){
        
        OWLObjectProperty object_property = factory.getOWLObjectProperty(iri);
        
        return object_property;
        
    }
    
    public OWLNamedIndividual getOWLNamedIndividual (IRI iri){
        OWLNamedIndividual individual = factory.getOWLNamedIndividual(iri);
        
        return individual;
    }
    
    public void getOntologyPrefix(){
        
        
        
    }
    
    public OWLNamedIndividual instantiateClassInstance(IRI iri){
        OWLNamedIndividual owlNamedIndividual = factory.getOWLNamedIndividual(iri);
        return owlNamedIndividual;
    }
    
    public OWLAnnotation getOWLAnnotation(String description){
        OWLAnnotation owlAnnotation = factory.getOWLAnnotation(factory.getRDFSLabel(), factory.getOWLLiteral(description, "en"));
        
        return owlAnnotation;
    }
    
    public void encodeClassAssertion(OWLNamedIndividual instance, OWLClass owlclass){
        OWLClassAssertionAxiom assertion = factory.getOWLClassAssertionAxiom(owlclass, instance);
        
        AddAxiom newAxiom = new AddAxiom(ontology, assertion);
        ontology.applyChange(newAxiom);
    }
    
    public void encodeInstanceDefinition(String definition,OWLNamedIndividual instance){
        
        //factory.getOWLAnnotation(factory.getRDFSComment(), factory.ge
        OWLAnnotation owlAnnotation = factory.getOWLAnnotation(factory.getRDFSComment(), factory.getOWLLiteral(definition, "en"));
        
        OWLAnnotationAssertionAxiom assertion = factory.getOWLAnnotationAssertionAxiom(instance.getIRI(), owlAnnotation);
        
        AddAxiom addAxiom = new AddAxiom(ontology, assertion);
        ontology.applyChange(addAxiom);
        
    }
    
    public void encodeAnnotationAssertion(OWLAnnotation annotation, OWLNamedIndividual instance){
        
        OWLAnnotationAssertionAxiom assertion = factory.getOWLAnnotationAssertionAxiom(instance.getIRI(), annotation);
        AddAxiom addAxiom = new AddAxiom(ontology, assertion);
        ontology.applyChange(addAxiom);
    }
    
    public void encodeObjectPropertyAssertion(OWLNamedIndividual subject, OWLObjectProperty property, OWLNamedIndividual object){
        
        OWLObjectPropertyAssertionAxiom assertion = factory.getOWLObjectPropertyAssertionAxiom(property, subject, object);
        
        AddAxiom newAxiom = new AddAxiom(ontology, assertion);
        ontology.applyChange(newAxiom);
        
    }
    
    public void saveOntology(String fileName){
        try {
            manager.saveOntology(ontology, new OWLXMLDocumentFormat(), new FileOutputStream(new File(fileName)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BBOController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(BBOController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) {
        
        
        
        
        
    }
    
}
