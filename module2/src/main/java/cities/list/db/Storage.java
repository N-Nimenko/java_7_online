package cities.list.db;

import java.util.*;

public class Storage {
    public static int findCheapestPath(List<List<Integer>> cities, int startCityIndex, int endCityIndex) {
        int[] distances = new int[cities.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> distances[a]));
        pq.offer(startCityIndex);
        distances[startCityIndex] = 0;

        while (!pq.isEmpty()) {
            int curr = pq.poll();

            if (curr == endCityIndex)
                break;

            List<Integer> neighbors = cities.get(curr);
            for (int j = 0; j < neighbors.size(); j += 2) {
                int neighborIndex = neighbors.get(j);
                int cost = neighbors.get(j + 1);
                int newCost = distances[curr] + cost;

                if (newCost <= 200000 && newCost < distances[neighborIndex]) {
                    distances[neighborIndex] = newCost;
                    pq.offer(neighborIndex);
                }
            }
        }

        return distances[endCityIndex];
    }
}