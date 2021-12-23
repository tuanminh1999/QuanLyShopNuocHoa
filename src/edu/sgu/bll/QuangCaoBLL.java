package edu.sgu.bll;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.sgu.dal.QuangCaoDAL;
import edu.sgu.dto.QuangCao;
import edu.sgu.utils.Format;



public class QuangCaoBLL {
	private List<QuangCao> lstQuangCao = new ArrayList<>();
	private QuangCaoDAL quangCaoDAL = new QuangCaoDAL();

	public QuangCaoBLL() {
		lstQuangCao = quangCaoDAL.dsQuangCao();
	}

	public List<QuangCao> dsQuangCao() {
		return lstQuangCao;
	}

	public int themQuangCao(QuangCao qc) {
		return quangCaoDAL.themQuangCao(qc);
	}

	public int xoaQuangCao(int id) {
		return quangCaoDAL.xoaQuangCao(id);
	}

	public int suaQuangCao(QuangCao qc) {
		return quangCaoDAL.suaQuangCao(qc);
	}

	public List<QuangCao> timKiemQuangCao(String search, String type) {
		List<QuangCao> lstQuangCao = new ArrayList<>();
		for (QuangCao qc : dsQuangCao()) {
			if (type.equals("Tất cả")) {
				if (String.valueOf(qc.getId()).toLowerCase().contains((search))
						|| qc.getHinhAnh().toLowerCase().contains(search)
						|| qc.getThongDiep().toLowerCase().contains(search)
						|| qc.getThongTinChiTiet().toLowerCase().contains(search)
						|| Format.chuyenNgayVietNam(qc.getNgayDang()).toLowerCase().contains(search)) {
					lstQuangCao.add(qc);
				}
			} else {
				switch (type) {
				case "Mã quảng cáo":
					if (String.valueOf(qc.getId()).toLowerCase().contains((search))) {
						lstQuangCao.add(qc);
					}
					break;
				case "Hình ảnh":
					if (qc.getHinhAnh().toLowerCase().contains((search))) {
						lstQuangCao.add(qc);
					}
					break;
				case "Thông điệp":
					if (qc.getThongDiep().toLowerCase().contains((search))) {
						lstQuangCao.add(qc);
					}
					break;
				case "Thông tin chi tiết":
					if (qc.getThongTinChiTiet().toLowerCase().contains((search))) {
						lstQuangCao.add(qc);
					}
					break;
				case "Ngày đăng":
					if (Format.chuyenNgayVietNam(qc.getNgayDang()).toLowerCase().contains((search))) {
						lstQuangCao.add(qc);
					}
					break;
				}
			}
		}
		return lstQuangCao;
	}
	
	public void sapXep(JTable quangCaoTable) {
		DefaultTableModel dtm = (DefaultTableModel) quangCaoTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
		quangCaoTable.setRowSorter(sorter);
	}

	public void refreshTable(JTable quangCaoTable) {
		DefaultTableModel dtm = (DefaultTableModel) quangCaoTable.getModel();
		dtm.setRowCount(0);
		
		// Gọi lại thuongHieu lên để cập nhật
		QuangCaoBLL quangCaoBLLNew = new QuangCaoBLL();
		List<QuangCao> qc = quangCaoBLLNew.dsQuangCao();
		for (int i = 0; i < qc.size(); i++) {
			dtm.addRow(new Object[] { qc.get(i).getId(), qc.get(i).getHinhAnh(), qc.get(i).getThongDiep(),
					qc.get(i).getThongTinChiTiet(), Format.chuyenNgayVietNam(qc.get(i).getNgayDang()) });
		}
		quangCaoTable.setModel(dtm);
	}

	public void timKiem(JTable quangCaoTable, JTextField txtLoaiTimKiem, JComboBox<String> cboLoaiTimKiem) {
		DefaultTableModel dtm = (DefaultTableModel) quangCaoTable.getModel();

		dtm.setRowCount(0);

		String search = txtLoaiTimKiem.getText().toLowerCase();
		String type = cboLoaiTimKiem.getSelectedItem().toString();

		List<QuangCao> lstQuangCao = timKiemQuangCao(search, type);
		for (QuangCao qc : lstQuangCao) {
			dtm.addRow(new Object[] { qc.getId(), qc.getHinhAnh(), qc.getThongDiep(), qc.getThongTinChiTiet(),
					Format.chuyenNgayVietNam(qc.getNgayDang()) });
		}

	}

}
