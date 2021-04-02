/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import clasesDAOFormularios.Formulario;
import funciones.FuncionesFormularios;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grifiun
 */
@WebServlet(name="ControladorExportarFormulario", urlPatterns={"/ControladorExportarFormulario"})
public class ControladorExportarFormulario extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");   
        String index = request.getSession().getAttribute("index").toString();
        String idFormulario = request.getSession().getAttribute("id_formulario"+index).toString().replaceAll("\"", "").trim();
        String idUsuario = request.getSession().getAttribute("codigo").toString().replaceAll("\"", "").trim();        
        FuncionesFormularios funcionesFormulario = new FuncionesFormularios();
        Formulario formularioAux = funcionesFormulario.obtenerFormularioPorIdYPorUsuarioCreador(idFormulario, idUsuario);        
                
        
        
        if(formularioAux != null){            
            try{
                String nombreArchivo = "Formulario: "+formularioAux.getNombre();
                nombreArchivo += ".form";
                String contenidoString = formularioAux.generarCodigoExportado();
                byte[] contenido = contenidoString.getBytes();

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition","attachment;filename="+nombreArchivo);
                ServletOutputStream ouputStream = response.getOutputStream();
                //Mandamos a descargar el archivo
                ouputStream.write(contenido);
                ouputStream.flush();
                ouputStream.close();
            }catch(Exception ex){ 
                ex.printStackTrace(); 
                System.out.println("Ocurrio un error al exportar el aarchivo");
            } 
        }
            
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
