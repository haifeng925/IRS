package boolesch;
import java.io.*;
import java.util.*;
/**
 * Describe class Porterstemming here.
 *
 *
 * Created: Tue May 24 13:55:56 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @author <a href=“mailto:berthold.lee@gmail.com">Bo Li</a>
 * @version 1.0
 */
public class Porterstemming {

    /**
     * Creates a new <code>Porterstemming</code> instance.
     *
     */
    public Porterstemming() {

    }

    /**
     *  return ein Schriftzeichen:
     *      Wenn es der Vokal ist, return v;
     *      Wenn es der Konsonant ist, return c;
     *      Wenn es 'y' ist, return y.
     */
    private char vcTrans(char c){
	if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return 'v';
	if(c == 'y') return 'y';
	return 'c';
    }

    /**
     *  Ueberlegen, ob der Buchstabe der Vokal oder der Konsonant ist.
     */
    private boolean isConsonant(String word , int index){

	if(index < word.length() && index > 0){
	    char charAtIndex = word.charAt(index);
	    char vc = vcTrans(charAtIndex);
	    switch (vc) {
	    case 'c':
		return true;
	    case 'v':
		return false;
	    case 'y':
		
		if(isConsonant(word,index-1)){
		    return false;
		}else {
		    return true;
		}
	
	    default:
		break;
	    }
	    
	}else {
	    return false;
	}
	return false;
	
    }
    /**
     *  Rechnen die Anzahl, wie viel 'm' es im Wort gibt.
     */
    public int getM(String word){

	int m = 0;
	
	String vc = "";
	if(word != null){
	    for (int i = 0; i < word.length(); i++) {
		char charAtIndex = word.charAt(i);
	
		char vcChar =vcTrans(charAtIndex);
	
		if(vcChar == 'y'){
		    if(i>0 && vcTrans(word.charAt(i-1)) == 'c'){
			vc.concat("v");
		    }else{
			vc.concat("c");
		    }
		}else{
		    // String s = String.valueOf(word.charAt(i));
		    // String ns =(String) vcTable.get(s);
		    String ns = String.valueOf(vcTrans(word.charAt(i)));
		    vc.concat(ns);
		}
	
	    }
         
	    for (int i = 0; i < vc.length()-1; ++i) {
		if ((vc.charAt(i) == 'v') && (vc.charAt(i+1) == 'c')) {
		    m++;
		}
	    }

	}
	return m;

    }

    /**
     *  Porter-Algorithmus-Stemming: Step1a
     */
    private String step1a (String word){
	int lw = word.length();
	if(word.endsWith("sses")){
	    word = word.substring(0 , lw-4).concat("ss");
	}else if(word.endsWith("ies")){
	    word = word.substring(0 , lw -2);
	}else if(word.endsWith("ss")){
	    word = word;
	}else if(word.endsWith("s")){
	    word = word.substring(0 , lw-1);
	}

	return word;
    }

    /**
     *  Porter-Algorithmus-Stemming: Step1b
     */
    private String step1b (String word){
	
	int lw = word.length();
	int mbefore = 0;


	if(word.endsWith("eed")){
	    if(word.length()>3){
		mbefore = getM(word.substring(0,lw-3));
		if(mbefore>0){
		    word = word.substring(0,lw-1);
			    
		}
	    }
	 
	}else if(word.endsWith("ed")){

	    if(word.length()>2){
		String restED = word.substring(0 , lw-2);
		if(isVowelInStem(restED) ){
		    word = restInStep1b(restED);
		}

	    }

	    
	}else if(word.endsWith("ing")){

	    if(word.length()>3){
		String restING = word.substring(0 , lw-3);
		if(isVowelInStem(restING)){
		    word = restInStep1b(restING);
		}
	
	    }

	}

	return word;

    }

    /**
     *  Porter-Algorithmus-Stemming: Step1c
     */
    private String step1c (String word){
	int lw = word.length();
	//	System.out.println(lw);
	if(lw > 1){
	    if(isVowelInStem(word.substring(0,lw-1)) && word.charAt(lw-1) == 'y'){
		word = word.substring(0,lw-1).concat("i");
	    }
	}

	return word;
    }



