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

import edu.sgu.bll.SanPhamBLL;
import edu.sgu.gui.formThemSua.ThemSuaSanPhamGUI;
import edu.sgu.utils.Excel;

public class SanPhamGUI extends JPanel {

	private static final long serialVersionUID = -5220809939731425412L;

	private JTable table;
	private JTextField txtTen;
	private JTextField txtDonGia;
	private JTextField txtDonGiaKM;
	private JTextField txtNgayTao;
	private JTextField txtHienThi;
	private JTextField txtLoai;
	private JTextField txtSoLuong;
	private JTextField txtThuongHieu;
	private JTextField txtMa;
	private JTextArea txtMoTa;
	private JLabel lblImage;

	private JTextField txtLoaiTimKiem;
	private JComboBox<String> cboLoaiTimKiem;
	private JPanel pnlLoaiTimKiem;

	private SanPhamBLL sanPhamBLL = new SanPhamBLL();

	public SanPhamGUI() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(1566, 1005);
		setLayout(null);
		setVisible(true);

		JLabel lblTitle = new JLabel("SẢN PHẨM");
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
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã SP", "Tên sản phẩm", "Mô tả",
				"Hình ảnh", "Đơn giá", "Đơn giá KM", "SL", "Ngày tạo", "Hiển thị", "Loại", "Thương hiệu" }));

		// đổ dữ liệu vào bảng
		sanPhamBLL.refreshTable(table);

		// độ dài các cột
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(0);
		table.getColumnModel().getColumn(6).setPreferredWidth(0);
		table.getColumnModel().getColumn(7).setPreferredWidth(0);
		table.getColumnModel().getColumn(8).setPreferredWidth(0);
		table.getColumnModel().getColumn(9).setPreferredWidth(0);

		// canh giữa cột
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(8).setCellRenderer(cellRenderer);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int d = table.getSelectedRow();
					if (d == -1) {
						return;
					}
					txtMa.setText(table.getValueAt(d, 0).toString());
					txtTen.setText(table.getValueAt(d, 1).toString());
					txtMoTa.setText(table.getValueAt(d, 2).toString());
					ImageIcon icon = new ImageIcon("src/images/sanpham/" + table.getValueAt(d, 3).toString());
					icon.setImage(icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(),
							Image.SCALE_SMOOTH));
					lblImage.setIcon(icon);
					txtDonGia.setText(table.getValueAt(d, 4).toString());
					txtDonGiaKM.setText(table.getValueAt(d, 5).toString());
					txtSoLuong.setText(table.getValueAt(d, 6).toString());
					txtNgayTao.setText(table.getValueAt(d, 7).toString());
					txtHienThi.setText(table.getValueAt(d, 8).toString());
					txtLoai.setText(table.getValueAt(d, 9).toString());
					txtThuongHieu.setText(table.getValueAt(d, 10).toString());
				}

			}
		});

		JTableHeader thd = table.getTableHeader();
		thd.setBackground(new Color(255, 204, 102));
		thd.setFont(new Font("Times New Roman", Font.BOLD, 25));
		thd.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);

		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setLayout(null);
		pnlThongTin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin về sản phẩm",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongTin.setBackground(Color.WHITE);
		pnlThongTin.setBounds(144, 620, 1276, 375);
		((javax.swing.border.TitledBorder) pnlThongTin.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		add(pnlThongTin);

		lblImage = new JLabel("");
		lblImage.setBounds(12, 62, 287, 287);
		pnlThongTin.add(lblImage);

		JPanel pnlTen = new JPanel();
		pnlTen.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tên sản phẩm",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTen.setBackground(Color.WHITE);
		pnlTen.setBounds(311, 85, 526, 65);
		pnlThongTin.add(pnlTen);
		((javax.swing.border.TitledBorder) pnlTen.getBorder()).setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlTen.setLayout(null);

		txtTen = new JTextField();
		txtTen.setHorizontalAlignment(SwingConstants.CENTER);
		txtTen.setBounds(12, 31, 502, 21);
		txtTen.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setCaretColor(Color.BLACK);
		txtTen.setBorder(null);
		txtTen.setBackground(Color.WHITE);
		pnlTen.add(txtTen);

		JPanel pnlDonGia = new JPanel();
		pnlDonGia.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Đơn giá",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDonGia.setBackground(Color.WHITE);
		pnlDonGia.setBounds(311, 157, 250, 65);
		((javax.swing.border.TitledBorder) pnlDonGia.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(55, 30, 140, 22);
		txtDonGia.setHorizontalAlignment(SwingConstants.CENTER);
		txtDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtDonGia.setEditable(false);
		txtDonGia.setColumns(10);
		txtDonGia.setCaretColor(Color.BLACK);
		txtDonGia.setBorder(null);
		txtDonGia.setBackground(Color.WHITE);
		((javax.swing.border.TitledBorder) pnlThongTin.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlDonGia.setLayout(null);
		pnlDonGia.add(txtDonGia);

		JPanel pnlDonGiaKM = new JPanel();
		pnlDonGiaKM.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Đơn giá khuyến mãi",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDonGiaKM.setBackground(Color.WHITE);
		pnlDonGiaKM.setBounds(587, 158, 250, 65);
		((javax.swing.border.TitledBorder) pnlDonGiaKM.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlDonGiaKM);
		pnlDonGiaKM.setLayout(null);

		txtDonGiaKM = new JTextField();
		txtDonGiaKM.setBounds(56, 30, 140, 22);
		txtDonGiaKM.setHorizontalAlignment(SwingConstants.CENTER);
		txtDonGiaKM.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtDonGiaKM.setEditable(false);
		txtDonGiaKM.setColumns(10);
		txtDonGiaKM.setCaretColor(Color.BLACK);
		txtDonGiaKM.setBorder(null);
		txtDonGiaKM.setBackground(Color.WHITE);
		pnlDonGiaKM.add(txtDonGiaKM);

		JPanel pnlMoTa = new JPanel();
		pnlMoTa.setLayout(null);
		pnlMoTa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mô tả", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMoTa.setBackground(Color.WHITE);
		pnlMoTa.setBounds(860, 13, 404, 350);
		((javax.swing.border.TitledBorder) pnlMoTa.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlMoTa);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 29, 380, 308);
		pnlMoTa.add(scrollPane_1);

		txtMoTa = new JTextArea();
		txtMoTa.setEditable(false);
		txtMoTa.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtMoTa.setWrapStyleWord(true);
		txtMoTa.setLineWrap(true);
		scrollPane_1.setViewportView(txtMoTa);

		JPanel pnlNgayTao = new JPanel();
		pnlNgayTao.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ngày tạo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlNgayTao.setBackground(Color.WHITE);
		pnlNgayTao.setBounds(311, 229, 250, 65);
		((javax.swing.border.TitledBorder) pnlNgayTao.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlNgayTao);
		pnlNgayTao.setLayout(null);

		txtNgayTao = new JTextField();
		txtNgayTao.setBounds(55, 30, 140, 22);
		txtNgayTao.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgayTao.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtNgayTao.setEditable(false);
		txtNgayTao.setColumns(10);
		txtNgayTao.setCaretColor(Color.BLACK);
		txtNgayTao.setBorder(null);
		txtNgayTao.setBackground(Color.WHITE);
		pnlNgayTao.add(txtNgayTao);

		JPanel pnlHienThi = new JPanel();
		pnlHienThi.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Hiển thị",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlHienThi.setBackground(Color.WHITE);
		pnlHienThi.setBounds(587, 230, 250, 65);
		((javax.swing.border.TitledBorder) pnlHienThi.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlHienThi);
		pnlHienThi.setLayout(null);

		txtHienThi = new JTextField();
		txtHienThi.setBounds(56, 30, 140, 22);
		txtHienThi.setHorizontalAlignment(SwingConstants.CENTER);
		txtHienThi.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtHienThi.setEditable(false);
		txtHienThi.setColumns(10);
		txtHienThi.setCaretColor(Color.BLACK);
		txtHienThi.setBorder(null);
		txtHienThi.setBackground(Color.WHITE);
		pnlHienThi.add(txtHienThi);

		JPanel pnlLoai = new JPanel();
		pnlLoai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Loại", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlLoai.setBackground(Color.WHITE);
		pnlLoai.setBounds(311, 301, 252, 65);
		((javax.swing.border.TitledBorder) pnlLoai.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlLoai);
		pnlLoai.setLayout(null);

		txtLoai = new JTextField();
		txtLoai.setBounds(57, 30, 140, 22);
		txtLoai.setHorizontalAlignment(SwingConstants.CENTER);
		txtLoai.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtLoai.setEditable(false);
		txtLoai.setColumns(10);
		txtLoai.setCaretColor(Color.BLACK);
		txtLoai.setBorder(null);
		txtLoai.setBackground(Color.WHITE);
		pnlLoai.add(txtLoai);

		JPanel pnlSoLuong = new JPanel();
		pnlSoLuong.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Số lượng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSoLuong.setBackground(Color.WHITE);
		pnlSoLuong.setBounds(587, 13, 250, 65);
		((javax.swing.border.TitledBorder) pnlSoLuong.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlSoLuong);
		pnlSoLuong.setLayout(null);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(55, 30, 140, 22);
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtSoLuong.setEditable(false);
		txtSoLuong.setColumns(10);
		txtSoLuong.setCaretColor(Color.BLACK);
		txtSoLuong.setBorder(null);
		txtSoLuong.setBackground(Color.WHITE);
		pnlSoLuong.add(txtSoLuong);

		JPanel pnlThuongHieu = new JPanel();
		pnlThuongHieu.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thương hiệu",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThuongHieu.setBackground(Color.WHITE);
		pnlThuongHieu.setBounds(587, 302, 250, 65);
		((javax.swing.border.TitledBorder) pnlThuongHieu.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlThuongHieu);
		pnlThuongHieu.setLayout(null);

		txtThuongHieu = new JTextField();
		txtThuongHieu.setBounds(55, 30, 140, 22);
		txtThuongHieu.setHorizontalAlignment(SwingConstants.CENTER);
		txtThuongHieu.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtThuongHieu.setEditable(false);
		txtThuongHieu.setColumns(10);
		txtThuongHieu.setCaretColor(Color.BLACK);
		txtThuongHieu.setBorder(null);
		txtThuongHieu.setBackground(Color.WHITE);
		pnlThuongHieu.add(txtThuongHieu);

		JPanel pnlMa = new JPanel();
		pnlMa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mã sản phẩm",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMa.setBackground(Color.WHITE);
		pnlMa.setBounds(311, 13, 250, 65);
		((javax.swing.border.TitledBorder) pnlMa.getBorder()).setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlMa);
		pnlMa.setLayout(null);

		txtMa = new JTextField();
		txtMa.setBounds(55, 30, 140, 22);
		txtMa.setHorizontalAlignment(SwingConstants.CENTER);
		txtMa.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtMa.setEditable(false);
		txtMa.setColumns(10);
		txtMa.setCaretColor(Color.BLACK);
		txtMa.setBorder(null);
		txtMa.setBackground(Color.WHITE);
		pnlMa.add(txtMa);

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
				new String[] { "Tất cả", "Mã sản phẩm", "Tên sản phẩm", "Mô tả", "Hình ảnh", "Đơn giá",
						"Đơn giá khuyến mãi", "Số lượng", "Ngày tạo", "Hiển thị", "Loại sản phẩm", "Thương hiệu" }));
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
				sanPhamBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				sanPhamBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				sanPhamBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
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
				sanPhamBLL.refreshTable(table);
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
				ThemSuaSanPhamGUI them = new ThemSuaSanPhamGUI("Thêm", table, 0);
				them.setVisible(true);
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
					if (JOptionPane.showConfirmDialog(SanPhamGUI.this,
							"Bạn muốn xoá sản phẩm "
									+ table.getValueAt(table.getSelectedRow(), 1).toString().toUpperCase() + " không?",
							"Xác nhận", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
						if (sanPhamBLL.xoaSanPham(id) == 1) {
							JOptionPane.showMessageDialog(SanPhamGUI.this, "Xoá thành công", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
							sanPhamBLL.refreshTable(table);

							lblImage.setIcon(null);
							txtTen.setText("");
							txtMa.setText("");
							txtSoLuong.setText("");
							txtDonGia.setText("");
							txtDonGiaKM.setText("");
							txtNgayTao.setText("");
							txtHienThi.setText("");
							txtLoai.setText("");
							txtThuongHieu.setText("");
							txtMoTa.setText("");
						} else {
							JOptionPane.showMessageDialog(SanPhamGUI.this, "Xoá thất bại", "Lỗi",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(SanPhamGUI.this, "Vui lòng chọn sản phẩm để xoá");

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
					ThemSuaSanPhamGUI themSuaThuongHieuGUI = new ThemSuaSanPhamGUI("Sửa", table, id);
					themSuaThuongHieuGUI.setVisible(true);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(SanPhamGUI.this, "Vui lòng chọn sản phẩm để cập nhật");
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
				Excel.xuatFileExcelSanPham();
			}
		});
		btnXuat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconExport = new ImageIcon(ThuongHieuGUI.class.getResource("/images/excel.png"));
		iconExport.setImage(iconExport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnXuat.setIcon(iconExport);
		btnXuat.setBounds(1396, 95, 137, 43);
		add(btnXuat);

		sanPhamBLL.sapXep(table);
	}
}
