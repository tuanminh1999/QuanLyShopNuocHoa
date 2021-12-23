package edu.sgu.bll;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.sgu.dal.DonHangDAL;
import edu.sgu.dto.DonHang;
import edu.sgu.utils.Format;



public class DonHangBLL {
	private List<DonHang> lstDonHang = new ArrayList<DonHang>();
	private DonHangDAL donHangDAL = new DonHangDAL();
	private ChiTietDonHangBLL chiTietDonHangBLL = new ChiTietDonHangBLL();
	private TrangThaiDonHangBLL trangThaiDonHangBLL = new TrangThaiDonHangBLL();

	public DonHangBLL() {
		lstDonHang = donHangDAL.dsDonHang();
	}

	public List<DonHang> dsDonHang() {
		return lstDonHang;
	}

	public List<DonHang> timKiemDonHang(String search, String type) {
		List<DonHang> lstDonHang = new ArrayList<>();
		for (DonHang dh : dsDonHang()) {
			if (type.equals("Tất cả")) {
				if (String.valueOf(dh.getId()).toLowerCase().contains((search))
						|| String.valueOf(dh.getId_khachHang()).toLowerCase().contains(search)
						|| Format.chuyenNgayVietNam(dh.getNgayDatHang()).toLowerCase().contains(search)
						|| dh.getTenNguoiNhanHang().toLowerCase().contains(search)
						|| dh.getDienThoaiNguoiNhan().toLowerCase().contains(search)
						|| dh.getDiaChiGiaoHang().toLowerCase().contains(search)
						|| dh.getGhiChu().toLowerCase().contains(search)
						|| (dh.getThanhToan() == 0 ? "Chưa thanh toán" : "Đã thanh toán").toLowerCase().contains(search)
						|| (trangThaiDonHangBLL.hienThiTenTrangThai(dh.getTrangThai())).toLowerCase().contains(search)
						|| (String.valueOf(chiTietDonHangBLL.tongTien(dh.getId())).toLowerCase().contains((search)))) {
					lstDonHang.add(dh);
				}
			} else {
				switch (type) {
				case "Mã hoá đơn":
					if (String.valueOf(dh.getId()).toLowerCase().contains((search))) {
						lstDonHang.add(dh);
					}
					break;
				case "Mã khách hàng":
					if (String.valueOf(dh.getId_khachHang()).toLowerCase().contains((search))) {
						lstDonHang.add(dh);
					}
					break;
				case "Ngày đặt":
					if (Format.chuyenNgayVietNam(dh.getNgayDatHang()).toLowerCase().contains((search))) {
						lstDonHang.add(dh);
					}
					break;
				case "Tên người nhận":
					if (dh.getTenNguoiNhanHang().toLowerCase().contains((search))) {
						lstDonHang.add(dh);
					}
					break;
				case "Điện thoại":
					if (dh.getDienThoaiNguoiNhan().toLowerCase().contains((search))) {
						lstDonHang.add(dh);
					}
					break;
				case "Địa chỉ":
					if (dh.getDiaChiGiaoHang().toLowerCase().contains((search))) {
						lstDonHang.add(dh);
					}
					break;
				case "Ghi chú":
					if (dh.getGhiChu().toLowerCase().contains((search))) {
						lstDonHang.add(dh);
					}
					break;
				case "Thanh toán":
					if ((dh.getThanhToan() == 0 ? "Chưa thanh toán" : "Đã thanh toán").toLowerCase()
							.contains((search))) {
						lstDonHang.add(dh);
					}
					break;
				case "Trạng thái":
					if (trangThaiDonHangBLL.hienThiTenTrangThai(dh.getTrangThai()).toLowerCase().contains((search))) {
						lstDonHang.add(dh);
					}
					break;
				case "Tổng tiền":
					if (String.valueOf(chiTietDonHangBLL.tongTien(dh.getId())).toLowerCase().contains((search))) {
						lstDonHang.add(dh);
					}
					break;
				}
			}
		}
		return lstDonHang;
	}
	
	public void sapXep(JTable donHangTable) {
		DefaultTableModel dtm = (DefaultTableModel) donHangTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
		donHangTable.setRowSorter(sorter);
	}

	public void refreshTable(JTable donHangTable) {
		DefaultTableModel dtm = (DefaultTableModel) donHangTable.getModel();
		dtm.setRowCount(0);

		// Gọi lại donhang vs chitietdonhang lên để cập nhật
		DonHangBLL donHangBLL = new DonHangBLL();
		ChiTietDonHangBLL chiTietDonHangBLL = new ChiTietDonHangBLL();

		List<DonHang> lstDonHang = donHangBLL.dsDonHang();
		for (DonHang dh : lstDonHang) {
			dtm.addRow(
					new Object[] { dh.getId(), dh.getId_khachHang(), Format.chuyenNgayVietNam(dh.getNgayDatHang()),
							dh.getTenNguoiNhanHang(), dh.getDienThoaiNguoiNhan(), dh.getDiaChiGiaoHang(),
							dh.getGhiChu(), dh.getThanhToan() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
							trangThaiDonHangBLL.hienThiTenTrangThai(dh.getTrangThai()),
							Format.chuyenTienVietNam(chiTietDonHangBLL.tongTien(dh.getId())) });
		}
		donHangTable.setModel(dtm);

	}

	public void timKiem(JTable donHangTable, JTextField txtLoaiTimKiem, JComboBox<String> cboLoaiTimKiem) {
		DefaultTableModel dtm = (DefaultTableModel) donHangTable.getModel();
		dtm.setRowCount(0);

		String search = txtLoaiTimKiem.getText().toLowerCase();
		String type = cboLoaiTimKiem.getSelectedItem().toString();

		List<DonHang> lstDonHang = timKiemDonHang(search, type);
		for (DonHang dh : lstDonHang) {
			dtm.addRow(
					new Object[] { dh.getId(), dh.getId_khachHang(), Format.chuyenNgayVietNam(dh.getNgayDatHang()),
							dh.getTenNguoiNhanHang(), dh.getDienThoaiNguoiNhan(), dh.getDiaChiGiaoHang(),
							dh.getGhiChu(), dh.getThanhToan() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
							trangThaiDonHangBLL.hienThiTenTrangThai(dh.getTrangThai()),
							Format.chuyenTienVietNam(chiTietDonHangBLL.tongTien(dh.getId())) });
		}

	}

}