    /**
     *  Porter-Algorithmus-Stemming: Step2
     */
    private String step2 (String word){
	int lw = word.length();
	if(isCondition(word,"ational",0)){
	    int le = "ational".length();
	    word = word.substring(0 , lw - le).concat("ate");
	}else if(isCondition(word,"tional",0)){
	    int le = "tional".length();
	    word = word.substring(0 , lw-le).concat("tion");
	}else if(isCondition(word,"enci",0)){
	    int le = "enci".length();
	    word = word.substring(0 , lw-le).concat("ence");
	}else if(isCondition(word,"anci",0)){
	    int le = "anci".length();
	    word = word.substring(0 , lw-le).concat("anci");
	}else if(isCondition(word,"izer",0)){
	    int le = "izer".length();
	    word = word.substring(0 , lw-le).concat("ize");
	}else if(isCondition(word,"abli",0)){
	    int le = "abli".length();
	    word = word.substring(0 , lw-le).concat("able");
	}else if(isCondition(word,"alli",0)){
	    int le = "alli".length();
	    word = word.substring(0 , lw-le).concat("al");
	}else if(isCondition(word,"entli",0)){
	    int le = "entli".length();
	    word = word.substring(0 , lw-le).concat("ent");
	}else if(isCondition(word,"eli",0)){
	    int le = "eli".length();
	    word = word.substring(0 , lw-le).concat("e");
	}else if(isCondition(word,"ousli",0)){
	    int le = "ousli".length();
	    word = word.substring(0 , lw-le).concat("ous");
	}else if(isCondition(word,"ization",0)){
	    int le = "ization".length();
	    word = word.substring(0 , lw-le).concat("ize");
	}else if(isCondition(word,"ation",0)){
	    int le = "ation".length();
	    word = word.substring(0 , lw-le).concat("ate");
	}else if(isCondition(word,"ator",0)){
	    int le = "ator".length();
	    word = word.substring(0 , lw-le).concat("ate");
	}else if(isCondition(word,"alism",0)){
	    int le = "alism".length();
	    word = word.substring(0 , lw-le).concat("al");
	}else if(isCondition(word,"iveness",0)){
	    int le = "iveness".length();
	    word = word.substring(0 , lw-le).concat("ive");
	}else if(isCondition(word,"fulness",0)){
	    int le = "fulness".length();
	    word = word.substring(0 , lw-le).concat("ful");
	}else if(isCondition(word,"ousness",0)){
	    int le = "ousness".length();
	    word = word.substring(0 , lw-le).concat("ousness");
	}else if(isCondition(word,"aliti",0)){
	    int le = "aliti".length();
	    word = word.substring(0 , lw-le).concat("al");
	}else if(isCondition(word,"iviti",0)){
	    int le = "iviti".length();
	    word = word.substring(0 , lw-le).concat("ive");
	}else if(isCondition(word,"biliti",0)){
	    int le = "biliti".length();
	    word = word.substring(0 , lw-le).concat("ble");
	}
	return word;
	
    }

    /**
     *  Vergleichen, ob die 'm'-Anzahl des Wortes die Bedingungen entspricht.
     */
    private boolean isCondition(String word, String ending , int m){
	int lw = word.length();
	int le = ending.length();
	if(word.endsWith(ending) && getM(word.substring(0,lw-le))>m){
	    return true;
	}else {
	    return false;
	}

	
    }

