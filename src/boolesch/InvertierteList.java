package boolesch;
import java.io.*;
import java.util.*;


/**
 * Describe class InvertierteList here.
 *
 *
 * Created: Sun Jun 12 19:03:46 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class InvertierteList {

    /**
     * Creates a new <code>InvertierteList</code> instance.
     *
     */
    public InvertierteList() {
        vocabFile = "../vocabulary.txt";
        //        docPath = "../doc/zl";
        docTable = new HashMap();
        FilesOpt fList = new FilesOpt("../doc/datei");
        files = fList.getFiles();
        //   System.out.println("this is in InvertierteList");
        docTableInit();
        inverListInit();
    }

    /**
     *
     *
     */
    public InvertierteList(String dp){
        vocabFile = dp;
        docTable = new HashMap();
        FilesOpt fList = new FilesOpt();
        files = fList.getFiles();
        docTableInit();
        //  docPath = "../doc/zl";
        inverListInit();
    }


    
    /**
     *
     *DocTable(eine HashMap) initializieren,
     *Die Documentpath wird auf Documentnummer abgebildet
     */
    public void docTableInit(){
     
        for(int i=0; i<files.length; i++){
            //    System.out.println(fArray[i].getPath());
            docTable.put(i,files[i].getPath());
            
        }

        // //test for docTable

        // for (int i = 0; i<docTable.size();i++) {
        //     System.out.println(docTable.get(i));

        // }


    }

     public void inverListtest(){
        
        for(int i = 0; i<iLists.size();i++){
            System.out.print(iLists.get(i).getWord() + " ");
            int l = iLists.get(i).getDocList().size();
            for(int n = 0; n<l;n++){
                System.out.print(iLists.get(i).getDocList().get(n) + " ");
                
            }
            System.out.println();
        }
    }
    


     /**
     *
     *Initialisierung der invertierte List
     *alle Document wird durchlaufen, ueberpruefen, ob das Document das Schusswort enthalten
     */
    public void inverListInit(){

        try {
       
            Reader reader = new FileReader(vocabFile);
            BufferedReader bfReader = new BufferedReader(reader);
            String textInLine = null;
            do{
                textInLine = bfReader.readLine();
                if(textInLine != null){
                    int l = textInLine.length();
                    int index = 3;
                    while(index<l && textInLine.charAt(index)!=' '){
                        index++;
                    }
                    String word = textInLine.substring(3,index).toLowerCase();
                    InverListStru newInverList = new InverListStru(word);
                    for(int i=0; i<files.length; i++){
                        if(isContain(files[i],word)){
                            newInverList.addDoc(i);
                        }   
                    }

                    iLists.add(newInverList);
                }
            }while(textInLine != null);


            bfReader.close();
            reader.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *Ob das Document das Schlussword enthalten
     */
    public boolean isContain(File file ,String word){
        String text = "";
        try {
            Reader reader = new FileReader(file);
            BufferedReader bfReader = new BufferedReader(reader);
            String textInLine = "";
  
            do{
                textInLine = bfReader.readLine();
                if(textInLine != null){
                    text += textInLine.toLowerCase();
                }
            }while (textInLine != null);


            bfReader.close();
            reader.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }

   
        if(text.indexOf(word) >= 0){
            //  System.out.println("---------"+word);
            return true;
        }else{
            return false;
        }
      
    }

    /**
     *
     * die invertierte List wird durchlaufen,suchen die List,
     *deren Schlussword gleich wie Anfrage ist.
     *
     */
    
    public ArrayList<Integer> search(String wd){
        ArrayList<Integer> rst = new ArrayList<Integer>();
        for (int i=0; i<iLists.size(); i++) {
       
            if(iLists.get(i).getWord().equals(wd.toLowerCase())){
              
                rst.addAll(iLists.get(i).getDocList());
            }

        }
        return rst;

    }

    

    public ArrayList<String> numToPath(ArrayList<Integer> numList){
        ArrayList<String> pathList = new ArrayList<String>();
        for(int i=0;i<numList.size();i++){
            int x = numList.get(i);
            String tmp = (String)docTable.get(x);
            pathList.add(tmp);
        }
        return pathList;
    }



    public ArrayList<Integer> searchBool(String str){
        if(str.indexOf("AND")>=0){
            return searchAND(str);
        }else if(str.indexOf("OR")>=0){
            return searchOR(str);
        }else {
            return search(str);
        }

    }
    /**
     *
     * eine Suche mittels AND von zwei Suchbegriffen
     */
    

    public ArrayList<Integer> searchAND(String str){
        int lg = str.length();
        int indexOfAnd = str.indexOf(" AND ");
        String firstElement = str.substring(0 , indexOfAnd);
        String secondElement = str.substring(indexOfAnd+5,lg);

        ArrayList<Integer> rs1 = search(firstElement);
        ArrayList<Integer> rs2 = search(secondElement);
        ArrayList<Integer> rst = new ArrayList<Integer>();
        int index1 = 0;
        int index2 = 0;

        while(index1<rs1.size() && index2<rs2.size()){
            if(rs1.get(index1) < rs2.get(index2)){
                index1++;
            }else if(rs1.get(index1) > rs2.get(index2)){
                index2++;
            }else if(rs1.get(index1) == rs2.get(index2)){
                rst.add(rs1.get(index1));
                index1++;
                index2++;
            }

        }
        return rst;
        
    }

    /**
     *
     * eine Suche mittels OR von zwei Suchbegriffen
     */
    

    public ArrayList<Integer> searchOR(String str){
        int lg = str.length();
        int indexOfAnd = str.indexOf(" OR ");
        String firstElement = str.substring(0 , indexOfAnd);
        String secondElement = str.substring(indexOfAnd+4,lg);

        ArrayList<Integer> rs1 = search(firstElement);
        ArrayList<Integer> rs2 = search(secondElement);
        ArrayList<Integer> rst = new ArrayList<Integer>();
        int index1 = 0;
        int index2 = 0;

        while(index1<rs1.size() && index2<rs2.size()){
            if(rs1.get(index1) < rs2.get(index2)){
                rst.add(rs1.get(index1));
                index1++;
            }else if(rs1.get(index1) > rs2.get(index2)){
                rst.add(rs2.get(index2));
                index2++;
            }else {
                rst.add(rs2.get(index2));
                index1++;
                index2++;
            }
        }

        if(index1<rs1.size()){
            for(int x = index1; x<rs1.size(); x++){
                rst.add(rs1.get(x));
            }
        }
        if(index2<rs2.size()){
            for(int x = index2; x<rs2.size(); x++){
                rst.add(rs2.get(x));
            }
        }

        return rst;
        
    }
   


    private File[] files = null;
    private String vocabFile = null;
    //    private String docPath = null;
    private HashMap docTable = null;
    private ArrayList<Integer> spaceList = new ArrayList<Integer>();
    private ArrayList<InverListStru> iLists = new ArrayList<InverListStru>();
}
