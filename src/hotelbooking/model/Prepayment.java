package hotelbooking.model;

public class Prepayment {
	private int id_prepayment;
	private int percent;
	
	public Prepayment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prepayment(int id_prepayment, int percent) {
		super();
		this.id_prepayment = id_prepayment;
		this.percent = percent;
	}

	public int getId_prepayment() {
		return id_prepayment;
	}

	public void setId_prepayment(int id_prepayment) {
		this.id_prepayment = id_prepayment;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
}
