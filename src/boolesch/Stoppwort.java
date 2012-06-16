package boolesch;
import java.util.*;
import java.io.*;

/**
 * Describe class Stoppworte here.
 *
 *
 * Created: Sun May 22 21:47:49 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @author <a href=“mailto:berthold.lee@gmail.com">Bo Li</a>
 * @version 1.0
 */
public class Stoppwort {

    /**
     * 
     *pathST = "../englishST.txt"
     */
    public Stoppwort() {
     	pathST = "../englishST.txt";
	hashInit();

    }
    /**
     * path ist der Pfad von Stoppwortedatei
     *
     */
    public Stoppwort(String path){
	pathST = path;
	hashInit();
		
    }

    /**
     * HashTable von Stoppwort initialisieren
     *
     */
    private void hashInit(){

	stoppwort = new Hashtable();

	try{
	    Reader reader = new FileReader(pathST);
		
	    BufferedReader bfReader = new BufferedReader(reader);
	    String textInLine = null;
	    do{
		textInLine = bfReader.readLine();
		if(textInLine != null){
		    stoppwort.put(textInLine,true);
		}
	    }while (textInLine != null);


	    bfReader.close();
	    
	}catch (Exception e) {
	    e.printStackTrace();
	}
	
    }

    

    public Hashtable getStoppwort(){
	return stoppwort;
    }

    /**
     * ueberpruefen, ob das Worte Stoppworte ist
     *
     */
    public boolean isStoppWort(String worte){
	return  stoppwort.containsKey(worte);
	
    }

    /**
     * eine String wird verarbeitet,
     * jedes StoppWort in diser String wird entfernt
     */
    public String stwElimieren(String context){


	ArrayList<Integer> spaceList = new ArrayList<Integer>();
	ArrayList<String> newWortList = new ArrayList<String>();
	int lastSpace = 0;
	String newContext = "";

	spaceList.add(0);

	for(int i=1; i<context.length(); i++){
	    if(context.charAt(i) == ' ' || context.charAt(i) == ','
	       ||context.charAt(i) == ';'|| context.charAt(i)=='.'){
		spaceList.add(i);
	
	    }
	}

	spaceList.add(context.length());

	//das erstes Worte
	int b = 0;
	int e = spaceList.get(1);
	String nst = context.substring(b,e);
	String nstl = nst.toLowerCase();
	if(!isStoppWort(nstl)){
	    newWortList.add(nst);
	    // System.out.println(nst);
	}

	for(int n=2; n<spaceList.size(); n++){

	    int begin = spaceList.get(n-1)+1;
	    int end = spaceList.get(n);
	    String ns = context.substring(begin,end);
	    String nslower = ns.toLowerCase();
	    if(!isStoppWort(nslower)){
		newWortList.add(ns);
		//	System.out.println(ns);
	    }
	}



	for(int j = 0; j < newWortList.size(); j++){
	    newContext = newContext + newWortList.get(j) + " ";
	}

	//	newContext = newContext.substring(0 , newContext.length()-1);
	return newContext;

    }

    
    
    private  Hashtable stoppwort;
    private  String pathST;

}
