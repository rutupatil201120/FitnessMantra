package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fitness.mantra.model.Payment;

public class PaymentsDao extends BaseDao {

	private static final String SELECT_ALL_PAYMENTS = "select p.*, CONCAT(u.first_name, \" \", u.last_name) as user_name,CONCAT(a.first_name, \" \", a.last_name) as admin_name from payments as p left join users as u on p.user_id = u.id left join admins as a on p.admin_id = a.id";
	private static final String SELECT_PAYMENTS_BY_USER_ID = SELECT_ALL_PAYMENTS + " where user_id = ?";
	private static final String NEW_PAYMENT_ENTRIES_FOR_CURRENT_MONTH = "insert into payments(user_id, amount, description) select u.id as user_id, p.price as amount, DATE_FORMAT(sysdate(), \"Renewed Subscription %M-%Y\") as description from users as u join plans as p on u.plan_id = p.id where u.is_active = 1 and u.id not in (select user_id from payments where entry_date > date_add(date_add(LAST_DAY(sysdate()),interval 1 DAY),interval -1 MONTH))";

	private static final String INSERT_PAYMENT = "INSERT INTO payments (user_id, amount, description) VALUES (?, ?, ?)";
	private static final String UPDATE_PAYMENT_AS_RECEIVED = "update payments set payment_date= sysdate(), is_received = ?, admin_id = ? where id = ?";
	private static final String UPDATE_PAYMENT_AS_NOT_RECEIVED = "update payments set payment_date= null, admin_id = null, is_received = ? where id = ?";
	private static final String DELETE_PAYMENT_BY_ID = "delete from payments where id = ?";

	public List<Payment> selectAllPayments() {

		List<Payment> payments = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENTS);) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				payments.add(getPayment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return payments;
	}

	public List<Payment> selectAllPayments(int userId) {

		List<Payment> payments = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENTS_BY_USER_ID);) {
			preparedStatement.setInt(1, userId);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				payments.add(getPayment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return payments;
	}

	public boolean updatePaymentStatus(Payment payment) {
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						payment.isReceived() ? UPDATE_PAYMENT_AS_RECEIVED : UPDATE_PAYMENT_AS_NOT_RECEIVED);) {
			int index = 1;
			statement.setBoolean(index++, payment.isReceived());

			if (payment.isReceived()) {
				statement.setInt(index++, payment.getAdminId());
			}
			statement.setInt(index++, payment.getId());

			rowUpdated = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	public void insertPayment(Payment payment) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT)) {

			preparedStatement.setInt(1, payment.getUserId());
			preparedStatement.setDouble(2, payment.getAmount());
			preparedStatement.setString(3, payment.getDescription());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deletePayment(int id) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PAYMENT_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

	public int updateCurrentMonthEntries() {
		int rowsInserted = 0;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(NEW_PAYMENT_ENTRIES_FOR_CURRENT_MONTH);) {

			rowsInserted = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsInserted;
	}

	private Payment getPayment(ResultSet rs) throws SQLException {
		Payment payment = new Payment();

		payment.setId(rs.getInt("id"));
		payment.setUserId(rs.getInt("user_id"));
		payment.setAmount(rs.getDouble("amount"));
		payment.setReceived(rs.getBoolean("is_received"));
		payment.setDueDate(rs.getString("due_date"));
		payment.setPaymentDate(rs.getString("payment_date"));
		payment.setAdminId(rs.getInt("admin_id"));
		payment.setDescription(rs.getString("description"));

		payment.setAdminName(rs.getString("admin_name"));
		payment.setUserName(rs.getString("user_name"));

		return payment;
	}

}