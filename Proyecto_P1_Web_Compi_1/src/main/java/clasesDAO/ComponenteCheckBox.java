/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesDAO;

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
        codigoHTML = codigoHTML + "<h5>"+getTextoVisible()+"</h5> \n"; 
        
        for(String auxTexto:  getOpciones()){
            if(auxTexto != null){
                if(getRequerido().equalsIgnoreCase("SI")){
                    codigoHTML = codigoHTML + " <input type=\"checkbox\" id=\""+getIdComponente()+"\" value=\""+auxTexto+"\" name=\""+getNombreCampo()+"\" required/> "+auxTexto+"\n";
                }else{
                    codigoHTML = codigoHTML + " <input type=\"checkbox\" id=\""+getIdComponente()+"\" value=\""+auxTexto+"\" name=\""+getNombreCampo()+"\"/> "+auxTexto+" \n";
                }

            }
        }
           
        codigoHTML = codigoHTML + "</div> \n";
        /*
         <div class="form-group">
        <input type="checkbox" id="id" name="nombre_campo" value="html">HTML

    </div> 
        
        */
        
        return codigoHTML;
    }
}
