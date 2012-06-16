package boolesch;
import java.io.*;
import java.util.*;
/**
 * Describe class Signatur here.
 *
 *
 * Created: Thu Jun 16 10:19:38 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class Signatur {

    /**
     * Creates a new <code>Signatur</code> instance.
     *
     */

    public Signatur(String wd) {
        word = wd;
        int f = 64;
        int d = 4;
        signatur = new BitSet(f);

        int m = getM(f,d);
        for(int i=1; i<=m ; i++){
            int pos = hashfun(wd,i,f);
            // System.out.println(pos);
            signatur.set(pos);
        }

    }

    
    
    public Signatur(String wd,int f,int d){
        signatur = new BitSet(f);
        word = wd;
        int m = getM(f,d);
       
        for(int i=1; i<=m ; i++){
            int pos = hashfun(wd,i,f);
            // System.out.println(pos);
            signatur.set(pos);
        }
    }

    public static int getM (int f, int d){
        int rst =(int) ( f * Math.log(2) /d);
        return rst;
    }

    public static void primInit(){
        int count=1;
        int number = 3;
        primeNumber[0] = 2;
        while(count<100){

            if(isPrimeNum(number)){
                primeNumber[count] = number;
                count ++;
            }

            number += 2;
        }

    }

    public static boolean isPrimeNum(int num){
        int x = (int)Math.sqrt(num);
        for(int i=3;i<=x;i++){
            if(num%i == 0)
                return false;
        }

        return true;

    }

    public int getP(int i){
        return primeNumber[i];
    }
    
    public int hashfun(String wd, int i,int f){
        double hashval = getP(i+1);
        for(int n=0; n<wd.length();n++){
            hashval = (hashval + wd.charAt(n)) * getP(i);
        }

        hashval = hashval%f;
        return (int)hashval;
    }

    /**
     *
     *geben die Signatur zurueck
     */
    public BitSet getSig(){
        return signatur;
    }
    
    /**
     *
     *geben das Wort zurueck
     */
    public String getWord(){
        return word;
    }

        
    /**
     *
     *rufen die or() Funktion von class BitSet auf
     */
    public void or(BitSet bs){
        signatur.or(bs);
    }


        
    /**
     *
     *rufen die and() Funktion von class BitSet auf
     */
    public void and(BitSet bs){
        signatur.and(bs);
    }
     
    /**
     *
     *ersetzen das Schlussword
     */
    public void setWord(String wd){
        word = wd;
    }



    private String word;
    private BitSet signatur;
    private static int[] primeNumber = new int[100];


}
