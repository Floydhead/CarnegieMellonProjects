<%-- 
    Document   : index
    Created on : Sep 20, 2018, 9:57:09 AM
    Author     : Sankalp Devasthali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DS Clicker</title>
    </head>
    <body>
        <h1>Distributed Systems Class Clicker</h1>
        
        <p>Submit your answer to the current question:</p>
        <form action="getSubmitAnswers" method="GET">
            <label for="letter">A</label>
            <input type="radio" name="questionnaire" value="A" /><br>
            <label for="letter">B</label>
            <input type="radio" name="questionnaire" value="B" /><br>
            <label for="letter">C</label>
            <input type="radio" name="questionnaire" value="C" /><br>
            <label for="letter">D</label>
            <input type="radio" name="questionnaire" value="D" /><br>
            <input type="submit" value="Click Here" />
        </form> 
        
    </body>
</html>
