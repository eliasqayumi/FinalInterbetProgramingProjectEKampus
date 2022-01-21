<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Subject Panel</title>
        <link rel="stylesheet" href="<c:url value="/subjectsPanel.css"/>">
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
                    <th>Subject Name</th>
                    <th>Subject Credit</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="subject" items="${subjects}">
                    <tr>
                        <td>${subject.id}</td>
                        <td>${subject.subjectName}</td>
                        <td>${subject.subjectCredit}</td>

                        <td><a id="update" href="/subject/edit/${subject.id}" class="replay">Update</a>
                            <a id="delete" href="/subject/delete/${subject.id}" class="replay">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <a id="add" href="/subject/add">Add new Subject</a><br>
            <a id="subjectTeachBy" href="/subject/teachBy">Subjects Teach By</a>

        </div>
    </div>
</div>

</body>
</html>
