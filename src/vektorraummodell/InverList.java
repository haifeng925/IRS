package vektorraummodell;
import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import boolesch.FilesOpt;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;


/**
 * Describe class InverList here.
 *
 *
 * Created: Wed Jun 29 23:10:57 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class InverList {

    /**
     * Creates a new <code>InverList</code> instance.
     *
     */
    public InverList() {
        FilesOpt fList = new FilesOpt("../doc/stwe");
        files = fList.getFiles();
        nDocs = files.length;
        initVocab();
        nVoc = vocab.size();
        tf = new int[nVoc][nDocs+1];
        tfVectorInit();
        initLists();
 
    }
    
    /**
     * die Invertiertelist anzeigen
     *
     */
    public void printInverList(){
        System.out.println("###############  sizeof invertiertelist is " + iLists.size()+" ########");
        System.out.println();
        for (int i = 0; i < nVoc; i++) {
            
            InverEntity ie = iLists.get(i);
            System.out.print("wqk: "+iLists.get(i).getWqk()+" *** ");
            System.out.print(ie.getTerm()+" ==> ");
            for (int j = 0; j < ie.getEntityList().size(); j++) {
                ListElement le = ie.getEntityList().get(j);
                System.out.print(" -> " + "( " +le.getDocNum() + "  | " + le.getWeight()+" )");
            }
            System.out.println();
            System.out.println();
        }
        
        System.out.println();
        System.out.println("#################### end of invertiertelist ############################################");
        System.out.println();
        
    }
    
    /**
     * initiiere das Vokabular List,alle Wörte wird im vocabulary.txt wird im eine List gespeichert.
     *
     */
    private void initVocab(){
        try {
            Reader reader = new FileReader(vocabFile);
            BufferedReader bfReader = new BufferedReader(reader);
            String textInLine = "";
            do{
                textInLine = bfReader.readLine();
                if(textInLine != null){
                    int l = textInLine.length();
                    int index = 3;
                    while(index<l && textInLine.charAt(index)!=' '){
                        index++;
                    }
                    String word = textInLine.substring(3,index).toLowerCase();
                    vocab.add(word);
                }
            }while(textInLine != null);
            bfReader.close();
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        // for (int i = 0; i < vocab.size(); i++) {
        //     System.out.println(vocab.get(i));
        // }
    }

    /**
     * initiiere die Invertiertelist
     *
     */
    private void initLists(){
        for (int i = 0; i < nVoc; i++) {
            InverEntity newEntity = new InverEntity(vocab.get(i));
            for (int j = 0; j < nDocs; j++) {
                double wdk = calculateWeighting(i,j);
                if(wdk>0){
                    ListElement le = new ListElement(j,wdk);
                    newEntity.addElement(le);
                }
            }
            iLists.add(newEntity);
        }
    }

    /**
     * liest alle Worte im eine Dokument und liefert als eine List von Wort zurueck
     */
    private ArrayList<String> fileToChars(File fl){
        ArrayList<String> charList = toCharList(fileToString(fl));
        // try {
        //     Reader reader = new FileReader(fl);
        //     BufferedReader bfReader = new BufferedReader(reader);
        //     String textInLine = "";
        //     do{
        //         textInLine = bfReader.readLine();
        //         if(textInLine != null){
        //             textInLine = textInLine.toLowerCase();
        //             String str[] = textInLine.split("[^a-zA-Z0-9]+&&[\n]");
        //             //toLowerCase
        //             ArrayList alist = new ArrayList(Arrays.asList(str));
        //             charList.addAll(alist);
        //         }
        //     }while(textInLine != null);
        //     bfReader.close();
        //     reader.close();
            
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
 
        return charList;
    }

    
    /**
     * liest alle Worte im eine Dokument und liefert als <code>String</code> zurueck
     *
     * @param fl a <code>File</code> value
     * @return a <code>String</code> value
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

    /**
     * liest alle Worte im String und liefert als eine List von Worte zurueck
     */
    public ArrayList<String> toCharList(String context){
	ArrayList<String> newWortList = new ArrayList<String>();
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
    
    
    /**
     * initiiere den Vektortabelle fuer Vorkommenshaeufigkeit(tf)
     *
     */
    private void tfVectorInit(){
        for (int i = 0; i < nDocs; i++) {
            ArrayList<String> fString = fileToChars(files[i]);
            for (int j = 0; j < fString.size(); j++) {
                for (int n = 0; n < nVoc; n++) {
                    if(fString.get(j).indexOf(vocab.get(n))>=0){
                        tf[n][i]+=1;
                    }
                }
            }
        }

        for (int i = 0; i < nVoc; i++) {
            int sumN = 0;
            for (int j = 0; j < nDocs; j++) {
                if(tf[i][j]>0){
                    sumN+=1;
                }
            }
            tf[i][nDocs] = sumN;
        }

        // for (int i = 0; i < nVoc; i++) {
        //     for (int j = 0; j < nDocs+1; j++) {
        //         System.out.print(tf[i][j]);
        //     }
        //     System.out.println();

        // }

    }

    
    /**
     * rechne Wdk
     *
     * @param termNum an <code>int</code> value
     * @param docNum an <code>int</code> value
     * @return a <code>double</code> value
     */
    public double calculateWeighting(int termNum, int docNum){
        double denominator=0.0;
        double tmpDenom = 0.0;
        double nominator = 0.0;
        if(tf[termNum][nDocs]==0){
            return 0.0;
        }
        nominator = tf[termNum][docNum]*Math.log(nDocs/tf[termNum][nDocs]);

        for (int i = 0; i < nVoc; i++) {
            if(tf[i][nDocs]==0){
                tmpDenom += 0;
            }else{
                tmpDenom += Math.pow(tf[i][docNum] * Math.log(nDocs/tf[i][nDocs]),2);

            }
        }
        denominator = Math.sqrt(tmpDenom);
        double result = nominator/denominator;
        return result;
        
    }

    
    /**
     * rechne Wqk
     *
     * @param terms a <code>String</code> value
     */
    public void calculateWeightingOfQ(String terms){
        ArrayList<String> wordList = toCharList(terms.toLowerCase());
        int[] tfQ = new int[nVoc];
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < nVoc; j++) {
                if(wordList.get(i).equals(iLists.get(j).getTerm())){
                    tfQ[j]+=1;
                }
            }
        }
        //double[] wqk = new double[nVoc];
        int maxtfQ = maxtfQ(tfQ);
        for (int i = 0; i < nVoc; i++) {
            if(tfQ[i]==0){
                
                iLists.get(i).setWqk(0.0);
            }else {
                double w=(0.5 + 0.5*tfQ[i]/maxtfQ)*Math.log(nDocs/tf[i][nDocs]);
                iLists.get(i).setWqk(w);
            }

        }
        //return wqk;
        
    }

    
    /**
     * lifert das maximal Vorkommenshaeufigkeit(tfqi) des Terms im Anfrage zurueck
     *
     * @param tfq an <code>int</code> value
     * @return an <code>int</code> value
     */
    private int maxtfQ(int[] tfq){
        int max = 0;
        for (int i = 0; i < tfq.length; i++) {
            max = Math.max(max,tfq[i]);
        }
        return max;

    }

 
    /**
     * setzt den Durchlaufzeiger auf das erste Listenelement
     *
     * @param k an <code>int</code> value
     */
    public void initList(int k){
        iLists.get(k).resetIndex();
    }

    /**
     * liefert das aktuelle Paar(D,Wdk) aus der Liste, setzt den Durchlaufzeiger um ein ListenerNotFoundException weiter
     *
     * @param k an <code>int</code> value
     * @return a <code>ListElement</code> value
     */
    public ListElement GetNextElementOfList(int k){
        return iLists.get(k).GetNextElementOfList();
    }

    
    /**
     * liefert TRUE, sorfert der Durchlaufzeiger auf einem Listenelement steht,
     * und FALSE, wenn der Durchlaufzeiger hinter dem letzten Listenelement steht.
     *
     * @param k an <code>int</code> value
     * @return a <code>boolean</code> value
     */
    public boolean NotAtEndOfList(int k){
        if(iLists.get(k).NotAtEndOfList()){
            return true;
        }else {
            return false;
        }

    }
    /**
     * suchen die Anfrage im Dokumentcollection und liefert die erst r Dokument zurueck
     *
     */
    public ArrayList<File> search(String que,int r){
 
        calculateWeightingOfQ(que);
        Comparator comp = new ListEntityComparator();
        Collections.reverseOrder(comp);
        Collections.sort(iLists,comp);
        // Collections.reverse(iLists);
       // printInverList();

        TopDocs topDocs = new TopDocs(r);

        HelpStructure helpStr = new HelpStructure();
        algorithmusOfBL(helpStr, topDocs,r);

        ArrayList<File> result = new ArrayList<File>();
        System.out.println("the result for \" " +que + " \" is: ");
        System.out.println();

        int count = topDocs.getCount();
        int c = r;
        if(count<r){
            c = count;
        }
        for (int i = 0; i < c; i++) {
            int docNum = topDocs.getDocNum(i);
            System.out.print((i+1)+". ");
            System.out.println(files[docNum].getName() + "     " +topDocs.getRang(i));
            result.add(files[docNum]);
        }

        System.out.println();
        return result;

    }

    
    

    /**
     * Der Algorithmus nach Buckley & Lewit
     *
     * @param helpStr a <code>HelpStructure</code> value
     * @param topDocs a <code>TopDocs</code> value
     * @param r an <code>int</code> value
     */
    public void algorithmusOfBL(HelpStructure helpStr, TopDocs topDocs,int r){
        int i = 0;
        while (iLists.get(i).getWqk() > 0) {
            initList(i);
            while(NotAtEndOfList(i)){
                ListElement newle = GetNextElementOfList(i);
                int d = newle.getDocNum();
                double wd = newle.getWeight();
                if(helpStr.IsInDS(d)){
                    helpStr.AddToDSEntry(d , iLists.get(i).getWqk() * wd);
                }else {
                    helpStr.InsertIntoDS(d , iLists.get(i).getWqk() * wd);
                }
                helpStr.byWeightSort();
                topDocs.setTopDocs(helpStr);
            }
            ListElement[] topD = topDocs.getTopDocsList();
            // System.out.println(topD[r-1].getWeight());
            // System.out.println( topD[r].getWeight() + MaxRemainingWeight(i));
            int lastIndext = topDocs.getCount()-1;

            if(topD[lastIndext].getWeight() > (topD[lastIndext].getWeight() + MaxRemainingWeight(i))){
                break;
            }

            i++;
        }

    }

    private double MaxRemainingWeight(int i){
        double sum = 0;
        while(i<iLists.size() && iLists.get(i).getWqk()>0){
            sum += iLists.get(i).getWqk();
            i++;
        }
        return sum;
    }

    private File[] files;
    private int nDocs;
    private int nVoc;
    private String vocabFile = "../vocabulary.txt";
    private ArrayList<InverEntity> iLists = new ArrayList<InverEntity>();
    private ArrayList<String> vocab = new ArrayList<String>();
    private int[][] tf;

}
