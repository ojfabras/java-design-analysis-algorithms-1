/**
 * $Id: MergeSort.java, v 1.0 09/08/16 16:02 oscarfabra Exp $
 * {@code MergeSort} Class that sorts an array of numbers using mergesort.
 *
 * @author <a href="mailto:ojfabras@gmail.com">Oscar Fabra</a>
 * @version 1.0
 * @since 09/08/16
 */

/**
 * Class that sorts an array of numbers using mergesort.
 */
public class MergeSort
{
    //-------------------------------------------------------------------------
    // PUBLIC CLASS METHODS
    //-------------------------------------------------------------------------

    /**
     * Sorts the given array using mergesort.
     * @param a Array of int numbers.
     */
    public static void sort(int[] a)
    {
        sort(a, a.length);
    }

    /**
     * Prints the elements of the given array in standard output.
     * @param a Array of int numbers.
     */
    public static void show(int[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    //-------------------------------------------------------------------------
    // PRIVATE HELPER METHODS
    //-------------------------------------------------------------------------

    /**
     *
     * @param a
     * @param n
     */
    private static void sort(int[] a, int n)
    {
        if (n == 1) { return; }
        int m = (n % 2 == 0)? n / 2 : (n / 2) + 1;
        int [] b = subArray(a, 0, m );
        sort(b, b.length);
        int [] c = subArray(a, m, n);
        sort(c, c.length);
        merge(a, b, c, m, n);
    }

    /**
     *
     * @param a
     * @param lb
     * @param ub
     * @return
     */
    private static int[] subArray(int[] a, int lb, int ub)
    {
        int [] b = new int[ub - lb];
        for (int i = lb; i < ub; i++)
        {
            b[i - lb] = a[i];
        }
        return b;
    }

    /**
     *  @param a
     * @param b
     * @param c
     * @param m
     * @param n
     */
    private static void merge(int[] a, int[] b, int[] c, int m, int n)
    {
        int i = 0, j = 0;
        for (int k = 0; k < n; k++)
        {
            if(i == m)
            {
                a[k] = c[j];
                j++;
            }
            else if (j == n - m)
            {
                a[k] = b[i];
                i++;
            }
            else if (b[i] <= c[j])
            {
                a[k] = b[i];
                i++;
            }
            else
            {
                a[k] = c[j];
                j++;
            }
        }
    }

}
