package test.matrix;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import matrix.MatrixGenerator;
import matrix.OptimizedMatrixGenerator;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestOptimizedGenerator
{
  // Tests for various values of c
  
  @Test
  public void test5x5Matrix_300() throws Exception
  {
    MatrixGenerator calc = new OptimizedMatrixGenerator();
    float[][] result = calc.generate(loadTestData(300), 4);
    
    assertEquals(0.279525f, result[0][0], DELTA);
    assertEquals(0.276682f, result[0][1], DELTA);
    assertEquals(0.268098f, result[0][2], DELTA);
    assertEquals(0.254212f, result[0][3], DELTA);
    assertEquals(0.235722f, result[0][4], DELTA);
    
    assertEquals(0.276682f, result[1][0], DELTA);
    assertEquals(0.280218f, result[1][1], DELTA);
    assertEquals(0.277855f, result[1][2], DELTA);
    assertEquals(0.269717f, result[1][3], DELTA);
    assertEquals(0.256231f, result[1][4], DELTA);
    
    assertEquals(0.268098f, result[2][0], DELTA);
    assertEquals(0.277855f, result[2][1], DELTA);
    assertEquals(0.281864f, result[2][2], DELTA);
    assertEquals(0.279912f, result[2][3], DELTA);
    assertEquals(0.272113f, result[2][4], DELTA);
    
    assertEquals(0.254212f, result[3][0], DELTA);
    assertEquals(0.269717f, result[3][1], DELTA);
    assertEquals(0.279912f, result[3][2], DELTA);
    assertEquals(0.284270f, result[3][3], DELTA);
    assertEquals(0.282571f, result[3][4], DELTA);
    
    assertEquals(0.235722f, result[4][0], DELTA);
    assertEquals(0.256231f, result[4][1], DELTA);
    assertEquals(0.272113f, result[4][2], DELTA);
    assertEquals(0.282571f, result[4][3], DELTA);
    assertEquals(0.287076f, result[4][4], DELTA);
  }
  
  @Test
  public void test4x4Matrix_300() throws Exception
  {
    MatrixGenerator calc = new OptimizedMatrixGenerator();
    float[][] result = calc.generate(loadTestData(300), 3);
    
    assertEquals(0.281236f, result[0][0], DELTA);
    assertEquals(0.278655f, result[0][1], DELTA);
    assertEquals(0.270235f, result[0][2], DELTA);
    assertEquals(0.256417f, result[0][3], DELTA);
    
    assertEquals(0.278655f, result[1][0], DELTA);
    assertEquals(0.282492f, result[1][1], DELTA);
    assertEquals(0.280319f, result[1][2], DELTA);
    assertEquals(0.272259f, result[1][3], DELTA);
    
    assertEquals(0.270235f, result[2][0], DELTA);
    assertEquals(0.280319f, result[2][1], DELTA);
    assertEquals(0.284533f, result[2][2], DELTA);
    assertEquals(0.282666f, result[2][3], DELTA);
    
    assertEquals(0.256417f, result[3][0], DELTA);
    assertEquals(0.272259f, result[3][1], DELTA);
    assertEquals(0.282666f, result[3][2], DELTA);
    assertEquals(0.287110f, result[3][3], DELTA);
  }
  
  @Test
  public void test3x3Matrix_300() throws Exception
  {
    MatrixGenerator calc = new OptimizedMatrixGenerator();
    float[][] result = calc.generate(loadTestData(300), 2);
    
    assertEquals(0.283510f, result[0][0], DELTA);
    assertEquals(0.281119f, result[0][1], DELTA);
    assertEquals(0.272777f, result[0][2], DELTA);
    
    assertEquals(0.281119f, result[1][0], DELTA);
    assertEquals(0.285162f, result[1][1], DELTA);
    assertEquals(0.283073f, result[1][2], DELTA);
    
    assertEquals(0.272777f, result[2][0], DELTA);
    assertEquals(0.283073f, result[2][1], DELTA);
    assertEquals(0.287373f, result[2][2], DELTA);
  }
  
  // Tests for various values of n
  
  @Test
  public void test5x5Matrix_250() throws Exception
  {
    MatrixGenerator calc = new OptimizedMatrixGenerator();
    float[][] result = calc.generate(loadTestData(250), 4);
    
    assertEquals(0.240569f, result[0][0], DELTA);
    assertEquals(0.238045f, result[0][1], DELTA);
    assertEquals(0.230466f, result[0][2], DELTA);
    assertEquals(0.218135f, result[0][3], DELTA);
    assertEquals(0.201605f, result[0][4], DELTA);
    
    assertEquals(0.238045f, result[1][0], DELTA);
    assertEquals(0.240899f, result[1][1], DELTA);
    assertEquals(0.238584f, result[1][2], DELTA);
    assertEquals(0.231139f, result[1][3], DELTA);
    assertEquals(0.218886f, result[1][4], DELTA);
    
    assertEquals(0.230466f, result[2][0], DELTA);
    assertEquals(0.238584f, result[2][1], DELTA);
    assertEquals(0.241685f, result[2][2], DELTA);
    assertEquals(0.239528f, result[2][3], DELTA);
    assertEquals(0.232171f, result[2][4], DELTA);
    
    assertEquals(0.218135f, result[3][0], DELTA);
    assertEquals(0.231139f, result[3][1], DELTA);
    assertEquals(0.239528f, result[3][2], DELTA);
    assertEquals(0.242802f, result[3][3], DELTA);
    assertEquals(0.240740f, result[3][4], DELTA);
    
    assertEquals(0.201605f, result[4][0], DELTA);
    assertEquals(0.218886f, result[4][1], DELTA);
    assertEquals(0.232171f, result[4][2], DELTA);
    assertEquals(0.240740f, result[4][3], DELTA);
    assertEquals(0.244111f, result[4][4], DELTA);
  }
  
  @Test
  public void test5x5Matrix_200() throws Exception
  {
    MatrixGenerator calc = new OptimizedMatrixGenerator();
    float[][] result = calc.generate(loadTestData(200), 4);
    
    assertEquals(0.194793f, result[0][0], DELTA);
    assertEquals(0.192979f, result[0][1], DELTA);
    assertEquals(0.187240f, result[0][2], DELTA);
    assertEquals(0.177778f, result[0][3], DELTA);
    assertEquals(0.164975f, result[0][4], DELTA);
    
    assertEquals(0.192979f, result[1][0], DELTA);
    assertEquals(0.195412f, result[1][1], DELTA);
    assertEquals(0.193838f, result[1][2], DELTA);
    assertEquals(0.188249f, result[1][3], DELTA);
    assertEquals(0.178841f, result[1][4], DELTA);
    
    assertEquals(0.187240f, result[2][0], DELTA);
    assertEquals(0.193838f, result[2][1], DELTA);
    assertEquals(0.196550f, result[2][2], DELTA);
    assertEquals(0.195151f, result[2][3], DELTA);
    assertEquals(0.189625f, result[2][4], DELTA);
    
    assertEquals(0.177778f, result[3][0], DELTA);
    assertEquals(0.188249f, result[3][1], DELTA);
    assertEquals(0.195151f, result[3][2], DELTA);
    assertEquals(0.198053f, result[3][3], DELTA);
    assertEquals(0.196724f, result[3][4], DELTA);
    
    assertEquals(0.164975f, result[4][0], DELTA);
    assertEquals(0.178841f, result[4][1], DELTA);
    assertEquals(0.189625f, result[4][2], DELTA);
    assertEquals(0.196724f, result[4][3], DELTA);
    assertEquals(0.199699f, result[4][4], DELTA);
  }
  
  @Test
  public void test5x5Matrix_150() throws Exception
  {
    MatrixGenerator calc = new OptimizedMatrixGenerator();
    float[][] result = calc.generate(loadTestData(150), 4);
    
    assertEquals(0.143372f, result[0][0], DELTA);
    assertEquals(0.142482f, result[0][1], DELTA);
    assertEquals(0.138733f, result[0][2], DELTA);
    assertEquals(0.132244f, result[0][3], DELTA);
    assertEquals(0.123276f, result[0][4], DELTA);
    
    assertEquals(0.142482f, result[1][0], DELTA);
    assertEquals(0.144719f, result[1][1], DELTA);
    assertEquals(0.144042f, result[1][2], DELTA);
    assertEquals(0.140411f, result[1][3], DELTA);
    assertEquals(0.133950f, result[1][4], DELTA);
    
    assertEquals(0.138733f, result[2][0], DELTA);
    assertEquals(0.144042f, result[2][1], DELTA);
    assertEquals(0.146526f, result[2][2], DELTA);
    assertEquals(0.145985f, result[2][3], DELTA);
    assertEquals(0.142386f, result[2][4], DELTA);
    
    assertEquals(0.132244f, result[3][0], DELTA);
    assertEquals(0.140411f, result[3][1], DELTA);
    assertEquals(0.145985f, result[3][2], DELTA);
    assertEquals(0.148617f, result[3][3], DELTA);
    assertEquals(0.148109f, result[3][4], DELTA);
    
    assertEquals(0.123276f, result[4][0], DELTA);
    assertEquals(0.133950f, result[4][1], DELTA);
    assertEquals(0.142386f, result[4][2], DELTA);
    assertEquals(0.148109f, result[4][3], DELTA);
    assertEquals(0.150771f, result[4][4], DELTA);
  }
  
  private static float[] loadTestData(int length) throws Exception
  {
    URL url = TestOptimizedGenerator.class.getClassLoader().getResource("test.prn");  
    Path path = Paths.get(url.toURI());
    
    String[] lines = Files.lines(path)
                          .limit(length)
                          .toArray(String[]::new);

    float[] input = new float[lines.length];
    
    for (int i = 0; i < lines.length; i++)
    {
      input[i] = Float.parseFloat(lines[i]);
    }
    
    return input;
  }
  
  private static final float DELTA = 0.000001f;
}