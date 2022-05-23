package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fitness.mantra.model.GPlanBean;
import com.fitness.mantra.model.UInfoBean;

public class GPlanDao extends BaseDao {

	private static final String SELECT_ALL = "select * from gplan";
	private static final String INSERT = "insert into gplan(name,price,duration) values (?,?,?)";

	public List<GPlanBean> selectAllPlans() {

		List<GPlanBean> users = new ArrayList<>();

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String price = rs.getString("price");
				String duration = rs.getString("duration");

				users.add(new GPlanBean(id, name, price, duration));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean insert(GPlanBean gplanbean) {
		Connection con = getConnection();
		boolean result = false;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(INSERT);
			ps.setString(1, gplanbean.getName());
			ps.setString(2, gplanbean.getPrice());
			ps.setString(3, gplanbean.getDuration());

			result = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
