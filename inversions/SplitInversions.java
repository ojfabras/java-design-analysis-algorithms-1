/**
 * Class to count the split inversions of a given array of integers.
 *
 * @author <a href="mailto:ojfabras@gmail.com">Oscar Fabra</a>
 * @version 1.0
 * @since 13/08/16
 */
public class SplitInversions
{
    //-------------------------------------------------------------------------
    // CONSTRUCTOR
    //-------------------------------------------------------------------------

    // This class should not be instantiated
    private SplitInversions(){}

    //-------------------------------------------------------------------------
    // PUBLIC CLASS METHOD
    //-------------------------------------------------------------------------

    /**
     * Sorts the given array a calculating the number of split inversions.
     * @param a Array of int numbers.
     * @return Number of split inversions.
     */
    public static long sortAndCount(int[] a)
    {
        return sortAndCount(a, a.length);
    }

    //-------------------------------------------------------------------------
    // PRIVATE HELPER METHODS
    //-------------------------------------------------------------------------

    /**
     * Sorts the given array a calculating the number of split inversions.
     * @param a Array of int numbers.
     * @param n Size of the array a.
     * @return Number of split inversions.
     */
    private static long sortAndCount(int[] a, int n)
    {
        if(n == 1) { return 0; }
        int m = (n % 2 == 0) ? n / 2 : n / 2 + 1;
        int [] b = subArray(a, 0, m );
        long x = sortAndCount(b, b.length);
        int [] c = subArray(a, m, n);
        long y = sortAndCount(c, c.length);
        long z = mergeAndCountSplitInversions(a, b, c, m, n);
        return x + y + z;
    }

    /**
     * Obtains a sub-array from array a between indices [lb...ub-1]
     * @param a Array of int numbers.
     * @param lb Lower bound index.
     * @param ub Upper bound index.
     * @return Sub-array of a within indices [lb...ub-1]
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
     * Merges the two sorted arrays b and c into a and counts the number of
     * split inversions.
     * @param a Array of int numbers of size n.
     * @param b Array of int numbers of size m.
     * @param c Array of int numbers of size n - m.
     * @param m Middle index of a, or size of array b.
     * @param n Size of array a, or sum of sizes of b and c.
     * @return Number of split inversions found.
     */
    private static long mergeAndCountSplitInversions(int[] a, int[] b, int[] c, int m, int n)
    {
        int i = 0, j = 0, inversions = 0;
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
                inversions += m - i;
                a[k] = c[j];
                j++;
            }
        }
        return inversions;
    }
}
