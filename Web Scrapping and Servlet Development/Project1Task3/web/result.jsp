<%-- 
    Document   : result
    Created on : Sep 20, 2018, 10:14:16 AM
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
        <% if (request.getAttribute("submittedAnswer") != "Select Valid Answer") { %>
            Your <%=request.getAttribute("submittedAnswer")%> response has been registered <br>
        <% } else { %>
            <%=request.getAttribute("submittedAnswer")%>
            <% } %><br>
        Submit your answer to the next question:<br>
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
