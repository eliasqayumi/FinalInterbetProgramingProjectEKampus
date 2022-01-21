<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="l" dir="ltr">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value="/first.css"/>">
    <title>Log in</title>
</head>
<body>
<div class="image">
    <img src="education.jpeg" alt="value not found">
</div>
<div class="container">

    <form class="login" action="<%=request.getContextPath()%>/login" method="post">
        <h1>login</h1>
        <small>${message}</small>
        <input type="text" class="inputs" name="username" value="${username}"
               placeholder="username" required>
        <hr>
        <input type="password" class="inputs" name="password" placeholder="password" required>
        <hr>
        <input type="submit" class="button" name="submit" value="login">
        <div class="footerOfForm">
            <label for="checkbox">
                <input type="checkbox" id="checkbox" name="remember">
                remember me
            </label>
            <a href="#">forgot password</a>
        </div>
    </form>
</div>


</body>
</html>
