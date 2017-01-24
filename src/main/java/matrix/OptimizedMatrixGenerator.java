package matrix;

/**
 * A matrix generator implementation that provides some performance optimizations over
 * the unoptimized version. This uses the fact that several cells in the matrix rely
 * on performing numerous additions and multiplies in common with other cells. This
 * attempts to minimize the number of multiplies and adds of the same values.
 */
public class OptimizedMatrixGenerator implements MatrixGenerator
{
  /**
   * Generate the values contained in the matrix by working through the cells diagonally
   * from bottom left to top right.
   *
   * @param input an array of floating point values
   * @param count a count used to control the size of the matrix
   *
   * @return a two dimensional array of floats, representing the values in the matrix.
   */
  @Override
  public float[][] generate(float[] input, int count)
  {
    float[][] matrix = new float[count+1][count+1];

    // Note that the following loops could potentially be run in parallel across
    // several cores to speed things up further...

    for (int row = count; row >= 0; row--)
    {
      // Starting from bottom left, and moving up, generate the values in these cells
      // diagonally down towards the bottom right...

      // *
      // * *
      // * * *
      // * * * *
      // * * * * *

      calculateDiagonalCells(row, 0, input, count, matrix);
    }

    for (int col = 1; col <= count; col++)
    {
      // .. then diagonally generate these towards the right.

      //    * * * *
      //      * * *
      //        * *
      //          *
      //

      calculateDiagonalCells(0, col, input, count, matrix);
    }

    return matrix;
  }

  /**
   * Generate the cells for a single diagonal in the matrix. The implmentation here makes
   * use of two properties of the algorithm: 1) the value for cells across the diagonal are
   * based on multiplication of many of the same numbers, and 2) the values across the diagonal
   * also add many of the same values for each cell. This method tries to compute the common values
   * once for the diagonal, and then when looping through the cells, just compute the things
   * that are different for each cell, which just involves choosing which extra numbers to add.
   * This means that for the test set of data, it will use 2708 floating point adds and 2680
   * floating point multiplies, compared to the 7400 floating point adds and 7400 floating point
   * multiplies of the unoptimized version. Though it performs fewer arithmetic operations, this
   * comes at the cost of the implementation being somewhat harder to understand than the unoptimzed
   * one, and less recognisable as an implementation of the specified algorithm.
   *
   * @param row the index of the row at the top left of the diagonal
   * @param col the index of the column at the top left of the diagonal
   * @param input the array of input data
   * @param count the specified count ('c' in the specification)
   * @param result the array to receive the results of the computation
   */
  private static void calculateDiagonalCells(int row, int col, float[] input, int count, float[][] result)
  {
    final int length = input.length;

    // Set up some initial variables. Here we work out the starting index into the input array
    // for the top left row and column of this diagonal. We'll then iterate on that index
    // next, taking the values from the input array, multiplying them, and adding the result
    // to the lookup table for use in the summation.

    int pos;            // Track the position in the matrix as it is computed.
    int offset;         // Used as the base offset into the multiplication lookup table.
    int inputIndexRow;  // Lowest index into the input array based on the row at the top left of the diagonal.
    int inputIndexCol;  // Lowest index into the input array based on the column at the top left of the diagonal.

    if (col == 0)
    {
      pos = row;
      offset = count - pos;
      inputIndexRow = 0;
      inputIndexCol = pos;
    }
    else
    {
      pos = col;
      offset = count - pos;
      inputIndexRow = pos;
      inputIndexCol = 0;
    }

    // Peform the multiplications here, building a lookup table to use for each of the diagonal
    // cells we compute in this method.

    double[] products = new double[length];

    for (int i = 0; i < length - pos; i++)
    {
      products[i] = input[inputIndexRow + i] * input[inputIndexCol + i];
    }

    // Now for the all the product values that are included in the sums for all cells,
    // add them here into a common base value.

    double common = 0.0f;

    for (int i = offset; i < length - count; i++)
    {
      common += products[i];
    }

    // Finally, now we have computed the common additions needed for each cell along
    // the diagonal, loop through the cells down towards the bottom right of the matrix,
    // adding on the products that are not common to the other cells on the diagonal.
    // The 'low' and 'high' variables track the number of non-shared values we need to add
    // from the (offset) ends of the products array.

    int low = 0, high;

    do
    {
      double sum = common;

      for (int i = 0; i < low; i++)
      {
        sum += products[offset + i];
      }

      high = offset;

      for (int i = 0; i < high; i++)
      {
        sum += products[length - count + i];
      }

      result[row][col] = (float)sum;

      offset--;
      low++;
    }
    while ((++row <= count) && (++col <= count));
  }
}
