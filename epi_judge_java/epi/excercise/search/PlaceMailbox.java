package epi.excercise.search;

import java.util.*;
import java.util.stream.Collectors;

// Variant 11.8
public class PlaceMailbox {

    public static int placeMailbox_sort(List<Building> buildings) {
        Collections.sort(buildings, Comparator.comparingInt(o -> o.dist));
        int totalPeople = buildings.stream().map(building -> building.ppl).mapToInt(Integer::intValue).sum();
        int sum = 0;
        for (int i=0; i<buildings.size(); i++) {
            sum += buildings.get(i).ppl;
            if (sum >= totalPeople/2) return buildings.get(i).dist;
        }
        return -1;
    }

    public static int placeMailbox(List<Building> buildings) {
        Random r = new Random();
        int totalPeople = buildings.stream().map(building -> building.ppl).mapToInt(Integer::intValue).sum();
        int left = 0, right = buildings.size()-1;
        while (left <= right) {
            int pivot = r.nextInt(right-left+1) + left;
            int newPivot = partition(buildings, pivot, left, right);
            int sum = sum(buildings, newPivot);
            int sumBeforePivot = sum(buildings, newPivot-1);
            if (sumBeforePivot <= totalPeople/2 && totalPeople/2 <= sum)
                return buildings.get(newPivot).dist;
            if (sum < totalPeople/2) left = newPivot+1;
            else right = newPivot -1;
        }
        return -1;
    }

    private static int sum(List<Building> buildings, int idx) {
        if (idx < 0) return 0;
        int sum = 0;
        for (int i=0; i<=idx; i++) sum+=buildings.get(i).ppl;
        return sum;
    }

    private static int partition(List<Building> buildings, int pivot, int left, int right) {
        int v = buildings.get(pivot).dist;
        Collections.swap(buildings, pivot, right);
        for (int i=left; i<right; i++) {
            if (buildings.get(i).dist < v)
                Collections.swap(buildings, left++, i);
        }
        Collections.swap(buildings, left, right);
        return left;
    }

    public static void main(String... args) {
        List<Building> buildings = new ArrayList<>();
        buildings.add(new Building(60, 100));
        buildings.add(new Building(20, 3));
        buildings.add(new Building(50, 2));
        buildings.add(new Building(40, 3));
        buildings.add(new Building(10, 20));
        buildings.add(new Building(30, 1));
        System.out.println(placeMailbox_sort(buildings));
    }

}

class Building {
    int ppl, dist;

    Building(int d, int p) {
        ppl = p;
        dist = d;
    }
}