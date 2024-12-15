public class City {
    private String CityName;

    public City(String CityName) {
        this.CityName = CityName;
    }

    public String getCityName() {
        return CityName;
    }

    @Override
    public String toString() {
        return CityName;
    }
}
