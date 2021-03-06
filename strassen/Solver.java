import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Class that reads and solves a file with two square matrices using Strassen's
 * Sub-cubic Matrix Multiplication Algorithm.
 *
 * @author <a href="mailto:ojfabras@gmail.com">Oscar Fabra</a>
 * @version 1.0
 * @since 15/08/16
 */
public class Solver
{
    //-------------------------------------------------------------------------
    // CLASS METHODS
    //-------------------------------------------------------------------------

    /**
     * Reads the lines from the standard input and arranges them in a List.
     * @param args Array of String with the filepath of the file to read.
     * @return A List of lines with the data for the problem.
     * @throws java.io.FileNotFoundException If the file couldn't be found.
     */
    private static List<String> readLines(String[] args) throws FileNotFoundException
    {
        List<String> lines = new Vector<String>();
        String filename = null;

        // Get the file name
        for (String arg : args)
        {
            if (arg.startsWith("-file="))
            {
                filename = arg.substring(6);
            }
        }
        if (filename == null) { return null; }

        // Reads the lines out of the file
        BufferedReader input = new BufferedReader(new FileReader(filename));
        String line = null;
        try {
            while((line = input.readLine()) != null)
            {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the lines read
        return lines;
    }

    /**
     * Solves the instance and prints the solution in standard output.
     * @param lines Input list with the variables of the problem.
     */
    private static void solve(List<String> lines)
    {
        // Gets the size of the square matrices
        int n = Integer.parseInt(lines.remove(0));

        // Reads the first matrix
        SquareMatrix x = new SquareMatrix(n);
        readSquareMatrix(x, 0, n, lines);

        // Reads the second matrix
        SquareMatrix y = new SquareMatrix(n);
        readSquareMatrix(y, n, n, lines);

        // Multiplies the two square matrices using Strassen's algorithm
        SquareMatrix z = SquareMatrix.strassensMultiplication(x, y);

        // Prints the product in standard output
        System.out.println(z.toString());
    }

    /**
     * Reads the corresponding list of lines and stores them in the given
     * SquareMatrix.
     * @param m SquareMatrix object to put the values into.
     * @param lb Initial row of lines to read.
     * @param n Size of the side of the SquareMatrix.
     * @param lines Input list with the variables of the problem.
     */
    private static void readSquareMatrix(SquareMatrix m, int lb, int n,
                                         List<String> lines)
    {
        for (int i = lb; i < lb + n; i++)
        {
            String line = lines.get(i);
            String [] row = line.split("\\s+");
            for (int j = 0; j < n; j++)
            {
                m.set(i - lb, j, Integer.parseInt(row[j]));
            }
        }
    }

    //-------------------------------------------------------------------------
    // MAIN
    //-------------------------------------------------------------------------

    /**
     * Main test method.
     * @param args filepath relative to the file with the list of numbers, in
     *             the form -file=filepath
     */
    public static void main(String [] args)
    {
        try
        {
            List<String> lines = null;
            lines = Solver.readLines(args); // Reads the lines from the file
            Solver.solve(lines);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
