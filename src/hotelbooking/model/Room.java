package hotelbooking.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Room implements Serializable {
	private int id_room;
	private int hotel_id;
	private String name;
	private int room_number;
	private int adult_number;
	private int children_number;
	private int bed_number;
	private int status;
	private Float rating;
	private String image;
	private String description;
	private String detail;
	private Float price;
	private int prepayment;
	private String hotel_name;
	private String hotel_image;
	private int city_id;
	private String city_name;
	private float acreage;
	private String wifi;
	private String television;
	private String conditioning;
	private String drinks;
	private String restaurant;
	private String service;
	private long quantity;
	private String checkin;
	private String checkout;
	private String createdAt;
	private String updatedAt;
	private int del;
	private int discount;
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Room(int id_room, int hotel_id, String name, int room_number, int adult_number, int children_number,
			int bed_number, int status, Float rating, String image, String description, String detail, Float price,
			int prepayment, String hotel_name, String hotel_image, int city_id, String city_name, float acreage,
			String wifi, String television, String conditioning, String drinks, String restaurant, String service,
			long quantity, String checkin, String checkout, String createdAt, String updatedAt, int del,int discount) {
		super();
		this.id_room = id_room;
		this.hotel_id = hotel_id;
		this.name = name;
		this.room_number = room_number;
		this.adult_number = adult_number;
		this.children_number = children_number;
		this.bed_number = bed_number;
		this.status = status;
		this.rating = rating;
		this.image = image;
		this.description = description;
		this.detail = detail;
		this.price = price;
		this.prepayment = prepayment;
		this.hotel_name = hotel_name;
		this.hotel_image = hotel_image;
		this.city_id = city_id;
		this.city_name = city_name;
		this.acreage = acreage;
		this.wifi = wifi;
		this.television = television;
		this.conditioning = conditioning;
		this.drinks = drinks;
		this.restaurant = restaurant;
		this.service = service;
		this.quantity = quantity;
		this.checkin = checkin;
		this.checkout = checkout;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.del = del;
		this.discount = discount;
	}
	public int getId_room() {
		return id_room;
	}
	public void setId_room(int id_room) {
		this.id_room = id_room;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	public int getAdult_number() {
		return adult_number;
	}
	public void setAdult_number(int adult_number) {
		this.adult_number = adult_number;
	}
	public int getChildren_number() {
		return children_number;
	}
	public void setChildren_number(int children_number) {
		this.children_number = children_number;
	}
	public int getBed_number() {
		return bed_number;
	}
	public void setBed_number(int bed_number) {
		this.bed_number = bed_number;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public int getPrepayment() {
		return prepayment;
	}
	public void setPrepayment(int prepayment) {
		this.prepayment = prepayment;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getHotel_image() {
		return hotel_image;
	}
	public void setHotel_image(String hotel_image) {
		this.hotel_image = hotel_image;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public float getAcreage() {
		return acreage;
	}
	public void setAcreage(float acreage) {
		this.acreage = acreage;
	}
	public String getWifi() {
		return wifi;
	}
	public void setWifi(String wifi) {
		this.wifi = wifi;
	}
	public String getTelevision() {
		return television;
	}
	public void setTelevision(String television) {
		this.television = television;
	}
	public String getConditioning() {
		return conditioning;
	}
	public void setConditioning(String conditioning) {
		this.conditioning = conditioning;
	}
	public String getDrinks() {
		return drinks;
	}
	public void setDrinks(String drinks) {
		this.drinks = drinks;
	}
	public String getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
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
}
