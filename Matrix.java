package week1;

class MatrixMultiplierThread extends Thread {
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] result;
    private int row;
    
    public MatrixMultiplierThread(int[][] matrixA, int[][] matrixB, int[][] result, int row) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.row = row;
    }
    
    @Override
    public void run() {
        int colsB = matrixB[0].length;
        int colsA = matrixA[0].length;
        
        for (int j = 0; j < colsB; j++) {
            result[row][j] = 0;
            for (int k = 0; k < colsA; k++) {
                result[row][j] += matrixA[row][k] * matrixB[k][j];
            }
        }
    }
}

public class Matrix {
    
    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;
        
        if (colsA != rowsB) {
            throw new IllegalArgumentException("Matrix multiplication not possible: Columns of A must match rows of B.");
        }
        
        int[][] result = new int[rowsA][colsB];
        Thread[] threads = new Thread[rowsA];
        
        for (int i = 0; i < rowsA; i++) {
            threads[i] = new MatrixMultiplierThread(matrixA, matrixB, result, i);
            threads[i].start();
        }
        
        for (int i = 0; i < rowsA; i++) {
            try {
                threads[i].join(); // Wait for all threads to complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixB = {{2, 0}, {1, 2}};
        
        int[][] result = multiplyMatrices(matrixA, matrixB);
        
        System.out.println("Result of the multiplication:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}