package hotelbooking.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import hotelbooking.constant.Defines;
import hotelbooking.dao.BookingDao;
import hotelbooking.dao.CityDao;
import hotelbooking.dao.RoomDao;
import hotelbooking.dao.UserDao;
import hotelbooking.model.Booking;
import hotelbooking.model.City;
import hotelbooking.model.Room;
import hotelbooking.model.User;

@Controller
@RequestMapping("/admin")
public class AdminMoveData {
	@Autowired
	private Defines defines;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private CityDao cityDao;

	@RequestMapping("/movedata")
	public String index(ModelMap model) {
		Session session = defines.ConnectNeo4j();
		StatementResult result;
		LocalDate today = LocalDate.now();
		String presentDate = defines.getDateDay();

		/******** START - USER ***********/
		// List user created or updated
		List<User> listUserMove = userDao.getListUsersMoveData(presentDate);

		String value = "";
		String query = "";
		long year = 0;
		for (User user : listUserMove) {
			LocalDate localDate = LocalDate.parse(user.getBirthday());
			year = Period.between(localDate, today).getYears();

			value = "user.id_user=%d, " + "user.firstname='%s', " + "user.lastname='%s', " + "user.age=%d, "
					+ "user.gender=%d, " + "user.address='%s', "
					+ "user.location=point({latitude:toFloat(%.6f),longitude:toFloat(%.6f)})";
			value = String.format(value, user.getId_user(), user.getFirstname(), user.getLastname(), year,
					user.getGender(), user.getAddress(), user.getLatitude(), user.getLongitude());

			// Create User and Create
			query = " MERGE (user:user {id_user:%d }) " + "ON CREATE SET %s " + "ON MATCH SET %s  "
					+ "WITH user MATCH (other: user) "
					+ "WHERE user.id_user <> other.id_user AND user.gender = other.gender "
					+ "MERGE (user)-[r1:SIMILAR_GENDER]-(other)  " + "ON MATCH SET r1.score = 1 "
					+ "ON CREATE SET r1.score= 1 " + "WITH user, other "
					+ "WHERE user.id_user <> other.id_user AND abs(user.age - other.age) < 3 "
					+ "MERGE (user)-[r1:SIMILAR_BIRTHDAY]-(other)  " + "ON MATCH SET r1.score = 2 "
					+ "ON CREATE SET r1.score= 2 " + "WITH user, other  "
					+ "WITH distance(user.location, other.location) as distance, user, other "
					+ "WHERE user.id_user <> other.id_user AND distance <= 20000 MERGE (user)-[r1:SIMILAR_LOCATION]-(other) "
					+ "ON MATCH SET r1.score= 3 " + "ON CREATE SET r1.score = 3";

			query = String.format(query, user.getId_user(), value, value);
			result = session.run(query);
		}

		// create relationship [SIMILAR]
		query = "MATCH (a:user)-[r:SIMILAR_GENDER|:SIMILAR_BIRTHDAY|:SIMILAR_LOCATION]-(b:user) WITH a, b, sum(r.score) as sum MERGE (a)-[r1:SIMILAR]-(b) ON CREATE SET r1.score = sum ON MATCH SET r1.score= sum";
		result = session.run(query);

		// Delete relationship [SIMILAR_GENDER|:SIMILAR_BIRTHDAY|:SIMILAR_LOCATION]
		query = "MATCH (a:user)-[r:SIMILAR_GENDER|:SIMILAR_BIRTHDAY|:SIMILAR_LOCATION]-(b:user) WITH r DELETE r";
		result = session.run(query);

		// delete relationship [SIMILAR] when update gender user
		for (User user : listUserMove) {
			if (user.getGender() == 0) {
				query = String.format("MATCH (a:user{id_user:%d})-[r:SIMILAR]-(b:user{gender:1}) WITH r DELETE r",
						user.getId_user());
			} else {
				query = String.format("MATCH (a:user{id_user:%d})-[r:SIMILAR]-(b:user{gender:0}) WITH r DELETE r",
						user.getId_user());
			}
			result = session.run(query);
		}
		/******** END - ROOM ***********/

		/******** START - ROOM ***********/
		// get list room created or updated
		List<Room> listRoom = roomDao.getListRoomsMoveData(presentDate);
		for (Room room : listRoom) {
			value = "room.id_room=%d, " + "room.hotel_id=%d, " + "room.name='%s', " + "room.hotel_name='%s', "
					+ "room.room_number=%d, " + "room.adult_number=%d, " + "room.children_number=%d, "
					+ "room.bed_number=%d, " + "room.price=%.2f, " + "room.image='%s', " + "room.rating=%.2f, "
					+ "room.service='%s', " + "room.city_id = %d";

			value = String.format(value, room.getId_room(), room.getHotel_id(), room.getName(), room.getHotel_name(),
					room.getRoom_number(), room.getAdult_number(), room.getChildren_number(), room.getBed_number(),
					room.getPrice(), room.getImage(), room.getRating(), room.getService(), room.getCity_id());

			query = " MERGE (room:room {id_room:%d }) " + "ON CREATE SET %s " + "ON MATCH SET %s";
			query = String.format(query, room.getId_room(), value, value);
			result = session.run(query);
		}

		query = "MATCH (ro:room), (other:room) WHERE ro.id_room <> other.id_room AND ro.hotel_id = other.hotel_id  MERGE (ro)-[r:GENERAL]-(other)  ON MATCH SET r.score = 1 ON CREATE SET r.score= 1";
		result = session.run(query);

		query = "MATCH (ro:room), (other:room) WHERE ro.id_room <> other.id_room AND abs(ro.rating - other.rating) < 1.1 MERGE (ro)-[r:RATING]-(other) ON MATCH SET r.score = 1 ON CREATE SET r.score= 1";
		result = session.run(query);

		query = "MATCH (ro:room), (other:room) WHERE ro.id_room <> other.id_room AND abs(ro.price - other.price) < 1000000 MERGE (ro)-[r:PRICE]-(other) ON MATCH SET r.score = 1 ON CREATE SET r.score= 1";
		result = session.run(query);

		query = "MATCH (ro:room), (other:room) WHERE ro.id_room <> other.id_room AND ro.adult_number = other.adult_number MERGE (ro)-[r:ADULT]-(other) ON MATCH SET r.score = 1 ON CREATE SET r.score= 1";
		result = session.run(query);

		query = "MATCH (ro:room), (other:room) WHERE ro.id_room <> other.id_room AND ro.children_number = other.children_number MERGE (ro)-[r:CHILDREN]-(other) ON MATCH SET r.score = 1 ON CREATE SET r.score= 1";
		result = session.run(query);

		query = "MATCH (ro:room), (other:room) WHERE ro.id_room <> other.id_room AND ro.bed_number = other.bed_number MERGE (ro)-[r:BED]-(other) ON MATCH SET r.score = 1 ON CREATE SET r.score= 1";
		result = session.run(query);
		
		query = "MATCH (ro:room)-[r:GENERAL|:RATING|:PRICE|:ADULT|:CHILDREN|BED]-(other:room) WITH ro, other, sum(r.score) as sum MERGE (ro)-[r1:SIMILAR_HOTEL]-(other) ON CREATE SET r1.score = sum ON MATCH SET r1.score= sum";
		query = "MATCH (ro:room)-[r:GENERAL|:RATING|:PRICE|:ADULT|:CHILDREN|BED]-(other:room) WITH r DELETE r";
		result = session.run(query);

		// get list booking created
		List<Booking> listBooking = bookingDao.getListBookingMoveData();
		for (Booking booking : listBooking) {
			query = "MATCH (u:user {id_user:%d}), (r:room {id_room:%d}) WITH u, r MERGE (u)-[i:INTERACTIVE]-(r) ON CREATE SET i.score = 1 ON MATCH SET i.score = i.score + 1";

			query = String.format(query, booking.getUser_id(), booking.getRoom_id());
			result = session.run(query);
		}

		// update check_move data
		bookingDao.updateCheckMove();

		// Select 5 room của 3 user gióng vs user
		// query = "MATCH (u: user { id_user: 21 })-[r:SIMILAR]-(other:user) WITH other,
		// SUM(r.score) as similar_score, u ORDER BY similar_score DESC LIMIT 3 OPTIONAL
		// MATCH (other)-[i1:INTERACTIVE]-(r:room) WITH r, u, other, i1.score +
		// similar_score as recommendation ORDER BY recommendation DESC RETURN u, other
		// LIMIT 5";

		/******** END - ROOM ***********/

		/******** STRAT - CITY ***********/
		List<City> listcity = cityDao.getListCityMoveData(presentDate);
		for (City city : listcity) {
			value = "city.listcity = %d, city.city_name = '%s'";
			value = String.format(value, city.getId_city(), city.getCity_name());

			query = " MERGE (city:city {id_city:%d }) " + "ON CREATE SET %s "
					+ "ON MATCH SET %s WITH city AS c MATCH (r:room{city_id:%d}) MERGE (c)-[i:HAS_ROOM]-(r) ON CREATE SET i.score = 1 ON MATCH SET i.score = 1";
			query = String.format(query, city.getId_city(), value, value, city.getId_city());

			result = session.run(query);
		}
		/******** END - CITY ***********/

		return "redirect:/admin/room/index";
	}
}
