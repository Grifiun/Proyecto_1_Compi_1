/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import archivos.CargarDatos;
import archivos.GuardarDatos;
import clasesDAO.BloqueParametros;
import clasesDAOFormularios.ComponenteCheckBox;
import clasesDAO.TokenParametro;
import clasesDAOFormularios.Componente;
import clasesDAOFormularios.ComponenteAreaTexto;
import clasesDAOFormularios.ComponenteBoton;
import clasesDAOFormularios.ComponenteCampoTexto;
import clasesDAOFormularios.ComponenteCombo;
import clasesDAOFormularios.ComponenteFichero;
import clasesDAOFormularios.ComponenteImagen;
import clasesDAOFormularios.ComponenteRadio;
import clasesDAOFormularios.Formulario;
import java.util.ArrayList;

/**
 *
 * @author grifiun
 */
public class FuncionesComponentes {
    /**
     * Funcion dedicada a crear componentes dependiendo de su clase
     * @param clase
     * @return 
     */
    public Componente crearComponentePorClase(String clase){
        Componente componenteAux;
         switch (clase){
            case "\"AREA_TEXTO\"":
                componenteAux = new ComponenteAreaTexto();
                /*Llenamos el objeto con cada dato*/

                break;
            case "\"CHECKBOX\"":
                componenteAux = new ComponenteCheckBox();
                /*Llenamos el objeto con cada dato*/

                break;
            case "\"RADIO\"":
                componenteAux = new ComponenteRadio();
                /*Llenamos el objeto con cada dato*/

                break;
            case "\"FICHERO\"":
                componenteAux = new ComponenteFichero();
                /*Llenamos el objeto con cada dato*/

                break;
            case "\"IMAGEN\"":
                componenteAux = new ComponenteImagen();
                /*Llenamos el objeto con cada dato*/

                break;
            case "\"BOTON\"":
                componenteAux = new ComponenteBoton();
                /*Llenamos el objeto con cada dato*/

                break;
            case "\"COMBO\"":
                componenteAux = new ComponenteCombo();
                /*Llenamos el objeto con cada dato*/

                break;   
            default://campo texto, clase por defecto
                componenteAux = new ComponenteCampoTexto();
                /*Llenamos el objeto con cada dato*/
        }
        
        return componenteAux;
    
    }
    
    /**
     * Funcion guiada para agregar parametros al compoente de un formulario
     * se recibe el componente y el bloque dde parametros
     * Finalizamos la funcion retornando el componente previamente llenado
     * @param bloqueAux
     * @param componenteAux
     * @return 
     */
    public Componente agregarDatosCompoente(BloqueParametros bloqueAux, Componente componenteAux){
    
        for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
            if(parametroAux != null){ //dependiendo del tipo de dato llamaremos a diferentes funciones                   
                editarCampo(componenteAux, parametroAux.getTipoParametro(), parametroAux.getLexema());
            }
        }
        
