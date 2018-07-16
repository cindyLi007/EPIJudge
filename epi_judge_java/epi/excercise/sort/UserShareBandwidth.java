package epi.excercise.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserShareBandwidth {
  public static class TimeConnection {
    int start;
    int end;
    int bandwidth;

    TimeConnection(int start, int end, int bandwidth) {
      this.start = start;
      this.end = end;
      this.bandwidth = bandwidth;
    }
  }

  public static class Endpoint {
    int time;
    int bandwidth;

    Endpoint(int time, int bandwidth) {
      this.time = time;
      this.bandwidth = bandwidth;
    }
  }

  public static int maxBandWidth(List<TimeConnection> TC) {
    int max = 0;
    // first make a list of endpoint, start with positive bandwidth, end with negative bandwidth
    List<Endpoint> E = TC.stream().map(timeConnection ->
        Arrays.asList(new Endpoint(timeConnection.start, timeConnection.bandwidth),
                      new Endpoint(timeConnection.end, -timeConnection.bandwidth)))
        .flatMap(List::stream).collect(Collectors.toList());
    E.sort((a, b) -> {
      if (a.time!=b.time) {
        return Integer.compare(a.time, b.time);
      } else {
        return a.bandwidth>0 && b.bandwidth<0 ? -1 : a.bandwidth<0 && b.bandwidth>0 ? 1 : 0;
      }
    });

    int current = 0;
    for (Endpoint endpoint : E) {
      current+=endpoint.bandwidth;
      max = Math.max(max, current);
    }

    return max;
  }

  public static void main(String... args) {
    List<TimeConnection> timeConnections = Arrays.asList(new TimeConnection(1, 5, 10),
                                                        new TimeConnection(2, 7, 19),
                                                        new TimeConnection(4, 5, 12),
                                                        new TimeConnection(6, 10, 28));
    int max = maxBandWidth(timeConnections);

    System.out.println("The peak bandwidth is " + max);
  }



}
