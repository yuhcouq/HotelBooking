package hotelbooking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hotelbooking.constant.Defines;
import hotelbooking.model.Room;
import hotelbooking.model.RoomImage;
import hotelbooking.model.Hotel;
import java.util.Date;
import java.text.SimpleDateFormat;
@Repository
public class RoomDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public List<Room> getListRooms() {
		String sql = "SELECT r.*,h.hotel_name, c.city_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id WHERE r.del = 0 ORDER BY r.id_room DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}
	
	public List<Room> getListRoomAlls() {
		String sql = "SELECT r.*,h.hotel_name, c.city_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id ORDER BY r.id_room DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}
	
	public List<Room> getListRoomALLs() {
		String sql = "SELECT r.*,h.hotel_name, c.city_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id ORDER BY r.id_room DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}
	
	public int getCountListRoomAlls() {
		String sql = "SELECT COUNT(*) FROM room";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public int checkDiscountRoom(int id_room) {
		String sql = "SELECT COUNT(*) FROM discount WHERE room_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_room }, Integer.class);
	}
	
	public int getDiscountRoom(int id_room) {
		String sql = "SELECT discount FROM discount WHERE room_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_room }, Integer.class);
	}
	
	public int addDiscount(int id_room, int discount) {
		String sql = "INSERT INTO discount(room_id,discount) VALUE (?,?) ";
		return jdbcTemplate.update(sql, new Object[] { id_room,discount});
	}
	
	public int deleteDiscount(int id_room) {
		String sql = "DELETE FROM discount WHERE room_id = ?";
		return jdbcTemplate.update(sql, new Object[] { id_room});
	}
	
	public int getCountListRooms() {
		String sql = "SELECT COUNT(*) FROM room WHERE del = 0";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<Room> getListRoom10s() {
		String sql = "SELECT r.*,h.hotel_name, c.city_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id WHERE r.del = 0 ORDER BY r.rating DESC LIMIT 10";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}
	
	public List<Room> getListRoomForUserRecom10s(String id, int user_id) {
		if(id == "") {
			id = "-1";
		}
		String sql = "SELECT r.*,h.hotel_name, c.city_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id  WHERE r.del = 0 AND r.id_room NOT IN (SELECT room_id FROM booking WHERE user_id = ? GROUP BY room_id) ORDER BY FIELD(r.id_room,"+id+") DESC limit 10";
		return jdbcTemplate.query(sql, new Object[] { user_id }, new BeanPropertyRowMapper<Room>(Room.class));
	}
	
	public List<Room> getListRoomForUserRecomAlls(String id, int user_id) {
		if(id == "") {
			id = "-1";
		}
		String sql = "SELECT r.*,h.hotel_name, c.city_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id  WHERE r.del = 0 AND r.id_room NOT IN (SELECT room_id FROM booking WHERE user_id = ? GROUP BY room_id) ORDER BY FIELD(r.id_room,"+id+") DESC";
		return jdbcTemplate.query(sql, new Object[] { user_id }, new BeanPropertyRowMapper<Room>(Room.class));
	}
	
	public List<Room> getListRoomRecom10s(String id) {
		if(id == "") {
			id = "-1";
		}
		String sql = "SELECT r.*,h.hotel_name, c.city_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id WHERE r.id_room IN ("+id+") AND r.del = 0 ORDER BY rating DESC LIMIT 10";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}
	
	
	public List<Room> getListRoomcreate10s() {
		String sql = "SELECT r.*,h.hotel_name, c.city_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id WHERE r.del = 0 ORDER BY r.createdAT DESC LIMIT 6";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}
	
	public List<Room> getListRoomHot10s(Date date) {
		String sql = "SELECT r.*,h.hotel_name, c.city_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id INNER JOIN booking b ON b.room_id = r.id_room WHERE b.created_time >= ? AND r.del = 0 GROUP BY b.room_id ORDER BY COUNT(b.room_id) DESC LIMIT 10";
		return jdbcTemplate.query(sql, new Object[] { date }, new BeanPropertyRowMapper<Room>(Room.class));
	}
	
	public List<Room> getListRoomsLimit(int offset) {
		String sql = "SELECT r.*,h.hotel_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel WHERE r.del = 0 ORDER BY rating DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, Defines.ROW_COUNT_PUBLIC },
				new BeanPropertyRowMapper<Room>(Room.class));
	}
	public List<Room> getListRoomsHotelLimit(int offset, int id) {
		String sql = "SELECT r.*,h.hotel_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel WHERE r.hotel_id = ? AND r.del = 0 ORDER BY rating DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new Object[] { id,offset, Defines.ROW_COUNT_PUBLIC },
				new BeanPropertyRowMapper<Room>(Room.class));
	}
	public int addRoom(Room room,int id_hotel) {
		String sql = "INSERT INTO room(hotel_id, name, room_number, adult_number, children_number, bed_number, status, rating, price, prepayment, image, city_id, detail, description, acreage, service, CreatedAT, del) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0)";
		String sql2 = "SELECT city_id FROM hotel WHERE id_hotel = ?";
		List<Hotel> hotel = jdbcTemplate.query(sql2, new Object[] { id_hotel }, new BeanPropertyRowMapper<Hotel>(Hotel.class));
		for (Hotel s : hotel) {
			room.setCity_id(s.getCity_id());
        }
		return jdbcTemplate.update(sql,
				new Object[] { room.getHotel_id(), room.getName(), room.getRoom_number(), room.getAdult_number(),
						room.getChildren_number(), room.getBed_number(), 0, 1, room.getPrice(), room.getPrepayment(),
						room.getImage(), room.getCity_id(), room.getDetail(), room.getDescription(), room.getAcreage(), room.getService(),
						room.getCreatedAt() });
	}

	public Room getRoom(int id_room) {
		String sql = "SELECT r.*, h.hotel_name, h.hotel_image, c.city_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id WHERE id_room = ? AND r.del = 0";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_room }, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public int editRoom(Room room) {
		String sql = "UPDATE room SET name = ?, room_number = ?, adult_number = ?, children_number = ?, bed_number = ?, price = ?, prepayment = ?, image = ?, detail = ?, description = ?, acreage = ?, service = ?, UpdatedAT = ? WHERE id_room = ? AND del = 0 ";
		return jdbcTemplate.update(sql,
				new Object[] { room.getName(), room.getRoom_number(), room.getAdult_number(), room.getChildren_number(),
						room.getBed_number(), room.getPrice(), room.getPrepayment(), room.getImage(), room.getDetail(),
						room.getDescription(), room.getAcreage(), room.getService(), room.getUpdatedAt(),
						room.getId_room() });
	}

	public int delRoom(int id_room) {
		String sql = "UPDATE room SET del = 1 WHERE id_room = ?";
		return jdbcTemplate.update(sql, new Object[] { id_room });
	}

	public Room getRoomTop1(int id_hotel) {
		String sql = "SELECT * FROM room r INNER JOIN hotel h ON r.hotel_id = h.id_hotel WHERE id_hotel = ? AND r.del = 0 ORDER BY r.rating DESC LIMIT 1";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_hotel }, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public Room getRoomTop10(int id_hotel) {
		String sql = "SELECT * FROM room r INNER JOIN hotel h ON r.hotel_id = h.id_hotel INNER JOIN city c ON h.city_id = c.id_city WHERE hotel_id = ? AND r.del = 0 ORDER BY r.rating DESC LIMIT 1";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_hotel }, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public Room getRoomNews(Room room) {
		String sql = "SELECT * FROM room r WHERE hotel_id = ? AND room_number = ? AND r.del = 0 ORDER BY r.id_room DESC LIMIT 1";
		return jdbcTemplate.queryForObject(sql, new Object[] { room.getHotel_id(), room.getRoom_number() },
				new BeanPropertyRowMapper<Room>(Room.class));
	}

	public int addImage(int id_room, String image) {
		String sql = "INSERT INTO room_image(room_id, image) VALUES(?,?)";
		return jdbcTemplate.update(sql, new Object[] { id_room, image });
	}

	public List<Room> getListRooms(int id_hotel) {
		String sql = "SELECT r.* FROM room AS r WHERE hotel_id = ? AND r.del = 0";
		return jdbcTemplate.query(sql, new Object[] { id_hotel }, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public int deleteImages(int id_room) {
		String sql = "DELETE FROM room_image WHERE room_id = ?";
		return jdbcTemplate.update(sql, new Object[] { id_room });
	}

	public List<RoomImage> GetImages(int id_room) {
		String sql = "SELECT * FROM room_image WHERE room_id = ?";
		return jdbcTemplate.query(sql, new Object[] { id_room }, new BeanPropertyRowMapper<RoomImage>(RoomImage.class));
	}

	public List<RoomImage> GetListImages(int id_room) {
		String sql = "SELECT * FROM room_image WHERE room_id = ? ORDER BY id_image DESC LIMIT 4";
		return jdbcTemplate.query(sql, new Object[] { id_room }, new BeanPropertyRowMapper<RoomImage>(RoomImage.class));
	}

	public List<Room> getRooms() {
		String sql = "SELECT r.*, hotel_name, city_name FROM room r INNER JOIN hotel h ON r.hotel_id = h.id_hotel INNER JOIN city c ON c.id_city = h.city_id WHERE r.del = 0";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public int getCountImage(int id_room) {
		String sql = "SELECT COUNT(*) FROM room_image WHERE room_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_room }, Integer.class);
	}

	public RoomImage getImage(int id_image) {
		String sql = "SELECT * FROM room_image WHERE id_image = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_image },
				new BeanPropertyRowMapper<RoomImage>(RoomImage.class));
	}

	public int editImage(int id_image, String image) {
		String sql = "UPDATE room_image SET image = ? WHERE id_image = ? ";
		return jdbcTemplate.update(sql, new Object[] { image, id_image });
	}

	public int deleteImage(int id_image) {
		String sql = "DELETE FROM room_image WHERE id_image = ?";
		return jdbcTemplate.update(sql, new Object[] { id_image });
	}

	public List<Room> check_index(String sql) {
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}
	
	public List<Room> count_check_index(String sql) {
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public List<Room> check_room(String sql) {
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public List<Room> search(String hotel_name, int offset) {
		String sql = "SELECT r.*,h.hotel_name FROM room r INNER JOIN hotel h ON r.hotel_id = h.id_hotel WHERE h.hotel_name LIKE ? AND r.del = 0 ORDER BY id_room DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new Object[] { hotel_name, offset, Defines.ROW_COUNT_PUBLIC },
				new BeanPropertyRowMapper<Room>(Room.class));
	}

	public int getCountSearch(String hotel_name) {
		String sql = "SELECT COUNT(*) FROM room r INNER JOIN hotel h ON r.hotel_id = h.id_hotel WHERE r.del = 0 AND h.hotel_name LIKE ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { hotel_name }, Integer.class);
	}

	public int getCountRooms() {
		String sql = "SELECT COUNT(*) FROM room r INNER JOIN hotel h ON r.hotel_id = h.id_hotel WHERE r.del = 0";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public List<Room> getListRooms(int id_hotel, int offset) {
		String sql = "SELECT r.*,h.hotel_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel WHERE r.hotel_id = ? AND r.del = 0 ORDER BY id_hotel DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new Object[] { id_hotel, offset, Defines.ROW_COUNT_PUBLIC },
				new BeanPropertyRowMapper<Room>(Room.class));
	}

	public int getCountRoomsID(int id_hotel) {
		String sql = "SELECT COUNT(*) FROM room r INNER JOIN hotel h ON r.hotel_id = h.id_hotel WHERE r.hotel_id = ? AND r.del = 0";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_hotel }, Integer.class);
	}

	public List<Room> getListRoomsMoveData(String date) {
		String sql = "SELECT r.*,h.hotel_name,h.city_id FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel WHERE r.del = 0 AND (DAY(CreatedAT) = DAY(?) OR DAY(UpdatedAT) = DAY(?))";
		return jdbcTemplate.query(sql, new Object[] { date, date }, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public List<Room> getAllRoomsOfHotel(int hotel_id) {
		String sql = "SELECT r.*,h.hotel_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel WHERE r.hotel_id = ? AND r.del = 0 ORDER BY id_room DESC";
		return jdbcTemplate.query(sql, new Object[] { hotel_id }, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public List<Room> getRoomsAdd(int limit) {
		String sql = "SELECT * FROM room WHERE del = 0 ORDER BY RAND() LIMIT ?";
		return jdbcTemplate.query(sql, new Object[] { limit }, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public List<Room> getRoomsTop3() {
		String sql = "SELECT * FROM room WHERE del = 0 ORDER BY RAND() LIMIT 3";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public List<Room> getRoomsTop9() {
		String sql = "SELECT * FROM room WHERE del = 0 ORDER BY RAND() LIMIT 9";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
	}

	public int updateRating(Room room) {
		String sql = "UPDATE room SET rating = ? WHERE id_room = ? AND del = 0";
		return jdbcTemplate.update(sql, new Object[] { room.getRating(), room.getId_room() });
	}

}
