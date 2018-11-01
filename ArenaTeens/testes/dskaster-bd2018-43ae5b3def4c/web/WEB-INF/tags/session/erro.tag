<%-- 
    Document   : erro
    Created on : 04/07/2018, 11:41:41
    Author     : dskaster
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="alertClass" required="true"%>
<%@attribute name="errorMessage" required="true"%>
<%@attribute name="rollbackErrorMessage" required="false"%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${not empty sessionScope.error}">
    <div class="alert alert-danger fade in <c:out value="${alertClass}"/>">
        <button class="close" type="button" data-dismiss="alert">&times;</button>
        <c:out value="${errorMessage}"/>
    </div>

    <c:remove var="error" scope="session"/>
</c:if>

<c:if test="${not empty sessionScope.rollbackError}">
    <div class="alert alert-danger fade in <c:out value="${alertClass}"/>">
        <button class="close" type="button" data-dismiss="alert">&times;</button>
        <c:out value="${rollbackErrorMessage}"/>
    </div>

    <c:remove var="rollbackError" scope="session"/>
</c:if>