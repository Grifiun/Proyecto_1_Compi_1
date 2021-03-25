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
public class ComponenteImagen extends Componente{
    //Constructor
    public ComponenteImagen() {
        super();
    }      

    @Override
    public String generarCodigoHTMLCompoente() {  
        String codigoHTML = "<div class=\"form-group\">\n";
        codigoHTML = codigoHTML + "<h5>"+getTextoVisible()+"</h5> \n";        
        codigoHTML = codigoHTML +" <input type=\"image\" id=\""+getIdComponente()+"\" src=\""+getURL()+"\"/> \n";       
        codigoHTML = codigoHTML + "</div>\n";
        /*
        <div class="form-group">
        <input type="image" id="id_etiqueta" src="url-image" />
        </div>   
        */
        return codigoHTML;
    }
}
