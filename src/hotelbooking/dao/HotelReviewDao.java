package hotelbooking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hotelbooking.model.HotelReview;

@Repository
public class HotelReviewDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<HotelReview> getListHotelReviews() {
		String sql = "SELECT hr.*,h.hotel_name,u.firstname,u.lastname FROM hotel_review hr INNER JOIN hotel h ON hr.hotel_id = h.id_hotel INNER JOIN user u ON hr.user_id = u.id_user";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<HotelReview>(HotelReview.class));
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

	public int delHotelReview(int id_review) {
		String sql = "DELETE FROM hotel_review WHERE id_review = ?";
		return jdbcTemplate.update(sql, new Object[] { id_review });
	}
	
}
