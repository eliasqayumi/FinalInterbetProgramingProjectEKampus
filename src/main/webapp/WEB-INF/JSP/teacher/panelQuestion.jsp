<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions</title>
    <link rel="stylesheet" href="<c:url value="/allQuestion.css"/>">
</head>
<body>
<div class="header">
    <a href="/question/add/${id}" class="addButton">Add new Question </a>
</div>
<div class="container">
    <div class="pack">
        <table class="bTable">
            <tr>
                <th>Question ID</th>
                <th>Question</th>
                <th>Term</th>
                <th>Subject Name</th>
                <th>Action</th>
                <th></th>
            </tr>
            <c:forEach var="question" items="${questions}">
                <tr class="rTable">
                    <td>
                            ${question.id}
                    </td>
                    <td>
                        <span>${question.question}</span>
                        <table>
                            <tr>
                                <th>Answer ID</th>
                                <th>Answers</th>
                                <th>Answer Status</th>
                            </tr>
                            <c:forEach var="answer" items="${answersList}">
                                <c:if test="${answer.question.id==question.id}">
                                    <tr>
                                        <td> ${answer.id}</td>
                                        <td>${answer.answer}</td>
                                        <td> ${answer.status}</td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </table>
                    </td>
                    <td>${question.term}</td>
                    <td>${question.subject.subjectName}</td>
                    <td class="buttons">
                        <a class="updateButton" href="/question/edit/${question.id}" class="replay">Update</a>
                        <a class="updateButton" href="/question/delete/${question.id}" class="replay">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
