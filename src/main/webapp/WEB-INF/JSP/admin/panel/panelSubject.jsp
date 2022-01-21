<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Subject Panel</title>
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
<a id="subjectTeachBy" href="/subject/teachBy">Subjects Teach By</a>
<div class="package">
    <a id="add" href="/subject/add">Add new Subject</a>
    <div class="container">
        <div class="pack">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Subject Name</th>
                    <th>Subject Credit</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="subject" items="${subjects}">
                    <tr>
                        <td>${subject.id}</td>
                        <td>${subject.subjectName}</td>
                        <td>${subject.subjectCredit}</td>

                        <td><a href="/subject/edit/${subject.id}" class="replay">Update</a></td>
                            <%--                    <a href="/teacher/delete/${teacher.id}" class="replay">Delete</a>--%>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
