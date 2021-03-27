/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesDAOFormularios;

/**
 *
 * @author grifiun
 */
public class ComponenteAreaTexto extends Componente{
    //Constructor
    public ComponenteAreaTexto() {
        super();
    }      

    
    
    @Override
    public String generarCodigoHTMLCompoente() {  
        String codigoHTML = "<div class=\"form-group\">\n";
        codigoHTML = codigoHTML + "<h5>"+getTextoVisible().replaceAll("\"", "").trim()+"</h5> \n";        
        
        if(getRequerido().equalsIgnoreCase("SI")){
            codigoHTML = codigoHTML + " <textarea id=\""+getIdComponente().replaceAll("\"", "").trim()+"\" rows = \""+getFilas().replaceAll("\"", "").trim()+"\" cols = \""+getColumnas().replaceAll("\"", "").trim()+"\" name=\""+getNombreCampo().replaceAll("\"", "").trim()+"\" required ></textarea> \n";
        }else{
            codigoHTML = codigoHTML + " <textarea id=\""+getIdComponente().replaceAll("\"", "").trim()+"\" rows = \""+getFilas().replaceAll("\"", "").trim()+"\" cols = \""+getColumnas().replaceAll("\"", "").trim()+"\" name=\""+getNombreCampo().replaceAll("\"", "").trim()+"\"  ></textarea> \n";
        }
        
        
        codigoHTML = codigoHTML + "</div>\n";
        /*
        <div class="form-group">
        <textarea id="id_etiqueta" rows="numerofilas" cols="numerocolumnas" name="nombre_campo"></textarea>

        </div>   
        */
        return codigoHTML;
    }

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
        if(getFilas().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"FILAS\" : "+getFilas()+",\n";
        }
        if(getColumnas().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"COLUMNAS\" : "+getColumnas()+",\n";
        }
        
        if(codigoAlmacenamiento.equals("{\n") == false){//tiene datos
            int longitud = codigoAlmacenamiento.length() - 2;
            codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
        }        
        
        codigoAlmacenamiento = codigoAlmacenamiento + "\n}\n";
        
        return codigoAlmacenamiento;
        
    }
}

