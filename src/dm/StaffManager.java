package dm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ds.Staff;


public class StaffManager {
	
//	public static boolean addNewStaff(Staff s) {
//		String sql = "insert into staff(id, name, password, question, answer, date) values (?, ?, ?, ?, ?, ?)";
//		
//		try (Connection con = DBConnection.getconnection();
//				PreparedStatement pstm = con.prepareStatement(sql);){
//			pstm.setInt(1, s.getId());
//			pstm.setString(2, s.getName());
//			pstm.setString(3, s.getPassword());
//			pstm.setString(4, s.getQuestion());
//			pstm.setString(5, s.getAnswer());
//			int r = pstm.executeUpdate();
//			return r > 0;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
	
	
	public static List<Staff> getAllStaff() {
		String sql = "select * from staff";
		ArrayList<Staff> list = new ArrayList<>();
		
		try (Connection con = DBConnection.getconnection();
				PreparedStatement psmt = con.prepareStatement(sql);){
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Staff s = new Staff(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("question"), rs.getString("answer"), rs.getDate("date"));
				list.add(s);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean updateStaff(Staff toUpdate) {
		String sql = "update staff set username=?, password=?, question=?, answer=? where id=?";
		try (Connection con = DBConnection.getconnection();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setString(1, toUpdate.getName());
			psmt.setString(2, toUpdate.getPassword());
			psmt.setString(1, toUpdate.getQuestion());
			psmt.setString(2, toUpdate.getAnswer());
			psmt.setInt(3, toUpdate.getId());
			int r = psmt.executeUpdate();
			return r > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteStaff(Staff toDelete) {
		String sql = "delete from staff where id=?";
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

	public static int newStaffNumber() {
		String sql = "select max(id) + 1 newId from staff";
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
 