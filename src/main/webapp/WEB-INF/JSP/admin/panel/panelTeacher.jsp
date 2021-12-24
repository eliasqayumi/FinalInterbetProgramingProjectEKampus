<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Teacher Panel</title>
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
        <a href="/admins">
            <i class="fas fa-university"></i>
        </a>
        <a id="logout" href="/">Log out</a>
    </div>
</header>
<div class="package">
    <a id="add" href="/teacher/add">Add new Teacher</a>
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
                        <td><a href="/teacher/edit/${teacher.id}" class="replay">Update</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
