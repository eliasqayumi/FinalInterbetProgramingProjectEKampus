<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Department Panel</title>
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
    <div class="container">
        <div class="pack">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Department Name</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="department" items="${departments}">
                    <tr>
                        <td>${department.id}</td>
                        <td>${department.departmentName}</td>
                        <td><a id="update" href="/department/edit/${department.id}" class="replay">Update</a>
                        <a  id="delete" href="/department/edit/${department.id}" class="replay">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <a id="add" href="/department/add">Add new Department</a>
        </div>
    </div>
</div>

</body>
</html>
