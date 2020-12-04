package hotelbooking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hotelbooking.model.Login;
import hotelbooking.model.User;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<User> getNewDataUser() {
		String sql = "SELECT * FROM user ORDER BY id_user DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getListUsers() {
		String sql = "SELECT * FROM user WHERE del = 0";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public List<User> CheckCity(int id_user) {
		String sql = "SELECT COUNT(*) FROM user WHERE id_user= ?";
		return jdbcTemplate.query(sql, new Object[] {id_user }, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public List<User> getListUserAlls() {
		String sql = "SELECT * FROM user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public int getCountListUserAlls() {
		String sql = "SELECT COUNT(*) FROM user";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public int getCountListUsers() {
		String sql = "SELECT COUNT(*) FROM user WHERE del=0";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int addUser(User user) {
		String sql = "INSERT INTO user(role_id, hotel_id, username, password, avatar, firstname, lastname, birthday, city, address, phone, gender, email, CreatedAT, latitude, longitude,del) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0)";
		return jdbcTemplate.update(sql,
				new Object[] { user.getRole_id(), user.getHotel_id(), user.getUsername(), user.getPassword(), user.getAvatar(), user.getFirstname(),
						user.getLastname(), user.getBirthday(), user.getCity(), user.getAddress(), user.getPhone(),
						user.getGender(), user.getEmail(), user.getCreatedAt(), user.getLatitude(),
						user.getLongitude() });
	}

	public User getUser(int id_user) {
		String sql = "SELECt * FROM user WHERE id_user = ? AND del = 0";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_user }, new BeanPropertyRowMapper<User>(User.class));
	}

	public int editIdHotel(User user,int id) {
		String sql = "UPDATE user SET hotel_id = ? WHERE id_user = ? AND del = 0";
		return jdbcTemplate.update(sql,
				new Object[] {id, user.getId_user()});
	}
	public int editUser(User user) {
		String sql = "UPDATE user SET username = ?,password = ?,avatar = ?, firstname = ?, lastname = ?, birthday = ?, city = ?, address = ?, phone = ?, gender = ?, email = ?, UpdatedAT = ? WHERE id_user = ? AND del = 0";
		return jdbcTemplate.update(sql,
				new Object[] { user.getUsername(), user.getPassword(), user.getAvatar(), user.getFirstname(),
						user.getLastname(), user.getBirthday(), user.getCity(), user.getAddress(), user.getPhone(),
						user.getGender(), user.getEmail(), user.getUpdatedAt(), user.getId_user() });
	}
	public int editUserByAdmin(User user) {
		String sql = "UPDATE user SET username = ?,hotel_id = ?,password = ?,avatar = ?, firstname = ?, lastname = ?, birthday = ?, city = ?, address = ?, phone = ?, gender = ?, email = ?, UpdatedAT = ? WHERE id_user = ? AND del = 0";
		return jdbcTemplate.update(sql,
				new Object[] { user.getUsername(), user.getHotel_id(), user.getPassword(), user.getAvatar(), user.getFirstname(),
						user.getLastname(), user.getBirthday(), user.getCity(), user.getAddress(), user.getPhone(),
						user.getGender(), user.getEmail(), user.getUpdatedAt(), user.getId_user() });
	}

	public int delUser(int id_user) {
		String sql = "UPDATE user SET del = 1 WHERE id_user = ?";
		return jdbcTemplate.update(sql, new Object[] { id_user });
	}

	public List<User> getListUsersMoveData(String date) {
		String sql = "SELECT * FROM user WHERE del = 0 AND (DAY(CreatedAT) = DAY(?) OR DAY(UpdatedAT) = DAY(?))";
		return jdbcTemplate.query(sql, new Object[] { date, date }, new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getListUsers(String emailSignIn) {
		String sql = "SELECT * FROM user WHERE email = ? AND del= 0";
		return jdbcTemplate.query(sql, new Object[] { emailSignIn }, new BeanPropertyRowMapper<User>(User.class));
	}

	public int getUser(String emailSignUp) {
		String sql = "SELECT COUNT(*) FROM user WHERE email = ? AND del= 0";
		return jdbcTemplate.queryForObject(sql, new Object[] { emailSignUp }, Integer.class);
	}

	public int addUser(Login login, String date) {
		String sql = "INSERT INTO user(role_id,hotel_id, email, password, firstname, lastname, gender, CreatedAT, latitude, longitude,del) VALUES (4,?,?,?,?,?,1,?,?,?,0)";
		return jdbcTemplate.update(sql,
				new Object[] { 0,login.getEmailSignUp(), login.getPasswordSignUp(), login.getFirstnameSignUp(),
						login.getLastnameSignUp(), date, login.getLatitude(), login.getLongitude() });
	}

	public int updateUser(User user) {
		String sql = "UPDATE user SET firstname = ?, lastname = ?, phone = ?, email = ?, gender = ?, birthday = ?, city = ?, address = ?, UpdatedAT = ? WHERE id_user = ?";
		return jdbcTemplate.update(sql,
				new Object[] { user.getFirstname(), user.getLastname(), user.getPhone(), user.getEmail(),
						user.getGender(), user.getBirthday(), user.getCity(), user.getAddress(), user.getUpdatedAt(),
						user.getId_user() });
	}

	public List<User> getAllUsers(int role) {
		String sql = "SELECT * FROM user WHERE role_id = ? AND del = 0 ORDER BY id_user DESC";
		return jdbcTemplate.query(sql, new Object[] { role }, new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getAllPartner() {
		String sql = "SELECT * FROM user WHERE role_id = 2 AND del = 0 ORDER BY id_user DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getAllStaff(int role, int hotel_id) {
		String sql = "SELECT * FROM user WHERE role_id = ? AND hotel_id = ? AND del = 0 ORDER BY id_user DESC";
		return jdbcTemplate.query(sql, new Object[] { role, hotel_id }, new BeanPropertyRowMapper<User>(User.class));
	}
}
