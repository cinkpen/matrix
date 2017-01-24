package matrix;

/**
 * Unoptimized version of the algorithm to generate the values in the matrix.
 * This uses 3 unoptimized loops to generate the cells in row/column ordering,
 * with an inner summation loop. For 300 input values and a count of 4, this will
 * find the result using 7400 additions and 7400 multiplications. This is provided
 * as a reference against the optimized class, for comparison of the results.
 */
public class UnoptimizedMatrixGenerator implements MatrixGenerator
{
  /**
   * Generate the values in the matrix for the given values and count.
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

    for (int k = 0; k <= count; k++)
    {
      for (int j = 0; j <= count; j++)
      {
        double sum = 0.0f;

        for (int i = count; i <= input.length-1; i++)
        {
          sum += input[i-k] * input[i-j];
        }

        matrix[k][j] = (float)sum;
      }
    }

    return matrix;
  }
}
