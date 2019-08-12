package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class GroupEqualEntries {
  @EpiUserType(ctorParams = {Integer.class, String.class})

  public static class Person {
    public Integer age;
    public String name;

    public Person(Integer k, String n) {
      age = k;
      name = n;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;

      Person person = (Person)o;

      if (!age.equals(person.age))
        return false;
      return name.equals(person.name);
    }

    @Override
    public int hashCode() {
      int result = age.hashCode();
      result = 31 * result + name.hashCode();
      return result;
    }
  }

  // Time: O(N), Space: O(M) m is distinct element
  public static void groupByAge(List<Person> people) {
    Map<Integer, Integer> ageToCount = new HashMap<>();
    for (Person person : people) {
      ageToCount.put(person.age, ageToCount.getOrDefault(person.age, 0) + 1);
    }
    Map<Integer, Integer> ageToOffset = new HashMap<>();
    int offset = 0;
    for (Map.Entry<Integer, Integer> entry : ageToCount.entrySet()) {
      ageToOffset.put(entry.getKey(), offset);
      offset += entry.getValue();
    }
    /**
     * in-place swap in list people, each time find the next entry in ageToOffset, the entry's value is the from-Pos
     * which is the temp storage, use this idx to find the entry we want to move from people
     */
    while (!ageToOffset.isEmpty()) {
      Map.Entry<Integer, Integer> entry = ageToOffset.entrySet().iterator().next();
      int toSwapToCorrectPosAge = people.get(entry.getValue()).age;
      int idx_1 = ageToOffset.get(toSwapToCorrectPosAge);
      Collections.swap(people, entry.getValue(), idx_1);
      int count = ageToCount.get(toSwapToCorrectPosAge)-1;
      if (count==0) {
        ageToOffset.remove(toSwapToCorrectPosAge);
      } else {
        ageToCount.put(toSwapToCorrectPosAge, count);
        ageToOffset.put(toSwapToCorrectPosAge, ageToOffset.get(toSwapToCorrectPosAge) + 1);
      }
    }
  }

  // Time: O(N), Space: O(N)
  public static void groupByAge_extraSpace(List<Person> people) {
    Map<Integer, Integer> ageToCount = new HashMap<>();
    for (Person person : people) {
      ageToCount.put(person.age, ageToCount.getOrDefault(person.age, 0)+1);
    }
    Map<Integer, Integer> ageOffset = new HashMap<>();
    int offset = 0;
    for (Map.Entry<Integer, Integer> entry : ageToCount.entrySet()) {
      ageOffset.put(entry.getKey(), offset);
      offset += entry.getValue();
    }
    List<Person> res = new ArrayList<>(people);
    for (Person person : people) {
      res.set(ageOffset.get(person.age), person);
      ageOffset.put(person.age, ageOffset.get(person.age)+1);
    }
    people.clear();
    for (Person person : res) {
      people.add(person);
    }
  }

  private static Map<Person, Integer> buildMultiset(List<Person> people) {
    Map<Person, Integer> m = new HashMap<>();
    for (Person p : people) {
      m.put(p, m.getOrDefault(p, 0) + 1);
    }
    return m;
  }

  @EpiTest(testfile = "group_equal_entries.tsv")
  public static void groupByAgeWrapper(TimedExecutor executor,
                                       List<Person> people) throws Exception {
    if (people.isEmpty()) {
      return;
    }
    Map<Person, Integer> values = buildMultiset(people);

    executor.run(() -> groupByAge(people));

    Map<Person, Integer> newValues = buildMultiset(people);
    if (!values.equals(newValues)) {
      throw new TestFailure("Entry set changed");
    }
    int lastAge = people.get(0).age;
    Set<Integer> ages = new HashSet<>();

    for (Person p : people) {
      if (ages.contains(p.age)) {
        throw new TestFailure("Entries are not grouped by age");
      }
      if (p.age != lastAge) {
        ages.add(lastAge);
        lastAge = p.age;
      }
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
