package edu.sgu.gui.formThemSua;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.List;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import edu.sgu.bll.LoaiSanPhamBLL;
import edu.sgu.bll.SanPhamBLL;
import edu.sgu.dto.LoaiSanPham;
import edu.sgu.dto.SanPham;
import edu.sgu.formChon.ChonThuongHieuGUI;
import edu.sgu.utils.Format;

public class ThemSuaSanPhamGUI extends JFrame {

	private static final long serialVersionUID = -1351228664590590115L;
	
	private JPanel contentPane;
	private JTextField txtSoLuong;
	private JTextField txtDonGia;
	private JTextField txtDonGiaKM;
	private JTextField txtTenSanPham;
	private JTextField txtNgayTao;
	private JTextField txtThuongHieu;
	private JTextField txtDuongDan;
	private JLabel lblSanPham;

	/**
	 * Create the frame.
	 */
	
	private int xMouse;
	private int yMouse;
	private JComboBox<String> cboLoai;
	private JComboBox<String> cboHienThi;
	private Date ngayTao;

	private LoaiSanPhamBLL loaiSanPhamBLL = new LoaiSanPhamBLL();
	private List<LoaiSanPham> lstLoaiSP = loaiSanPhamBLL.dsLoaiSanPham();
	private SanPhamBLL sanPhamBLL = new SanPhamBLL();
	private JTextArea txtMoTa;
	private Date luuNgay;

	public ThemSuaSanPhamGUI(String type, JTable table, int idSanPhamCapNhat) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				DefaultComboBoxModel<String> dcmlLoai = (DefaultComboBoxModel<String>) cboLoai.getModel();
				for (LoaiSanPham loai : lstLoaiSP) {
					dcmlLoai.addElement(loai.getTenLoai());
				}

				SanPhamBLL sanPhamBLL = new SanPhamBLL();
				DefaultComboBoxModel<String> dcmlHienThi = (DefaultComboBoxModel<String>) cboHienThi.getModel();
				dcmlHienThi.addElement("Hiện");
				dcmlHienThi.addElement("Ẩn");

				ngayTao = new java.sql.Date(new java.util.Date().getTime());
				txtNgayTao.setText(Format.chuyenNgayVietNam(ngayTao));

