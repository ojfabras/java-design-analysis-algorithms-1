/**
 * Class that represents a bi-dimensional square matrix.
 * @author <a href="mailto:ojfabras@gmail.com">Oscar Fabra</a>
 * @since 15/08/16
 */
public class SquareMatrix
{
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
        return strassensAlgorithm(x, y, x.n);
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
    private static SquareMatrix strassensAlgorithm(SquareMatrix x,
                                                   SquareMatrix y, int n)
    {
        return null;
    }
}
