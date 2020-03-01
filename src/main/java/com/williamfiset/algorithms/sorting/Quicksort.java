/**
 * Quicksort implementation using Hoare partitioning
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */
package com.williamfiset.algorithms.sorting;

import java.util.Random;
import org.checkerframework.checker.index.qual.*;
import org.checkerframework.common.value.qual.*;

public class Quicksort {

  /* Can't make 0 and ar.length - 1 as IndexFor("ar") */
  @SuppressWarnings("index")
  public static void quicksort(int[] ar) {
    if (ar == null) return;
    quicksort(ar, 0, ar.length - 1);
  }

  // Sort interval [lo, hi] inplace recursively
  /* Can't apply @IndexFor("ar") for constant 1 */
  @SuppressWarnings("index")
  private static void quicksort(int[] ar, @IndexFor("#1") int lo, @IndexFor("#1") int hi) {
    if (lo < hi) {
      @IndexFor("ar") int splitPoint = partition(ar, lo, hi);
      quicksort(ar, lo, splitPoint);
      quicksort(ar, splitPoint + 1, hi);
    }
  }

  // Performs Hoare partition algorithm for quicksort
  /* Can't apply IndexFor("ar") for i and j due to increment and decrement operation */
  @SuppressWarnings("index")
  private static @IndexFor("#1") int partition(int[] ar, @IndexFor("#1") int lo, @IndexFor("#1") int hi) {
    int pivot = ar[lo];
    int i = lo - 1, j = hi + 1;
    while (true) {
      do {
        i++;
      } while (ar[i] < pivot);
      do {
        j--;
      } while (ar[j] > pivot);
      if (i < j) swap(ar, i, j);
      else return j;
    }
  }

  // Swap two elements
  private static void swap(int[] ar, @IndexFor("#1") int i, @IndexFor("#1") int j) {
    int tmp = ar[i];
    ar[i] = ar[j];
    ar[j] = tmp;
  }

  public static void main(String[] args) {

    int @ArrayLen(8) [] array = {10, 4, 6, 4, 8, -13, 2, 3};
    quicksort(array);
    System.out.println(java.util.Arrays.toString(array));

    // TODO(williamfiset): Move to test file
    runTests();
  }

  /* TESTING BELOW */

  static Random RANDOM = new Random();

  /* For randInt() function, it says 1st argument should be of the type @LessThan() but this
   * annotation gives error when applied on -1000000 */
  @SuppressWarnings("index")
  public static void runTests() {
    final @Positive int NUM_TESTS = 1000;
    for (int i = 1; i <= NUM_TESTS; i++) {

      int[] array = new int[i];
      for (int j = 0; j < i; j++) array[j] = randInt(-1000000, +1000000);
      int[] arrayCopy = array.clone();

      quicksort(array);
      java.util.Arrays.sort(arrayCopy);

      if (!java.util.Arrays.equals(array, arrayCopy)) System.out.println("ERROR");
    }
  }

  static int randInt(@LessThan("#2") int min, int max) {
    return RANDOM.nextInt((max - min) + 1) + min;
  }
}
