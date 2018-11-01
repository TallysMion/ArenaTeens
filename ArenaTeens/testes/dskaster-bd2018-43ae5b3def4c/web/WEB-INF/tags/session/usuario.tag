<%-- 
    Document   : usuario
    Created on : 03/07/2018, 10:31:06
    Author     : dskaster
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@tag description="Authentication checking" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="context" required="true"%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${empty sessionScope.usuario}">
    <c:redirect url="/" />
</c:if>