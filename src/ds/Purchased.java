package ds;

import java.time.LocalDate;

public class Purchased {
	
	private int id;
	private int staff_id;
	private int customer_id;
	private LocalDate purchased_date;
	
	public Purchased(int id, int staff_id, int customer_id, LocalDate purchased_date) {
		super();
		this.id = id;
		this.staff_id = staff_id;
		this.customer_id = customer_id;
		this.purchased_date = purchased_date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public LocalDate getPurchased_date() {
		return purchased_date;
	}
	public void setPurchased_date(LocalDate purchased_date) {
		this.purchased_date = purchased_date;
	}

	@Override
	public String toString() {
		return "Purchased [id=" + id + ", staff_id=" + staff_id + ", customer_id=" + customer_id + ", purchased_date="
				+ purchased_date + "]";
	}
	
	

}
