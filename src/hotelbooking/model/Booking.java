package hotelbooking.model;

public class Booking {
	private static int code_auto = 243632;

	private int id_booking;
	private int code;
	private int Hotel_id;
	private String hotel_name;
	private int room_id;
	private int room_number;
	private String image;
	private Float price;
	private int user_id;
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private int gender;
	private String birthday;
	private String city;
	private String address;
	private String checkin;
	private String checkout;
	private int day;
	private Float total_price;
	private int paid;
	private int prepayment;
	private int status;
	private String note;
	private int check_move;
	private String created_time;
	private Room room;

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(int id_booking, int code, int hotel_id, String hotel_name, int room_id, int room_number,
			String image, Float price, int user_id, String firstname, String lastname, String phone, String email,
			int gender, String birthday, String city, String address, String checkin, String checkout, int day,
			Float total_price, int paid, int prepayment, int status, String note, int check_move, String created_time,
			Room room) {
		super();
		this.id_booking = id_booking;
		this.code = code;
		Hotel_id = hotel_id;
		this.hotel_name = hotel_name;
		this.room_id = room_id;
		this.room_number = room_number;
		this.image = image;
		this.price = price;
		this.user_id = user_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		this.city = city;
		this.address = address;
		this.checkin = checkin;
		this.checkout = checkout;
		this.day = day;
		this.total_price = total_price;
		this.paid = paid;
		this.prepayment = prepayment;
		this.status = status;
		this.note = note;
		this.check_move = check_move;
		this.created_time = created_time;
		this.room = room;
	}

	public static int getCode_auto() {
		return code_auto;
	}

	public static void setCode_auto(int code_auto) {
		Booking.code_auto = code_auto;
	}

	public int getId_booking() {
		return id_booking;
	}

	public void setId_booking(int id_booking) {
		this.id_booking = id_booking;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getHotel_id() {
		return Hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		Hotel_id = hotel_id;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getRoom_number() {
		return room_number;
	}

	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
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

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Float total_price) {
		this.total_price = total_price;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(int prepayment) {
		this.prepayment = prepayment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getCheck_move() {
		return check_move;
	}

	public void setCheck_move(int check_move) {
		this.check_move = check_move;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}