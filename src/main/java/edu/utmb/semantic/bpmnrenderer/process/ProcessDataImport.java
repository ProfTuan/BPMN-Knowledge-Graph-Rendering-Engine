/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.process;

import edu.utmb.semantic.bpmnrenderer.model.AbstractBPMNPropertyLink;
import edu.utmb.semantic.bpmnrenderer.model.AbstractBPMNThing;
import edu.utmb.semantic.bpmnrenderer.model.EndEvent;
import edu.utmb.semantic.bpmnrenderer.model.Gateway;
import edu.utmb.semantic.bpmnrenderer.model.InputOutputLink;
import edu.utmb.semantic.bpmnrenderer.model.ReferenceLink;
import edu.utmb.semantic.bpmnrenderer.model.Resource;
import edu.utmb.semantic.bpmnrenderer.model.StartEvent;
import edu.utmb.semantic.bpmnrenderer.model.Subprocess;
import edu.utmb.semantic.bpmnrenderer.model.Task;
import edu.utmb.semantic.bpmnrenderer.model.TextEntity;
import edu.utmb.semantic.bpmnrenderer.model.TimerEvent;
import edu.utmb.semantic.bpmnrenderer.ontology.BPMN_IRI;
import edu.utmb.semantic.bpmnrenderer.util.Utility;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.semanticweb.owlapi.model.IRI;

/**
 *
 * @author tuan
 */
public class ProcessDataImport {

    private String process_file = "";

    private String root_path = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private Workbook workbook;

    private Sheet sheet;

    private Set<AbstractBPMNThing> nodes;
    
    private Set<ReferenceLink> sequence_links;
    
    private Set<InputOutputLink> resource_links;

    //private Set<Triple<Process, IRI, ? extends AbstractBPMNThing>> process_elements;

    public ProcessDataImport() {

    }

