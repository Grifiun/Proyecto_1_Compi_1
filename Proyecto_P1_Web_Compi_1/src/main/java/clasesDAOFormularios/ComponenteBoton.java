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
    
}
