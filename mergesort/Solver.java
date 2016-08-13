import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Class that reads and solves a file with the parameters for the array to
 * sort in ascending order using mergesort.
 *
 * @author <a href="mailto:ojfabras@gmail.com">Oscar Fabra</a>
 * @version 1.0
 * @since 09/08/16
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
        // Reads the numbers from the List and stores them in an array
        int [] a = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++)
        {
            a[i] = Integer.parseInt(lines.get(i));
        }
        // Sort the array and show it in standard output using MergeSort
        MergeSort.sort(a);
        MergeSort.show(a);
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
