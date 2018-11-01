<%-- 
    Document   : create
    Created on : 26/06/2018, 11:32:31
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="session" tagdir="/WEB-INF/tags/session"  %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuário|Criar</title>
        <%@include file="/view/include/head.jsp" %>
        <link href="${pageContext.servletContext.contextPath}/assets/vendor/css/datepicker.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_form.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="/view/include/navbar.jsp"%>
        <div class="container">
            <h2 class="text-center">Inserção de um novo usuário</h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/usuario/create"
                enctype="multipart/form-data"
                method="POST">

                <div class="form-group">
                    <label class="control-label" for="usuario-login">Login</label>
                    <input id="usuario-login" class="form-control" type="text" name="login" required autofocus/>

                    <p class="help-block"></p>
                </div>


                <div class="form-group">
                    <label class="h4">Senha</label>
                    <input class="form-control password-input"
                           type="password" name="senha"
                           pattern=".{4,}" required title="Pelo menos 4 caracteres."/>
                </div>

                <div class="form-group pwd-confirm">
                    <label class="h4">Confirmar senha</label>
                    <input class="form-control password-confirm"
                           type="password" name="senha-confirmacao"
                           pattern=".{4,}" required title="Pelo menos 4 caracteres."/>
                    <p class="help-block"></p>
                </div>

                <div class="form-group">
                    <label for="usuario-nome" class="control-label">Nome</label>
                    <input id="usuario-nome" class="form-control" type="text" name="nome" required/>
                </div>


                <div class="form-group">
                    <label for="usuario-nasc" class="control-label">Data de nascimento</label>
                    <input id="usuario-nasc" class="form-control datepicker" type="text" name="nascimento"
                           placeholder="dd/mm/yyyy"
                           pattern="\d{2}/\d{2}/\d{4}" required/>
                </div>

                <div class="form-group">
                    <label for="usuario-avatar">Foto do perfil</label>
                    <input type="file"
                           class="form-control" id="usuario-avatar"
                           name="avatar"
                           accept="image/*"/>
                </div>

                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Salvar</button>
                </div>
            </form>

            <session:erro alertClass="usuario-form-alert" errorMessage="${sessionScope.error}" rollbackErrorMessage="${sessionScope.rollbackError}"/>

        </div>
        <%@include file="/view/include/scripts.jsp" %>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap-datepicker.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap-datepicker.pt-BR.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>

    </body>
</html>
