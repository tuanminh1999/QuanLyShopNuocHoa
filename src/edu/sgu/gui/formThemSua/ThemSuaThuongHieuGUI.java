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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import edu.sgu.bll.ThuongHieuBLL;
import edu.sgu.dto.ThuongHieu;

public class ThemSuaThuongHieuGUI extends JFrame {

	private static final long serialVersionUID = 6590108619484902136L;

	private JPanel contentPane;
	private JTextField txtTenThuongHieu;
	private JTextField txtDuongDan;
	private JLabel lblSanPham;

	/**
	 * Create the frame.
	 */
	
	private int xMouse;
	private int yMouse;

	private ThuongHieuBLL thuongHieuBLL = new ThuongHieuBLL();

	public ThemSuaThuongHieuGUI(String type, JTable table, int idThuongHieuCapNhat) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (type.equals("Sửa")) {
					List<ThuongHieu> lstThuongHieu = thuongHieuBLL.dsThuongHieu();
					for (ThuongHieu thuongHieuCapNhat : lstThuongHieu) {
						if (thuongHieuCapNhat.getId() == idThuongHieuCapNhat) {
							txtTenThuongHieu.setText(thuongHieuCapNhat.getTenThuongHieu());
							txtDuongDan.setText(thuongHieuCapNhat.getHinhAnh());
						}
					}

					ImageIcon iconSanPham = new ImageIcon(
							ThemSuaThuongHieuGUI.class.getResource("/images/thuonghieu/" + txtDuongDan.getText()));
					iconSanPham.setImage(iconSanPham.getImage().getScaledInstance(lblSanPham.getWidth(),
							lblSanPham.getHeight(), Image.SCALE_SMOOTH));
					lblSanPham.setIcon(iconSanPham);
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ThemSuaThuongHieuGUI.class.getResource("/images/window_perfume.png")));
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
		ImageIcon icon_window = new ImageIcon(ThemSuaThuongHieuGUI.class.getResource("/images/window_perfume.png"));
		icon_window.setImage(icon_window.getImage().getScaledInstance(lblWindow.getWidth(), lblWindow.getHeight(),
				Image.SCALE_SMOOTH));
		lblWindow.setIcon(icon_window);
		panel.add(lblWindow);

		JLabel lblTitle = new JLabel("");
		if (type.equals("Sửa")) {
			lblTitle.setText("Sửa thương hiệu");
		} else if (type.equals("Thêm")) {
			lblTitle.setText("Thêm thương hiệu");
		}
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTitle.setBounds(70, 0, 200, 40);
		panel.add(lblTitle);

		JPanel pnlRestoreDown = new JPanel();
		pnlRestoreDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(ThemSuaThuongHieuGUI.ICONIFIED);
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
			lblTieuDeNoiDung.setText("SỬA THƯƠNG HIỆU");
		} else if (type.equals("Thêm")) {
			lblTieuDeNoiDung.setText("THÊM THƯƠNG HIỆU");
		}
		lblTieuDeNoiDung.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeNoiDung.setForeground(new Color(255, 20, 147));
		lblTieuDeNoiDung.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblTieuDeNoiDung.setBounds(10, 13, 1004, 40);
		pnlContent.add(lblTieuDeNoiDung);

		ImageIcon iconChonThuongHieu = new ImageIcon(ThemSuaThuongHieuGUI.class.getResource("/images/folder.png"));
		iconChonThuongHieu.setImage(iconChonThuongHieu.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));
		ImageIcon iconChonHinh = new ImageIcon(ThemSuaThuongHieuGUI.class.getResource("/images/folder.png"));
		iconChonHinh.setImage(iconChonHinh.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));

		JButton btnThemSua = new JButton("");
		btnThemSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type.equals("Thêm")) {
					String tenTH, hinhAnh;

					tenTH = txtTenThuongHieu.getText();
					hinhAnh = txtDuongDan.getText();

					ThuongHieu th = new ThuongHieu();
					th.setTenThuongHieu(tenTH);
					th.setHinhAnh(hinhAnh);

					if (thuongHieuBLL.themThuongHieu(th) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Thêm thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						thuongHieuBLL.refreshTable(table);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Thêm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				} else if (type.equals("Sửa")) {
					String tenTH, hinhAnh;

					tenTH = txtTenThuongHieu.getText();
					hinhAnh = txtDuongDan.getText();

					ThuongHieu th = new ThuongHieu();
					th.setTenThuongHieu(tenTH);
					th.setHinhAnh(hinhAnh);
					th.setId(idThuongHieuCapNhat);

					if (thuongHieuBLL.suaThuongHieu(th) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						thuongHieuBLL.refreshTable(table);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		if (type.equals("Sửa")) {
			ImageIcon iconThemSua = new ImageIcon(ThemSuaThuongHieuGUI.class.getResource("/images/edit.png"));
			btnThemSua.setText("Sửa");
			iconThemSua.setImage(iconThemSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			btnThemSua.setIcon(iconThemSua);
		} else if (type.equals("Thêm")) {
			ImageIcon iconThemSua = new ImageIcon(ThemSuaThuongHieuGUI.class.getResource("/images/plus.png"));
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
		ImageIcon iconHuy = new ImageIcon(ThemSuaThuongHieuGUI.class.getResource("/images/cancel.png"));
		iconHuy.setImage(iconHuy.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnHuy.setIcon(iconHuy);
		pnlContent.add(btnHuy);

		JLabel lblTenTH = new JLabel("Tên thương hiệu");
		lblTenTH.setBounds(10, 76, 202, 40);
		pnlContent.add(lblTenTH);
		lblTenTH.setFont(new Font("Times New Roman", Font.BOLD, 23));

		txtTenThuongHieu = new JTextField();
		txtTenThuongHieu.setBounds(224, 76, 790, 40);
		pnlContent.add(txtTenThuongHieu);
		txtTenThuongHieu.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtTenThuongHieu.setColumns(10);

		JPanel pnlHinhAnh = new JPanel();
		pnlHinhAnh.setBounds(275, 158, 476, 176);
		pnlContent.add(pnlHinhAnh);
		pnlHinhAnh.setLayout(null);
		pnlHinhAnh.setBorder(new TitledBorder(null, "Hình ảnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		((javax.swing.border.TitledBorder) pnlHinhAnh.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 23));
		pnlHinhAnh.setBackground(Color.WHITE);

		lblSanPham = new JLabel("");
		lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblSanPham.setBounds(168, 29, 140, 80);
		pnlHinhAnh.add(lblSanPham);

		txtDuongDan = new JTextField();
		txtDuongDan.setEditable(false);
		txtDuongDan.setBackground(Color.WHITE);
		txtDuongDan.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtDuongDan.setColumns(10);
		txtDuongDan.setBounds(12, 122, 380, 40);
		pnlHinhAnh.add(txtDuongDan);

		JButton btnChonHinh = new JButton("");
		btnChonHinh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				FileDialog fd = new FileDialog(ThemSuaThuongHieuGUI.this, "Chọn hình ảnh", FileDialog.LOAD);
				fd.setVisible(true);
				String pathString = fd.getDirectory() + fd.getFile();
				if (fd.getDirectory() != null && fd.getFile() != null) {
					txtDuongDan.setText(fd.getFile());
				} else if (!txtDuongDan.equals("") || !txtTenThuongHieu.equals("")) {
					return;
				} else {
					txtDuongDan.setText("");
				}
				ImageIcon iconSanPham = new ImageIcon(pathString);
				iconSanPham.setImage(iconSanPham.getImage().getScaledInstance(lblSanPham.getWidth(),
						lblSanPham.getHeight(), Image.SCALE_SMOOTH));
				lblSanPham.setIcon(iconSanPham);

			}
		});
		btnChonHinh.setBounds(404, 122, 60, 40);
		btnChonHinh.setIcon(iconChonHinh);
		pnlHinhAnh.add(btnChonHinh);

	}

}
