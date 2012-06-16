package boolesch;
import java.io.*;
import java.util.*;
/**
 * Describe class SignaturBlock here.
 *
 *
 * Created: Thu Jun 16 14:21:13 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class SignaturBlock {

    /**
     * Creates a new <code>SignaturBlock</code> instance.
     *
     */
    public SignaturBlock(){
        d = 4;
        Signatur.primInit();
        FilesOpt fList = new FilesOpt("../datei");
        files = fList.getFiles();
    }

    /**
     *
     *
     *
     */


    public ArrayList<String> searchBool(String str){

        if(str.indexOf("AND")>=0){
            return searchAND(str);
        }else if(str.indexOf("OR")>=0){
            return searchOR(str);
        }else {
            return search(str);
        }

    }


    
    public ArrayList<String> searchAND(String str){
        int lg = str.length();
        int indexOfAnd = str.indexOf(" AND ");
        String firstElement = str.substring(0 , indexOfAnd);
        String secondElement = str.substring(indexOfAnd+5,lg);
        String newString =firstElement + " "+secondElement;
        /*   System.out.println(firstElement);
        System.out.println(secondElement);
        System.out.println(newString);*/
        return search(newString);
        
    }

    public ArrayList<String> searchOR(String str){
        int lg = str.length();
        int indexOfAnd = str.indexOf(" OR ");
        String firstElement = str.substring(0 , indexOfAnd);
        String secondElement = str.substring(indexOfAnd+4,lg);
        ArrayList<String> firstCollection = search(firstElement);
        ArrayList<String> secondCollection = search(secondElement);
        for(int i=0; i<secondCollection.size();i++){
            for(int j=0;j<firstCollection.size();j++){
                if(! secondCollection.get(i).equals(firstCollection.get(j))){
                    firstCollection.add(secondCollection.get(i));
                }
            }

        }

        return firstCollection;

    }

    
    public ArrayList<String> searchExact(String str){
        ArrayList<String> rst = new ArrayList<String>();
        for(int i=0; i<files.length; i++){
            if(isHave(files[i],str)){
                String temp = fileToString(files[i]);
                //   System.out.println(temp);
                if(temp.indexOf(str)>=0){
                      rst.add(files[i].getPath());
                }
            }
        }
        return rst;
    }

    public ArrayList<String> search(String str){
        str = str.toLowerCase();
        ArrayList<String> rst = new ArrayList<String>();
        for(int i=0; i<files.length; i++){
            if(isHave(files[i],str)){
                String temp = fileToString(files[i]);
                //   System.out.println(temp);
                boolean b = true;
                ArrayList<String> wordList = toCharList(str);
                for (int j=0; j<wordList.size();j++) {
                    if(!(temp.indexOf(wordList.get(j))>=0)){
                        b = false;
                    }
                }
                if(b==true){
                    rst.add(files[i].getPath());
                }

            }
        }
        return rst;
    }
    

    /**
     *
     * ob eines Document das Schlussword enthalten
     */
    public boolean isHave(File fl, String keyword){
        ArrayList<Signatur> sigBlockList = fileToSig(fl);
        Signatur sigOfKey = stringToSig(keyword.toLowerCase());
        for(int i = 0; i<sigBlockList.size();i++){
            sigBlockList.get(i).and(sigOfKey.getSig());
            
            if(sigOfKey.getSig().equals(sigBlockList.get(i).getSig())){
                return true;
            }
        }
        return false;
    }

    

    /**
     *
     * verarbeited das Document mittels Blocksignaturen und liefern eine List von Type Signatur
     */
    
    public ArrayList<Signatur> fileToSig(File fl){
        ArrayList<String> wordList = toCharList(fileToString(fl));
        //  for(int i=0; i<wordList.size();i++){
            // System.out.println(wordList.get(i));
        //}
        ArrayList<Signatur> sigBlock = new ArrayList<Signatur>();
        Signatur sigTemp = new Signatur(wordList.get(0));
        for(int i=0; i<wordList.size();i++){
            if(i%d == 0){
                sigTemp = new Signatur(wordList.get(i));
            }else if(i%d == d-1){
                String wd = wordList.get(i);
                Signatur newSig = new Signatur(wd);
                sigTemp.setWord(sigTemp.getWord() + " "+ wd);
                sigTemp.or(newSig.getSig());
                sigBlock.add(sigTemp);
            }else {
                String wd = wordList.get(i);
                Signatur newSig = new Signatur(wd);
                sigTemp.setWord(sigTemp.getWord()+ " "+ wd);
                sigTemp.or(newSig.getSig());
            }

        }

        return sigBlock;
    }

    /**
     *
     * 
     */
    public String fileToString(File fl){
        String context="";
        
        try {
       
            Reader reader = new FileReader(fl);
            BufferedReader bfReader = new BufferedReader(reader);
            String textInLine = null;
            do{
                textInLine = bfReader.readLine();
                if(textInLine != null){
                    context += textInLine.toLowerCase();
                }

            }while(textInLine != null);


            bfReader.close();
            reader.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return context;
    }
    

    public Signatur stringToSig(String str){
        ArrayList<String> wordList = toCharList(str);
        /*  for(int i=0; i<wordList.size();i++){
            System.out.println(wordList.get(i));
            }*/
        ArrayList<Signatur> sigList = new ArrayList<Signatur>();
        for(int i=0;i<wordList.size();i++){
            sigList.add(new Signatur(wordList.get(i)));
        }
        Signatur sig = sigList.get(0);
        for(int i=1; i<sigList.size();i++){
            sig.or(sigList.get(i).getSig());
        }
        return sig;
    }

    public ArrayList<String> toCharList(String context){
	ArrayList<String> newWortList = new ArrayList<String>();

        //	int lastSpace = 0;
	String newContext = "";

        int actIdent=0;
        int lastIdent=0;
        boolean flag = false;
        for(int i=0; i<context.length();i++){
            if(Character.isLetterOrDigit(context.charAt(i)) && !flag){
                lastIdent = i;
                flag = true;
            }
            if((!Character.isLetterOrDigit(context.charAt(i)) && flag)){
                newWortList.add(context.substring(lastIdent , i));
                flag = false;
            }
 

        }

        newWortList.add(context.substring(lastIdent , context.length()));
        


        return newWortList;
    }
    
    private String vocabFile = "../vocabulary.txt";
    private File[] files = null;
    private int d;

}
