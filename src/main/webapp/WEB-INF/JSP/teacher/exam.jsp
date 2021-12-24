<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Exams</title>
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
        <a href="/teachers">
            <i class="fas fa-university"></i>
        </a>
        <a id="logout" href="/">Log out</a>
    </div>
</header>
<div class="package">
    <div class="container">
        <a id="add" href="/teacher/exam/add/${id}">Add new Exam</a>
        <h1>Exams </h1>
        <div class="pack">
            <table>
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
                        <td><a id="update" href="/teacher/exam/edit/${exam.id}">Update</a></td>
                        <td><a id="delete"
                               href="/teacher/exam/delete/${exam.id}">Delete</a></td>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
