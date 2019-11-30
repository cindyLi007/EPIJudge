package epi.excercise.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Let T = (T0, T1,..., Tn-1) be a set of tasks. Each task runs on a single generic server. Task Ti has a duration t,
 * and a set P (possibly empty) of tasks that must be completed before Ti can be started. The set is feasible if there
 * does not exist a sequence of tasks {T0,T1,...,Ti,T0) starting and ending at the same task such that for each consecutive
 * pair of tasks, the first task must be completed before the second task can begin.
 * Given an instance of the task scheduling problem, compute the least amount of time in which all the tasks can be performed,
 * assuming an unlimited number of servers. Explicitly check that the system is feasible.
 */
public class TaskSchedule {

  // Topological sort, from each end point which is the last task in a chain, it's time depends on max time of all its prerequisite
  // tasks, plus this task's during. in the meantime, we also need check cycle (first ignore cyclic graph)

  int max = 0;

  // Time: O(V+E), Space: O(V)
  public int maxTime(List<Task> Graph) {
    for (Task task : Graph) {
      Set<Integer> inProgress =  new HashSet<>();
      if (task.duration == 0) {
        if (dfs(task, inProgress) == -1) {
          throw new IllegalArgumentException("this is a cycle");
        }
      }
    }
    return max;
  }

  private int dfs(Task task, Set<Integer> inProgress) {
    if (task.duration > 0) return task.duration;

    if (inProgress.contains(task.label)) return -1;

    inProgress.add(task.label);
    int maxPreTime = 0;
    // only 2 cases we can return to upper layer, detect a loop or the task does not have prerequisite
    for (Task pre : task.prerequisite) {
      int res = dfs(pre, inProgress);
      if (res==-1) return -1;
      maxPreTime = Math.max(maxPreTime, res);
    }
    inProgress.remove(task.label);

    task.duration = maxPreTime + task.time;
    max = Math.max(max, task.duration);
    return task.duration;
  }

  public static void main(String... args) {
    Task task1 = new Task(1, 10);
    Task task2 = new Task(2, 6);
    Task task3 = new Task(3, 20);
    Task task4 = new Task(4, 23);
    Task task5 = new Task(5, 18);
    Task task6 = new Task(6, 2);
    Task task7 = new Task(7, 56);
    task1.prerequisite.add(task3); task1.prerequisite.add(task4);
    task2.prerequisite.add(task3); task2.prerequisite.add(task1);
    task3.prerequisite.add(task4); task3.prerequisite.add(task6);
    task4.prerequisite.add(task5); task4.prerequisite.add(task7);
    task5.prerequisite.add(task6);
    task7.prerequisite.add(task5);
    List<Task> graph = new ArrayList<>();
    graph.add(task1);
    graph.add(task2);
    graph.add(task3);
    graph.add(task4);
    graph.add(task5);
    graph.add(task6);
    graph.add(task7);
    TaskSchedule taskSchedule = new TaskSchedule();
    System.out.println(taskSchedule.maxTime(graph));
  }
}

class Task {
  int label;
  int time; // how long to exe this time
  List<Task> prerequisite;
  int duration; // total time needed to finish this time, including exe all its prerequisite

  Task(int l, int t) {
    label = l;
    time = t;
    duration = 0; // duration == 0 means we have not visit it
    prerequisite = new ArrayList<>();
  }
}
