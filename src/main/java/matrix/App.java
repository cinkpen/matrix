package matrix;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;

/**
 * Main class for the application. Will parse the command line arguments, read
 * in the input data, calculate the results and print them out.
 */
public class App
{
  /**
   * Main application entry point. Writes the resulting matrix to standard output.
   * Specify the count and length via the -c and -n command line arguments, respectively.
   *
   * @param args command line arguments
   */
  public static void main(String[] args)
  {
    try
    {
      parseArguments(args);
      checkArguments();

      float[] input = parseInputFile(inputFilePath);
      sanitizeInputData(input);

      MatrixGenerator generator = unoptimized ? new UnoptimizedMatrixGenerator()
                                              : new OptimizedMatrixGenerator();

      float[][] result = generator.generate(input, count);
      printMatrix(System.out, result, NUM_FRACTION_DIGITS);
    }
    catch (Exception e)
    {
      fail(ERROR_UNEXPECTED, e.getLocalizedMessage());
    }
  }

  /**
   * Parse the command line arguments, and set up the parameters
   * for the computation such as the input file, and count. For
   * testing purposes, the -u switch allows an unoptimized version
   * of the algorithm to be used for comparision against the optimized
   * one.
   *
   * @param args the command line argument array
   */
  private static void parseArguments(String[] args)
  {
    int i = 0;

    while (i < args.length)
    {
      switch (args[i])
      {
        case "-c":
        {
          parseCountArgument(args[++i]);
          break;
        }
        case "-n":
        {
          parseLengthArgument(args[++i]);
          break;
        }
        case "-f":
        {
          inputFilePath = Paths.get(args[++i]);
          break;
        }
        case "-u":
        {
          unoptimized = true;
          break;
        }
        default:
        {
          System.out.println("Unknown command line argument: " + args[i]);
          usage();
          break;
        }
      }

      i++;
    }
  }

  /**
   * Sanity check the arguments specified on the command line.
   */
  private static void checkArguments()
  {
    if (count == -1)
    {
      fail(ERROR_INVALID_ARGUMENT, "Count argument not specified.");
    }

    if (length == -1)
    {
      fail(ERROR_INVALID_ARGUMENT, "No input length specified");
    }

    if (inputFilePath == null)
    {
      fail(ERROR_INVALID_ARGUMENT, "No input file path specified.");
    }

    if (!Files.exists(inputFilePath))
    {
      fail(ERROR_INVALID_ARGUMENT, String.format("Input file does not exist: %s.", inputFilePath.toAbsolutePath()));
    }
  }

  /**
   * Parse the count value from the command line arguments.
   * @param arg the string specified on the command line for the -c parameter.
   */
  private static void parseCountArgument(String arg)
  {
    try
    {
      count = Integer.parseInt(arg);

      if (count <= 0)
      {
        fail(ERROR_INVALID_ARGUMENT, "Count argument must be positive");
      }
    }
    catch (NumberFormatException e)
    {
      fail(ERROR_INVALID_ARGUMENT, "Count argument is not a valid number: " + arg);
    }
  }

  /**
   * Parse the length value from the command line arguments.
   * @param arg the string specified on the command line for the -n parameter.
   */
  private static void parseLengthArgument(String arg)
  {
    try
    {
      length = Integer.parseInt(arg);

      if (length <= 0)
      {
        fail(ERROR_INVALID_ARGUMENT, "Length argument must be positive");
      }
    }
    catch (NumberFormatException e)
    {
      fail(ERROR_INVALID_ARGUMENT, "Length argument is not a valid number: " + arg);
    }
  }

  /**
   * Read in the input file and parse the contents, returning an array of
   * float values parsed from the file.
   *
   * @param path the path to the file containing the input data
   *
   * @return an array of float values parsed from the file.
   */
  private static float[] parseInputFile(Path path) throws IOException
  {
    String[] lines = Files.lines(path).limit(length).toArray(String[]::new);

    if (length > lines.length)
    {
      fail(ERROR_INVALID_INPUT_DATA, String.format("Not enough input values for length %d", length));
    }

    float[] input = new float[length];

    try
    {
      for (int i = 0; i < length; i++)
      {
        input[i] = Float.parseFloat(lines[i]);
      }
    }
    catch (NumberFormatException e)
    {
      fail(ERROR_INVALID_INPUT_DATA, "Invalid value in input file: " + e.getLocalizedMessage());
    }

    return input;
  }

  /**
   * Sanity check that the values parsed from the input array are suitable.
   *
   * @param input the array of input data
   */
  private static void sanitizeInputData(float[] input)
  {
    for (float f : input)
    {
      if (Float.isInfinite(f) || Float.isNaN(f))
      {
        fail(ERROR_INVALID_INPUT_DATA, "Invalid input data in data file: " + Float.toString(f));
      }
    }
  }

  /**
   * Print the values of the matrix with the specified number of fractional
   * digits.
   *
   * @param out a PrintStream instance to write the output to.
   * @param matrix the matrix values to be printed.
   * @param digits the number of fractional digits to print in the output
   */
  private static void printMatrix(PrintStream out, float[][] matrix, int digits)
  {
    NumberFormat format = NumberFormat.getInstance();

    format.setMinimumFractionDigits(digits);
    format.setMaximumFractionDigits(digits);

    for (int i = 0; i < matrix.length; i++)
    {
      for (int j = 0; j < matrix[i].length; j++ )
      {
        out.print(format.format(matrix[i][j]));

        if (j < matrix[i].length - 1)
        {
          out.print(' ');
        }
      }

      out.println();
    }
  }

  /**
   * Print command line usage to the console.
   */
  private static void usage()
  {
    System.out.println("Usage: java -jar matrix.jar -c 4 -n 300 -f test.prn");
    System.out.println("\t-c <count>");
    System.out.println("\t-n <input length>");
    System.out.println("\t-f <input file path>");
    System.out.println();
  }

  /**
   * Terminate the appliation on an unrecoverable error with a specified exit code.
   *
   * @param status an exit status integer
   * @param msg an error to be written to the std error stream.
   */
  private static void fail(int status, String msg)
  {
    System.err.println(msg);
    System.exit(status);
  }

  private static int count = -1;
  private static int length = -1;
  private static Path inputFilePath;
  private static boolean unoptimized;

  private static final int NUM_FRACTION_DIGITS = 6;

  // Error exit codes
  private static final int ERROR_UNEXPECTED = -1;
  private static final int ERROR_INVALID_ARGUMENT = 1;
  private static final int ERROR_INVALID_INPUT_DATA = 2;
}
