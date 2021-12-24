<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Score</title>
    <link rel="stylesheet" href="<c:url value="/panelExa.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>

<body>
<header>
    <div class="navbar">
        <a href="/students">
            <i class="fas fa-university"></i>
        </a>
        <a id="logout" href="/">Log out</a>
    </div>
</header>
<div class="package">
    <div class="container">
        <h1>Exams </h1>
        <div class="pack">
            <table>
                <tr>
                    <th>Subject Name</th>
                    <th>Exam Date</th>
                    <th>Exam term</th>
                    <th>Score</th>
                </tr>
                <c:forEach var="lessonTakes" items="${lessonTakes}">
                    <tr>
                        <td>${lessonTakes.subject.subjectName}</td>
                        <c:forEach var="scoreTable" items="${scoreTables}">
                            <c:if test="${scoreTable.id.subjectId==lessonTakes.subject.id && scoreTable.id.studentId==lessonTakes.student.studentId}">
                                <td>${scoreTable.id.examDate}</td>
                                <td>${scoreTable.id.examName}</td>
                                <td>${scoreTable.marks}</td>
                            </c:if>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