        return componenteAux;
    }
    
    public Componente editarCampo(Componente componenteAux, String tipoParametro, String nuevoValor){
        switch(tipoParametro){
            case "\"ID\"":                                        
                    componenteAux.setIdComponente(nuevoValor);
            case "\"ID_COMPONENTE\"":  //Para el guardado de datos                                      
                    componenteAux.setIdComponente(nuevoValor);
                break;
            case "\"NOMBRE_CAMPO\"":                                        
                    componenteAux.setNombreCampo(nuevoValor);
                break;
            case "\"FORMULARIO\"":                                        
                    componenteAux.setFormularioId(nuevoValor);
                break;
            case "\"CLASE\"":                                        
                    componenteAux.setClase(nuevoValor);
                break;
            case "\"INDICE\"":                                        
                    componenteAux.setIndice(nuevoValor);
                break;
            case "\"TEXTO_VISIBLE\"":                                        
                    componenteAux.setTextoVisible(nuevoValor);
                break;
            case "\"ALINEACION\"":                                        
                    componenteAux.setAlineacion(nuevoValor);
                break;
            case "\"REQUERIDO\"":                                        
                    componenteAux.setRequerido(nuevoValor);
                break;
            case "\"OPCIONES\"":                                        
                    componenteAux.setOpciones(nuevoValor); 
                break;
            case "\"FILAS\"":                                        
                    componenteAux.setFilas(nuevoValor);
                break;
            case "\"COLUMNAS\"":                                        
                    componenteAux.setColumnas(nuevoValor);
                break;
            case "\"URL\"":                                        
                    componenteAux.setURL(nuevoValor);
                break;
        }        
        
        return componenteAux;
    }
    
    public ArrayList<Formulario> agregarComponenteFormularioPorId(Componente componente, ArrayList<Formulario> listadoFormularios){
        
        String idFormulario = componente.getFormularioId();
        String idComponente = componente.getIdComponente();
        
        if(listadoFormularios != null && listadoFormularios.size() > 0){
            for(Formulario formularioAux: listadoFormularios){
                //Id de formulario sin comillas ni espacios
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                idFormulario = idFormulario.replaceAll("\"", "").trim();
                
                if(idFormularioCargado.equals(idFormulario)){//ENCONTRAMOS EL FORMULARIO DESEADO   
                    int index = listadoFormularios.indexOf(formularioAux);
                    //Agregamos el componente al listado de componentes del formulario
                    formularioAux.agregarComponente(componente);
                    listadoFormularios.set(index, formularioAux);//actualizamos el listado
                    
                }                
            }
        }
        return listadoFormularios;
    }
    
    
    /**
     * Se obtiene el objeto Componente por el id, usando metodos de la funcion de fomularios
     * @param listadoUsuarioAux
     * @param nombreUsuarioEnviado
     * @return 
     */
    public Componente obtenerComponentePorId(String idFormulario, String idComponente){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("formularios");//cargamos la data de los usuarios
        ArrayList<Formulario> listadoFormularioAux = cargarDatos.getListadoFormulariosCargados();        
        
        if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
            for(Formulario formularioAux: listadoFormularioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                idFormulario = idFormulario.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                
                if(idFormularioCargado.equals(idFormulario)){
                    for(Componente componenteAux: formularioAux.getListadoComponentes()){//buscamos el compnente deseado
                        String idComponenteCargado = componenteAux.getIdComponente().replaceAll("\"", "").trim();
                        idComponente = idComponente.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                        
                        if(idComponenteCargado.equals(idComponente)){//se encontro el componente deseado
                            return componenteAux;
                        }
                    }  
                }
                
            }
        }
        
        //Si no concuerda con ningun usuario se retorna nulo
        return null;
    }
    
    /**
     * Se obtiene el objeto Componente por el id, usando metodos de la funcion de fomularios
     * @param listadoUsuarioAux
     * @param nombreUsuarioEnviado
     * @return 
     */
    public Componente obtenerComponentePorId(String idFormulario, String idComponente, ArrayList<Formulario> listadoFormularioAux){
        
        if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
            for(Formulario formularioAux: listadoFormularioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                idFormulario = idFormulario.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                
                if(idFormularioCargado.equals(idFormulario)){
                    for(Componente componenteAux: formularioAux.getListadoComponentes()){//buscamos el compnente deseado
                        String idComponenteCargado = componenteAux.getIdComponente().replaceAll("\"", "").trim();
                        idComponente = idComponente.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                        
                        if(idComponenteCargado.equals(idComponente)){//se encontro el componente deseado
                            return componenteAux;
                        }
                    }  
                }
                
            }
        }
        
        //Si no concuerda con ningun usuario se retorna nulo
        return null;
    }
    
    /**
     * Se elimina el componente por el id
     * @param listadoUsuarioAux
     * @param nombreUsuarioEnviado
     * @return 
     */
    public void eliminarComponentePorId(String idFormulario, String idComponente){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("formularios");//cargamos la data de los usuarios
        ArrayList<Formulario> listadoFormularioAux = cargarDatos.getListadoFormulariosCargados();   
        
        if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
            for(Formulario formularioAux: listadoFormularioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                idFormulario = idFormulario.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                
                if(idFormularioCargado.equals(idFormulario)){//Se encontro el formulario deseado
                    //posicion en el listado
                    for(Componente componenteAux: formularioAux.getListadoComponentes()){//buscamos el compnente deseado
                        String idComponenteCargado = componenteAux.getIdComponente().replaceAll("\"", "").trim();
                        idComponente = idComponente.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                        
                        if(idComponenteCargado.equals(idComponente)){//se encontro el componente deseado
                            int index = listadoFormularioAux.indexOf(formularioAux);
                            //REmovemos el componente del formulario
                            formularioAux.getListadoComponentes().remove(componenteAux);
                            //actualizamos el listado
                            listadoFormularioAux.set(index, formularioAux);
                        }
                    }                                     
                }
                
            }
        }
        
        ///Guardamos los datos
        GuardarDatos gd = new GuardarDatos();
        FuncionesFormularios ff = new FuncionesFormularios();
        gd.guardarDatos(ff.generarCodigoAlmacenamientoFormularios(listadoFormularioAux),"formularios");
    }
    
    
    /**
     * Se obtiene el objeto Formulario por el id
     * @param listadoUsuarioAux
     * @param nombreUsuarioEnviado
     * @return 
     */
    public void modificarComponentePorId(String idFormulario, String idComponente, String tipoParametro, String nuevoValor){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("formularios");//cargamos la data de los usuarios
        ArrayList<Formulario> listadoFormularioAux = cargarDatos.getListadoFormulariosCargados();   
        
        if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
            for(Formulario formularioAux: listadoFormularioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                idFormulario = idFormulario.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                
                if(idFormularioCargado.equals(idFormulario)){//Se encontro el formulario deseado
                    //posicion en el listado
                    for(Componente componenteAux: formularioAux.getListadoComponentes()){//buscamos el compnente deseado
                        String idComponenteCargado = componenteAux.getIdComponente().replaceAll("\"", "").trim();
                        idComponente = idComponente.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                        
                        if(idComponenteCargado.equals(idComponente)){//se encontro el componente deseado
                            int index = listadoFormularioAux.indexOf(formularioAux); 
                            int indexComponente = listadoFormularioAux.get(index).getListadoComponentes().indexOf(componenteAux); 
                            //Modificamos el compoennte                    
                            componenteAux = editarCampo(componenteAux, tipoParametro, nuevoValor);   
                            //actualizamos el listado de ccomponentes
                            formularioAux.getListadoComponentes().set(indexComponente, componenteAux);                            
                            //actualizamos el form
                            listadoFormularioAux.set(index, formularioAux);
                        }
                    }                                     
                }
                
            }
        }
        
        ///Guardamos los datos
        GuardarDatos gd = new GuardarDatos();
        FuncionesFormularios ff = new FuncionesFormularios();
        gd.guardarDatos(ff.generarCodigoAlmacenamientoFormularios(listadoFormularioAux),"formularios");
    }
    
    /**
     * Se recibe un listado de formularios y un id, se retorna si ya existe ese formulario ya posee un componente con ese id
     * @param listadoFormularios
     * @param idFormulario
     * @return 
     */
    public boolean verificaraComponenteConId (ArrayList<Formulario> listadoFormularios, Componente componente){
        boolean existencia = false;
        String idFormulario = componente.getFormularioId();
        String idComponente = componente.getIdComponente();
        
        if(listadoFormularios != null && listadoFormularios.size() > 0){
            for(Formulario formularioAux: listadoFormularios){
                //Id de formulario sin comillas ni espacios
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                idFormulario = idFormulario.replaceAll("\"", "").trim();
                
                if(idFormularioCargado.equals(idFormulario)){//ENCONTRAMOS EL FORMULARIO DESEADO    
                    //Verificamos en el listado de componentes del formulario si existe el id buscado
                    for(Componente componenteAux: formularioAux.getListadoComponentes()){//buscamos el compnente deseado
                        String idComponenteCargado = componenteAux.getIdComponente().replaceAll("\"", "").trim();
                        idComponente = idComponente.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                        
                        if(idComponenteCargado.equals(idComponente)){//se encontro el componente deseado, significa que ese id ya existe
                           
                            existencia = true;//Existe un nombre de usuario
                            break;
                        }
                    }
                    
                    
                }                
            }
        }
        
        return existencia;
    }
    
    /**
     * Se obtiene el objeto Formulario por el id, modificamos los registros
     * @param listadoUsuarioAux
     * @param nombreUsuarioEnviado
     * @return 
     */
    public void agregarRegistroComponente(String idFormulario, String idComponente, String valorRegistro){
        CargarDatos cargarDatos = new CargarDatos();
        
        cargarDatos.leerDatos("formularios");//cargamos la data de los usuarios
        ArrayList<Formulario> listadoFormularioAux = cargarDatos.getListadoFormulariosCargados();   
                
        idFormulario = idFormulario.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
        idComponente = idComponente.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
                
        if(listadoFormularioAux != null && listadoFormularioAux.size() > 0){
            for(Formulario formularioAux: listadoFormularioAux){
                //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues
                String idFormularioCargado = formularioAux.getId().replaceAll("\"", "").trim();
                
                if(idFormularioCargado.equals(idFormulario)){//Se encontro el formulario deseado
                    //posicion en el listado
                    ArrayList<Componente> listadoComponentes = formularioAux.getListadoComponentes();
                    for(Componente componenteAux: listadoComponentes){//buscamos el compnente deseado
                        String idComponenteCargado = componenteAux.getIdComponente().replaceAll("\"", "").trim();
                        
                        if(idComponenteCargado.equals(idComponente)){//se encontro el componente deseado
                            int index = listadoFormularioAux.indexOf(formularioAux); 
                            int indexComponente = listadoComponentes.indexOf(componenteAux); 
                            //agregamos el registro                    
                            componenteAux.agregarRegistro(valorRegistro);
                            //actualizamos el listado de ccomponentes
                            listadoComponentes.set(indexComponente, componenteAux); 
                            //agregamos el listado de componentes
                            formularioAux.setListadoComponentes(listadoComponentes);
                            //actualizamos el form
                            listadoFormularioAux.set(index, formularioAux);
                        }
                    }                                     
                }
                
            }
        }
        
        ///Guardamos los datos
        GuardarDatos gd = new GuardarDatos();
        FuncionesFormularios ff = new FuncionesFormularios();
        System.out.println("CODIGO GENERADO//////////////////////////////////////////////////\n"+ff.generarCodigoAlmacenamientoFormularios(listadoFormularioAux));
        gd.guardarDatos(ff.generarCodigoAlmacenamientoFormularios(listadoFormularioAux),"formularios");
    }
}
