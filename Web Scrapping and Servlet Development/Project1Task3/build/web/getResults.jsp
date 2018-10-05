<%-- 
    Document   : getResults
    Created on : Sep 20, 2018, 10:17:10 AM
    Author     : Sankalp Devasthali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Survey Results</title>
    </head>
    <body>
        <h1>Distributed Systems Class Clicker</h1>
        <%--
            If there are no values stored in the backend, display no results
            else
            display survey results as they exist
        --%>
        <% if(request.getAttribute("outputD") == null && request.getAttribute("outputC") == null && request.getAttribute("outputB") == null && request.getAttribute("outputA") == null){ %>
            There are currently no results.
        <%} else { %>
        The results from the survey are as follows:
        <br>
        <% if(request.getAttribute("outputA") != null){ %>
            A = <%= request.getAttribute("outputA")%><br>
        <%}%>
        
        <% if(request.getAttribute("outputB") != null){ %>
            B = <%= request.getAttribute("outputB")%><br>
        <%}%>
        
        <% if(request.getAttribute("outputC") != null){ %>
            C = <%= request.getAttribute("outputC")%><br>
        <%}%>
        
        <% if(request.getAttribute("outputD") != null){ %>
            D = <%= request.getAttribute("outputD")%><br>
        <%}%>
        
        The results have now been cleared.
        <%}%>
    </body>
</html>
