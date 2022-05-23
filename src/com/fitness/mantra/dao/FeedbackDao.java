package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fitness.mantra.model.FeedbackBean;

public class FeedbackDao extends BaseDao {

	public String insert(FeedbackBean feedbackbean) {
		Connection con = getConnection();
		String result = "Thank you for your feedback";
		String sql = "insert into reviews(name,email,mesg) values (?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, feedbackbean.getName());
			ps.setString(2, feedbackbean.getEmail());
			ps.setString(3, feedbackbean.getMesg());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "feedback Send failed";
		}

		return result;
	}

}