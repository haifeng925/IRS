package vektorraummodell;

import java.util.Comparator;

/**
 * Describe class ListEntityComparator here.
 *
 *
 * Created: Sat Jul  2 00:17:22 2011
 *
 * @author <a href="mailto:haifeng925@gmail.com">Haifeng Gao</a>
 * @version 1.0
 */
public class ListEntityComparator implements Comparator {

    /**
     * Creates a new <code>ListEntityComparator</code> instance.
     *
     */
    public ListEntityComparator() {

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
        InverEntity ie =(InverEntity) object;
        InverEntity ie1 = (InverEntity) object1;

        if(ie.getWqk()<ie1.getWqk()){
            return 1;
        }else {
            return 0;
        }

    }
 
}
