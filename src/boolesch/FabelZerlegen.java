package boolesch;

import java.io.*;
import java.util.*;


/**
 * Describe class DocOpt here.
 *
 *
 * Created: Mon May 16 21:22:47 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0


 */
public class FabelZerlegen {

    /**
     * Creates a new <code>DocOpt</code> instance.
     *
     */
    public FabelZerlegen(String fp) {
	filePath = fp;
	
    }

    public FabelZerlegen(){
	filePath = "../aesopa10.txt";
    }

    public void orgDocRead(){

	//	String lineText = null;aesopa10
	//	aesopa10
	    try{
		Reader reader = new FileReader(filePath);
		
		BufferedReader bfReader = new BufferedReader(reader);
		String textLine = null;
	    
		//um die Hinweise zu ignorieren
		blankLineIdent(3,bfReader);
		blankLineIdent(3,bfReader);


                
		// textLine = bfReader.readLine();
		// while(textLine != null){

		// 	System.out.println(textLine);
		// 	textLine = bfReader.readLine();
		// }
		int ct = 1;
		do{
		    //	blankLineIdent(2,bfReader);
		    saveInFile(bfReader);
		    //  System.out.println(ct++);
		
		}while(isEnd == false);

		bfReader.close();
		reader.close();
	    }catch (Exception e){

		e.printStackTrace();
	    }
	
    }

    public void saveInFile (BufferedReader bf){
	// title 
	String fileName = titleRead(bf);
	if(fileName != null){

	    // System.out.println(fileName);
	    blankLineIdent(2,bf);
	    fileName = "../doc/zl/"+fileName+".txt";
	    String textInLine = null;
	    //
	    try{

		File newFile = new File(fileName);
		if(!newFile.exists()){
		    newFile.createNewFile();
		}
		Writer fw = new FileWriter(newFile);
		BufferedWriter bw = new BufferedWriter(fw);
		int blankLineCount = 0;
		do{
		    
		    textInLine = bf.readLine();
		    if(textInLine == null){
			isEnd = true;
			break;
		    }

		    if(textInLine.length()==0){
			blankLineCount++;
			bw.newLine();
		    }else{
			blankLineCount = 0;
			bw.write(textInLine);
			bw.newLine();
						
	       
		    }
		   
		
		}while(blankLineCount < 3);
	    

		bw.flush();
		bw.close();
		fw.close();

	    }catch(Exception e){

		e.printStackTrace();
	    }
	}
	
    }

    
    public void blankLineIdent(int numOfLine,BufferedReader bf){
	String line = null;
	int count = 0;
	try{
	    do{
		line = bf.readLine();
		if(line.length()==0){
		    count++;
		}else{
		    count = 0;
		}
	    }while(count < numOfLine && line != null);
	    if(line == null){
		isEnd = true;
	    }
	    
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    public String titleRead(BufferedReader bf){

	String title = null;
	try{
	    
	    title = bf.readLine();
	    if(title == null){
		isEnd = true;
	    }
	    //entfernt die erst 3 Leerzeichen
	    title = title.substring(2,title.length());
	    //zu klein
	    title = title.toLowerCase();
	    //ersetzen die Leerzeichen durch Unterstrich
	    title = title.replace(' ' , '_');

	
	}catch(Exception e){
	    e.printStackTrace();
	}

	return title;
    }


    private static String filePath;
    private static boolean isEnd = false;

}
