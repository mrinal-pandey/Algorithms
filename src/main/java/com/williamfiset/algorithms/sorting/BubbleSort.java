/**
 * Bubble sort implementation
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

public class BubbleSort {

  // Sort the array using bubble sort. The idea behind
  // bubble sort is to look for adjacent indexes which
  // are out of place and interchange their elements
  // until the entire array is sorted.
  public static void bubbleSort(int[] ar) {
    if (ar == null) return;

    final @NonNegative int N = ar.length;
    boolean sorted;

    do {

      sorted = true;

      for (int i = 1; i < N; i++) {
        if (ar[i] < ar[i - 1]) {
          swap(ar, i - 1, i);
          sorted = false;
        }
      }

    } while (!sorted);
  }

  private static void swap(int[] ar, @IndexFor("#1") int i, @IndexFor("#1") int j) {
    int tmp = ar[i];
    ar[i] = ar[j];
    ar[j] = tmp;
  }

  public static void main(String[] args) {

    int @ArrayLen(7) [] array = {10, 4, 6, 8, 13, 2, 3};
    bubbleSort(array);
    System.out.println(java.util.Arrays.toString(array));

    // TODO(williamfiset): move to javatests/...
    runTests();
  }

  static Random RANDOM = new Random();

  /* If there is no annotation on `min` below on line number 83 in function randInt(),
   * Checker framework says `min` should be of the type @Positive but we can't make
   * `min` @Positive as -1000000 is passed as an argument, framework says so because argument
   * of `nextInt()` below at line number 84 should be positive
   * but that can be made sure by making `min` @LessThan("max") so that `max - min` is always positive,
   * so added the annotation @LessThan("#2") but still we get an error where checker framework
   * asks -1000000 to be made @LessThan(?) whereas it is @LessThanUnknown, now this
   * annotation when applied on constant value -1000000 gives an error `illegal start of expression`,
   * hence I'm suppressing this warning issued corresponding to line number 73*/
  @SuppressWarnings("argument.type.incompatible")
  public static void runTests() {
    final @Positive int NUM_TESTS = 1000;
    for (int i = 1; i <= NUM_TESTS; i++) {
      int [] array = new int[i];
      for (int j = 0; j < i; j++) array[j] = randInt(-1000000, +1000000);
      int[] arrayCopy = array.clone();

      bubbleSort(array);
      java.util.Arrays.sort(arrayCopy);

      if (!java.util.Arrays.equals(array, arrayCopy)) System.out.println("ERROR");
    }
  }

  static int randInt(@LessThan("#2") int min, int max) {
    return RANDOM.nextInt((max - min) + 1) + min;
  }
}
