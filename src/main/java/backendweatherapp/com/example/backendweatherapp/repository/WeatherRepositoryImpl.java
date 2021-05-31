package backendweatherapp.com.example.backendweatherapp.repository;

import backendweatherapp.com.example.backendweatherapp.model.TopTenHottestCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class WeatherRepositoryImpl implements WeatherRepository{

    private static final String INSERT_CITY_TEMP =
            "insert into citytemp(name,averagetemp)\n" +
                    "values (?,?);";

    private static final String GET_TOP_TEN_CITIES =
            "SELECT top 10 name,averagetemp FROM CITYTEMP order by averagetemp desc;";

    private static final String DELETE_FROM_CITYTEMP =
            "delete from citytemp";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void storeAvgTempOfCity(String name, double avgTempOfCity) {

        jdbcTemplate.update(INSERT_CITY_TEMP,new Object[]{name,avgTempOfCity});
    }

    @Override
    public List<TopTenHottestCity> getTopTenCities() {
        return jdbcTemplate.query(GET_TOP_TEN_CITIES, new backendweatherapp.com.example.backendweatherapp.repository.TopTenHottestCityMapper());
    }

    @Override
    public void deleteFromTable() {
        jdbcTemplate.update(DELETE_FROM_CITYTEMP);
    }
}
