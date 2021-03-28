/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;


import archivos.CargarDatos;
import archivos.GuardarDatos;
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
    public Usuario agregarDatosUsuario(BloqueParametros bloqueAux){
        
        Usuario usuarioAux = new Usuario();
        
        for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
            if(parametroAux != null){ //dependiendo del tipo de dato llamaremos a diferentes funciones     
                usuarioAux = editarCampo(usuarioAux, parametroAux.getTipoParametro(), parametroAux.getLexema());
            }
        }
    
        return usuarioAux;
    }
    
    
    
    public Usuario editarCampo(Usuario usuarioAux, String tipoParametro, String nuevoValor){
        
        switch(tipoParametro){
            case "\"USUARIO\"":                                        
                    usuarioAux.setUsuario(nuevoValor);
                break;
            case "\"PASSWORD\"":                                        
                    usuarioAux.setPassword(nuevoValor);
                break;
            case "\"FECHA_CREACION\"":                                        
                    usuarioAux.setFechaCreacion(nuevoValor);
                break;
            case "\"FECHA_MODIFICACION\"":                                        
                    usuarioAux.setFechaModificacion(nuevoValor);
                break;

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
    
    /**
     * Ejecutamos el lector, para obtener el listado de usuarios registrados
     * @return 
     */
    public ArrayList<Usuario> obtenerListadoUsuariosRegistrados(){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("usuarios");//cargamos la data de los usuarios
        ArrayList<Usuario> listadoUsuarioAux = cargarDatos.getListadoUsuariosCargados();
        
        return listadoUsuarioAux;
    }
    
    /**
     * Se obtiene el objeto usuario por un nombre de usuario
     * @param listadoUsuarioAux
     * @param nombreUsuarioEnviado
     * @return 
     */
    public Usuario obtenerUsuarioConNombre(ArrayList<Usuario> listadoUsuarioAux, String nombreUsuarioEnviado){
       
        if(listadoUsuarioAux != null && listadoUsuarioAux.size() > 0){
            for(Usuario usuarioAux: listadoUsuarioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String nombreUsuarioEntrada = usuarioAux.getUsuario().replaceAll("\"", "").trim();
                nombreUsuarioEnviado = nombreUsuarioEnviado.replaceAll("\"", "").trim();//removemos espacios
                
                if(nombreUsuarioEntrada.equals(nombreUsuarioEnviado)){
                    return usuarioAux;
                }
                
            }
        }
        
        //Si no concuerda con ningun usuario se retorna nulo
        return null;
    }
    
    /**
     * Funcion dedicada a modificar parametros por el nombre de usuario
     * @param nombre 
     */
    public void modificarDatosUsuario(String nombreUsuarioEnviado, String tipoParametro, String nuevoValor){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("usuarios");//cargamos la data de los usuarios
        ArrayList<Usuario> listadoUsuarioAux = cargarDatos.getListadoUsuariosCargados();
    
         if(listadoUsuarioAux != null && listadoUsuarioAux.size() > 0){
            for(Usuario usuarioAux: listadoUsuarioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String nombreUsuarioEntrada = usuarioAux.getUsuario().replaceAll("\"", "").trim();
                nombreUsuarioEnviado = nombreUsuarioEnviado.replaceAll("\"", "").trim();//removemos espacios
                
                if(nombreUsuarioEntrada.equals(nombreUsuarioEnviado)){                    
                    int index = listadoUsuarioAux.indexOf(usuarioAux);                    
                    //Modificamos el form
                    usuarioAux = editarCampo(usuarioAux, tipoParametro, nuevoValor);
                    //actualizamos el form
                    listadoUsuarioAux.set(index, usuarioAux);
                    
                }
                
            }
        }
        
        //Guardamos los cambios
        GuardarDatos gd = new GuardarDatos();
        gd.guardarDatos(generarCodigoAlmacenamientoUsuarios(listadoUsuarioAux),"usuarios");
        
    }
        
    
    /**
     * Funcion dedicada a eliminar usuarios por el nombre de usuario
     * @param nombre 
     */
    public void eliminarUsuarioPorNombre(String nombreUsuarioEnviado){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("usuarios");//cargamos la data de los usuarios
        ArrayList<Usuario> listadoUsuarioAux = cargarDatos.getListadoUsuariosCargados();
    
         if(listadoUsuarioAux != null && listadoUsuarioAux.size() > 0){
            for(Usuario usuarioAux: listadoUsuarioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String nombreUsuarioEntrada = usuarioAux.getUsuario().replaceAll("\"", "").trim();
                nombreUsuarioEnviado = nombreUsuarioEnviado.replaceAll("\"", "").trim();//removemos espacios
                
                if(nombreUsuarioEntrada.equals(nombreUsuarioEnviado)){
                    //removemos el objeto
                    listadoUsuarioAux.remove(usuarioAux);
                }
                
            }
        }
        
        //Guardamos los cambios
        GuardarDatos gd = new GuardarDatos();
        gd.guardarDatos(generarCodigoAlmacenamientoUsuarios(listadoUsuarioAux),"usuarios");
        
    }
}
