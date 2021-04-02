<%-- 
    Document   : visualizar-formulario
    Created on : 27 mar. 2021, 3:39:30
    Author     : grifiun
--%>

<%@page import="clasesDAOFormularios.Formulario"%>
<%@page import="java.lang.String"%>
<%@page import="funciones.FuncionesFormularios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            FuncionesFormularios funcionesFormularios = new FuncionesFormularios();            
            String index = request.getParameter("index");
            String idFormulario = session.getAttribute("id_formulario"+index).toString();
            Formulario formAux =  funcionesFormularios.obtenerFormularioPorId(idFormulario);
            String codigoHTMLFormulario =formAux.generarCodigoHTMLFormulario();
            String tema = "powderblue";
            if(formAux != null){
                if(formAux.getTema().replaceAll("\"", "").trim().equalsIgnoreCase("dark")){
                    tema = "#b3b3b3";
                }else if(formAux.getTema().replaceAll("\"", "").trim().equalsIgnoreCase("white")){
                    tema = "#ffffff";
                }   
            }
            System.out.println("CODIGO HTML\n\n"+codigoHTMLFormulario);
            
        %>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso administrador</title>        
        <%@include file="../html/css-bootstrap.html"%>
        <link href="../css/style-ingresos.css" rel="stylesheet" type="text/css"/>
    </head>
    <%
    
    %>
    <body  style="background-color:<%=tema%>;">
        <%@include file="../html/navs/nav-usuario.html" %>    
        <%@include file="../html/ingresos/parte-superior.html" %>
        
        <h5>FORM: <%=idFormulario%></h5>
        <% out.print(codigoHTMLFormulario);%>
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>
