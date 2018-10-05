/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P1T2;

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
 */
@WebServlet(name = "Project1Task2Servlet", urlPatterns = {"/Project1Task2Servlet"}) 
//Provided a url pattern for the jsp to connect with controller/servlet

public class Project1Task2Servlet extends HttpServlet {
    
Project1Task2Model p1t2m = null;
    //initializing the model class
    @Override
    public void init() {
        p1t2m = new Project1Task2Model();
    }

    /*
    implementing the doGet method of HTTPServlet.
    It inputs data from the index jsp form for input city, start year, and end year.
    processes requested data and provides a random image on result jsp with title and year of publishing.
    */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextView;    //placeholder for output page
        
        //gets city, start, and end year data from form.
        String city = request.getParameter("inputCity");
        
        int startYear = 1500;
        int endYear = 2018;
        if(!request.getParameter("startYear").equals("")){
            startYear = Integer.parseInt(request.getParameter("startYear"));
        }
        if(!request.getParameter("endYear").equals("") && Integer.parseInt(request.getParameter("endYear")) > startYear){
            endYear = Integer.parseInt(request.getParameter("endYear"));
        }
        
        //flags for illegal expressions or requests. remain false unless true.
        request.setAttribute("illegalExpression", "False");
        request.setAttribute("illegalRequest", "False");
        
        //Code to handle mobile and desktop devices
        String ua = request.getHeader("User-Agent");
        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone. Credits: Distributed Systems CMU-Heinz
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        
        //return placeholders for output
        String title = null;
        String imagepath = null;
        String year = null;
        String client = null;

        /*
        doMapSearch is an overloaded function with the signature (city,[startYear,endYear], clientType)
        based on the parameters, it searches accordingly. 
        The parameters parsing is done in the if-else statement sequence.
        
        A special case of end year being smaller than start year has been taken care of.
        If the input end year is indeed smaller than start year, function ignores end year and only
        uses start year.
        
        IllegalArgumentExceptions are used to handle requests bearing no results.
        IllegalRequestExceptions are used to handle requests when the internet is down or servers are slow or timeouts.
        */
        
        if(city.length() != 0){     //if city data given
            if(startYear != 0){     //if start year given
                if(endYear != 0 && startYear < endYear){ //if end year is given and is greater than start year
                    try{
                        String[] searchresult = p1t2m.doMapSearch(city, startYear, endYear, mobile); 
                        //function doMapSearch returns String array for required outputs
                        title = searchresult[0];
                        imagepath = searchresult[1];
                        year = searchresult[2];
                        client = searchresult[3];
                    }
                    //catches empty result queries
                    catch(IllegalArgumentException iae){
                        request.setAttribute("illegalExpression", "True");
                        request.setAttribute("searchedCity", city);
                        request.setAttribute("startingYear", startYear);
                        request.setAttribute("endingYear", endYear);
                        nextView = "result.jsp";
                        RequestDispatcher view = request.getRequestDispatcher(nextView);
                        view.forward(request, response);
                    }
                    //catches timeouts, incomplete results, or internet issues and sets output appropriately
                    catch(NullPointerException npe){
                        request.setAttribute("illegalRequest", "True");
                        nextView = "result.jsp";
                        RequestDispatcher view = request.getRequestDispatcher(nextView);
                        view.forward(request, response);
                    }
                    catch(Exception e){
                        request.setAttribute("illegalRequest", "True");
                        request.setAttribute("illegalExpression", "True");
                        nextView = "index.jsp";
                        RequestDispatcher view = request.getRequestDispatcher(nextView);
                        view.forward(request, response);
                    }
                }else{
                    try{
                    String[] searchresult = p1t2m.doMapSearch(city, startYear, mobile);
                    title = searchresult[0];
                    imagepath = searchresult[1];
                    year = searchresult[2];
                    client = searchresult[3];
                    }
                    catch(IllegalArgumentException iae){
                        request.setAttribute("illegalExpression", "True");
                        request.setAttribute("searchedCity", city);
                        request.setAttribute("startingYear", startYear);
                        request.setAttribute("endingYear", endYear);
                        nextView = "result.jsp";
                        RequestDispatcher view = request.getRequestDispatcher(nextView);
                        view.forward(request, response);
                    }
                    catch(NullPointerException npe){
                        request.setAttribute("illegalRequest", "True");
                        nextView = "result.jsp";
                        RequestDispatcher view = request.getRequestDispatcher(nextView);
                        view.forward(request, response);
                    }
                    catch(Exception e){
                        request.setAttribute("illegalRequest", "True");
                        request.setAttribute("illegalExpression", "True");
                        nextView = "index.jsp";
                        RequestDispatcher view = request.getRequestDispatcher(nextView);
                        view.forward(request, response);
                    }
                }
            }
            else{       //only city data given
                try{
                String[] searchresult = p1t2m.doMapSearch(city, mobile);
                title = searchresult[0];
                imagepath = searchresult[1];
                year = searchresult[2];
                client = searchresult[3];
                }
                catch(IllegalArgumentException iae){
                        request.setAttribute("illegalExpression", "True");
                        request.setAttribute("searchedCity", city);
                        request.setAttribute("startingYear", startYear);
                        request.setAttribute("endingYear", endYear);
                        nextView = "result.jsp";
                        RequestDispatcher view = request.getRequestDispatcher(nextView);
                        view.forward(request, response);
                    }
                catch(NullPointerException npe){
                        request.setAttribute("illegalRequest", "True");
                        nextView = "result.jsp";
                        RequestDispatcher view = request.getRequestDispatcher(nextView);
                        view.forward(request, response);
                    }
                catch(Exception e){
                        request.setAttribute("illegalRequest", "True");
                        request.setAttribute("illegalExpression", "True");
                        nextView = "index.jsp";
                        RequestDispatcher view = request.getRequestDispatcher(nextView);
                        view.forward(request, response);
                    }
            }
        }
        else{
            title = "Please enter valid data";
        }
        request.setAttribute("outputTitle", title.substring(title.indexOf("-")+2, title.length()));
        request.setAttribute("outputImage", imagepath);
        request.setAttribute("outputYear", year.substring(year.lastIndexOf("-")+2, year.length()));
        request.setAttribute("Client", client);
        nextView = "result.jsp";        //for the resulting view
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

}