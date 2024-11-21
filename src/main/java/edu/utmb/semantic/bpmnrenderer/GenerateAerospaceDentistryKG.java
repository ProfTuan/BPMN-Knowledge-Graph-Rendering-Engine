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
public class GenerateAerospaceDentistryKG extends App{
    
    //private ConfigurationController configuration;
    
    public GenerateAerospaceDentistryKG(){
        super();
        
    }
    
    public void generateExtractionProcessKG(){
        
        configuration.setRuntimeStartingNumber(1000);
        
        DataCurator data_curator = new DataCurator("Aerospace_Dental_Extraction");
        data_curator.importDataFromSpreadsheet(configuration.getDataFile(), "Extraction");
        data_curator.curateProcessToElementLinks();
        data_curator.saveKnowledgeGraph("ADE_KG.owl");
    }
    
    public void generateDislodgedRestorationsKG(){
        
        configuration.setRuntimeStartingNumber(2000);
        
        DataCurator data_curator = new DataCurator("Aerospace_Dislodged_Restorations");
        data_curator.importDataFromSpreadsheet(configuration.getDataFile(), "Dislodged Restorations");
        data_curator.curateProcessToElementLinks();
        data_curator.saveKnowledgeGraph("ADR_KG.owl");
    }
    
    public void generateAbcessesKG(){
        
        configuration.setRuntimeStartingNumber(3000);
        
        DataCurator data_curator = new DataCurator("Aerospace_Abcsesses");
        data_curator.importDataFromSpreadsheet(configuration.getDataFile(), "Abcsesses");
        data_curator.curateProcessToElementLinks();
        data_curator.saveKnowledgeGraph("ADA_KG.owl");
    }
    
    public void generateMinorDentalTramuaKG(){
        
        configuration.setRuntimeStartingNumber(4000);
        
        DataCurator data_curator = new DataCurator("Aerospace_Minor_Dental_Tramua");
        data_curator.importDataFromSpreadsheet(configuration.getDataFile(), "Minor Dental Trauma");
        data_curator.curateProcessToElementLinks();
        data_curator.saveKnowledgeGraph("AMDT_KG.owl");
    }
    
    public static void main(String[] args) {
        
        GenerateAerospaceDentistryKG app = new GenerateAerospaceDentistryKG();
        //app.generateExtractionProcessKG();
        //app.generateDislodgedRestorationsKG();
        //app.generateAbcessesKG();
        //app.generateMinorDentalTramuaKG();
        
    }
    
}
