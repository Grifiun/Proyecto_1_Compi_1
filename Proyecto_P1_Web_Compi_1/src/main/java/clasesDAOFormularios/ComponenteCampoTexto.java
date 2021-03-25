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
public class ComponenteCampoTexto extends Componente{
    //Constructor
    public ComponenteCampoTexto() {
        super();
    }      

    @Override
    public String generarCodigoHTMLCompoente() {  
        String codigoHTML = "<div class=\"form-group\">\n";
        codigoHTML = codigoHTML + "<h5>"+getTextoVisible()+"</h5> \n";        
        
        if(getRequerido().equalsIgnoreCase("SI")){
            codigoHTML = codigoHTML + " <input type=\"text\" id=\""+getIdComponente()+"\" name=\""+getNombreCampo()+"\" required /> \n";
        }else{
            codigoHTML = codigoHTML + " <input type=\"text\" id=\""+getIdComponente()+"\" name=\""+getNombreCampo()+"\"  /> \n";
        }        
        
        codigoHTML = codigoHTML + "</div>\n";
        /*
        <div class="form-group">
         <input type="text" id="id_etiqueta" name="nombre_campo" />

        </div>   
        */
        return codigoHTML;
    }
}
