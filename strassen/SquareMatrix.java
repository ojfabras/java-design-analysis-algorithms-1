/**
 * Class that represents a bi-dimensional square matrix.
 * @author <a href="mailto:ojfabras@gmail.com">Oscar Fabra</a>
 * @since 15/08/16
 */
public class SquareMatrix
{
    //-------------------------------------------------------------------------
    // CONSTANTS
    //-------------------------------------------------------------------------

    private static final int UPPER_LEFT_CORNER = 1;
    private static final int UPPER_RIGHT_CORNER = 2;
    private static final int LOWER_LEFT_CORNER = 3;
    private static final int LOWER_RIGHT_CORNER = 4;

    //-------------------------------------------------------------------------
    // ATTRIBUTES
    //-------------------------------------------------------------------------

    private int [][] table;     // Integer matrix in which to store the values.
    private int n;              // Size of a side of the SquareMatrix.

    //-------------------------------------------------------------------------
    // CONSTRUCTOR
    //-------------------------------------------------------------------------

    /**
     * Creates a new SquareMatrix.
     * @param n Size of a side of the SquareMatrix.
     */
    public SquareMatrix(int n)
    {
        this.table = new int[n][n];
        this.n = n;
    }

    //-------------------------------------------------------------------------
    // PUBLIC METHODS
    //-------------------------------------------------------------------------

    /**
     *
     * @param row
     * @param col
     * @param value
     */
    public void set(int row, int col, int value)
    {
        this.table[row][col] = value;
    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    public int get(int row, int col)
    {
        return this.table[row][col];
    }

    //-------------------------------------------------------------------------
    // PUBLIC CLASS METHODS
    //-------------------------------------------------------------------------

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static SquareMatrix strassensMultiplication(SquareMatrix x,
                                                       SquareMatrix y)
    {
        return strassensMultiplication(x, y, x.n);
    }

    //-------------------------------------------------------------------------
    // PRIVATE CLASS METHODS
    //-------------------------------------------------------------------------

    /**
     *
     * @param x
     * @param y
     * @param n
     * @return
     */
    private static SquareMatrix strassensMultiplication(SquareMatrix x,
                                                        SquareMatrix y, int n)
    {
        if(n == 2)
        {
            SquareMatrix z = new SquareMatrix(n);
            z.set(0, 0, x.get(0,0) * y.get(0,0) + x.get(0,1) * y.get(1,0));
            z.set(0, 1, x.get(0,0) * y.get(0,1) + x.get(0,1) * y.get(1,1));
            z.set(1, 0, x.get(1,0) * y.get(0,0) + x.get(1,1) * y.get(1,0));
            z.set(1, 1, x.get(1,0) * y.get(0,1) + x.get(1,1) * y.get(1,1));
            return z;
        }

        SquareMatrix a = subSquareMatrix(x, UPPER_LEFT_CORNER);
        SquareMatrix b = subSquareMatrix(x, UPPER_RIGHT_CORNER);
        SquareMatrix c = subSquareMatrix(x, LOWER_LEFT_CORNER);
        SquareMatrix d = subSquareMatrix(x, LOWER_RIGHT_CORNER);
        SquareMatrix e = subSquareMatrix(y, UPPER_LEFT_CORNER);
        SquareMatrix f = subSquareMatrix(y, UPPER_RIGHT_CORNER);
        SquareMatrix g = subSquareMatrix(y, LOWER_LEFT_CORNER);
        SquareMatrix h = subSquareMatrix(y, LOWER_RIGHT_CORNER);

        SquareMatrix p1 = strassensMultiplication(a, subtract(f,h), n/2);
        SquareMatrix p2 = strassensMultiplication(add(a,b), h, n/2);
        SquareMatrix p3 = strassensMultiplication(add(c,d), e, n/2);
        SquareMatrix p4 = strassensMultiplication(d, subtract(g,e), n/2);
        SquareMatrix p5 = strassensMultiplication(add(a,d), add(e,h), n/2);
        SquareMatrix p6 = strassensMultiplication(subtract(b,d), add(g,h), n/2);
        SquareMatrix p7 = strassensMultiplication(subtract(a,c), add(e,f), n/2);

        return computeProducts(p1, p2, p3, p4, p5, p6, p7, n);
    }

    /**
     *
     * @param x
     * @param subSquareMatrix
     * @return
     */
    private static SquareMatrix subSquareMatrix(SquareMatrix x, int subSquareMatrix)
    {
        // TODO: Get subSquareMatrix
        return null;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    private static SquareMatrix add(SquareMatrix x, SquareMatrix y)
    {
        // TODO: Add matrices
        return null;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    private static SquareMatrix subtract(SquareMatrix x, SquareMatrix y)
    {
        // TODO: Subtract matrices
        return null;
    }

    /**
     * 
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @param p5
     * @param p6
     * @param p7
     * @param n
     * @return
     */
    private static SquareMatrix computeProducts(SquareMatrix p1, SquareMatrix p2,
                                                SquareMatrix p3, SquareMatrix p4,
                                                SquareMatrix p5, SquareMatrix p6,
                                                SquareMatrix p7, int n)
    {
        // TODO: Compute products
        return null;
    }
}
