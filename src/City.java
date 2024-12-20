public class City {
    private String CityName;

    public City(String CityName) {
        this.CityName = CityName;
    }

    public String getCityName() {
        return CityName;
    }

    //Instead of override
    public String toString() {
        return CityName;
    }
}

class Route {
    private City city1;
    private City city2;
    private int time;

    //Constructor
    public Route(City city1, City city2, int time) {
        this.city1 = city1;
        this.city2 = city2;
        this.time = time;
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getTime() {
        return time;
    }

}
