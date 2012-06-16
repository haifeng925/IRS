import java.io.*;
import java.util.*;
import vektorraummodell.*;
import boolesch.*;
import vektorraummodell.TopDocs;
import vektorraummodell.ListEntityComparator;

/**
 * Describe class IRS here.
 *
 *
 * Created: Mon May 16 21:22:18 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @author <a href=“mailto:berthold.lee@gmail.com">Bo Li</a>
 * @version 1.0
 */

public class IRS {
   
    
    /**
     * das main Funktion
     *
     */
    public static void main(String[] args) {
        //	stw = new Stoppwort("../englishST.txt");
        //lovinsstemm = new Lovinsstemming();
	//pts = new Porterstemming();
	//FabelZerlegen fz = new FabelZerlegen(fabel);
	///fz.orgDocRead();
	

	//	testStopWortElimieren();
        //	stoppWortElimieren();
	//	testLovins();
	//lovins();
	//testPorter();
        //	porter();
        // testInvertierteList();

        //testSignature();
        //testSignaturBlock();
        // runInvertierteList();
        //runSignatur();
        vektortest();
        searchInVektorraummodel();
       
        
    }

    

    /**
     * Es ist nur die Antwort fuer Praktikum4.
     * Gebe die Reihenfolgen fuer die Suchbegriffe "man" und "fox" aus
     */
    public static void vektortest(){
        InverList il = new InverList();
     
        il.printInverList();

        ArrayList<File> rst = il.search("fox man",5);
        ArrayList<File> fox = il.search("fox",5);
        ArrayList<File> man = il.search("man",5);


        // il.printInverList();

        // Test a = new Test();
    }

    


