package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fitness.mantra.model.User;

public class RegisterDao extends BaseDao {

	public String insert(User user) {
		Connection con = getConnection();
		String result = "Register successfully";
		String sql = "insert into userreg(fname,lname,uname,gmail,password,cpassword) values (?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, user.getFname());
			ps.setString(2, user.getLname());
			ps.setString(3, user.getUname());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getCpassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "Registration failed";
		}

		return result;
	}

}
