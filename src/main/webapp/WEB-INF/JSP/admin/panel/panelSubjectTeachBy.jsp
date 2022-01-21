<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Student" %>
<%@ page import="com.example.model.Subject" %>
<%@ page import="com.example.model.LessonTaken" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Subject Teach By</title>
    <link rel="stylesheet" href="<c:url value="/viewAll.css"/>">

</head>
<body>
<header>
    <div class="navbar">
        <a href="/admins">
            <i class="fas fa-university"></i>
        </a>
        <a id="logout" href="/">Log out</a>
    </div>
</header>
<div class="package">
    <a id="add" href="/subject/teachBy/add">Add new Subject Teach By</a>
    <div class="container">
        <div class="pack">
            <table>
                <tr>
                    <th>Subject Name</th>
                    <th>Teacher ID</th>
                    <th>Teacher Name</th>
                    <th>Teacher Department</th>
                    <th>Start Date</th>
                    <th>Finish Date</th>
                    <%--                    <th>Action</th>--%>
                </tr>
                <c:forEach var="subjectTeach" items="${subjectTeaches}">
                    <tr>
                        <c:forEach var="subject" items="${subjects}">
                            <c:if test="${subject.id==subjectTeach.id.subjectId}">
                                <td>${subject.subjectName}</td>
                            </c:if>
                        </c:forEach>

                        <c:forEach var="teacher" items="${teachers}">
                            <c:if test="${teacher.id==subjectTeach.id.teacherId}">
                                <td>${teacher.id}</td>
                                <td>${teacher.teacherName}</td>
                                <c:forEach var="teacherInfo" items="${teacherInfos}">
                                    <c:if test="${teacher.id==teacherInfo.id}">
                                        <c:forEach var="department" items="${departments}">
                                            <c:if test="${department.id==teacherInfo.department.id}">
                                                <td>${department.departmentName}</td>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                        <td>${subjectTeach.id.startDate}</td>
                        <td>${subjectTeach.id.finishDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
