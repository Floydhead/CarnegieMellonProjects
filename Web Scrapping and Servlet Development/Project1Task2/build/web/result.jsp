<%-- 
    Document   : result
    Created on : Sep 17, 2018, 7:08:00 PM
    Author     : Sankalp Devasthali
    
    Creates a result page asking a person to input city and years.
    attributes illegalRequest and illegalExpression are used for exception handling
    based on the code in Model and Servlet
--%>

<%@page import="java.time.YearMonth"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Map Result</title>
    </head>
    <body>
        <% if(request.getAttribute("illegalRequest").equals("True")){ %>
            The request cannot be completed because of incorrect URL or server down.
        <%} else if(request.getAttribute("illegalExpression").equals("True")){%>
            Your request for <%=request.getAttribute("searchedCity")%> for years <%=request.getAttribute("startingYear")%> and <%=request.getAttribute("endingYear")%> returned no results. 
        <% } else { %>
        Image Title is - <%=request.getAttribute("outputTitle")%> <br>
        Print Year - <%=request.getAttribute("outputYear")%><br><br>
    <img src = "<%=request.getAttribute("outputImage")%>" width="800">    
        <% } %>
    <br><br><br><br><br>
        
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
