<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Student</title>
    <link rel="stylesheet" href="<c:url value="/student.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <form:form modelAttribute="student" action="/student/add"><br>
        <label for="studentName">Student Name</label>
        <form:input cssClass="studentInput" path="studentName" placeholder="FirstName" required="true"/> <br/>
        <form:errors path="studentName"/>
        <label for="studentSurname">Student Surname</label>
        <form:input cssClass="studentInput" path="studentSurname" placeholder="Surname" required="true"/><br/>
        <form:errors path="studentSurname"/>
        <label for="studentTcNo">Student T.C No</label>
        <form:input cssClass="studentInput" path="studentTcNo" placeholder="T.C.No" required="true"/><br/>
        <form:errors path="studentTcNo"/>
        <label for="studentPhoneNo">Student Contact No</label>
        <form:input cssClass="studentInput" path="studentPhoneNo" placeholder="Contact" required="true"/> <br/>
        <form:errors path="studentPhoneNo"/>
        <label for="studentEmail">Student Email</label>
        <form:input cssClass="studentInput" path="studentEmail" placeholder="example@mail.com" required="true"/><br/>
        <form:errors path="studentEmail"/>
        <label for="studentAddress">Student Address</label>
        <form:input cssClass="studentInput" path="studentAddress" placeholder="Address" required="true"/> <br/>
        <form:errors path="studentEmail"/><br>
        <label for="studentImageURL">Student Image</label>
        <form:input cssClass="studentInput" path="studentImageURL" placeholder="Image" required="true"/><br/>
        <form:errors path="studentImageURL"/>
        <label for="enrolDate">Enrol Date</label>
        <input class="datePicker" type="date" name="enrolDate" id="enrolDate" required><br>
        <h3>Select department</h3>
        <select name="department" id="department">
            <c:forEach var="department" items="${departments}">
                <option value="${department.id}">${department.departmentName}</option>
            </c:forEach>
        </select>
        <input class="button" type="submit" value="Add New Student" name="submit"/>
    </form:form>
</div>
</body>
</html>
