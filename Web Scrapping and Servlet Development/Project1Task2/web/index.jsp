<%-- 
    Document   : index
    Created on : Sep 17, 2018, 7:07:17 PM
    Author     : Sankalp Devasthali

    Creates a landing page asking a person to input city and years.
--%>

<%@page import="java.time.YearMonth"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search David's Maps</title>
    </head>
    <body>
        <p>Hi, Search for a map of your choice of city with an optional range of start or end years</p>
        <form action="Project1Task2Servlet" method="GET" >
            <label for="letter">Input City</label>
            <input type="text" name="inputCity" required /><br>
            <label for="letter">Start Year</label>
            <input type="number" name="startYear" value=1500 /><br>
            <label for="letter">End Year</label>
            <input type="number" name="endYear" value=<%=YearMonth.now().getYear()%> /><br>
            <input type="submit" value="Click Here" />
        </form>
            <br><br>
            Credits go to <a href="https://www.davidrumsey.com/" title="https://www.davidrumsey.com/">David Rumsey's Maps</a> 
    </body>
</html>