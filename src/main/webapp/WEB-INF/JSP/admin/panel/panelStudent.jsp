<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Panel</title>
    <link rel="stylesheet" href="<c:url value="/panelStudent.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
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
    <a id="add" href="/student/add">Add new Student</a>
    <div class="container">
        <div class="pack">
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>T.C.No</th>
                <th>Email</th>
                <th>Contact</th>
                <th>Address</th>
                <th>Image</th>
                <th>Department</th>
                <th>Enrol Date</th>
                <th>Action</th>
            </tr>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.studentId}</td>
                    <td>${student.studentName}</td>
                    <td>${student.studentSurname}</td>
                    <td>${student.studentTcNo}</td>
                    <td>${student.studentEmail}</td>
                    <td>${student.studentPhoneNo}</td>
                    <td>${student.studentAddress}</td>
                    <td>${student.studentImageURL}</td>
                    <c:forEach var="studentInfo" items="${studentInfos}">
                        <c:if test="${student.studentId==studentInfo.id}">
                            <td>${studentInfo.department.departmentName}</td>
                            <td>${studentInfo.enrolDate}</td>
                        </c:if>
                    </c:forEach>
                    <td><a href="/student/edit/${student.studentId}" class="replay">Update</a></td>
                        <%--                    <a href="/teacher/delete/${teacher.id}" class="replay">Delete</a>--%>
                </tr>
            </c:forEach>
        </table>
    </div>
    </div>
</div>

</body>
</html>
