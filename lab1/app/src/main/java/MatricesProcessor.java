package lab1.app.src.main.java;

/**
 * The {@code MatricesProcessor} class provides static methods to perform
 * operations on matrices of type {@code byte}.
 * <p>
 * Functionality includes:
 * <ul>
 * <li>Adding two matrices of the same dimensions.</li>
 * <li>Printing matrices with labels.</li>
 * <li>Computing the sum of the smallest elements in each column of a
 * matrix.</li>
 * </ul>
 * <p>
 * The main method demonstrates these operations using sample matrices.
 * <p>
 * <b>Matrix operation:</b> C = A + B<br>
 * <b>Element type:</b> byte<br>
 * <b>Additional computation:</b> Sum of smallest elements of each column of C
 * </p>
 *
 * <p>
 * Exceptions are thrown if:
 * <ul>
 * <li>The input matrices do not have the same dimensions.</li>
 * <li>Overflow occurs during matrix addition.</li>
 * </ul>
 * </p>
 *
 * @author qwertannov
 */
public class MatricesProcessor {

    public static void main(String[] args) {
        byte[][] matrixA = {
                { 10, 20, 30 },
                { 40, 50, 60 },
                { 70, 80, 90 }
        };
        byte[][] matrixB = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        printMatrix("Matrix A", matrixA);
        printMatrix("Matrix B", matrixB);

        try {
            byte[][] resultMatrix = addMatrices(matrixA, matrixB);
            printMatrix("Result of C = A + B", resultMatrix);

            int columnSum = sumColumnMins(resultMatrix);
            System.out.println("Sum of smallest elements in each column of C: " + columnSum);

        } catch (IllegalArgumentException | ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static byte[][] addMatrices(byte[][] a, byte[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        if (rows != b.length || cols != b[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }

        byte[][] result = new byte[rows][cols];
        for (int i = 0; i < rows; i++) {
            if (a[i].length != cols || b[i].length != cols) {
                throw new IllegalArgumentException("All rows must have the same number of columns");
            }
            for (int j = 0; j < cols; j++) {
                int sum = a[i][j] + b[i][j];
                if (sum > Byte.MAX_VALUE || sum < Byte.MIN_VALUE) {
                    throw new ArithmeticException(
                            "Overflow at position [" + i + "][" + j + "]: " + a[i][j] + " + " + b[i][j]);
                }
                result[i][j] = (byte) sum;
            }
        }
        return result;
    }

    private static void printMatrix(String label, byte[][] matrix) {

        System.out.println(label + " (" + matrix.length + "x" + matrix[0].length + "):");
        for (byte[] row : matrix) {
            for (byte val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int sumColumnMins(byte[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int sum = 0;

        for (int j = 0; j < cols; j++) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < rows; i++) {
                min = Math.min(min, matrix[i][j]);
            }
            sum += min;
        }
        return sum;
    }
}
