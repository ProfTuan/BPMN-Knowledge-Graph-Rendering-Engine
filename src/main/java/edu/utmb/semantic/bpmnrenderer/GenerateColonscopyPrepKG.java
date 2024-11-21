/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer;

import edu.utmb.semantic.bpmnrenderer.process.DataCurator;

/**
 *
 * @author tuan
 */
public class GenerateColonscopyPrepKG extends App{
    
    //private ConfigurationController configuration;
    
    public GenerateColonscopyPrepKG(){
        super();
        configuration.setDataFileAtRuntime("sample_bpmn_colonscopy_preparation.xlsx");
        
    }
    
    public void generateMiraLAXGatorade(){
        configuration.setRuntimeStartingNumber(1000);
        
        DataCurator data_curator = new DataCurator("MiraLAXGatorade_Colonscopy_Preparation");
        data_curator.importDataFromSpreadsheet(configuration.getDataFile(), "MiraLAX-Gatorade");
        data_curator.curateProcessToElementLinks();
        data_curator.saveKnowledgeGraph("MiraLAXGatorade_KG.owl");
    }
    
    public void generateGolytely(){
        configuration.setRuntimeStartingNumber(2000);
        
        DataCurator data_curator = new DataCurator("Golytely_Colonscopy_Preparation");
        data_curator.importDataFromSpreadsheet(configuration.getDataFile(), "Golytely");
        data_curator.curateProcessToElementLinks();
        data_curator.saveKnowledgeGraph("Golytely_KG.owl");
    }
    
    public void generatePlenvu(){
        
        configuration.setRuntimeStartingNumber(3000);
        
        DataCurator data_curator = new DataCurator("Plenvu_Colonscopy_Preparation");
        data_curator.importDataFromSpreadsheet(configuration.getDataFile(), "Plenvu");
        data_curator.curateProcessToElementLinks();
        data_curator.saveKnowledgeGraph("Plenvu_KG.owl");
    }
    
    public void generateSutab(){
        
        configuration.setRuntimeStartingNumber(4000);
        
        DataCurator data_curator = new DataCurator("Sutab_Colonscopy_Preparation");
        data_curator.importDataFromSpreadsheet(configuration.getDataFile(), "Sutab");
        data_curator.curateProcessToElementLinks();
        data_curator.saveKnowledgeGraph("Sutab_KG.owl");
        
    }
    
    public static void main(String[] args) {
        
        GenerateColonscopyPrepKG app = new GenerateColonscopyPrepKG();
        
        app.generateMiraLAXGatorade();
        
        //app.generateGolytely();
        
        //app.generatePlenvu();
        
        //app.generateSutab();
    }
    
}
