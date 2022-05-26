package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fitness.mantra.model.User;

public class UsersDao extends BaseDao {

	private static final String INSERT_USERS_SQL = "INSERT INTO users (first_name, last_name, contact_number, email, birth_date, gender, time_slot, plan_id, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_USERS_SQL = "update users set first_name= ?, last_name= ?, contact_number= ?, email= ?, birth_date= ?, gender= ?, time_slot= ?, plan_id= ? where id = ?";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?";

	private static final String SELECT_USER_BY_ID = "select * from users where id =?";
	private static final String SELECT_ALL_NON_ADMIN_USERS = "select * from users where is_admin='N'";
	private static final String VALIDATE_USER = "select * from users where email = ? and password = ?";
	private static final String SELECT_USER_BY_EMAIL = "select * from users where email = ?";

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

	public boolean checkIfUserExist(User user) {
		boolean status = false;

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_EMAIL);) {
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean insertUser(User user) {
		boolean status = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL,
						Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getContactNumber());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getBirthDate());
			preparedStatement.setString(6, user.getGender());
			preparedStatement.setString(7, user.getTimeSlot());
			preparedStatement.setInt(8, user.getPlanId());
			preparedStatement.setString(9, user.getPassword());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
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
			statement.setString(3, user.getContactNumber());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getBirthDate());
			statement.setString(6, user.getGender());
			statement.setString(7, user.getTimeSlot());
			statement.setInt(8, user.getPlanId());
			statement.setInt(9, user.getId());

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
		user.setContactNumber(rs.getString("contact_number"));
		user.setEmail(rs.getString("email"));
		user.setBirthDate(rs.getString("birth_date"));
		user.setGender(rs.getString("gender"));
		user.setTimeSlot(rs.getString("time_slot"));
		user.setPlanId(rs.getInt("plan_id"));
		user.setIsAdmin(rs.getString("is_admin"));

		return user;
	}

}