    /**
     *  Porter-Algorithmus-Stemming: Step3
     */
    private String step3 (String word){
	int lw = word.length();
	if(isCondition(word,"icate",0)){
	    
	    int le = "icate".length();
	    word = word.substring(0 , lw - le).concat("ic");
	    
	}else if(isCondition(word,"ative",0)){
	    
	    int le = "ative".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"alize",0)){
	    
	    int le = "alize".length();
	    word = word.substring(0 , lw-le).concat("al");
	    
	}else if(isCondition(word,"iciti",0)){
	    
	    int le = "iciti".length();
	    word = word.substring(0 , lw-le).concat("ic");
	    
	}else if(isCondition(word,"ical",0)){
	    
	    int le = "ical".length();
	    word = word.substring(0 , lw-le).concat("ic");
	    
	}else if(isCondition(word,"ful",0)){
	    
	    int le = "ful".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ness",0)){
	    
	    int le = "ness".length();
	    word = word.substring(0 , lw-le);
	    
	}
	return word;

    }

    /**
     *  Porter-Algorithmus-Stemming: Step4
     */
    private String step4(String word){
	int lw = word.length();
	if(isCondition(word,"al",1)){
	    
	    int le = "al".length();
	    word = word.substring(0 , lw - le);
	    
	}else if(isCondition(word,"ance",1)){
	    
	    int le = "ance".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ence",1)){
	    
	    int le = "ence".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"er",1)){
	    
	    int le = "er".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ic",1)){
	    
	    int le = "ic".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"able",1)){
	    
	    int le = "able".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ible",1)){
	    
	    int le = "ible".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ant",1)){
	    
	    int le = "ant".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ement",1)){
	    
	    int le = "ement".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ment",1)){
	    
	    int le = "ment".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ent",1)){
	    
	    int le = "ent".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"sion",1)){
	    
	    int le = "sion".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"tion",1)){
	    
	    int le = "tion".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ou",1)){
	    
	    int le = "ou".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ism",1)){
	    
	    int le = "ism".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ate",1)){
	    
	    int le = "ate".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"iti",1)){
	    
	    int le = "iti".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ous",1)){
	    
	    int le = "ous".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ive",1)){
	    
	    int le = "ive".length();
	    word = word.substring(0 , lw-le);
	    
	}else if(isCondition(word,"ize",1)){
	    
	    int le = "ize".length();
	    word = word.substring(0 , lw-le);
	    
	}
	return word;
    }


    /**
     *  Porter-Algorithmus-Stemming: Step5a
     */
    private String step5a (String word) {
	int lw = word.length();
	if (lw > 1) {
	    String subs = word.substring(0 , lw-1);
	    if(getM(subs)>1 && word.endsWith("e")){
		word = subs;
	    }else if(getM(subs) == 1 && !vcv(subs) && word.endsWith("e")){
		word = subs;
	    }

	}


	return word;
	
    }

    /**
     *  Porter-Algorithmus-Stemming: Step5b
     */
    private String step5b (String word){
	int lw = word.length();
	if(getM(word)>0 && isDoubleEnd(word) && word.charAt(lw-1) == 'l'){
	    word = word.substring(0,lw-1);
	}
	
	return word;
    }
 

    
	
    /**
     *  Urteilen ueber den Vokal im Stemming
     */

    private boolean isVowelInStem(String word){

	boolean rt = false;
	for(int i=0; i<word.length(); i++){
	    //   String vRc =(String) vcTable.get(String.valueOf(word.charAt(i)));
	    char vRc = vcTrans(word.charAt(i));
	    switch(vRc){
	    case 'v':
		rt = true;
		break;
	    case 'c':
		break;
	    case 'y':

		{
		    //   if (i>0 && vcTable.get(word.charAt(i-1)).equals("c")) {
		    if(i>0 && vcTrans(word.charAt(i-1))=='c'){
			rt = true;
		    }

		}
		break;
	    default:
		break;
		
	    }
	}

	return rt;
	
    }

    /**
     *  Porter-Algorithmus-Stemming: weiter Arbeiten vom Step1b
     */


    private String restInStep1b(String word){
	
	int lw = word.length();
	char lastChar = word.charAt(lw-1);
	
	if(word.endsWith("at") || word.endsWith("bl") || word.endsWith("iz")){
	    word = word.concat("e");	    
	}else if(isDoubleEnd(word) &&
		 !(lastChar == 'l' || lastChar == 's' || lastChar == 'z')){
	    word = word.substring(0,lw-1);
	}else if(getM(word)==1 && vcv(word)){
	    word = word.concat("e");
	}
	return word;

    }

    /**
     *  Porter-Algorithmus-Stemming: Urteilen ueber '*o'
     */
    private boolean vcv(String word){
	int lw = word.length();
	
	if(lw < 3 || ! isConsonant(word,lw-3)
	   || isConsonant(word,lw-2) || isConsonant(word,lw-1)){
	    return false;
	}

	char lastChar = word.charAt(lw-1);

	if(lastChar == 'w' || lastChar == 'x' || lastChar == 'z' ) return false;

	return true;

    }
    /**
     *  Porter-Algorithmus-Stemming: Urteilen ueber den Ende, ob er doppel Buchstabe ist.
     */
    private boolean isDoubleEnd(String word){
	int lw = word.length();

	if(lw > 1){
	    if(word.charAt(lw-1) == word.charAt(lw-2) && isConsonant(word,lw-1)){
		return true;
	    }else{
		return false;
	    } 

	}else {
	    return false;
	}

    }
	    

    /**
     *  Porter-Algorithmus-Stemming: Wort Stemming
     */
    public String stemmingWord(String word){
	word = word.toLowerCase();
	word = step5b(step5a(step4(step3(step2(step1c(step1b(step1a(word))))))));
	return word;

    }

    
    /**
     *  Porter-Algorithmus-Stemming: String stemming
     */
    public String stemmingString(String context){
	ArrayList<Integer> spaceList = new ArrayList<Integer>();
	ArrayList<String> newWortList = new ArrayList<String>();

	int lastSpace = 0;
	String newContext = "";

	spaceList.add(0);
	while(context.endsWith(" ") || context.endsWith("\n")){
	    context = context.substring(0 , context.length()-1);
	}

	for(int i=1; i<context.length(); i++){
	    if(context.charAt(i) == ' ' || context.charAt(i) == ','
	       ||context.charAt(i) == ';'|| context.charAt(i)=='.'
	       || context.charAt(i)==':'){
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
    
    
}
