package epi.excercise.array;

public class QuickSortFourKeys {
  public void quickSort(int[] array) {
    // left is the 1st 2 pos, mid is 1st 3 pos, right is left of 1st 4 pos
    int left=0, i=0, mid=0, right=array.length-1;
    while (i<=right) {
      if (array[i]==1) {
        // when we find 1, if we directly change left to i, it will make 2 behind 3, so we need first swap 1st 2 and
        // 1st 3, then swap 3 with 1
        swap(array, left, mid++);
        swap(array, left++, i++);
      } else if (array[i]==4) {
        swap(array, i, right--);
      } else if (array[i]==2) {
        // insert 2 to the current 1st 3, 8
        swap(array, mid++, i++);
      } else {
        i++;
      }
    }
  }

  public static void main(String[] args) {
    int[] arrays = new int[]{3, 4, 2, 1, 2, 4, 3, 2, 1};
    QuickSortFourKeys quickSortFourKeys = new QuickSortFourKeys();
    quickSortFourKeys.quickSort(arrays);
    for (int i=0; i<arrays.length; i++) {
      System.out.print(arrays[i] + ", ");
    }
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i]=array[j];
    array[j]=temp;
  }
}
