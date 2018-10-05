/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P1T3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sankalp Devasthali
 * Servlet implements doGet method
 */
@WebServlet(name = "Project1Task3Servlet", urlPatterns = {"/getSubmitAnswers","/getResults"})
public class Project1Task3Servlet extends HttpServlet {

Project1Task3Model p1t3m = null;
    @Override
    public void init() {
        p1t3m = new Project1Task3Model();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nextView;
        String answer = request.getParameter("questionnaire");      //gets selection from jsp
        if(answer != null){     //if answer is legitimate
            request.setAttribute("submittedAnswer", answer);
            p1t3m.storeAnswers(answer);
            nextView = "result.jsp";
        }
        else{                   //if answer is not selected/legitimate
            request.setAttribute("submittedAnswer", "Select Valid Answer");
            nextView = "result.jsp";
        }
        if(request.getServletPath().equals("/getResults")){     //checks whether getResults.jsp is being called
            if(p1t3m.retrieveAnswers("A") > 0){                 //as long as there are values > 0 
                request.setAttribute("outputA", p1t3m.retrieveAnswers("A"));
            }
            if(p1t3m.retrieveAnswers("B") > 0){
                request.setAttribute("outputB", p1t3m.retrieveAnswers("B"));
            }
            if(p1t3m.retrieveAnswers("C") > 0){
                request.setAttribute("outputC", p1t3m.retrieveAnswers("C"));
            }
            if(p1t3m.retrieveAnswers("D") > 0){
                request.setAttribute("outputD", p1t3m.retrieveAnswers("D"));
            }
            nextView = "getResults.jsp";
            p1t3m.deleteAnswers();      //resets all values to 0
        }
    
            String ua = request.getHeader("User-Agent");

    boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
    if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
    } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
    }
    
    RequestDispatcher view = request.getRequestDispatcher(nextView);
    view.forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
