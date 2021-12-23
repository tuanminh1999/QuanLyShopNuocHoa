package edu.sgu.bll;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.sgu.dal.ChiTietDonHangDAL;
import edu.sgu.dto.ChiTietDonHang;
import edu.sgu.utils.Format;

public class ChiTietDonHangBLL {
	
	private List<ChiTietDonHang> lstChiTietDonHang = new ArrayList<>();
	private ChiTietDonHangDAL chiTietDonHangDAL = new ChiTietDonHangDAL();

	public ChiTietDonHangBLL() {
		lstChiTietDonHang = chiTietDonHangDAL.docTatCa();
	}

	public List<ChiTietDonHang> thongTinChiTiet(int id_hoaDon) {
		List<ChiTietDonHang> lstChiTiet = new ArrayList<>();
			for(ChiTietDonHang ctdh : lstChiTietDonHang) {
				if(ctdh.getId_donHang() == id_hoaDon) {
					lstChiTiet.add(ctdh);
				}
			}
		return lstChiTiet;
	}

	public double tongTien(int id_hoaDon) {
		double tongTien = 0;
		for (ChiTietDonHang o : thongTinChiTiet(id_hoaDon)) {
			tongTien += o.getThanhTien();
		}
		return tongTien;
	}
	
	public List<ChiTietDonHang> timKiemChiTietDonHang(String search, String type, int id_donHang) {
		List<ChiTietDonHang> ctdhTimKiem = new ArrayList<>();
		for (ChiTietDonHang ct : thongTinChiTiet(id_donHang)) {
			if (type.equals("Tất cả")) {
				if (String.valueOf(ct.getId()).toLowerCase().contains((search))
						|| ct.getSanPham().getTenSanPham().toLowerCase().contains(search)
						|| String.valueOf(ct.getSoLuong()).toLowerCase().contains(search)
						|| String.valueOf(ct.getDonGia()).toLowerCase().contains(search)
						|| String.valueOf(ct.getThanhTien()).toLowerCase().contains(search)) {
					ctdhTimKiem.add(ct);
				}
			} else {
				switch (type) {
				case "Mã sản phẩm":
					if (String.valueOf(ct.getId()).toLowerCase().contains((search))) {
						ctdhTimKiem.add(ct);
					}
					break;
				case "Tên sản phẩm":
					if (ct.getSanPham().getTenSanPham().toLowerCase().contains((search))) {
						ctdhTimKiem.add(ct);
					}
					break;
				case "Số lượng":
					if (String.valueOf(ct.getSoLuong()).toLowerCase().contains((search))) {
						ctdhTimKiem.add(ct);
					}
					break;
				case "Đơn giá":
					if (String.valueOf(ct.getDonGia()).toLowerCase().contains((search))) {
						ctdhTimKiem.add(ct);
					}
					break;
				case "Thành tiền":
					if (String.valueOf(ct.getThanhTien()).toLowerCase().contains((search))) {
						ctdhTimKiem.add(ct);
					}
					break;
				}
			}
		}
		return ctdhTimKiem;

	}
	
	public void timKiem(JTable sanPhamTable, JTextField txtLoaiTimKiem, JComboBox<String> cboLoaiTimKiem, int id_donHang) {
		DefaultTableModel dtm = (DefaultTableModel) sanPhamTable.getModel();
		dtm.setRowCount(0);

		String search = txtLoaiTimKiem.getText().toLowerCase();
		String type = cboLoaiTimKiem.getSelectedItem().toString();

		List<ChiTietDonHang> lstTimKiem = timKiemChiTietDonHang(search, type, id_donHang);
		for (ChiTietDonHang ctdh : lstTimKiem) {
			dtm.addRow(new Object[] { ctdh.getId(), ctdh.getSanPham().getTenSanPham(), ctdh.getSanPham().getHinhAnh(),
					ctdh.getSoLuong(), Format.chuyenTienVietNam(ctdh.getDonGia()),
					Format.chuyenTienVietNam(ctdh.getThanhTien()) });
		}

	}
	
	public void refreshTable(JTable sanPhamTable, int id_donHang) {
		DefaultTableModel dtm = (DefaultTableModel) sanPhamTable.getModel();
		dtm.setRowCount(0);
		// Gọi lại sanPham lên để cập nhật
		ChiTietDonHangBLL chiTietDonHangBLL = new ChiTietDonHangBLL();
		List<ChiTietDonHang> ct = chiTietDonHangBLL.thongTinChiTiet(id_donHang);
		for (int i = 0; i < ct.size(); i++) {
			dtm.addRow(new Object[] { ct.get(i).getId(), ct.get(i).getSanPham().getTenSanPham(), ct.get(i).getSanPham().getHinhAnh(),
					ct.get(i).getSoLuong(), Format.chuyenTienVietNam(ct.get(i).getDonGia()),
					Format.chuyenTienVietNam(ct.get(i).getThanhTien()) });
		}
		sanPhamTable.setModel(dtm);

	}
	
	public void sapXep(JTable sanPhamTable) {
		DefaultTableModel dtm = (DefaultTableModel) sanPhamTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
		sanPhamTable.setRowSorter(sorter);
	}

}
