package backendweatherapp.com.example.backendweatherapp.model;


public class TopTenHottestCity {

    private String city;
    private double temp;

    public TopTenHottestCity() {
    }

    public TopTenHottestCity(String city, double temp) {
        this.city = city;
        this.temp = temp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
