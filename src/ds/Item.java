package ds;

import java.sql.Date;

public class Item {
	
	private int id;
	private String name;
	private Double price;
	private int quantity;
	private String category;
	private String image;
	private int purchased_quantity;
	private Date date;
	
	public Item(int id, String name, Double price, int quantity, String category, String image) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.image = image;
	}

	public Item(int id, String name, Double price, String category, String image, int purchased_quantity, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.image = image;
		this.purchased_quantity = purchased_quantity;
		this.date = date;
	}





	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getPurchased_quantity() {
		return purchased_quantity;
	}

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPurchased_quantity(int purchased_quantity) {
		this.purchased_quantity = purchased_quantity;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", category="
				+ category + ", image=" + image + ", purchased_quantity=" + purchased_quantity + ", date=" + date + "]";
	}


	
	

}