/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.process;

import com.github.jsonldjava.shaded.com.google.common.collect.SetMultimap;
import edu.utmb.semantic.bpmnrenderer.model.AbstractBPMNPropertyLink;
import edu.utmb.semantic.bpmnrenderer.model.AbstractBPMNThing;
import edu.utmb.semantic.bpmnrenderer.model.BPMNProcess;
import edu.utmb.semantic.bpmnrenderer.model.InputOutputLink;
import edu.utmb.semantic.bpmnrenderer.model.ReferenceLink;
import edu.utmb.semantic.bpmnrenderer.ontology.BBOController;
import edu.utmb.semantic.bpmnrenderer.util.Utility;
import java.util.Set;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

/**
 *
 * @author tuan
 */
public class DataCurator {
    
    private ProcessDataImport process_data_importer;
    
    private SetMultimap <IRI, ReferenceLink> process_to_elements;
    
    private String process_name = "";
    
    private BPMNProcess process;
    
    public DataCurator(){
        
    }
    
    public DataCurator(String process_name){
        this.process_name = process_name;
        
        //process = new BPMNProcess();
        process = new BPMNProcess();
        process.setLabel(process_name);
        //create process
        BBOController.getInstance().createProcess(process);
    }
    
    
    
    public void importDataFromSpreadsheet(String fileName, String sheetName){
        process_data_importer = new ProcessDataImport(fileName);
        process_data_importer.extractProcessData(sheetName, 0);
    }
    
    public void curateProcessToElementLinks(){
        
        
        process_name = this.process_name;
        
        
        Set<? extends AbstractBPMNThing> elements = process_data_importer.getBPMN_Nodes();
        Set<? extends AbstractBPMNPropertyLink> flow_links = process_data_importer.getBPMN_Links(); //sequence links
        Set<? extends AbstractBPMNPropertyLink> resource_links = process_data_importer.getResource_Links();
        //create Flow Elements
        elements.forEach(element->{
            
            //BBOController.getInstance().createTaskInstance(t);
            BBOController.getInstance().createFlowElements(element);
            
            //create link to process with flow elements (has flow Element)
            if(Utility.isAFlowElement(element))
                BBOController.getInstance().createHasProcessFlowElements(process, element);
        });
        
        
        flow_links.forEach(link -> {

            //if (!Utility.isMiscEntity(link.getObjectProperty().getIRI())) {
                //create Flow Link (Sequence Flow)
                BBOController.getInstance().createSequenceFlows(link);
                //create link to process with flow elements (has flow Element)
                BBOController.getInstance().createHasProcessFlowElements(process, link);

                //create sequence references (source ref and target ref)
                BBOController.getInstance().createSequenceReferences(link);
            

        });
        
        resource_links.forEach(rlink->{
            
            //link the flow element to io specification
            OWLNamedIndividual ioSpecificationInstance = ((InputOutputLink)rlink).getIOSpecificationInstance();
            AbstractBPMNThing element = ((InputOutputLink)rlink).getFlowElement();
            BBOController.getInstance().createIOSpecificationReference(ioSpecificationInstance, element);
            
            //link the io specficiation to resource
            
            //System.out.println(rlink.getProperty());
            BBOController.getInstance().createResourceReferences(ioSpecificationInstance, rlink);
        });
        
        
        //test
        
        
    }
    
    public void saveKnowledgeGraph(String fileName){
        
        if(BBOController.getInstance() != null){
            BBOController.getInstance().saveOntology(fileName);
        }
        
    }
    
    public void encodeBBOTasks(){
        
        
        
    }
    
    public static void main(String[] args) {
        
      

        
    }
    
}
