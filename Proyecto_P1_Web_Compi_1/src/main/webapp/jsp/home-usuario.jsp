<%-- 
    Document   : home-paciente
    Created on : 03-oct-2020, 2:16:02
    Author     : grifiun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            if(session.getAttribute("fecha_sistema") == null){
                String fecha = "2020/10/04";
                session.setAttribute("fecha_sistema", fecha);            
            }  
            session.setAttribute("codigoAleatorio", "activado");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/css-bootstrap.html"%>
        <title>Admin</title>
        <%@include file="../html/css-bootstrap.html"%>
        <link href="../css/style-ingresos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../html/navs/nav-usuario.html" %>    
        <%@include file="../html/ingresos/parte-superior.html" %>
        <%@include file="../html/form-mensaje-resultado-ingreso.html" %>
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>

