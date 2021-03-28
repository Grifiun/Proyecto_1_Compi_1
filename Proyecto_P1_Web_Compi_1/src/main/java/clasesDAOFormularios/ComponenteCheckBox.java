/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesDAOFormularios;

import clasesDAOFormularios.Componente;

/**
 *
 * @author grifiun
 */
public class ComponenteCheckBox extends Componente{
    //Constructor
    public ComponenteCheckBox() {
        super();
    }      

    @Override
    public String generarCodigoHTMLCompoente() {  
        String codigoHTML = "<div class=\"form-group\">\n";
        codigoHTML = codigoHTML + "<h5>"+getTextoVisible().replaceAll("\"", "").trim()+"</h5> \n"; 
        int index = 0;
        for(String auxTexto:  getOpciones()){
            if(auxTexto != null){
                if(getRequerido().replaceAll("\"", "").trim().equalsIgnoreCase("SI")){
                    codigoHTML = codigoHTML + " <input type=\"checkbox\" id=\""+getIdComponente().replaceAll("\"", "").trim()+"\" value=\""+auxTexto.replaceAll("\"", "").trim()+"\" name=\""+getIdComponente().replaceAll("\"", "").trim()+"-"+index+"\" required/> "+auxTexto.replaceAll("\"", "").trim()+"\n";
                }else{
                    codigoHTML = codigoHTML + " <input type=\"checkbox\" id=\""+getIdComponente().replaceAll("\"", "").trim()+"\" value=\""+auxTexto.replaceAll("\"", "").trim()+"\" name=\""+getIdComponente().replaceAll("\"", "").trim()+"-"+index+"\"/> "+auxTexto.replaceAll("\"", "").trim()+" \n";
                }

            }
            index++;
        }
           
        codigoHTML = codigoHTML + "</div> \n";
        /*
         <div class="form-group">
        <input type="checkbox" id="id" name="nombre_campo" value="html">HTML

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
        if(getOpcionesCadena().equals("") == false){//no esta vacio
            codigoAlmacenamiento += "\"OPCIONES\" : "+getOpcionesCadena()+",\n";
        }
        
        if(codigoAlmacenamiento.equals("{\n") == false){//tiene datos
               int longitud = codigoAlmacenamiento.length() - 2;
               codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
           }        

       codigoAlmacenamiento = codigoAlmacenamiento + "\n}\n";
        if(getListadoRegistros() != null && getListadoRegistros().size() > 0){//si hay registros
             codigoAlmacenamiento += generarCodigoAlmacenamientoRegistros();
        }
       
       return codigoAlmacenamiento;

   }
}
