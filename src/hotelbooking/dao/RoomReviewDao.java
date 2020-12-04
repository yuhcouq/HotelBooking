package hotelbooking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hotelbooking.model.RoomReview;

@Repository
public class RoomReviewDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public RoomReview getRoomReview(int id_review) {
		String sql = "SELECT rr.*,r.room_number,u.firstname,u.lastname,h.hotel_name FROM room_review rr INNER JOIN room r ON rr.room_id = r.id_room INNER JOIN user u ON rr.user_id = u.id_user INNER JOIN hotel h ON r.hotel_id = h.id_hotel Where id_review = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_review },
				new BeanPropertyRowMapper<RoomReview>(RoomReview.class));
	}

	public int editRoomReview(RoomReview roomReview) {
		String sql = "UPDATE room_review SET content = ?, rating = ? WHERE id_review = ? ";
		return jdbcTemplate.update(sql,
				new Object[] { roomReview.getContent(), roomReview.getRating(), roomReview.getId_review() });
	}
	
	public int editRoomReviewRequest(int id) {
		String sql = "UPDATE room_review SET request = 1 WHERE id_review = ? ";
		return jdbcTemplate.update(sql,
				new Object[] { id });
	}

	public int delRoomReview(int id_review) {
		String sql = "DELETE FROM room_review WHERE id_review = ?";
		return jdbcTemplate.update(sql, new Object[] { id_review });
	}

	public List<RoomReview> getListRoomReviews() {
		String sql = "SELECT rr.*,r.room_number,u.firstname,u.lastname,h.hotel_name FROM room_review rr INNER JOIN room r ON rr.room_id = r.id_room INNER JOIN user u ON rr.user_id = u.id_user INNER JOIN hotel h ON r.hotel_id = h.id_hotel";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<RoomReview>(RoomReview.class));
	}
	public List<RoomReview> getListRoomReviewHotels(int id) {
		String sql = "SELECT rr.*,r.room_number,u.firstname,u.lastname,h.hotel_name FROM room_review rr INNER JOIN room r ON rr.room_id = r.id_room INNER JOIN user u ON rr.user_id = u.id_user INNER JOIN hotel h ON r.hotel_id = h.id_hotel WHERE r.hotel_id = ?";
		return jdbcTemplate.query(sql,new Object[] { id }, new BeanPropertyRowMapper<RoomReview>(RoomReview.class));
	}

	public List<RoomReview> getListReviews(int id_room) {
		String sql = "SELECT rr.*,u.firstname, u.lastname,u.avatar FROM room_review rr INNER JOIN user u ON rr.user_id = u.id_user WHERE room_id = ?";
		return jdbcTemplate.query(sql, new Object[] { id_room },
				new BeanPropertyRowMapper<RoomReview>(RoomReview.class));
	}
	
	public List<RoomReview> getListReviewUsers(int id_user) {
		String sql = "SELECT rr.*,r.room_number,r.name,h.hotel_name FROM room_review rr INNER JOIN room r ON rr.room_id = r.id_room INNER JOIN hotel h ON r.hotel_id = h.id_hotel WHERE rr.user_id = ?";
		return jdbcTemplate.query(sql,new Object[] { id_user }, new BeanPropertyRowMapper<RoomReview>(RoomReview.class));
	}

	public int delRoomReviews(int id_room) {
		String sql = "DELETE FROM room_review WHERE room_id = ?";
		return jdbcTemplate.update(sql, new Object[] { id_room });
	}

	public int InsertReview(RoomReview roomReview, int id_room, int id_user) {
		String sql = "INSERT INTO room_review(user_id, room_id, content, rating, request, create_time) VALUES(?,?,?,?,0,?)";
		return jdbcTemplate.update(sql, new Object[] { id_user, id_room, roomReview.getContent(),
				roomReview.getRating(), roomReview.getCreate_time() });
	}
}
