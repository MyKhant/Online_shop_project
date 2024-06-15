package dm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ds.Data;
import ds.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemManager {
//	public static Category getCategoryByID(int id) {
//		String sql = "select * from categories where cat_id = ?";
//		Category c = null;
//		try (Connection conn = DBConnection.getconnection();
//				PreparedStatement psmt = conn.prepareStatement(sql)) {
//			psmt.setInt(1, id);
//			ResultSet rs = psmt.executeQuery();
//			if (rs.next()) {
//				c = new Category(id, rs.getString("name"));
//			}
//			rs.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return c;
//	}
	
	public static boolean addNewItem(Item s) {
		String sql = "insert into item(item_id, name, price, quantity, category, image) values (?, ?, ?, ?, ?, ?)";
		
		try (Connection con = DBConnection.getconnection();
				PreparedStatement pstm = con.prepareStatement(sql);){
			pstm.setInt(1, s.getId());
			pstm.setString(2, s.getName());
			pstm.setDouble(3, s.getPrice());
			pstm.setInt(4, s.getQuantity());
			pstm.setString(5, s.getCategory());
			String path = Data.path;
            path = path.replace("\\", "\\\\");
			pstm.setString(6, path);
			int r = pstm.executeUpdate();
			return r > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ObservableList<Item> getAllItem() {
		String sql = "select * from item";
		ObservableList<Item> list = FXCollections.observableArrayList();
		try (Connection conn = DBConnection.getconnection();
				PreparedStatement psmt = conn.prepareStatement(sql)) {
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Item item = new Item(rs.getInt("item_id"),
						rs.getString("name"),
						rs.getDouble("price"),
						rs.getInt("quantity"),
						rs.getString("category"),
						rs.getString("image"));
				list.add(item);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean updateItem(Item toUpdate) {
		String sql = "update item set name=?, price=?, quantity=?, category=?, image=? where item_id=?";
		try (Connection con = DBConnection.getconnection();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setString(1, toUpdate.getName());
			psmt.setDouble(2, toUpdate.getPrice());
			psmt.setInt(3, toUpdate.getQuantity());
			psmt.setString(4, toUpdate.getCategory());
			String path = Data.path;
            path = path.replace("\\", "\\\\");
			psmt.setString(5, path);
			psmt.setInt(6, toUpdate.getId());
			int r = psmt.executeUpdate();
			return r > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteItem(Item toDelete) {
		String sql = "delete from item where item_id=?";
		try (Connection con = DBConnection.getconnection();
				PreparedStatement psmt = con.prepareStatement(sql);){
			psmt.setInt(1, toDelete.getId());
			int r = psmt.executeUpdate();
			return r > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int newItemNumber() {
		String sql = "select max(item_id) + 1 newId from item";
		int newId = 1;
		try (Connection con = DBConnection.getconnection();
				PreparedStatement psmt = con.prepareStatement(sql);) {
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				newId = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newId;
	}

}