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
import clasesDAOFormularios.Registro;
import java.util.ArrayList;
import java.util.List;

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
                    int cantidadRegistros = 0;
                    //MIRAMOS CUANTOS REGISTROS TIENE
                    if(formularioAux.getListadoComponentes() != null && formularioAux.getListadoComponentes().size() > 0){//tiene mas de algun componente, entonces
                        for(Componente compo: formularioAux.getListadoComponentes()){//recorremos todos los componentes
                            int cantidadRegistrosAux = compo.getListadoRegistros().size();
                            if(cantidadRegistrosAux > cantidadRegistros){
                                cantidadRegistros = cantidadRegistrosAux;
                            }
                        }
                    }
                    
                    for(int i = 0; i < cantidadRegistros; i++){//agregamos los registros
                        componente.agregarRegistro("\"---------\"");
                    }
                    
                    System.out.println("CANTIDAD DE REGISSTROS: "+cantidadRegistros);
                    
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
     * FUNCION PARA EL SQFORM
     * Se obtiene el objeto Componente por el id, usando metodos de la funcion de fomularios
     * @param listadoUsuarioAux
     * @param nombreUsuarioEnviado
     * @return 
     */
    public Componente obtenerComponentePorIdNombre(Formulario formularioAux, String idNombre){     
        
        if(formularioAux != null){            
            //Obtenemos su nombre dde usuario sin las etiquetas de las comillas y sin los espacios antes y despues            
            idNombre = idNombre.replaceAll("\"", "").trim();//tambien quitamos comillas y espacios en el id enviado
            for(Componente componenteAux: formularioAux.getListadoComponentes()){//buscamos el compnente deseado
                String idComponenteCargado = componenteAux.getIdComponente().replaceAll("\"", "").trim();
                String nombreCampo = componenteAux.getNombreCampo().replaceAll("\"", "").trim();

                if(idComponenteCargado.equals(idNombre) || idComponenteCargado.equals(nombreCampo)){//se encontro el componente deseado
                    return componenteAux;
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
    
    public ArrayList<String> verificarRepitenciaIdsComponentes(Formulario formularioAux){
        ArrayList<String> idComponentes = new ArrayList();
        ArrayList<String> idRepetidos = new ArrayList();
        
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nREPITENCIAS: ");
        try{
            if(formularioAux != null && formularioAux.getListadoComponentes().size() > 0){
                System.out.println("componentes: "+formularioAux.getListadoComponentes().size());
                for(Componente componenteAuxiliar: formularioAux.getListadoComponentes()){
                    String idComponenteAux = componenteAuxiliar.getIdComponente().replaceAll("\"", "").trim();
                     System.out.println(""
                             + "Compo: "+idComponenteAux);
                    if(idComponentes.contains(idComponenteAux)){//si ya lo contiene significa que esta repeteido
                        if(idRepetidos.contains(idComponenteAux) == false){//si aun no esta agregado al listado de ids repetidos lo agregamos
                            idRepetidos.add(idComponenteAux);
                            System.out.println("\n"+idComponenteAux);
                            System.out.println("Se agrega al listado con repitencia");
                        }
                    }else{
                        System.out.println("Se agrega al listado sin errores");
                        idComponentes.add(idComponenteAux);
                    }                 
                }
            }
        }catch(Exception ex){
            System.out.println("Error, no se pudo comparar los elementos de los componentes");
        }
        
        return idRepetidos;
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
    
    public List<ArrayList<String>> valoresTabla(ArrayList<Componente> listadoComponentes){
        List<ArrayList<String>> tablaDatos = new ArrayList();
        try{        
            if(listadoComponentes != null){					
                for(Componente componenteAux: listadoComponentes){
                    int i = 0;
                    ArrayList<String> filaAux = new ArrayList();
                    
                    if(componenteAux.getListadoRegistros().size() > 0){
                        for(Registro registroAux: componenteAux.getListadoRegistros()){														
                                if (i == 0){
                                        //agregamos el titulo
                                        filaAux.add(componenteAux.getNombreCampo().replaceAll("\"", "").trim());
                                        i++;								
                                }
                                filaAux.add(registroAux.getRegistroDato().replaceAll("\"", "").trim());
                        }	
                        tablaDatos.add(filaAux);	
                    }
                    				
                }
            }
        }catch(Exception ex){
            System.out.println("Se produjo un error al intentar agregar los datos a la tabla: "+ex.getMessage());
        }
        
        return invertirValoresTabla(tablaDatos);
    }
    
    public List<ArrayList<String>> invertirValoresTabla(List<ArrayList<String>> informacion){
        List<ArrayList<String>> tablaDatos = new ArrayList();        
        
        try{
            if(informacion != null && informacion.size() > 0){					
                int cantidadRegistros = informacion.get(0).size();
                int cantidadComponentes = informacion.size();
                for(int y = 0; y < cantidadRegistros; y++){
                    ArrayList<String> datosFila = new ArrayList();
                    //Agregamos los componentes
                    for(int x = 0; x < cantidadComponentes; x++){
                        //agregamos cada registro
                        datosFila.add(informacion.get(x).get(y));
                    }
                    //agregamos la fila a la tabla
                    tablaDatos.add(datosFila);
                }
            }        
        }catch(Exception ex){
            System.out.println("Se produjo un error al intentar invertir la tabla: "+ex.getMessage());
        }
        
        
        
        return tablaDatos;
    }
    
}
