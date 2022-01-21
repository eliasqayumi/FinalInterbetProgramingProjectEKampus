<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Student" %>
<%@ page import="com.example.model.Subject" %>
<%@ page import="com.example.model.LessonTaken" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Lesson Takes By Panel</title>
    <link rel="stylesheet" href="<c:url value="/panelProgram.css"/>">
</head>
<body>
<a href="/admins">Home</a>
<script src="deleteFunction.js"></script>
<header>
    <div class="navbar">
        <a href="/admins">
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
                    <th>Subject Name</th>
                    <th>Student StudentID</th>
                    <th>Student Name</th>
                    <th>Student Department</th>
                    <th>Start Date</th>
                    <th>Finish Date</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="lessonTaken" items="${lessonTakes}">
                    <tr>
                        <c:forEach var="subject" items="${subjects}">
                            <c:if test="${subject.id==lessonTaken.subject.id}">
                                <td>${subject.subjectName}</td>
                            </c:if>
                        </c:forEach>
                        <c:forEach var="student" items="${students}">
                            <c:if test="${student.studentId==lessonTaken.student.studentId}">
                                <td>${student.studentId}</td>
                                <td>${student.studentName}</td>
                                <c:forEach var="studentInfo" items="${studentInfos}">
                                    <c:if test="${student.studentId==studentInfo.id}">
                                        <c:forEach var="department" items="${departments}">
                                            <c:if test="${department.id==studentInfo.department.id}">
                                                <td>${department.departmentName}</td>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                        <td>${lessonTaken.startDate}</td>
                        <td>${lessonTaken.finishDate}</td>
                        <td><a id="update" href="/programs/edit/${lessonTaken.id}" class="replay">Update</a>
                            <a id="delete" href="/programs/delete/${lessonTaken.id}" class="replay">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <a id="add" href="/programs/add">Add new Lesson Takes</a>
        </div>
    </div>
</div>

</body>
</html>
