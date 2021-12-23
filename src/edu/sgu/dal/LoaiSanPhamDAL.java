package edu.sgu.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sgu.dto.LoaiSanPham;

public class LoaiSanPhamDAL {
	public List<LoaiSanPham> dsLoaiSanPham(){
		List<LoaiSanPham> lstLoai = null; 
		LoaiSanPham loai = null;
		try (Connection ketNoi = KetNoi.getKetNoi()) {
			lstLoai = new ArrayList<>();
			String sql = "select * from loai";
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				loai = new LoaiSanPham();
				loai.setId(rs.getInt("id"));
				loai.setTenLoai(rs.getString("tenloai"));
				lstLoai.add(loai);
			}
		} catch (Exception e) {
			System.out.println("Không thể lấy được thông tin loại sản phẩm");
		}
		return lstLoai;
	}
	
	public int themLoaiSanPham(LoaiSanPham lsp) {
		String sql = "insert into loai(tenloai) values(?)";
		int status = 0;
		try(Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, lsp.getTenLoai());
			status = pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Thêm không thành công loại sản phẩm");
		}
		return status;
	}
	
	public int xoaLoaiSanPham(int id) {
		int status = 0;
		String sql = "delete from loai where id = " + id;
		try(Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Xoá loại sẩn phẩm không thành công");
		}
		return status;
	}
	
	public int suaLoaiSanPham(LoaiSanPham lsp) {
		int status = 0;
		String sql = "update loai set tenloai = ? where id = ?";
		try(Connection ketNoi = KetNoi.getKetNoi()) {
			PreparedStatement psm = ketNoi.prepareStatement(sql);
			psm.setString(1, lsp.getTenLoai());
			psm.setInt(2, lsp.getId());
			status = psm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Cập nhật sản phẩm không thành công");
		}
		return status;
	}
	
}
