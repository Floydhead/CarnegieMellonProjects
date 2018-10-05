/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P1T2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Sankalp Devasthali
 */
public class Project1Task2Model {
    
    /*
    There are 3 functions implemented here - doMapSearch, randomPick, Fetch.
    1.  doMapSearch takes in city, city-startyear, or city-startyear-endyear, and devicetype to return a search string for HTML
    2.  randomPick function takes the search string and client type and returns a string array containing output data such as
    title, date, image source, client type. Function also scraps a large size image for client of the desktop and non-mobile series.
    3.  Fetch takes a search string and client type and returns an Elements array containing
        output image source based on client type (desktop requires further parsing which is done in randomPick)
        The Elements array are jSoup parses and the correct tag can give us the required image title, image source, and image date.
    */
    public String[] doMapSearch(String city, boolean client) throws UnsupportedEncodingException{
        city = URLEncoder.encode(city, "UTF-8");
        String search = "https://www.davidrumsey.com/luna/servlet/view/search?q=city="
                + city
                + "%20LIMIT:RUMSEY~8~1&sort=pub_list_no_initialsort,pub_date,pub_list_no,series_no&pgs=100";
        String[] res = randomPick(search, client);
        return res;
    }
    
    public String[] doMapSearch(String city, int startYear, boolean client) throws UnsupportedEncodingException{
        city = URLEncoder.encode(city, "UTF-8");
        String search = "https://www.davidrumsey.com/luna/servlet/view/search?q=="
                + city
                + "%20AND%20date="
                + startYear
                + "...%20LIMIT:RUMSEY~8~1&sort=pub_list_no_initialsort,pub_date,pub_list_no,series_no&pgs=100";
        String[] res = randomPick(search, client);
        return res;
    }
    
    public String[] doMapSearch(String city, int startYear, int endYear, boolean client) throws UnsupportedEncodingException{
        city = URLEncoder.encode(city, "UTF-8");
        String search = "https://www.davidrumsey.com/luna/servlet/view/search?q=="
                + city
                + "%20AND%20date="
                + startYear
                + "..."
                + endYear
                + "%20LIMIT:RUMSEY~8~1&sort=pub_list_no_initialsort,pub_date,pub_list_no,series_no&pgs=100";
        String[] res = randomPick(search, client);
        return res;
    }
    
    private String[] randomPick(String search, Boolean client){
        Random r = new Random();
        Elements[] ret = fetch(search, client);
        Elements titles = ret[0];
        Elements images = ret[1];
        Elements years = ret[2];
        int i = r.nextInt(images.size());
        String title = titles.get(i).attr("title");
        String year = years.get(i).attr("title");
        String imageURL = null;
        if(client){     //client is a mobile phone
            imageURL = images.get(i).attr("src");
        }else{
            try {       //client is not a mobile phone
                Elements temp = images.get(i).select("a");  //parses for the "a" tag in the randomly generated "i'th" element
                String innerWebsite = temp.attr("href");    //finds the href tag for the link of the orignal image in thumbnail reference
                Document tempDoc = Jsoup.connect(innerWebsite).get();   //parsing the inner html source for original image
                //parsing the appropriate tag
                Elements imElements = tempDoc.select("div[id=Workspace]");  
                Element imE = imElements.get(0);
                //substringing the original image URL from the parsed string
                imageURL = imE.text().substring(imE.text().indexOf("src")+5, imE.text().indexOf("jpg")+3);
            } catch (Exception ex) {
                Logger.getLogger(Project1Task2Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String[] returnvalue = new String[4];
        returnvalue[0] = title;
        returnvalue[1] = imageURL;
        returnvalue[2] = year;
        returnvalue[3] = String.valueOf(client);
        return returnvalue;
    }
    
    //documentation provided above
    private Elements[] fetch(String searchString, Boolean client){
        //placeholders for image information
        Document doc = null;
        Elements titles = null;
        Elements images = null;
        Elements years = null;
        Elements[] ret = new Elements[3];
        //Parses given search string and creates JSoup elements
        try{
        doc = Jsoup.connect(searchString).get();
        if(client){     //if client is a mobile device
            images = doc.select("img[src]");
        }else{          //if client is not a mobile device
            images = doc.select("div[class=mediaContainer]");
        }       //for title and year
        titles = doc.select("blockquote[class=thumbnailLabel1]");
        years = doc.select("blockquote[class=thumbnailLabel2]");
        }
        catch(Exception e){
            ret[0] = titles;
            ret[1] = images;
            ret[2] = years;
            return ret;
        }
        ret[0] = titles;
        ret[1] = images;
        ret[2] = years;
        
        return ret;
    }
}
