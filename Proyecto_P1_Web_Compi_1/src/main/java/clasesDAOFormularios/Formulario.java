/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesDAOFormularios;

import clasesDAO.BloqueParametros;
import clasesDAO.TokenParametro;
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
    private String publico;
    private String usuarioCreacion;
    private String fechaCreacion;
    
    ArrayList<Componente> listadoComponentes = new ArrayList();    
    
    public Formulario(){
        this.id = "";
        this.titulo = "";
        this.nombre = "";
        this.tema = ""; 
        this.publico = "\"NO\"";
        this.usuarioCreacion = "";//Hay que agregar el usuario logueado
        this.fechaCreacion = "";//Fecha
    }
    
    public String generarCodigoAlmacenamiento(){
        String codigoAlmacenamiento = "{\n";
        
        if(id.equals("") == false){
            codigoAlmacenamiento += "\t\"ID_FORMULARIO\" : "+id+",\n";
        }
        if(titulo.equals("") == false){
            codigoAlmacenamiento += "\t\"TITULO\" : "+titulo+",\n";
        }
        if(nombre.equals("") == false){
            codigoAlmacenamiento += "\t\"NOMBRE\" : "+nombre+",\n";
        }
        if(tema.equals("") == false){
            codigoAlmacenamiento += "\t\"TEMA\" : "+tema+",\n";
        }
        if(publico.equals("") == false){
            codigoAlmacenamiento += "\t\"PUBLICO\" : "+publico+",\n";
        }
        if(fechaCreacion.equals("") == false){
            codigoAlmacenamiento += "\t\"FECHA_CREACION\" : "+fechaCreacion+",\n";
        }
        if(usuarioCreacion.equals("") == false){
            codigoAlmacenamiento += "\t\"USUARIO_CREACION\" : "+usuarioCreacion+",\n";
        }
        if(listadoComponentes != null && listadoComponentes.size() > 0){
             codigoAlmacenamiento += generarCodigoEstructura() + ",\n";
        }
       
        
        if(codigoAlmacenamiento.equals("{\n") == false){//tiene datos
            int longitud = codigoAlmacenamiento.length() - 2;
            codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
        }        

       codigoAlmacenamiento = codigoAlmacenamiento + "\n}\n";
        
        return codigoAlmacenamiento;
    }
    
    public String generarCodigoExportado(){        
        String codigoAlmacenamiento = "{\n";
        
        if(id.equals("") == false){
            codigoAlmacenamiento += "\t\"ID_FORMULARIO\" : "+id+",\n";
        }
        if(titulo.equals("") == false){
            codigoAlmacenamiento += "\t\"TITULO\" : "+titulo+",\n";
        }
        if(nombre.equals("") == false){
            codigoAlmacenamiento += "\t\"NOMBRE\" : "+nombre+",\n";
        }
        if(tema.equals("") == false){
            codigoAlmacenamiento += "\t\"TEMA\" : "+tema+",\n";
        }
        if(listadoComponentes != null && listadoComponentes.size() > 0){
             codigoAlmacenamiento += generarCodigoEstructuraExportado() + ",\n";
        }       
        
        if(codigoAlmacenamiento.equals("{\n") == false){//tiene datos
            int longitud = codigoAlmacenamiento.length() - 2;
            codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
        }        

       codigoAlmacenamiento = codigoAlmacenamiento + "\n}\n";
        
        return "new.formulario(\n"+codigoAlmacenamiento+")";
    }
    
    public BloqueParametros generarBloqueParametros(){
        BloqueParametros bloqueParam = new BloqueParametros("\"IMPORTAR_FORMULARIO\"", "\"PARAMETROS_FORMULARIO\"");
        
        
        if(id.equals("") == false){
            bloqueParam.insertarParametro("\"ID_FORMULARIO\"", id); 
        }
        if(titulo.equals("") == false){
            bloqueParam.insertarParametro("\"TITULO\"", titulo); 
        }
        if(nombre.equals("") == false){
            bloqueParam.insertarParametro("\"NOMBRE\"", nombre); 
        }
        if(tema.equals("") == false){
            bloqueParam.insertarParametro("\"TEMA\"", tema); 
        }
        if(publico.equals("") == false){
            bloqueParam.insertarParametro("\"PUBLICO\"", publico); 
        }
        if(listadoComponentes != null && listadoComponentes.size() > 0){
             agregarParametrosEstructura(bloqueParam);
        }
        
        return bloqueParam;
    }
    
    public String generarCodigoEstructura(){
        String codigoEstructura = "";
        
        if(listadoComponentes != null && listadoComponentes.size() > 0){
            codigoEstructura +=  "\t\"ESTRUCTURA\" : (\n";
            
            for(Componente componenteAux: listadoComponentes){
                codigoEstructura += componenteAux.generarCodigoAlmacenamiento() + ",\n";
                
            }
            if(codigoEstructura.equals("") == false){//tiene datos
                int longitud = codigoEstructura.length() - 2;
                codigoEstructura = codigoEstructura.substring(0, longitud); //removemos la ultima coma y el salto de linea
            }  
            
            codigoEstructura +=  "\t\n\t)\n";
        } 
       
        return codigoEstructura;
    }
    
    public String generarCodigoEstructuraExportado(){
        String codigoEstructura = "";
        
        if(listadoComponentes != null && listadoComponentes.size() > 0){
            codigoEstructura +=  "\t\"ESTRUCTURA\" : (\n";
            
            for(Componente componenteAux: listadoComponentes){
                codigoEstructura += componenteAux.generarCodigoExportado() + ",\n";
                
            }
            if(codigoEstructura.equals("") == false){//tiene datos
                int longitud = codigoEstructura.length() - 2;
                codigoEstructura = codigoEstructura.substring(0, longitud); //removemos la ultima coma y el salto de linea
            }  
            
            codigoEstructura +=  "\t\n\t)\n";
        } 
       
        return codigoEstructura;
    }
    
    public BloqueParametros agregarParametrosEstructura(BloqueParametros bloqueParam){
        
        if(listadoComponentes != null && listadoComponentes.size() > 0){
            for(Componente componenteAux: listadoComponentes){
                bloqueParam.insertarParametro("\"ID_COMPONENTE\"", componenteAux.getIdComponente());                
            }
        } 
       
        return bloqueParam;
    }
    
    /**
     * Funcion para generar el codigo de un formulario
     * @return 
     */
    public String generarCodigoHTMLFormulario(){
        String codigoHTMLFormulario = "";
        String inicioFormulario = "<form class=\"col-12 caja2\" method=\"post\" action=\"../ControladorRegistro\"> \n";
        String tituloFormulario = "<h1>"+this.titulo+"</h1> \n";         
        String finFormulario = "</form> \n";
                
        
        codigoHTMLFormulario = codigoHTMLFormulario + inicioFormulario + tituloFormulario;
        codigoHTMLFormulario += "<div class=\"form-group\">\n" +
                            "     <h5>ID Formulario: </h5> \n    "+
                            "        <select class=\"cont\" name=\"ID_FORMULARIO\" id=\"ID_FORMULARIO\" required>\n" +
                            "              <option value=\""+this.id.replaceAll("\"", "").trim()+"\"> "+this.id.replaceAll("\"", "").trim()+"  </option>\n" +                       
                            "        </select>\n" +
                            "    </div>";
        
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

    public String getPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
}

