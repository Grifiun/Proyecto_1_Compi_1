package controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import archivos.CargarDatos;
import archivos.GuardarDatos;
import clasesDAO.BloqueParametros;
import clasesDAO.TokenError;
import clasesDAO.TokenParametro;
import clasesDAOFormularios.Componente;
import clasesDAOFormularios.ComponenteBoton;
import clasesDAOFormularios.Formulario;
import clasesDAOUsuario.Usuario;
import funciones.FuncionesComponentes;
import funciones.FuncionesFormularios;
import funciones.FuncionesGenerarCodigoRespuesta;
import funciones.FuncionesLogin;
import funciones.FuncionesSolicitudes;
import funciones.FuncionesUsuario;
import gramatica_indigo.LexerIndigo;
import gramatica_indigo.parser;
import gramatica_reporteria.LexerReporteria;
import gramatica_reporteria.ParserReporteria;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sesionCliente.UsuarioLogueado;

/**
 *
 * @author grifiun
 */
@WebServlet(urlPatterns = {"/Indigo"})
public class Indigo extends HttpServlet {    
    

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
        response.setContentType("text/html;charset=ISO-8859-1");
        //response.setContentType("text/html;charset=UTF-8");
        //ISO-8859-1
        
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /*
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Indigo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Indigo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
            /*
            String parametro = request.getParameter("param").toString();
            System.out.println("LOG TOMCAT: "+parametro);
            
            out.println("Mensaje del servidor al cliente, conexion hecha");
            out.println("Mensaje recibido del cliente: "+parametro);
            */
            
            
            BufferedReader bf = request.getReader();
            String textoRecibido = ""; //"TEXTO DEL SERVER AL CLIENTE:\n";
            String aux = "";

            try {
                while((aux = bf.readLine()) != null){//Lee todas las lineas del texto  
                    textoRecibido = textoRecibido + aux + "\n";
                }
            } catch (Exception e) {
                System.out.println("ERROR AL LEER EL BF: "+e.getMessage());
            }
            
            String palabraOriginal = new String(textoRecibido.getBytes("ISO-8859-1"), "UTF-8");
            textoRecibido = palabraOriginal;
            try{
                StringReader sr = new StringReader(textoRecibido);
                LexerIndigo lexer = new LexerIndigo(sr);
                System.out.println(" Lexer Ejecutado");
                parser pars = new parser(lexer);
                
                try{                        
                    pars.parse();     
                }catch(Exception ex){
                    System.out.println("Error en el respuestas indigo");
                }

                System.out.println(" Parser Ejecutado");
                System.out.println("_____________________________________________");    
                
                //FUNCIONEs
                FuncionesComponentes funcionesComponentes = new FuncionesComponentes(); 
                FuncionesFormularios funcionesFormularios = new FuncionesFormularios();
                FuncionesUsuario funcionesUsuarios = new FuncionesUsuario();
                boolean existenciaError = false;
                
                /////
                ArrayList<BloqueParametros> listadoSolicitudes;
                listadoSolicitudes = pars.getListadoSolicitudes();    
                ArrayList<TokenError> listadoErroresSintacticos = pars.getListadoErroresParser();
                ArrayList<TokenError> listadoErroresLexicos = lexer.obtenerListadoErroresLexicos();
                if(listadoErroresLexicos != null && listadoErroresLexicos.size() > 0){
                    for(TokenError tokenError: listadoErroresLexicos){
                        out.print("Tipo error: "+tokenError.getTipoToken()+ ": Descripcion: "+tokenError.getLexema()+" Mensaje: "+tokenError.getMsgError()+" Lin: "+tokenError.getLinea()+" Col: "+tokenError.getColumna()+"\n");
                        
                    }
                    existenciaError = true;
                }
                
                if(listadoErroresSintacticos != null && listadoErroresSintacticos.size() > 0){
                    for(TokenError tokenError: listadoErroresSintacticos){
                        out.print("Tipo error: "+tokenError.getTipoToken()+ ": Descripcion: "+tokenError.getLexema()+" Mensaje: "+tokenError.getMsgError()+" Lin: "+tokenError.getLinea()+" Col: "+tokenError.getColumna()+"\n");
                        
                    }
                    existenciaError = true;
                    
                }
                
                if(existenciaError == false){//si no hay errores
                    String resultado = ejecutarSolicitudes(listadoSolicitudes);                      
                    out.print(resultado);
                }
                
                
               
                
            } catch (Exception ex) {
                System.out.println("Error irrecuperrable: "+ex.getMessage()+ex.getLocalizedMessage()+ex.toString());
            } 
           // System.out.println("Texto con acentos:\n"+textoRecibido);            
            //String convertido = java.net.URLDecoder.decode(textoRecibido, "ISO-8859-1");
            //System.out.println("LOG TOMCAT: "+textoRecibido);
            //out.print(textoRecibido);
            //out.print("Texto recibido");
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

