package hotelbooking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hotelbooking.constant.Defines;
import hotelbooking.model.Hotel;
import hotelbooking.model.Room;
import hotelbooking.model.User;

@Repository
public class HotelDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getCount() {
		String sql = "SELECT COUNT(*) FROM hotel";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public List<Hotel> getListHotel() {
		// TODO Auto-generated method stub
		String sql = "SELECT h.*,c.* FROM hotel AS h INNER JOIN city AS c ON h.city_id = c.id_city ORDER BY h.id_hotel DESC";
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Hotel>(Hotel.class));
	}

	public List<Hotel> getListHotel(int limit) {
		String sql = "SELECT h.* FROM hotel AS h ORDER BY h.rating DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new Object[] { limit, Defines.ROW_COUNT_PUBLIC },
				new BeanPropertyRowMapper<Hotel>(Hotel.class));
//		return jdbcTemplate.query(sql, new Object[] {limit}, new BeanPropertyRowMapper<Hotel>(Hotel.class));
	}
	
	public int addHotel(Hotel hotel, User user, UserDao userDao) {
		String sql = "INSERT INTO hotel( city_id, hotel_name, address, description, detail, hotel_image, rating) VALUES(?,?,?,?,?,?,-1)";
	
		int t =jdbcTemplate.update(sql, new Object[] { hotel.getCity_id(), hotel.getHotel_name(), hotel.getAddress(),
				hotel.getDescription(), hotel.getDetail(), hotel.getHotel_image() });
		if(user.getRole_id() == 2) {
			String sql1 = "SELECT h.* FROM hotel AS h ORDER BY h.id_hotel DESC LIMIT ?";
			List<Hotel> hotels = jdbcTemplate.query(sql1, new Object[] {1}, new BeanPropertyRowMapper<Hotel>(Hotel.class));
			for (Hotel ht : hotels) {
				userDao.editIdHotel(user,ht.getId_hotel());
			}
		}
		return t;
	}

	public Hotel getHotel(int id_hotel) {
		String sql = "SELECT * FROM hotel WHERE id_hotel = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_hotel },
				new BeanPropertyRowMapper<Hotel>(Hotel.class));
	}

	public int editHotel(Hotel hotel) {
		String sql = "UPDATE hotel SET city_id = ?, hotel_name = ?, address = ?, description = ?, detail = ?, hotel_image = ? WHERE id_hotel = ? ";
		int t = jdbcTemplate.update(sql, new Object[] { hotel.getCity_id(), hotel.getHotel_name(), hotel.getAddress(),
				hotel.getDescription(), hotel.getDetail(), hotel.getHotel_image(), hotel.getId_hotel() });
		return t;
	}

	public int delHotel(int id_hotel) {
		String sql = "DELETE FROM hotel WHERE id_hotel = ?";
		return jdbcTemplate.update(sql, new Object[] { id_hotel });
	}

	public List<Hotel> getListHotelTop2() {
		String sql = "SELECT * FROM hotel ORDER BY rating DESC LIMIT 2,2";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Hotel>(Hotel.class));
	}

	public List<Hotel> getListHotelTop4() {
		String sql = "SELECT * FROM hotel ORDER BY rating DESC LIMIT 2";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Hotel>(Hotel.class));
	}

	public List<Hotel> getAllHotel() {
		String sql = "SELECT h.* FROM hotel h WHERE h.id_hotel NOT IN (SELECT u.hotel_id FROM user u)";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Hotel>(Hotel.class));
	}
	public int getCountHotels() {
		String sql = "SELECT COUNT(*) FROM hotel";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
