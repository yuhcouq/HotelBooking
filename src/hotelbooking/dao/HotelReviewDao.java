package hotelbooking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hotelbooking.model.HotelReview;
import hotelbooking.model.RoomReview;

@Repository
public class HotelReviewDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<HotelReview> getListHotelReviews() {
		String sql = "SELECT hr.*,h.hotel_name,u.firstname,u.lastname FROM hotel_review hr INNER JOIN hotel h ON hr.hotel_id = h.id_hotel INNER JOIN user u ON hr.user_id = u.id_user";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<HotelReview>(HotelReview.class));
	}
	
	public List<HotelReview> getListReviewOfHotels(int id) {
		String sql = "SELECT hr.*,h.hotel_name,u.firstname,u.lastname,u.avatar FROM hotel_review hr INNER JOIN hotel h ON hr.hotel_id = h.id_hotel INNER JOIN user u ON hr.user_id = u.id_user WHERE hr.hotel_id = ?";
		return jdbcTemplate.query(sql,new Object[] { id },new BeanPropertyRowMapper<HotelReview>(HotelReview.class));
	}
	
	public List<HotelReview> getListReviewOfUsers(int id) {
		String sql = "SELECT hr.*,h.hotel_name,u.firstname,u.lastname FROM hotel_review hr INNER JOIN hotel h ON hr.hotel_id = h.id_hotel INNER JOIN user u ON hr.user_id = u.id_user WHERE hr.user_id = ?";
		return jdbcTemplate.query(sql,new Object[] { id },new BeanPropertyRowMapper<HotelReview>(HotelReview.class));
	}
	
	public int getCheckReviewOfUsers(int id) {
		String sql = "SELECT COUNT(*) FROM hotel_review WHERE user_id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] { id }, Integer.class);
	}

	public HotelReview getHotelReview(int id_review) {
		String sql = "SELECT hr.*,h.hotel_name,u.firstname,u.lastname FROM hotel_review hr INNER JOIN hotel h ON hr.hotel_id = h.id_hotel INNER JOIN user u ON hr.user_id = u.id_user WHERE id_review = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_review },
				new BeanPropertyRowMapper<HotelReview>(HotelReview.class));
	}

	public int editHotelReview(HotelReview hotelReview) {
		String sql = "UPDATE hotel_review SET title = ?, content = ?, rating = ? WHERE id_review = ? ";
		return jdbcTemplate.update(sql, new Object[] { hotelReview.getTitle(), hotelReview.getContent(),hotelReview.getRating() , hotelReview.getId_review()});
	}
	
	public int editHotelReviewRequest(int id) {
		String sql = "UPDATE hotel_review SET request = 1 WHERE id_review = ? ";
		return jdbcTemplate.update(sql, new Object[] {id});
	}

	public int delHotelReview(int id_review) {
		String sql = "DELETE FROM hotel_review WHERE id_review = ?";
		return jdbcTemplate.update(sql, new Object[] { id_review });
	}
	
	public int InsertReview(HotelReview hotelReview, int id_hotel, int id_user) {
		String sql = "INSERT INTO hotel_review(user_id, hotel_id, content, rating, request, create_time) VALUES(?,?,?,?,0,?)";
		return jdbcTemplate.update(sql, new Object[] { id_user, id_hotel, hotelReview.getContent(),
				hotelReview.getRating(), hotelReview.getCreate_time() });
	}
	
}
