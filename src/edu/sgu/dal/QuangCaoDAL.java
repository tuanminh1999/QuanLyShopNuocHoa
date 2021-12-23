package edu.sgu.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sgu.dto.QuangCao;

public class QuangCaoDAL {
	public List<QuangCao> dsQuangCao() {
		List<QuangCao> lstQuangCao = null;
		QuangCao qc = null;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			lstQuangCao = new ArrayList<>();
			String sql = "select * from quangcao";
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				qc = new QuangCao();
				qc.setId(rs.getInt("id"));
				qc.setHinhAnh(rs.getString("hinhanh"));
				qc.setThongDiep(rs.getString("thongdiep"));
				if (rs.getString("thongtinchitiet") == null)
					qc.setThongTinChiTiet("");
				else
					qc.setThongTinChiTiet(rs.getString("thongtinchitiet"));
				qc.setNgayDang(rs.getDate("ngaydang"));
				lstQuangCao.add(qc);
			}
		} catch (Exception e) {
			System.out.println("Không thể lấy được thông tin quảng cáo");
		}
		return lstQuangCao;
	}

	public int themQuangCao(QuangCao qc) {
		int status = 0;
		String sql = "insert into quangcao(hinhanh, thongdiep, thongtinchitiet, ngaydang) values(?,?,?,?)";
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			psm.setString(1, qc.getHinhAnh());
			psm.setString(2, qc.getThongDiep());
			psm.setString(3, qc.getThongTinChiTiet());
			psm.setDate(4, qc.getNgayDang());
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Không thể thêm quảng cáo");
		}
		return status;
	}

	public int xoaQuangCao(int id) {
		int status = 0;
		String sql = "delete from quangcao where id = " + id;
		try(Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Xoá quảng cáo không thành công");
		}
		return status;
	}
	
	public int suaQuangCao(QuangCao qc) {
		int status = 0;
		String sql = "update quangcao set hinhanh = ?, thongdiep = ?, thongtinchitiet = ?, ngaydang = ? where id = ?";
		try(Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			psm.setString(1, qc.getHinhAnh());
			psm.setString(2, qc.getThongDiep());
			psm.setString(3, qc.getThongTinChiTiet());
			psm.setDate(4, qc.getNgayDang());
			psm.setInt(5, qc.getId());
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Cập nhật quảng cáo thất bại");
		}
		return status;
	}
	
}
