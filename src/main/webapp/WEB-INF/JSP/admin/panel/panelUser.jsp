<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Panel</title>
    <link rel="stylesheet" href="<c:url value="/panelUser.css"/>">

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
                    <th>id</th>
                    <th>FullName</th>
                    <th>Password</th>
                    <th>User Role</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userFullName}</td>
                        <td>${user.userPassword}</td>
                        <td>${user.userRole}</td>
                        <td>${user.userEmail}</td>
                        <td><a id="update" href="/user/edit/${user.id}" class="replay">Update</a>
                            <a id="delete" href="/user/delete/${user.id}" class="replay">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <a id="add" href="/user/add">Add new User</a>
        </div>
    </div>
</div>

</body>
</html>
