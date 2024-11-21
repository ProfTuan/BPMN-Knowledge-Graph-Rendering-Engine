package edu.utmb.semantic.bpmnrenderer;

import edu.utmb.semantic.bpmnrenderer.process.DataCurator;


public class App 

{
    protected ConfigurationController configuration;
    
    public App(){
        configuration = new ConfigurationController();
        configuration.initConfiguration();
    }
    
    
    
    
    
    public void generateExtractionProcessKG(String process_name, String sheet_name, String KG_file_name){
        DataCurator data_curator = new DataCurator(process_name);
        data_curator.importDataFromSpreadsheet(configuration.getDataFile(), sheet_name);
        data_curator.saveKnowledgeGraph(KG_file_name);
    }
    
    public static void main( String[] args )
    {
        
    }
}
