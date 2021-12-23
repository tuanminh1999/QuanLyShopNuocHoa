package edu.sgu.bll;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.sgu.dal.NguoiDungDAL;
import edu.sgu.dto.NguoiDung;


public class NguoiDungBLL {
	private List<NguoiDung> lstNguoiDung = new ArrayList<>();
	private NguoiDungDAL nguoiDungDAL = new NguoiDungDAL();

	//

	private VaiTroBLL vaiTroBLL = new VaiTroBLL();

	public NguoiDungBLL() {
		lstNguoiDung = nguoiDungDAL.dsNguoiDung();
	}

	public List<NguoiDung> dsNguoiDung() {
		return lstNguoiDung;
	}

	public int themNguoiDung(NguoiDung nd) {
		return nguoiDungDAL.themNguoiDung(nd);
	}

	public int xoaNguoiDung(int id) {
		return nguoiDungDAL.xoaNguoiDung(id);
	}

	public int suaNguoiDung(NguoiDung nd) {
		return nguoiDungDAL.suaNguoiDung(nd);
	}
	
	public NguoiDung dangNhap(String email, String password) {
		for (NguoiDung nd : dsNguoiDung()) {
			if (email.equals(nd.getEmail()) && password.equals(nd.getPassword()))
				return nd;
		}
		return null;
	}

	public List<NguoiDung> timKiemNguoiDung(String search, String type) {
		List<NguoiDung> lstNguoiDung = new ArrayList<>();
		for (NguoiDung nd : dsNguoiDung()) {
			if (type.equals("Tất cả")) {
				if (String.valueOf(nd.getId()).toLowerCase().contains((search))
						|| nd.getHoTen().toLowerCase().contains(search) || nd.getEmail().toLowerCase().contains(search)
						|| nd.getDiaChi().toLowerCase().contains(search) || nd.getDtdt().toLowerCase().contains(search)
						|| vaiTroBLL.hienThiTenVaiTro(nd.getVaiTro()).toLowerCase().contains(search)) {
					lstNguoiDung.add(nd);
				}
			} else {
				switch (type) {
				case "Mã người dùng":
					if (String.valueOf(nd.getId()).toLowerCase().contains((search))) {
						lstNguoiDung.add(nd);
					}
					break;
				case "Email":
					if (nd.getEmail().toLowerCase().contains((search))) {
						lstNguoiDung.add(nd);
					}
					break;
				case "Họ tên":
					if (nd.getHoTen().toLowerCase().contains((search))) {
						lstNguoiDung.add(nd);
					}
					break;
				case "Địa chỉ":
					if (nd.getDiaChi().toLowerCase().contains((search))) {
						lstNguoiDung.add(nd);
					}
					break;
				case "Điện thoại":
					if (nd.getDtdt().toLowerCase().contains((search))) {
						lstNguoiDung.add(nd);
					}
					break;
				case "Vai trò":
					if (vaiTroBLL.hienThiTenVaiTro(nd.getVaiTro()).toLowerCase().contains((search))) {
						lstNguoiDung.add(nd);
					}
					break;
				}
			}
		}
		return lstNguoiDung;
	}
	
	public void sapXep(JTable nguoiDungTable) {
		DefaultTableModel dtm = (DefaultTableModel) nguoiDungTable.getModel();

		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
		nguoiDungTable.setRowSorter(sorter);
	}

	public void refreshTable(JTable nguoiDungTable) {
		DefaultTableModel dtm = (DefaultTableModel) nguoiDungTable.getModel();
		dtm.setRowCount(0);
		// Gọi lại nguoidung lên để cập nhật
		NguoiDungBLL nguoiDungBLLNew = new NguoiDungBLL();
		List<NguoiDung> lstNguoiDung = nguoiDungBLLNew.dsNguoiDung();
		for (NguoiDung nd : lstNguoiDung) {
			dtm.addRow(new Object[] { nd.getId(), nd.getEmail(), nd.getPassword(), nd.getHoTen(), nd.getDiaChi(),
					nd.getDtdt(), vaiTroBLL.hienThiTenVaiTro(nd.getVaiTro()) });
		}
		nguoiDungTable.setModel(dtm);
	}

	public void timKiem(JTable nguoiDungTable, JTextField txtLoaiTimKiem, JComboBox<String> cboLoaiTimKiem) {
		DefaultTableModel dtm = (DefaultTableModel) nguoiDungTable.getModel();

		dtm.setRowCount(0);

		String search = txtLoaiTimKiem.getText().toLowerCase();
		String type = cboLoaiTimKiem.getSelectedItem().toString();

		List<NguoiDung> lstNguoiDung = timKiemNguoiDung(search, type);
		for (NguoiDung nd : lstNguoiDung) {
			dtm.addRow(new Object[] { nd.getId(), nd.getEmail(), nd.getPassword(), nd.getHoTen(), nd.getDiaChi(),
					nd.getDtdt(), vaiTroBLL.hienThiTenVaiTro(nd.getVaiTro()) });
		}
	}
	
}
