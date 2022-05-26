package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fitness.mantra.model.Plan;

public class PlansDao extends BaseDao {

	private static final String SELECT_ALL_PLANS = "select * from plans";
	private static final String SELECT_PLAN_BY_ID = "select * from plans where id =?";

	private static final String INSERT_PLAN = "INSERT INTO plans (name, price) VALUES (?, ?)";
	private static final String UPDATE_PLAN_BY_ID = "update plans set name= ?, price= ? where id = ?";
	private static final String DELETE_PLAN_BY_ID = "delete from plans where id = ?";

	public List<Plan> selectAllPlans() {

		List<Plan> plans = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PLANS);) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				plans.add(getPlan(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return plans;
	}

	public Plan selectPlan(int id) {
		Plan user = null;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PLAN_BY_ID);) {
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				user = getPlan(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public boolean updatePlan(Plan user) {
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_BY_ID);) {

			statement.setString(1, user.getName());
			statement.setInt(2, user.getPrice());

			statement.setInt(3, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	public void insertPlan(Plan user) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLAN)) {

			preparedStatement.setString(1, user.getName());
			preparedStatement.setInt(2, user.getPrice());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deletePlan(int id) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

	private Plan getPlan(ResultSet rs) throws SQLException {
		Plan plan = new Plan();

		plan.setId(rs.getInt("id"));
		plan.setName(rs.getString("name"));
		plan.setPrice(rs.getInt("price"));

		return plan;
	}

}