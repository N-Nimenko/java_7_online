package separation.dao;

import java.util.Arrays;

public class DAO {
    private String[] people = {"Oleksandr", "Vladimir", "Oleg", "Ivan", "Ihor", "Evgeniy", "Egor", "Pavel"};
    private int[][] friendships = {
            {0, 1},
            {0, 2},
            {1, 3},
            {2, 4},
            {3, 4},
            {4, 5}
    };

    private String[] path;
    private int pathIndex;

    public DAO() {
        shuffleFriendships();
        shufflePeople();
    }

    public void shuffleFriendships() {
        int n = people.length;
        friendships = new int[n][2];

        for (int i = 0; i < n; i++) {
            friendships[i][0] = i;
            friendships[i][1] = (i + 1) % n;
        }

        shuffleArray(friendships);
    }

    public void shufflePeople() {
        shuffleArray(people);
    }

    public boolean findPath(String source, String target) {
        boolean[] visited = new boolean[people.length];
        visited[getIndex(source)] = true;
        path = new String[people.length];
        pathIndex = 0;
        return findPathHelper(source, target, visited);
    }

    private boolean findPathHelper(String source, String target, boolean[] visited) {
        if (source.equals(target)) {
            path[pathIndex++] = source;
            return true;
        }

        for (int[] friendship : friendships) {
            String person1 = people[friendship[0]];
            String person2 = people[friendship[1]];

            if (source.equals(person1) && !visited[getIndex(person2)]) {
                visited[getIndex(person2)] = true;
                if (findPathHelper(person2, target, visited)) {
                    path[pathIndex++] = person1;
                    return true;
                }
            } else if (source.equals(person2) && !visited[getIndex(person1)]) {
                visited[getIndex(person1)] = true;
                if (findPathHelper(person1, target, visited)) {
                    path[pathIndex++] = person2;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isValidName(String name) {
        return Arrays.asList(people).contains(name);
    }

    public String[] getPath() {
            String[] trimmedPath = new String[pathIndex];
            System.arraycopy(path, 0, trimmedPath, 0, pathIndex);
            return trimmedPath;
        }

    private int getIndex(String name) {
        for (int i = 0; i < people.length; i++) {
            if (people[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private void shuffleArray(Object[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int randomIndex = i + (int) (Math.random() * (n - i));
            Object temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }
}
