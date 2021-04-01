/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import clasesDAO.BloqueParametros;
import clasesDAO.TokenError;
import clasesDAO.TokenParametro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grifiun
 */
public class FuncionesGenerarCodigoRespuesta {
    
    public String generarCodigoRespuestaSolicitud(BloqueParametros bloqueAux, ArrayList<TokenError> listadoErrorSemanticoBloque){
        String tipoSolicitud = bloqueAux.getTipoToken();
        String tipoBloqueParametros = bloqueAux.getLexema();
        
        String respuesta = "            <!ini_respuesta: "+tipoSolicitud+" >\n";
               respuesta += "            {  "+tipoBloqueParametros+" :[{\n";
                
        for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
            int i = 0;
            if(parametroAux != null){    
                respuesta += "\tPARAMETRO : "+parametroAux.getTipoParametro()+" VALOR: "+parametroAux.getLexema()+",\n";

            }           
        }
        if(listadoErrorSemanticoBloque != null && listadoErrorSemanticoBloque.size() > 0){
            for(TokenError tok : listadoErrorSemanticoBloque){
                 respuesta += "\tRESPUESTA : \""+tok.getMsgError()+"\",\n";
            }          
        }else{             
            respuesta += "\tRESPUESTA : \"Se efectuo la solicitud sin problemas\",\n";
        }       
        
        if( bloqueAux.getListadoParametros() != null && bloqueAux.getListadoParametros().size() > 0){
            int longitud = respuesta.length() - 2;
            respuesta = respuesta.substring(0, longitud); //removemos la ultima coma y el salto de linea
        }
        
        
        respuesta += "     }         \n" +
                        "         ]\n" +
                        "      }\n" +
                        "	<!fin_respuesta>\n\n";
        
        return respuesta;
    }
    
    public String generarCodigoDatosConsultas(List<ArrayList<String>> datos){
        String tipoSolicitud = "TABLA_CONSULTA";
        String tipoBloqueParametros = "CONSULTAS";
        
        String respuesta = "            <!ini_respuesta:\""+tipoSolicitud+"\">\n";
               respuesta += "            { \""+tipoBloqueParametros+"\":[{\n\t";
                
               
        for(int i = 0; i < datos.size(); i++){
            for(int j = 0; j < datos.get(0).size(); j++){
                //System.out.print("  *  "+datos.get(i).get(j));
                if(i == 0){
                    respuesta += "PARAMETRO : "+"\""+datos.get(i).get(j)+"\"  "; 
                }else{
                    respuesta += "VALOR : "+"\""+datos.get(i).get(j)+"\"  "; 
                }                                                                
            }
            if(((i + 1) == datos.size()) == false){
               respuesta += ",\n\t";
            }else{
                respuesta += "\n\t";
            }
            //System.out.println("");
        }
                
        respuesta += "     }         \n" +
                        "         ]\n" +
                        "      }\n" +
                        "	<!fin_respuesta>\n\n";
        
        return respuesta;
    }
    
    
}
