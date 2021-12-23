package edu.sgu.gui.formChiTiet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

import edu.sgu.bll.ChiTietDonHangBLL;
import edu.sgu.gui.formHienThi.MainGUI;
import edu.sgu.gui.formHienThi.VaiTroGUI;
import edu.sgu.gui.formThemSua.ThemSuaSanPhamGUI;

public class ChiTietDonHangGUI extends JFrame {

	private static final long serialVersionUID = 6300850170156104173L;
	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableCellRenderer cellRenderer;
	private JTextField txtLoaiTimKiem;
	private JComboBox<String> cboLoaiTimKiem;
	private JPanel pnlLoaiTimKiem;

	private JTextField txtTen;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTextField txtMa;
	private JLabel lblImage;

	private ChiTietDonHangBLL chiTietDonHangBLL = new ChiTietDonHangBLL();

	private int xMouse;
	private int yMouse;
	private JTextField txtThanhTien;
	
	public ChiTietDonHangGUI(int id_donHang) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ChiTietDonHangGUI.class.getResource("/images/window_perfume.png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1050, 1000);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - xMouse, y - yMouse);
			}
		});
		panel.setForeground(new Color(0, 1, 139));
		panel.setBackground(new Color(0, 139, 128));
		panel.setBounds(2, 2, 1046, 40);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblWindow = new JLabel("");
		lblWindow.setBounds(0, 0, 60, 40);
		ImageIcon icon_window = new ImageIcon(ThemSuaSanPhamGUI.class.getResource("/images/window_perfume.png"));
		icon_window.setImage(icon_window.getImage().getScaledInstance(lblWindow.getWidth(), lblWindow.getHeight(),
				Image.SCALE_SMOOTH));
		lblWindow.setIcon(icon_window);
		panel.add(lblWindow);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane.setBounds(12, 180, 1026, 392);
		scrollPane.getViewport().setBackground(Color.WHITE); // background scrollPane is white
		getContentPane().add(scrollPane);

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
				new String[] { "Mã sản phẩm", "Tên sản phẩm", "Hình ảnh", "Số lượng", "Đơn giá", "Thành tiền" }));

		chiTietDonHangBLL.refreshTable(table, id_donHang);

		// độ dài các cột
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(3).setPreferredWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(0);
		table.getColumnModel().getColumn(5).setPreferredWidth(0);
		
		// ẩn cột hình ảnh
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);

		// canh giữa cột
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);

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
					ImageIcon icon = new ImageIcon("src/images/sanpham/" + table.getValueAt(d, 2).toString());
					icon.setImage(icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(),
							Image.SCALE_SMOOTH));
					lblImage.setIcon(icon);
					txtSoLuong.setText(table.getValueAt(d, 3).toString());
					txtDonGia.setText(table.getValueAt(d, 4).toString());
					txtThanhTien.setText(table.getValueAt(d, 5).toString());
				}

			}
		});

		JTableHeader thd = table.getTableHeader();
		thd.setBackground(new Color(255, 204, 102));
		thd.setFont(new Font("Times New Roman", Font.BOLD, 25));
		thd.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);

		JLabel lblTitle = new JLabel("Chi tiết đơn hàng "+id_donHang);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTitle.setBounds(70, 0, 245, 40);
		panel.add(lblTitle);

		JPanel pnlRestoreDown = new JPanel();
		pnlRestoreDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(MainGUI.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				pnlRestoreDown.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlRestoreDown.setBackground(new Color(0, 139, 128));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pnlRestoreDown.setBackground(Color.GRAY);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pnlRestoreDown.setBackground(new Color(0, 139, 128));
			}
		});
		pnlRestoreDown.setBackground(new Color(0, 139, 128));
		pnlRestoreDown.setBounds(906, 0, 70, 40);
		panel.add(pnlRestoreDown);
		pnlRestoreDown.setLayout(null);

		JLabel lblRestoreDown = new JLabel("-");
		lblRestoreDown.setBounds(0, 0, 70, 40);
		pnlRestoreDown.add(lblRestoreDown);
		lblRestoreDown.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRestoreDown.setForeground(Color.WHITE);
		lblRestoreDown.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoreDown.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));

		JPanel pnlExit = new JPanel();
		pnlExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlExit.setBackground(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(rootPane, "Bạn có chắn chắn muốn thoát không?", "Xác nhận",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)
					dispose();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlExit.setBackground(new Color(0, 139, 128));
			}
		});
		pnlExit.setBackground(new Color(0, 139, 128));
		pnlExit.setBounds(976, 0, 70, 40);
		panel.add(pnlExit);
		pnlExit.setLayout(null);

		JLabel lblExit = new JLabel("X");
		lblExit.setBounds(0, 0, 70, 40);
		pnlExit.add(lblExit);
		lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel pnlContent = new JPanel();
		pnlContent.setBackground(Color.WHITE);
		pnlContent.setBounds(2, 41, 1046, 946);
		contentPane.add(pnlContent);
		pnlContent.setLayout(null);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setLayout(null);
		pnlTimKiem.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiem.setBackground(Color.WHITE);
		pnlTimKiem.setBounds(57, 22, 487, 87);
		pnlContent.add(pnlTimKiem);

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
				new String[] { "Tất cả", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền" }));
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
				chiTietDonHangBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem, id_donHang);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				chiTietDonHangBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem, id_donHang);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				chiTietDonHangBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem, id_donHang);
			}
		});

		txtLoaiTimKiem.setBorder(null);
		txtLoaiTimKiem.setBounds(12, 26, 267, 24);
		txtLoaiTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtLoaiTimKiem.setColumns(10);
		pnlLoaiTimKiem.add(txtLoaiTimKiem);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLamMoi.setBounds(601, 43, 137, 43);
		ImageIcon iconRefresh = new ImageIcon(VaiTroGUI.class.getResource("/images/refresh.png"));
		iconRefresh.setImage(iconRefresh.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnLamMoi.setIcon(iconRefresh);
		pnlContent.add(btnLamMoi);

		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBounds(96, 587, 853, 346);
		pnlContent.add(pnlThongTin);
		pnlThongTin.setLayout(null);
		pnlThongTin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng tin v\u1EC1 s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnlThongTin.setBackground(Color.WHITE);
		((javax.swing.border.TitledBorder) pnlThongTin.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));

		lblImage = new JLabel("");
		lblImage.setBounds(12, 29, 287, 287);
		pnlThongTin.add(lblImage);

		JPanel pnlTen = new JPanel();
		pnlTen.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "T\u00EAn s\u1EA3n ph\u1EA9m",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTen.setBackground(Color.WHITE);
		pnlTen.setBounds(311, 37, 526, 65);
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
		pnlDonGia.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0110\u01A1n gi\u00E1",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDonGia.setBackground(Color.WHITE);
		pnlDonGia.setBounds(311, 241, 250, 65);
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

		JPanel pnlSoLuong = new JPanel();
		pnlSoLuong.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "S\u1ED1 l\u01B0\u1EE3ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSoLuong.setBackground(Color.WHITE);
		pnlSoLuong.setBounds(587, 139, 250, 65);
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

		JPanel pnlMa = new JPanel();
		pnlMa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "M\u00E3 s\u1EA3n ph\u1EA9m",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMa.setBackground(Color.WHITE);
		pnlMa.setBounds(311, 139, 250, 65);
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

		JPanel pnlThanhTien = new JPanel();
		pnlThanhTien.setLayout(null);
		pnlThanhTien.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thành tiền",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThanhTien.setBackground(Color.WHITE);
		pnlThanhTien.setBounds(587, 241, 250, 65);
		((javax.swing.border.TitledBorder) pnlThanhTien.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.add(pnlThanhTien);

		txtThanhTien = new JTextField();
		txtThanhTien.setHorizontalAlignment(SwingConstants.CENTER);
		txtThanhTien.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtThanhTien.setEditable(false);
		txtThanhTien.setColumns(10);
		txtThanhTien.setCaretColor(Color.BLACK);
		txtThanhTien.setBorder(null);
		txtThanhTien.setBackground(Color.WHITE);
		txtThanhTien.setBounds(55, 30, 140, 22);
		pnlThanhTien.add(txtThanhTien);
		ImageIcon iconChon = new ImageIcon(ThemSuaSanPhamGUI.class.getResource("/images/ok.png"));
		iconChon.setImage(iconChon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon iconHuy = new ImageIcon(ChiTietDonHangGUI.class.getResource("/images/cancel.png"));
		iconHuy.setImage(iconHuy.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

		chiTietDonHangBLL.sapXep(table);

	}

}
