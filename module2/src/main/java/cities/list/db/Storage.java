package cities.list.db;

import java.util.*;

public class Storage {
    public static int findCheapestPath(List<Map<Integer, Integer>> cities, int startCityIndex, int endCityIndex) {
        int[] distances = new int[cities.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> distances[a]));
        pq.offer(startCityIndex);
        distances[startCityIndex] = 0;

        while (!pq.isEmpty()) {
            int curr = pq.poll();

            if (curr == endCityIndex)
                break;

            for (Map.Entry<Integer, Integer> neighbor : cities.get(curr).entrySet()) {
                int neighborIndex = neighbor.getKey();
                int newCost = distances[curr] + neighbor.getValue();

                if (newCost <= 200000 && newCost < distances[neighborIndex]) {
                    distances[neighborIndex] = newCost;
                    pq.offer(neighborIndex);
                }
            }
        }

        return distances[endCityIndex];
    }
}