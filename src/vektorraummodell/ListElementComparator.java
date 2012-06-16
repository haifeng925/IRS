package vektorraummodell;


import java.util.Comparator;

/**
 * Describe class ListElementComparator here.
 *
 *
 * Created: Sat Jul  2 11:34:14 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class ListElementComparator implements Comparator {

    /**
     * Creates a new <code>ListElementComparator</code> instance.
     *
     */
    public ListElementComparator() {

    }

    // Implementation of java.util.Comparator

    /**
     * Describe <code>equals</code> method here.
     *
     * @param object an <code>Object</code> value
     * @return a <code>boolean</code> value
     */
    public final boolean equals(final Object object) {
        return false;
    }

    /**
     * Describe <code>compare</code> method here.
     *
     * @param object an <code>Object</code> value
     * @param object1 an <code>Object</code> value
     * @return an <code>int</code> value
     */
    public final int compare(final Object object, final Object object1) {
        ListElement le = (ListElement) object;
        ListElement le1 = (ListElement) object1;

        if(le.getWeight() < le1.getWeight()){
            return 1;
        }else {

            return 0;
        }
    }

 
}
