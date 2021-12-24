<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Exam</title>
    <link rel="stylesheet" href="<c:url value="/exam.css"/>">
</head>
<body>
<div class="contain">
    <div class="exams">
        <h1>Exam</h1>
        <h3>${midterm}</h3>
        <form action="/answer/submit/${id}" method="post">
            <ol>
                <c:forEach var="question" items="${questions}">
                    <div class="questionAnswer">
                        <li><input type="hidden" name="question" value="${question.id}">${question.question}</li>
                        <ol class="examAnswer">
                            <c:forEach var="answer" items="${answers}">
                                <c:if test="${question==answer.question}">
                                    <li class="answerList">
                                        <input type="radio" name="${question.id}" value="${answer.id}"
                                        <c:forEach var="answerKey" items="${answerKeys}">
                                        <c:if test="${answer.id==answerKey}">
                                                ${"checked"}
                                        </c:if>
                                        </c:forEach>
                                               id="answer">${answer.answer}</li>
                                    <hr class="answerSprater"/>
                                </c:if>
                            </c:forEach>
                        </ol>
                        <br/>
                    </div>
                </c:forEach>
            </ol>
            <input type="submit" id="submit" name="submit" value="Submit"/>
        </form>
    </div>
</div>
</body>
</html>
