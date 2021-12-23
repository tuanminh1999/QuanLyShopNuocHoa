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
import javax.swing.JTextArea;
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

import edu.sgu.bll.DonHangBLL;
import edu.sgu.gui.formChiTiet.ChiTietDonHangGUI;
import edu.sgu.utils.Excel;

public class DonHangGUI extends JPanel {

	private static final long serialVersionUID = -5800489994498327875L;

	private JTable table;
	private JTextField txtDiaChi;
	private JTextField txtNguoiNhan;
	private JTextField txtDienThoai;
	private JTextArea txtGhiChu;
	private JTextField txtMaDonHang;
	private JTextField txtMaKhachHang;
	private JTextField txtNgayDatHang;
	private JTextField txtThanhToan;
	private JTextField txtTrangThai;
	private JTextField txtLoaiTimKiem;
	private JComboBox<String> cboLoaiTimKiem;
	private JPanel pnlLoaiTimKiem;
	private JTextField txtThanhTien;

	private DonHangBLL donHangBLL = new DonHangBLL();

	public DonHangGUI() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(1566, 1005);
		setLayout(null);
		setVisible(true);

		JLabel lblTitle = new JLabel("ĐƠN HÀNG");
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
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã HĐ", "Mã KH", "Ngày đặt",
				"Tên người nhận", "Điện thoại", "Địa chỉ", "Ghi chú", "Thanh toán", "Trạng thái", "Tổng tiền" }));

		donHangBLL.refreshTable(table);

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(0);
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(0);
		table.getColumnModel().getColumn(7).setPreferredWidth(0);
		table.getColumnModel().getColumn(8).setPreferredWidth(0);
		table.getColumnModel().getColumn(9).setPreferredWidth(0);

		// canh giữa cột đầu tiên và cuối cùng
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(9).setCellRenderer(cellRenderer);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int d = table.getSelectedRow();
					txtMaDonHang.setText(table.getValueAt(d, 0).toString());
					txtMaKhachHang.setText(table.getValueAt(d, 1).toString());
					txtNgayDatHang.setText(table.getValueAt(d, 2).toString());
					txtNguoiNhan.setText(table.getValueAt(d, 3).toString());
					txtDienThoai.setText(table.getValueAt(d, 4).toString());
					txtDiaChi.setText(table.getValueAt(d, 5).toString());
					txtGhiChu.setText(table.getValueAt(d, 6).toString());
					txtThanhToan.setText(table.getValueAt(d, 7).toString());
					txtTrangThai.setText(table.getValueAt(d, 8).toString());
					txtThanhTien.setText(table.getValueAt(d, 9).toString());
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
		pnlThongTin.setBounds(132, 620, 1181, 354);
		add(pnlThongTin);
		((javax.swing.border.TitledBorder) pnlThongTin.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.setLayout(null);

		JPanel pnlTenNguoiNhan = new JPanel();
		pnlTenNguoiNhan.setBackground(Color.WHITE);
		pnlTenNguoiNhan.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "T\u00EAn ng\u01B0\u1EDDi nh\u1EADn",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTenNguoiNhan.setBounds(33, 118, 454, 65);
		pnlThongTin.add(pnlTenNguoiNhan);
		((javax.swing.border.TitledBorder) pnlTenNguoiNhan.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlTenNguoiNhan.setLayout(null);

		txtNguoiNhan = new JTextField();
		txtNguoiNhan.setBounds(12, 30, 430, 22);
		txtNguoiNhan.setHorizontalAlignment(SwingConstants.CENTER);
		txtNguoiNhan.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtNguoiNhan.setEditable(false);
		txtNguoiNhan.setBackground(Color.WHITE);
		txtNguoiNhan.setBorder(null);
		txtNguoiNhan.setCaretColor(Color.BLACK);
		pnlTenNguoiNhan.add(txtNguoiNhan);
		txtNguoiNhan.setColumns(10);

		JPanel pnlDiaChi = new JPanel();
		pnlDiaChi.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Địa chỉ người nhận",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDiaChi.setBackground(Color.WHITE);
		pnlDiaChi.setBounds(33, 195, 454, 65);
		pnlThongTin.add(pnlDiaChi);
		((javax.swing.border.TitledBorder) pnlDiaChi.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlDiaChi.setLayout(null);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(12, 30, 430, 22);
		txtDiaChi.setHorizontalAlignment(SwingConstants.CENTER);
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setCaretColor(Color.BLACK);
		txtDiaChi.setBorder(null);
		txtDiaChi.setBackground(Color.WHITE);
		pnlDiaChi.add(txtDiaChi);

		JPanel pnlDienThoai = new JPanel();
		pnlDienThoai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Điện thoại người nhận",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDienThoai.setBackground(Color.WHITE);
		pnlDienThoai.setBounds(276, 272, 211, 65);
		((javax.swing.border.TitledBorder) pnlDienThoai.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlDienThoai);
		pnlDienThoai.setLayout(null);

		txtDienThoai = new JTextField();
		txtDienThoai.setBounds(35, 30, 140, 22);
		txtDienThoai.setHorizontalAlignment(SwingConstants.CENTER);
		txtDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtDienThoai.setEditable(false);
		txtDienThoai.setColumns(10);
		txtDienThoai.setCaretColor(Color.BLACK);
		txtDienThoai.setBorder(null);
		txtDienThoai.setBackground(Color.WHITE);
		pnlDienThoai.add(txtDienThoai);

		JPanel pnlGhiChu = new JPanel();
		pnlGhiChu.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ghi chú",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlGhiChu.setBackground(Color.WHITE);
		pnlGhiChu.setBounds(499, 40, 670, 220);
		pnlThongTin.add(pnlGhiChu);
		((javax.swing.border.TitledBorder) pnlGhiChu.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlGhiChu.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 28, 646, 179);
		pnlGhiChu.add(scrollPane_1);

		txtGhiChu = new JTextArea();
		txtGhiChu.setEditable(false);
		txtGhiChu.setWrapStyleWord(true);
		txtGhiChu.setLineWrap(true);
		txtGhiChu.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		scrollPane_1.setViewportView(txtGhiChu);

		JPanel pnlMaDonHang = new JPanel();
		pnlMaDonHang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mã đơn hàng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMaDonHang.setBackground(Color.WHITE);
		pnlMaDonHang.setBounds(33, 41, 211, 65);
		((javax.swing.border.TitledBorder) pnlMaDonHang.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlMaDonHang);
		pnlMaDonHang.setLayout(null);

		txtMaDonHang = new JTextField();
		txtMaDonHang.setBounds(35, 30, 140, 22);
		txtMaDonHang.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaDonHang.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtMaDonHang.setEditable(false);
		txtMaDonHang.setColumns(10);
		txtMaDonHang.setCaretColor(Color.BLACK);
		txtMaDonHang.setBorder(null);
		txtMaDonHang.setBackground(Color.WHITE);
		pnlMaDonHang.add(txtMaDonHang);

		JPanel pnlMaKhachHang = new JPanel();
		pnlMaKhachHang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mã khách hàng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMaKhachHang.setBackground(Color.WHITE);
		pnlMaKhachHang.setBounds(276, 41, 211, 65);
		((javax.swing.border.TitledBorder) pnlMaKhachHang.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlMaKhachHang);
		pnlMaKhachHang.setLayout(null);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setBounds(35, 30, 140, 22);
		txtMaKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setCaretColor(Color.BLACK);
		txtMaKhachHang.setBorder(null);
		txtMaKhachHang.setBackground(Color.WHITE);
		pnlMaKhachHang.add(txtMaKhachHang);

		JPanel pnlNgayDatHang = new JPanel();
		pnlNgayDatHang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ngày đặt hàng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlNgayDatHang.setBackground(Color.WHITE);
		pnlNgayDatHang.setBounds(33, 272, 211, 65);
		((javax.swing.border.TitledBorder) pnlNgayDatHang.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlNgayDatHang);
		pnlNgayDatHang.setLayout(null);

		txtNgayDatHang = new JTextField();
		txtNgayDatHang.setBounds(35, 30, 140, 22);
		txtNgayDatHang.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgayDatHang.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtNgayDatHang.setEditable(false);
		txtNgayDatHang.setColumns(10);
		txtNgayDatHang.setCaretColor(Color.BLACK);
		txtNgayDatHang.setBorder(null);
		txtNgayDatHang.setBackground(Color.WHITE);
		pnlNgayDatHang.add(txtNgayDatHang);

		JPanel pnlThanhToan = new JPanel();
		pnlThanhToan.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thanh toán",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThanhToan.setBackground(Color.WHITE);
		pnlThanhToan.setBounds(499, 272, 211, 65);
		((javax.swing.border.TitledBorder) pnlThanhToan.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlThanhToan);
		pnlThanhToan.setLayout(null);

		txtThanhToan = new JTextField();
		txtThanhToan.setBounds(35, 30, 140, 22);
		txtThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		txtThanhToan.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtThanhToan.setEditable(false);
		txtThanhToan.setColumns(10);
		txtThanhToan.setCaretColor(Color.BLACK);
		txtThanhToan.setBorder(null);
		txtThanhToan.setBackground(Color.WHITE);
		pnlThanhToan.add(txtThanhToan);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tr\u1EA1ng th\u00E1i",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTrangThai.setBackground(Color.WHITE);
		pnlTrangThai.setBounds(730, 272, 211, 65);
		((javax.swing.border.TitledBorder) pnlTrangThai.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlTrangThai);
		pnlTrangThai.setLayout(null);

		txtTrangThai = new JTextField();
		txtTrangThai.setBounds(34, 30, 140, 22);
		txtTrangThai.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtTrangThai.setEditable(false);
		txtTrangThai.setColumns(10);
		txtTrangThai.setCaretColor(Color.BLACK);
		txtTrangThai.setBorder(null);
		txtTrangThai.setBackground(Color.WHITE);
		pnlTrangThai.add(txtTrangThai);

		JPanel pnlThanhTien = new JPanel();
		pnlThanhTien.setLayout(null);
		pnlThanhTien.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tổng tiền",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThanhTien.setBackground(Color.WHITE);
		pnlThanhTien.setBounds(958, 272, 211, 65);
		((javax.swing.border.TitledBorder) pnlThanhTien.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlThanhTien);

		txtThanhTien = new JTextField();
		txtThanhTien.setForeground(Color.RED);
		txtThanhTien.setHorizontalAlignment(SwingConstants.CENTER);
		txtThanhTien.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		txtThanhTien.setEditable(false);
		txtThanhTien.setColumns(10);
		txtThanhTien.setCaretColor(Color.BLACK);
		txtThanhTien.setBorder(null);
		txtThanhTien.setBackground(Color.WHITE);
		txtThanhTien.setBounds(12, 30, 187, 22);
		pnlThanhTien.add(txtThanhTien);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiem.setBackground(Color.WHITE);
		pnlTimKiem.setBounds(52, 70, 487, 87);
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
				new String[] { "Tất cả", "Mã hoá đơn", "Mã khách hàng", "Ngày đặt", "Tên người nhận", "Điện thoại",
						"Địa chỉ", "Ghi chú", "Thanh toán", "Trạng thái", "Tổng tiền" }));
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
				donHangBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				donHangBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				donHangBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
			}
		});

		txtLoaiTimKiem.setBorder(null);
		txtLoaiTimKiem.setBounds(12, 26, 267, 24);
		txtLoaiTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtLoaiTimKiem.setColumns(10);
		pnlLoaiTimKiem.add(txtLoaiTimKiem);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconRefresh = new ImageIcon(VaiTroGUI.class.getResource("/images/refresh.png"));
		iconRefresh.setImage(iconRefresh.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnLamMoi.setIcon(iconRefresh);
		btnLamMoi.setBounds(591, 95, 137, 43);
		add(btnLamMoi);

		JButton btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					ChiTietDonHangGUI chiTietDonHangGUI = new ChiTietDonHangGUI(row);
					chiTietDonHangGUI.setVisible(true);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(DonHangGUI.this, "Vui lòng chọn hoá đơn để xem");
				}

			}
		});
		btnXemChiTiet.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon icon_view = new ImageIcon(ThuongHieuGUI.class.getResource("/images/show-property.png"));
		icon_view.setImage(icon_view.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnXemChiTiet.setIcon(icon_view);
		btnXemChiTiet.setBounds(1158, 95, 160, 43);
		add(btnXemChiTiet);

		JButton btnNhapDS = new JButton("Nhập DS");
		btnNhapDS.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconExport = new ImageIcon(ThuongHieuGUI.class.getResource("/images/excel.png"));
		iconExport.setImage(iconExport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnNhapDS.setIcon(iconExport);
		btnNhapDS.setBounds(780, 95, 137, 43);
		add(btnNhapDS);

		JButton btnXuatDS = new JButton("Xuất DS");
		btnXuatDS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excel.xuatFileExcelHoaDon();
			}
		});
		btnXuatDS.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconImport = new ImageIcon(ThuongHieuGUI.class.getResource("/images/excel.png"));
		iconImport.setImage(iconImport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnXuatDS.setIcon(iconImport);
		btnXuatDS.setBounds(969, 95, 137, 43);
		add(btnXuatDS);

		JButton btnInPDF = new JButton("In đơn");
		btnInPDF.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconPrint = new ImageIcon(ThuongHieuGUI.class.getResource("/images/pdf.png"));
		iconPrint.setImage(iconPrint.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnInPDF.setIcon(iconPrint);
		btnInPDF.setBounds(1355, 931, 137, 43);
		add(btnInPDF);

		donHangBLL.sapXep(table);
	}
}
