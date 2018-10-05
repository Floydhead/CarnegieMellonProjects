<%-- 
    Document   : index
    Created on : Sep 13, 2018, 7:52:41 PM
    Author     : Sankalp Devasthali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Starting Page</title>
    </head>
    <body>
        <%--
        This is the landing page which takes input string and 
        makes the user select the type of hashing.
        The servlet used is "getComputeHashes"
        --%>
        <p>Enter a String of Text Data and Hashing Function: </p>
        <form action="getComputeHashes" method="GET">
            <label for="letter">Type the String.</label>
            <input type="text" name="userText" required /><br>
            <label for="letter">Select the Hash.</label><br>
            <label for="letter">MD5</label>
            <input type="radio" name="hashing" value="MD5" required/>
            <label for="letter">SHA-256</label>
            <input type="radio" name="hashing" value="SHA-256" /><br>
            <input type="submit" value="Click Here" />
        </form>
    </body>
</html>
