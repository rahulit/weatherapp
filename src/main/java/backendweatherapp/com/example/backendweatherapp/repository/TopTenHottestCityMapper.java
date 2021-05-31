package backendweatherapp.com.example.backendweatherapp.repository;

import backendweatherapp.com.example.backendweatherapp.model.TopTenHottestCity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


class TopTenHottestCityMapper implements RowMapper<TopTenHottestCity> {
    @Override
    public TopTenHottestCity mapRow(ResultSet resultSet, int i) throws SQLException {

        TopTenHottestCity usStateCapital = new TopTenHottestCity();
        usStateCapital.setCity(resultSet.getString("name"));
        usStateCapital.setTemp(resultSet.getDouble("averagetemp"));

        return usStateCapital;
    }
}
