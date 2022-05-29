package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fitness.mantra.model.User;

public class AdminsDao extends BaseDao {

	private static final String VALIDATE_USER = "select * from admins where email = ? and password = MD5(?)";

	public boolean validate(User user) {
		boolean status = false;

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(VALIDATE_USER);) {
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				getUser(user, rs);
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	private User getUser(User user, ResultSet rs) throws SQLException {

		user.setId(rs.getInt("id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setContactNumber(rs.getString("contact_number"));
		user.setEmail(rs.getString("email"));
		user.setAdmin(true);

		return user;
	}

}