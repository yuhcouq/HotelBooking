package hotelbooking.model;

public class RoomReview {
	private int id_review;
	private int  user_id;
	private int room_id;
	private int hotel_id;
	private String content;
	private Float rating;
	private int request;
	private int room_number;
	private String firstname;
	private String lastname;
	private String name;
	private String hotel_name;
	private String create_time;
	private String avatar;
	
	public RoomReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomReview(int id_review, int user_id, int room_id, int hotel_id, String content, Float rating, int request,
			int room_number, String firstname, String lastname,String name, String hotel_name, String create_time, String avatar) {
		super();
		this.id_review = id_review;
		this.user_id = user_id;
		this.room_id = room_id;
		this.hotel_id = hotel_id;
		this.content = content;
		this.rating = rating;
		this.request = request;
		this.room_number = room_number;
		this.firstname = firstname;
		this.lastname = lastname;
		this.hotel_name = hotel_name;
		this.name = name;
		this.create_time = create_time;
		this.avatar = avatar;
	}

	public int getId_review() {
		return id_review;
	}

	public void setId_review(int id_review) {
		this.id_review = id_review;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	public int getRequest() {
		return request;
	}

	public void setRequest(int request) {
		this.request = request;
	}

	public int getRoom_number() {
		return room_number;
	}

	public void setRoom_number(int room_number) {
		this.room_number = room_number;
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

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
