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
public class SUPPORTING_IRI {

    public SUPPORTING_IRI() {

    }

//IAO textual entity
    private IRI IRI_TEXTUAL_ENTITY = IRI.create("http://purl.obolibrary.org/obo/IAO_0000300");
    
    private IRI IRI_DENOTES = IRI.create("http://purl.obolibrary.org/obo/IAO_0000219");

    public IRI TEXTUAL_ENTITY() {
        return IRI_TEXTUAL_ENTITY;
    }
    
    public IRI DENOTES(){
        return IRI_DENOTES;
    }

}
