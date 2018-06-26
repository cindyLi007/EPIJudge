package epi.excercise.array;

import java.util.Arrays;

/**
 * Given an array A of n objects with Boolean-valued keys, reorder the array so that objects that have the key false
 * appear first. The relative ordering of objects with key true should not change.
 */
public class MoveBoolean {

  /**
   * right points to the last false element, when swap right and i, right will move to the next-left false element which
   * is the current last false element, since we loop from back to forth, we can keep the true-key-object order, can insert
   * true-key-object to back part
   */
  public void quickSort(BooleanElement[] array) {
    int right=array.length-1;
    for (int i=array.length-1; i>=0; i--) {
      if (array[i].key) {
        BooleanElement temp = array[i];
        array[i]=array[right];
        array[right--]=temp;
      }
    }
  }

  public static void main(String[] args) {
    BooleanElement[] booleanElements = new BooleanElement[5];
    booleanElements[0] = new BooleanElement(false, 1);
    booleanElements[1] = new BooleanElement(true, 1);
    booleanElements[2] = new BooleanElement(false, 2);
    booleanElements[3] = new BooleanElement(true, 2);
    booleanElements[4] = new BooleanElement(false, 3);
    Arrays.stream(booleanElements).forEach(i -> System.out.print(i.key + " " + i.value + ", "));
    System.out.println();
    MoveBoolean moveBoolean = new MoveBoolean();
    moveBoolean.quickSort(booleanElements);
    Arrays.stream(booleanElements).forEach(i -> System.out.print(i.key + " " + i.value + ", "));
  }
}

class BooleanElement{
  boolean key;
  int value;

  public BooleanElement(boolean key, int value) {
    this.key = key;
    this.value = value;
  }
}