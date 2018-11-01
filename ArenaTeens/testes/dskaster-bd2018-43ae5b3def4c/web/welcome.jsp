<%-- 
    Document   : welcome
    Created on : 27/06/2018, 11:46:44
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="session" tagdir="/WEB-INF/tags/session"  %>

<session:usuario context="${pageContext.servletContext.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bem vindo!</title>
        <%@include file="/view/include/head.jsp"  %>        
    </head>
    <body>
        <%@include file="/view/include/navbar.jsp"%>
        <div class="container">

            <div class="jumbotron">
                <h1>Bem-vindo,
                    <c:out value="${usuario.nome}"/>!</h1>
                <p>Este é um exemplo de cadastro de usuários para o trabalho da disciplina Bancos de Dados.</p>
                <p>
                    <a class="btn btn-lg btn-primary" href="https://bitbucket.org/dskaster/bd2018" target="_blank">
                        Saiba mais...
                    </a>
                </p>
            </div>
        </div>
        <%@include file="/view/include/scripts.jsp"%>
    </body>
</html>
