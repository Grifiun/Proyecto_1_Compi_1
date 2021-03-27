<%-- 
    Document   : visualizar-listado-formularios
    Created on : 27 mar. 2021, 2:36:32
    Author     : grifiun
--%>

<%@page import="funciones.FuncionesFormularios"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%        
        //titulos
        ArrayList<String> titulo = new ArrayList<>(Arrays.asList("ID_FORMULARIO","TITULO","NOMBRE","TEMA","PUBLICO", "ACCIONES"));
        String usuario = session.getAttribute("codigo").toString();
        
        System.out.println("-----------------USUARIO: "+usuario+"\n");
        
        FuncionesFormularios funcionesFormularios = new FuncionesFormularios();
        List<ArrayList<String>> lista = funcionesFormularios.listadoDatosFormularios(usuario);
        
        %>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso cita</title>        
        <%@include file="../html/css-bootstrap.html"%>
        <link href="../css/style-ingresos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../html/navs/nav-usuario.html" %>
        <%@include file="../html/ingresos/parte-superior.html" %>
        <%@include file="../html/mostrar-registro/tabla-generica.html" %>
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>
