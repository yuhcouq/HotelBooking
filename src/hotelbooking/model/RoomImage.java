package hotelbooking.model;

public class RoomImage {
	private int id_image;
	private int id_room;
	private String image;
	
	public RoomImage(int id_image, int id_room, String image) {
		super();
		this.id_image = id_image;
		this.id_room = id_room;
		this.image = image;
	}
	
	public RoomImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_image() {
		return id_image;
	}
	public void setId_image(int id_image) {
		this.id_image = id_image;
	}
	public int getId_room() {
		return id_room;
	}
	public void setId_room(int id_room) {
		this.id_room = id_room;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
