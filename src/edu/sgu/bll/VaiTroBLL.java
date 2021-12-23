package edu.sgu.bll;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.sgu.dal.VaiTroDAL;
import edu.sgu.dto.VaiTro;



public class VaiTroBLL {
	private VaiTroDAL vaiTroDAL = new VaiTroDAL();
	private List<VaiTro> lstVaiTro = new ArrayList<VaiTro>();

	public VaiTroBLL() {
		lstVaiTro = vaiTroDAL.dsVaiTro();
	}

	public List<VaiTro> dsVaiTro() {
		return lstVaiTro;
	}

	public int themVaiTro(VaiTro vt) {
		return vaiTroDAL.themVaiTro(vt);
	}

	public int xoaVaiTro(int id) {
		return vaiTroDAL.xoaVaiTro(id);
	}

	public int suaVaiTro(VaiTro vt) {
		return vaiTroDAL.suaVaiTro(vt);
	}

	public String hienThiTenVaiTro(int id_vaiTro) {
		for (VaiTro vt : dsVaiTro())
			if (id_vaiTro == vt.getId())
				return vt.getTenVaiTro();
		return null;
	}

	public List<VaiTro> timKiemVaiTro(String search, String type) {
		List<VaiTro> lstVaiTro = new ArrayList<>();
		for (VaiTro vt : dsVaiTro()) {
			if (type.equals("Tất cả")) {
				if (String.valueOf(vt.getId()).toLowerCase().contains((search))
						|| vt.getTenVaiTro().toLowerCase().contains(search)
						|| vt.getMoTa().toLowerCase().contains(search)) {
					lstVaiTro.add(vt);
				}
			} else {
				switch (type) {
				case "Mã vai trò":
					if (String.valueOf(vt.getId()).toLowerCase().contains((search))) {
						lstVaiTro.add(vt);
					}
					break;
				case "Tên vai trò":
					if (vt.getTenVaiTro().toLowerCase().contains((search))) {
						lstVaiTro.add(vt);
					}
					break;
				case "Mô tả":
					if (vt.getMoTa().toLowerCase().contains((search))) {
						lstVaiTro.add(vt);
					}
					break;
				}
			}
		}
		return lstVaiTro;
	}
	
	public void sapXep(JTable vaiTroTable) {
		DefaultTableModel dtm = (DefaultTableModel) vaiTroTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
		vaiTroTable.setRowSorter(sorter);
	}

	public void refreshTable(JTable vaiTroTable) {
		DefaultTableModel dtm = (DefaultTableModel) vaiTroTable.getModel();
		dtm.setRowCount(0);
		
		// Gọi lại thuongHieu lên để cập nhật
		VaiTroBLL vaiTroBLLNew = new VaiTroBLL();
		List<VaiTro> vt = vaiTroBLLNew.dsVaiTro();
		for (int i = 0; i < vt.size(); i++) {
			dtm.addRow(new Object[] { vt.get(i).getId(), vt.get(i).getTenVaiTro(), vt.get(i).getMoTa() });
		}
		vaiTroTable.setModel(dtm);
	}

	public void timKiem(JTable vaiTroTable, JTextField txtLoaiTimKiem, JComboBox<String> cboLoaiTimKiem) {
		DefaultTableModel dtm = (DefaultTableModel) vaiTroTable.getModel();

		dtm.setRowCount(0);

		String search = txtLoaiTimKiem.getText().toLowerCase();
		String type = cboLoaiTimKiem.getSelectedItem().toString();

		List<VaiTro> lstVaiTro = timKiemVaiTro(search, type);
		for (VaiTro vt : lstVaiTro) {
			dtm.addRow(new Object[] { vt.getId(), vt.getTenVaiTro(), vt.getMoTa() });
		}

	}
}
