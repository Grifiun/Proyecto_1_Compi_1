/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;


import clasesDAO.BloqueParametros;
import clasesDAO.TokenParametro;
import clasesDAOUsuario.Usuario;
import java.util.ArrayList;

/**
 * Funcones usuario
 * @author grifiun
 */
public class FuncionesUsuario {
    
    
    /**
     * Agregamos los parametros recibidos al objetod e usuario, y luego lo retornamos
     * @param bloqueAux
     * @param usuarioAux
     * @return 
     */
    public Usuario agregarDatosUsuario(BloqueParametros bloqueAux, Usuario usuarioAux){
        
        
        for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
            if(parametroAux != null){ //dependiendo del tipo de dato llamaremos a diferentes funciones                   
                switch(parametroAux.getTipoParametro()){
                    case "\"USUARIO\"":                                        
                            usuarioAux.setUsuario(parametroAux.getLexema());
                        break;
                    case "\"PASSWORD\"":                                        
                            usuarioAux.setPassword(parametroAux.getLexema());
                        break;
                    case "\"FECHA_CREACION\"":                                        
                            usuarioAux.setFechaCreacion(parametroAux.getLexema());
                        break;
                    case "\"FECHA_MODIFICACION\"":                                        
                            usuarioAux.setFechaModificacion(parametroAux.getLexema());
                        break;

                }                                  
            }
        }
    
        return usuarioAux;
    }
    
    /**
     * Funcion dedicada a la generacion del codigo de almacenamiento
     * @param listadoUsuarios
     * @return 
     */
    public String generarCodigoAlmacenamientoUsuarios(ArrayList<Usuario> listadoUsuarios){
        String codigoAlmacenamiento = "db.usuarios(\n";    
        
        
        if(listadoUsuarios != null && listadoUsuarios.size() > 0){
            for(Usuario usuarioAux: listadoUsuarios){
                codigoAlmacenamiento += usuarioAux.generarCodigoAlmacenamientoUsuario()+ ",\n";
            }
        }
        
        
        if(codigoAlmacenamiento.equals("db.usuarios(\n") == false){//si tiene datos
            int longitud = codigoAlmacenamiento.length() - 2;
            codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
        }
        
        codigoAlmacenamiento += "\n)\n";
        
        return codigoAlmacenamiento;
    }
    
    public boolean verificarNombreUsuario (ArrayList<Usuario> listadoUsuarios, String nombreUsuario){
        boolean existencia = false;
        
        if(listadoUsuarios != null && listadoUsuarios.size() > 0){
            for(Usuario usuarioAux: listadoUsuarios){
                //Nombre de usuario sin comillas ni espacios
                String nombreUsuarioAux = usuarioAux.getUsuario().replaceAll("\"", "").trim();
                String nombreUsuarioAuxComparar = nombreUsuario.replaceAll("\"", "").trim();
                
                if(nombreUsuarioAux.equals(nombreUsuarioAuxComparar)){                
                    existencia = true;//Existe un nombre de usuario
                    break;
                }
                
            }
        }
        
        return existencia;
    }
}
