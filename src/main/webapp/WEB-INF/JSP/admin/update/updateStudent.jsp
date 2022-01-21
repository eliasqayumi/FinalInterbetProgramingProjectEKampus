<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
    <link rel="stylesheet" href="<c:url value="/updateStudent.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<a href="/admins">Home</a>

<div class="container">
    <form:form modelAttribute="student" action="/student/update/${student.studentId}"><br>
        <label for="studentName">Student Name</label>
        <form:input cssClass="studentInput" path="studentName" placeholder="FirstName" required="true"/> <br/>
        <label for="studentSurname">Student Surname</label>
        <form:input cssClass="studentInput" path="studentSurname" placeholder="Surname" required="true"/><br/>
        <label for="studentTcNo">Student T.C No</label>
        <form:input cssClass="studentInput" path="studentTcNo" placeholder="T.C.No" required="true"/><br/>
        <label for="studentPhoneNo">Student Contact No</label>
        <form:input cssClass="studentInput" path="studentPhoneNo" placeholder="Contact" required="true"/> <br/>
        <label for="studentEmail">Student Email</label>
        <form:input cssClass="studentInput" path="studentEmail" placeholder="example@mail.com" required="true"/><br/>
        <label for="studentAddress">Student Address</label>
        <form:input cssClass="studentInput" path="studentAddress" placeholder="Address" required="true"/> <br/>
        <label for="enrolDate">Enrol Date</label>
        <input class="studentInput" type="date" name="enrolDate" value="${studentInfo.enrolDate}" id="enrolDate" required><br>
        <label for="department">Department</label>
        <select class="studentInput" name="department" id="department">
            <c:forEach var="department" items="${departments}">
                <option value="${department.id}" <c:if test="${studentInfo.department.id==department.id}">${"selected"}</c:if>
                >${department.departmentName}</option>
            </c:forEach>
        </select><br>
        <input class="button" type="submit" value="Update" name="submit"/>
    </form:form>
</div>
</body>
</html>
