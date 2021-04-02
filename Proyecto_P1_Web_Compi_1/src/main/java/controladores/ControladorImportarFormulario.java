/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import archivos.CargarDatos;
import archivos.GuardarDatos;
import clasesDAO.BloqueParametros;
import clasesDAO.TokenError;
import clasesDAOFormularios.Formulario;
import funciones.FuncionesComponentes;
import funciones.FuncionesFormularios;
import funciones.FuncionesGenerarCodigoRespuesta;
import gramatica_importacion_formularios.LexerImportacionDatos;
import gramatica_importacion_formularios.ParserImportacionDatos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grifiun
 */
@WebServlet(name = "ControladorImportarFormulario", urlPatterns = {"/ControladorImportarFormulario"})
public class ControladorImportarFormulario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            BufferedReader bf = request.getReader();
            String textoRecibido = ""; //"TEXTO DEL SERVER AL CLIENTE:\n";
            String aux = "";
            FuncionesFormularios funcionesFormularios = new FuncionesFormularios();
            FuncionesComponentes funcionesComponentes = new FuncionesComponentes();            
            FuncionesGenerarCodigoRespuesta funcionesRespuesta = new FuncionesGenerarCodigoRespuesta();
            ArrayList<TokenError> listadoErroresSemanticos = new ArrayList();
            BloqueParametros bloqueParam;
            boolean existeErrorSintacLex = false;
            boolean existeErrorSemantico = false;
            
            if(sesionCliente.UsuarioLogueado.isLogin()){
                try {
                    while((aux = bf.readLine()) != null){//Lee todas las lineas del texto  
                        textoRecibido = textoRecibido + aux + "\n";
                    }
                } catch (Exception e) {
                    System.out.println("ERROR AL LEER EL BF: "+e.getMessage());
                }
                //REmovemos la corrupcion del texto
                String palabraOriginal = new String(textoRecibido.getBytes("ISO-8859-1"), "UTF-8");

                try{
                    StringReader reader = new StringReader(palabraOriginal);
                    LexerImportacionDatos lexer = new LexerImportacionDatos(reader);
                    ParserImportacionDatos parser = new ParserImportacionDatos(lexer);
                    try{    
                        parser.parse();
                    }catch(Exception ex){
                        System.out.println("Error al ejecutar el parser del importadao");
                    }
                    
                    try{
                        Formulario formAux = parser.getFormularioLeido();
                        CargarDatos cargarDatos = new CargarDatos();
                        cargarDatos.leerDatos("formularios");     
                         ArrayList<Formulario> listadoFormularioAux = cargarDatos.getListadoFormulariosCargados();//listado de formularios
                        //////////////LISTADO DE ERRORES SITACTICOS Y SEMANTICOS
                        try{
                            ArrayList<TokenError> listadoErroresSintacticos = parser.getListadoErroresParser();
                            if(listadoErroresSintacticos != null && listadoErroresSintacticos.size() > 0){//hay errores sintacticos
                                for(TokenError tokenError: listadoErroresSintacticos){
                                    out.print("Tipo error: "+tokenError.getTipoToken()+ ": Descripcion: "+tokenError.getLexema()+" Mensaje: "+tokenError.getMsgError()+" Lin: "+tokenError.getLinea()+" Col: "+tokenError.getColumna()+"\n");

                                }
                                existeErrorSintacLex = true;
                            }
                        }catch(Exception ex){
                            System.out.println("Error al ejecutar el parser");
                        }
                        
                        try{
                            ArrayList<TokenError> listadoErroresLexicos = lexer.obtenerListadoErroresLexicos();
                            if(listadoErroresLexicos != null && listadoErroresLexicos.size() > 0){//hay errores sintacticos
                                for(TokenError tokenError: listadoErroresLexicos){
                                    out.print("Tipo error: "+tokenError.getTipoToken()+ ": Descripcion: "+tokenError.getLexema()+" Mensaje: "+tokenError.getMsgError()+" Lin: "+tokenError.getLinea()+" Col: "+tokenError.getColumna()+"\n");

                                }
                                existeErrorSintacLex = true;
                            }
                        }catch(Exception ex){
                            System.out.println("Error al ejecutar el parser");
                        }
                        
                         
                        ////////////////////////////////////MANEJO ERRORES SEMANTICOS                        
                                                 
                        ///SI EL FORMULARIO NO EXISTE, ENTONCES LO AGREGAMOS AL LISTADO
                        if(formAux != null && funcionesFormularios.verificaraFormularioConId(listadoFormularioAux, formAux.getId()) == false){  
                            //si no existe
                             //agregamos fecha de creacion
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = new Date();
                            String fechaSistema = "\""+dateFormat.format(date) + "\"";
                            formAux.setFechaCreacion(fechaSistema);
                            //agregamos usuario creador
                            formAux.setUsuarioCreacion(""+sesionCliente.UsuarioLogueado.usuarioLogueado+"");                            
                                                    
                        }else{
                            listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "FORMULARIO CON ID REPETIDO", 
                                                " Quiere importar un formulario con un id existente en el sistema", 
                                                0, 0));
                            existeErrorSemantico = true;
                        }
                        
                         ////////////////////////////VERIFICAMOS REPETICION DE IDS DE COMPONENTES
                        if(formAux != null){
                            ArrayList<String> idComponentesRepetidos = funcionesComponentes.verificarRepitenciaIdsComponentes(formAux);
                            if(idComponentesRepetidos != null && idComponentesRepetidos.size() > 0){//si tiene ids repetidos
                                for(String idRepetido: idComponentesRepetidos){
                                    listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "COMPONENTE CON ID REPETIDO", 
                                                " Quiere importar un formulario con un id de componente ("+idRepetido+") repetido en el bloque", 
                                                0, 0));
                                }
                                existeErrorSemantico = true;
                            } 
                        }   
                        
                        if(existeErrorSemantico == false && existeErrorSintacLex == false){//sino hay errores, guardamos                                
                            try{
                                listadoFormularioAux.add(formAux);
                                String entradaExportar = funcionesFormularios.generarCodigoAlmacenamientoFormularios(listadoFormularioAux);

                                ///Guardamos los datos
                                GuardarDatos gd = new GuardarDatos();
                                gd.guardarDatos(entradaExportar,"formularios");
                                
                                
                            }catch(Exception ex){
                                System.out.println("Error al guardar "+ex.getMessage());
                            }                                
                        }                                
                        if(formAux != null && existeErrorSintacLex == false){
                            bloqueParam = formAux.generarBloqueParametros();
                            String resultado = funcionesRespuesta.generarCodigoRespuestaSolicitud(bloqueParam, listadoErroresSemanticos);
                            resultado = "<!ini_respuestas>\n" + resultado + "<!fin_respuestas>\n";
                            out.print(resultado);
                        }   
                    }catch(Exception ex)
                    {
                        System.out.println("No se agrego el formulario");
                        out.print("No se agrego el formulario, surgio un error de improvisto: "+ex.getMessage());
                    }



                }catch(Exception ex){
                    System.out.println("Error al ejecutar el analizador sintactico y semantico de la importacion");
                    out.print("Error al ejecutar el analizador sintactico y semantico de la importacion");
                }
            }else{
                out.println("inicie sesion primero");
            }
            
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
