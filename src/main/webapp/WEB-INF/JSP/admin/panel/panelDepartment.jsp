<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Department Panel</title>
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
    <a id="add" href="/department/add">Add new Department</a>
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
                        <td><a href="/department/edit/${department.id}" class="replay">Update</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
