<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Subjects</title>
    <link rel="stylesheet" href="<c:url value="/viewPanel.css"/>">
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
        <div class="pack">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Subject Name</th>
                    <th>Subject Credit</th>
                    <th>Exams</th>
                    <th>Questions</th>
                </tr>
                <c:forEach var="subjectTeach" items="${subjectTeaches}">
                    <tr>
                        <c:forEach var="subject" items="${subjects}">
                            <c:if test="${subjectTeach.id.subjectId==subject.id}">
                                <td>${subject.id}</td>
                                <td>${subject.subjectName}</td>
                                <td>${subject.subjectCredit}</td>
                                <td><a href="/teacher/exam/${subject.id}">Exam</a>
                                </td>
                                <td><a href="/question/${subject.id}">Question</a>
                                </td>
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
