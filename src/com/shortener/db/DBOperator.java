package com.shortener.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shortener.Encoder;


public class DBOperator {
	
	private static String url = "";
        private static String user = "";
        private static String password = "";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String addUrl(String longUrl) {
		Connection con = null;
        	PreparedStatement ps = null;
        	ResultSet rs = null;
        	try {
        		con = getConnection();
        		String query = "INSERT INTO urls (url) VALUES(?)";
	                ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	                ps.setString(1, longUrl);
	                ps.executeUpdate();
	                rs = ps.getGeneratedKeys();
	                if (rs.next()) {
	                       int recordId = rs.getInt(1);
		               String encodedId = Encoder.encode(recordId);
		                query = "UPDATE urls SET code=(?) WHERE id=? ";
		                ps = con.prepareStatement(query);
		                ps.setString(1, encodedId);
		                ps.setInt(2, recordId);
		                ps.executeUpdate();
		                return "shrt.hol.es/" + encodedId;
	            	}
        	
	        } catch (SQLException ex) {
	        	System.out.println("SqlException occured...");
	        }
	        return "";
	}
	
	public static String getUrl(String code) {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	con = getConnection();
        	String selectSQL = "SELECT url FROM urls WHERE id=?";
            ps = con.prepareStatement(selectSQL);
            ps.setInt(1, Encoder.decode(code));
            rs = ps.executeQuery();
            if (rs.next()) {
            	String url = rs.getString("url");
            	return url.startsWith("http") ? url : "http://" + url;
            }
        } catch (SQLException ex) {
        	System.out.println("SqlException occured...");
        }
        return "";
	}

}
