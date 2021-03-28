/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import clasesDAO.BloqueParametros;
import clasesDAO.TokenParametro;
import clasesDAO.Token;
import java.util.ArrayList;
import java.util.Collections;
import clasesDAO.TokenError;

/**
 *
 * @author grifiun
 */
public class FuncionesSolicitudes {
    ArrayList<TokenError> listadoErroresParser = new ArrayList();
    
    /**
     * Funcion que analiza un bloque de parametros, revisa la entrada de todos los parametros obligatorios segun la clase y parametros invalidos
     * Principalmente guiada a los componentes 
     * @param bloqueParametros 
     */
    public void analizarParametros(BloqueParametros bloqueParametros){
        String paramObligatorios = "";
        String paramInvalidos = "";
        String clase = "";

        /*
                CODIGO PARA REVISAR ERRORES DE REPETICIONES
        */
        int ocurrenciasCLASE = Collections.frequency(bloqueParametros.getListadoTipoParametros(), "\"CLASE\"");
        
        if(ocurrenciasCLASE == 1 &&  bloqueParametros.getListadoTipoParametros().contains("\"CLASE\"")){
                int index = bloqueParametros.getListadoTipoParametros().indexOf("\"CLASE\"");
                TokenParametro parametroAux = bloqueParametros.getListadoParametros().get(index);			

                if(parametroAux.getLexema().contains("CAMPO_TEXTO")){
                        paramObligatorios = "\"ID\",\"NOMBRE_CAMPO\",\"FORMULARIO\",\"TEXTO_VISIBLE\"";
                        //Los campos de texto no reciben opciones, filas, columnas ni url
                        paramInvalidos = "\"OPCIONES\",\"FILAS\",\"COLUMNAS\",\"URL\"";
                        clase = "CAMPO_TEXTO";
                } else if(parametroAux.getLexema().contains("AREA_TEXTO")){
                        paramObligatorios = "\"ID\",\"NOMBRE_CAMPO\",\"FORMULARIO\",\"TEXTO_VISIBLE\",\"FILAS\",\"COLUMNAS\"";
                        clase = "AREA_TEXTO";
                        //Los area texto no pueden tener opciones, url			
                        paramInvalidos = "\"OPCIONES\",\"URL\"";
                } else if(parametroAux.getLexema().contains("CHECKBOX")){
                        paramObligatorios = "\"ID\",\"NOMBRE_CAMPO\",\"FORMULARIO\",\"TEXTO_VISIBLE\",\"OPCIONES\"";
                        clase = "CHECKBOX";
                        //Los checkbox, combo y radio no pueden tener filas, columnas, url
                        paramInvalidos = "\"FILAS\",\"COLUMNAS\",\"URL\"";
                } else if(parametroAux.getLexema().contains("RADIO")){
                        paramObligatorios = "\"ID\",\"NOMBRE_CAMPO\",\"FORMULARIO\",\"TEXTO_VISIBLE\",\"OPCIONES\"";
                        clase = "RADIO";
                        //Los checkbox, combo y radio no pueden tener filas, columnas, url
                        paramInvalidos = "\"FILAS\",\"COLUMNAS\",\"URL\"";
                } else if(parametroAux.getLexema().contains("FICHERO")){
                        paramObligatorios = "\"ID\",\"NOMBRE_CAMPO\",\"FORMULARIO\",\"TEXTO_VISIBLE\"";
                        clase = "FICHERO";
                        //Los ficheros no reciben opciones, filas, columnas ni url
                        paramInvalidos = "\"OPCIONES\",\"FILAS\",\"COLUMNAS\",\"URL\"";
                } else if(parametroAux.getLexema().contains("IMAGEN")){
                        paramObligatorios = "\"ID\",\"FORMULARIO\",\"TEXTO_VISIBLE\",\"URL\"";
                        clase = "IMAGEN";
                        //Una imagen no reciben opciones, filas, columnas, requerrido, ni nomnre_campo
                        paramInvalidos = "\"OPCIONES\",\"FILAS\",\"COLUMNAS\",\"REQUERIDO\",\"NOMBRE_CAMPO\"";
                } else if(parametroAux.getLexema().contains("COMBO")){
                        paramObligatorios = "\"ID\",\"NOMBRE_CAMPO\",\"FORMULARIO\",\"TEXTO_VISIBLE\",\"OPCIONES\"";
                        clase = "COMBO";
                        //Los checkbox, combo y radio no pueden tener filas, columnas, url
                        paramInvalidos = "\"FILAS\",\"COLUMNAS\",\"URL\"";
                } else if(parametroAux.getLexema().contains("BOTON")){
                        paramObligatorios = "\"ID\",\"FORMULARIO\",\"TEXTO_VISIBLE\"";
                        clase = "BOTON";
                        //Un boton no reciben opciones, filas, columnas, url, requerrido, ni nomnre_campo
                        paramInvalidos = "\"OPCIONES\",\"FILAS\",\"COLUMNAS\",\"URL\",\"REQUERIDO\",\"NOMBRE_CAMPO\"";
                } 

                comprobarObligatoriosPorClase(paramObligatorios, bloqueParametros, clase);
                comprobarParametrosInvalidosPorClase(paramInvalidos, bloqueParametros, clase);
        }else{
            if(ocurrenciasCLASE > 1){//Tiene mas de una clase
                 String msgError = "Posee mas de una clase definida para el bloque de parametros que inicia en linea:"+bloqueParametros.getLinea()+", Columna"+bloqueParametros.getColumna()
                +" y termina en linea2: "+bloqueParametros.getLineaFin()+" columna2: "+bloqueParametros.getColumnaFin()+" con lo cual no se pueden verificar los parametros obligatorios ni invalidos";
                        
                agregarNuevoError("ERROR SEMANTICO", "Mas de una clase definida", msgError, bloqueParametros.getLinea(), bloqueParametros.getColumna());  
            }else{
                 String msgError = "No tiene una clase definida para el bloque de parametros que inicia en linea:"+bloqueParametros.getLinea()+", Columna"+bloqueParametros.getColumna()
                +" y termina en linea2: "+bloqueParametros.getLineaFin()+" columna2: "+bloqueParametros.getColumnaFin()+", por favor, defina una clase para verificar los parametros obligatorios e invalidos";
                        
                agregarNuevoError("ERROR SEMANTICO", "Falta clase", msgError, bloqueParametros.getLinea(), bloqueParametros.getColumna());  
            }
            
                             
        }
    }
    /**
     * Comprueba los datos obligatorios segun los parametros obligatorios recibidos
     * @param paramObligatorios
     * @param bloqueParametros
     * @param clase 
     */
    public void comprobarObligatoriosPorClase(String paramObligatorios, BloqueParametros bloqueParametros, String tipo){
        String[] arrParamObligatorio = paramObligatorios.split(",");
        ArrayList<String> listadoTipoParametros = bloqueParametros.getListadoTipoParametros();
        int contador = 0;
        String mensaje = "";
        String msjError = "";

        //recorremos todos los param obligatorios
        for(int i = 0; i < arrParamObligatorio.length; i++){
                //recorremos los parametros
                if(listadoTipoParametros.contains(arrParamObligatorio[i])){
                        contador++;				
                }else{//se agrega al mensaje que no posee ese param obligatorio
                        if(i + 1 == arrParamObligatorio.length)
                                mensaje = mensaje +arrParamObligatorio[i];
                        else
                                mensaje = mensaje +arrParamObligatorio[i]+", ";
                }
                if(contador == arrParamObligatorio.length)//encontramos todos los datos obligatorios
                        break;
        }
        if(mensaje.isEmpty() == false || mensaje.equals("") == false){//hay errores
            msjError = "Faltan los siguientes parametros obligatorios para el bloque con tipo \""+tipo+"\": "+mensaje+" que inicia en linea:"+bloqueParametros.getLinea()+", columna:"+bloqueParametros.getColumna()
                +" y termina en linea: "+bloqueParametros.getLineaFin()+" columna: "+bloqueParametros.getColumnaFin();
        
            agregarNuevoError("ERROR SEMANTICO", "Faltan parametros obligatorios", msjError, bloqueParametros.getLinea(), bloqueParametros.getColumna());
        }        
        
    }
    
