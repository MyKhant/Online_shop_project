package ds;

public class Purchased_item {
	
	private int purchased_id;
	private int item_id;
	private int quantity;
	
	public Purchased_item(int purchased_id, int item_id, int quantity) {
		super();
		this.purchased_id = purchased_id;
		this.item_id = item_id;
		this.quantity = quantity;
	}
	
	public int getPurchased_id() {
		return purchased_id;
	}
	public void setPurchased_id(int purchased_id) {
		this.purchased_id = purchased_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Purchased_item [purchased_id=" + purchased_id + ", item_id=" + item_id + ", quantity=" + quantity + "]";
	}
	
	
	

}
