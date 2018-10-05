/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P1T1;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Sankalp Devasthali
 */
@WebServlet(name = "ComputeHashes",
        urlPatterns = {"/getComputeHashes"})
public class ComputeHashes extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        stringUserText gets the forms text input data and is converted to a byte array representation.
        This byte array is then fed to the appropriate hashing method and
        a Hex and 64 bit Binary representation is found and sent back to result jsp.
        */
        
        String ua = request.getHeader("User-Agent");

        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone. 
             * Credits: Distributed Systems Coursework at Heinz College, CMU
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        
        String stringUserText = request.getParameter("userText");
        byte[] byteUserText = stringUserText.getBytes("UTF-8");
        String hasher = request.getParameter("hashing");
        String hex, bin64;
        String nextView;
        
        switch (hasher) {
            case "MD5":
                try {
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    md5.update(byteUserText);
                    byte[] ref = md5.digest();
                    hex = DatatypeConverter.printHexBinary(ref);
                    bin64 = DatatypeConverter.printBase64Binary(ref);
                    request.setAttribute("outputHexText", hex);
                    request.setAttribute("outputbin64Text", bin64);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
                    nextView = "index.jsp";
                    RequestDispatcher view = request.getRequestDispatcher(nextView);
                    view.forward(request, response);
                }   break;
            case "SHA-256":
                try {
                    MessageDigest sha = MessageDigest.getInstance("SHA-256");
                    sha.update(byteUserText);
                    byte[] ref = sha.digest();
                    hex = DatatypeConverter.printHexBinary(ref);
                    bin64 = DatatypeConverter.printBase64Binary(ref);
                    request.setAttribute("outputHexText", hex);
                    request.setAttribute("outputbin64Text", bin64);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
                    nextView = "index.jsp";
                    RequestDispatcher view = request.getRequestDispatcher(nextView);
                    view.forward(request, response);
                }   break;
            default:
                hex = "None Parsed";
                bin64 = "None Parsed";
                request.setAttribute("outputHexText", hex);
                request.setAttribute("outputbin64Text", bin64);
                break;
        }
        request.setAttribute("inputText",stringUserText);
        request.setAttribute("inputHash",hasher);
        nextView = "result.jsp";
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
}
