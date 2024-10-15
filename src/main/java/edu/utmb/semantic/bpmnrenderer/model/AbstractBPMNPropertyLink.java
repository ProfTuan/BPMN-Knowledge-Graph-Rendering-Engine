/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.model;

import edu.utmb.semantic.bpmnrenderer.ontology.BBOController;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 *
 * @author tuan
 */
public abstract class AbstractBPMNPropertyLink extends AbstractBPMNThing {
    
    protected IRI property = null;
    
    private AbstractBPMNThing _source;
    private AbstractBPMNThing _target;
    
    protected OWLObjectProperty object_property;
    
    public IRI getProperty(){
        return property;
    }
    
    public void setProperty(IRI property){
        this.property = property;
        object_property = BBOController.getInstance().getOWLObjectProperty(this.property);
        //this.owl_class = BBOController.getInstance().getOWLClass(property);
    }
    
    public void setProperty(String property_string){
        IRI iri_property = IRI.create(property_string);
        
        property = iri_property;
    }
    
    protected void setSource(AbstractBPMNThing source){
        this._source = source;
    }
    
    protected void setTarget(AbstractBPMNThing target){
        this._target = target;
    }
    
    protected AbstractBPMNThing getSource(){
        return this._source;
    }
    
    protected AbstractBPMNThing getTarget(){
        return this._target;
    }
    
    public abstract void setNodes(AbstractBPMNThing source, AbstractBPMNThing target);
    
    public ImmutablePair<? extends AbstractBPMNThing,? extends AbstractBPMNThing> getNodes (){
        
        ImmutablePair<? extends AbstractBPMNThing, ? extends AbstractBPMNThing> nodes = new ImmutablePair<>(_source,_target);
        
        return nodes;
        
    }
    
    public ImmutableTriple<? extends AbstractBPMNThing, OWLObjectProperty, ? extends AbstractBPMNThing> getTriple(){
        
        ImmutableTriple<? extends AbstractBPMNThing, OWLObjectProperty, ? extends AbstractBPMNThing> triple_pattern = new ImmutableTriple<>(_source, object_property, _target);
        
        return triple_pattern;
        
    }
    
    public OWLObjectProperty getObjectProperty(){
        return this.object_property;
    }
    
}
