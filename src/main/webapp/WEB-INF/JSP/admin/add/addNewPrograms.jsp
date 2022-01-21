<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New LessonTaken</title>
    <link rel="stylesheet" href="<c:url value="/programi.css"/> ">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<a href="/admins">Home</a>
<div class="container">
    <form  action="/lessonTaken/add"><br>
        <h3>Select Subject</h3>
        <select name="subject" class="lessonTakers" id="subject">
            <c:forEach var="subject" items="${subjects}">
                <option value="${subject.id}">${subject.subjectName}</option>
            </c:forEach>
        </select><br>
        <h3>Select Student</h3>
        <select name="student" class="lessonTakers" id="student">
            <c:forEach var="student" items="${students}">
                <option value="${student.studentId}">${student.studentName}</option>
            </c:forEach>
        </select><br>
        <label for="startDate">Start Date</label>
        <input class="lessonTakers" type="date" name="startDate" id="startDate" required><br>
        <label for="finishDate">finish Date</label>
        <input class="lessonTakers" type="date" name="finishDate" id="finishDate" required><br>
        <input class="button" type="submit" value="Submit" name="submit"/>
    </form>
</div>
</body>
</html>
