<%-- 
    Document   : result
    Created on : Sep 14, 2018, 6:55:56 PM
    Author     : Sankalp Devasthali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
    </head>
    <body>
        <%--
        This is the result page which shows the Hex and Bin64 hashes of input text 
        and provides an option to search for another text's hash
        --%>
        Hashed "<%=request.getAttribute("inputHash")%>" file representations for "<%=request.getAttribute("inputText")%>" are:<br>
        Hex is "<%=request.getAttribute("outputHexText")%>"<br>
        Bin64 is "<%=request.getAttribute("outputbin64Text")%>"<br><br>
        <p>Enter another String of Text Data and Hashing Function: </p>
        <form action="getComputeHashes" method="GET">
            <label for="letter">Type the String.</label>
            <input type="text" name="userText" required /><br>
            <label for="letter">Select the Hash.</label><br>
            <label for="letter">MD5</label>
            <input type="radio" name="hashing" value="MD5" required />
            <label for="letter">SHA-256</label>
            <input type="radio" name="hashing" value="SHA-256" /><br>
            <input type="submit" value="Click Here" />
        </form>    
    </body>
</html>
