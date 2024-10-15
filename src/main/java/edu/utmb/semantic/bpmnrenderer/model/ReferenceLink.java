/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.utmb.semantic.bpmnrenderer.model;

/**
 *
 * @author tuan
 */
public class ReferenceLink extends AbstractBPMNPropertyLink{
    
    
    public ReferenceLink(){
        
    }

    @Override
    public void setNodes(AbstractBPMNThing source, AbstractBPMNThing target) {
      
        
        this.setSource(source);
        this.setTarget(target);
        
        
    }
}