				if (type.equals("Sửa")) {
					List<SanPham> lstSanPham = sanPhamBLL.dsSanPham();
					for (SanPham sanPhamCapNhat : lstSanPham) {
						if (sanPhamCapNhat.getId() == idSanPhamCapNhat) {
							txtTenSanPham.setText(sanPhamCapNhat.getTenSanPham());
							txtMoTa.setText(sanPhamCapNhat.getMoTa());
							txtDuongDan.setText(sanPhamCapNhat.getHinhAnh());
							txtDonGia.setText(String.format("%.0f",sanPhamCapNhat.getDonGia()));
							txtDonGiaKM.setText(String.format("%.0f",sanPhamCapNhat.getDonGiaKhuyenMai()));
							txtSoLuong.setText(String.valueOf(sanPhamCapNhat.getSoLuong()));
							txtNgayTao.setText(Format.chuyenNgayVietNam(sanPhamCapNhat.getNgayTao()));
							luuNgay = sanPhamCapNhat.getNgayTao();
							if(sanPhamCapNhat.getHienThi()==1) {
								cboHienThi.setSelectedItem("Hiện");
							}else {
								cboHienThi.setSelectedItem("Ẩn");
							}
							for (LoaiSanPham loai : lstLoaiSP) {
								if(loai.getId() == idSanPhamCapNhat) {
									cboLoai.setSelectedItem(loai.getTenLoai());
								}
							}
							txtThuongHieu.setText(String.valueOf(sanPhamCapNhat.getId_thuongHieu()));
									
						}
					}

					ImageIcon iconSanPham = new ImageIcon(
							ThemSuaThuongHieuGUI.class.getResource("/images/sanpham/" + txtDuongDan.getText()));
					iconSanPham.setImage(iconSanPham.getImage().getScaledInstance(lblSanPham.getWidth(),
							lblSanPham.getHeight(), Image.SCALE_SMOOTH));
					lblSanPham.setIcon(iconSanPham);
				}

			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ThemSuaSanPhamGUI.class.getResource("/images/window_perfume.png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1050, 880);
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

		JLabel lblTitle = new JLabel("");
		if (type.equals("Sửa")) {
			lblTitle.setText("Sửa sản phẩm");
		} else if (type.equals("Thêm")) {
			lblTitle.setText("Thêm sản phẩm");
		}
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTitle.setBounds(70, 0, 200, 40);
		panel.add(lblTitle);

		JPanel pnlRestoreDown = new JPanel();
		pnlRestoreDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(ThemSuaSanPhamGUI.ICONIFIED);
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
		pnlContent.setBounds(12, 41, 1026, 829);
		contentPane.add(pnlContent);
		pnlContent.setLayout(null);

		JLabel lblTieuDeNoiDung = new JLabel("");
		if (type.equals("Sửa")) {
			lblTieuDeNoiDung.setText("SỬA SẢN PHẨM");
		} else if (type.equals("Thêm")) {
			lblTieuDeNoiDung.setText("THÊM SẢN PHẨM");
		}
		lblTieuDeNoiDung.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeNoiDung.setForeground(new Color(255, 20, 147));
		lblTieuDeNoiDung.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblTieuDeNoiDung.setBounds(10, 13, 1004, 40);
		pnlContent.add(lblTieuDeNoiDung);

		JLabel lblMoTa = new JLabel("Mô tả");
		lblMoTa.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblMoTa.setBounds(10, 134, 167, 40);
		pnlContent.add(lblMoTa);

		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblSoLuong.setBounds(10, 469, 167, 40);
		pnlContent.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setHorizontalAlignment(SwingConstants.TRAILING);
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(190, 469, 253, 40);
		pnlContent.add(txtSoLuong);

		JLabel lblDonGia = new JLabel("Đơn giá");
		lblDonGia.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblDonGia.setBounds(10, 353, 167, 40);
		pnlContent.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setHorizontalAlignment(SwingConstants.TRAILING);
		txtDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(190, 353, 193, 40);
		pnlContent.add(txtDonGia);

		JLabel lblDonGiaKM = new JLabel("Đơn giá KM");
		lblDonGiaKM.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblDonGiaKM.setBounds(10, 411, 167, 40);
		pnlContent.add(lblDonGiaKM);

		txtDonGiaKM = new JTextField();
		txtDonGiaKM.setHorizontalAlignment(SwingConstants.TRAILING);
		txtDonGiaKM.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtDonGiaKM.setColumns(10);
		txtDonGiaKM.setBounds(190, 411, 193, 40);
		pnlContent.add(txtDonGiaKM);

		JLabel lblNgayTao = new JLabel("Ngày tạo");
		lblNgayTao.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNgayTao.setBounds(10, 585, 167, 40);
		pnlContent.add(lblNgayTao);

		txtNgayTao = new JTextField();
		txtNgayTao.setBackground(Color.WHITE);
		txtNgayTao.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtNgayTao.setEditable(false);
		txtNgayTao.setColumns(10);
		txtNgayTao.setBounds(190, 585, 253, 40);
		pnlContent.add(txtNgayTao);

		JLabel lblHienThi = new JLabel("Hiển thị");
		lblHienThi.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblHienThi.setBounds(10, 701, 167, 40);
		pnlContent.add(lblHienThi);

		cboHienThi = new JComboBox<>();
		cboHienThi.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		cboHienThi.setBackground(Color.WHITE);
		cboHienThi.setBounds(190, 701, 253, 40);
		pnlContent.add(cboHienThi);

		JLabel lblLoai = new JLabel("Loại");
		lblLoai.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblLoai.setBounds(10, 527, 167, 40);
		pnlContent.add(lblLoai);

		cboLoai = new JComboBox<>();
		cboLoai.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		cboLoai.setBackground(Color.WHITE);
		cboLoai.setBounds(190, 527, 253, 40);
		pnlContent.add(cboLoai);

		JLabel lblThuongHieu = new JLabel("Thương hiệu");
		lblThuongHieu.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblThuongHieu.setBounds(10, 643, 167, 40);
		pnlContent.add(lblThuongHieu);

		txtThuongHieu = new JTextField();
		txtThuongHieu.setHorizontalAlignment(SwingConstants.CENTER);
		txtThuongHieu.setBackground(Color.WHITE);
		txtThuongHieu.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtThuongHieu.setEditable(false);
		txtThuongHieu.setColumns(10);
		txtThuongHieu.setBounds(190, 643, 183, 40);
		pnlContent.add(txtThuongHieu);

		JButton btnChonThuongHieu = new JButton("");
		btnChonThuongHieu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChonThuongHieuGUI chonThuongHieu = new ChonThuongHieuGUI(txtThuongHieu);
				chonThuongHieu.setVisible(true);
			}
		});

		ImageIcon iconChonThuongHieu = new ImageIcon(ThemSuaSanPhamGUI.class.getResource("/images/folder.png"));
		iconChonThuongHieu.setImage(iconChonThuongHieu.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));
		btnChonThuongHieu.setIcon(iconChonThuongHieu);
		btnChonThuongHieu.setBounds(385, 643, 60, 40);
		pnlContent.add(btnChonThuongHieu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 134, 825, 201);
		pnlContent.add(scrollPane);

		txtMoTa = new JTextArea();
		txtMoTa.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtMoTa.setWrapStyleWord(true);
		txtMoTa.setLineWrap(true);
		scrollPane.setViewportView(txtMoTa);
		ImageIcon iconChonHinh = new ImageIcon(ThemSuaSanPhamGUI.class.getResource("/images/folder.png"));
		iconChonHinh.setImage(iconChonHinh.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));

		JButton btnThemSua = new JButton("");
		btnThemSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type == "Thêm") {
					String tenSP, moTa, hinhAnh, hienThi;
					int soLuong, idLoai, idThuongHieu;
					double donGia, donGiaKM;

					tenSP = txtTenSanPham.getText();
					moTa = txtMoTa.getText();
					hinhAnh = txtDuongDan.getText();
					soLuong = Integer.parseInt(txtSoLuong.getText());
					donGia = Double.parseDouble(txtDonGia.getText());
					donGiaKM = Double.parseDouble(txtDonGiaKM.getText());
					idLoai = chuyenTenThanhId(cboLoai.getSelectedItem().toString());
					idThuongHieu = Integer.parseInt(txtThuongHieu.getText());
					hienThi = cboHienThi.getSelectedItem().toString();

					SanPham sp = new SanPham();
					sp.setTenSanPham(tenSP);
					sp.setMoTa(moTa);
					sp.setHinhAnh(hinhAnh);
					sp.setDonGia(donGia);
					sp.setDonGiaKhuyenMai(donGiaKM);
					sp.setSoLuong(soLuong);
					sp.setId_loai(idLoai);
					sp.setId_thuongHieu(idThuongHieu);
					sp.setNgayTao(ngayTao);
					if (hienThi.equals("Hiện"))
						sp.setHienThi(1);
					else
						sp.setHienThi(0);

					if (sanPhamBLL.themSanPham(sp) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Thêm thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						sanPhamBLL.refreshTable(table);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Thêm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				} else if (type.equals("Sửa")) {
					String tenSP, moTa, hinhAnh, hienThi;
					int soLuong, idLoai, idThuongHieu;
					double donGia, donGiaKM;

					tenSP = txtTenSanPham.getText();
					moTa = txtMoTa.getText();
					hinhAnh = txtDuongDan.getText();
					soLuong = Integer.parseInt(txtSoLuong.getText());
					donGia = Double.parseDouble(txtDonGia.getText());
					donGiaKM = Double.parseDouble(txtDonGiaKM.getText());
					idLoai = chuyenTenThanhId(cboLoai.getSelectedItem().toString());
					idThuongHieu = Integer.parseInt(txtThuongHieu.getText());
					hienThi = cboHienThi.getSelectedItem().toString();

					SanPham sp = new SanPham();
					sp.setTenSanPham(tenSP);
					sp.setMoTa(moTa);
					sp.setHinhAnh(hinhAnh);
					sp.setDonGia(donGia);
					sp.setDonGiaKhuyenMai(donGiaKM);
					sp.setSoLuong(soLuong);
					sp.setId_loai(idLoai);
					sp.setId_thuongHieu(idThuongHieu);
					sp.setNgayTao(luuNgay);
					sp.setId(idSanPhamCapNhat);
					if (hienThi.equals("Hiện"))
						sp.setHienThi(1);
					else
						sp.setHienThi(0);

					if (sanPhamBLL.suaSanPham(sp) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						sanPhamBLL.refreshTable(table);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		if (type.equals("Sửa")) {
			ImageIcon iconThemSua = new ImageIcon(ThemSuaSanPhamGUI.class.getResource("/images/edit.png"));
			btnThemSua.setText("Cập nhật");
			iconThemSua.setImage(iconThemSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			btnThemSua.setIcon(iconThemSua);
		} else if (type.equals("Thêm")) {
			ImageIcon iconThemSua = new ImageIcon(ThemSuaSanPhamGUI.class.getResource("/images/plus.png"));
			btnThemSua.setText("Thêm");
			iconThemSua.setImage(iconThemSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			btnThemSua.setIcon(iconThemSua);
		}
		btnThemSua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThemSua.setBounds(338, 764, 137, 43);
		pnlContent.add(btnThemSua);

		JButton btnHuy = new JButton("Huỷ");
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHuy.setBounds(538, 764, 137, 43);
		ImageIcon iconHuy = new ImageIcon(ThemSuaSanPhamGUI.class.getResource("/images/cancel.png"));
		iconHuy.setImage(iconHuy.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnHuy.setIcon(iconHuy);
		pnlContent.add(btnHuy);

		JLabel lblVND = new JLabel("VNĐ");
		lblVND.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblVND.setBounds(395, 353, 50, 40);
		pnlContent.add(lblVND);

		JLabel lblVNDKM = new JLabel("VNĐ");
		lblVNDKM.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblVNDKM.setBounds(395, 411, 50, 40);
		pnlContent.add(lblVNDKM);

		JLabel lblTenSP = new JLabel("Tên sản phẩm");
		lblTenSP.setBounds(10, 76, 167, 40);
		pnlContent.add(lblTenSP);
		lblTenSP.setFont(new Font("Times New Roman", Font.BOLD, 23));

		txtTenSanPham = new JTextField();
		txtTenSanPham.setBounds(190, 76, 824, 40);
		pnlContent.add(txtTenSanPham);
		txtTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtTenSanPham.setColumns(10);

		JPanel pnlHinhAnh = new JPanel();
		pnlHinhAnh.setBounds(538, 353, 476, 388);
		pnlContent.add(pnlHinhAnh);
		pnlHinhAnh.setLayout(null);
		pnlHinhAnh.setBorder(new TitledBorder(null, "Hình ảnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		((javax.swing.border.TitledBorder) pnlHinhAnh.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 23));
		pnlHinhAnh.setBackground(Color.WHITE);

		lblSanPham = new JLabel("");
		lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblSanPham.setBounds(63, 29, 350, 280);
		pnlHinhAnh.add(lblSanPham);

		txtDuongDan = new JTextField();
		txtDuongDan.setEditable(false);
		txtDuongDan.setBackground(Color.WHITE);
		txtDuongDan.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtDuongDan.setColumns(10);
		txtDuongDan.setBounds(12, 335, 380, 40);
		pnlHinhAnh.add(txtDuongDan);

		JButton btnChonHinh = new JButton("");
		btnChonHinh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileDialog fd = new FileDialog(ThemSuaSanPhamGUI.this, "Chọn hình ảnh", FileDialog.LOAD);
				fd.setVisible(true);
				String pathString = fd.getDirectory() + fd.getFile();
				if (fd.getDirectory() != null && fd.getFile() != null)
					txtDuongDan.setText(fd.getFile());
				else {
					txtDuongDan.setText("");
				}
				ImageIcon iconSanPham = new ImageIcon(pathString);
				iconSanPham.setImage(iconSanPham.getImage().getScaledInstance(lblSanPham.getWidth(),
						lblSanPham.getHeight(), Image.SCALE_SMOOTH));
				lblSanPham.setIcon(iconSanPham);
			}
		});
		btnChonHinh.setBounds(404, 335, 60, 40);
		btnChonHinh.setIcon(iconChonHinh);
		pnlHinhAnh.add(btnChonHinh);

	}

	private int chuyenTenThanhId(String name) {
		for (LoaiSanPham loaiSanPham : lstLoaiSP) {
			if (loaiSanPham.getTenLoai().equals(name)) {
				return loaiSanPham.getId();
			}
		}
		return 0;

	}

}
