package vektorraummodell;

import java.util.ArrayList;

/**
 * packt das Term und eine List von Type ListElement zusammen
 *
 *
 * Created: Wed Jun 29 19:53:58 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class InverEntity {

    /**
     * Creates a new <code>InverEntity</code> instance.
     *
     */
    public InverEntity(String keyword) {
        term = keyword;
        listEntity = new ArrayList<ListElement>();
        index = 0;
        wqk = 0;
    }
 
    /**
     * <code>insertElement</code> fuegt ein ListElement in entityList ein
     *
     * @param le a <code>ListElement</code> value
     */
    public void addElement(ListElement le) {
        listEntity.add(le);
    }

    
    /**
     * index = 0;
     *
     */
    public void resetIndex(){
        index = 0;
    }

    
    /**
     * liefert das Term zurueck
     *
     * @return a <code>String</code> value
     */
    public String getTerm(){
        return term;
    }

    /**
     * liefert eine List zurueck,die von Type ListElement 
     */
    public ArrayList<ListElement> getEntityList(){
        return listEntity;
    }
    
    /**
     * setzt wqk von Anfrageterm  
     *
     * @param w a <code>double</code> value
     */
    public void setWqk(double w){
        wqk = w;
    }
    
    /**
     * liefert wqk zurueck
     *
     * @return a <code>double</code> value
     */
    public double getWqk(){
        return wqk;
    }

    public boolean NotAtEndOfList(){
        if(index<listEntity.size()){
            return true;
        }else {
            return false;
        }

    }
        
    /**
     * liefert das aktuelle Paar (D,Wdk),und setzt den den Durchlaufzeiger 
     *
     * @return a <code>ListElement</code> value
     */
    public ListElement GetNextElementOfList(){
        ListElement le = listEntity.get(index);
        index++;
        return le;
    }
    
    private int index;
    private String term;
    private double wqk;
    private ArrayList<ListElement> listEntity;

}
