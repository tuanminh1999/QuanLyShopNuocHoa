package edu.sgu.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sgu.dto.ThuongHieu;

public class ThuongHieuDAL {
	public List<ThuongHieu> dsThuongHieu() {
		List<ThuongHieu> lstThuongHieu = null;
		ThuongHieu th = null;
		try (Connection ketnoi = KetNoi.getKetNoi()) {
			lstThuongHieu = new ArrayList<>();
			String sql = "select* from thuonghieu";
			Statement stm = ketnoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				th = new ThuongHieu();
				th.setId(rs.getInt("id"));
				th.setTenThuongHieu(rs.getString("tenthuonghieu"));
				th.setHinhAnh(rs.getString("hinhanh"));
				lstThuongHieu.add(th);
			}
		} catch (Exception e) {
			System.out.println("Không thể lấy được thông tin thương hiệu");
		}
		return lstThuongHieu;
	}

	public int themThuongHieu(ThuongHieu th) {
		int status = 0;
		String sql = "insert into thuonghieu(tenthuonghieu, hinhanh) values(?,?)";
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			psm.setString(1, th.getTenThuongHieu());
			psm.setString(2, th.getHinhAnh());
			status = psm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Không thể thêm thương hiệu");
		}
		return status;
	}
	
	public int xoaThuongHieu(int id) {
		int status = 0;
		String sql = "delete from thuonghieu where id = " + id;
		try(Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Xoá thương hiệu thất bại");
		}
		return status;
	}
	
	public int suaThuongHieu(ThuongHieu th) {
		int status = 0;
		String sql = "update thuonghieu set tenthuonghieu = ?, hinhanh = ? where id = ?";
		try(Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			psm.setString(1, th.getTenThuongHieu());
			psm.setString(2, th.getHinhAnh());
			psm.setInt(3, th.getId());
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Sửa thương hiệu thất bại");
		}
		return status;
	}
	
}
