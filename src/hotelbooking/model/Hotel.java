package hotelbooking.model;

public class Hotel {
	private int id_hotel;
	private String hotel_name;
	private String address;
	private String description;
	private String detail;
	private String hotel_image;
	private Float rating;
	private int city_id;
	private String city_name;

	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hotel(int id_hotel, String hotel_name, String address, String description, String detail, String hotel_image,
			Float rating, int city_id, String city_name) {
		super();
		this.id_hotel = id_hotel;
		this.hotel_name = hotel_name;
		this.address = address;
		this.description = description;
		this.detail = detail;
		this.hotel_image = hotel_image;
		this.rating = rating;
		this.city_id = city_id;
		this.city_name = city_name;
	}

	public int getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getHotel_image() {
		return hotel_image;
	}

	public void setHotel_image(String hotel_image) {
		this.hotel_image = hotel_image;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
}
