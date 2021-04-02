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
        codigoHTML = codigoHTML + "<input type=\"submit\" value=\""+getTextoVisible().replaceAll("\"", "").trim()+"\"/>";
        codigoHTML = codigoHTML + "</div>\n";
        return codigoHTML;
    }
    
    @Override
    public String generarCodigoAlmacenamiento() {
        String codigoAlmacenamiento = "\t\t{\n";
        
        if(getIdComponente().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\t\t\"ID_COMPONENTE\" : "+getIdComponente()+",\n";
        }
        if(getClase().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\t\t\"CLASE\" : "+getClase()+",\n";
        }
        if(getIndice().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\t\t\"INDICE\" : "+getIndice()+",\n";
        }
        if(getTextoVisible().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\t\t\"TEXTO_VISIBLE\" : "+getTextoVisible()+",\n";
        }
        if(getAlineacion().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\t\t\"ALINEACION\" : "+getAlineacion()+",\n";
        }
        
        if(codigoAlmacenamiento.equals("\t\t{\n") == false){//tiene datos
            int longitud = codigoAlmacenamiento.length() - 2;
            codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
        }        
        
        codigoAlmacenamiento = codigoAlmacenamiento + "\n\t\t}\n";
        
        return codigoAlmacenamiento;
        
    }
    
    @Override
    public String generarCodigoExportado() {        
        return generarCodigoAlmacenamiento();
        
    }
    
    
}
