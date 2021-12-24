<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New LessonTaken</title>
    <link rel="stylesheet" href="<c:url value="/addNewSubjectTeachBy.css"/> ">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <form action="/lessonTaken/update/${lessonTaken.id}" method="post">
        <h3>Select Subject</h3>
        <select name="subject" id="subject" class="lessonTakers">
            <c:forEach var="subject" items="${subjects}">
                <option value="${subject.id}" <c:if
                        test="${lessonTaken.subject.id==subject.id}">${"selected"}</c:if> >
                        ${subject.subjectName}
                </option>
            </c:forEach>
        </select><br>

        <h3>Select Student</h3>
        <select name="student" id="student" class="lessonTakers">
            <c:forEach var="student" items="${students}">
                <option value="${student.studentId}"
                        <c:if test="${lessonTaken.student.studentId==student.studentId}">${"selected"}</c:if>
                        <c:if test="${lessonTaken.student.studentId!=student.studentId}">${"disabled"}</c:if>
                >
                        ${student.studentName}
                </option>
            </c:forEach>
        </select><br>

        <label for="startDate">Start Date</label>
        <input class="datePicker" type="date" value="${lessonTaken.startDate}" name="startDate" id="startDate"
               required/><br>
        <label for="finishDate">finish Date</label>
        <input class="datePicker" type="date" value="${lessonTaken.finishDate}" name="finishDate" id="finishDate"
               required/><br>

        <input class="button" type="submit" value="Update" name="submit"/>
    </form>
</div>
</body>
</html>
