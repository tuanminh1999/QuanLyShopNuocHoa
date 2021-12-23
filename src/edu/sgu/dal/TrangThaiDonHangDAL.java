package edu.sgu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sgu.dto.TrangThaiDonHang;

public class TrangThaiDonHangDAL {
	public List<TrangThaiDonHang> dsTrangThaiDonHang() {
		List<TrangThaiDonHang> lstTrangThaiDonHang = null;
		TrangThaiDonHang thdh = null;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			lstTrangThaiDonHang = new ArrayList<>();
			String sql = "select * from trangthaidonhang";
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				thdh = new TrangThaiDonHang();
				thdh.setId(rs.getInt("id"));
				thdh.setTenTrangThai(rs.getString("tentrangthai"));
				lstTrangThaiDonHang.add(thdh);
			}
		} catch (Exception e) {
			System.out.println("Không thể lấy được thông tin trạng thái đơn hàng");
		}
		return lstTrangThaiDonHang;
	}
}
