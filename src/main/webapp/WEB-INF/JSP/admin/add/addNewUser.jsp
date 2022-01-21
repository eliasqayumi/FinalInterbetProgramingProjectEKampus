<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New User</title>
    <link rel="stylesheet" href="<c:url value="/updateUser.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<a href="/admins">Home</a>
<div class="container">
    <form:form modelAttribute="user" action="/user/add/"><br>
        <label for="userFullName">User Full Name</label>
        <form:input cssClass="userInput" id="userFullName" path="userFullName" placeholder="FullName" required="true"/> <br/>
        <label for="userPassword">User Password</label>
        <form:input cssClass="userInput" id="userPassword" path="userPassword" placeholder="Password" required="true"/><br/>
        <h3>User Role</h3>
        <select class="userInput" name="userRole" id="userRole">
            <option value="Admin">Admin</option>
            <option value="Teacher">Teacher</option>
            <option value="Student">Student</option>
        </select>
        <label for="userEmail">User Email</label>
        <form:input cssClass="userInput" id="userEmail" path="userEmail" placeholder="example@mail.com" required="true"/><br/>
        <form:errors cssClass="userInput" path="userEmail"/>
        <input class="button" type="submit" value="Add New User" name="submit"/>
    </form:form>
</div>
</body>
</html>
