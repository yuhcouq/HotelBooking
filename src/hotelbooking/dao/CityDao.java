package hotelbooking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hotelbooking.model.City;

@Repository
public class CityDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<City> getListCities() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM city ORDER BY id_city DESC";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<City>(City.class));
	}

	public int addCity(City city) {
		String sql = "INSERT INTO city(city_name, country) VALUES(?, ?)";
		return jdbcTemplate.update(sql, new Object[] { city.getCity_name(), "Việt Nam"});
	}

	public City getCity(int id_city) {
		String sql = "SELECT * FROM city WHERE id_city = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_city },
				new BeanPropertyRowMapper<City>(City.class));
	}

	public int editCity(City city) {
		String sql = "UPDATE city SET city_name = ?, country = ? WHERE id_city = ? ";
		return jdbcTemplate.update(sql, new Object[] { city.getCity_name(), city.getCountry(), city.getId_city()});
	}

	public int delCity(int id_city) {
		String sql = "DELETE FROM city WHERE id_city = ?";
		return jdbcTemplate.update(sql, new Object[] { id_city });
	}

	public List<City> getListCityMoveData(String date) {
		String sql = "SELECT * FROM city WHERE DAY(CreatedAT) = DAY(?) OR DAY(UpdatedAT) = DAY(?)";
		return jdbcTemplate.query(sql, new Object[] { date, date }, new BeanPropertyRowMapper<City>(City.class));
	}
}
