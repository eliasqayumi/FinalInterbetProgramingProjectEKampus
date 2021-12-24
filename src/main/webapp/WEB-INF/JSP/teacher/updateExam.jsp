<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Exam</title>
    <link rel="stylesheet" href="<c:url value="/addExam.css"/> ">
</head>
<body>
<div class="container">
    <h2>${exam.subject.subjectName} Exam</h2>
    <form action="/teacher/exam/update/${exam.id}" method="post"><br>
        <h3>Exam term</h3>
        <div class="term">
            <label for="midterm">Midterm</label>
            <input type="radio" name="term" id="midterm"
            <c:if test="${exam.examTerm.equals(mid)}"> checked
            </c:if> value="midterm" required>
            <label for="final">Final</label>
            <input type="radio" name="term"
            <c:if test="${exam.examTerm.equals(fin)}"> checked
            </c:if> id="final"  value="final" required>
        </div>
        <label for="examDuration">Exam Duration</label>
        <select name="examDuration" id="examDuration">
            <c:forEach var="value" begin="1" end="60">
                <option value="${value}"<c:if test="${exam.duration==value}">${"selected"}</c:if>
                >${value}</option>
            </c:forEach>
        </select><br>
        <label for="examDate">Exam Date</label>
        <input class="datePicker" type="datetime-local" name="examDate" value="${exam.examDateTime}" id="examDate"
               required><br>


        <input class="button" type="submit" value="Update Exam" name="submit"/>
    </form>
</div>
</body>
</html>
