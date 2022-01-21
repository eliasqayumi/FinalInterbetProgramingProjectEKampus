
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="l" dir="ltr">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/adminMain.css">
    <title>Admin</title>
</head>
<body>
<header>
    <div class="navbar">
        <a id="logout" href="/">Log out</a>
    </div>
</header>
<div class="body">
    <ul>
        <div class="panel-column">
            <li><a href="/teacher"><i class="fas fa-chalkboard-teacher"></i><span>Teacher</span></a></li>
            <li><a href="/student"><i class='fas fa-user-graduate'></i><span>Students</span></a></li>
            <li><a href="/subject"><i class='fas fa-book-open'></i><span>Subjects</span></a></li>
        </div>
        <div class="panel-column">
            <li><a href="/user"><i class='fas fa-users'></i><span>Users</span></a></li>
            <li><a href="/department"><i class='fas fa-building'></i><span>Departments</span></a></li>
            <li><a href="/programs"><i class='fas fa-users'></i><span>Programs</span></a></li>
        </div>
    </ul>
</div>

</body>
</html>
