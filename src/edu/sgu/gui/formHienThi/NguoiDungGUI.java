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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import edu.sgu.bll.NguoiDungBLL;
import edu.sgu.gui.formThemSua.ThemSuaNguoiDungGUI;

public class NguoiDungGUI extends JPanel {

	private static final long serialVersionUID = -7865163927583297928L;

	private JTable table;
	private JTextField txtDiaChi;
	private JTextField txtHoTen;
	private JTextField txtMaNguoiDung;
	private JTextField txtDienThoai;
	private JTextField txtEmail;
	private JTextField txtVaiTro;
	private JTextField txtLoaiTimKiem;
	private JComboBox<String> cboLoaiTimKiem;
	private JPanel pnlLoaiTimKiem;

	private NguoiDungBLL nguoiDungBLL = new NguoiDungBLL();

	public NguoiDungGUI() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(1566, 1005);
		setLayout(null);
		setVisible(true);

		JLabel lblTitle = new JLabel("NGƯỜI DÙNG");
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
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã người dùng", "Email", "Password", "Họ tên", "Địa chỉ", "Điện thoại", "Vai trò" }));

		//
		nguoiDungBLL.refreshTable(table);

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(5).setPreferredWidth(0);
		table.getColumnModel().getColumn(6).setPreferredWidth(0);