    /**
     * Comprueba parametros invalidos para los componentes
     * @param paramInvalido
     * @param bloqueParametros
     * @param clase 
     */
    public void comprobarParametrosInvalidosPorClase(String paramInvalido, BloqueParametros bloqueParametros, String clase){
        String[] arrParamInvalidos = paramInvalido.split(",");
        ArrayList<String> listadoTipoParametros = bloqueParametros.getListadoTipoParametros();

        //recorremos todos los param obligatorios
        for(int i = 0; i < arrParamInvalidos.length; i++){
                //recorremos los parametros
                if(listadoTipoParametros.contains(arrParamInvalidos[i])){	
                        int index = listadoTipoParametros.indexOf(arrParamInvalidos[i]);
                        int linea = bloqueParametros.getListadoParametros().get(index).getLinea();
                        int columna = bloqueParametros.getListadoParametros().get(index).getColumna();
                        String mensaje = "Linea: "+linea+", Columna: "+columna+" La clase \""+clase+"\" no permite el parametro "+arrParamInvalidos[i]+" que usted ingreso: ";
                                               
                        agregarNuevoError("ERROR SEMANTICO", "Parametro invalido", mensaje, linea, columna);
                }
        }

    }
    
    /**
     * Analizamos entradas multiples
     * @param a
     * @param b 
     */
    public void analizarEntradasMultiples(BloqueParametros bloqueParametros, TokenParametro parametro){
        String nombreParametroAux =  parametro.getTipoParametro();
        if(bloqueParametros.getListadoTipoParametros().contains(nombreParametroAux)){//si ya lo contiene
                String mensaje = "Se detectaron multiples entradas del mismo parametro "+nombreParametroAux+ " con valor: "+parametro.getLexema()+" en la linea: "+ parametro.getLinea() + " y columna: "+parametro.getColumna();
               
                agregarNuevoError("ERROR SEMANTICO", "Parametro invalido", mensaje, parametro.getLinea(), parametro.getColumna());
        }	
    }
    
