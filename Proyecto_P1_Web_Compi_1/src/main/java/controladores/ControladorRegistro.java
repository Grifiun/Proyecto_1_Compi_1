/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import archivos.CargarDatos;
import clasesDAOFormularios.Componente;
import clasesDAOFormularios.Formulario;
import funciones.FuncionesComponentes;
import funciones.FuncionesFormularios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grifiun
 */
@WebServlet(name = "ControladorRegistro", urlPatterns = {"/ControladorRegistro"})
public class ControladorRegistro extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorRegistro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorRegistro at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");          
            
            
            
            
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
        System.out.println("DATOS RECIBIDOS -----------------------------------------------------------------------\n");
        String id_formulario = request.getParameter("ID_FORMULARIO").toString();
        System.out.println("IDFORM: " +id_formulario);

        CargarDatos cd = new CargarDatos();            
        FuncionesFormularios funcionesFormularios = new FuncionesFormularios();
        FuncionesComponentes funcionesComponentes = new FuncionesComponentes();
        //Obtenemos el formulario con el id recibido
        Formulario formularioAux = funcionesFormularios.obtenerFormularioPorId(id_formulario);

        try {
            for(Componente componenteAux: formularioAux.getListadoComponentes()){
                
                String claseComponente = componenteAux.getClase().replaceAll("\"","").trim();
                
                if(claseComponente.equals("BOTON") == false && claseComponente.equals("IMAGEN") == false){//tienen que ser datos que recopilen informacion
                    System.out.println("DATO RECIBIDO DEL COMPONENTE: "+componenteAux.getNombreCampo().replaceAll("\"", "").trim());
                    String idComponenteAux = componenteAux.getIdComponente();
                    if(claseComponente.equals("CHECKBOX")){
                        int index = 0;
                        String datosCheckBox = "";
                        for(String opcion: componenteAux.getOpciones()){
                            String datoRecibido = request.getParameter(idComponenteAux.replaceAll("\"", "").trim()+"-"+index);                           
                            if(datoRecibido != null){
                                datosCheckBox += (index + 1)+".: "+ datoRecibido+"  ";                                
                                System.out.println("REGISTRO CHEBOX: "+index+" = "+datoRecibido);                                
                            }                            
                            index++;
                        }     
                        if(datosCheckBox.equals("") == true || datosCheckBox == null){
                            datosCheckBox = "---------";
                        }
                        
                        funcionesComponentes.agregarRegistroComponente(id_formulario, idComponenteAux, "\""+datosCheckBox+"\"");
                    }else{                        
                        String datoRecibido = request.getParameter(idComponenteAux.replaceAll("\"", "").trim());                        
                        if(datoRecibido == null || datoRecibido.equals("") == true){
                            datoRecibido = "---------";
                        }
                        
                        System.out.println(" REGISTRO  = "+datoRecibido);
                        funcionesComponentes.agregarRegistroComponente(id_formulario, idComponenteAux, "\""+datoRecibido+"\"");
                    }
                    
                    
                }
                
               
            }
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
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
