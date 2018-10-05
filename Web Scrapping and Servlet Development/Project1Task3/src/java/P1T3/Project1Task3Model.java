/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P1T3;

import java.util.HashMap;

/**
 *
 * @author Sankalp Devasthali
 * Class Project1Task3Model has 3 functions - 
 * 1.   storeAnswers - stores the appropriate choice to the appropriate placeholder
 * 2.   retrieveAnswers - retrieves the appropriate choice
 * 3.   deleteAnswers - resets all placeholder values to 0
 * 
 */
public class Project1Task3Model {
    int A,B,C,D = 0;            //Placeholders for all options
    
    public void storeAnswers(String answer){
        if(answer.contains("A")){
            A += 1;
        }
        else if(answer.contains("B")){
            B +=1;
        }
        else if(answer.contains("C")){
            C +=1;
        }
        else{
            D +=1;
        }
    }
    
    public int retrieveAnswers(String answer){
        if(answer.contains("A")){
            return A;
        }
        else if(answer.contains("B")){
            return B;
        }
        else if(answer.contains("C")){
            return C;
        }
        else{
            return D;
        }       
    }
    
    public void deleteAnswers(){
        A=0;
        B=0;
        C=0;
        D=0;
    }
}
