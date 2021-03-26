/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import clasesDAOFormularios.Formulario;
import java.util.ArrayList;

/**
 *
 * @author grifiun
 */
public class FuncionesFormularios {
    
    public String generarCodigoAlmacenamientoFormularios(ArrayList<Formulario> listadoFormularios){
        String codigoAlmacenamiento = "db.formularios(\n";    
        
        
        if(listadoFormularios != null && listadoFormularios.size() > 0){
            for(Formulario formularioAux: listadoFormularios){
                codigoAlmacenamiento += formularioAux.generarCodigoAlmacenamiento() + ",\n";
            }
        }
        
        
        if(codigoAlmacenamiento.equals("db.formularios(\n") == false){//si tiene datos
            int longitud = codigoAlmacenamiento.length() - 2;
            codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
        }
        
        codigoAlmacenamiento += "\n)\n";
        
        return codigoAlmacenamiento;
    }
}
