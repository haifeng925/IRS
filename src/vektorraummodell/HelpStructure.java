package vektorraummodell;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

/**
 * HelpStructure fuer Basisalgorithmus mit invertierten Listen
 *
 *
 * Created: Sat Jul  2 10:53:28 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class HelpStructure {

    /**
     * Creates a new <code>HelpStructure</code> instance.
     *
     */
    public HelpStructure() {
        helpList = new ArrayList<ListElement>();
    }

    
    /**
     * Ueberpruefen, prueft fuer eine gegebene Dokument-ID , ob sie in der HelpStructure enthalten.
     *
     * @param d eine Parameter von type <code>int</code> , geben Sie die ID von Dokument.
     * @return eine <code>boolean</code>
     */
    public boolean IsInDS(int d){
        for (int i = 0; i < helpList.size(); i++) {
            if(helpList.get(i).getDocNum()==d){
                return true;
            }
        }
        return false;
    }

    

    /**
     * fuegt ein Element in die Datenstruktur ein.
     *
     * @param d,von Type int, ist die Dokument-ID
     * @param w, von Type double, die Ergebniss von der Rechnung des Gewicht.
     */
    public void InsertIntoDS(int d,double w){
        ListElement le = new ListElement(d,w);
        helpList.add(le);
    }

    

    /**
     * fuegt zu dem bisher in der Dotenstructur verwalteten Gewicht fuer d den Wert w hinzu
     *
     * @param d an <code>int</code> value
     * @param w a <code>double</code> value
     */
    public void AddToDSEntry(int d,double w){
        int i=0;
        while (helpList.get(i).getDocNum()!=d) {
            i++;
        }
        helpList.get(i).addWeighting(w);

    }

    
    /**
     * <Code>GetWeightFromDS</code> liefert die Gewicht.
     *
     * @param d an <code>int</code> value
     * @return a <code>double</code> value
     */
    public double GetWeightFromDS(int d){
        int i=0;
        while (helpList.get(i).getDocNum()!=d) {
            i++;
        }

        return helpList.get(i).getWeight();
    }

    /**
     * lifert die ListeElement zurueck
     */
    public ArrayList<ListElement> getList(){
        return helpList;
    }

    
    /**
     * sortiere die HelpStructureList nach Gewicht.
     *
     */
    public void byWeightSort(){
        
        Comparator comp = new ListElementComparator();
        
        Collections.reverseOrder(comp);
        Collections.sort(helpList,comp);
    }


    
    private ArrayList<ListElement> helpList;

}
