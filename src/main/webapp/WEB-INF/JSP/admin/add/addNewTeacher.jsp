<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Teacher</title>
    <link rel="stylesheet" href="<c:url value="/teacher.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <form:form modelAttribute="teacher" action="/teacher/add"><br>
        <form:input cssClass="teacherInput" path="teacherName" placeholder="FirstName" required="true"/> <br/>
        <form:errors path="teacherName"/>
        <form:input cssClass="teacherInput" path="teacherSurname" placeholder="Surname" required="true"/><br/>
        <form:errors path="teacherSurname"/>
        <form:input cssClass="teacherInput" path="teacherTcno" placeholder = "T.C.No"  required="true" /><br/>
        <form:errors  path="teacherTcno"/>
        <form:input cssClass="teacherInput" path="teacherPhone" placeholder="Contact" required="true"/> <br/>
        <form:errors path="teacherPhone"/>
        <form:input cssClass="teacherInput" path="teacherEmail" placeholder="example@mail.com" required="true"/><br/>
        <form:errors path="teacherEmail"/>
        <form:input cssClass="teacherInput" path="teacherAddress" placeholder="Address" required="true"/> <br/>
        <form:errors  path="teacherAddress"/>
        <label for="enrolDate">Enrol Date</label>
        <input class="datePicker" type="date" name="enrolDate" id="enrolDate" required><br>
        <h3>Select department</h3>
        <select name="department" id="department">
            <c:forEach var="department" items="${departments}">
                <option value="${department.id}">${department.departmentName}</option>
            </c:forEach>
        </select><br>
        <input class="button" type="submit" value="Add New Teacher" name="submit"/>
    </form:form>
</div>
</body>
</html>
