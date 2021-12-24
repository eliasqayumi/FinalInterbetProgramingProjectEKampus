<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Exams</title>
    <link rel="stylesheet" href="<c:url value="/style.css"/>">
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
<div class="container">
    <h1>Exams </h1>
    <table class="examTable">
        <tr>
            <th>Subject Name</th>
            <th>Exam Date</th>
            <th>Exam Duration</th>
            <th>Exam term</th>
            <th></th>
        </tr>
        <c:forEach var="exam" items="${exams}">
            <tr>
                <c:forEach var="subject" items="${subjects}">
                    <c:if test="${exam.subject.id==subject.id}">
                        <td>${subject.subjectName}</td>
                    </c:if>
                </c:forEach>
                <td>${exam.examDateTime}</td>
                <td>${exam.duration}</td>
                <td>${exam.examTerm}</td>
                <td>
                    <c:forEach var="examButton" items="${examButtons}">
                        <c:if test="${examButton.key== exam.subject.id}">
                            <form action="/exam/${exam.subject.id}" method="post">
                                <input class="startButton"

                                <c:forEach var="color" items="${buttonColor}">
                                <c:if test="${color.key==exam.subject.id}">
                                       style="background-color: ${color.value}"
                                </c:if>
                                </c:forEach>
                                       type="submit" ${examButton.value}
                                <c:forEach var="val" items="${buttonValue}">
                                <c:if test="${val.key==exam.subject.id}">
                                       value="${val.value}"

                                </c:if>
                                </c:forEach>
                                >
                            </form>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
