package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }
  }

  /*@EpiTest(testfile = "calendar_rendering.tsv")

  // Time: O(NlgN), Space: O(N)
  *//**
   * keep a end time priority queue, whenever we start an event, pop up all already-end events.
   *//*
  public static int findMaxSimultaneousEvents(List<Event> A) {
    Collections.sort(A, Comparator.comparingInt(e -> e.start));
    int max=0;
    int current=0;
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    for (Event event : A) {
      while (!priorityQueue.isEmpty() && priorityQueue.peek() < event.start) {
        priorityQueue.poll();
        current--;
      }
      priorityQueue.offer(event.finish);
      current++;
      max = Math.max(current, max);
    }

    return max;
  }*/

  @EpiTest(testfile = "calendar_rendering.tsv")

  // Time: O(NlgN), Space: O(N)
  /**
   * first make an list of all time point (Endponit), use a boolean to diff start and end, then sorted by time
   * 2nd loop through all endpoints
   */
  public static int findMaxSimultaneousEvents(List<Event> A) {
    List<Endpoint> E = A.stream().map(event -> Arrays.asList(new Endpoint(event.start, true), new Endpoint(event.finish, false)))
        .flatMap(List::stream).collect(Collectors.toList());
    E.sort((a, b) -> {
      if (a.time!=b.time) {
        return a.time-b.time;
      } else {
        // this makes "Comparison method violates its general contract" because if a is T, no matter b is T or F, result is -1
        // but the contract if a<b then b>a, but switch a&b, when b is T, a is T, the result is 1.
        // return a.isStart ? -1 : b.isStart ? 1 : 0;
        // should be
        return a.isStart&!b.isStart ? -1 : !a.isStart&b.isStart ? 1 : 0;
      }
    });

    int max = 0, current = 0;
    for (Endpoint endpoint : E) {
      if (endpoint.isStart) {
        current++;
        max = Math.max(current, max);
      } else {
        current--;
      }
    }

    return max;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
