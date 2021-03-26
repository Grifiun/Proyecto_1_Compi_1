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
    
    public String generarCodigoAlmacenamiento(){
        String codigoAlmacenamiento = "{\n";
        
        if(id.equals("") == false){
            codigoAlmacenamiento += "\"ID_FORMULARIO\" : "+id+",\n";
        }
        if(titulo.equals("") == false){
            codigoAlmacenamiento += "\"TITULO\" : "+titulo+",\n";
        }
        if(nombre.equals("") == false){
            codigoAlmacenamiento += "\"NOMBRE\" : "+nombre+",\n";
        }
        if(tema.equals("") == false){
            codigoAlmacenamiento += "\"TEMA\" : "+tema+",\n";
        }
        
        codigoAlmacenamiento += generarCodigoEstructura() + ",\n";
        
        if(codigoAlmacenamiento.equals("{\n") == false){//tiene datos
            int longitud = codigoAlmacenamiento.length() - 2;
            codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
        }        

       codigoAlmacenamiento = codigoAlmacenamiento + "\n}\n";
        
        return codigoAlmacenamiento;
    }
    
    public String generarCodigoEstructura(){
        String codigoEstructura = "";
        
        if(listadoComponentes != null && listadoComponentes.size() > 0){
            codigoEstructura +=  "\"ESTRUCTURA\" : (\n";
            
            for(Componente componenteAux: listadoComponentes){
                codigoEstructura += componenteAux.generarCodigoAlmacenamiento() + ",\n";
                
            }
            if(codigoEstructura.equals("") == false){//tiene datos
                int longitud = codigoEstructura.length() - 2;
                codigoEstructura = codigoEstructura.substring(0, longitud); //removemos la ultima coma y el salto de linea
            }  
            
            codigoEstructura +=  "\n)\n";
        } 
       
        return codigoEstructura;
    }
    
    /**
     * Funcion para generar el codigo de un formulario
     * @return 
     */
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

