package edu.sgu.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sgu.dto.SanPham;

public class SanPhamDAL {
	public List<SanPham> dsSanPham() {
		List<SanPham> lstSanPham = null;
		SanPham sp = null;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			lstSanPham = new ArrayList<>();
			String sql = "select * from sanpham";
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				sp = new SanPham();
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

				lstSanPham.add(sp);
			}
		} catch (Exception e) {
			System.out.println("Không thể lấy được thông tin sản phẩm");
		}
		return lstSanPham;
	}

	public int themSanPham(SanPham sp) {
		String sql = "insert into sanpham(tensanpham, mota, hinhanh, dongia, dongiaKM, soluong, ngaytao, hienthi, id_loai, id_thuonghieu) values (?,?,?,?,?,?,?,?,?,?)";
		int status = 0;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, sp.getTenSanPham());
			pst.setString(2, sp.getMoTa());
			pst.setString(3, sp.getHinhAnh());
			pst.setDouble(4, sp.getDonGia());
			pst.setDouble(5, sp.getDonGiaKhuyenMai());
			pst.setInt(6, sp.getSoLuong());
			pst.setDate(7, sp.getNgayTao());
			pst.setInt(8, sp.getHienThi());
			pst.setInt(9, sp.getId_loai());
			pst.setInt(10, sp.getId_thuongHieu());

			status = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Không thêm được sản phẩm");
		}
		return status;
	}

	public int xoaSanPham(int id) {
		int status = 0;
		String sql = "delete from sanpham where id=" + id;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Không thể xoá được sản phẩm");
		}
		return status;
	}

	public int suaSanPham(SanPham sp) {
		int status = 0;
		String sql = "update sanpham set tensanpham = ?, mota = ?, hinhanh = ?, dongia = ?, dongiaKM = ?, soluong = ?, ngaytao = ?, hienthi = ?, id_loai = ?, id_thuonghieu = ? where id = ?";
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement spm = ketNoi.prepareStatement(sql);
			spm.setString(1, sp.getTenSanPham());
			spm.setString(2, sp.getMoTa());
			spm.setString(3, sp.getHinhAnh());
			spm.setDouble(4, sp.getDonGia());
			spm.setDouble(5, sp.getDonGiaKhuyenMai());
			spm.setInt(6, sp.getSoLuong());
			spm.setDate(7, sp.getNgayTao());
			spm.setInt(8, sp.getHienThi());
			spm.setInt(9, sp.getId_loai());
			spm.setInt(10, sp.getId_thuongHieu());
			spm.setInt(11, sp.getId());
			status = spm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Sửa sản phẩm không thành công");
		}
		return status;
	}

}
