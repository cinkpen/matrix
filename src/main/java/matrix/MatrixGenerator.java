package matrix;

/**
 * Interface for an implementation of the algorithm for an array of input data
 * and a specified count. Allows different implementations of the algorithm to
 * be swapped out in the main program.
 */
public interface MatrixGenerator
{
  /**
   * Generate the values in the matrix for the given values and count.
   *
   * @param input an array of floating point values
   * @param count a count used to control the size of the matrix
   *
   * @return a two dimensional array of floats, representing the values in the matrix.
   */
  float[][] generate(float[] input, int count);
}
