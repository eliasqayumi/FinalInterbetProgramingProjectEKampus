<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Teacher</title>
    <link rel="stylesheet" href="<c:url value="/updateTeacher.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<a href="/admins">Home</a>
<div class="container">
    <form:form modelAttribute="teacher" action="/teacher/update/${teacher.id}"><br>
        <label for="teacherName">First Name</label>
        <form:input cssClass="teacherInput" path="teacherName" placeholder="FirstName" required="true"/> <br/>
        <label for="teacherSurname">Surname</label>
        <form:input cssClass="teacherInput" path="teacherSurname" placeholder="Surname" required="true"/><br/>
        <label for="teacherTcno">Tc.No</label>
        <form:input cssClass="teacherInput" path="teacherTcno" placeholder = "T.C.No"  required="true" /><br/>
        <label for="teacherPhone">Contact</label>
        <form:input cssClass="teacherInput" path="teacherPhone" placeholder="Contact" required="true"/> <br/>
        <label for="teacherEmail">Email</label>
        <form:input cssClass="teacherInput"  path="teacherEmail" placeholder="example@mail.com" required="true"/><br/>
        <label for="teacherAddress">Address</label>
        <form:input cssClass="teacherInput" path="teacherAddress" placeholder="Address" required="true"/> <br/>
        <label for="enrolDate">Enrol Date</label>
        <input class="teacherInput" type="date" name="enrolDate" value="${teacherInfo.inroleDate}" id="enrolDate" required><br>
        <label for="department">Select Department</label>
        <select class="teacherInput" name="department" id="department">
            <c:forEach var="department" items="${departments}">
            <option value="${department.id}" <c:if test="${teacherInfo.department.id==department.id}">${"selected"}</c:if>
                >${department.departmentName}</option>
            </c:forEach>
        </select><br>
        <input class="button" type="submit" value="Update" name="submit"/>
    </form:form>
</div>
</body>
</html>
