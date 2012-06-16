package boolesch;

import java.io.*;

/**
 * Describe class FilesOpt here.
 *
 *
 * Created: Sun May 22 21:16:18 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @author <a href=“mailto:berthold.lee@gmail.com">Bo Li</a>
 * @version 1.0
 */
public class FilesOpt {

    

    public FilesOpt() {

	path = "../doc/zl";

    }

    public FilesOpt(String pathname){
	path = pathname;
	
    }

    /**
     * 
     *liefern eine Array von Files zurueck
     */
    public  File[] getFiles(){
	File f = new File(path);
	if(f.isDirectory()){
	    fList = f.listFiles();    
	}
	return fList;
    }
    

    private static String path;
    private static File[] fList;

}
