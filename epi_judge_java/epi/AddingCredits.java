package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class AddingCredits {

  public static class ClientsCreditsInfo {
    Map<String, Integer> map = new HashMap<>();
    // Is a sorted Map based on credits value, so we can quickly insert, delete
    TreeMap<Integer, Set<String>> tree = new TreeMap<>();
    int offset = 0;

    // Time: O(lgN)
    public void insert(String clientID, int c) {
      remove(clientID);
      c -= offset;
      map.put(clientID, c);
      tree.computeIfAbsent(c, key -> new HashSet<>()).add(clientID);
    }

    // Time: O(lgN)
    public boolean remove(String clientID) {
      Integer credits = map.remove(clientID);
      if (credits == null) {
        return false;
      }
      tree.get(credits).remove(clientID);
      if (tree.get(credits).isEmpty()) tree.remove(credits);
      return true;
    }

    // Time: O(1)
    public int lookup(String clientID) {
      Integer credits = map.get(clientID);
      return credits == null ? -1 : credits.intValue() + offset;
    }

    // Time: O(1)
    public void addAll(int C) {
      offset += C;
    }

    // Time: O(lgN)
    public String max() {
      if (map.isEmpty()) return "";
      return tree.lastEntry().getValue().iterator().next();
    }

    @Override
    public String toString() {
      return "{clientToCredit=" + map + '}';
    }
  }

  @EpiUserType(ctorParams = {String.class, String.class, int.class})
  public static class Operation {
    public String op;
    public String sArg;
    public int iArg;

    public Operation(String op, String sArg, int iArg) {
      this.op = op;
      this.sArg = sArg;
      this.iArg = iArg;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Operation operation = (Operation)o;

      if (iArg != operation.iArg) {
        return false;
      }
      if (!op.equals(operation.op)) {
        return false;
      }
      return sArg.equals(operation.sArg);
    }

    @Override
    public int hashCode() {
      int result = op.hashCode();
      result = 31 * result + sArg.hashCode();
      result = 31 * result + iArg;
      return result;
    }

    @Override
    public String toString() {
      return String.format("%s(%s, %d)", op, sArg, iArg);
    }
  }

  @EpiTest(testfile = "adding_credits.tsv")
  public static void ClientsCreditsInfoTester(List<Operation> ops)
      throws TestFailure {
    ClientsCreditsInfo cr = new ClientsCreditsInfo();
    int opIdx = 0;
    for (Operation x : ops) {
      String sArg = x.sArg;
      int iArg = x.iArg;
      int result;
      switch (x.op) {
      case "ClientsCreditsInfo":
        break;
      case "remove":
        result = cr.remove(sArg) ? 1 : 0;
        if (result != iArg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, cr)
              .withProperty(TestFailure.PropertyName.COMMAND, x)
              .withMismatchInfo(opIdx, iArg, result);
        }
        break;
      case "insert":
        cr.insert(sArg, iArg);
        break;
      case "add_all":
        cr.addAll(iArg);
        break;
      case "lookup":
        result = cr.lookup(sArg);
        if (result != iArg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, cr)
              .withProperty(TestFailure.PropertyName.COMMAND, x)
              .withMismatchInfo(opIdx, iArg, result);
        }
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
