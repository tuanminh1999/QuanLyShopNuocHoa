package edu.sgu.gui.formHienThi;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import edu.sgu.bll.VaiTroBLL;
import edu.sgu.gui.formThemSua.ThemSuaVaiTroGUI;
import edu.sgu.utils.Excel;

public class VaiTroGUI extends JPanel {

	private static final long serialVersionUID = -5105279656426480317L;
	
	private JTable table;
	private JTextField txtLoaiTimKiem;
	private JComboBox<String> cboLoaiTimKiem;
	private JPanel pnlLoaiTimKiem;

	private VaiTroBLL vaiTroBLL = new VaiTroBLL();

	public VaiTroGUI() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(1566, 1005);
		setLayout(null);
		setVisible(true);

		JLabel lblTitle = new JLabel("VAI TRÒ");
		lblTitle.setForeground(new Color(255, 0, 102));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblTitle.setBounds(254, 0, 1057, 59);
		add(lblTitle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane.setBounds(12, 180, 1541, 392);
		scrollPane.getViewport().setBackground(Color.WHITE);
		add(scrollPane);

		// JTable Alternate Row Background
		table = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component returnComp = super.prepareRenderer(renderer, row, column);
				Color alternateColor = new Color(252, 242, 206);
				Color whiteColor = Color.WHITE;
				if (!returnComp.getBackground().equals(getSelectionBackground())) {
					Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
					returnComp.setBackground(bg);
					bg = null;
				}
				return returnComp;
			}
		};
		table.setSelectionBackground(new Color(255, 153, 51));
		table.setRowHeight(40);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã vai trò", "Tên vai trò", "Mô tả" }));

		//
		vaiTroBLL.refreshTable(table);

		// canh giữa cột đầu tiên
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

		JTableHeader thd = table.getTableHeader();
		thd.setBackground(new Color(255, 204, 102));
		thd.setFont(new Font("Times New Roman", Font.BOLD, 25));
		thd.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(
				new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiem.setBackground(Color.WHITE);
		pnlTimKiem.setBounds(32, 70, 487, 87);
		add(pnlTimKiem);
		((javax.swing.border.TitledBorder) pnlTimKiem.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlTimKiem.setLayout(null);

		cboLoaiTimKiem = new JComboBox<>();
		cboLoaiTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlLoaiTimKiem.setBorder(new TitledBorder(null, cboLoaiTimKiem.getSelectedItem().toString(),
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				((javax.swing.border.TitledBorder) pnlLoaiTimKiem.getBorder())
						.setTitleFont(new Font("Times New Roman", Font.BOLD, 18));

			}
		});
		cboLoaiTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		cboLoaiTimKiem
				.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Mã vai trò", "Tên vai trò", "Mô tả" }));
		cboLoaiTimKiem.setBackground(Color.WHITE);
		cboLoaiTimKiem.setBounds(12, 30, 164, 30);
		pnlTimKiem.add(cboLoaiTimKiem);

		pnlLoaiTimKiem = new JPanel();
		pnlLoaiTimKiem.setBackground(Color.WHITE);
		pnlLoaiTimKiem.setBorder(new TitledBorder(null, "Tất cả", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLoaiTimKiem.setBounds(184, 13, 291, 61);
		((javax.swing.border.TitledBorder) pnlLoaiTimKiem.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 18));
		pnlTimKiem.add(pnlLoaiTimKiem);
		pnlLoaiTimKiem.setLayout(null);

		txtLoaiTimKiem = new JTextField();
		txtLoaiTimKiem.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				vaiTroBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				vaiTroBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				vaiTroBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);

			}
		});

		txtLoaiTimKiem.setBorder(null);
		txtLoaiTimKiem.setBounds(12, 26, 267, 24);
		txtLoaiTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtLoaiTimKiem.setColumns(10);
		pnlLoaiTimKiem.add(txtLoaiTimKiem);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaiTroBLL.refreshTable(table);
			}
		});
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconRefresh = new ImageIcon(VaiTroGUI.class.getResource("/images/refresh.png"));
		iconRefresh.setImage(iconRefresh.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnLamMoi.setIcon(iconRefresh);
		btnLamMoi.setBounds(551, 95, 137, 43);
		add(btnLamMoi);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemSuaVaiTroGUI themSuaVaiTroGUI = new ThemSuaVaiTroGUI("Thêm", table, 0);
				themSuaVaiTroGUI.setVisible(true);
			}
		});
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconAdd = new ImageIcon(ThuongHieuGUI.class.getResource("/images/plus.png"));
		iconAdd.setImage(iconAdd.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnThem.setIcon(iconAdd);
		btnThem.setBounds(720, 95, 137, 43);
		add(btnThem);

		JButton btnXoa = new JButton("Xoá");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

					if (JOptionPane.showConfirmDialog(VaiTroGUI.this,
							"Bạn muốn xoá vai trò "
									+ table.getValueAt(table.getSelectedRow(), 1).toString().toUpperCase() + " không?",
							"Xác nhận", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
						if (vaiTroBLL.xoaVaiTro(id) == 1) {
							JOptionPane.showMessageDialog(VaiTroGUI.this, "Xoá thành công", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
							vaiTroBLL.refreshTable(table);
						} else {
							JOptionPane.showMessageDialog(VaiTroGUI.this, "Xoá thất bại", "Lỗi",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(VaiTroGUI.this, "Vui lòng chọn vai trò để xoá");

				}
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconRemove = new ImageIcon(ThuongHieuGUI.class.getResource("/images/remove.png"));
		iconRemove.setImage(iconRemove.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnXoa.setIcon(iconRemove);
		btnXoa.setBounds(889, 95, 137, 43);
		add(btnXoa);

		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					ThemSuaVaiTroGUI themSuaVaiTroGUI = new ThemSuaVaiTroGUI("Sửa", table, id);
					themSuaVaiTroGUI.setVisible(true);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(VaiTroGUI.this, "Vui lòng chọn vai trò để cập nhật");
				}
			}
		});
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconEdit = new ImageIcon(ThuongHieuGUI.class.getResource("/images/edit.png"));
		iconEdit.setImage(iconEdit.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnSua.setIcon(iconEdit);
		btnSua.setBounds(1058, 95, 137, 43);
		add(btnSua);

		JButton btnNhap = new JButton("Nhập DS");
		btnNhap.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconImport = new ImageIcon(ThuongHieuGUI.class.getResource("/images/excel.png"));
		iconImport.setImage(iconImport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnNhap.setIcon(iconImport);
		btnNhap.setBounds(1227, 95, 137, 43);
		add(btnNhap);

		JButton btnXuat = new JButton("Xuất DS");
		btnXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excel.xuatFileExcelVaiTro();
			}
		});
		btnXuat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconExport = new ImageIcon(ThuongHieuGUI.class.getResource("/images/excel.png"));
		iconExport.setImage(iconExport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnXuat.setIcon(iconExport);
		btnXuat.setBounds(1396, 95, 137, 43);
		add(btnXuat);

		vaiTroBLL.sapXep(table);
	}

}
