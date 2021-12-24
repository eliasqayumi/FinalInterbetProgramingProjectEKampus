<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Question With Answer</title>
    <link rel="stylesheet" href="<c:url value="/questions.css"/>">

</head>
<body>
<div class="contain">
    <div class="question">
        <h2>Add new Question</h2>
        <h2>Subject ${subject.subjectName}</h2>
        <hr>
        <form action="/question/add/${subject.id}" method="post">
            <label for="question">Question</label><br>
            <textarea name="question" id="question" rows="8" cols="80"></textarea>
            <hr>
            <h2>Answer</h2><br>

            <div class="answers">
                <div class="radioBttn">
                    <h3>Term</h3>
                    <div class="term">
                        <input type="radio" id="midterm" name="term" checked value="Midterm">
                        <label for="midterm">Midterm</label>
                        <input type="radio" id="final" name="term" value="Final">
                        <label for="final">Final</label>
                    </div>
                </div>
                <hr>
                <label for="answer1">Optional Answer 1</label><br>
                <textarea name="answer1" id="answer1" rows="8" required cols="80"></textarea><br>
                <label for="answer2">Optional Answer 2</label><br>
                <textarea name="answer2" id="answer2" rows="8" required cols="80"></textarea><br>
                <label for="answer3">Optional Answer 3</label><br>
                <textarea name="answer3" id="answer3" rows="8" required cols="80"></textarea><br>
                <label for="answer4">True Answer 4</label><br>
                <textarea name="answer4" id="answer4" rows="8" required cols="80"></textarea><br>
                <input type="submit" name="submit" value="Add New Question" class="addQuestionBttn">
            </div>
        </form>
    </div>
</div>

</body>
</html>
