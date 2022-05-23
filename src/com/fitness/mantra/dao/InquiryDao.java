package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fitness.mantra.model.InquiryBean;

public class InquiryDao extends BaseDao {

	public String insert(InquiryBean inquirybean) {
		Connection con = getConnection();
		String result = "Message Send succesfully";
		String sql = "insert into inquiry(name,contact,email,mesg) values (?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, inquirybean.getName());
			ps.setString(2, inquirybean.getContact());
			ps.setString(3, inquirybean.getEmail());
			ps.setString(4, inquirybean.getMesg());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "Message Send failed";
		}

		return result;
	}
}