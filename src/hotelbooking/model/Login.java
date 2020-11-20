package hotelbooking.model;

public class Login {
	private int check;
	private String emailSignIn;
	private String passwordSignIn;
	private String firstnameSignUp;
	private String lastnameSignUp;
	private String emailSignUp;
	private String passwordSignUp;
	private Double latitude;
	private Double longitude;
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Login(int check, String emailSignIn, String passwordSignIn, String firstnameSignUp, String lastnameSignUp,
			String emailSignUp, String passwordSignUp, Double latitude, Double longitude) {
		super();
		this.check = check;
		this.emailSignIn = emailSignIn;
		this.passwordSignIn = passwordSignIn;
		this.firstnameSignUp = firstnameSignUp;
		this.lastnameSignUp = lastnameSignUp;
		this.emailSignUp = emailSignUp;
		this.passwordSignUp = passwordSignUp;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public String getEmailSignIn() {
		return emailSignIn;
	}
	public void setEmailSignIn(String emailSignIn) {
		this.emailSignIn = emailSignIn;
	}
	public String getPasswordSignIn() {
		return passwordSignIn;
	}
	public void setPasswordSignIn(String passwordSignIn) {
		this.passwordSignIn = passwordSignIn;
	}
	public String getFirstnameSignUp() {
		return firstnameSignUp;
	}
	public void setFirstnameSignUp(String firstnameSignUp) {
		this.firstnameSignUp = firstnameSignUp;
	}
	public String getLastnameSignUp() {
		return lastnameSignUp;
	}
	public void setLastnameSignUp(String lastnameSignUp) {
		this.lastnameSignUp = lastnameSignUp;
	}
	public String getEmailSignUp() {
		return emailSignUp;
	}
	public void setEmailSignUp(String emailSignUp) {
		this.emailSignUp = emailSignUp;
	}
	public String getPasswordSignUp() {
		return passwordSignUp;
	}
	public void setPasswordSignUp(String passwordSignUp) {
		this.passwordSignUp = passwordSignUp;
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
}
