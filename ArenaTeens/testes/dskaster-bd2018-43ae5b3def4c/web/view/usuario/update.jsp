<%-- 
    Document   : update
    Created on : 27/06/2018, 10:50:46
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@taglib prefix="session" tagdir="/WEB-INF/tags/session"  %>

<session:usuario context="${pageContext.servletContext.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuário|Edição</title>
        <%@include file="/view/include/head.jsp" %>
        <link href="${pageContext.servletContext.contextPath}/assets/vendor/css/datepicker.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_form.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/view/include/navbar.jsp"%>
        <div class="container">
            <h2 class="text-center">Edição do usuário <c:out value="${usuario.nome}"/></h2>

            <form class="form-group" action="${pageContext.servletContext.contextPath}/usuario/update" method="POST">

                <input type="hidden" name="id" value="${usuario.id}">

                <div class="form-group">
                    <label class="h4">Login</label>
                    <input class="form-control" type="text" name="login" value="${usuario.login}" required autofocus/>
                </div>

                <div class="form-group">
                    <label class="h4">Senha</label>
                    <input class="form-control password-input"
                           type="password" name="senha"
                           pattern=".{4,}" title="Pelo menos 4 caracteres."/>
                </div>

                <div class="form-group pwd-confirm">
                    <label class="h4">Confirmar senha</label>
                    <input class="form-control password-confirm"
                           type="password" name="senha-confirmacao"
                           pattern=".{4,}" title="Pelo menos 4 caracteres."/>
                    <p class="help-block"></p>
                </div>

                <div class="form-group">
                    <label class="h4">Nome</label>
                    <input class="form-control" type="text" name="nome" value="${usuario.nome}" required title="Nome">
                </div>

                <div class="form-group">
                    <label class="h4">Data de nascimento</label>
                    <input class="form-control datepicker" type="text" name="nascimento"
                           value="<fmt:formatDate pattern="dd/MM/yyyy" value="${usuario.nascimento}"/>"
                           placeholder="dd/mm/yyyy" pattern="\d{2}/\d{2}/\d{4}" required>
                </div>

                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Editar</button>
                </div>
            </form>

            <session:erro alertClass="usuario-form-alert" errorMessage="${sessionScope.error}"/>
        </div>
        <%@include file="/view/include/scripts.jsp" %>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap-datepicker.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap-datepicker.pt-BR.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>

    </body>
</html>
