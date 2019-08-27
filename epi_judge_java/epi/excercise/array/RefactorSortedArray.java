package epi.excercise.array;

import java.util.Arrays;
import java.util.List;

/**
 * Write a program which takes as input a sorted array A of integers and a positive integer m, and update A so that is x
 * appears m times in A it appears exactly min(2, m) times in A.
 */
public class RefactorSortedArray {
  public static int deleteDuplicatesM(List<Integer> A, int m) {
    int max = m==1 ? 1 : 2;
    int j=1;
    int count=1;
    for (int i=1; i<A.size(); i++) {
      // Notice, since element in List is Integer, even value is same, A.get(i)==A.get(j) always return false;
      // if we begin a new item, j is the writeIdx or we have not meet the threshold
      if (Integer.compare(A.get(i), A.get(j-1))!=0 || count<max) {
        count = Integer.compare(A.get(i), A.get(j-1))!=0 ? 1 : count+1;
        A.set(j++, A.get(i));
      }
    }
    return j;
  }

  public static int deleteKey(List<Integer> A, int m) {
    int j=0;
    for (int i=0; i<A.size(); i++) {
      // Notice, since element in List is Integer, even value is same, A.get(i)==A.get(j) always return false;
      if (A.get(i)!=m) {
        A.set(j++, A.get(i));
      }
    }
    return j;
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(2, 3, 4, 4, 4, 5, 10, 10);
    int res = RefactorSortedArray.deleteDuplicatesM(list, 1);
//    int res = RefactorSortedArray.deleteKey(list, 6);
    System.out.println("the number of refactor array is: " + res);
  }
}
