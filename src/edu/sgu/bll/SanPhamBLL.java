package edu.sgu.bll;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.sgu.dal.SanPhamDAL;
import edu.sgu.dto.SanPham;
import edu.sgu.utils.Format;


public class SanPhamBLL {
	private List<SanPham> lstSanPham = new ArrayList<SanPham>();
	private SanPhamDAL sanPhamDAL = new SanPhamDAL();

	//
	private LoaiSanPhamBLL loaiSanPhamBLL = new LoaiSanPhamBLL();
	private ThuongHieuBLL thuongHieuBLL = new ThuongHieuBLL();

	public SanPhamBLL() {
		lstSanPham = sanPhamDAL.dsSanPham();
	}

	public List<SanPham> dsSanPham() {
		return lstSanPham;
	}

	public int themSanPham(SanPham sp) {
		return sanPhamDAL.themSanPham(sp);
	}

	public int xoaSanPham(int id) {
		return sanPhamDAL.xoaSanPham(id);
	}

	public int suaSanPham(SanPham sp) {
		return sanPhamDAL.suaSanPham(sp);
	}

	public List<SanPham> timKiemSanPham(String search, String type) {
		List<SanPham> sanPhamTimKiem = new ArrayList<>();
		for (SanPham sp : dsSanPham()) {
			if (type.equals("Tất cả")) {
				if (String.valueOf(sp.getId()).toLowerCase().contains((search))
						|| sp.getTenSanPham().toLowerCase().contains(search)
						|| sp.getMoTa().toLowerCase().contains(search) || sp.getHinhAnh().toLowerCase().contains(search)
						|| String.valueOf(sp.getDonGia()).toLowerCase().contains(search)
						|| String.valueOf(sp.getDonGiaKhuyenMai()).toLowerCase().contains(search)
						|| String.valueOf(sp.getSoLuong()).toLowerCase().contains(search)
						|| Format.chuyenNgayVietNam(sp.getNgayTao()).toLowerCase().contains(search)
						|| (sp.getHienThi() == 1 ? "Hiện" : "Ẩn").toLowerCase().contains(search)
						|| loaiSanPhamBLL.hienThiTenLoai(sp.getId_loai()).toLowerCase().contains(search)
						|| thuongHieuBLL.hienThiTenThuongHieu(sp.getId_thuongHieu()).toLowerCase().contains(search)) {
					sanPhamTimKiem.add(sp);
				}
			} else {
				switch (type) {
				case "Mã sản phẩm":
					if (String.valueOf(sp.getId()).toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				case "Tên sản phẩm":
					if (sp.getTenSanPham().toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				case "Mô tả":
					if (sp.getMoTa().toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				case "Hình ảnh":
					if (sp.getHinhAnh().toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				case "Đơn giá":
					if (String.valueOf(sp.getDonGia()).toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				case "Đơn giá khuyến mãi":
					if (String.valueOf(sp.getDonGiaKhuyenMai()).toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				case "Số lượng":
					if (String.valueOf(sp.getSoLuong()).toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				case "Ngày tạo":
					if (Format.chuyenNgayVietNam(sp.getNgayTao()).toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				case "Hiển thị":
					if ((sp.getHienThi() == 1 ? "Hiện" : "Ẩn").toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				case "Loại sản phẩm":
					if (loaiSanPhamBLL.hienThiTenLoai(sp.getId_loai()).toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				case "Thương hiệu":
					if (thuongHieuBLL.hienThiTenThuongHieu(sp.getId_thuongHieu()).toLowerCase().contains((search))) {
						sanPhamTimKiem.add(sp);
					}
					break;
				}
			}
		}
		return sanPhamTimKiem;
	}
	
	public void sapXep(JTable sanPhamTable) {
		DefaultTableModel dtm = (DefaultTableModel) sanPhamTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
		sanPhamTable.setRowSorter(sorter);

	}

	public void refreshTable(JTable sanPhamTable) {
		DefaultTableModel dtm = (DefaultTableModel) sanPhamTable.getModel();
		dtm.setRowCount(0);

		// Gọi lại sanPham lên để cập nhật
		SanPhamBLL sanPhamBLLNew = new SanPhamBLL();
		List<SanPham> sp = sanPhamBLLNew.dsSanPham();

		for (int i = 0; i < sp.size(); i++) {
			dtm.addRow(new Object[] { sp.get(i).getId(), sp.get(i).getTenSanPham(), sp.get(i).getMoTa(),
					sp.get(i).getHinhAnh(), Format.chuyenTienVietNam(sp.get(i).getDonGia()),
					Format.chuyenTienVietNam(sp.get(i).getDonGiaKhuyenMai()), sp.get(i).getSoLuong(),
					Format.chuyenNgayVietNam(sp.get(i).getNgayTao()), sp.get(i).getHienThi() == 1 ? "Hiện" : "Ẩn",
					loaiSanPhamBLL.hienThiTenLoai(sp.get(i).getId_loai()),
					thuongHieuBLL.hienThiTenThuongHieu(sp.get(i).getId_thuongHieu()) });
		}
		sanPhamTable.setModel(dtm);

	}

	public void timKiem(JTable sanPhamTable, JTextField txtLoaiTimKiem, JComboBox<String> cboLoaiTimKiem) {
		DefaultTableModel dtm = (DefaultTableModel) sanPhamTable.getModel();
		dtm.setRowCount(0);

		String search = txtLoaiTimKiem.getText().toLowerCase();
		String type = cboLoaiTimKiem.getSelectedItem().toString();

		List<SanPham> lstTimKiem = timKiemSanPham(search, type);
		for (SanPham sp : lstTimKiem) {
			dtm.addRow(new Object[] { sp.getId(), sp.getTenSanPham(), sp.getMoTa(), sp.getHinhAnh(),
					Format.chuyenTienVietNam(sp.getDonGia()), Format.chuyenTienVietNam(sp.getDonGiaKhuyenMai()),
					sp.getSoLuong(), Format.chuyenNgayVietNam(sp.getNgayTao()), sp.getHienThi() == 1 ? "Hiện" : "Ẩn",
					loaiSanPhamBLL.hienThiTenLoai(sp.getId_loai()),
					thuongHieuBLL.hienThiTenThuongHieu(sp.getId_thuongHieu()) });
		}

	}
	
}
