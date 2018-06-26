package epi.excercise.primitive.types;

import java.util.Random;

/**
 * Created 5/1/2018
 */
public class BitOperations {

  /**
   * https://stackoverflow.com/questions/2811319/difference-between-and
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("************** Operator TEST *********************");
    System.out.println(6&4);  // should be 4
    System.out.println(1|2);  // should be 3
    System.out.println(8>>1); // should be 4
    System.out.println(Integer.toBinaryString(-16 >>> 2)); // also shift the sign, -16 is 111111....10000, so it becomes 001111..1100
    System.out.println(-16>>2); // should be -4
    System.out.println(1<<10); // should be 1024
    System.out.println(Integer.toBinaryString(~0)); // because reverse 0 will be 111....11, which is -1
    System.out.println(15^9); // should be 6
    System.out.println(Integer.MAX_VALUE);

    System.out.println("************** Size Spec *********************");
    System.out.println(Short.SIZE); // should be 16
    System.out.println(Integer.SIZE);  // 32
    System.out.println(Long.SIZE); // 64
    System.out.println(Double.SIZE); // 64
    System.out.println(Byte.SIZE); // 8
    System.out.println(Character.SIZE); // 16 char is can be short
    System.out.println(Float.SIZE); // 32
    System.out.println(Boolean.TRUE);

    System.out.println("************** Box Type *********************");
    System.out.println(Double.valueOf("1.23"));
    System.out.println(Boolean.valueOf("true"));
    System.out.println(Float.toString(-1.34f));

    System.out.println("************** Math operation *********************");
    System.out.println(Math.ceil(2.17)); // 3.0
    System.out.println(Math.floor(2.97)); // 2.0
    System.out.println(Math.pow(2.71, 3)); // 19.9
    System.out.println(Math.sqrt(225)); // 15

    System.out.println("************** Primitive Type conversion *********************");
    System.out.println(Character.getNumericValue('a')); // should be 10
    System.out.println(Character.getNumericValue('0' - '1')); // Do not know why is it -1, do NOT use this method
    System.out.println(String.valueOf(123)); // should be "123"

    System.out.println("************** Primitive Type conversion *********************");
    Random random = new Random();
    for (int i=0; i<5; i++) {
      // uniformly distributed int value between 0 (inclusive) and the specified value (exclusive),
      System.out.println("A random number from 0 to 16 is " + random.nextInt(16));
    }
    System.out.println();
    for (int i=0; i<5; i++) {
      // All [Integer.MIN_VALUE, Integer.MAX_VALUE] int values are produced with (approximately) equal probability.
      System.out.println("A random number without parameter passed is  " + random.nextInt());
    }
    System.out.println();
    for (int i=0; i<2; i++) {
      // either true or false
      System.out.println("A random value of Boolean " + random.nextBoolean());
    }
    System.out.println();
    for (int i=0; i<5; i++) {
      // chosen (approximately) uniformly from the range 0.0d (inclusive) to 1.0d (exclusive)
      System.out.println("A random value of Double " + random.nextDouble());
    }

    /** autoboxing could not apply to array
    Integer[] characters = new int[]{'a', 'b'};
    */
  }
}
