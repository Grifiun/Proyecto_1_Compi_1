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
        codigoHTML = codigoHTML + "<h5>"+getTextoVisible()+"</h5> \n"; 
        if(getRequerido().equalsIgnoreCase("SI")){
            codigoHTML = codigoHTML + " <select name=\""+getNombreCampo()+"\" class=\"cont\" id=\""+getIdComponente()+"\" required> \n";
        }else{
            codigoHTML = codigoHTML + " <select name=\""+getNombreCampo()+"\" class=\"cont\" id=\""+getIdComponente()+"\" > \n";
        }
        
        for(String auxTexto:  getOpciones()){
            if(auxTexto != null){
                codigoHTML = codigoHTML + "<option value=\""+auxTexto+"\">"+auxTexto+"</option> \n"; 
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
}
