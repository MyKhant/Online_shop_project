package dm;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import ds.Data;
import ds.Item;
import ds.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class mainFormController implements Initializable {

	@FXML
	private ImageView item_imageView;

	@FXML
	private Button item_importBtn;

	@FXML
	private Button cardView_btn;

	@FXML
	private Label username;

	@FXML
	private Button signout_btn;

	@FXML
	private AnchorPane menu_form;

	@FXML
	private AnchorPane main_form;

	@FXML
	private GridPane menu_gridPane;

	@FXML
	private TextField purchased_amount;

	@FXML
	private Label purchased_change;

	@FXML
	private TableColumn<Item, String> purchased_col_itemName;

	@FXML
	private TableColumn<Item, Double> purchased_col_price;

	@FXML
	private TableColumn<Item, Integer> purchased_col_quantity;

	@FXML
	private Button purchased_payBtn;

	@FXML
	private Button purchased_receiptBtn;

	@FXML
	private Button purchased_removeBtn;

	@FXML
	private TableView<Item> purchased_tableView;

	@FXML
	private Label purchased_total;

	@FXML
	private TableColumn<Item, String> colItem_category;

	@FXML
	private TableColumn<Item, String> colItem_id;

	@FXML
	private TableColumn<Item, String> colItem_name;

	@FXML
	private TableColumn<Item, String> colItem_price;

	@FXML
	private TableColumn<Item, String> colItem_quantity;

	@FXML
	private ComboBox<?> inventory_itemCotegory;

	@FXML
	private TextField inventory_itemId;

	@FXML
	private TextField inventory_itemName;

	@FXML
	private TextField inventory_itemPrice;

	@FXML
	private TextField inventory_itemQty;

	@FXML
	private Button item_btn;

	@FXML
	private Button staff_btn;

	@FXML
	private AnchorPane item_form;

	@FXML
	private AnchorPane staff_form;

	@FXML
	private Button resetStaff;

	@FXML
	private Button updateStaff_btn;

	@FXML
	private Button deleteStaff;

	@FXML
	private TextField staff_id;

	@FXML
	private TextField staff_name;

	@FXML
	private TextField staff_pass;

	@FXML
	private TextField staff_ans;

	@FXML
	private Button item_addBtn;

	@FXML
	private Button resetItem;

	@FXML
	private Button deleteItem;

	@FXML
	private TableView<Staff> tblStaff;

	@FXML
	private TableColumn<Staff, String> colId;

	@FXML
	private TableColumn<Staff, String> colName;

	@FXML
	private TableColumn<Staff, String> colPass;

	@FXML
	private TableColumn<Staff, String> colQues;

	@FXML
	private TableColumn<Staff, String> colAns;

	@FXML
	private TableColumn<Staff, String> colDate;

	@FXML
	private TableView<Item> tblItem;

	private Staff staff;

	private Staff updateStaff = null;
	private Item updateItem, item = null;

	private ObservableList<Item> cardListData = FXCollections.observableArrayList();

	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;

	// void addNewStaff() {
	//
	// int id = Integer.parseInt(staff_id.getText());
	// String name = staff_name.getText();
	// Staff s = new Staff(id, name, );
	// if (StaffManager.addNewStaff(s)) {
	// new Alert(AlertType.INFORMATION, "Success", ButtonType.OK).show();
	// tblStaff.getItems().add(s);
	// staffResetAction(null);
	// } else {
	// new Alert(AlertType.ERROR, "Fail", ButtonType.OK).show();
	// }
	// }

	void addNewItem() {
		int id = Integer.parseInt(inventory_itemId.getText());
		String name = inventory_itemName.getText();
		Double price = Double.parseDouble(inventory_itemPrice.getText());
		int quantity = Integer.parseInt(inventory_itemQty.getText());
		String category = (String) inventory_itemCotegory.getSelectionModel().getSelectedItem();
		String path = Data.path;
		path = path.replace("\\", "\\\\");

		Item item = new Item(id, name, price, quantity, category, path);
		if (ItemManager.addNewItem(item)) {
			new Alert(AlertType.INFORMATION, "Success", ButtonType.OK).show();
			tblItem.getItems().add(item);
			itemResetAction(null);
		} else {
			new Alert(AlertType.ERROR, "Fail", ButtonType.OK).show();
		}
	}

	@FXML
	void deleteAction(ActionEvent event) {

		if (event.getSource() == deleteStaff) {
			Staff toDelete = tblStaff.getSelectionModel().getSelectedItem();
			if (toDelete == null)
				return;
			Alert a = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
			ButtonType r = a.showAndWait().get();
			if (r.equals(ButtonType.YES)) {
				if (StaffManager.deleteStaff(toDelete)) {
					new Alert(AlertType.INFORMATION, "Success", ButtonType.OK).show();
					tblStaff.getItems().remove(toDelete);
				} else {
					new Alert(AlertType.ERROR, "Fail", ButtonType.OK).show();
				}
			}
		} else if (event.getSource() == deleteItem) {
			Item toDelete = tblItem.getSelectionModel().getSelectedItem();
			if (toDelete == null)
				return;
			Alert a = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
			ButtonType r = a.showAndWait().get();
			if (r.equals(ButtonType.YES)) {
				if (ItemManager.deleteItem(toDelete)) {
					new Alert(AlertType.INFORMATION, "Success", ButtonType.OK).show();
					tblItem.getItems().remove(toDelete);
				} else {
					new Alert(AlertType.ERROR, "Fail", ButtonType.OK).show();
				}
			}
		}
	}

	@FXML
	void updateStaffAction(ActionEvent event) {
		if (updateStaff == null)
			return;
		updateStaff();

	}

	@FXML
	void itemBtnAction(ActionEvent event) {
		if (updateItem == null) {
			addNewItem();
		} else {
			updateItem();
		}
	}

	@FXML
	void staffResetAction(ActionEvent event) {
		staff_name.clear();
		staff_pass.clear();
		staff_ans.clear();
		staff_id.setText(StaffManager.newStaffNumber() + "");
		staff_id.setEditable(false);
		staff_id.requestFocus();
		updateStaff = null;

	}

	@FXML
	void itemResetAction(ActionEvent event) {
		inventory_itemName.clear();
		inventory_itemPrice.clear();
		inventory_itemQty.clear();
		inventory_itemCotegory.getSelectionModel().clearSelection();
		Data.path = "";
		item_imageView.setImage(null);
		inventory_itemId.setText(ItemManager.newItemNumber() + "");
		inventory_itemId.setEditable(false);
		inventory_itemId.requestFocus();
		updateItem = null;
		item_addBtn.setText("Add");

	}

	void updateStaff() {
		updateStaff.setName(staff_name.getText());
		if (StaffManager.updateStaff(updateStaff)) {
			new Alert(AlertType.INFORMATION, "Update Success", ButtonType.OK).show();
			tblStaff.refresh();
			staffResetAction(null);
		} else {

			new Alert(AlertType.ERROR, "Update Fail", ButtonType.OK).show();
		}
	}

	void updateItem() {
		updateItem.setName(inventory_itemName.getText());
		updateItem.setPrice(Double.parseDouble(inventory_itemPrice.getText()));
		updateItem.setQuantity(Integer.parseInt(inventory_itemQty.getText()));
		updateItem.setCategory(inventory_itemCotegory.getSelectionModel().getSelectedItem() + "");
		String path = Data.path;
		path = path.replace("\\", "\\\\");
		updateItem.setImage(path);
		if (ItemManager.updateItem(updateItem)) {
			new Alert(AlertType.INFORMATION, "Update Success", ButtonType.OK).show();
			tblItem.refresh();
			itemResetAction(null);
		} else {
			new Alert(AlertType.ERROR, "Update Fail", ButtonType.OK).show();
		}
	}

	@FXML
	void itemImportAction(ActionEvent event) {
		FileChooser openFile = new FileChooser();
		openFile.getExtensionFilters().add(new ExtensionFilter("Open file image", "*png", "*jpg"));
		File file = openFile.showOpenDialog(main_form.getScene().getWindow());
		if (file != null) {
			Image image = new Image(file.toURI().toString(), 120, 127, false, true);
			Data.path = file.getAbsolutePath();
			item_imageView.setImage(image);
		}

	}

	public void displayUserName() {
		String user = Data.username;
		user = user.substring(0, 1).toUpperCase() + user.substring(1);
		username.setText(user);
	}

	void viewStaff() {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
		colQues.setCellValueFactory(new PropertyValueFactory<>("question"));
		colAns.setCellValueFactory(new PropertyValueFactory<>("answer"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		tblStaff.getItems().addAll(StaffManager.getAllStaff());

		tblStaff.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1) {
				updateStaff = tblStaff.getSelectionModel().getSelectedItem();
				if (updateStaff != null) {
					staff_id.setText(updateStaff.getId() + "");
					staff_name.setText(updateStaff.getName());
					staff_pass.setText(updateStaff.getPassword());
					staff_ans.setText(updateStaff.getAnswer());
					staff_name.selectAll();
					staff_name.requestFocus();
				}
			}
		});
	}

	private ObservableList<Item> inventoryListItem;

	void viewItem() {
		inventoryListItem = ItemManager.getAllItem();
		colItem_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		colItem_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		colItem_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		colItem_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		colItem_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		// tblItem.getItems().addAll(ItemManager.getAllItem());
		tblItem.setItems(inventoryListItem);
		tblItem.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1) {
				updateItem = tblItem.getSelectionModel().getSelectedItem();
				if (updateItem != null) {
					inventory_itemId.setText(updateItem.getId() + "");
					inventory_itemName.setText(updateItem.getName());
					inventory_itemPrice.setText(updateItem.getPrice() + "");
					inventory_itemQty.setText(updateItem.getQuantity() + "");
					// inventory_itemCotegory.setPromptText(updateItem.getCategory());

					Data.path = updateItem.getImage();
					String path = "File:" + updateItem.getImage();
					Image image = new Image(path, 120, 127, false, true);
					item_imageView.setImage(image);
					inventory_itemName.selectAll();
					inventory_itemName.requestFocus();
					item_addBtn.setText("update");
				}
			}
		});
	}

	private String[] categoryList = { "Electronic", "Clothing" };

	public void inventoryCategoryList() {
		List<String> typeC = new ArrayList<>();
		for (String data : categoryList) {
			typeC.add(data);
		}
		ObservableList listData = FXCollections.observableArrayList(typeC);
		inventory_itemCotegory.setItems(listData);
	}

	public ObservableList<Item> itemGetData() {
		String sql = "select * from item";
		ObservableList<Item> list = FXCollections.observableArrayList();
		try (Connection conn = DBConnection.getconnection(); PreparedStatement psmt = conn.prepareStatement(sql)) {
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Item item = new Item(rs.getInt("item_id"), rs.getString("name"), rs.getDouble("price"),
						rs.getInt("quantity"), rs.getString("category"), rs.getString("image"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void itemDisplayCard() {
		cardListData.clear();
		cardListData.addAll(itemGetData());

		int row = 0;
		int column = 0;

		menu_gridPane.getRowConstraints().clear();
		menu_gridPane.getColumnConstraints().clear();

		for (int i = 0; i < cardListData.size(); i++) {
			try {
				FXMLLoader load = new FXMLLoader();
				load.setLocation(getClass().getResource("../resource/cardProduct.fxml"));
				AnchorPane pane = load.load();
				CardItemController itemCard = load.getController();
				itemCard.setData(cardListData.get(i));

				if (column == 4) {
					column = 0;
					row += 1;
				}
				menu_gridPane.add(pane, column++, row);
				GridPane.setMargin(pane, new Insets(10));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ObservableList<Item> purchasedGetOrder() {
		customerID();
		ObservableList<Item> listData = FXCollections.observableArrayList();

		String sql = "select * from customer where customer_id = " + cID;

		try (Connection conn = DBConnection.getconnection(); PreparedStatement psmt = conn.prepareStatement(sql)) {
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Item item = new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getDouble("price"),
						rs.getString("category"), rs.getString("image"), rs.getInt("quantity"), rs.getDate("date"));
				listData.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	private ObservableList<Item> purchasedOrderListData;

	public void purchasedShowOrderData() {
		purchasedOrderListData = purchasedGetOrder();

		purchased_col_itemName.setCellValueFactory(new PropertyValueFactory<>("name"));
		purchased_col_quantity.setCellValueFactory(new PropertyValueFactory<>("purchased_quantity"));
		purchased_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

		purchased_tableView.setItems(purchasedOrderListData);
	}
	
	private int getId;
	
	public void purchasedSelectOrder() {
		Item ite = purchased_tableView.getSelectionModel().getSelectedItem();
		int num = purchased_tableView.getSelectionModel().getSelectedIndex();
		
		if ((num - 1) < -1) {
            return;
        }
		getId = ite.getId();
		
	}

	private double totalP;

	public void purchasedGetTotal() {
		customerID();
		String total = "select sum(price) from customer where customer_id = " + cID;
		try {
			conn = DBConnection.getconnection();
			pstm = conn.prepareStatement(total);
			rs = pstm.executeQuery();

			if (rs.next()) {
				totalP = rs.getDouble("sum(price)");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void purchasedDisplayTotal() {
		purchasedGetTotal();
		purchased_total.setText("$" + totalP);
	}

	private double amount;
	private double change;

	public void purchasedAmount() {
		purchasedGetTotal();
		if (purchased_amount.getText().isEmpty() || totalP == 0) {
			new Alert(AlertType.ERROR, "Invalid!", ButtonType.OK).showAndWait();
		} else {
			amount = Double.parseDouble(purchased_amount.getText());
			if (amount < totalP) {
				purchased_amount.setText("");
			} else {
				change = (amount - totalP);
				purchased_change.setText("$" + change);
			}
		}
	}

	public void purchasedPayBtn() {
		if (totalP == 0) {
			new Alert(AlertType.ERROR, "Please choose your order first!", ButtonType.OK).showAndWait();
		} else {
			purchasedGetTotal();
			String sql = "insert into receipt (customer_id, total, date, staff_username) values (?,?,?,?)";

			try {
				conn = DBConnection.getconnection();
				if (amount == 0) {
					new Alert(AlertType.ERROR, "Something wrong!", ButtonType.OK).showAndWait();

				} else {
					Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?");
					Optional<ButtonType> option = alert.showAndWait();
					if (option.get().equals(ButtonType.OK)) {
						customerID();
						purchasedGetTotal();
						pstm = conn.prepareStatement(sql);
						pstm.setString(1, String.valueOf(cID));
						pstm.setString(2, String.valueOf(totalP));
						Date date = new Date();
						java.sql.Date sqlDate = new java.sql.Date(date.getTime());
						pstm.setString(3, String.valueOf(sqlDate));
						pstm.setString(4, Data.username);
						pstm.executeUpdate();
						new Alert(AlertType.INFORMATION, "Successful.", ButtonType.OK).showAndWait();
						purchasedShowOrderData();
						purchasedRestart();
					} else {
						new Alert(AlertType.WARNING, "Cancelled.", ButtonType.OK).showAndWait();

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void purchasedRemoveBtn() {
		if(getId == 0) {
			new Alert(AlertType.ERROR, "Please select the order you want to remove!", ButtonType.OK).showAndWait();
		} else {
			System.out.println(Data.id);
			String deleteData = "delete from customer where id = " + Data.id;
			try {
				conn = DBConnection.getconnection();
				pstm = conn.prepareStatement(deleteData);
//				pstm.executeUpdate();
				purchasedShowOrderData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void purchasedRestart() {
		totalP = 0;
		change = 0; 
		amount = 0;
		purchased_total.setText("$0.0");
		purchased_amount.setText("");
		purchased_change.setText("$0.0");
	}

	private int cID;

	public void customerID() {
		String sql = "select max(customer_id) from customer";

		try {
			conn = DBConnection.getconnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if (rs.next()) {
				cID = rs.getInt("max(customer_id)");
			}
			String checkCID = "select max(customer_id) from receipt";
			pstm = conn.prepareStatement(checkCID);
			rs = pstm.executeQuery();
			int checkID = 0;
			if (rs.next()) {
				checkID = rs.getInt("max(customer_id)");
			}

			if (cID == 0) {
				cID += 1;
			} else if (cID == checkID) {
				cID += 1;
			}
			Data.cID = cID;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void signOut() {
		try {
			Optional<ButtonType> option = new Alert(AlertType.INFORMATION, "Are your sure want to logout?", ButtonType.OK, ButtonType.CANCEL).showAndWait();
			if (option.get().equals(ButtonType.OK)) {
				signout_btn.getScene().getWindow().hide();
				
				Parent root = FXMLLoader.load(getClass().getResource("../resource/login.fxml"));
				Stage st = new Stage();
				Scene sc = new Scene(root);
				
				st.setScene(sc);
				st.setTitle("Online Shop Management");
				st.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void switchForm(ActionEvent event) {
		if (event.getSource() == staff_btn) {
			menu_form.setVisible(false);
			item_form.setVisible(false);
			staff_form.setVisible(true);
		} else if (event.getSource() == item_btn) {
			item_form.setVisible(true);
			staff_form.setVisible(false);
			menu_form.setVisible(false);
			inventoryCategoryList();
			viewItem();
		} else if (event.getSource() == cardView_btn) {
			item_form.setVisible(false);
			staff_form.setVisible(false);
			menu_form.setVisible(true);
			itemDisplayCard();
			purchasedDisplayTotal();
			purchasedShowOrderData();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resourceBundle) {
		displayUserName();
		viewStaff();
		viewItem();
		inventoryCategoryList();
		itemDisplayCard();
		purchasedGetOrder();
		purchasedDisplayTotal();
		purchasedShowOrderData();
	}

}