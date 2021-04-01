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
    private String opcionesCadena;
    private String filas;
    private String columnas;
    private String URL;
    private ArrayList<Registro> listadoRegistros;
    
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
        this.opcionesCadena = "";
        this.filas = "";
        this.columnas = "";
        this.URL = "";     
        listadoRegistros = new ArrayList();
    }
    public abstract String generarCodigoAlmacenamiento();
    
    public String generarCodigoAlmacenamientoRegistros(){  
        String codigoAlmacenamientoRegistros = "\"DATOS_RECOPILADOS\" : (\n{\n\n";
        
        if(listadoRegistros != null && listadoRegistros.size() > 0){  
            int i = 1;
            for(Registro registroAux: listadoRegistros){
                 codigoAlmacenamientoRegistros += "     \"REGISTRO-"+i+"\" :  "+registroAux.getRegistroDato()+",\n";
                 i++;
            }                         
        }
        
        if(codigoAlmacenamientoRegistros.equals("\"DATOS_RECOPILADOS\" : (\n{\n\n") == false){//tiene datos
            int longitud = codigoAlmacenamientoRegistros.length() - 2;
            codigoAlmacenamientoRegistros = codigoAlmacenamientoRegistros.substring(0, longitud); //removemos la ultima coma y el salto de linea
            codigoAlmacenamientoRegistros += "      \n}\n)\n";
            
            //retornamos
            return codigoAlmacenamientoRegistros;
        }else{//si esta vacio no enviamos nada
            return "";
        }   
        
        
        
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
        this.opcionesCadena = opciones;
        String[] auxOp = opciones.split("\\|");  
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

    public String getOpcionesCadena() {
        return opcionesCadena;
    }

    public ArrayList<Registro> getListadoRegistros() {
        return listadoRegistros;
    }

    public void setListadoRegistros(ArrayList<Registro> listadoRegistros) {
        this.listadoRegistros = listadoRegistros;
    }
    
    public void agregarRegistro(String valorRegistro){
        Registro regAux = new Registro(valorRegistro);        
        this.listadoRegistros.add(regAux);
    }
    
    
    
}



/*
    @Override
    public String generarCodigoAlmacenamiento() {
        String codigoAlmacenamiento = "{\n";
         
        if(getIdComponente().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"ID_COMPONENTE\" : "+getIdComponente()+",\n";
        }
        if(getNombreCampo().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"NOMBRE_CAMPO\" : "+getNombreCampo()+",\n";
        }
        if(getClase().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"CLASE\" : "+getClase()+",\n";
        }
        if(getIndice().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"INDICE\" : "+getIndice()+",\n";
        }
        if(getTextoVisible().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"TEXTO_VISIBLE\" : "+getTextoVisible()+",\n";
        }
        if(getAlineacion().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"ALINEACION\" : "+getAlineacion()+",\n";
        }
        if(getRequerido().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"REQUERIDO\" : "+getRequerido()+",\n";
        }
        if(getOpcionesCadena().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"OPCIONES\" : "+getOpcionesCadena()+",\n";
        }
        if(getFilas().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"FILAS\" : "+getFilas()+",\n";
        }
        if(getColumnas().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"COLUMNAS\" : "+getColumnas()+",\n";
        }
        if(getURL().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"URL\" : "+getURL()+",\n";
        }

        if(codigoAlmacenamiento.equals("{\n") == false){//tiene datos
               int longitud = codigoAlmacenamiento.length() - 2;
               codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
           }        

       codigoAlmacenamiento = codigoAlmacenamiento + "}\n";

       return codigoAlmacenamiento;

   }

*/