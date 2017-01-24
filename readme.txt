
Introduction
============

This project contains two Java classes to calculate the matrix from the input data file. The UnoptimizedMatrixGenerator class provides a basic implementation of the algorithm using a simple row/column iteration over the matrix, multiplying the input values and summing them. This is just implemented for comparison purposes.

The OptimizedMatrixGenerator class tries to minimize the amount of computation by taking the approach of reducing the overall number of floating point multiplies and adds. The optimized computation is done by working across the diagonals of the matrix, left to right. For each diagonal, it will build a lookup table of the multiplication products required by the cells on that diagonal. It will then, again once for the whole diagonal, add the products that are common to all the cells on that diagonal to use as a base result. Finally, it will loop over the cells in the diagonal and, for each one, add the products that are not common to all to the base result.

The optimization.xslx workbook contains some totals for the number of floating point adds and multiplies for both optimized and unoptimized versions of the algorithm for different values of N and c. The optimized algorithm improves on the unoptimized one for increasing values of N, but the biggest performance gain is for increasing values of c, as less work is done as the resulting matrix gets bigger.

The output of the classes are single precision floating point values - this wasn't specified in the task - just to use single precision input - so it takes the approach of singles in, singles out. The actual computation uses double precision values for accuracy when doing the adds and multiplies. 

The calculation of the matrix diagonals are not parallelized across multiple cores (given that the problem was to reduce computation, not execution time), but could be done as another optimization.

The binary is built using Gradle - the build.gradle script will build the jar file, then copy it to a 'bin' folder, along with the test data. The source code is laid out in the typical Gradle way for the main source and the test source code. The JUnit tests are meant to be illustrative rather than comprehensive, due to time constraints. Rather than compare the float values using the equality operator ==, the tests compare the floating point values using a small delta value.


Building and running the code
=============================

This project requires a Java 8 JDK to build.

To build, run the following command:

> gradlew build

Then to execute:

> cd bin
> java -jar matrix.jar -c 4 -n 300 -f "test.prn"