<%-- 
    Document   : index
    Created on : 27 mar. 2021, 0:09:21
    Author     : grifiun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <title>Login</title>
        <%
            session.setAttribute("codigoAleatorio", "activado");
            session.setAttribute("fechaSistema", "activado");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css-botstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style-login.css" rel="stylesheet" type="text/css"/>
        <link href="css/posicion-caja1-login.css" rel="stylesheet" type="text/css"/>
        <script src="js-bootstrap/bootstrap.js" type="text/javascript"></script>
        <script src="../script/script-login.js" type="text/javascript"></script>
      
    </head>
    <body>       
        <%@include file="html/html-login/parte-superior-login.html"%>
        <%@include file="html/html-login/imagen-superior.html"%>
        <%@include file="html/html-login/form-datos-login.html"%>
        <%@include file="html/html-login/parte-inferior-login.html"%>        
        
        <script src="js-bootstrap/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="js-bootstrap/bootstrap.bundle.js" type="text/javascript"></script>
    </body>
</html>
