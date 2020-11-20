package hotelbooking.model;

public class Slide {
	private int id_slide;
	private int status;
	private String image;
	
	public Slide() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Slide(int id_slide, int status, String image) {
		super();
		this.id_slide = id_slide;
		this.status = status;
		this.image = image;
	}

	public int getId_slide() {
		return id_slide;
	}


	public void setId_slide(int id_slide) {
		this.id_slide = id_slide;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
}