    /**
     * Verifica que los usuarios de usuarios y formularios recibieron los datos obligatorios
     * @param parametros
     * @param tipo 
     */
    public void analizarParametrosUsuariosFormularios(BloqueParametros parametros, String tipo){
        String paramObligatorios = "";

        if(tipo.equals("CREAR_USUARIO")){
                paramObligatorios = "\"USUARIO\",\"PASSWORD\"";
        } else if(tipo.equals("MODIFICAR_USUARIO")){
                paramObligatorios = "\"USUARIO_ANTIGUO\",\"USUARIO_NUEVO\",\"NUEVO_PASSWORD\"";
        } else if(tipo.equals("NUEVO_FORMULARIO")){
                paramObligatorios = "\"ID\",\"TITULO\",\"NOMBRE\",\"TEMA\"";
        } else if(tipo.equals("MODIFICAR_FORMULARIO")){
                paramObligatorios = "\"ID\"";  
                //Se necesita al menos otro dato ademas del ID
                
                int ocurrenciasID = Collections.frequency(parametros.getListadoTipoParametros(), "\"ID\"");
                int cantidadParametros = parametros.getListadoTipoParametros().size();  
                if(ocurrenciasID == 1 && cantidadParametros <= ocurrenciasID){//No hay mas de un parametro
                    String msjError = "La solicitud MODIFICAR_FORMULARIO requiere que se ingrese al menos otro parametro \"TITULO\", \"NOMBRE\" o \"TEMA\" ) ademas del \"ID\", bloque lina: "+parametros.getLinea()+" columna: "+parametros.getColumna();

                    agregarNuevoError("ERROR SEMANTICO", "Faltan parametros", msjError, parametros.getLinea(), parametros.getColumna());
                }
        } else if(tipo.equals("MODIFICAR_COMPONENTE")){
                paramObligatorios = "\"ID\",\"FORMULARIO\"";              
                
                
                int ocurrenciasID = Collections.frequency(parametros.getListadoTipoParametros(), "\"ID\"");
                int ocurrenciasFORMULARIO = Collections.frequency(parametros.getListadoTipoParametros(), "\"FORMULARIO\"");
                int cantidadParametros = parametros.getListadoTipoParametros().size();  
                if(ocurrenciasID == 1 && cantidadParametros <= (ocurrenciasID + ocurrenciasFORMULARIO)){//No contiene ningun parametro opcional
                    String msjError = "La solicitud MODIFICAR_COMPONENTE requiere que se ingrese al menos otro parametro (\"INDICE\", \"TEXTO_VISIBLE\", \"ALINEACION\", etc ) ademas del \"ID\" y el \"FORMULARIO\", bloque lina: "+parametros.getLinea()+" columna: "+parametros.getColumna();

                    agregarNuevoError("ERROR SEMANTICO", "Faltan parametros", msjError, parametros.getLinea(), parametros.getColumna());
                }               
                
        } 

        comprobarObligatoriosPorClase(paramObligatorios, parametros, tipo);		
    }
        
