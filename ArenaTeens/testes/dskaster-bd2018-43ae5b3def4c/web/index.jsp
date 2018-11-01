<%-- 
    Document   : index
    Created on : 27/06/2018, 11:25:31
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="session" tagdir="/WEB-INF/tags/session"  %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Cadastro de Usuários</title>
        <%@include file="/view/include/head.jsp"%>
        <link href="${pageContext.servletContext.contextPath}/assets/css/login.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="${pageContext.servletContext.contextPath}/login" method="POST">
                <h2 class="form-signin-heading">Por favor, faça login.</h2>

                <input class="form-control" type="text" name="login" placeholder="Usuário" required autofocus>
                <input class="form-control" type="password" name="senha" placeholder="Senha" required>
                <p class="help-block">Ainda não é cadastrado?
                    <a href="${pageContext.servletContext.contextPath}/usuario/create">
                        Clique aqui
                    </a>
                </p>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </form>

            <session:erro alertClass="login-alert" errorMessage="${sessionScope.error}"/>
        </div>
        <%@include file="/view/include/scripts.jsp"%>
        <script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
    </body>
</html>
