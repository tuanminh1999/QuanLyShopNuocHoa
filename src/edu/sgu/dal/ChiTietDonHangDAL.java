package edu.sgu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sgu.dto.ChiTietDonHang;
import edu.sgu.dto.SanPham;

public class ChiTietDonHangDAL {

	public List<ChiTietDonHang> docTatCa() {
		List<ChiTietDonHang> lstChiTietDonHang = null;
		String sql = "select * from chitietdonhang as ctdh inner join sanpham as sp where ctdh.id_sanpham = sp.id";
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			lstChiTietDonHang = new ArrayList<>();
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				ChiTietDonHang ct = new ChiTietDonHang();
				ct.setId(rs.getInt("id"));
				ct.setId_donHang(rs.getInt("id_donhang"));
				ct.setId_sanPham(rs.getInt("id_sanpham"));
				ct.setSoLuong(rs.getInt("soluong"));
				ct.setDonGia(rs.getDouble("donGia"));
				try {
					SanPham sp = new SanPham();
					sp.setId(rs.getInt("id"));
					sp.setTenSanPham(rs.getString("tensanpham"));
					sp.setMoTa(rs.getString("mota"));
					sp.setHinhAnh(rs.getString("hinhanh"));
					sp.setDonGia(rs.getDouble("dongia"));
					sp.setDonGiaKhuyenMai(rs.getDouble("dongiaKM"));
					sp.setSoLuong(rs.getInt("soluong"));
					sp.setNgayTao(rs.getDate("ngaytao"));
					sp.setHienThi(rs.getInt("hienthi"));
					sp.setId_loai(rs.getInt("id_loai"));
					sp.setId_thuongHieu(rs.getInt("id_thuonghieu"));
					ct.setSanPham(sp);
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				lstChiTietDonHang.add(ct);
			}
		} catch (Exception e) {
			System.out.println("Không lấy được chi tiết đơn hàng");
		}
		return lstChiTietDonHang;
	}

}
