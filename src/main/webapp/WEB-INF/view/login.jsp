<%--
  Created by IntelliJ IDEA.
  User: Anya
  Date: 18.02.2020
  Time: 02:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>

<h3>Login Page</h3>
<p style="color: #ff1522;">${errorMessage}</p>


<form method="POST" action="${pageContext.request.contextPath}/login">
    <table border="0">
        <tr>
            <td>User Login</td>
            <td><input type="text" name="login" value= "${user.login}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td>Remember me</td>
            <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
            </td>
        </tr>
    </table>
</form>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