    public void analizarSolicitud(Token inicio, Token fin, BloqueParametros param, String tipoSolicitud){
        System.out.println("\nAnalisis: ");
        param.setLinea(inicio.getLinea());
        param.setColumna(inicio.getColumna());
        param.setLineaFin(fin.getLinea());
        param.setColumnaFin(fin.getColumna());
       
        if(tipoSolicitud.contains("AGREGAR_COMPONENTE"))
            analizarParametros(param);
        else
            analizarParametrosUsuariosFormularios(param, tipoSolicitud); 
    }
    
    /**
     * Funcion utilizada para retornar un bloque de parametros, si esta vacia se instancia uno nuevo y se añaden los parametros validos unicamente, ademas de llamar a las funciones de repeticion
     * @param a
     * @param b
     * @param tipoBloque
     * @param tipoParametros
     * @return 
     */
    public BloqueParametros agregarParametroABloque(BloqueParametros a, TokenParametro b, String tipoBloque, String tipoParametros){
        if(a == null){//si aun no se inicializa el bloque lo hacemos
            a = new BloqueParametros("\""+tipoBloque+"\"",  "\""+tipoParametros+"\"");
        }

        if(a != null && b != null){//si hay un parametro valido y ya esta inicializado el bloque
            //Verificamos parámetros repetidos
            analizarEntradasMultiples(a, b);	
            a.insertarParametro(b);
            a.insertarTipoParametros(b.getTipoParametro());				
        }
		
        return a;
    }
    /**
     * Funcion que nos agrega en inicio y el fin de los bloques de parametros
     * @return 
     */
    public BloqueParametros agregarInicioFinBloque(Token inicio, Token fin, BloqueParametros param){
        param.setLinea(inicio.getLinea());
        param.setColumna(inicio.getColumna());
        param.setLineaFin(fin.getLinea());
        param.setColumnaFin(fin.getColumna());
        
        return param;
    }
    
    
   /**
    * Agregamos un tipo de error al listado mandado
    * @param listadoErroresParser
    * @param tipoError
    * @param lexema
    * @param mensajeError
    * @param linea
    * @param columna 
    */
    public void agregarNuevoError(String tipoError, String lexema, String mensajeError, int linea, int columna){			
            TokenError tokenErrorAux = new TokenError(tipoError, lexema, mensajeError, linea, columna);
            listadoErroresParser.add(tokenErrorAux);
    }

    /**
     * Getter del listado de errores del parser
     * @return 
     */
    public ArrayList<TokenError> getListadoErroresParser() {
        return listadoErroresParser;
    }

    /**
     * Setter del listado de errores del parser
     * @param listadoErroresParser 
     */
    public void setListadoErroresParser(ArrayList<TokenError> listadoErroresParser) {
        this.listadoErroresParser = listadoErroresParser;
    }
    
    public void imprimirListadoErrores(){
        
        for(TokenError aux: listadoErroresParser){
            System.out.println("TIPO: "+aux.getTipoToken()+" LINEA: "+aux.getLinea()+" COLUMNA: "+aux.getColumna()+" CAUSA: "+aux.getLexema()+" DESCRIPCION: "+aux.getMsgError());
        }
    }
    
    public void imprimirListadoErrores(ArrayList<TokenError> listadoErrores){
        
        for(TokenError aux: listadoErrores){
            System.out.println("TIPO: "+aux.getTipoToken()+" LINEA: "+aux.getLinea()+" COLUMNA: "+aux.getColumna()+" CAUSA: "+aux.getLexema()+" DESCRIPCION: "+aux.getMsgError());
        }
    }
    
    

}
