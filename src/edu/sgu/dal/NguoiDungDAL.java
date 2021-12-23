package edu.sgu.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sgu.dto.NguoiDung;

public class NguoiDungDAL {
	public NguoiDung login(String email, String password) {
		NguoiDung nd = null;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			String sql = "select * from nguoidung where email='" + email + "' and password = '" + password + "'";
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				nd = new NguoiDung();
				nd.setId(rs.getInt("id"));
				nd.setEmail(rs.getString("email"));
				nd.setPassword(rs.getString("password"));
				nd.setHoTen(rs.getString("hoten"));
				nd.setDiaChi(rs.getString("diachi"));
				nd.setDtdt(rs.getString("dtdd"));
				nd.setVaiTro(rs.getInt("id_vaitro"));
			}
		} catch (Exception e) {
			System.out.println("Không thể tìm người dùng này");
		}
		return nd;
	}

	public List<NguoiDung> dsNguoiDung() {
		List<NguoiDung> lstNguoiDung = null;
		NguoiDung nd = null;
		try (Connection ketnoi = KetNoi.getKetNoi()) {
			lstNguoiDung = new ArrayList<>();
			String sql = "SELECT * from nguoidung";
			Statement stm = ketnoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				nd = new NguoiDung();
				nd.setId(rs.getInt("id"));
				nd.setEmail(rs.getString("email"));
				nd.setPassword(rs.getString("password"));
				nd.setHoTen(rs.getString("hoten"));
				nd.setDiaChi(rs.getString("diachi"));
				nd.setDtdt(rs.getString("dtdd"));
				nd.setVaiTro(rs.getInt("id_vaitro"));
				lstNguoiDung.add(nd);
			}
		} catch (Exception e) {
			System.out.println("Không thể lấy được thông tin người dùng");
		}
		return lstNguoiDung;
	}

	public int themNguoiDung(NguoiDung nd) {
		int status = 0;
		String sql = "insert into nguoidung(email, password, hoten, diachi, dtdd, id_vaitro) values(?,?,?,?,?,?)";
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			psm.setString(1, nd.getEmail());
			psm.setString(2, nd.getPassword());
			psm.setString(3, nd.getHoTen());
			psm.setString(4, nd.getDiaChi());
			psm.setString(5, nd.getDtdt());
			psm.setInt(6, nd.getVaiTro());
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Thêm người dùng không thành công");
		}
		return status;
	}

	public int xoaNguoiDung(int id) {
		int status = 0;
		String sql = "delete from nguoidung where id=" + id;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Không xoá được người dùng");
		}
		return status;
	}

	public int suaNguoiDung(NguoiDung nd) {
		int status = 0;
		String sql = "update nguoidung set email = ?, password = ?, hoten = ?, diachi = ?, dtdd = ?, id_vaitro = ? where id = ?";
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			psm.setString(1, nd.getEmail());
			psm.setString(2, nd.getPassword());
			psm.setString(3, nd.getHoTen());
			psm.setString(4, nd.getDiaChi());
			psm.setString(5, nd.getDtdt());
			psm.setInt(6, nd.getVaiTro());
			psm.setInt(7, nd.getId());
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Sửa người dùng không thành công");
		}
		return status;
	}

}
