<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Department</title>
    <link rel="stylesheet" href="<c:url value="/updateDepartment.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<a href="/admins">Home</a>
<div class="container">
    <form:form modelAttribute="department" action="/department/update/${department.id}"><br>
        <label for="departmentName">Department Name</label>
        <form:input cssClass="addInput"  path="departmentName" placeholder="department Name" required="true"/> <br/>
        <input class="button" type="submit" value="Update Department" name="submit"/>
    </form:form>
</div>
</body>
</html>
