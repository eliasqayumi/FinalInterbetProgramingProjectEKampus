<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Subject</title>
    <link rel="stylesheet" href="<c:url value="/subject.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <form:form modelAttribute="subject" action="/subject/add"><br>
        <label for="subjectName"> Subject Name</label>
        <form:input cssClass="answersInput" path="subjectName" placeholder="Subject Name" required="true"/> <br/>
        <form:errors path="subjectName"/>
        <label for="subjectCredit">Subject Credit</label>
        <form:input cssClass="answersInput" path="subjectCredit" placeholder="Subject Credit" required="true"/><br/>
        <form:errors path="subjectCredit"/>
        <input class="button" type="submit" value="Add Subject" name="submit"/>
    </form:form>
</div>
</body>
</html>
