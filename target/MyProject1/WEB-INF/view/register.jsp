
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Registration Form</title>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>

<h3>User Register Form</h3>

<p style="color: #ff213f;">${errorMessage}</p>

<form action="register" method="post">
    <table style="with: 50%">
        <tr>
            <td>User name</td>
            <td><input type="text" name="name" pattern="[A-Za-z0-9]{1,20}"
                       id="name" placeholder="Enter your name" title="Name should be greater than 1 and less than 20 characters. Use only A-Z, a-z, 0-9 characters." /></td>
        </tr>
        <tr>
            <td>User login</td>
            <td><input type="text" name="login" pattern="[A-Za-z0-9]{1,20}"
                       id="login" placeholder="Enter your login" title="Login should be greater than 1 and less than 20 characters. Use only A-Z, a-z, 0-9 characters." /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" pattern="[A-Za-z0-9@#$%!^&*]{6,20}"
                        id="password" placeholder="Enter Password" title="Password should be greater than 6 and less than 20 characters . Use only A-Z, a-z, 0-9, @ # $ % ! ^ & * characters."
                         /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                       id="email" placeholder="Enter email" title="Enter valid Email"
            /></td>

    </table>
    <input type="submit" value="Submit" /></form>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