    public ProcessDataImport(String file_name) {

        FileInputStream file = null;
        try {

            String file_path = root_path + file_name;
            file = new FileInputStream(file_path);

            workbook = new XSSFWorkbook(file);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcessDataImport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcessDataImport.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(ProcessDataImport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public IRI getCellIRIType(Cell cell) {

        IRI iri = IRI.create(cell.getStringCellValue());

        return iri;

    }
    
    public void linkProcessData(String sheet_name, int header_row_number){
        
        //read the link data
        
        int column_id = CellReference.convertColStringToIndex("A");
        int column_text = CellReference.convertColStringToIndex("B");
        int column_type = CellReference.convertColStringToIndex("C");
        int column_target = CellReference.convertColStringToIndex("D");
        
        BPMN_IRI bpmn = BPMN_IRI.getInstance();
        
        sheet = workbook.getSheet(sheet_name);
        
        //get the squenceflows
        
        
        
        //search for the nodes and link the nodes
        
    }
    
    private boolean isAFlowElement(String human_id){
        
        //boolean value = false;
        //Boolean value = false;
        
        
        
        return false;
    }
    
    public IRI getFlowNodeIRI(Cell cell){
        IRI iri = null;
        
        String cell_value = cell.getStringCellValue();
        
        for(AbstractBPMNThing n : nodes){
            
            if(n.getAssignedIdentifier().equalsIgnoreCase(cell_value)){
                iri = n.getOWLClass().getIRI();
            }
            
        }
        
        return iri;
    }
    
    private AbstractBPMNThing getBPMNThing(String assigned_id){
        AbstractBPMNThing thing = null;
        
        for(AbstractBPMNThing node : nodes){
            
            if(node.getAssignedIdentifier().equalsIgnoreCase(assigned_id)){
                thing = node;
                
            }
            
        }
        
        return thing;
    }

    public void extractProcessData(String sheet_name, int header_row_number) 
    {


        nodes = new HashSet<>();

        int column_id = CellReference.convertColStringToIndex("A");
        int column_text = CellReference.convertColStringToIndex("B");
        int column_type = CellReference.convertColStringToIndex("C");
        int column_target = CellReference.convertColStringToIndex("D");

        BPMN_IRI bpmn = BPMN_IRI.getInstance();

        sheet = workbook.getSheet(sheet_name);

        //get the flow nodes
        for (Row row : sheet) {

            if (row.getRowNum() > header_row_number) {

                Cell cell_id = row.getCell(column_id);
                Cell cell_text = row.getCell(column_text);
                Cell cell_type = row.getCell(column_type);

                if (cell_type != null) {

                    IRI iri_type = this.getCellIRIType(cell_type);
                    Optional<Cell> celllabel = Optional.ofNullable(cell_text);
                    String label = (celllabel.isPresent()) ? cell_text.getStringCellValue() : "";
                    
                    if (iri_type.equals(bpmn.TASK())) 
                    {

                        Task task = new Task();

                        if (cell_id != null) {
                            task.setLabel(label);
                            task.setAssignedIdentifier(cell_id.getStringCellValue());
                            
                            
                            nodes.add(task);
                        }

                    }
                    else if (iri_type.equals(bpmn.TIMER_EVENT()))
                    {
                        
                        TimerEvent timer_event = new TimerEvent();
                        
                        if(cell_id !=null){
                            timer_event.setLabel(label);
                            timer_event.setAssignedIdentifier(cell_id.getStringCellValue());
                            nodes.add(timer_event);
                            
                        }
                        
                    }
                    else if (iri_type.equals(bpmn.RESOURCE()))
                    {
                        Resource resource = new Resource();
                        
                        if(cell_id != null){
                            resource.setLabel(label);
                            resource.setAssignedIdentifier(cell_id.getStringCellValue());
                            nodes.add(resource);
                        }
                        
                    }
                    else if(iri_type.equals(bpmn.GATEWAY())){
                        
                        Gateway gateway = new Gateway();
                        
                        if(cell_id != null){
                            gateway.setLabel(label);
                            gateway.setAssignedIdentifier(cell_id.getStringCellValue());
                            nodes.add(gateway);
                        }
                    }
                    else if(iri_type.equals(bpmn.START_EVENT())){
                        
                        StartEvent start_event = new StartEvent();
                        
                        if(cell_id != null){
                            start_event.setLabel(label);
                            start_event.setAssignedIdentifier(cell_id.getStringCellValue());
                            nodes.add(start_event);
                        }
                        
                    }
                    else if(iri_type.equals(bpmn.END_EVENT())){
                        
                        EndEvent end_event = new EndEvent();
                        
                        if(cell_id != null){
                            end_event.setLabel(label);
                            end_event.setAssignedIdentifier(cell_id.getStringCellValue());
                            nodes.add(end_event);
                        }
                        
                    }
                    else if (iri_type.equals(bpmn.SUBPROCESS())) {
                        Subprocess subprocess = new Subprocess();

                        if (cell_id != null) {
                            subprocess.setLabel(label);
                            
                            subprocess.setAssignedIdentifier(cell_id.getStringCellValue());
                            
                            nodes.add(subprocess);

                        }
                    }
                    else if(iri_type.equals(bpmn.TEXTUAL_ENTITY())){
                        TextEntity text_entity = new TextEntity();
                        
                        if(cell_id != null){
                            text_entity.setLabel(label);
                            text_entity.setAssignedIdentifier(cell_id.getStringCellValue());
                            
                            nodes.add(text_entity);
                            
                        }
                        
                    }
                    else{
                        System.out.println("Something is missing");
                    }

                }

            }

        }

        
        sequence_links = new HashSet<>();
        resource_links = new HashSet<>();
        //get the sequence flow
        for(Row row : sheet){
            
            if(row.getRowNum() > header_row_number){
                
                Cell cell_id = row.getCell(column_id);
                Cell target_cell_id = row.getCell(column_target);
                
                if (cell_id != null && target_cell_id !=null) {
                    
               
                    
                    String source_id = cell_id.getStringCellValue();
                    String target_id = target_cell_id.getStringCellValue();

                    AbstractBPMNThing source = this.getBPMNThing(source_id);
                    AbstractBPMNThing target = this.getBPMNThing(target_id);
                    
                    if(Utility.isAFlowElement(source) && Utility.isAFlowElement(target)){
                        ReferenceLink reference_link = new ReferenceLink();

                        reference_link.setNodes(source, target);
                        reference_link.setProperty(BPMN_IRI.getInstance().SEQUENCE_FLOW());
                        sequence_links.add(reference_link);
                        
                    }
                    else if (Utility.isAResourceElement(source) && Utility.isAFlowElement(target)){
                        
                        InputOutputLink reference_link = new InputOutputLink();
                        
                        reference_link.setNodes(target, source);
                        reference_link.setProperty(BPMN_IRI.getInstance().HAS_RESOURCE_INPUT());
                        resource_links.add(reference_link);
                        
                    }
                    else if (Utility.isAFlowElement(source) && Utility.isAResourceElement(target)){
                        
                        InputOutputLink reference_link = new InputOutputLink();
                        
                        reference_link.setNodes(source, target);
                        reference_link.setProperty(BPMN_IRI.getInstance().HAS_RESOURCE_OUTPUT());
                        resource_links.add(reference_link);
                        
                    }
                    else if(Utility.isMiscEntity(source) && Utility.isAFlowElement(target)){
                        ReferenceLink reference_link = new ReferenceLink();
                        
                        reference_link.setNodes(source, target);
                        reference_link.setProperty(BPMN_IRI.getInstance().DENOTES());
                        sequence_links.add(reference_link);
                    }
                    
                }
                
                
            }
            
        }
        
        
        
    }

    
    public Set<Task> getTasks() {
        
        
        Set<Task> tasks = new HashSet<Task>();
        
        nodes.forEach(b->{
            
            if( b instanceof Task){
                tasks.add((Task) b);
            }
        });
        
        return tasks;
    }
    
    public Set<? extends AbstractBPMNThing> getBPMN_Nodes(){
        
        return nodes;
    }
    
    public Set<? extends AbstractBPMNPropertyLink> getBPMN_Links(){
        //System.out.println(sequence_links.size());
        
        return sequence_links;
    }
    
    public Set<? extends AbstractBPMNPropertyLink> getResource_Links(){
        
        System.out.println(resource_links.size());
        
        return resource_links;
    }
    
    public static void main(String[] args) {

      
    }

}
