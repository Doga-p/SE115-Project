public class CountryMap {
    private City[] cityArray;
    private Route[] routeArray;
    private int cityCount;
    private int routeCount;

    //Constructor
    public CountryMap(int maxCities, int maxRoutes) {
        cityArray = new City[maxCities];
        routeArray = new Route[maxRoutes];
        cityCount = 0;
        routeCount = 0;
    }

    public void addCity(City city) {
        cityArray[cityCount] = city;
        cityCount++;
    }

    public void addRoute(Route route) {
        routeArray[routeCount] = route;
        routeCount++;
    }

    //Finds connected cities
    public Route[] getRoutesFromCity(City city) {
        Route[] result = new Route[routeCount];
        int index = 0;
        for (int i = 0; i < routeCount; i++) {
            if (routeArray[i].getCity1().equals(city)) {
                result[index] = routeArray[i];
                index++;
            }
        }
        // Deleting empty array boxes
        Route[] trimmedResult = new Route[index];
        System.arraycopy(result, 0, trimmedResult, 0, index);
        return trimmedResult;
    }

    public City getCityByName(String name) {
        for (City city : cityArray) {
            if (city != null && city.getCityName().equals(name)) {
                return city;
            }
        }
        return null;
    }


    public int getCityCount() {
        return cityCount;
    }

    public City[] getCities() {
        return cityArray;
    }
}