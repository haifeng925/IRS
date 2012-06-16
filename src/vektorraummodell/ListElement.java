package vektorraummodell;

/**
 * Diese Class packt die Dokument-ID und die Gewicht zusammen ein.
 *
 *
 * Created: Wed Jun 29 19:24:23 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class ListElement {

    /**
     * Creates a new <code>ListElement</code> instance.
     * @param doc,von Type <code>int</code>, ist Dokument-ID
     * @param weight,von Type <code>double</code>,das Gewicht des Termes von Dokument(Wdk). 
     *
     */
    public ListElement(int doc, double weight) {
        docNum = doc;
        weighting = weight;
    }
    

    /**
     * das Gewicht wird eingesetzt.
     *
     * @param w,Type <code>double</code>,
     */
    
    public void setWeighting (double w){
        weighting = w;
    }

    /**
     * addieren die Gewicht mit gegebendem Wert
     *
     * @param w, Type <code>double</code>,
     */
    public void addWeighting (double w){
        weighting += w;
    }

    
    /**
     * liefert den Gewicht zurueck.
     *
     * @return a <code>double</code> value
     */
    public double getWeight(){
        return weighting;
    }

    
    /**
     * liefert den Dokument-ID zurueck
     *
     * @return an <code>int</code> value
     */
    public int getDocNum(){
        return docNum;
    }

    
    /**
     * Die Dokument-ID wird eingesetzt
     *
     * @param d an <code>int</code> value
     */
    public void setDocNum(int d){
        docNum = d;
    }


    private int docNum;
    private double weighting;
}
