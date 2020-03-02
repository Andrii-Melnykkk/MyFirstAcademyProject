<%--
  Created by IntelliJ IDEA.
  User: Anya
  Date: 19.02.2020
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Advertisement</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>

<h3>Create Advertisement</h3>

<p style="color: #ff213f;">${errorMessage}</p>

<form method="POST" action="${pageContext.request.contextPath}/createAdvertisement">
    <table border="0">
        <tr>
            <td>Short content</td>
            <td><textarea type="text" name="content" value="${advertisement.content}"
                          maxlength="20" cols="30" rows="1"></textarea></td>
        </tr>
        <tr>
            <td>Advertisement</td>
            <td><textarea type="text" name="text" value="${advertisement.text}"
                          maxlength="200" cols="30" rows="5"></textarea></td>
        </tr>
        <tr>
            <td colspan="2">
                <br><br>
                Type of your Advertisement: <select name="type" value="${advertisement.type}">

                <option>Other</option>
                <option>Buying ads</option>
                <option>Sales ads</option>
            </select>
                <br><br>
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
</form>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
