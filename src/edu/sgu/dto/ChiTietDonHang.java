package edu.sgu.dto;

public class ChiTietDonHang {
	private int id;
	private int id_donHang;
	private int id_sanPham;
	private int soLuong;
	private double donGia;
	private SanPham sanPham;
	private double thanhTien;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_donHang() {
		return id_donHang;
	}

	public void setId_donHang(int id_donHang) {
		this.id_donHang = id_donHang;
	}

	public int getId_sanPham() {
		return id_sanPham;
	}

	public void setId_sanPham(int id_sanPham) {
		this.id_sanPham = id_sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public double getThanhTien() {
		this.thanhTien = this.soLuong * this.donGia;
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

}
