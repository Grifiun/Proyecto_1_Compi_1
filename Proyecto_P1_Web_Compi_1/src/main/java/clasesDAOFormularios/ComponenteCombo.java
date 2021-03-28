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
public class ComponenteCombo extends Componente{
    //Constructor
    public ComponenteCombo() {
        super();
    }      

    @Override
    public String generarCodigoHTMLCompoente() {  
        String codigoHTML = "<div class=\"form-group\">\n";
        codigoHTML = codigoHTML + "<h5>"+getTextoVisible().replaceAll("\"", "").trim()+"</h5> \n"; 
        if(getRequerido().replaceAll("\"", "").trim().equalsIgnoreCase("SI")){
            codigoHTML = codigoHTML + " <select name=\""+getIdComponente().replaceAll("\"", "").trim()+"\" class=\"cont\" id=\""+getIdComponente().replaceAll("\"", "").trim()+"\" required> \n";
        }else{
            codigoHTML = codigoHTML + " <select name=\""+getIdComponente().replaceAll("\"", "").trim()+"\" class=\"cont\" id=\""+getIdComponente().replaceAll("\"", "").trim()+"\" > \n";
        }
        
        for(String auxTexto:  getOpciones()){
            if(auxTexto != null){
                codigoHTML = codigoHTML + "<option value=\""+auxTexto.replaceAll("\"", "").trim()+"\">"+auxTexto.replaceAll("\"", "").trim()+"</option> \n"; 
            }
        }
           
        codigoHTML = codigoHTML + "</select> \n"; 
        codigoHTML = codigoHTML + "</div> \n";
        /*
         <div class="form-group">
        <select name="sangre" class="cont" id="sangre" required>   
            <option value="" disabled selected hidden>Tipo de sangre</option>
            <option value="O-">O-</option>
            <option value="O+">O+</option>
            <option value="A-">A-</option>
            <option value="A+">A+</option>
            <option value="B-">B-</option>
            <option value="B+">B+</option>
            <option value="AB-">AB-</option>
            <option value="AB+">AB+</option>
        </select>
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
