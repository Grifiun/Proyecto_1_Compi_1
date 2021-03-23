/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
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
            String textoRecibido = "TEXTO DEL SERVER AL CLIENTE:\n";
            String aux = "";

            try {
                while((aux = bf.readLine()) != null){//Lee todas las lineas del texto  
                    textoRecibido = textoRecibido + aux + "\n";
                }
            } catch (Exception e) {
                System.out.println("ERROR AL LEER EL BF: "+e.getMessage());
            }
            System.out.println("LOG TOMCAT: "+textoRecibido);
            out.print(textoRecibido);
            
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
