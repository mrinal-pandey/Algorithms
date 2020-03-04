/**
 * Implementation of heapsort
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */
package com.williamfiset.algorithms.sorting;

import java.util.*;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.LessThan;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.common.value.qual.ArrayLen;

public class Heapsort {

  /* Checker says `i` should be @IndexFor("ar") but,
   * if IndexFor("ar") is applied on `i` in loop on line 30, `Math.max()` gives assignment.type.incompatible
   * error and `i--` gives `unary.decrement.type.incompatible` error,
   * `swap()` on line 34 and `sink()` on line 35 gives argument.type.incompatible error,
   * and if IndexFor("ar") is applied on `i` in loop on line 33, similar errors are thrown,
   * hence suppressing these warnings because not able to add annotations to handle these warnings*/
  @SuppressWarnings("argument.type.incompatible")
  public static void heapsort(int[] ar) {

    if (ar == null) return;
    @NonNegative int n = ar.length;

    // Heapify, converts array into binary heap, O(n)
    for (int i = Math.max(0, (n / 2) - 1); i >= 0; i--) sink(ar, n, i);

    // Sorting bit
    for (int i = n - 1; i >= 0; i--) {
      swap(ar, 0, i);
      sink(ar, i, 0);
    }
  }

  private static void sink(int[] ar, @NonNegative int n, @IndexFor("#1") int i) {

    while (true) {
      // Not able to use IndexFor("ar") for constant 1 on line 44 and constant 2 on line 46)
      @SuppressWarnings("assignment.type.incompatible")
      @IndexFor("ar") int left = 2 * i + 1; // Left  node
      @SuppressWarnings("assignment.type.incompatible")
      @IndexFor("ar") int right = 2 * i + 2; // Right node
      @IndexFor("ar") int largest = i;

      // Right child is larger than parent
      if (right < n && ar[right] > ar[largest]) largest = right;

      // Left child is larger than parent
      if (left < n && ar[left] > ar[largest]) largest = left;

      // Move down the tree following the largest node
      if (largest != i) {
        swap(ar, largest, i);
        i = largest;
      } else break;
    }
  }

  private static void swap(int[] ar, @IndexFor("#1") int i, @IndexFor("#1") int j) {
    int tmp = ar[i];
    ar[i] = ar[j];
    ar[j] = tmp;
  }

  /* TESTING */

  public static void main(String[] args) {

    int @ArrayLen(8) [] array = {10, 4, 6, 4, 8, -13, 2, 3};
    heapsort(array);
    System.out.println(java.util.Arrays.toString(array));

    // TODO(williamfiset): move to javatests/...
    runTests();
  }

  static Random RANDOM = new Random();

  // Same reason as stated in BubbleSort.java
  @SuppressWarnings("argument.type.incompatible")
  public static void runTests() {
    final @Positive int NUM_TESTS = 1000;
    for (int i = 1; i <= NUM_TESTS; i++) {

      int[] array = new int[i];
      // for(int j = 0; j < i; j++) array[j] = randInt(-1000000, +1000000);
      for (int j = 0; j < i; j++) array[j] = randInt(-10, +10);
      int[] arrayCopy = array.clone();

      heapsort(array);
      java.util.Arrays.sort(arrayCopy);

      if (!java.util.Arrays.equals(array, arrayCopy)) {
        System.out.println(Arrays.toString(array));
        System.out.println("ERROR");
        break;
      }
    }
  }

  static int randInt(@LessThan("#2") int min, int max) {
    return RANDOM.nextInt((max - min) + 1) + min;
  }
}
