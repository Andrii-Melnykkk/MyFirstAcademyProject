<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anya
  Date: 17.02.2020
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div style="padding: 10px">
   || <a href="${pageContext.request.contextPath}/">Home</a> ||
    <a href="${pageContext.request.contextPath}/advertisementListApproved">Advertisement List</a> ||
    <c:if test="${loginedUser.role eq 'USER'}">
        <a href="${pageContext.request.contextPath}/createAdvertisement">Create Advertisement</a> ||
    </c:if>
    <c:if test="${loginedUser!=null}">
        <a href="${pageContext.request.contextPath}/userInfo">User info</a> ||
    </c:if>
    <c:if test="${loginedUser.role eq 'MANAGER'}">
        <a href="${pageContext.request.contextPath}/managerTask">Manager tasks</a> ||
    </c:if>
    <c:if test="${loginedUser.role eq 'ADMIN'}">
        <a href="${pageContext.request.contextPath}/adminTask">Administrator tasks</a> ||
    </c:if>
    <c:if test="${loginedUser == null}">
        <a href="${pageContext.request.contextPath}/login">Login</a> ||
    </c:if>
</div>


