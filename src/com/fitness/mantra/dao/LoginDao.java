package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fitness.mantra.model.LoginBean;

public class LoginDao extends BaseDao {

	public boolean validate(LoginBean loginBean) {
		boolean status = false;

		Connection con = getConnection();

		String sql = "select * from ureg where uname = ? and password =?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, loginBean.getUsername());
			ps.setString(2, loginBean.getPassword());
			ResultSet rs = ps.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}