/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesDAOFormularios;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author grifiun
 */
public abstract class Componente {
    private String idComponente;
    private String nombreCampo;
    private String formularioId;
    private String clase;
    private String indice;
    private String textoVisible;    
    private String alineacion;
    private String requerido;
    private ArrayList<String> opciones;
    private String filas;
    private String columnas;
    private String URL;   
    
    public Componente(){
        this.idComponente = "";
        this.nombreCampo = "";
        this.formularioId = "";
        this.clase = "";
        this.indice = "";
        this.textoVisible = "";    
        this.alineacion = "";
        this.requerido = "";
        ArrayList<String> opciones = new ArrayList();
        this.filas = "";
        this.columnas = "";
        this.URL = "";          
    }
    
    public abstract String generarCodigoHTMLCompoente();    

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public void setAlineacion(String alineacion) {
        this.alineacion = alineacion;
    }   

    public void setIdComponente(String idComponente) {
        this.idComponente = idComponente;
    }

    public void setFormularioId(String formularioId) {
        this.formularioId = formularioId;
    }

    public void setTextoVisible(String textoVisible) {
        this.textoVisible = textoVisible;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getIdComponente() {
        return idComponente;
    }

    public String getFormularioId() {
        return formularioId;
    }

    public String getTextoVisible() {
        return textoVisible;
    }

    public String getClase() {
        return clase;
    }

    public String getIndice() {
        return indice;
    }

    public String getAlineacion() {
        return alineacion;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public String getRequerido() {
        return requerido;
    }

    public void setRequerido(String requerido) {
        this.requerido = requerido;
    }

    public ArrayList<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        String[] auxOp = opciones.split("|");        
        this.opciones = new ArrayList<>(Arrays.asList(auxOp));
    }

    public String getFilas() {
        return filas;
    }

    public void setFilas(String filas) {
        this.filas = filas;
    }

    public String getColumnas() {
        return columnas;
    }

    public void setColumnas(String columnas) {
        this.columnas = columnas;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    
    
    
}
