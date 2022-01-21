<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Panel</title>
    <link rel="stylesheet" href="<c:url value="/students.css"/>">
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
                        <td><a id="update" href="/student/edit/${student.studentId}" class="replay">Update</a>
                            <a id="delete" href="/student/delete/${student.studentId}" class="replay">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <a id="add" href="/student/add">Add new Student</a>
        </div>
    </div>
</div>

</body>
</html>
