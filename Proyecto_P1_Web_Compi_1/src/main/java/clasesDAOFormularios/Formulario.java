/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesDAOFormularios;

import clasesDAO.BloqueParametros;
import java.util.ArrayList;

/**
 *
 * @author grifiun
 */
public class Formulario {
    //DATOS OBLIGATORIOS GENERALES
    private String id;
    private String titulo;
    private String nombre;
    private String tema;    
    
    
    ArrayList<Componente> listadoComponentes = new ArrayList();    
    
    public Formulario(){
        this.id = "";
        this.titulo = "";
        this.nombre = "";
        this.tema = "";        
    }
    
    public String generarCodigoHTMLFormulario(){
        String codigoHTMLFormulario = "";
        String inicioFormulario = "<form class=\"col-12 caja2\" method=\"post\" action=\"../ControladorIngresoRegistro\"> \n";
        String tituloFormulario = "<h1>"+this.titulo+"</h1> \n"; 
        String finFormulario = "</form> \n";
                
        
        codigoHTMLFormulario = codigoHTMLFormulario + inicioFormulario + tituloFormulario;
        
        for(Componente componenteAux: listadoComponentes){
            codigoHTMLFormulario = codigoHTMLFormulario + componenteAux.generarCodigoHTMLCompoente();
        }
        
        codigoHTMLFormulario = codigoHTMLFormulario + finFormulario;
        
        /*
            <form class="col-12 caja2" method="post" action="../ControladorIngresoRegistro" >    
            <h1>TITULO</h1>
            </form>
        */
        
        return codigoHTMLFormulario;
    }
    
    public void agregarComponente(Componente componenteAux){
        listadoComponentes.add(componenteAux);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public ArrayList<Componente> getListadoComponentes() {
        return listadoComponentes;
    }

    public void setListadoComponentes(ArrayList<Componente> listadoComponentes) {
        this.listadoComponentes = listadoComponentes;
    }
    
    
}

