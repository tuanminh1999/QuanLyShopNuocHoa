package edu.sgu.bll;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.sgu.dal.ThuongHieuDAL;
import edu.sgu.dto.ThuongHieu;



public class ThuongHieuBLL {
	private List<ThuongHieu> lstThuongHieu = new ArrayList<ThuongHieu>();
	private ThuongHieuDAL thuongHieuDAL = new ThuongHieuDAL();

	public ThuongHieuBLL() {
		lstThuongHieu = thuongHieuDAL.dsThuongHieu();
	}

	public List<ThuongHieu> dsThuongHieu() {
		return lstThuongHieu;
	}

	public int themThuongHieu(ThuongHieu th) {
		return thuongHieuDAL.themThuongHieu(th);
	}

	public int xoaThuongHieu(int id) {
		return thuongHieuDAL.xoaThuongHieu(id);
	}

	public int suaThuongHieu(ThuongHieu th) {
		return thuongHieuDAL.suaThuongHieu(th);
	}

	public String hienThiTenThuongHieu(int id_thuongHieu) {
		for (ThuongHieu th : dsThuongHieu())
			if (id_thuongHieu == th.getId())
				return th.getTenThuongHieu();
		return null;
	}

	public List<ThuongHieu> timKiemThuongHieu(String search, String type) {
		List<ThuongHieu> lstThuongHieu = new ArrayList<>();
		for (ThuongHieu th : dsThuongHieu()) {
			if (type.equals("Tất cả")) {
				if (String.valueOf(th.getId()).toLowerCase().contains((search))
						|| th.getTenThuongHieu().toLowerCase().contains(search)
						|| th.getTenThuongHieu().toLowerCase().contains(search)
						|| th.getHinhAnh().toLowerCase().contains(search)) {
					lstThuongHieu.add(th);
				}
			} else {
				switch (type) {
				case "Mã thương hiệu":
					if (String.valueOf(th.getId()).toLowerCase().contains((search))) {
						lstThuongHieu.add(th);
					}
					break;
				case "Tên thương hiệu":
					if (th.getTenThuongHieu().toLowerCase().contains((search))) {
						lstThuongHieu.add(th);
					}
					break;
				case "Hình ảnh":
					if (th.getHinhAnh().toLowerCase().contains((search))) {
						lstThuongHieu.add(th);
					}
					break;
				}
			}
		}
		return lstThuongHieu;
	}
	
	public void timKiem(JTable thuongHieuTable, JTextField txtLoaiTimKiem, JComboBox<String> cboLoaiTimKiem) {
		DefaultTableModel dtm = (DefaultTableModel) thuongHieuTable.getModel();

		dtm.setRowCount(0);

		String search = txtLoaiTimKiem.getText().toLowerCase();
		String type = cboLoaiTimKiem.getSelectedItem().toString();

		List<ThuongHieu> lstThuongHieu = timKiemThuongHieu(search, type);
		for (ThuongHieu th : lstThuongHieu) {
			dtm.addRow(new Object[] { th.getId(), th.getTenThuongHieu(), th.getHinhAnh() });
		}

	}
	
	public void refreshTable(JTable thuongHieuTable) {
		DefaultTableModel dtm = (DefaultTableModel) thuongHieuTable.getModel();
		dtm.setRowCount(0);
		// Gọi lại thuongHieu lên để cập nhật
		ThuongHieuBLL thuongHieuNew = new ThuongHieuBLL();
		List<ThuongHieu> th = thuongHieuNew.dsThuongHieu();
		for (int i = 0; i < th.size(); i++) {
			dtm.addRow(new Object[] { th.get(i).getId(), th.get(i).getTenThuongHieu(), th.get(i).getHinhAnh() });
		}
		thuongHieuTable.setModel(dtm);
	}
	
	public void sapXep(JTable thuongHieuTable) {
		DefaultTableModel dtm = (DefaultTableModel) thuongHieuTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
		thuongHieuTable.setRowSorter(sorter);
	}

}
