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
import clasesDAOFormularios.Formulario;
import clasesDAOUsuario.Usuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sesionCliente.UsuarioLogueado;

/**
 *
 * @author grifiun
 */
public class FuncionesFormularios {
    
    
    /**
     * Generamos un nuevo formulario a partir de un bloque de parametros
     * @param bloqueAux
     * @return 
     */
    public Formulario agregarDatosFormulario(BloqueParametros bloqueAux){
        Formulario formularioAux = new Formulario();
        
        for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
            if(parametroAux != null){ //dependiendo del tipo de dato llamaremos a diferentes funciones                  
                formularioAux = agregarParametroFormulario(formularioAux, parametroAux);
            }
        }
        
        return formularioAux;
    }
    
    /**
     * Agregar un parametro individual
     * @param formularioAux
     * @param parametroAux
     * @return 
     */
    public Formulario agregarParametroFormulario(Formulario formularioAux, TokenParametro parametroAux){    
        String tipoParametro = parametroAux.getTipoParametro();
        String nuevoValor = parametroAux.getLexema();
        
        formularioAux = editarCampo(formularioAux, tipoParametro, nuevoValor);        
        
        return formularioAux;
    }
    
    /**
     * Agregar un parametro individual
     * @param formularioAux
     * @param parametroAux
     * @return 
     */
    public Formulario editarCampo(Formulario formularioAux, String tipoParametro, String nuevoValor){        
        System.out.println("Tipo parametro: "+tipoParametro);
        switch(tipoParametro){
            case "\"ID\"":                                        
                    formularioAux.setId(nuevoValor);
                break;
            case "\"ID_FORMULARIO\"":   //para el lenguaje de guardado de datos                                     
                    formularioAux.setId(nuevoValor);
                break;
            case "\"TITULO\"":                                        
                    formularioAux.setTitulo(nuevoValor);
                break;
            case "\"NOMBRE\"":                                        
                    formularioAux.setNombre(nuevoValor);
                break;
            case "\"TEMA\"":                                        
                    formularioAux.setTema(nuevoValor);
                break;
            case "\"PUBLICO\"":                                        
                    formularioAux.setPublico(nuevoValor);
                break;
            case "\"USUARIO_CREACION\"":                                        
                    formularioAux.setUsuarioCreacion(nuevoValor);
                break;
            case "\"FECHA_CREACION\"":                                        
                    formularioAux.setFechaCreacion(nuevoValor);
                break;
        }
        
        return formularioAux;
    }
    
    
    /**
     * Generamos el codigo de almacenamiento de un listado de formuarlos
     * @param listadoFormularios
     * @return 
     */
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
    
    /**
     * Se recibe un listado de formularios y un id, se retorna si ya existe ese formulario o no
     * @param listadoFormularios
     * @param idFormulario
     * @return 
     */
    public boolean verificaraFormularioConId (ArrayList<Formulario> listadoFormularios, String idFormulario){
        boolean existencia = false;
        
        if(listadoFormularios != null && listadoFormularios.size() > 0){
            for(Formulario formularioAux: listadoFormularios){
                //Id de formulario sin comillas ni espacios
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                String idFormularioEnviado = idFormulario.replaceAll("\"", "").trim();
                
                if(idFormularioCargado.equals(idFormularioEnviado)){                
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
    public ArrayList<Formulario> obtenerListadoFormulariosRegistradosDelUsuario(String usuario){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("formularios");//cargamos la data de los usuarios
        ArrayList<Formulario> listadoFormularioAux = cargarDatos.getListadoFormulariosCargados();
        
        ArrayList<Formulario> listadoFormulariosUsuario = new ArrayList();
        
        //Hacemos las comparaciones para obtener los formularios que creo el usuario ingresado
        if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
            for(Formulario formAux: listadoFormularioAux){
                if(formAux != null){
                    //removemos comillas y espacios en blanco al inicio y fin de cadena
                    String nombreUsuarioFormulario = formAux.getUsuarioCreacion().replaceAll("\"", "").trim();
                    System.out.println("USUARIO CREADOR: "+nombreUsuarioFormulario+"\n");
                    System.out.println("USUARIO ACTUAL: "+usuario+"\n");
                    if(nombreUsuarioFormulario.equals(usuario.trim())){//Si los 2 nombres coinciden
                        listadoFormulariosUsuario.add(formAux);//agregamos el formulario
                    }
                }
            } 
        } 
        
        return listadoFormulariosUsuario;
    }
    
    /**
     * FUNCION PARA EL SQFORM
     * Ejecutamos el lector, para obtener el listado de usuarios registrados
     * @return 
     */
    public Formulario obtenerFormularioPorIdYPorUsuarioCreador(String idNombreFormulario, String usuario){
        if(usuario == null){
            usuario = UsuarioLogueado.usuarioLogueado;
            usuario = usuario.replaceAll("\"", "").trim();
        }
        
        CargarDatos cargarDatos = new CargarDatos();        
        cargarDatos.leerDatos("formularios");//cargamos la data de los usuarios
        ArrayList<Formulario> listadoFormularioAux = cargarDatos.getListadoFormulariosCargados();
        
        System.out.println("ID/N FORM: "+idNombreFormulario);
        
        //Hacemos las comparaciones para obtener los formularios que creo el usuario ingresado
        
        try{
        
            if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
                for(Formulario formAux: listadoFormularioAux){
                    if(formAux != null){
                        //removemos comillas y espacios en blanco al inicio y fin de cadena    
                        String nombreUsuarioFormulario = "";
                        if(formAux.getUsuarioCreacion() != null && formAux.getUsuarioCreacion().length() > 0){
                             nombreUsuarioFormulario = formAux.getUsuarioCreacion().replaceAll("\"", "").trim();
                        }

                        String nombreFormulario = formAux.getNombre().replaceAll("\"", "").trim();
                        String idFormulario = formAux.getId().replaceAll("\"", "").trim();

                        System.out.println("USUARIO CREADOR: "+nombreUsuarioFormulario+"\n");
                        System.out.println("USUARIO ACTUAL: "+usuario+"\n");                   
                        if(nombreUsuarioFormulario.equals(usuario.trim())){//Si los 2 nombres coinciden, y si tienen id o nombre de form correctos
                            System.out.println("Es del usuario");                      
                            if(nombreFormulario.equals(idNombreFormulario)){   
                                System.out.println("RETORNAMOS 1");
                                return formAux;
                            }else if(idFormulario.equals(idNombreFormulario)){
                                System.out.println("RETORNAMOS 2");
                                return formAux;

                            }   
                        }


                    }
                } 
            } 
        } catch(Exception ex) {
            System.out.println("Se produjo un error al intenar obtener el formulario por id y por usuario creador: "+ex.getMessage());
        }
        
        System.out.println("RETORNAMOS NULL");
        //si no se encuentra se retorna null
        return null;
    }
    
    /**
     * Obtenemos los datos principales de los formularios
     * en un arreglo de Strings bidimencional
     * @param usuario
     * @return 
     */
    public List<ArrayList<String>> listadoDatosFormularios(String usuario){
        List<ArrayList<String>> listadoDatosFormularios = new ArrayList();
        ArrayList<Formulario> listadoFormulariosUsuario = obtenerListadoFormulariosRegistradosDelUsuario(usuario);
            
        if(listadoFormulariosUsuario != null && listadoFormulariosUsuario.size() > 0){
            for(Formulario formAux: listadoFormulariosUsuario){
                ArrayList<String> datosFormAux = new ArrayList<>(Arrays.asList(formAux.getId(),
                                                                               formAux.getTitulo(),
                                                                               formAux.getNombre(),
                                                                               formAux.getTema(),
                                                                               formAux.getPublico()));
                
                listadoDatosFormularios.add(datosFormAux);
            }
        }else{
            return null;
        }    
        
        return listadoDatosFormularios;
    }
    
    /**
     * Se obtiene el objeto Formulario por el id
     * @param listadoUsuarioAux
     * @param nombreUsuarioEnviado
     * @return 
     */
    public Formulario obtenerFormularioPorId(String idFormulario){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("formularios");//cargamos la data de los usuarios
        ArrayList<Formulario> listadoFormularioAux = cargarDatos.getListadoFormulariosCargados();        
        
        if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
            for(Formulario formularioAux: listadoFormularioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                idFormulario = idFormulario.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                
                if(idFormularioCargado.equals(idFormulario)){
                    return formularioAux;
                }
                
            }
        }
        
        //Si no concuerda con ningun usuario se retorna nulo
        return null;
    }
    
    /**
     * Se obtiene el objeto Formulario por el id
     * @param listadoUsuarioAux
     * @param nombreUsuarioEnviado
     * @return 
     */
    public Formulario obtenerFormularioPorId(String idFormulario, ArrayList<Formulario> listadoFormularioAux){   
        
        if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
            for(Formulario formularioAux: listadoFormularioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                idFormulario = idFormulario.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                
                if(idFormularioCargado.equals(idFormulario)){
                    return formularioAux;
                }
                
            }
        }
        
        //Si no concuerda con ningun usuario se retorna nulo
        return null;
    }
    
    /**
     * Se obtiene el objeto Formulario por el id
     * @param listadoUsuarioAux
     * @param nombreUsuarioEnviado
     * @return 
     */
    public void eliminarFormularioPorId(String idFormulario){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("formularios");//cargamos la data de los usuarios
        ArrayList<Formulario> listadoFormularioAux = cargarDatos.getListadoFormulariosCargados();   
        
        if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
            for(Formulario formularioAux: listadoFormularioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                idFormulario = idFormulario.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                
                if(idFormularioCargado.equals(idFormulario)){
                    //posicion en el listado
                    int index = listadoFormularioAux.indexOf(formularioAux);
                    //removemos el form
                    listadoFormularioAux.remove(formularioAux);                    
                }
                
            }
        }
        
        ///Guardamos los datos
        GuardarDatos gd = new GuardarDatos();
        gd.guardarDatos(generarCodigoAlmacenamientoFormularios(listadoFormularioAux),"formularios");
    }
    
    
    /**
     * Funcion para modificar datos de un formulario
     * @param idFormulario
     * @param tipoParametro
     * @param nuevoValor 
     */
    public void editarCampoFormulario(String idFormulario, String tipoParametro, String nuevoValor){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("formularios");//cargamos la data de los usuarios
        ArrayList<Formulario> listadoFormularioAux = cargarDatos.getListadoFormulariosCargados();   
        
        if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
            for(Formulario formularioAux: listadoFormularioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                idFormulario = idFormulario.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                
                if(idFormularioCargado.equals(idFormulario)){
                    int index = listadoFormularioAux.indexOf(formularioAux);
                    //Modificamos el form                    
                    formularioAux = editarCampo(formularioAux, tipoParametro, nuevoValor);                    
                    //actualizamos el form
                    listadoFormularioAux.set(index, formularioAux);
                    
                }
                
            }
        }
        
        ///Guardamos los datos
        GuardarDatos gd = new GuardarDatos();
        gd.guardarDatos(generarCodigoAlmacenamientoFormularios(listadoFormularioAux),"formularios");
    
    }
    
}
