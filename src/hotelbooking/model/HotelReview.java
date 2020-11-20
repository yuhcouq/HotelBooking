package hotelbooking.model;

public class HotelReview {
	private int id_review;
	private int hotel_id;
	private int user_id;
	private String title;
	private String content;
	private float rating;
	private String hotel_name;
	private String firstname;
	private String lastname;
	
	public HotelReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelReview(int id_review, int hotel_id, int user_id, String title, String content, float rating,
			String hotel_name, String firstname, String lastname) {
		super();
		this.id_review = id_review;
		this.hotel_id = hotel_id;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.rating = rating;
		this.hotel_name = hotel_name;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getId_review() {
		return id_review;
	}

	public void setId_review(int id_review) {
		this.id_review = id_review;
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
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
}
