<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>Bulletin board</h1>
    </div>

    <div style="position: absolute; left: 50%;">
        <h2><c:choose>
            <c:when test="${loginedUser==null}">
                <br />
            </c:when>
            <c:otherwise>
                Hello, ${loginedUser.login}!
                <br />
            </c:otherwise>
        </c:choose>
        </h2>
        </div>


    <div style="float: right; padding: 10px; text-align: right;">

        <br/>
        <c:choose>
            <c:when test="${loginedUser==null}">
                <a href="${pageContext.request.contextPath}/register">Register here!</a>
                <br />
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/logout">Logout</a>
                <br />
            </c:otherwise>
        </c:choose>

    </div>

</div>
