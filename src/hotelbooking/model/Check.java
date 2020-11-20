package hotelbooking.model;

public class Check {
	private String checkin;
	private String checkout;
	private String adults;
	private String children;
	private String min_price;
	private String max_price;
	private String search;
	private int check;
	private int hotel_id;

	public Check() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Check(String checkin, String checkout, String adults, String children, String min_price, String max_price,
			String search, int check, int hotel_id) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
		this.adults = adults;
		this.children = children;
		this.min_price = min_price;
		this.max_price = max_price;
		this.search = search;
		this.check = check;
		this.hotel_id = hotel_id;
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

	public String getAdults() {
		return adults;
	}

	public void setAdults(String adults) {
		this.adults = adults;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getMin_price() {
		return min_price;
	}

	public void setMin_price(String min_price) {
		this.min_price = min_price;
	}

	public String getMax_price() {
		return max_price;
	}

	public void setMax_price(String max_price) {
		this.max_price = max_price;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
}
