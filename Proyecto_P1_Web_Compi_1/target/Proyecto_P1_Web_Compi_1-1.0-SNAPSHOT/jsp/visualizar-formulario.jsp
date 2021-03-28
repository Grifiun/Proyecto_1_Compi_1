<%-- 
    Document   : visualizar-formulario
    Created on : 27 mar. 2021, 3:39:30
    Author     : grifiun
--%>

<%@page import="funciones.FuncionesFormularios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            FuncionesFormularios funcionesFormularios = new FuncionesFormularios();
            String idFormulario = session.getAttribute("id_formulario").toString();
            String codigoHTMLFormulario = funcionesFormularios.obtenerFormularioPorId(idFormulario).generarCodigoHTMLFormulario();
            System.out.println("CODIGO HTML\n\n"+codigoHTMLFormulario);
            
        %>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso administrador</title>        
        <%@include file="../html/css-bootstrap.html"%>
        <link href="../css/style-ingresos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../html/navs/nav-usuario.html" %>    
        <%@include file="../html/ingresos/parte-superior.html" %>
        
        <h5>FORM: <%=idFormulario%></h5>
        <% out.print(codigoHTMLFormulario);%>
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>
