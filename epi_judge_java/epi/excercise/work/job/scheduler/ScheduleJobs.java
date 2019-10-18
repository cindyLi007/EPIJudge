package epi.excercise.work.job.scheduler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class ScheduleJobs {
  ListMultimap<String, String> capabilityToHostMap = ArrayListMultimap.create();
  ListMultimap<String, String> locationToHostMap = ArrayListMultimap.create();
  ListMultimap<String, String> resourceToHostMap = ArrayListMultimap.create();
  Map<String, Host> listMap = new HashMap<>();

  // first build up maps
  public void insertHost(Host host) {
    capabilityToHostMap.put(host.capablilities, host.hostName);
    locationToHostMap.put(host.locaction, host.hostName);
    resourceToHostMap.put(host.resources, host.hostName);
    listMap.put(host.hostName, host);
  }

  public void assign(Job job) {
    List<String> capablitySet = capabilityToHostMap.get(job.jobCapablilities);
    List<String> regionySet = locationToHostMap.get(job.jobRegion);
    List<String> resourceSet = resourceToHostMap.get(job.jobResource);
    Set<String> res = new HashSet<>(capablitySet);
    res.retainAll(regionySet);
    res.retainAll(resourceSet);
    updateHost(job, res);
  }

  private void updateHost(Job job, Set<String> res) {

  }

  public static void main(String... args) {
    String fileName = "C:\\Users\\grchan\\Downloads\\temp\\host.txt";
    ScheduleJobs scheduleJobs = new ScheduleJobs();

    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

      stream.forEach(o -> {
        String[] tokens = o.split(",");
        scheduleJobs.insertHost(new Host(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim()));
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
    scheduleJobs.assign(new Job("2518", "AzJB", "us", "r1"));
  }

}

class Host {
  String hostName;
  String capablilities;
  String locaction;
  String resources;

  public Host(String name, String capablilities, String locaction, String resources) {
    hostName = name;
    this.capablilities = capablilities;
    this.locaction = locaction;
    this.resources = resources;
  }
}

class Job {
  String jobId;
  String jobCapablilities;
  String jobRegion;
  String jobResource;

  public Job(String jobId, String jobCapablilities, String jobRegion, String jobResource) {
    this.jobCapablilities = jobCapablilities;
    this.jobRegion = jobRegion;
    this.jobResource = jobResource;
    this.jobId = jobId;
  }


}
