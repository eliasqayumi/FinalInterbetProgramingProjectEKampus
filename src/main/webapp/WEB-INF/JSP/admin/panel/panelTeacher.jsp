<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Teacher Panel</title>
    <link rel="stylesheet" href="<c:url value="/teachers.css"/>">
</head>
<body>
<a href="/admins">Home</a>
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
                    <th>ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>T.C.No</th>
                    <th>Email</th>
                    <th>Contact</th>
                    <th>Address</th>
                    <th>Department</th>
                    <th>Enrole date</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="teacher" items="${allTeachers}">
                    <tr>
                        <td>${teacher.id}</td>
                        <td>${teacher.teacherName}</td>
                        <td>${teacher.teacherSurname}</td>
                        <td>${teacher.teacherTcno}</td>
                        <td>${teacher.teacherEmail}</td>
                        <td>${teacher.teacherPhone}</td>
                        <td>${teacher.teacherAddress}</td>
                        <c:forEach var="studentInfo" items="${teacherInfos}">
                            <c:if test="${teacher.id==studentInfo.id}">
                            <td>${studentInfo.department.departmentName}</td>
                            <td>${studentInfo.inroleDate}</td>
                            </c:if>
                        </c:forEach>
                        <td><a id="update" href="/teacher/edit/${teacher.id}" class="replay">Update</a>
                        <a id="delete" href="/teacher/delete/${teacher.id}" class="replay">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <a id="add" href="/teacher/add">Add new Teacher</a>
        </div>
    </div>
</div>

</body>
</html>
