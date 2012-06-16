package boolesch;
import java.io.*;
import java.util.*;
/**
 * Describe class Lovinsstemming here.
 *
 *
 * Created: Mon May 23 22:23:20 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @author <a href=“mailto:berthold.lee@gmail.com">Bo Li</a>
 * @version 1.0
 */
public class Lovinsstemming {


    
    /**
     *um HashMap zu initialisieren
     *
     */
    public Lovinsstemming() {

	// ending, deren Länge 11 ist
	ending_l11 = new HashMap();
	ending_l11.put("alistically","B");
	ending_l11.put("arizability","A");
	ending_l11.put("izationally","B");

	// 10
	ending_l10 = new HashMap();
	ending_l10.put("antialness","A");
	ending_l10.put("arisations","A");
	ending_l10.put("arizations","A");
	ending_l10.put("entialness","A");

	// 9
	ending_l9 = new HashMap();
	ending_l9.put("allically","C");
	ending_l9.put("antaneous","A");

	// 4

	ending_l4 = new HashMap();
	ending_l4.put("able","A");
	ending_l4.put("ably","A");
	ending_l4.put("ages","B");
	ending_l4.put("ally","B");

	//3
	ending_l3 = new HashMap();
	ending_l3.put("ism","B");

	//1
	ending_l1 = new HashMap();
	ending_l1.put("e","A");
	

    }

    
    /**
     *
     *Abtrennung der Endung 
     */

    private String removeEnding(String word){
	int lg_word = word.length();
	int lg_ending = 11;
	
	while(lg_ending > 0){

	    if(lg_word - lg_ending > 1){
		String ending = word.substring(lg_word - lg_ending);
		String cd = null;
		switch (lg_ending) {
		case 11:
		    cd = (String)ending_l11.get(ending);
		    break;
		case 10:
		    cd = (String)ending_l10.get(ending);
		    break;
		case 9:
		    cd = (String)ending_l9.get(ending);
		    break;
		case 4:
		    cd = (String)ending_l4.get(ending);
		    break;
		case 3:
		    cd = (String)ending_l3.get(ending);
		    break;
		case 1:
		    cd = (String)ending_l1.get(ending);
		    break;
		default:
		    break;
		}

		if(cd != null){
		    switch (cd.charAt(0)) {
		    case 'A':
			return word.substring(0,lg_word - lg_ending);
		
		    case 'B':
			if( lg_word - lg_ending >= 3){
			    return word.substring(0,lg_word - lg_ending);
			}
			break;
		    case 'C':
			if(lg_word - lg_ending >= 4){
			    return word.substring(0,lg_word - lg_ending);
			}
			break;
		    default:
			break;
		    }
		}


	    }
	    lg_ending--;
	}
	return word;
    }

    /**
     *anhand 7 Regeln um das Wort zu verarbeiten
     *
     */

    private String recoding(String word){

	int lw = word.length();

	// Rule 1
	if(word.endsWith("s")){
	    char last2 = word.charAt(lw-2);

	    if(isConsonant(last2) && last2 !='s'){

		word = word.substring(0 , lw-1);
		lw--;
	
	    }

	}


	// Rule 2 endet mit "es" -> "e"
	if(word.endsWith("es")){
	    word = word.substring(0 , lw-1);
	    lw--;
	}
	
	// Rule 3 endet mit "iev" -> "ief"
	if(word.endsWith("iev")){
	    word = word.substring(0 , lw-3).concat("ief");
	    lw -= 3;
	}

	// Rule 4 endet mit "ing"
	if(word.endsWith("ing")){
	    if(!(lw == 4 || (lw==5 && (word.substring(0,2)).equals("th")))){
		word = word.substring(0 , lw-3);
		lw -= 3;
	    }

	    if(word.endsWith("ed")){
		word = word.concat("e");
		lw += 1;
	    }
	}
	
	// Rule 5 endet mit "ed"
	if(word.endsWith("ed") && isConsonant(word.charAt(lw-3))){
	    if(lw > 3){
		word = word.substring(0 , lw-2);
		lw -= 2;
	    }
	}

	// Rule 6 "bb" -> "b" "dd" -> "d" ...
	if (word.endsWith("bb") ||
	    word.endsWith("dd") ||
	    word.endsWith("gg") ||
	    word.endsWith("ll") ||
	    word.endsWith("mm") ||
	    word.endsWith("nn") ||
	    word.endsWith("pp") ||
	    word.endsWith("rr") ||
	    //word.endsWith("ss") ||
	    word.endsWith("tt")) {
	    word = word.substring(0, lw-1);
	    lw--;
	}

	// Rule 7 endet mit "ion"
	if(word.endsWith("ion")){

	    if(lw > 5){
		word = word.substring(0 , lw-3);
		lw -= 3;
		if(isConsonant(word.charAt(lw-1)) && !isConsonant(word.charAt(lw-2))){
		    word = word.concat("e");
		    lw++;
		}
	    }



	}
	return word;

    }


    /**
     * ueberpruefen, ob der Buchstabe Konsonant ist
     *
     */
    private boolean isConsonant(char c){

	if (consonant.indexOf(String.valueOf(c)) == -1) {
	    return false;
	}else {
	    return true;
	}

    }


    public String afterRecoding(String word){
	// if(word.endsWith("et")
	//   || word.endsWith("ed")
	//    || word.endsWith("es")){
	//     word = word.concat("e");
	// }
	return word;

    }

    /**
     * um einzelnes Wort zu verarbeiten
     *
     */
    public String stemmingWord(String word){
	if (word.length() > 2) {
	    
	    return afterRecoding(recoding(removeEnding(word.toLowerCase())));
	}else{
	    return word.toLowerCase();
	}
    }

    /**
     * um viele Woerte zusammen zu verarbeiten
     *
     */
    public String stemmingString(String context){
	ArrayList<Integer> spaceList = new ArrayList<Integer>();
	ArrayList<String> newWortList = new ArrayList<String>();

	int lastSpace = 0;
	String newContext = "";

	spaceList.add(0);

	for(int i=1; i<context.length(); i++){
	    if(context.charAt(i) == ' ' || context.charAt(i) == ','
	       ||context.charAt(i) == ';'|| context.charAt(i)=='.'
	       ||context.charAt(i) == '"'){
		spaceList.add(i);
	
	    }
	}

	spaceList.add(context.length());

	int b = 0;
	int e = spaceList.get(1);
	String nst = context.substring(b,e);
	nst = stemmingWord(nst);
	newWortList.add(nst);

	
	for(int n=2; n<spaceList.size(); n++){

	    int begin = spaceList.get(n-1)+1;
	    int end = spaceList.get(n);
	    String ns = context.substring(begin,end);
	    ns = stemmingWord(ns);
	    newWortList.add(ns);
	    
	}

	for(int j = 0; j < newWortList.size(); j++){
	    newContext = newContext + newWortList.get(j) + " ";
	}

	return newContext;
    }


    
    
    private static HashMap ending_l11 = null;
    private static HashMap ending_l10 = null;
    private static HashMap ending_l9 = null;
    private static HashMap ending_l4 = null;
    private static HashMap ending_l3 = null;
    private static HashMap ending_l1 = null;

    private static String consonant="bcdfghjklmnpqrstvwxy";

}
