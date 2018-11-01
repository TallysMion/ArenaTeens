<%-- 
    Document   : navbar
    Created on : 03/07/2018, 11:55:04
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand"><a href="${pageContext.servletContext.contextPath}/">Banco de Dados</a></span>
        </div>
        <div class="navbar-collapse collapse">
            <c:if test="${not empty sessionScope.usuario}">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/">Home</a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown">
                            Gerenciar <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.servletContext.contextPath}/usuario">Usuários</a></li>
                        </ul>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>
                </ul>
            </c:if>

        </div>
    </div>
</div>