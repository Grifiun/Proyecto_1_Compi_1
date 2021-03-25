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
public class ComponenteRadio extends Componente{
    //Constructor
    public ComponenteRadio() {
        super();
    }      

    @Override
    public String generarCodigoHTMLCompoente() {  
        String codigoHTML = "<div class=\"form-group\">\n";
        codigoHTML = codigoHTML + "<h5>"+getTextoVisible()+"</h5> \n"; 
        
        for(String auxTexto:  getOpciones()){
            if(auxTexto != null){
                if(getRequerido().equalsIgnoreCase("SI")){
                    codigoHTML = codigoHTML +" <input type=\"radio\" id=\""+getIdComponente()+"\" value=\""+auxTexto+"\" name=\""+getNombreCampo()+"\" required/> "+auxTexto+"\n";
                }else{
                    codigoHTML = codigoHTML + " <input type=\"radio\" id=\""+getIdComponente()+"\" value=\""+auxTexto+"\" name=\""+getNombreCampo()+"\"/> "+auxTexto+" \n";
                }

            }
        }
           
        codigoHTML = codigoHTML + "</div> \n";
        /*
         <div class="form-group">
        <input type="radio" id="id_etiqueta" value="menos18" name="nombre_campo"/>Menos de 18
    </div> 
        
        */
        
        return codigoHTML;
    }
}
