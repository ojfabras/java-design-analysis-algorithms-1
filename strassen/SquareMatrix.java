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
     * Sets the integer value of this SquareMatrix at (row,col).
     * @param row Row position, row in [0,1,...,n-1]
     * @param col Column position, col in [0,1,...,n-1]
     * @param value Value to set at (row,col).
     */
    public void set(int row, int col, int value)
    {
        this.table[row][col] = value;
    }

    /**
     * Gets the value at (row,col) of this SquareMatrix.
     * @param row Row position, row in [0,1,...,n-1]
     * @param col Column position, col in [0,1,...,n-1]
     * @return Value of this SquareMatrix at (row,col).
     */
    public int get(int row, int col)
    {
        return this.table[row][col];
    }

    /**
     * Returns a String with the corresponding values of this SquareMatrix.
     * @return A String with the values of this SquareMatrix to show in
     * standard output.
     */
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                buffer.append(this.table[i][j] + " ");
            }
            buffer.append('\n');
        }
        return buffer.toString();
    }

    //-------------------------------------------------------------------------
    // PRIVATE METHODS
    //-------------------------------------------------------------------------

    /**
     * Puts the values of the given SquareMatrix in the corresponding place of
     * this SquareMatrix.
     * @param a SquareMatrix whose values are going to be copied
     * @param subSquareMatrix Where to put the values? (see class constants)
     */
    private void putSubSquareMatrix(SquareMatrix a, int subSquareMatrix)
    {
        int row = 0;
        int col = 0;
        switch (subSquareMatrix)
        {
            case UPPER_LEFT_CORNER: row = 0; col = 0; break;
            case UPPER_RIGHT_CORNER: row = 0; col = a.n; break;
            case LOWER_LEFT_CORNER: row = a.n; col = 0; break;
            case LOWER_RIGHT_CORNER: row = a.n; col = a.n; break;
        }
        // Fills the sub-square matrix with the corresponding values of a
        for (int i = row; i < row + a.n; i++)
        {
            for (int j = col; j < col + a.n; j++)
            {
                this.table[i][j] = a.get(i - row, j - col);
            }
        }
    }

    //-------------------------------------------------------------------------
    // PUBLIC CLASS METHODS
    //-------------------------------------------------------------------------

    /**
     * Multiplies two given square matrices using Strassen's Sub-cubic
     * Multiplication Algorithm.
     * @param x First SquareMatrix.
     * @param y Second SquareMatrix.
     * @return The SquareMatrix product of x and y.
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
     * Recursively multiplies two given square matrices using Strassen's
     * Sub-cubic Multiplication Algorithm.
     * @param x First SquareMatrix.
     * @param y Second SquareMatrix.
     * @param n Size of a side of the SquareMatrices.
     * @return The SquareMatrix product of x and y.
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

        SquareMatrix a = getSubSquareMatrix(x, UPPER_LEFT_CORNER);
        SquareMatrix b = getSubSquareMatrix(x, UPPER_RIGHT_CORNER);
        SquareMatrix c = getSubSquareMatrix(x, LOWER_LEFT_CORNER);
        SquareMatrix d = getSubSquareMatrix(x, LOWER_RIGHT_CORNER);
        SquareMatrix e = getSubSquareMatrix(y, UPPER_LEFT_CORNER);
        SquareMatrix f = getSubSquareMatrix(y, UPPER_RIGHT_CORNER);
        SquareMatrix g = getSubSquareMatrix(y, LOWER_LEFT_CORNER);
        SquareMatrix h = getSubSquareMatrix(y, LOWER_RIGHT_CORNER);

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
     * Returns a SquareMatrix from the given SquareMatrix.
     * @param x The SquareMatrix to take the sub-square matrix from
     * @param subSquareMatrix Which square Matrix? (see class constants)
     * @return The corresponding SquareMatrix.
     */
    private static SquareMatrix getSubSquareMatrix(SquareMatrix x, int subSquareMatrix)
    {
        int row = 0;
        int col = 0;
        int r = x.n % 2;
        switch (subSquareMatrix)
        {
            case UPPER_LEFT_CORNER: row = 0; col = 0; break;
            case UPPER_RIGHT_CORNER: row = 0; col = x.n/2 + r; break;
            case LOWER_LEFT_CORNER: row = x.n/2 + r; col = 0; break;
            case LOWER_RIGHT_CORNER: row = x.n/2 + r; col = x.n/2 + r; break;
        }

        SquareMatrix m = new SquareMatrix(x.n/2 + r);
        for (int i = row; i < row + x.n/2 + r; i++)
        {
            for (int j = col; j < col + x.n/2 + r; j++)
            {
                m.set(i - row, j - col, x.get(i, j));
            }
        }
        return m;
    }

    /**
     * Adds the two given SquareMatrices.
     * @param x First SquareMatrix.
     * @param y Second SquareMatrix.
     * @return The resulting SquareMatrix.
     */
    private static SquareMatrix add(SquareMatrix x, SquareMatrix y)
    {
        SquareMatrix z = new SquareMatrix(x.n);
        for (int i = 0; i < x.n; i++)
        {
            for (int j = 0; j < x.n; j++)
            {
                z.set(i, j, x.get(i,j) + y.get(i,j));
            }
        }
        return z;
    }

    /**
     * Subtracts the second SquareMatrix from the first SquareMatrix.
     * @param x First SquareMatrix.
     * @param y Second SquareMatrix.
     * @return The resulting SquareMatrix.
     */
    private static SquareMatrix subtract(SquareMatrix x, SquareMatrix y)
    {
        SquareMatrix z = new SquareMatrix(x.n);
        for (int i = 0; i < x.n; i++)
        {
            for (int j = 0; j < x.n; j++)
            {
                z.set(i, j, x.get(i,j) - y.get(i,j));
            }
        }
        return z;
    }

    /**
     * Computes the 7 corresponding products according to Strassen's Algorithm.
     * @param p1 SquareMatrix resulting from the first product.
     * @param p2 SquareMatrix resulting from the first product.
     * @param p3 SquareMatrix resulting from the first product.
     * @param p4 SquareMatrix resulting from the first product.
     * @param p5 SquareMatrix resulting from the first product.
     * @param p6 SquareMatrix resulting from the first product.
     * @param p7 SquareMatrix resulting from the first product.
     * @param n Size of a side of the resulting SquareMatrix.
     * @return The adequate product of matrices x and y.
     */
    private static SquareMatrix computeProducts(SquareMatrix p1, SquareMatrix p2,
                                                SquareMatrix p3, SquareMatrix p4,
                                                SquareMatrix p5, SquareMatrix p6,
                                                SquareMatrix p7, int n)
    {
        SquareMatrix z = new SquareMatrix(n);
        z.putSubSquareMatrix(add(subtract(add(p5,p4),p2),p6), UPPER_LEFT_CORNER);
        z.putSubSquareMatrix(add(p1,p2), UPPER_RIGHT_CORNER);
        z.putSubSquareMatrix(add(p3,p4), LOWER_LEFT_CORNER);
        z.putSubSquareMatrix(subtract(subtract(add(p1,p5),p3),p7), LOWER_RIGHT_CORNER);
        return z;
    }
}
