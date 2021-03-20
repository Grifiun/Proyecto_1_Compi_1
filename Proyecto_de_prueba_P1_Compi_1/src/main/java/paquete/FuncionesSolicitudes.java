/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.util.ArrayList;

/**
 *
 * @author grifiun
 */
public class FuncionesSolicitudes {
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
        if(bloqueParametros.getListadoTipoParametros().contains("\"CLASE\"")){
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
                System.out.println("No tiene una clase definida para el bloque de parametros que inicia en linea:"+bloqueParametros.getLinea()+", Columna"+bloqueParametros.getColumna()
                +" y termina en linea2: "+bloqueParametros.getLineaFin()+" columna2: "+bloqueParametros.getColumnaFin()+"\n");
        }
    }
    /**
     * Comprueba los datos obligatorios segun los parametros obligatorios recibidos
     * @param paramObligatorios
     * @param bloqueParametros
     * @param clase 
     */
    public void comprobarObligatoriosPorClase(String paramObligatorios, BloqueParametros bloqueParametros, String clase){
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
        msjError = "Error sintactico: Faltan los siguientes parametros obligatorios para el bloque con clase \""+clase+"\": "+mensaje+"que inicia en linea:"+bloqueParametros.getLinea()+", columna:"+bloqueParametros.getColumna()
                +" y termina en linea: "+bloqueParametros.getLineaFin()+" columna: "+bloqueParametros.getColumnaFin();
        System.out.println(msjError+"\n");
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
                        String mensaje = "Error sintactico: Linea: "+linea+", Columna: "+columna+" La clase \""+clase+"\" no permite el parametro "+arrParamInvalidos[i]+" que usted ingreso: ";
                        System.out.println(mensaje+"\n");							
                }
        }

    }
    
    /**
     * Analizamos entradas multiples
     * @param a
     * @param b 
     */
    public void analizarEntradasMultiples(BloqueParametros a, TokenParametro b){
        String nombreParametroAux =  b.getTipoParametro();
        if(a.getListadoTipoParametros().contains(nombreParametroAux)){//si ya lo contiene
                System.out.println("Error semantico: Se detectaron multiples entradas del mismo parametro "+nombreParametroAux+ " con valor: "+b.getLexema()+" en la linea: "+ b.getLinea() + " y columna: "+b.getColumna());
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
        } else if(tipo.equals("MODIFICAR_COMPONENTE")){
                paramObligatorios = "\"ID\",\"FORMULARIO\"";
        } 

        comprobarObligatoriosPorClase(paramObligatorios, parametros, tipo);		
    }
        
    public void analizarSolicitud(Token inicio, Token fin, BloqueParametros param, String tipoSolicitud){
        System.out.println("\nAnalisis: ");
        param.setLinea(inicio.getLinea());
        param.setColumna(inicio.getColumna());
        param.setLineaFin(fin.getLinea());
        param.setColumnaFin(fin.getColumna());

        if(tipoSolicitud.contains("USUARIO") || tipoSolicitud.contains("FORMULARIO"))
            analizarParametrosUsuariosFormularios(param, tipoSolicitud); 
        else if(tipoSolicitud.contains("COMPONENTE"))
            analizarParametros(param);
    }

}
