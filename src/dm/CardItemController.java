package dm;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import ds.Data;
import ds.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CardItemController implements Initializable {

	@FXML
	private AnchorPane card_form;

	@FXML
	private Button item_addBtn;

	@FXML
	private ImageView item_imageView;

	@FXML
	private Label item_name;

	@FXML
	private Label item_price;

	@FXML
	private Spinner<Integer> item_spinner;

	private Item item;
	private Image image;

	private int itemId;
	private String category;
	private String item_image;

	private SpinnerValueFactory<Integer> spin;
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;

	public void setData(Item item) {
		this.item = item;
		itemId = item.getId();
		category = item.getCategory();
		item_image = item.getImage();
		item_name.setText(item.getName());
		item_price.setText(item.getPrice() + "");
		String path = "File:" + item.getImage();
		image = new Image(path, 190, 94, false, true);
		item_imageView.setImage(image);
		pr = item.getPrice();
	}

	private int qty;

	private void setQuantity() {
		spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
		item_spinner.setValueFactory(spin);

	}

	private double totalP;
	private double pr;

	@FXML
	void addBtn(ActionEvent event) {
		mainFormController mForm = new mainFormController();
		mForm.customerID();
		qty = item_spinner.getValue();
//		String check = "";
//		String checkCategory = "select category from item where item_id = '" + itemId + "'";
		try {
			int checkQty = 0;
			String checkQuantity = "select quantity from item where item_id = '" + itemId + "'";
			conn = DBConnection.getconnection();
			pstm = conn.prepareStatement(checkQuantity);
			rs = pstm.executeQuery();

			if (rs.next()) {
				checkQty = rs.getInt("quantity");
			}
			
			if (checkQty == 0) {
				
				String updateQty = "update item set name ='" + item_name.getText() + "', category = '"
						+ category + "', quantity = 0, price = " + pr + ", image = '" + item_image
						+ "' where item_id = '" + itemId + "'";
				pstm = conn.prepareStatement(updateQty);
				pstm.executeUpdate();
			}
			
			
//			pstm = conn.prepareStatement(checkCategory);
//			rs = pstm.executeQuery();
//			if (rs.next()) {
//				check = rs.getString("category");
//
//			}
			if (qty == 0) {
				new Alert(AlertType.ERROR, "Something wrong", ButtonType.OK).show();

			} else {

				if (checkQty < qty) {
					new Alert(AlertType.ERROR, "Invalid. This product is Out of Stock", ButtonType.OK).show();
				}

				else {
					item_image = item_image.replace("\\", "\\\\");

					String insertData = "insert into customer (customer_id, item_id, item_name, category, quantity, price, date, image) "
							+ "values(?,?,?,?,?,?,?,?)";

					pstm = conn.prepareStatement(insertData);
					pstm.setString(1, String.valueOf(Data.cID));
					pstm.setInt(2, itemId);
					pstm.setString(3, item_name.getText());
					pstm.setString(4, category);
					pstm.setString(5, String.valueOf(qty));

					totalP = qty * pr;
					pstm.setString(6, String.valueOf(totalP));

					Date date = new Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					pstm.setString(7, String.valueOf(sqlDate));
					pstm.setString(8, item_image);
//					pstm.setString(6, Data.username);
					pstm.executeUpdate();

					int upQty = checkQty - qty;

					item_image = item_image.replace("\\", "\\\\");

					String updateQuantity = "update item set name ='" + item_name.getText() + "', category = '"
							+ category + "', quantity = " + upQty + ", price = " + pr + ", image = '" + item_image
							+ "' where item_id = '" + itemId + "'";

					pstm = conn.prepareStatement(updateQuantity);
					pstm.executeUpdate();
 
					new Alert(AlertType.INFORMATION, "Successfully Added!", ButtonType.OK).showAndWait();
					mForm.purchasedGetTotal();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setQuantity();
	}

}