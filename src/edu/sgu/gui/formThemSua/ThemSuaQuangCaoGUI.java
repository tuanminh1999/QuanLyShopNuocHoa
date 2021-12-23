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

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import edu.sgu.bll.QuangCaoBLL;
import edu.sgu.dto.QuangCao;
import edu.sgu.utils.Format;



public class ThemSuaQuangCaoGUI extends JFrame {

	private static final long serialVersionUID = -4383211004762354233L;

	private JPanel contentPane;
	private JTextField txtNgayDang;
	private JTextField txtDuongDan;
	private JLabel lblHinhAnh;

	/**
	 * Create the frame.
	 */
	
	private int xMouse;
	private int yMouse;
	private Date ngayDang;

	private QuangCaoBLL quangCaoBLL = new QuangCaoBLL();
	private JTextField txtThongDiep;
	private JTextArea txtThongTinCT;
	private Date luuNgay;
	
	public ThemSuaQuangCaoGUI(String type, JTable table, int idQuangCaoCapNhat) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ngayDang = new java.sql.Date(new java.util.Date().getTime());
				txtNgayDang.setText(Format.chuyenNgayVietNam(ngayDang));
				
				if (type.equals("Sửa")) {
					List<QuangCao> lstQuangCao = quangCaoBLL.dsQuangCao();
					for (QuangCao quangCaoCapNhat : lstQuangCao) {
						if (quangCaoCapNhat.getId() == idQuangCaoCapNhat) {
							txtDuongDan.setText(quangCaoCapNhat.getHinhAnh());
							txtNgayDang.setText(Format.chuyenNgayVietNam(quangCaoCapNhat.getNgayDang()));
							luuNgay = quangCaoCapNhat.getNgayDang();
							txtThongDiep.setText(quangCaoCapNhat.getThongDiep());
							txtThongTinCT.setText(quangCaoCapNhat.getThongTinChiTiet());
						}
					}

					ImageIcon iconQuangCao = new ImageIcon(
							ThemSuaQuangCaoGUI.class.getResource("/images/quangcao/" + txtDuongDan.getText()));
					iconQuangCao.setImage(iconQuangCao.getImage().getScaledInstance(lblHinhAnh.getWidth(),
							lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
					lblHinhAnh.setIcon(iconQuangCao);
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ThemSuaQuangCaoGUI.class.getResource("/images/window_perfume.png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1050, 494);
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
		ImageIcon icon_window = new ImageIcon(ThemSuaQuangCaoGUI.class.getResource("/images/window_perfume.png"));
		icon_window.setImage(icon_window.getImage().getScaledInstance(lblWindow.getWidth(), lblWindow.getHeight(),
				Image.SCALE_SMOOTH));
		lblWindow.setIcon(icon_window);
		panel.add(lblWindow);

		JLabel lblTitle = new JLabel("");
		if (type.equals("Sửa")) {
			lblTitle.setText("Sửa quảng cáo");
		} else if (type.equals("Thêm")) {
			lblTitle.setText("Thêm quảng cáo");
		}
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTitle.setBounds(70, 0, 200, 40);
		panel.add(lblTitle);

		JPanel pnlRestoreDown = new JPanel();
		pnlRestoreDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(ThemSuaQuangCaoGUI.ICONIFIED);
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
		pnlContent.setBounds(12, 41, 1026, 440);
		contentPane.add(pnlContent);
		pnlContent.setLayout(null);

		JLabel lblTieuDeNoiDung = new JLabel("");
		if (type.equals("Sửa")) {
			lblTieuDeNoiDung.setText("SỬA QUẢNG CÁO");
		} else if (type.equals("Thêm")) {
			lblTieuDeNoiDung.setText("THÊM QUẢNG CÁO");
		}
		lblTieuDeNoiDung.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeNoiDung.setForeground(new Color(255, 20, 147));
		lblTieuDeNoiDung.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblTieuDeNoiDung.setBounds(10, 13, 1004, 40);
		pnlContent.add(lblTieuDeNoiDung);

		ImageIcon iconChonThuongHieu = new ImageIcon(ThemSuaQuangCaoGUI.class.getResource("/images/folder.png"));
		iconChonThuongHieu.setImage(iconChonThuongHieu.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));
		ImageIcon iconChonHinh = new ImageIcon(ThemSuaQuangCaoGUI.class.getResource("/images/folder.png"));
		iconChonHinh.setImage(iconChonHinh.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));

		JButton btnThemSua = new JButton("");
		btnThemSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type == "Thêm") {
					String hinhAnh, thongDiep, thongTinChiTiet;

					hinhAnh = txtDuongDan.getText();
					thongDiep = txtThongDiep.getText();
					thongTinChiTiet = txtThongTinCT.getText();

					QuangCao qc = new QuangCao();
					qc.setHinhAnh(hinhAnh);
					qc.setThongDiep(thongDiep);
					qc.setThongTinChiTiet(thongTinChiTiet);
					qc.setNgayDang(ngayDang);

					if (quangCaoBLL.themQuangCao(qc) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						quangCaoBLL.refreshTable(table);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Thêm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}else if (type == "Sửa") {
					String hinhAnh, thongDiep, thongTinChiTiet;

					hinhAnh = txtDuongDan.getText();
					thongDiep = txtThongDiep.getText();
					thongTinChiTiet = txtThongTinCT.getText();

					QuangCao qc = new QuangCao();
					qc.setHinhAnh(hinhAnh);
					qc.setThongDiep(thongDiep);
					qc.setThongTinChiTiet(thongTinChiTiet);
					qc.setNgayDang(luuNgay);
					qc.setId(idQuangCaoCapNhat);

					if (quangCaoBLL.suaQuangCao(qc) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						quangCaoBLL.refreshTable(table);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		if (type.equals("Sửa")) {
			ImageIcon iconThemSua = new ImageIcon(ThemSuaQuangCaoGUI.class.getResource("/images/edit.png"));
			btnThemSua.setText("Cập nhật");
			iconThemSua.setImage(iconThemSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			btnThemSua.setIcon(iconThemSua);
		} else if (type.equals("Thêm")) {
			ImageIcon iconThemSua = new ImageIcon(ThemSuaQuangCaoGUI.class.getResource("/images/plus.png"));
			btnThemSua.setText("Thêm");
			iconThemSua.setImage(iconThemSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			btnThemSua.setIcon(iconThemSua);
		}
		btnThemSua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThemSua.setBounds(338, 380, 137, 43);
		pnlContent.add(btnThemSua);

		JButton btnHuy = new JButton("Huỷ");
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHuy.setBounds(538, 380, 137, 43);
		ImageIcon iconHuy = new ImageIcon(ThemSuaQuangCaoGUI.class.getResource("/images/cancel.png"));
		iconHuy.setImage(iconHuy.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnHuy.setIcon(iconHuy);
		pnlContent.add(btnHuy);

		JLabel lblNgayDang = new JLabel("Ngày đăng");
		lblNgayDang.setBounds(498, 66, 202, 40);
		pnlContent.add(lblNgayDang);
		lblNgayDang.setFont(new Font("Times New Roman", Font.BOLD, 23));

		txtNgayDang = new JTextField();
		txtNgayDang.setBackground(Color.WHITE);
		txtNgayDang.setEditable(false);
		txtNgayDang.setBounds(712, 66, 302, 40);
		pnlContent.add(txtNgayDang);
		txtNgayDang.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtNgayDang.setColumns(10);

		JPanel pnlHinhAnh = new JPanel();
		pnlHinhAnh.setBounds(10, 66, 476, 262);
		pnlContent.add(pnlHinhAnh);
		pnlHinhAnh.setLayout(null);
		pnlHinhAnh.setBorder(new TitledBorder(null, "Hình ảnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		((javax.swing.border.TitledBorder) pnlHinhAnh.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 23));
		pnlHinhAnh.setBackground(Color.WHITE);

		lblHinhAnh = new JLabel("");
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblHinhAnh.setBounds(12, 29, 452, 167);
		pnlHinhAnh.add(lblHinhAnh);

		txtDuongDan = new JTextField();
		txtDuongDan.setEditable(false);
		txtDuongDan.setBackground(Color.WHITE);
		txtDuongDan.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtDuongDan.setColumns(10);
		txtDuongDan.setBounds(12, 209, 380, 40);
		pnlHinhAnh.add(txtDuongDan);

		JButton btnChonHinh = new JButton("");
		btnChonHinh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				FileDialog fd = new FileDialog(ThemSuaQuangCaoGUI.this, "Chọn hình ảnh", FileDialog.LOAD);
				fd.setVisible(true);
				String pathString = fd.getDirectory() + fd.getFile();
				if (fd.getDirectory() != null && fd.getFile() != null)
					txtDuongDan.setText(fd.getFile());
				else {
					txtDuongDan.setText("");
				}
				ImageIcon iconHinhAnh = new ImageIcon(pathString);
				iconHinhAnh.setImage(iconHinhAnh.getImage().getScaledInstance(lblHinhAnh.getWidth(),
						lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
				lblHinhAnh.setIcon(iconHinhAnh);

			}
		});
		btnChonHinh.setBounds(404, 209, 60, 40);
		btnChonHinh.setIcon(iconChonHinh);
		pnlHinhAnh.add(btnChonHinh);
		
		JLabel lblThongDiep = new JLabel("Thông điệp");
		lblThongDiep.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblThongDiep.setBounds(498, 129, 202, 40);
		pnlContent.add(lblThongDiep);
		
		txtThongDiep = new JTextField();
		txtThongDiep.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtThongDiep.setColumns(10);
		txtThongDiep.setBounds(712, 129, 302, 40);
		pnlContent.add(txtThongDiep);
		
		JLabel lblThongTinCT = new JLabel("Thông tin chi tiết");
		lblThongTinCT.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblThongTinCT.setBounds(498, 197, 202, 40);
		pnlContent.add(lblThongTinCT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(712, 201, 302, 127);
		pnlContent.add(scrollPane);
		
		txtThongTinCT = new JTextArea();
		txtThongTinCT.setWrapStyleWord(true);
		txtThongTinCT.setLineWrap(true);
		txtThongTinCT.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		scrollPane.setViewportView(txtThongTinCT);

	}
}
