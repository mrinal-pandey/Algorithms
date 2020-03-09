/**
 * Selection sort implementation
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */
package com.williamfiset.algorithms.sorting;

import java.util.Random;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.LessThan;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.common.value.qual.ArrayLen;

public class SelectionSort {

  public static void selectionSort(int[] array) {
    if (array == null) return;
    final @NonNegative int N = array.length;

    for (int i = 0; i < N; i++) {

      // Find the index beyond i with a lower value than i
      @IndexFor("array") int swapIndex = i;
      for (int j = i + 1; j < N; j++) if (array[j] < array[swapIndex]) swapIndex = j;

      swap(array, i, swapIndex);
    }
  }

  private static void swap(int[] ar, @IndexFor("#1") int i, @IndexFor("#1") int j) {
    int tmp = ar[i];
    ar[i] = ar[j];
    ar[j] = tmp;
  }

  public static void main(String[] args) {

    int @ArrayLen(7) [] array = {10, 4, 6, 8, 13, 2, 3};
    selectionSort(array);
    System.out.println(java.util.Arrays.toString(array));

    runTests();
  }

  // TODO(williamfiset): move this to a test file.

  static Random RANDOM = new Random();
  
  // Same reason as stated in BubbleSort.java
  @SuppressWarnings("argument.type.incompatible")
  public static void runTests() {
    final @Positive int NUM_TESTS = 1000;
    for (int i = 1; i <= NUM_TESTS; i++) {

      int[] array = new int[i];
      for (int j = 0; j < i; j++) array[j] = randInt(-1000000, +1000000);
      int[] arrayCopy = array.clone();

      selectionSort(array);
      java.util.Arrays.sort(arrayCopy);

      if (!java.util.Arrays.equals(array, arrayCopy)) System.out.println("ERROR");
    }
  }

  static int randInt(@LessThan("#2") int min, int max) {
    return RANDOM.nextInt((max - min) + 1) + min;
  }
}
