package hotelbooking.model;

public class City {
	private int id_city;
	private String city_name;
	private String country;
	private int del;
	
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(int id_city, String city_name, String country, int del) {
		super();
		this.id_city = id_city;
		this.city_name = city_name;
		this.country = country;
		this.del = del;
	}

	public int getId_city() {
		return id_city;
	}

	public void setId_city(int id_city) {
		this.id_city = id_city;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}
