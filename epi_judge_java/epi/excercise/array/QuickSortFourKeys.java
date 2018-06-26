package epi.excercise.array;

public class QuickSortFourKeys {
  public void quickSort(int[] array) {
    int left=0, i=0, mid=0, right=array.length-1;
    while (i<=right) {
      if (array[i]==1) {
        swap(array, left++, i++);
        mid++;
      } else if (array[i]==4) {
        swap(array, i, right--);
      } else if (array[i]==2) {
        swap(array, mid++, i++);
      } else {
        i++;
      }
    }
  }

  public static void main(String[] args) {
    int[] arrays = new int[]{3, 4, 1, 2, 3, 2};
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
