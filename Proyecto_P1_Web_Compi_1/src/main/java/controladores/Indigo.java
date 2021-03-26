package controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import archivos.CargarDatos;
import archivos.GuardarDatos;
import clasesDAO.BloqueParametros;
import clasesDAO.TokenParametro;
import clasesDAOFormularios.Componente;
import clasesDAOFormularios.ComponenteBoton;
import clasesDAOFormularios.Formulario;
import clasesDAOUsuario.Usuario;
import funciones.FuncionesComponentes;
import funciones.FuncionesFormularios;
import funciones.FuncionesUsuario;
import gramatica_indigo.LexerIndigo;
import gramatica_indigo.parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        response.setContentType("text/html;charset=UTF-8");
        
        
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
            
            
            
            try{
                StringReader sr = new StringReader(textoRecibido);
                LexerIndigo lexer = new LexerIndigo(sr);
                System.out.println(" Lexer Ejecutado");
                parser pars = new parser(lexer);
                pars.parse();

                System.out.println(" Parser Ejecutado");
                System.out.println("_____________________________________________");
                
                //FUNCIONEs
                FuncionesComponentes funcionesComponentes = new FuncionesComponentes(); 
                FuncionesFormularios funcionesFormularios = new FuncionesFormularios();
                FuncionesUsuario funcionesUsuarios = new FuncionesUsuario();
                
                /////
                ArrayList<BloqueParametros> listadoSolicitudes = pars.getListadoSolicitudes();                
                ArrayList<Formulario> listadoFormularios = new ArrayList();
                ArrayList<String> listadoIdFormularios = new ArrayList();
                ArrayList<Usuario> listadoUsuarios = new ArrayList();
                
                if(listadoSolicitudes != null){//Si existe un listado de solicitudes
                    
                    for(BloqueParametros bloqueAux: listadoSolicitudes){                                                
                        if(bloqueAux != null){//Si el bloque no es nulo
                            //Tipo de token y tipo de parametros
                            out.print(bloqueAux.getTipoToken() + ": "+bloqueAux.getLexema()+"\n");
                            
                            String tipoSolicitud = bloqueAux.getTipoToken();
                            switch(tipoSolicitud){
                                case "\"NUEVO_FORMULARIO\"":
                                    Formulario formularioAux;
                                    System.out.println("Agregamos un nuevo formulario");
                                    formularioAux = funcionesComponentes.agregarDatosFormulario(bloqueAux);
                                    listadoFormularios.add(formularioAux);//agregamos el formulario al listado
                                    listadoIdFormularios.add(formularioAux.getId());//agregamos el id del formulario auxiliar
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
                                    listadoFormularios = funcionesComponentes.agregarComponenteFormularioPorId(componenteAux, listadoFormularios, listadoIdFormularios);                                
                                    break;
                                case "\"CREAR_USUARIO\"":
                                    System.out.println("Agregamos un nuevo usuario");
                                    Usuario usuarioAux = new Usuario();

                                    //Agregamos los datos al usuario
                                    usuarioAux = funcionesUsuarios.agregarDatosUsuario(bloqueAux, usuarioAux);
                                    
                                    //comprobamos que el nombre de usuario sea unico
                                    if(funcionesUsuarios.verificarNombreUsuario(listadoUsuarios, usuarioAux.getUsuario()) != true){//Si no existe
                                        listadoUsuarios.add(usuarioAux);//Agregmamos el usuario
                                    }
                                    
                                    break;
                                default:
                                    
                            }
                            
                            for(TokenParametro parametroAux: bloqueAux.getListadoParametros()){
                                if(parametroAux != null){                                               
                                    out.print("Parametro: "+parametroAux.getTipoParametro()+" Valor: "+parametroAux.getLexema()+"\n");                                    
                                    
                                }
                            }
                            out.print("\n");
                        }         
                    }
                    
                    
                    System.out.println(funcionesFormularios.generarCodigoAlmacenamientoFormularios(listadoFormularios));
                    System.out.println(funcionesUsuarios.generarCodigoAlmacenamientoUsuarios(listadoUsuarios));
                    
                    GuardarDatos gd = new GuardarDatos();
                    gd.guardarDatos(funcionesFormularios.generarCodigoAlmacenamientoFormularios(listadoFormularios),"formularios");
                    gd.guardarDatos(funcionesUsuarios.generarCodigoAlmacenamientoUsuarios(listadoUsuarios),"usuarios");
                    
                    CargarDatos cd = new CargarDatos();
                    cd.leerDatos("formularios");
                    cd.leerDatos("usuarios");
                }
                
                
                
                
            } catch (Exception ex) {
                System.out.println("Error irrecuperrable: "+ex.getMessage()+ex.getLocalizedMessage()+ex.toString());
            } 
            
            
            
            
            
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

}
