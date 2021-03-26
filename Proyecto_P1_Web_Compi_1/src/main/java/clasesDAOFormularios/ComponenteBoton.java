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
public class ComponenteBoton extends Componente{
    //Constructor
    public ComponenteBoton() {
        super();
    }      

    @Override
    public String generarCodigoHTMLCompoente() {  
        String codigoHTML = "<div class=\"form-group\">\n";
        codigoHTML = codigoHTML + "<input type=\"submit\" value=\""+getTextoVisible()+"\"/>";
        codigoHTML = codigoHTML + "</div>\n";
        return codigoHTML;
    }
    
    @Override
    public String generarCodigoAlmacenamiento() {
        String codigoAlmacenamiento = "{\n";
        
        if(getIdComponente().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"ID_COMPONENTE\" : "+getIdComponente()+",\n";
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
        
        if(codigoAlmacenamiento.equals("{\n") == false){//tiene datos
            int longitud = codigoAlmacenamiento.length() - 2;
            codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
        }        
        
        codigoAlmacenamiento = codigoAlmacenamiento + "\n}\n";
        
        return codigoAlmacenamiento;
        
    }
    
    
    
    
}
