/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionCliente;

/**
 *
 * @author grifiun
 */
public class UsuarioLogueado {
    public static String usuarioLogueado = "";//estado inicial
    
    public static void iniciarSesion(String nombreUsuario){
        usuarioLogueado = nombreUsuario;
    }
    
    public static void cerrarSesion(){
        usuarioLogueado = "";
    }
    
    public static boolean isLogin(){
        if(usuarioLogueado.equals("")){
            return false;
        }else{
            return true;
        }
    }
}
