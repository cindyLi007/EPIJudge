package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class AddingCredits {

  public static class ClientsCreditsInfo {
    // Use bst to support Max() in O(1)
    TreeMap<Integer, Set<String>> bst = new TreeMap<>();
    // Use map to support remove, lookup in O(1)
    Map<String, Integer> clientToCredit = new HashMap<>();
    // use offset to support addAll in O(1). For later-added client-credit, should set the credit = credit - c;
    int offset = 0;

    // Time: O(lgN), dominated by BST
    public void insert(String clientID, int c) {
      // since BST uses credit as the key, we must first remove it, update and insert back to BST
      remove(clientID);
      clientToCredit.put(clientID, c - offset);
      bst.putIfAbsent(c - offset, new HashSet<>());
      bst.get(c - offset).add(clientID);
    }

    // Time: O(lgN), dominated by BST
    public boolean remove(String clientID) {
      if (clientToCredit.containsKey(clientID)) {
        Integer credit = clientToCredit.remove(clientID);
        Set<String> clientSet = bst.get(credit);
        clientSet.remove(clientID);
        // we need keep the BST as small as possible for fast search, so if there is no client bounds a credit,
        // remove the node from BST
        if (clientSet.isEmpty()) {
          bst.remove(credit);
        }
        return true;
      }
      return false;
    }

    // Time: O(1)
    public int lookup(String clientID) {
      return clientToCredit.containsKey(clientID) ?
              clientToCredit.get(clientID) + offset : -1;
    }

    // Time: O(1)
    public void addAll(int C) {
      offset += C;
    }

    // Time: O(1), library BST implementation uses caching
    public String max() {
      return bst.isEmpty() ? "" : bst.lastEntry().getValue().iterator().next();
    }

    @Override
    public String toString() {
      // Implement this placeholder.
      return super.toString();
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
