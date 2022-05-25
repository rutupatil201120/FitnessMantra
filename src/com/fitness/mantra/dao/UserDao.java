package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fitness.mantra.model.User;

public class UserDao extends BaseDao {

	private static final String INSERT_USERS_SQL = "INSERT INTO user (user_name, first_name, last_name, email, password, is_admin) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_USERS_SQL = "update user set first_name= ?, last_name = ? where id = ?";
	private static final String DELETE_USERS_SQL = "delete from user where id = ?";

	private static final String SELECT_USER_BY_ID = "select * from user where id =?";
	private static final String SELECT_ALL_NON_ADMIN_USERS = "select * from user where is_admin='N'";
	private static final String VALIDATE_USER = "select * from user where user_name = ? and password =?";

	public boolean validate(User user) {
		boolean status = false;

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(VALIDATE_USER);) {
			ps.setString(1, user.getUserName());
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

	public void insertUser(User user) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getIsAdmin());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User selectUser(int id) {
		User user = null;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				user = getUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public List<User> selectAllUsers() {

		List<User> users = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NON_ADMIN_USERS);) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				users.add(getUser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public boolean deleteUser(int id) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

	public boolean updateUser(User user) {
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {

			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setInt(3, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	private User getUser(ResultSet rs) throws SQLException {
		return getUser(new User(), rs);
	}

	private User getUser(User user, ResultSet rs) throws SQLException {
		user.setId(rs.getInt("id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setUserName(rs.getString("user_name"));
		user.setEmail(rs.getString("email"));
		user.setIsAdmin(rs.getString("is_admin"));

		return user;
	}
}