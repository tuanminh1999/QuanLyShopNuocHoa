package edu.sgu.bll;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.sgu.dal.LoaiSanPhamDAL;
import edu.sgu.dto.LoaiSanPham;



public class LoaiSanPhamBLL {
	private List<LoaiSanPham> lstLoaiSanPham = new ArrayList<LoaiSanPham>();
	private LoaiSanPhamDAL loaiSanPhamDAL = new LoaiSanPhamDAL();

	public LoaiSanPhamBLL() {
		lstLoaiSanPham = loaiSanPhamDAL.dsLoaiSanPham();
	}

	public List<LoaiSanPham> dsLoaiSanPham() {
		return lstLoaiSanPham;
	}

	public int themLoaiSanPham(LoaiSanPham lsp) {
		return loaiSanPhamDAL.themLoaiSanPham(lsp);
	}

	public int xoaLoaiSanPham(int id) {
		return loaiSanPhamDAL.xoaLoaiSanPham(id);
	}

	public int suaLoaiSanPham(LoaiSanPham lsp) {
		return loaiSanPhamDAL.suaLoaiSanPham(lsp);
	}

	public String hienThiTenLoai(int id_loai) {
		for (LoaiSanPham loaiSP : dsLoaiSanPham())
			if (id_loai == loaiSP.getId())
				return loaiSP.getTenLoai();
		return null;
	}

	public List<LoaiSanPham> timKiemLoaiSanPham(String search, String type) {
		List<LoaiSanPham> lstLoaiSanPham = new ArrayList<>();
		for (LoaiSanPham loai : dsLoaiSanPham()) {
			if (type.equals("Tất cả")) {
				if (String.valueOf(loai.getId()).toLowerCase().contains((search))
						|| loai.getTenLoai().toLowerCase().contains(search)) {
					lstLoaiSanPham.add(loai);
				}
			} else {
				switch (type) {
				case "Mã loại sản phẩm":
					if (String.valueOf(loai.getId()).toLowerCase().contains((search))) {
						lstLoaiSanPham.add(loai);
					}
					break;
				case "Tên loại sản phẩm":
					if (loai.getTenLoai().toLowerCase().contains((search))) {
						lstLoaiSanPham.add(loai);
					}
					break;
				}
			}
		}
		return lstLoaiSanPham;
	}
	
	public void timKiem(JTable loaiSanPhamTable, JTextField txtLoaiTimKiem, JComboBox<String> cboLoaiTimKiem) {
		DefaultTableModel dtm = (DefaultTableModel) loaiSanPhamTable.getModel();

		dtm.setRowCount(0);

		String search = txtLoaiTimKiem.getText().toLowerCase();
		String type = cboLoaiTimKiem.getSelectedItem().toString();

		List<LoaiSanPham> lstLoaiSanPham = timKiemLoaiSanPham(search, type);
		for (LoaiSanPham loai : lstLoaiSanPham) {
			dtm.addRow(new Object[] { loai.getId(), loai.getTenLoai() });
		}

	}
	
	public void refreshTable(JTable loaiSanPhamTable) {
		DefaultTableModel dtm = (DefaultTableModel) loaiSanPhamTable.getModel();
		dtm.setRowCount(0);
		// Gọi lại loại sản phẩm lên để cập nhật
		LoaiSanPhamBLL loaiSanPhamBLL = new LoaiSanPhamBLL();
		List<LoaiSanPham> loai = loaiSanPhamBLL.dsLoaiSanPham();
		for (int i = 0; i < loai.size(); i++) {
			dtm.addRow(new Object[] { loai.get(i).getId(), loai.get(i).getTenLoai() });
		}
		loaiSanPhamTable.setModel(dtm);
	}
	
	public void sapXep(JTable loaiSanPhamTable) {
		DefaultTableModel dtm = (DefaultTableModel) loaiSanPhamTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
		loaiSanPhamTable.setRowSorter(sorter);
	}

}
