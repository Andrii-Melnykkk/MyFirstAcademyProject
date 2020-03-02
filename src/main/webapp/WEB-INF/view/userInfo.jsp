<%--
  Created by IntelliJ IDEA.
  User: Anya
  Date: 18.02.2020
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>

<h3>${user.login}'s account information:</h3>

User login: <b>${user.login}</b>
<br />
User name: <b>${user.name}</b>
<br />
User email: <b>${user.email}</b>
<br />
<br />


<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

