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

import edu.sgu.bll.QuangCaoBLL;
import edu.sgu.gui.formThemSua.ThemSuaQuangCaoGUI;
import edu.sgu.utils.Excel;


public class QuangCaoGUI extends JPanel {
	private JTable table;
	private JTextField txtThongDiep;
	private JTextField txtMa;
	private JLabel lblImage;
	private JTextField txtNgayDang;
	private JTextArea txtThongTinCT;
	private JTextField txtLoaiTimKiem;
	private JComboBox<String> cboLoaiTimKiem;
	private JPanel pnlLoaiTimKiem;

	private QuangCaoBLL quangCaoBLL = new QuangCaoBLL();

	public QuangCaoGUI() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(1566, 1005);
		setLayout(null);
		setVisible(true);

		JLabel lblTitle = new JLabel("QUẢNG CÁO");
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
				new String[] { "Mã quảng cáo", "Hình ảnh", "Thông điệp", "Thông tin chi tiết", "Ngày đăng" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(15);

		//
		quangCaoBLL.refreshTable(table);

		// canh giữa cột đầu tiên và cuối cùng
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int d = table.getSelectedRow();
					if (d == -1) {
						return;
					}
					txtMa.setText(table.getValueAt(d, 0).toString());
					ImageIcon icon = new ImageIcon("src/images/quangcao/" + table.getValueAt(d, 1).toString());
					icon.setImage(icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(),
							Image.SCALE_SMOOTH));
					lblImage.setIcon(icon);
					txtThongDiep.setText(table.getValueAt(d, 2).toString());
					txtThongTinCT.setText(table.getValueAt(d, 3).toString());
					txtNgayDang.setText(table.getValueAt(d, 4).toString());
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
		pnlThongTin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin về quảng cáo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongTin.setBounds(347, 620, 895, 375);
		add(pnlThongTin);
		((javax.swing.border.TitledBorder) pnlThongTin.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTin.setLayout(null);

		lblImage = new JLabel("");
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblImage.setBounds(12, 31, 490, 331);
		pnlThongTin.add(lblImage);

		JPanel pnlMa = new JPanel();
		pnlMa.setBackground(Color.WHITE);
		pnlMa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mã quảng cáo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMa.setBounds(514, 15, 180, 65);
		pnlThongTin.add(pnlMa);
		((javax.swing.border.TitledBorder) pnlMa.getBorder()).setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlMa.setLayout(null);

		txtMa = new JTextField();
		txtMa.setBounds(17, 30, 145, 22);
		txtMa.setHorizontalAlignment(SwingConstants.CENTER);
		txtMa.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtMa.setEditable(false);
		txtMa.setBackground(Color.WHITE);
		txtMa.setBorder(null);
		txtMa.setCaretColor(Color.BLACK);
		pnlMa.add(txtMa);
		txtMa.setColumns(10);

		JPanel pnlThongDiep = new JPanel();
		pnlThongDiep.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông điệp",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongDiep.setBackground(Color.WHITE);
		pnlThongDiep.setBounds(514, 95, 369, 65);
		pnlThongTin.add(pnlThongDiep);
		((javax.swing.border.TitledBorder) pnlThongDiep.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongDiep.setLayout(null);

		txtThongDiep = new JTextField();
		txtThongDiep.setBounds(17, 30, 334, 22);
		txtThongDiep.setHorizontalAlignment(SwingConstants.CENTER);
		txtThongDiep.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtThongDiep.setEditable(false);
		txtThongDiep.setColumns(10);
		txtThongDiep.setCaretColor(Color.BLACK);
		txtThongDiep.setBorder(null);
		txtThongDiep.setBackground(Color.WHITE);
		pnlThongDiep.add(txtThongDiep);

		JPanel pnlNgayDang = new JPanel();
		pnlNgayDang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ngày đăng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlNgayDang.setBackground(Color.WHITE);
		pnlNgayDang.setBounds(706, 15, 180, 65);
		pnlThongTin.add(pnlNgayDang);
		((javax.swing.border.TitledBorder) pnlNgayDang.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlNgayDang.setLayout(null);

		txtNgayDang = new JTextField();
		txtNgayDang.setBounds(17, 30, 145, 22);
		txtNgayDang.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgayDang.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtNgayDang.setEditable(false);
		txtNgayDang.setColumns(10);
		txtNgayDang.setCaretColor(Color.BLACK);
		txtNgayDang.setBorder(null);
		txtNgayDang.setBackground(Color.WHITE);
		pnlNgayDang.add(txtNgayDang);

		JPanel pnlThongTinCT = new JPanel();
		pnlThongTinCT.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin chi tiết",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongTinCT.setBackground(Color.WHITE);
		pnlThongTinCT.setBounds(514, 173, 369, 189);
		pnlThongTin.add(pnlThongTinCT);
		((javax.swing.border.TitledBorder) pnlThongTinCT.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThongTinCT.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 28, 345, 148);
		pnlThongTinCT.add(scrollPane_1);

		txtThongTinCT = new JTextArea();
		txtThongTinCT.setEditable(false);
		txtThongTinCT.setWrapStyleWord(true);
		txtThongTinCT.setLineWrap(true);
		txtThongTinCT.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		scrollPane_1.setViewportView(txtThongTinCT);

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
		cboLoaiTimKiem.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Mã quảng cáo", "Hình ảnh",
				"Thông điệp", "Thông tin chi tiết", "Ngày đăng" }));
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
				quangCaoBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				quangCaoBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				quangCaoBLL.timKiem(table, txtLoaiTimKiem, cboLoaiTimKiem);
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
				quangCaoBLL.refreshTable(table);
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
				ThemSuaQuangCaoGUI themSuaQuangCaoGUI = new ThemSuaQuangCaoGUI("Thêm", table, 0);
				themSuaQuangCaoGUI.setVisible(true);
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

					if (JOptionPane.showConfirmDialog(QuangCaoGUI.this,
							"Bạn muốn xoá quảng cáo "
									+ table.getValueAt(table.getSelectedRow(), 1).toString().toUpperCase() + " không?",
							"Xác nhận", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
						if (quangCaoBLL.xoaQuangCao(id) == 1) {
							JOptionPane.showMessageDialog(QuangCaoGUI.this, "Xoá thành công", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
							quangCaoBLL.refreshTable(table);
							lblImage.setIcon(null);
							txtMa.setText("");
							txtNgayDang.setText("");
							txtThongDiep.setText("");
							txtThongTinCT.setText("");
						} else {
							JOptionPane.showMessageDialog(QuangCaoGUI.this, "Xoá thất bại", "Lỗi",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(QuangCaoGUI.this, "Vui lòng chọn quảng cáo để xoá");
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
					ThemSuaQuangCaoGUI themSuaQuangCaoGUI = new ThemSuaQuangCaoGUI("Sửa", table, id);
					themSuaQuangCaoGUI.setVisible(true);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(QuangCaoGUI.this, "Vui lòng chọn quảng cáo để cập nhật");
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
				Excel.xuatFileExcelQuangCao();
			}
		});
		btnXuat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ImageIcon iconExport = new ImageIcon(ThuongHieuGUI.class.getResource("/images/excel.png"));
		iconExport.setImage(iconExport.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnXuat.setIcon(iconExport);
		btnXuat.setBounds(1396, 95, 137, 43);
		add(btnXuat);

		quangCaoBLL.sapXep(table);
	}

}
