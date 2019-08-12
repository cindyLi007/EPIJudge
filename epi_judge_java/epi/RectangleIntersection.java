package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import static sun.swing.MenuItemLayoutHelper.max;

public class RectangleIntersection {
  @EpiUserType(ctorParams = {int.class, int.class, int.class, int.class})
  public static class Rectangle {
    int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Rectangle rectangle = (Rectangle)o;

      if (x != rectangle.x) {
        return false;
      }
      if (y != rectangle.y) {
        return false;
      }
      if (width != rectangle.width) {
        return false;
      }
      return height == rectangle.height;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      result = 31 * result + width;
      result = 31 * result + height;
      return result;
    }

    @Override
    public String toString() {
      return "[" + x + ", " + y + ", " + width + ", " + height + "]";
    }
  }

  @EpiTest(testfile = "rectangle_intersection.tsv")
  public static Rectangle intersectRectangle(Rectangle R1, Rectangle R2) {
    if (!hasIntersection(R1, R2)) {
      return new Rectangle(0, 0, -1, -1);
    }
    return new Rectangle(Math.max(R1.x, R2.x), max(R1.y, R2.y),
            Math.min(R1.x + R1.width, R2.x + R2.width) - Math.max(R1.x, R2.x),
            Math.min(R1.y + R1.height, R2.y + R2.height) - Math.max(R1.y, R2.y)
            );
  }

  private static boolean hasIntersection(Rectangle r1, Rectangle r2) {
    return (r1.x + r1.width) >= r2.x && (r2.x + r2.width) >= r1.x &&
            (r1.y + r1.height) >= r2.y && (r2.y + r2.height) >= r1.y;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
