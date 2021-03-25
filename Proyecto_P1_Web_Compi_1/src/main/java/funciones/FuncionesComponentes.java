/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import clasesDAO.BloqueParametros;
import clasesDAO.ComponenteCheckBox;
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
            case "AREA_TEXTO":
                componenteAux = new ComponenteAreaTexto();
                /*Llenamos el objeto con cada dato*/

                break;
            case "CHECKBOX":
                componenteAux = new ComponenteCheckBox();
                /*Llenamos el objeto con cada dato*/

                break;
            case "RADIO":
                componenteAux = new ComponenteRadio();
                /*Llenamos el objeto con cada dato*/

                break;
            case "FICHERO":
                componenteAux = new ComponenteFichero();
                /*Llenamos el objeto con cada dato*/

                break;
            case "IMAGEN":
                componenteAux = new ComponenteImagen();
                /*Llenamos el objeto con cada dato*/

                break;
            case "BOTON":
                componenteAux = new ComponenteBoton();
                /*Llenamos el objeto con cada dato*/

                break;
            case "COMBO":
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
                switch(parametroAux.getTipoParametro()){
                    case "\"ID\"":                                        
                            componenteAux.setIdComponente(parametroAux.getLexema());
                        break;
                    case "\"NOMBRE_CAMPO\"":                                        
                            componenteAux.setNombreCampo(parametroAux.getLexema());
                        break;
                    case "\"FORMULARIO\"":                                        
                            componenteAux.setFormularioId(parametroAux.getLexema());
                        break;
                    case "\"CLASE\"":                                        
                            componenteAux.setClase(parametroAux.getLexema());
                        break;
                    case "\"INDICE\"":                                        
                            componenteAux.setIndice(parametroAux.getLexema());
                        break;
                    case "\"TEXTO_VISIBLE\"":                                        
                            componenteAux.setTextoVisible(parametroAux.getLexema());
                        break;
                    case "\"ALINEACION\"":                                        
                            componenteAux.setAlineacion(parametroAux.getLexema());
                        break;
                    case "\"REQUERIDO\"":                                        
                            componenteAux.setRequerido(parametroAux.getLexema());
                        break;
                    case "\"OPCIONES\"":                                        
                            componenteAux.setOpciones(parametroAux.getLexema());
                        break;
                    case "\"FILAS\"":                                        
                            componenteAux.setFilas(parametroAux.getLexema());
                        break;
                    case "\"COLUMNAS\"":                                        
                            componenteAux.setColumnas(parametroAux.getLexema());
                        break;
                    case "\"URL\"":                                        
                            componenteAux.setURL(parametroAux.getLexema());
                        break;
                    

                }                                  
            }
        }
        
        return componenteAux;
    }
    
    public Formulario agregarDatosFormulario(BloqueParametros bloqueAux){
        Formulario formularioAux = new Formulario();
        
        for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
            if(parametroAux != null){ //dependiendo del tipo de dato llamaremos a diferentes funciones                  
                String tipoParametro = parametroAux.getTipoParametro();
                System.out.println("Tipo parametro: "+tipoParametro);
                switch(tipoParametro){
                    case "\"ID\"":                                        
                            formularioAux.setId(parametroAux.getLexema());
                        break;
                    case "\"TITULO\"":                                        
                            formularioAux.setTitulo(parametroAux.getLexema());
                        break;
                    case "\"NOMBRE\"":                                        
                            formularioAux.setNombre(parametroAux.getLexema());
                        break;
                    case "\"TEMA\"":                                        
                            formularioAux.setTema(parametroAux.getLexema());
                        break;

                }                                  
            }
        }
        
        return formularioAux;
    }
    
    public ArrayList<Formulario> agregarComponenteFormularioPorId(Componente componenteAux, ArrayList<Formulario> listadoFormularios, ArrayList<String> listadoIdFormularios){
        
        String idFormularioComponente = componenteAux.getFormularioId();
        System.out.println("Formulario Id del componente: "+idFormularioComponente);
        int indexFormulario;
        
        if(listadoIdFormularios.contains(idFormularioComponente)){
            indexFormulario = listadoIdFormularios.indexOf(idFormularioComponente);
        }else{
            indexFormulario = -1;//notacion para formulario inexistente
        }
        
        if(indexFormulario != -1){
            //Si existe el id de los formularios, lo agregamos al listado de formularios
            listadoFormularios.get(indexFormulario).agregarComponente(componenteAux);
        }else{
            System.out.println("Formulario inexistente");
        }
    
        return listadoFormularios;
    }
}