    /**
     * suchen mitels Vektoraummodel 
     *
     */
    public static void searchInVektorraummodel(){
        InverList il = new InverList();

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++   Search with vectormodell: ");
        System.out.println("+++   please enter a string: ");
        System.out.println("+++   e.g. \" fox man \"");
        System.out.println("+++   quit with \"q\" ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println();
            System.out.println("----------------------------------------");

            while(true){
                System.out.println("How many document would you like to display in results?");
                System.out.println("Please enter a number:  ");
                String str = stdin.readLine();
                if(str.equals("q")) {
                    System.exit(0);
                }                

                int r = Integer.parseInt(str);
                System.out.println("Now enter your search theme: ");
                String terms = stdin.readLine();
                if(terms.equals("q")){
                    System.exit(0);
                }
                ArrayList<File> rst = il.search(terms,r);
                
                
            }
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
    
    public static void runInvertierteList(){
        InvertierteList iL = new InvertierteList();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++   Test for InvertierteList: ");
        System.out.println("+++   you can search only one word or two word connet with \"AND\" or \"OR\" ");
        System.out.println("+++   e.g. \" Bird OR Ass \"");
        System.out.println("+++   quit with \"q\" ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //       iL.inverListtest();
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println();
            System.out.println("----------------------------------------");

            System.out.println("Please enter your search theme: ");
            String str = stdin.readLine();
            while (!str.equals("q")) {
                ArrayList<Integer> numList = iL.searchBool(str);
                ArrayList<String> al = iL.numToPath(numList);
                System.out.println("----Result:");
                for(int i=0;i<al.size() ;i++){
                    System.out.println(al.get(i));
                }
                System.out.println("----------------------------------------");
                System.out.println();
                System.out.println("----------------------------------------");

                System.out.println("Please enter your search theme: ");
                str = stdin.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
         }

        
        
    }
    
    public static void runSignatur(){
        SignaturBlock sb = new SignaturBlock();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++   Test for Signatur: ");
        System.out.println("+++   you can search wordgroup or two term connet with \"AND\" or \"OR\" ");
        System.out.println("+++   e.g. \" Cock Pearl AND Ox  \"");
        System.out.println("+++   quit with \"q\" ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println();
            System.out.println("----------------------------------------");

            System.out.println("Please enter your search theme: ");
            String str = stdin.readLine();   

            while (!str.equals("q")) {
                ArrayList<String> al = sb.searchBool(str);
                System.out.println("----Result:");
                for(int i=0;i<al.size() ;i++){
                    System.out.println(al.get(i));
                }
                System.out.println("----------------------------------------");
                System.out.println();
                System.out.println("----------------------------------------");

                System.out.println("Please enter your search theme: ");
                str = stdin.readLine();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    public static void testSignaturBlock(){
        SignaturBlock sb = new SignaturBlock();
        ArrayList<String> al = sb.search("Ass");
        for(int i=0;i<al.size() ;i++){
            System.out.println(al.get(i));
        }
        
    }
    public static void testSignature(){

        //
        Signatur.primInit();
        // System.out.println(Signatur.getM(64,4));
        
        Signatur sg = new Signatur("china");
        System.out.println(sg.getSig().toString());

        SignaturBlock sb = new SignaturBlock();
        System.out.println(sb.stringToSig("deutsch germany ").getSig().toString());
    }
    
    /*
    public static void testInvertierteList(){
        InvertierteList iL = new InvertierteList();
        iL.inverListtest();
        System.out.println("+++++++++++++++++");
        ArrayList<Integer> rs = iL.search("Bird");
        for(int i=0; i<rs.size(); i++){
            System.out.print(rs.get(i)+"   ");
   
        }
        System.out.println();
        
        rs = iL.search("Eagle","Arrow",'A');
         for(int i=0; i<rs.size(); i++){
            System.out.print(rs.get(i) + "   ");
            
        }
         System.out.println();

         rs = iL.search("Fox","Ass",'O');
         for(int i=0; i<rs.size(); i++){
            System.out.print(rs.get(i) + "   ");
            
        }
         System.out.println();
         
         
         }*/

    
    /**
     * das Testfunktion fuer toppwordelimierung, 
     *
     */
    public static void testStoppWortElimieren(){

	String s = stw.stwElimieren("Two neighbours came before Jupiter and prayed him to grant");

	System.out.println(s);
        
    }

    /**
     * alle Textdatai mit Stoppwortelimierung
     *
     */
    public static void stoppWortElimieren() {
	FilesOpt fopt = new FilesOpt(pathOfZl);
	File[] fList = fopt.getFiles();
	for(int i=0; i<fList.length; i++){

	    String fileName = fList[i].getName();
	    String newFilePath = "../doc/stwe/"+fileName.substring(0,fileName.length()-4)+"_stw"+".txt";
	    //   System.out.println(newFilePath);
	    String textLine = "";
	    File newfile = new File(newFilePath);
	    try{

		Reader reader = new FileReader(fList[i]);
		BufferedReader bfReader = new BufferedReader(reader);
		textLine = bfReader.readLine();
		FileWriter fwriter = new FileWriter(newfile);
		BufferedWriter bfwriter = new BufferedWriter(fwriter);
		

		//	System.out.println(textLine);
		while(textLine!=null){
		    
		    String newst = stw.stwElimieren(textLine);
		    //   System.out.println(newst);
		    bfwriter.write(newst);
		    bfwriter.newLine();
		    textLine = bfReader.readLine();
		 
		}
		
		bfwriter.flush();
		bfwriter.close();
		fwriter.close();
		bfReader.close();
		reader.close();


	    }catch (Exception e) {
		e.printStackTrace();

	    }

	}
    }

    /**
     * testfalls fuer Lovins-Algorithmus
     *
     */
    public static void testLovins(){
	String s = "stems stress places likes theses believable thinking singing thing";
	String ns = lovinsstemm.stemmingString(s);
	System.out.println(ns);
	s = "preceding ended red proceed proceeded embedded ";
	ns = lovinsstemm.stemmingString(s);
	System.out.println(ns);
	s = "direction polution plantation zion scion anion cation";
	ns = lovinsstemm.stemmingString(s);
		
	System.out.println(ns);
		
    }
    /**
     * alle Textdatei mit Lovins-Algorithmus verarbeited werden
     *
     */
    public static void lovins(){
	FilesOpt fopt = new FilesOpt(pathOfZl);
	File[] fList = fopt.getFiles();
	for(int i=0; i<fList.length; i++){

	    String fileName = fList[i].getName();
	    String newFilePath = "../doc/lovins/"+fileName.substring(0,fileName.length()-4)+"_lovins"+".txt";
	    //System.out.println(newFilePath);
	    String textLine = "";
	    File newfile = new File(newFilePath);
	    try{

		Reader reader = new FileReader(fList[i]);
		BufferedReader bfReader = new BufferedReader(reader);
		textLine = bfReader.readLine();
		FileWriter fwriter = new FileWriter(newfile);
		BufferedWriter bfwriter = new BufferedWriter(fwriter);
		

		//	System.out.println(textLine);
		while(textLine!=null){
		    
		    String newst = stw.stwElimieren(textLine);
		    System.out.println(newst);
		    String lovinsst = lovinsstemm.stemmingString(newst);
		    System.out.println(lovinsst);
		    bfwriter.write(lovinsst);
		    bfwriter.newLine();
		    textLine = bfReader.readLine();
		 
		}
		
		bfwriter.flush();
		bfwriter.close();
		fwriter.close();
		bfReader.close();
		reader.close();


	    }catch (Exception e) {
		e.printStackTrace();

	    }
	}	

    }


    public static void testPorter(){
	String tests = "stems stress places likes theses believable thinking singing thing ";
	String ns = pts.stemmingString(tests);
	System.out.println(ns);
    }

    /**
     * alle Textdatei mit Porter-Algorithmus verarbeited werden
     */

    public static void porter(){

	FilesOpt fopt = new FilesOpt(pathOfZl);
	File[] fList = fopt.getFiles();
	for(int i=0; i<fList.length; i++){
	
	    String fileName = fList[i].getName();


	    String newFilePath = "../doc/porter/"+fileName.substring(0,fileName.length()-4)+"_porter"+".txt";
	
	    //   System.out.println(newFilePath);
	    String textLine = "";
	    File newfile = new File(newFilePath);
	    try{

		Reader reader = new FileReader(fList[i]);
		BufferedReader bfReader = new BufferedReader(reader);
		textLine = bfReader.readLine();
		FileWriter fwriter = new FileWriter(newfile);
		BufferedWriter bfwriter = new BufferedWriter(fwriter);
		

		// System.out.println(textLine);
		while(textLine!=null){
		    
		    String newst = stw.stwElimieren(textLine);
		    System.out.println(newst);
		    String porterst = pts.stemmingString(newst);
		    System.out.println(porterst);
		    bfwriter.write(porterst);
		    bfwriter.newLine();
		    textLine = bfReader.readLine();
		 
		}
		
		bfwriter.flush();
		bfwriter.close();
		fwriter.close();
		bfReader.close();
		reader.close();


	    }catch (Exception e) {
		e.printStackTrace();

	    }
	}	

    }
    
    private static String fabel="../aesopa10.txt";
    private static String pathOfZl="../doc/zl";
    private static Stoppwort stw;
    private static Lovinsstemming lovinsstemm = null;
    private static Porterstemming pts = null;
}
