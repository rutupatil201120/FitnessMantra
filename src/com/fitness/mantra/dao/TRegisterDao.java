package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fitness.mantra.model.TRegisterBean;

public class TRegisterDao extends BaseDao {

	public String insert(TRegisterBean tregisterbean) {
		Connection con = getConnection();
		String result = "Data saved successfully";
		String sql = "insert into tinfo(fname, lname, DOB, adharno,address,gender,agreement, email, contact, uname, password) values (?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, tregisterbean.getFname());
			ps.setString(2, tregisterbean.getLname());
			ps.setString(3, tregisterbean.getDOB());
			ps.setString(4, tregisterbean.getAdharno());
			ps.setString(5, tregisterbean.getAddress());
			ps.setString(6, tregisterbean.getGender());
			ps.setString(7, tregisterbean.getAgree());
			ps.setString(8, tregisterbean.getEmail());
			ps.setString(9, tregisterbean.getContact());
			ps.setString(10, tregisterbean.getUname());
			ps.setString(11, tregisterbean.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "Data not saved";
		}

		return result;
	}

}
