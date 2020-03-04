/**
 * Mergesort implementation
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */
package com.williamfiset.algorithms.sorting;

import java.util.Arrays;
import java.util.Random;
import org.checkerframework.checker.index.qual.LessThan;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.index.qual.NonNegative;

public class Mergesort {

  public static int[] mergesort(int[] ar) {

    // Base case is when a single element (which is already sorted)
    @NonNegative int n = ar.length;
    if (n == 1) return ar;

    // Split array into two parts and recursively sort them
    int[] left = mergesort(Arrays.copyOfRange(ar, 0, n / 2));
    int[] right = mergesort(Arrays.copyOfRange(ar, n / 2, n));

    // Combine the two arrays into one larger array
    return merge(left, right);
  }

  // Merge two sorted arrays into a larger sorted array
  /* Not able to apply IndexFor() annotation for `i1` and `i2` alone on line 36
   * as they are not declared separately*/
  @SuppressWarnings("array.access.unsafe.high")
  private static int[] merge(int[] ar1, int[] ar2) {

    @NonNegative int n1 = ar1.length, n2 = ar2.length;
    @NonNegative int n = n1 + n2, i1 = 0, i2 = 0;
    int[] ar = new int[n];

    for (int i = 0; i < n; i++) {
      if (i1 == n1) {
        ar[i] = ar2[i2++];
      } else if (i2 == n2) {
        ar[i] = ar1[i1++];
      } else {
        if (ar1[i1] < ar2[i2]) {
          ar[i] = ar1[i1++];
        } else {
          ar[i] = ar2[i2++];
        }
      }
    }
    return ar;
  }

  public static void main(String[] args) {

    int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
    array = mergesort(array);
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
      for (int j = 0; j < i; j++) array[j] = randInt(-1000000, +1000000);
      int[] arrayCopy = array.clone();

      array = mergesort(array);
      java.util.Arrays.sort(arrayCopy);

      if (!java.util.Arrays.equals(array, arrayCopy)) System.out.println("ERROR");
    }
  }

  static int randInt(@LessThan("#2") int min, int max) {
    return RANDOM.nextInt((max - min) + 1) + min;
  }
}
