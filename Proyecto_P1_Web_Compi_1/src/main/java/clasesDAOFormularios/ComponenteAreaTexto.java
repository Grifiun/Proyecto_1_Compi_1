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
public class ComponenteAreaTexto extends Componente{
    //Constructor
    public ComponenteAreaTexto() {
        super();
    }      

    @Override
    public String generarCodigoHTMLCompoente() {  
        String codigoHTML = "<div class=\"form-group\">\n";
        codigoHTML = codigoHTML + "<h5>"+getTextoVisible()+"</h5> \n";        
        
        if(getRequerido().equalsIgnoreCase("SI")){
            codigoHTML = codigoHTML + " <textarea id=\""+getIdComponente()+"\" rows = \""+getFilas()+"\" cols = \""+getColumnas()+"\" name=\""+getNombreCampo()+"\" required ></textarea> \n";
        }else{
            codigoHTML = codigoHTML + " <textarea id=\""+getIdComponente()+"\" rows = \""+getFilas()+"\" cols = \""+getColumnas()+"\" name=\""+getNombreCampo()+"\"  ></textarea> \n";
        }
        
        
        codigoHTML = codigoHTML + "</div>\n";
        /*
        <div class="form-group">
        <textarea id="id_etiqueta" rows="numerofilas" cols="numerocolumnas" name="nombre_campo"></textarea>

        </div>   
        */
        return codigoHTML;
    }
}
