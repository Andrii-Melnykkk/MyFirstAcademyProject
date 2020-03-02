<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <meta charset="UTF-8">
    <title>Advertisement list</title>
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
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>
<h3>Advertisement list</h3>

<p style="color: red;">${errorMessage}</p>

<table border: 1px solid black;>
    <thead>
    <tr>
        <th>#</th>
        <th>Name of Advertiser</th>
        <th>Date of creation</th>
        <th>Short content</th>
        <th>Advertisement</th>
        <th>Advertisement type</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="i" value="1"/>


    <c:forEach var="advertisement" items="${advertisementListApproved}">
        <tr>
            <td>
                <c:out value="${i}"/>
            </td>
            <td>
                <c:out value="${advertisement.user.name}"/>
            </td>
            <td>
                <c:out value="${advertisement.createDate.format(DateTimeFormatter.ofPattern('dd.MM.yyyy'))}"/>
            </td>
            <td>
                <c:out value="${advertisement.content}"/>
            </td>
            <td>
                <c:out value="${advertisement.text}"/>
            </td>
            <td>
                <c:out value="${advertisement.type}"/>
            </td>
        </tr>
        <c:set var="i" value="${i+1}"/>
    </c:forEach>
    </tbody>
</table>
<br/>
<br/>
<br/>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
