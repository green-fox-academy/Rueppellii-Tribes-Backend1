package com.greenfox.tribes1.Josephus;

public class Matrices {

  public static int[][] matrixWithMaxValues(int[][] inputArr1, int[][] inputArr2) {
    int[][] outputMatrix = new int[inputArr1.length][inputArr1.length];
    for (int i = 0; i < inputArr1.length; i++) {
      for (int j = 0; j < inputArr2.length; j++) {
        if (inputArr1[i][j] > inputArr2[i][j]) {
          outputMatrix[i][j] = inputArr1[i][j];
        } else {
          outputMatrix[i][j] = inputArr2[i][j];
        }
      }
    }
    return outputMatrix;
  }

  public static int[][] getMinOutputMatrix(int[][] array1, int[][]array2){
    int[][] outputMatrix = new int[array1.length][array1.length];
    for(int i =0; i<array1.length; i++){
      for(int j=0; j<array2.length; j++){
        if(array1[i][j]<array2[i][j]){
          outputMatrix[i][j]= array1[i][j];
        } else{
          outputMatrix[i][j]= array2[i][j];
        }
      }
    }
    return outputMatrix;
  }
}