package edu.sgu.gui.formThemSua;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import edu.sgu.bll.NguoiDungBLL;
import edu.sgu.dto.NguoiDung;
import edu.sgu.formChon.ChonVaiTroGUI;

public class ThemSuaNguoiDungGUI extends JFrame {

	private static final long serialVersionUID = 4110524048677557188L;

	private JPanel contentPane;
	private JTextField txtHoTen;
	private JTextField txtVaiTro;
	
	/**
	 * Create the frame.
	 */
	
	private int xMouse;
	private int yMouse;

	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtDienThoai;
	private NguoiDungBLL nguoiDungBLL = new NguoiDungBLL();
	private JPasswordField txtPassword;

	public ThemSuaNguoiDungGUI(String type, JTable table, int idCapNhatNguoiDung) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (type.equals("Sửa")) {
					List<NguoiDung> lstNguoiDung = nguoiDungBLL.dsNguoiDung();
					for (NguoiDung nguoiDungCapNhat : lstNguoiDung) {
						if (nguoiDungCapNhat.getId() == idCapNhatNguoiDung) {
							txtEmail.setText(nguoiDungCapNhat.getEmail());
							txtHoTen.setText(nguoiDungCapNhat.getHoTen());
							txtPassword.setText(nguoiDungCapNhat.getPassword());
							txtDiaChi.setText(nguoiDungCapNhat.getDiaChi());
							txtDienThoai.setText(nguoiDungCapNhat.getDtdt());
							txtVaiTro.setText(String.valueOf(nguoiDungCapNhat.getVaiTro()));
						}
					}
				}else if(type.equals("Thêm")) {
					txtPassword.setEditable(true);
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ThemSuaNguoiDungGUI.class.getResource("/images/window_perfume.png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1050, 540);
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
		ImageIcon icon_window = new ImageIcon(ThemSuaNguoiDungGUI.class.getResource("/images/window_perfume.png"));
		icon_window.setImage(icon_window.getImage().getScaledInstance(lblWindow.getWidth(), lblWindow.getHeight(),
				Image.SCALE_SMOOTH));
		lblWindow.setIcon(icon_window);
		panel.add(lblWindow);

		JLabel lblTitle = new JLabel("");
		if (type.equals("Sửa")) {
			lblTitle.setText("Sửa thông tin người dùng");
		} else if (type.equals("Thêm")) {
			lblTitle.setText("Thêm người dùng");
		}
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTitle.setBounds(70, 0, 300, 40);
		panel.add(lblTitle);

		JPanel pnlRestoreDown = new JPanel();
		pnlRestoreDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(ThemSuaNguoiDungGUI.ICONIFIED);
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
		pnlContent.setBounds(12, 41, 1026, 483);
		contentPane.add(pnlContent);
		pnlContent.setLayout(null);

		JLabel lblTieuDeNoiDung = new JLabel("");
		if (type.equals("Sửa")) {
			lblTieuDeNoiDung.setText("SỬA THÔNG TIN NGƯỜI DÙNG");
		} else if (type.equals("Thêm")) {
			lblTieuDeNoiDung.setText("THÊM NGƯỜI DÙNG MỚI");
		}
		lblTieuDeNoiDung.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeNoiDung.setForeground(new Color(255, 20, 147));
		lblTieuDeNoiDung.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblTieuDeNoiDung.setBounds(11, 13, 1004, 40);
		pnlContent.add(lblTieuDeNoiDung);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblEmail.setBounds(10, 155, 167, 40);
		pnlContent.add(lblEmail);

		JLabel lblVaiTro = new JLabel("Vai trò");
		lblVaiTro.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblVaiTro.setBounds(579, 350, 167, 40);
		pnlContent.add(lblVaiTro);

		txtVaiTro = new JTextField();
		txtVaiTro.setHorizontalAlignment(SwingConstants.CENTER);
		txtVaiTro.setBackground(Color.WHITE);
		txtVaiTro.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtVaiTro.setEditable(false);
		txtVaiTro.setColumns(10);
		txtVaiTro.setBounds(759, 350, 183, 40);
		pnlContent.add(txtVaiTro);

		JButton btnChonThuongHieu = new JButton("");
		btnChonThuongHieu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChonVaiTroGUI chonVaiTroGUI = new ChonVaiTroGUI(txtVaiTro);
				chonVaiTroGUI.setVisible(true);
			}
		});

		ImageIcon iconChonThuongHieu = new ImageIcon(ThemSuaNguoiDungGUI.class.getResource("/images/folder.png"));
		iconChonThuongHieu.setImage(iconChonThuongHieu.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));
		btnChonThuongHieu.setIcon(iconChonThuongHieu);
		btnChonThuongHieu.setBounds(954, 350, 60, 40);
		pnlContent.add(btnChonThuongHieu);
		ImageIcon iconChonHinh = new ImageIcon(ThemSuaNguoiDungGUI.class.getResource("/images/folder.png"));
		iconChonHinh.setImage(iconChonHinh.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));

		JButton btnThemSua = new JButton("");
		btnThemSua.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (type.equals("Thêm")) {
					String hoTen, email, password, diaChi, dtdd;
					int idVaiTro;

					hoTen = txtHoTen.getText();
					email = txtEmail.getText();
					password = txtPassword.getText();
					diaChi = txtDiaChi.getText();
					dtdd = txtDienThoai.getText();
					idVaiTro = Integer.parseInt(txtVaiTro.getText());

					NguoiDung nd = new NguoiDung();
					nd.setHoTen(hoTen);
					nd.setEmail(email);
					nd.setPassword(password);
					nd.setDiaChi(diaChi);
					nd.setDtdt(dtdd);
					nd.setVaiTro(idVaiTro);

					NguoiDungBLL nguoiDungBLL = new NguoiDungBLL();
					if (nguoiDungBLL.themNguoiDung(nd) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Thêm thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						nguoiDungBLL.refreshTable(table);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Thêm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}else if (type.equals("Sửa")) {
					String hoTen, email, password, diaChi, dtdd;
					int idVaiTro;

					hoTen = txtHoTen.getText();
					email = txtEmail.getText();
					password = txtPassword.getText();
					diaChi = txtDiaChi.getText();
					dtdd = txtDienThoai.getText();
					idVaiTro = Integer.parseInt(txtVaiTro.getText());

					NguoiDung nd = new NguoiDung();
					nd.setHoTen(hoTen);
					nd.setEmail(email);
					nd.setPassword(password);
					nd.setDiaChi(diaChi);
					nd.setDtdt(dtdd);
					nd.setVaiTro(idVaiTro);
					nd.setId(idCapNhatNguoiDung);

					NguoiDungBLL nguoiDungBLL = new NguoiDungBLL();
					if (nguoiDungBLL.suaNguoiDung(nd) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						nguoiDungBLL.refreshTable(table);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		if (type.equals("Sửa")) {
			ImageIcon iconThemSua = new ImageIcon(ThemSuaNguoiDungGUI.class.getResource("/images/edit.png"));
			btnThemSua.setText("Cập nhật");
			iconThemSua.setImage(iconThemSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			btnThemSua.setIcon(iconThemSua);
		} else if (type.equals("Thêm")) {
			ImageIcon iconThemSua = new ImageIcon(ThemSuaNguoiDungGUI.class.getResource("/images/plus.png"));
			btnThemSua.setText("Thêm");
			iconThemSua.setImage(iconThemSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			btnThemSua.setIcon(iconThemSua);
		}
		btnThemSua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThemSua.setBounds(338, 427, 137, 43);
		pnlContent.add(btnThemSua);

		JButton btnHuy = new JButton("Huỷ");
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHuy.setBounds(537, 427, 137, 43);
		ImageIcon iconHuy = new ImageIcon(ThemSuaNguoiDungGUI.class.getResource("/images/cancel.png"));
		iconHuy.setImage(iconHuy.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnHuy.setIcon(iconHuy);
		pnlContent.add(btnHuy);

		JLabel lblHoTen = new JLabel("Họ tên");
		lblHoTen.setBounds(10, 90, 167, 40);
		pnlContent.add(lblHoTen);
		lblHoTen.setFont(new Font("Times New Roman", Font.BOLD, 23));

		txtHoTen = new JTextField();
		txtHoTen.setBounds(190, 90, 824, 40);
		pnlContent.add(txtHoTen);
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtHoTen.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtEmail.setColumns(10);
		txtEmail.setBounds(189, 155, 824, 40);
		pnlContent.add(txtEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblPassword.setBounds(10, 220, 167, 40);
		pnlContent.add(lblPassword);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(189, 285, 824, 40);
		pnlContent.add(txtDiaChi);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblDiaChi.setBounds(10, 285, 167, 40);
		pnlContent.add(lblDiaChi);

		JLabel lblDienThoai = new JLabel("Điện thoại");
		lblDienThoai.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblDienThoai.setBounds(10, 350, 167, 40);
		pnlContent.add(lblDienThoai);

		txtDienThoai = new JTextField();
		txtDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(189, 350, 260, 40);
		pnlContent.add(txtDienThoai);
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setEditable(false);
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtPassword.setBounds(190, 220, 824, 40);
		pnlContent.add(txtPassword);

	}
}
