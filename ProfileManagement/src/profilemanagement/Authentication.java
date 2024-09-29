package profilemanagement;

import java.sql.*;

public class Authentication {
	private static final String contact = null;

	public boolean registerUser(String username, String password, String email, String contact) {
		String sql = "INSERT INTO users (username, password, email, contact) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			pstmt.setString(4, contact);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public User loginUser(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new User(username, password, rs.getString("email"), rs.getString("contact"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
