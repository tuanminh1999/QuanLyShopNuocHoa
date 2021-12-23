package edu.sgu.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KetNoi {
	
	private static Connection ketNoi;

	public static Connection getKetNoi() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/phuong_perfume?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
			ketNoi = DriverManager.getConnection(url, "root", "1234");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Kết nối thất bại");
		}
		return ketNoi;
	}
}
