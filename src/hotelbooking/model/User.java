package hotelbooking.model;

public class User {
	private int id_user;
	private int role_id;
	private int hotel_id;
	private String hotel_name;
	private String username;
	private String password;
	private String avatar;
	private String firstname;
	private String lastname;
	private String birthday;
	private String city;
	private String address;
	private String phone;
	private int gender;
	private String email;
	private String createdAt;
	private String updatedAt;
	private Double latitude;
	private Double longitude;
	private String note;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id_user, int role_id, int hotel_id, String hotel_name, String username, String password,
			String avatar, String firstname, String lastname, String birthday, String city, String address,
			String phone, int gender, String email, String createdAt, String updatedAt, Double latitude,
			Double longitude, String note) {
		super();
		this.id_user = id_user;
		this.role_id = role_id;
		this.hotel_id = hotel_id;
		this.hotel_name = hotel_name;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.city = city;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
		this.email = email;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.latitude = latitude;
		this.longitude = longitude;
		this.note = note;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
