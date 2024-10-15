/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.ontology;

import org.semanticweb.owlapi.model.IRI;

/**
 *
 * @author tuan
 */
public class BPMN_IRI extends SUPPORTING_IRI{
    
    static private BPMN_IRI INSTANCE = null;
    
    private IRI IRI_BPMN = IRI.create("https://raw.githubusercontent.com/ProfTuan/BBO_BPMNbasedOntology/master/BBO.owl");
    
    private IRI IRI_TASK = IRI.create("http://BPMNbasedOntology#Task");
    
    private IRI IRI_PROCESS = IRI.create("http://BPMNbasedOntology#Process");
    
    private IRI IRI_HAS_FLOW_ELEMENTS = IRI.create("http://BPMNbasedOntology#has_flowElements");
    
    private IRI IRI_RESOURCE = IRI.create("http://BPMNbasedOntology#Resource");
    
    private IRI IRI_GATEWAY = IRI.create("http://BPMNbasedOntology#Gateway");
    
    private IRI IRI_START_EVENT = IRI.create("http://BPMNbasedOntology#StartEvent");
    
    private IRI IRI_END_EVENT = IRI.create("http://BPMNbasedOntology#EndEvent");
    
    private IRI IRI_TIMER_EVENT = IRI.create("http://BPMNbasedOntology#TimerEvent");
    
    private IRI IRI_SUBPROCESS = IRI.create("http://BPMNbasedOntology#SubProcess");
    
    private IRI IRI_SEQUENCE_FLOW = IRI.create("http://BPMNbasedOntology#SequenceFlow");
    
    private IRI IRI_SOURCE_REF = IRI.create("http://BPMNbasedOntology#has_sourceRef");
    
    private IRI IRI_TARGET_REF = IRI.create("http://BPMNbasedOntology#has_targetRef");
    
    private IRI IRI_RESOURCE_INPUT = IRI.create("http://BPMNbasedOntology#has_resourceInputs");
    
    private IRI IRI_RESOURCE_OUTPUT = IRI.create("http://BPMNbasedOntology#has_resourceOutputs");
    
    private IRI IRI_HAS_IO_SPECIFICATION = IRI.create("http://BPMNbasedOntology#has_ioSpecification");
    
    private IRI IRI_IO_SPECIFICATION = IRI.create("http://BPMNbasedOntology#InputOutputSpecification");
    
    private String prefix = "http://BPMNbasedOntology#";
    
    private BPMN_IRI(){
        
    }
    
    static public BPMN_IRI getInstance(){
        
        if(INSTANCE == null){
            INSTANCE = new BPMN_IRI();
        }
        
        return INSTANCE;
        
    }
    
    public IRI IO_SPECIFICATION(){
        return IRI_IO_SPECIFICATION;
    }
    
    public IRI HAS_IO_SPECIFICATION(){
        return IRI_HAS_IO_SPECIFICATION;
    }
    
    public IRI HAS_RESOURCE_INPUT(){
        return IRI_RESOURCE_INPUT;
    }
    
    public IRI HAS_RESOURCE_OUTPUT(){
        return IRI_RESOURCE_OUTPUT;
    }
    
    public IRI HAS_SOURCE_REF(){
        return IRI_SOURCE_REF;
    }
    
    public IRI HAS_TARGET_REF(){
        return IRI_TARGET_REF;
    }
    
    public IRI SUBPROCESS(){
        return IRI_SUBPROCESS;
    }
    
    public IRI SEQUENCE_FLOW(){
        return IRI_SEQUENCE_FLOW;
    }
    
    public String getPrefix(){
        
        return prefix;
    }
    
    public IRI RESOURCE(){
        return IRI_RESOURCE;
    }
    
    public IRI GATEWAY(){
        return IRI_GATEWAY;
    }
    
    public IRI START_EVENT(){
        return IRI_START_EVENT;
    }
    
    public IRI END_EVENT(){
        return IRI_END_EVENT;
    }
    
    public IRI TIMER_EVENT(){
        return IRI_TIMER_EVENT;
    }
    
    public IRI HAS_FLOW_ELEMENTS(){
        return IRI_HAS_FLOW_ELEMENTS;
    }
    
    public IRI TASK(){
        return IRI_TASK;
    }
    
    public IRI PROCESS(){
        return IRI_PROCESS;
    }
    
    
    public IRI getIRI_BPMN(){
        
        
        return IRI_BPMN;
    }
    
    public static void main(String[] args) {
        
    }
}