    private String ejecutarSolicitudes(ArrayList<BloqueParametros> listadoSolicitudes) {
        /////////////////////CARGAMAOS LA INFORMACION
        CargarDatos cd = new CargarDatos();        
        cd.leerDatos("formularios");
        cd.leerDatos("usuarios");
        
        ArrayList<Formulario> listadoFormulariosAux = cd.getListadoFormulariosCargados();
        ArrayList<Usuario> listadoUsuariosAux = cd.getListadoUsuariosCargados();
        String resultado = "";
        String resultadoErrores = "";
        boolean existenciaErrores = false;
        ////////////////////////////////REVISAMOS LAS SOLICITUDES
        FuncionesFormularios funcionesFormularios = new FuncionesFormularios();
        FuncionesComponentes funcionesComponentes = new FuncionesComponentes();
        FuncionesUsuario    funcionesUsuarios     = new FuncionesUsuario();
        FuncionesSolicitudes funcionesSolicitudes = new FuncionesSolicitudes();
        FuncionesGenerarCodigoRespuesta funcionesRespuesta = new FuncionesGenerarCodigoRespuesta();
        FuncionesLogin funcionesLogin = new FuncionesLogin();                
                
        ///////////////////////////////VARIABLES        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fechaSistema = "\""+dateFormat.format(date) + "\"";
        
        if(listadoSolicitudes != null){//Si existe un listado de solicitudes

            for(BloqueParametros bloqueAux: listadoSolicitudes){ 
                ArrayList<TokenError> listadoErroresSemanticos = new ArrayList();
                
                if(bloqueAux != null){//Si el bloque no es nulo
                    //Tipo de token y tipo de parametros
                    String tipoSolicitud = bloqueAux.getTipoToken();
                    
                    if(UsuarioLogueado.usuarioLogueado.equals("") == true && tipoSolicitud.contains("USUARIO") == false){
                        //si no son solicitudes para usuarios
                        //y ademas no existe un usuario logueado
                        listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "INICIE SESION PRIMERO PARA REALIZAR SOLICITUDES", 
                                                    " Quiere realizar una solicitud de tipo: "+tipoSolicitud+" pero este requiere una sesion activa", 
                                                    bloqueAux.getLinea(), bloqueAux.getColumna()));

                    }else if(tipoSolicitud.contains("USUARIO") == false && UsuarioLogueado.usuarioLogueado.equals("") == false){
                        //si no son para usuarios
                        //pero existe un usuario logueado, entonces se realizan las solicitudes
                        //
                        switch(tipoSolicitud){
                            case "\"NUEVO_FORMULARIO\"":
                                Formulario formularioAux;
                                System.out.println("Agregamos un nuevo formulario");
                                formularioAux = funcionesFormularios.agregarDatosFormulario(bloqueAux);
                                ///SI EL FORMULARIO NO EXISTE, ENTONCES LO AGREGAMOS AL LISTADO
                                if(funcionesFormularios.verificaraFormularioConId(listadoFormulariosAux, formularioAux.getId()) == false){  
                                    listadoFormulariosAux.add(formularioAux);//agregamos el formulario al listado     
                                }else{
                                    listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "FORMULARIO CON ID REPETIDO", 
                                                        " Quiere agregar un formulario con un id existente en el sistema", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));
                                }

                                break;
                            case "\"ELIMINAR_FORMULARIO\"":

                                System.out.println("Eliminamos un formulario");
                                String idFormEliminar = "";

                                for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                    String tipoParam = parametroAux.getTipoParametro().replaceAll("\"", "").trim();
                                    if(tipoParam.equals("ID")){
                                        idFormEliminar = parametroAux.getLexema();
                                    }
                                }
                                Formulario formAuxEliminar;                            
                                formAuxEliminar = funcionesFormularios.obtenerFormularioPorId(idFormEliminar, listadoFormulariosAux);

                                if(formAuxEliminar != null){
                                    listadoFormulariosAux.remove(formAuxEliminar);//removemos del listado
                                }else{
                                    listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "FORMULARIO INEXISTENTE", 
                                                        " Quiere eliminar un formulario inexistente en el sistema", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));
                                }    

                                break;
                            case "\"MODIFICAR_FORMULARIO\"":
                                System.out.println("Modificamos un formulario");                            
                                String idFormModificar = "";

                                for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                    String tipoParam = parametroAux.getTipoParametro().replaceAll("\"", "").trim();
                                    if(tipoParam.equals("ID")){
                                        idFormModificar = parametroAux.getLexema();
                                        break;
                                    }                                
                                }
                                //Buscamos al formulario por el id dentro del listado
                                Formulario formularioModificar = funcionesFormularios.obtenerFormularioPorId(idFormModificar, listadoFormulariosAux);

                                if(formularioModificar != null){
                                    //posicion del form en el listado
                                     int indexFormModificar = listadoFormulariosAux.indexOf(formularioModificar);                            

                                    for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                        String tipoParam = parametroAux.getTipoParametro().replaceAll("\"", "").trim();
                                        if(tipoParam.equals("ID") == false){//si no es
                                            formularioModificar = funcionesFormularios.editarCampo(formularioModificar, parametroAux.getTipoParametro(), parametroAux.getLexema());
                                        }                                
                                    }
                                    //Guardamos los datos en el listado
                                    listadoFormulariosAux.set(indexFormModificar, formularioModificar);

                                } else {
                                    listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "FORMULARIO INEXISTENTE", 
                                                       " Quiere modificar un formulario inexistente en el sistema", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));   
                                }                            

                                break;

                            case "\"AGREGAR_COMPONENTE\"":
                                int indexClase = bloqueAux.getListadoTipoParametros().indexOf("\"CLASE\"");
                                String clase = bloqueAux.getListadoParametros().get(indexClase).getLexema();
                                System.out.println("Agregamos un nuevo componente");
                                Componente componenteAux;

                                //Creamos el tipo de componente segun la clase                               
                                componenteAux = funcionesComponentes.crearComponentePorClase(clase);
                                //Agregamos los datos al componente
                                componenteAux = funcionesComponentes.agregarDatosCompoente(bloqueAux, componenteAux);  


                                ///SI EL FORMULARIO NO EXISTE, ENTONCES NOTIFIACMOS QUE NO EXISTE
                                if(funcionesFormularios.verificaraFormularioConId(listadoFormulariosAux, componenteAux.getFormularioId()) == false){  
                                    listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "FORMULARIO INEXISTENTE", 
                                                        " Quiere agregar un componente a un formulario con un id inexistente en el sistema", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));                                

                                }else{
                                    //EXISTE EL FORMULARIO
                                    //VERIFICAMOS QUE EL COMPONENTE NO TENGA UN ID YA EXISTENTE EN EL SISTEMA (falso significa que aun no existe, entonces lo agregamos al listado)
                                    if(funcionesComponentes.verificaraComponenteConId(listadoFormulariosAux, componenteAux) == false){
                                        listadoFormulariosAux = funcionesComponentes.agregarComponenteFormularioPorId(componenteAux, listadoFormulariosAux);  //agregamos el componente a los listados
                                    }else{
                                        //Si ya existe el id, entonces notificamos
                                        listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "COMPONENTE CON ID REPETIDO", 
                                                        " Quiere agregar un componente con un id repetido en el bloque del formulario", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));                              
                                    }                                
                                }               

                                break;
                            case "\"MODIFICAR_COMPONENTE\"":
                                System.out.println("modificamos un componente");
                                int indexClaseModComponente = bloqueAux.getListadoTipoParametros().indexOf("\"CLASE\"");

                                String idFormModificarComponente = "";
                                String idComponenteModificar = "";

                                for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                    String tipoParam = parametroAux.getTipoParametro().replaceAll("\"", "").trim();
                                    System.out.println("TIPO DE PARAMETRO = "+tipoParam);
                                    if(tipoParam.equals("FORMULARIO")){                                    
                                        idFormModificarComponente = parametroAux.getLexema();
                                        System.out.println("-> ES UN FORM: "+idFormModificarComponente);
                                    }else if(tipoParam.equals("ID")){
                                        idComponenteModificar = parametroAux.getLexema();
                                        System.out.println("-> ES UN ID COMP: "+idComponenteModificar);
                                    }                                
                                }
                                //Buscamos al formulario por el id dentro del listado
                                Formulario formularioModificarComponente = funcionesFormularios.obtenerFormularioPorId(idFormModificarComponente, listadoFormulariosAux);

                                if(formularioModificarComponente != null){
                                    //posicion del form en el listado
                                     int indexFormModificar = listadoFormulariosAux.indexOf(formularioModificarComponente); 


                                    Componente componenteAuxModComponente;
                                    //Creamos el tipo de componente segun la clase                               
                                    componenteAuxModComponente = funcionesComponentes.obtenerComponentePorId( idFormModificarComponente, idComponenteModificar, listadoFormulariosAux);

                                    //EXISTE EL FORMULARIO
                                    //VERIFICAMOS QUE EL COMPONENTE TENGA UN ID YA EXISTENTE EN EL SISTEMA (falso significa que aun no existe, entonces lo agregamos al listado)
                                    if(componenteAuxModComponente != null){
                                        int indexComponenteModificar = formularioModificarComponente.getListadoComponentes().indexOf(componenteAuxModComponente);

                                         //MODIFICAMOS EL COMPOENTE
                                         for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                            String tipoParam = parametroAux.getTipoParametro().replaceAll("\"", "").trim();
                                            if(tipoParam.equals("ID") == false && tipoParam.equals("FORMULARIO") == false) {//si no son los datos identifiacores
                                                componenteAuxModComponente = funcionesComponentes.editarCampo(componenteAuxModComponente, parametroAux.getTipoParametro(), parametroAux.getLexema());
                                            }                                
                                        }
                                        //Guardamos los datos en el listado de componentes al formulario
                                        formularioModificarComponente.getListadoComponentes().set(indexComponenteModificar, componenteAuxModComponente);
                                        //Guardamos los datos en el listado
                                        listadoFormulariosAux.set(indexFormModificar, formularioModificarComponente);

                                    }else{
                                        //Si no existe entones nofiticamos
                                        listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "COMPONENTE INEXISTENTE", 
                                                        " Quiere modificar un componente con un id inexistente en el bloque del formulario", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));                              
                                    }       

                                } else {
                                    listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "FORMULARIO INEXISTENTE", 
                                                        " Quiere modificar un componente a un formulario con un id "+idComponenteModificar+" inexistente en el sistema", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));  
                                }

                                break;
                            case "\"ELIMINAR_COMPONENTE\"":
                                System.out.println("Eliminamos un componente");
                                int indexClaseEliminarCompo = bloqueAux.getListadoTipoParametros().indexOf("\"CLASE\"");

                                String idFormEliminarComponente = "";
                                String idComponenteEliminar = "";

                                for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                    String tipoParam = parametroAux.getTipoParametro().replaceAll("\"", "").trim();
                                    System.out.println("TIPO DE PARAMETRO = "+tipoParam);
                                    if(tipoParam.equals("FORMULARIO")){
                                        idFormEliminarComponente = parametroAux.getLexema();
                                    }else if(tipoParam.equals("ID")){
                                        idComponenteEliminar = parametroAux.getLexema();
                                    }                                
                                }
                                //Buscamos al formulario por el id dentro del listado
                                Formulario formularioEliminarComponente = funcionesFormularios.obtenerFormularioPorId(idFormEliminarComponente, listadoFormulariosAux);

                                if(formularioEliminarComponente != null){
                                    //posicion del form en el listado
                                     int indexFormEliminar = listadoFormulariosAux.indexOf(formularioEliminarComponente); 


                                    Componente componenteAuxEliminarComponente;
                                    //Creamos el tipo de componente segun la clase                               
                                    componenteAuxEliminarComponente = funcionesComponentes.obtenerComponentePorId( idFormEliminarComponente, idComponenteEliminar, listadoFormulariosAux);

                                    //EXISTE EL FORMULARIO
                                    //VERIFICAMOS QUE EL COMPONENTE TENGA UN ID YA EXISTENTE EN EL SISTEMA (falso significa que aun no existe, entonces lo agregamos al listado)
                                    if(componenteAuxEliminarComponente != null){                                    
                                        //Eliminamos el componente del listado de componentes al formulario
                                        formularioEliminarComponente.getListadoComponentes().remove(componenteAuxEliminarComponente);
                                        //Guardamos los datos en el listado
                                        listadoFormulariosAux.set(indexFormEliminar, formularioEliminarComponente);

                                    }else{
                                        //Si no existe entones nofiticamos
                                        listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "COMPONENTE INEXISTENTE", 
                                                        " Quiere eliminar un componente con un id inexistente en el bloque del formulario", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));                              
                                    }       

                                } else {
                                    listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "FORMULARIO INEXISTENTE", 
                                                        " Quiere eliminar un componente a un formulario con un id "+idComponenteEliminar+" inexistente en el sistema", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));  
                                }

                                break;  
                            case "\"CONSULTAR_DATOS\"":
                                System.out.println("CONUSULTAR DATOS: ");
                                for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                    String cadenaSQForm;
                                    if(parametroAux != null){
                                            cadenaSQForm = parametroAux.getLexema();
                                            cadenaSQForm = cadenaSQForm.replaceAll("\"", "");
                                            int filaAux = 0;
                                            int colAux = 0;
                                            try{
                                                filaAux = parametroAux.getLinea();
                                                colAux = parametroAux.getColumna();
                                            }catch(Exception ex){
                                                filaAux = 0;
                                                colAux = 0;
                                            }
                                            
                                            String idFormularioAuxConsultaDatos = cadenaSQForm.substring(cadenaSQForm.indexOf(">")+1, cadenaSQForm.indexOf("[")).trim();
                                            try{       
                                                StringReader sr = new StringReader(cadenaSQForm);
                                                LexerReporteria lexer = new LexerReporteria(sr);
                                                lexer.setFilaInicio(filaAux);
                                                lexer.setColumna(colAux);
                                                
                                                System.out.println(" Lexer SQF Ejecutado");
                                                ParserReporteria pars = new ParserReporteria(lexer);
                                                //buscamos entre los formularios del cliente
                                                Formulario formConsultasAux = funcionesFormularios.obtenerFormularioPorIdYPorUsuarioCreador(idFormularioAuxConsultaDatos, null);
                                                pars.inicializarFormulario(formConsultasAux);
                                                pars.parse();     
                                                pars.setFilaInicio(filaAux);
                                                pars.setColumna(colAux);

                                                System.out.println(" Parser SQF Ejecutado");
                                                System.out.println("_____________________________________________");
                                                System.out.print("Texto SQFORM analizado");
                                                                      
                                                ArrayList<TokenError> listadoErroresLexicos = lexer.obtenerListadoErroresLexicos();
                                                ArrayList<TokenError> listadoErroresSintacticos = pars.getListadoErroresReporteria();
                                                
                                                if(listadoErroresLexicos != null && listadoErroresLexicos.size() > 0){//tiene errores
                                                    for(TokenError tokenError: listadoErroresLexicos){
                                                        resultadoErrores += "Tipo error: "+tokenError.getTipoToken()+ ": Descripcion: "+tokenError.getLexema()+" Mensaje: "+tokenError.getMsgError()+" Lin: "+tokenError.getLinea()+" Col: "+tokenError.getColumna()+"\n";
                                                    }
                                                }
                                                if(listadoErroresSintacticos != null && listadoErroresSintacticos.size() > 0){//tiene errores
                                                    for(TokenError tokenError: listadoErroresSintacticos){
                                                        resultadoErrores += "Tipo error: "+tokenError.getTipoToken()+ ": Descripcion: "+tokenError.getLexema()+" Mensaje: "+tokenError.getMsgError()+" Lin: "+tokenError.getLinea()+" Col: "+tokenError.getColumna()+"\n";
                                                    }
                                                }
                                                
                                                if(resultadoErrores.equals("")){//aun no se detectaron erores
                                                    List<ArrayList<String>> datos = pars.obtenerTablaDatosFinal();
                                                    try{
                                                        ///////////////////////////////////////////////////////AGREGAMOS
                                                        resultado +=  funcionesRespuesta.generarCodigoDatosConsultas(datos);
                                                    }catch(Exception ex) {
                                                        System.out.println("Error en la tabla final indigo: "+ex.getMessage());
                                                    }
                                                   
                                                }
                                                
                                            } catch (Exception ex) {
                                                System.out.println("Error irrecuperrable del SQFORM: "+ex.getMessage()+ex.getLocalizedMessage()+ex.toString());
                                            }
                                            
                                    }else{
                                            cadenaSQForm = "";
                                    }
                                    
                                    
                                }
                                
                               
                                break;
                        }
                    } else {
                        //////////////////////////////////////////////////////////////SOLCITUDES QUE NO PIDEN SESION //////////////////////////////////////////
                        switch(tipoSolicitud){
                            case "\"LOGIN_USUARIO\"":
                                //si es una solicitud para loguearse
                                System.out.println("Iniciamos login");                            
                                String usuarioUsername = "";
                                String usuarioPassword = "";

                                for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                    String tipoParam = parametroAux.getTipoParametro().replaceAll("\"", "").trim();
                                    if(tipoParam.equals("USUARIO")){
                                        usuarioUsername = parametroAux.getLexema();
                                    }else if(tipoParam.equals("PASSWORD")){
                                        usuarioPassword = parametroAux.getLexema();
                                    }
                                }
                                String estadoLogin = funcionesLogin.verificarUsuarioLogin(usuarioUsername, usuarioPassword);
                                String direccion;

                                switch(estadoLogin){
                                    case "Usuario logueado con exito"://se loguea el usuario 
                                        System.out.println("Usuario logueado con exito");
                                        UsuarioLogueado.iniciarSesion(usuarioUsername);
                                        //se loguea el usuario
                                        break;
                                    default:
                                        listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "USUARIO INEXISTENTE", 
                                                       " "+estadoLogin, 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));   
                                }
                                break;
                            

                            case "\"CREAR_USUARIO\"":
                                System.out.println("Agregamos un nuevo usuario");
                                Usuario usuarioAux;

                                //Agregamos los datos al usuario
                                usuarioAux = funcionesUsuarios.agregarDatosUsuario(bloqueAux);

                                //comprobamos que el nombre de usuario sea unico
                                if(funcionesUsuarios.verificarNombreUsuario(listadoUsuariosAux, usuarioAux.getUsuario()) != true){//Si no existe
                                    listadoUsuariosAux.add(usuarioAux);//Agregmamos el usuario
                                }else{
                                    listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "USUARIO YA REGISTRADO", 
                                                       " Quiere agregar un nuevo usuario con username ya existente en el sistema", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));                          
                                }         


                                break;

                            case "\"MODIFICAR_USUARIO\"":
                                System.out.println("Modificamos un usuario");                            
                                String usuarioAntiguo = "";
                                String usuarioNuevo = "";
                                String nuevoPassword = "";
                                String fechaModificacion = fechaSistema;

                                for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                    String tipoParam = parametroAux.getTipoParametro().replaceAll("\"", "").trim();
                                    if(tipoParam.equals("USUARIO_ANTIGUO")){
                                        usuarioAntiguo = parametroAux.getLexema();
                                    }else if(tipoParam.equals("USUARIO_NUEVO")){
                                        usuarioNuevo = parametroAux.getLexema();
                                    }else if(tipoParam.equals("NUEVO_PASSWORD")){
                                        nuevoPassword = parametroAux.getLexema();
                                    }else if(tipoParam.equals("FECHA_MODIFICACION")){
                                        fechaModificacion = parametroAux.getLexema();
                                    }
                                }
                                //Buscamos al usuario por el username antiguo
                                Usuario usuarioAuxModificarUsr = funcionesUsuarios.obtenerUsuarioConNombre(listadoUsuariosAux, usuarioAntiguo);
                                if(usuarioAuxModificarUsr != null){//existe
                                    int index = listadoUsuariosAux.indexOf(usuarioAuxModificarUsr);
                                    usuarioAuxModificarUsr = funcionesUsuarios.editarCampo(usuarioAuxModificarUsr, "\"USUARIO\"", usuarioNuevo);
                                    usuarioAuxModificarUsr = funcionesUsuarios.editarCampo(usuarioAuxModificarUsr, "\"PASSWORD\"", nuevoPassword);
                                    usuarioAuxModificarUsr = funcionesUsuarios.editarCampo(usuarioAuxModificarUsr, "\"FECHA_MODIFICACION\"", fechaModificacion);
                                    //agregamos al listado de usuarios los cambios

                                    listadoUsuariosAux.set(index, usuarioAuxModificarUsr);
                                }else{
                                    listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "USUARIO INEXISTENTE", 
                                                       " Quiere modificar un usuario inexistente en el sistema", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));   
                                }                            

                                // usuarioAux = editarCampo(usuarioAux, tipoParametro, nuevoValor);
                                break;

                            case "\"ELIMINAR_USUARIO\"":
                                System.out.println("Modificamos un usuario");                            
                                String usuarioEliminar = "";

                                for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                    String tipoParam = parametroAux.getTipoParametro().replaceAll("\"", "").trim();
                                    if(tipoParam.equals("USUARIO")){
                                        usuarioEliminar = parametroAux.getLexema();
                                    }
                                }
                                //Buscamos al usuario por el username antiguo
                                Usuario usuarioAEliminar = funcionesUsuarios.obtenerUsuarioConNombre(listadoUsuariosAux, usuarioEliminar);
                                if(usuarioAEliminar != null){//existe                              
                                    //Removemos el usuario del bloque
                                    listadoUsuariosAux.remove(usuarioAEliminar);
                                }else{
                                    listadoErroresSemanticos.add(new TokenError("ERROR SEMANTICO", "USUARIO INEXISTENTE", 
                                                       " Quiere modificar un usuario inexistente en el sistema", 
                                                        bloqueAux.getLinea(), bloqueAux.getColumna()));   
                                }                            

                                // usuarioAux = editarCampo(usuarioAux, tipoParametro, nuevoValor);
                                break;
                        }
                    }
                    /*
                    RESPUESTA DEL SERVIDOR
                    for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                        if(parametroAux != null){    
                            resultado += "Parametro: "+parametroAux.getTipoParametro()+" Valor: "+parametroAux.getLexema()+"\n";

                        }
                    }*/
                    
                    
                    
                }     
                
                if(listadoErroresSemanticos != null && listadoErroresSemanticos.size() > 0){//hay errores
                    existenciaErrores = true;
                }
                
                resultado += "\n"+funcionesRespuesta.generarCodigoRespuestaSolicitud(bloqueAux, listadoErroresSemanticos);
                //Agregmos
            }
            if(listadoSolicitudes.size() > 0){
                System.out.println("<!ini_respuestas>\n");
                System.out.println(resultado);
                System.out.println("<!fin_respuestas>\n");
                
                resultado = "<!ini_respuestas>\n" + resultado + "<!fin_respuestas>\n";
            }
            
            
            ////////////////GUARDAMOS LA NUEVA INFORMACION SI NO SURGIO NINGUN ERROR SEMANTICO            
            //System.out.println(funcionesFormularios.generarCodigoAlmacenamientoFormularios(listadoFormulariosAux));
            //System.out.println(funcionesUsuarios.generarCodigoAlmacenamientoUsuarios(listadoUsuariosAux));

            if(existenciaErrores == false){//si no hay errores
                GuardarDatos gd = new GuardarDatos();
                gd.guardarDatos(funcionesFormularios.generarCodigoAlmacenamientoFormularios(listadoFormulariosAux),"formularios");
                gd.guardarDatos(funcionesUsuarios.generarCodigoAlmacenamientoUsuarios(listadoUsuariosAux),"usuarios");

            }
            
            
        }
        if(resultadoErrores.equals("") == false){//hay errores
            return resultadoErrores;
        }else{
            return resultado;
        }
        
        
    }

}
