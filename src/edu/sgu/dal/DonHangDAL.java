package edu.sgu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sgu.dto.DonHang;

public class DonHangDAL {
	public List<DonHang> dsDonHang() {
		List<DonHang> lstDonHang = null;
		DonHang dh = null;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			lstDonHang = new ArrayList<>();
			String sql = "select * from donhang";
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dh = new DonHang();
				dh.setId(rs.getInt("id"));
				dh.setId_khachHang(rs.getInt("id_khachhang"));
				dh.setNgayDatHang(rs.getDate("ngaydathang"));
				dh.setTenNguoiNhanHang(rs.getString("tennguoinhanhang"));
				dh.setDienThoaiNguoiNhan(rs.getString("dienthoainguoinhan"));
				dh.setDiaChiGiaoHang(rs.getString("diachigiaohang"));
				if (rs.getString("ghichu") == null)
					dh.setGhiChu("");
				else
					dh.setGhiChu(rs.getString("ghichu"));
				dh.setThanhToan(rs.getInt("thanhtoan"));
				dh.setTrangThai(rs.getInt("id_trangthai"));
				lstDonHang.add(dh);
			}
		} catch (Exception e) {
			System.out.println("Không thể lấy được thông tin đơn hàng");
		}
		return lstDonHang;
	}
	
}
