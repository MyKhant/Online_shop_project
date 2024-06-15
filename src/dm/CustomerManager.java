package dm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ds.Customer;

public class CustomerManager {
	
	public static boolean addNewCustomer(Customer s) {
		String sql = "insert into customer (customer_id, name, age) values (?, ?, ?)";
		
		try (Connection con = DBConnection.getconnection();
				PreparedStatement pstm = con.prepareStatement(sql);){
			pstm.setInt(1, s.getId());
			pstm.setString(2, s.getName());
			pstm.setInt(3, s.getAge());
			int r = pstm.executeUpdate();
			return r > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static List<Customer> getAllCutomer() {
		String sql = "select * from customer";
		ArrayList<Customer> list = new ArrayList<>();
		
		try (Connection con = DBConnection.getconnection();
				PreparedStatement psmt = con.prepareStatement(sql);){
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Customer s = new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getInt("age"));
				list.add(s);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean updateCustomer(Customer toUpdate) {
		String sql = "update customer set name=?, age=? where customer_id=?";
		try (Connection con = DBConnection.getconnection();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setString(1, toUpdate.getName());
			psmt.setInt(2, toUpdate.getAge());
			psmt.setInt(3, toUpdate.getId());
			int r = psmt.executeUpdate();
			return r > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteCustomer(Customer toDelete) {
		String sql = "delete from customer where customer_id=?";
		try (Connection con = DBConnection.getconnection();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setInt(1, toDelete.getId());
			int r = psmt.executeUpdate();
			return r > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
