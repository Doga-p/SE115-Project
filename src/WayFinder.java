public class WayFinder {
    private CountryMap map;

    public WayFinder(CountryMap map) {
        this.map = map;
    }

    public String findFastestRoute(String startCityName, String endCityName) {
        City startCity = map.getCityByName(startCityName);
        City endCity = map.getCityByName(endCityName);

        if (startCity == null || endCity == null) {
            return "Error: Invalid city names.";
        }

        // Dijkstra's Algorithm
        int[] shortestTimes = new int[map.getCityCount()];
        City[] previousCities = new City[map.getCityCount()];
        boolean[] visited = new boolean[map.getCityCount()];

        // giving 0 for startCity
        for (int i = 0; i < map.getCityCount(); i++) {
            shortestTimes[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        //StartCity find in array. And sets time to 0
        int startIndex = getCityIndex(startCity);
        shortestTimes[startIndex] = 0;

        for (int i = 0; i < map.getCityCount(); i++) {
            int currentCityIndex = getMinDistanceCity(shortestTimes, visited);

            if (currentCityIndex == -1) return "Fastest Way: No path found\nTotal Time: 0 min";

            visited[currentCityIndex] = true;

            City currentCity = map.getCities()[currentCityIndex];
            Route[] routesFromCity = map.getRoutesFromCity(currentCity);

            for (Route route : routesFromCity) {
                City neighbor = route.getCity2();
                int neighborIndex = getCityIndex(neighbor);
                int newTime = shortestTimes[currentCityIndex] + route.getTime();

                if (newTime < shortestTimes[neighborIndex]) {
                    shortestTimes[neighborIndex] = newTime;
                    previousCities[neighborIndex] = currentCity;
                }
            }
        }

        String path = ""; // I started with an empty string at the beginning
        City current = endCity;

        while (current != null) {
            path = current.getCityName() + " -> " + path;
            current = previousCities[getCityIndex(current)];
        }

        path = path.substring(0, path.length() - 4); // Delete last ' -> '
        return "Fastest Way: " + path + "\nTotal Time: " + shortestTimes[getCityIndex(endCity)] + " min";
    }

    private int getCityIndex(City city) {
        for (int i = 0; i < map.getCityCount(); i++) {
            if (map.getCities()[i].equals(city)) {
                return i;
            }
        }
        return -1;
    }

    private int getMinDistanceCity(int[] times, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < map.getCityCount(); i++) {
            if (!visited[i] && times[i] < min) {
                min = times[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}