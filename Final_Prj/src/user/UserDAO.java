package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {

	// ���̵�� ��й�ȣ�� �޾� �α��� �õ�
	public int login(String userID, String userPassword) {
		String sql = "select userPassword from user where userID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // �α��� ����
				} else {
					return 0; // ��й�ȣ Ʋ��
				}
			}
			return -1; // ���̵� ����
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(pstmt != null) pstmt.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(rs != null) rs.close();} catch (Exception e) { e.printStackTrace(); }
		}
		return -2; //db ����
	}
	
	// ������� ������ �Է¹޾� ȸ������
	public int join(UserDTO user) {
		String sql = "insert into user values (?, ?, ?, ?, false)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(pstmt != null) pstmt.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(rs != null) rs.close();} catch (Exception e) { e.printStackTrace(); }
		}
		return -1; // ȸ������ ����
	}
	
	// ����ڰ� �̸��� ������ �ߴ����� ���� ����
	public boolean getUserEmailChecked(String userID) {
		String sql = "select userEmailChecked from user where userID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(pstmt != null) pstmt.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(rs != null) rs.close();} catch (Exception e) { e.printStackTrace(); }
		}
		return false; // db ����
	}
	
	// ������� ���̵��� �޾� �� ������� �̸��� ��ȯ
	public String getUserEmail(String userID) {
		String sql = "select userEmail from user where userID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(pstmt != null) pstmt.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(rs != null) rs.close();} catch (Exception e) { e.printStackTrace(); }
		}
		return null; // db ����
	}
	
	// Ư���� ������� �̸��� ���� ����
	public boolean setUserEmailChecked(String userID) {
		String sql = "update user set userEmailChecked = true where userID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);			
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(pstmt != null) pstmt.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(rs != null) rs.close();} catch (Exception e) { e.printStackTrace(); }
		}
		return false; // db ����
	}

}
