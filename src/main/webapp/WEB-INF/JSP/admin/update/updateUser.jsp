<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
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
    <form:form modelAttribute="user" action="/user/update/${user.id}"><br>
        <label for="id">Id</label>
        <form:input cssClass="userInput" path="id" placeholder="user ID" required="true"/> <br/>
        <label for="userFullName">FullName</label>
        <form:input cssClass="userInput" path="userFullName" placeholder="FirstName" required="true"/> <br/>
        <label for="userPassword">Password</label>
        <form:input cssClass="userInput" path="userPassword" placeholder="Surname" required="true"/><br/>
        <label for="userRole">Role</label>
        <select class="userInput" name="userRole" id="userRole">
            <option value="Admin" <c:if test="${user.userRole.equalsIgnoreCase(admin)}">${"selected"}</c:if>>Admin
            </option>
            <option value="Teacher" <c:if test="${user.userRole.equalsIgnoreCase(teacher)}">${"selected"}</c:if>>
                Teacher
            </option>
            <option value="Student" <c:if test="${user.userRole.equalsIgnoreCase(student)}">${"selected"}</c:if>>
                Student
            </option>
        </select>
        <label for="userEmail">Email</label>
        <form:input cssClass="userInput" path="userEmail" placeholder="example@mail.com" required="true"/><br/>
        <input class="button" type="submit" value="Submit" name="submit"/>
    </form:form>
</div>
</body>
</html>
