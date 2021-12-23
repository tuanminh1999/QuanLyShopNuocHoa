package edu.sgu.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sgu.dto.VaiTro;

public class VaiTroDAL {
	public List<VaiTro> dsVaiTro() {
		List<VaiTro> lstVaiTro = null;
		VaiTro vt = null;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			lstVaiTro = new ArrayList<>();
			String sql = "select * from vaitro";
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				vt = new VaiTro();
				vt.setId(rs.getInt("id"));
				vt.setTenVaiTro(rs.getString("tenvaitro"));
				vt.setMoTa(rs.getString("mota"));
				lstVaiTro.add(vt);
			}
		} catch (Exception e) {
			System.out.println("Không thể lấy được thông tin vai trò");
		}
		return lstVaiTro;
	}

	public int themVaiTro(VaiTro vt) {
		int status = 0;
		String sql = "insert into vaitro(tenvaitro, mota) values(?,?)";
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			psm.setString(1, vt.getTenVaiTro());
			psm.setString(2, vt.getMoTa());

			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Thêm vai trò không thành công");
		}
		return status;
	}

	public int xoaVaiTro(int id) {
		int status = 0;
		String sql = "delete from vaitro where id=" + id;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Xoá vai trò không thành công");
		}
		return status;
	}

	public int suaVaiTro(VaiTro vt) {
		int status = 0;
		String sql = "update vaitro set tenvaitro = ?, mota = ? where id = ?";
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			psm.setString(1, vt.getTenVaiTro());
			psm.setString(2, vt.getMoTa());
			psm.setInt(3, vt.getId());
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Sửa vai trò không thành công");
		}
		return status;
	}
}
