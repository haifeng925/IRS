package vektorraummodell;

/**
 * TopDocs Liste
 *
 *
 * Created: Sat Jul  2 13:33:20 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class TopDocs {

    /**
     * Creates a new <code>TopDocs</code> instance.
     *
     */
    public TopDocs(int n) {
        r = n;
        count = 0;
        topDocsList = new ListElement[r+1];
    }


    
    /**
     * erst r Element im HelpStructure wird im TopDocs gespeichert 
     *
     * @param hs a <code>HelpStructure</code> value
     */
    public void setTopDocs(HelpStructure hs){
        count = 0;
        for (int i = 0; (i < r+1) && (i < hs.getList().size()); i++) {
 
            int dnum = hs.getList().get(i).getDocNum();
            topDocsList[i] = new ListElement(dnum,hs.getList().get(i).getWeight());
            if(count<r+1){
                count++;
            }
            
        }

    }
   
    
    /**
     * liefert den Wert von r zurueck
     *
     * @return an <code>int</code> value
     */
    public int getR(){
        return r;
    }
    
    /**
     * die Elementes in List wird zurueckgeliefert
     *
     * @return a <code>ListElement[]</code> value
     */
    public ListElement[] getTopDocsList(){
        
        return topDocsList;
    }

    
    /**
     * lifert den Dokument-ID von k. Element im TopDocs
     *
     * @param k an <code>int</code> value
     * @return an <code>int</code> value
     */
    public int getDocNum(int k){
        return topDocsList[k].getDocNum();
    }

    /**
     * lifert den Gewicht von i. Element im TooDocs
     *
     * @param i an <code>int</code> value
     * @return a <code>double</code> value
     */
    public double getRang(int i){
        return topDocsList[i].getWeight();
    }

    
    /**
     * liefert den Counter zurueck, die zeigt das Anzahl des Element, die TopDocsList enthaltet.
     *
     * @return an <code>int</code> value
     */
    public int getCount(){
        return count;
    }
    
    private int r=0;
    private int count=0;
    private ListElement[] topDocsList;
}
