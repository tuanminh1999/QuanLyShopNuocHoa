package edu.sgu.dto;

import java.sql.Date;

public class DonHang {
	private int id;
	private int id_khachHang;
	private Date ngayDatHang;
	private String tenNguoiNhanHang;
	private String dienThoaiNguoiNhan;
	private String diaChiGiaoHang;
	private String ghiChu;
	private int thanhToan;
	private int trangThai;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_khachHang() {
		return id_khachHang;
	}

	public void setId_khachHang(int id_khachHang) {
		this.id_khachHang = id_khachHang;
	}

	public Date getNgayDatHang() {
		return ngayDatHang;
	}

	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}

	public String getTenNguoiNhanHang() {
		return tenNguoiNhanHang;
	}

	public void setTenNguoiNhanHang(String tenNguoiNhanHang) {
		this.tenNguoiNhanHang = tenNguoiNhanHang;
	}

	public String getDienThoaiNguoiNhan() {
		return dienThoaiNguoiNhan;
	}

	public void setDienThoaiNguoiNhan(String dienThoaiNguoiNhan) {
		this.dienThoaiNguoiNhan = dienThoaiNguoiNhan;
	}

	public String getDiaChiGiaoHang() {
		return diaChiGiaoHang;
	}

	public void setDiaChiGiaoHang(String diaChiGiaoHang) {
		this.diaChiGiaoHang = diaChiGiaoHang;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public int getThanhToan() {
		return thanhToan;
	}

	public void setThanhToan(int thanhToan) {
		this.thanhToan = thanhToan;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

}
