/**
 * $Id: BubbleSort.java, v 1.0 04/09/16 18:02 oscarfabra Exp $
 * {@code BubbleSort} Class that sorts an array of numbers using bubble sort.
 *
 * @author <a href="mailto:ojfabras@gmail.com">Oscar Fabra</a>
 * @version 1.0
 * @since 04/09/16
 */

/**
 * Class that sorts an array of numbers using bubble sort.
 */
public class BubbleSort
{
    //-------------------------------------------------------------------------
    // CONSTRUCTOR
    //-------------------------------------------------------------------------

    // This class should not be instantiated
    private BubbleSort(){}

    //-------------------------------------------------------------------------
    // PUBLIC CLASS METHODS
    //-------------------------------------------------------------------------

    /**
     * Sorts the given array using bubble sort.
     * @param a Array of int numbers.
     */
    public static void sort(int[] a)
    {
        boolean swapped = true;
        int j = 0, temp;

        while (swapped)
        {
            swapped = false;
            j++;
            for (int i = 0; i < a.length - j; i++)
            {
                if (a[i] > a[i + 1])
                {
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }
}
