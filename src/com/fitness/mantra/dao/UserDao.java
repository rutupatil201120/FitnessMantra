package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fitness.mantra.model.UInfoBean;

public class UserDao extends BaseDao {

	private static final String SELECT_USER_BY_ID = "select * from uinfo where id =?";
	private static final String SELECT_ALL_USERS = "select * from uinfo";
	private static final String INSERT_USERS_SQL = "insert into uinfo(fname, lname, dob, adharno, age, gender, height, weight, time_slot, medical, email, contact, address) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_USERS_SQL = "delete from uinfo where id = ?;";
	private static final String UPDATE_USERS_SQL = "update uinfo set  age=?, height=?, weight=?, time_slot=?, medical=?, email=?, contact=?, address=? where id = ?;";

	public UserDao() {

	}

	public UInfoBean selectUser(int id) {
		UInfoBean user = null;

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String dob = rs.getString("dob");
				String adharno = rs.getString("adharno");
				String age = rs.getString("age");
				String gender = rs.getString("gender");
				String height = rs.getString("height");
				String weight = rs.getString("weight");
				String timeSlot = rs.getString("time_slot");
				String medical = rs.getString("medical");
				String email = rs.getString("email");
				String contact = rs.getString("contact");
				String address = rs.getString("address");
				user = new UInfoBean(fname, lname, dob, adharno, age, gender, height, weight, timeSlot, medical, email,
						contact, address);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	private void printSQLException(SQLException e) {
		// TODO Auto-generated method stub

	}

	public List<UInfoBean> selectAllUsers() {

		List<UInfoBean> users = new ArrayList<>();

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String DOB = rs.getString("DOB");
				String adharno = rs.getString("adharno");
				String age = rs.getString("age");
				String gender = rs.getString("gender");
				String height = rs.getString("height");
				String weight = rs.getString("weight");
				String timeSlot = rs.getString("time_slot");
				String medical = rs.getString("medical");
				String email = rs.getString("email");
				String contact = rs.getString("contact");
				String address = rs.getString("address");
				users.add(new UInfoBean(id, fname, lname, DOB, adharno, age, gender, height, weight, timeSlot, medical,
						email, contact, address));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean insert(UInfoBean uinfobean) {
		boolean result = false;

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(INSERT_USERS_SQL);) {
			ps.setString(1, uinfobean.getFname());
			ps.setString(2, uinfobean.getLname());
			ps.setString(3, uinfobean.getDOB());
			ps.setString(4, uinfobean.getAdharno());
			ps.setString(5, uinfobean.getAge());
			ps.setString(6, uinfobean.getGender());
			ps.setString(7, uinfobean.getHeight());
			ps.setString(8, uinfobean.getWeight());
			ps.setString(9, uinfobean.getTimeSlot());
			ps.setString(10, uinfobean.getMedical());
			ps.setString(11, uinfobean.getEmail());
			ps.setString(12, uinfobean.getContact());
			ps.setString(13, uinfobean.getAddress());

			result = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
