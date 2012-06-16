package boolesch;
import java.io.*;
import java.util.*;

/**
 * Describe class InverListStru here.
 *
 *
 * Created: Mon Jun 13 18:36:29 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class InverListStru {

    /**
     * Creates a new <code>InverListStru</code> instance.
     *
     */
    public InverListStru(String wd) {
        word = wd;
    }

    public void setWord(String wd){
        word = wd;
    }
    

    public String getWord(){
        return word;
    }



    public ArrayList<Integer> getDocList(){
        return inverList;
      
    }
    
      


    public void addDoc(int num){
        inverList.add(num);
    }

    public void setAllDoc(ArrayList<Integer> na){
        inverList.clear();
        inverList.addAll(na);
    }
 
    private String word;
    private ArrayList<Integer> inverList = new ArrayList<Integer>();
}
