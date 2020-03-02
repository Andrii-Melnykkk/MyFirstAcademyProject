<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <meta charset="UTF-8">
    <title>User list</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2
        }

        th {
            background-color: #af369a;
            color: white;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>
<h3>User list</h3>
<p style="color: red;">${errorMessage}</p>

<table border: 1px solid black;>
    <thead>
    <tr>
        <th>#</th>
        <th>User login</th>
        <th>User name</th>
        <th>Current User's role</th>
        <th>Set user's role:</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="i" value="1"/>


    <c:forEach var="user" items="${usersWithoutAdmins}">
        <tr>
            <td>
                <c:out value="${i}"/>
            </td>
            <td>
                <c:out value="${user.login}"/>
            </td>
            <td>
                <c:out value="${user.name}"/>
            </td>
            <td>
                <c:out value="${user.role}"/>
            </td>
            <form method="POST" action="${pageContext.request.contextPath}/adminTask">
                <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                <td>
                <input type="radio" name="role" value="User"
                    <c:if test="${user.role eq 'USER'}">
                       checked</c:if>
                >User<br/><br/>
                <input type="radio" name="role"  value="Manager"
                <c:if test="${user.role eq 'MANAGER'}">
                       checked</c:if>
                >Manager<br/><br/>
                <input type="radio" name="role"  value="Administrator"
                <c:if test="${user.role eq 'ADMIN'}">
                       checked</c:if>
                >Administrator<br/><br/>
            </td>
            <td>
                <input  type="submit" value="submit" style="float: right; height:30px; width:100px">
            </td>
            </form>
        </tr>
        <c:set var="i" value="${i+1}"/>
    </c:forEach>
    </tbody>
</table>
<br/>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>