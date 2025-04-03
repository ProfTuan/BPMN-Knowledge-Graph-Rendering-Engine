/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.ontology.jfactreasoncheck;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import uk.ac.manchester.cs.jfact.JFactFactory;

/**
 *
 * @author tuan
 */
public class CheckOntologyModel {
    
  
    private OWLReasonerFactory reasonerFactory = new JFactFactory();
    private OWLOntologyManager manager = null;
    private OWLOntology ontology = null;
    
    private String IRI_ontology = "";
    
    private void init(){
        
        manager  = OWLManager.createOWLOntologyManager();
        try {
            ontology = manager.loadOntology(IRI.create(IRI_ontology));
            //ontology;
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(CheckOntologyModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void init(String ontology_iri){
        
        init();
    }
    
    public void check(){
        OWLReasonerConfiguration config = new SimpleConfiguration(50000);
        
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology, config);
        
        reasoner.precomputableInferenceTypes();
        
        if(reasoner.isConsistent()) {
            System.out.println("ontology is consistent");
        }
        else{
            System.out.println("ERROR: ontology is inconsistent");
        }
        
        Node<OWLClass> unsatisfiableClasses = reasoner.getUnsatisfiableClasses();
        
        Set<OWLClass> finalList = unsatisfiableClasses.getEntitiesMinusBottom();
        
        if(finalList.isEmpty()){
            System.out.println("ontology is satisfable");
        }
        else{
            System.out.println("ERROR: ontology is unsatisfable");
        }
        
    }
    
    public static void main(String[] args) {
        
        String [] cp_kgs = {
            "https://raw.githubusercontent.com/ProfTuan/BPMN-Knowledge-Graph-Rendering-Engine/refs/heads/main/examples/Colonoscopy%20Preparation%20Process/Sutab_KG.owl",
            "https://raw.githubusercontent.com/ProfTuan/BPMN-Knowledge-Graph-Rendering-Engine/refs/heads/main/examples/Colonoscopy%20Preparation%20Process/Plenvu_KG.owl",
            "https://raw.githubusercontent.com/ProfTuan/BPMN-Knowledge-Graph-Rendering-Engine/refs/heads/main/examples/Colonoscopy%20Preparation%20Process/MiraLAXGatorade_KG.owl",
            "https://raw.githubusercontent.com/ProfTuan/BPMN-Knowledge-Graph-Rendering-Engine/refs/heads/main/examples/Colonoscopy%20Preparation%20Process/Golytely_KG.owl"};
        
        
        for(int i=0; i<cp_kgs.length; i++){
            CheckOntologyModel checker = new CheckOntologyModel();
            checker.init(cp_kgs[i]);
            checker.check();
            System.out.println("\n----------------------\n");
        }
        
        
        
    }
    
}