		// ẩn cột password
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);

		// canh giữa cột đầu tiên
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int d = table.getSelectedRow();
					if (d == -1)
						return;
					else {
						txtMaNguoiDung.setText(table.getValueAt(d, 0).toString());
						txtEmail.setText(table.getValueAt(d, 1).toString());
						// txtPassword.setText(table.getValueAt(d, 2).toString());
						txtHoTen.setText(table.getValueAt(d, 3).toString());
						txtDiaChi.setText(table.getValueAt(d, 4).toString());
						txtDienThoai.setText(table.getValueAt(d, 5).toString());
						txtVaiTro.setText(table.getValueAt(d, 6).toString());
					}
				}

			}
		});

		JTableHeader thd = table.getTableHeader();
		thd.setBackground(new Color(255, 204, 102));
		thd.setFont(new Font("Times New Roman", Font.BOLD, 25));
		thd.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);

		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBackground(Color.WHITE);
		pnlThongTin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng tin v\u1EC1 \u0111\u01A1n h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnlThongTin.setBounds(436, 620, 692, 375);
		add(pnlThongTin);
		((javax.swing.border.TitledBorder) pnlThongTin.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.setLayout(null);

		JPanel pnlHoTen = new JPanel();
		pnlHoTen.setBackground(Color.WHITE);
		pnlHoTen.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "H\u1ECD t\u00EAn",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlHoTen.setBounds(33, 121, 300, 65);
		pnlThongTin.add(pnlHoTen);
		((javax.swing.border.TitledBorder) pnlHoTen.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlHoTen.setLayout(null);

		txtHoTen = new JTextField();
		txtHoTen.setBounds(12, 30, 276, 22);
		txtHoTen.setHorizontalAlignment(SwingConstants.CENTER);
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtHoTen.setEditable(false);
		txtHoTen.setBackground(Color.WHITE);
		txtHoTen.setBorder(null);
		txtHoTen.setCaretColor(Color.BLACK);
		pnlHoTen.add(txtHoTen);
		txtHoTen.setColumns(10);

		JPanel pnlMa = new JPanel();
		pnlMa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Mã người dùng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMa.setBackground(Color.WHITE);
		pnlMa.setBounds(32, 33, 301, 65);
		((javax.swing.border.TitledBorder) pnlMa.getBorder()).setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlMa);
		pnlMa.setLayout(null);

		txtMaNguoiDung = new JTextField();
		txtMaNguoiDung.setBounds(23, 30, 254, 22);
		txtMaNguoiDung.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtMaNguoiDung.setEditable(false);
		txtMaNguoiDung.setColumns(10);
		txtMaNguoiDung.setCaretColor(Color.BLACK);
		txtMaNguoiDung.setBorder(null);
		txtMaNguoiDung.setBackground(Color.WHITE);
		pnlMa.add(txtMaNguoiDung);

		JPanel pnlDienThoai = new JPanel();
		pnlDienThoai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Điện thoại di động", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnlDienThoai.setBackground(Color.WHITE);
		pnlDienThoai.setBounds(362, 33, 300, 65);
		((javax.swing.border.TitledBorder) pnlDienThoai.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlDienThoai);
		pnlDienThoai.setLayout(null);

		txtDienThoai = new JTextField();
		txtDienThoai.setBounds(23, 30, 254, 22);
		txtDienThoai.setHorizontalAlignment(SwingConstants.CENTER);
		txtDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtDienThoai.setEditable(false);
		txtDienThoai.setColumns(10);
		txtDienThoai.setCaretColor(Color.BLACK);
		txtDienThoai.setBorder(null);
		txtDienThoai.setBackground(Color.WHITE);
		pnlDienThoai.add(txtDienThoai);

		JPanel pnlEmail = new JPanel();
		pnlEmail.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Email", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlEmail.setBackground(Color.WHITE);
		pnlEmail.setBounds(33, 209, 629, 65);
		pnlThongTin.add(pnlEmail);
		((javax.swing.border.TitledBorder) pnlEmail.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlEmail.setLayout(null);

		txtEmail = new JTextField();
		txtEmail.setBounds(12, 30, 605, 22);
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setCaretColor(Color.BLACK);
		txtEmail.setBorder(null);
		txtEmail.setBackground(Color.WHITE);
		pnlEmail.add(txtEmail);

		JPanel pnlDiaChi = new JPanel();
		pnlDiaChi.setBounds(33, 297, 629, 65);
		pnlThongTin.add(pnlDiaChi);
		pnlDiaChi.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Địa chỉ người nhận", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnlDiaChi.setBackground(Color.WHITE);
		((javax.swing.border.TitledBorder) pnlDiaChi.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlDiaChi.setLayout(null);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(12, 30, 605, 22);
		txtDiaChi.setHorizontalAlignment(SwingConstants.CENTER);
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setCaretColor(Color.BLACK);
		txtDiaChi.setBorder(null);
		txtDiaChi.setBackground(Color.WHITE);
		pnlDiaChi.add(txtDiaChi);

		JPanel pnlVaiTro = new JPanel();
		pnlVaiTro.setBounds(362, 121, 300, 65);
		pnlThongTin.add(pnlVaiTro);
		pnlVaiTro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vai trò",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		((javax.swing.border.TitledBorder) pnlVaiTro.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlVaiTro.setBackground(Color.WHITE);
		pnlVaiTro.setLayout(null);

		txtVaiTro = new JTextField();
		txtVaiTro.setBounds(23, 30, 253, 22);
		txtVaiTro.setHorizontalAlignment(SwingConstants.CENTER);
		txtVaiTro.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtVaiTro.setEditable(false);
		txtVaiTro.setColumns(10);
		txtVaiTro.setCaretColor(Color.BLACK);
		txtVaiTro.setBorder(null);
		txtVaiTro.setBackground(Color.WHITE);
		pnlVaiTro.add(txtVaiTro);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		cboLoaiTimKiem.setModel(new DefaultComboBoxModel(
				new String[] { "Tất cả", "Mã người dùng", "Email", "Họ tên", "Địa chỉ", "Điện thoại", "Vai trò" }));
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
				nguoiDungBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				nguoiDungBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				nguoiDungBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
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
				nguoiDungBLL.refreshTable(table);
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
				ThemSuaNguoiDungGUI themSuaNguoiDungGUI = new ThemSuaNguoiDungGUI("Thêm", table, 0);
				themSuaNguoiDungGUI.setVisible(true);
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

					if (JOptionPane.showConfirmDialog(NguoiDungGUI.this,
							"Bạn muốn xoá người dùng "
									+ table.getValueAt(table.getSelectedRow(), 1).toString().toUpperCase() + " không?",
							"Xác nhận", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
						if (nguoiDungBLL.xoaNguoiDung(id) == 1) {
							JOptionPane.showMessageDialog(NguoiDungGUI.this, "Xoá thành công", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
							nguoiDungBLL.refreshTable(table);
							txtMaNguoiDung.setText("");
							txtEmail.setText("");
							txtHoTen.setText("");
							// txtPassword.setText("");
							txtDiaChi.setText("");
							txtDienThoai.setText("");
							txtVaiTro.setText("");
						} else {
							JOptionPane.showMessageDialog(NguoiDungGUI.this, "Xoá thất bại", "Lỗi",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(NguoiDungGUI.this, "Vui lòng chọn người dùng để xoá");

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
					ThemSuaNguoiDungGUI themSuaNguoiDungGUI = new ThemSuaNguoiDungGUI("Sửa", table, id);
					themSuaNguoiDungGUI.setVisible(true);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(NguoiDungGUI.this, "Vui lòng chọn người dùng để cập nhật");
				}
			}
		});
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconEdit = new ImageIcon(ThuongHieuGUI.class.getResource("/images/edit.png"));
		iconEdit.setImage(iconEdit.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnSua.setIcon(iconEdit);
		btnSua.setBounds(1058, 95, 137, 43);
		add(btnSua);

		JButton btnXuat = new JButton("Xuất DS");
		btnXuat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconExport = new ImageIcon(ThuongHieuGUI.class.getResource("/images/excel.png"));
		iconExport.setImage(iconExport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnXuat.setIcon(iconExport);
		btnXuat.setBounds(1396, 95, 137, 43);
		add(btnXuat);

		JButton btnNhap = new JButton("Nhập DS");
		btnNhap.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconImport = new ImageIcon(ThuongHieuGUI.class.getResource("/images/excel.png"));
		iconImport.setImage(iconImport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnNhap.setIcon(iconImport);
		btnNhap.setBounds(1227, 95, 137, 43);
		add(btnNhap);

		nguoiDungBLL.sapXep(table);
	}
}

