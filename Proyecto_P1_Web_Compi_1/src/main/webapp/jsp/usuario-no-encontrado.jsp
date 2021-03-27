<%-- 
    Document   : usuario-no-encontrado
    Created on : 03-oct-2020, 2:17:10
    Author     : grifiun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Incorrecto</title>
        <%@include file="../html/css-bootstrap.html" %>
        <link href="../css/style-login.css" rel="stylesheet" type="text/css"/>
        <link href="../css/posicion-caja1-login.css" rel="stylesheet" type="text/css"/>  
        
        <%            
            String mensajeError = session.getAttribute("mensajeError").toString();
        %>
    </head>
    <body>           
        <%@include file="../html/html-login/parte-superior-login.html"%>
        <%@include file="../html/crear-usuario/imagen.html"%>
        <%@include file="../html/usuario-incorrecto/regresar.html" %>
        <%@include file="../html/html-login/parte-inferior-login.html" %>    
        
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>

