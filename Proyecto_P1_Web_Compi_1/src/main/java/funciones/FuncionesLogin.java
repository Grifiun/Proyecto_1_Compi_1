/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import clasesDAOUsuario.Usuario;
import java.util.ArrayList;

/**
 *
 * @author grifiun
 */
public class FuncionesLogin {
    
    FuncionesUsuario funcionesUsuario = new FuncionesUsuario();
    
    public String verificarUsuarioLogin (String nombreUsuario, String password){
        nombreUsuario = nombreUsuario.trim();
        password = password.trim();
        ArrayList<Usuario> listadoUsuarioAux = funcionesUsuario.obtenerListadoUsuariosRegistrados();
        
        if(listadoUsuarioAux == null || listadoUsuarioAux.size() <= 0){//no hay usuaruos en el sistema
             return "No existen usuarios en el sistema";           
            
        }
        
        Usuario usuarioAux = funcionesUsuario.obtenerUsuarioConNombre(listadoUsuarioAux , nombreUsuario);

        if(usuarioAux == null){//si no existe el usuario
            return "Usuario inexistente en el sistema";
        }else{
            //removemos las comillas y los espacios en blanco antes y despues de las cadenas
            String passwordCargado = usuarioAux.getPassword().replaceAll("\"", "").trim();

            if(password.equals(passwordCargado) == false)
                return "Usuario existente pero password mal ingresada";
            else//Si no hubo ningun conflicto, entonces se ingresa el usuario
                return "Usuario logueado con exito";
        }
             
        
        
       
    }    
}
