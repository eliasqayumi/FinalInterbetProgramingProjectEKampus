<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
    <link rel="stylesheet" href="<c:url value="/update.css"/>">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<div class="container">

    <form:form modelAttribute="user" action="/user/${user.id}"><br>
        <form:input cssClass="userInput" path="id" placeholder="user ID" required="true"/> <br/>
        <form:errors path="id"/>
        <form:input cssClass="userInput" path="userFullName" placeholder="FirstName" required="true"/> <br/>
        <form:errors path="userFullName"/>
        <form:input cssClass="userInput" path="userPassword" placeholder="Surname" required="true"/><br/>
        <form:errors path="userPassword"/>
        <h3>User Role</h3>
        <select name="userRole" id="userRole">
            <option value="Admin" <c:if test="${user.userRole.equalsIgnoreCase(admin)}">${"selected"}</c:if>>Admin</option>
            <option value="Teacher" <c:if test="${user.userRole.equalsIgnoreCase(teacher)}">${"selected"}</c:if>>Teacher</option>
            <option value="Student" <c:if test="${user.userRole.equalsIgnoreCase(student)}">${"selected"}</c:if>>Student</option>
        </select>
        <form:input cssClass="userInput" path="userEmail" placeholder="example@mail.com" required="true"/><br/>
        <form:errors cssClass="userInput" path="userEmail"/>
        <input class="button" type="submit" value="Submit" name="submit"/>
    </form:form>
</div>
</body>
</html>
