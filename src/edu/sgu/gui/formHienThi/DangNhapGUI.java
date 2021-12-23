package edu.sgu.gui.formHienThi;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import edu.sgu.utils.IOFile;

public class DangNhapGUI extends JFrame {

	private static final long serialVersionUID = -2628697648807800545L;
	
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JPanel pnlButton;
	private int xMouse;
	private int yMouse;

	private JCheckBox chkGiuDangNhap;
	
	public DangNhapGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DangNhapGUI.class.getResource("/images/window_perfume.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		JPanel pnlUserName = new JPanel();
		pnlUserName.setToolTipText("");
		pnlUserName.setBackground(Color.WHITE);
		pnlUserName.setBounds(175, 162, 250, 40);
		contentPane.add(pnlUserName);
		pnlUserName.setLayout(null);

		JLabel lblEmail = new JLabel("");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(210, 0, 40, 40);
		pnlUserName.add(lblEmail);
		ImageIcon img_username = new ImageIcon(DangNhapGUI.class.getResource("/images/username.png"));
		img_username.setImage(img_username.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblEmail.setIcon(img_username);

		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtEmail.getText().equals("Email")) {
					txtEmail.setText("");
				} else {
					txtEmail.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtEmail.getText().equals("")) {
					txtEmail.setText("Email");
				}
			}
		});
		txtEmail.setBorder(null);
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtEmail.setText("Email");
		txtEmail.setBounds(12, 7, 186, 25);
		pnlUserName.add(txtEmail);
		txtEmail.setColumns(10);

		JPanel pnlPassword = new JPanel();
		pnlPassword.setToolTipText("");
		pnlPassword.setBackground(Color.WHITE);
		pnlPassword.setBounds(175, 215, 250, 40);
		contentPane.add(pnlPassword);
		pnlPassword.setLayout(null);

		JLabel lblPassword = new JLabel("");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon img_password = new ImageIcon(DangNhapGUI.class.getResource("/images/password.png"));
		img_password.setImage(img_password.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblPassword.setIcon(img_password);
		lblPassword.setBounds(210, 0, 40, 40);
		pnlPassword.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setEchoChar((char) 0);
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtPassword.getText().equals("Mật khẩu")) {
					txtPassword.setText("");
					txtPassword.setEchoChar('●');
				} else {
					txtPassword.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtPassword.getText().equals("")) {
					txtPassword.setText("Mật khẩu");
					txtPassword.setEchoChar((char) 0);
				}
			}
		});
		txtPassword.setBorder(null);
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtPassword.setText("Mật khẩu");
		txtPassword.setBounds(12, 7, 186, 25);
		pnlPassword.add(txtPassword);

		pnlButton = new JPanel();

		KeyAdapter ka = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					IOFile.lick_enterLogin(txtEmail, txtPassword, DangNhapGUI.this, chkGiuDangNhap);
				}
			}
		};
		txtEmail.addKeyListener(ka);
		txtPassword.addKeyListener(ka);

		pnlButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IOFile.lick_enterLogin(txtEmail, txtPassword, DangNhapGUI.this, chkGiuDangNhap);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				pnlButton.setBackground(new Color(30, 60, 60));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlButton.setBackground(new Color(47, 79, 79));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				pnlButton.setBackground(new Color(60, 80, 80));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pnlButton.setBackground(new Color(47, 79, 79));

			}
		});
		pnlButton.setLayout(null);
		pnlButton.setBackground(new Color(47, 79, 79));
		pnlButton.setBounds(175, 311, 250, 58);
		contentPane.add(pnlButton);

		JLabel lblBtnLogin = new JLabel("");
		lblBtnLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblBtnLogin.setBounds(40, 9, 40, 40);
		pnlButton.add(lblBtnLogin);
		ImageIcon img_btnlogin = new ImageIcon(DangNhapGUI.class.getResource("/images/btn_login.png"));
		img_btnlogin.setImage(img_btnlogin.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblBtnLogin.setIcon(img_btnlogin);

		JLabel lblNewLabel_1 = new JLabel("ĐĂNG NHẬP");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(84, 13, 125, 32);
		pnlButton.add(lblNewLabel_1);

		JLabel lblLogin = new JLabel("");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon img_login = new ImageIcon(DangNhapGUI.class.getResource("/images/login.png"));
		img_login.setImage(img_login.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		lblLogin.setIcon(img_login);
		lblLogin.setBounds(250, 52, 100, 100);
		contentPane.add(lblLogin);

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
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(2, 2, 596, 40);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(0, 0, 50, 40);
		ImageIcon img_icon = new ImageIcon(DangNhapGUI.class.getResource("/images/window_perfume.png"));
		img_icon.setImage(
				img_icon.getImage().getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), Image.SCALE_SMOOTH));
		lblIcon.setIcon(img_icon);
		panel.add(lblIcon);

		JLabel lblNewLabel = new JLabel("Đăng nhập");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel.setBounds(60, 0, 140, 40);
		panel.add(lblNewLabel);

		JPanel pnlRestoreDown = new JPanel();
		pnlRestoreDown.setBackground(new Color(0, 128, 128));
		pnlRestoreDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(DangNhapGUI.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				pnlRestoreDown.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlRestoreDown.setBackground(new Color(0, 128, 128));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pnlRestoreDown.setBackground(Color.GRAY);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pnlRestoreDown.setBackground(new Color(0, 128, 128));
			}
		});
		pnlRestoreDown.setBounds(456, 0, 70, 40);
		panel.add(pnlRestoreDown);
		pnlRestoreDown.setLayout(null);

		JLabel lblRestoreDown = new JLabel("-");
		lblRestoreDown.setBounds(0, 0, 70, 40);
		pnlRestoreDown.add(lblRestoreDown);
		lblRestoreDown.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRestoreDown.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoreDown.setForeground(Color.WHITE);
		lblRestoreDown.setFont(new Font("Comic Sans MS", Font.BOLD, 60));

		JPanel pnlExit = new JPanel();
		pnlExit.setBackground(new Color(0, 128, 128));
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
				pnlExit.setBackground(new Color(0, 128, 128));
			}
		});
		pnlExit.setLayout(null);
		pnlExit.setBounds(526, 0, 70, 40);
		panel.add(pnlExit);

		JLabel lblExit = new JLabel("X");
		lblExit.setBounds(0, 0, 70, 40);
		pnlExit.add(lblExit);
		lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
		lblExit.setForeground(Color.WHITE);

		chkGiuDangNhap = new JCheckBox("Giữ đăng nhập");
		chkGiuDangNhap.setSelected(true);
		chkGiuDangNhap.setForeground(Color.WHITE);
		chkGiuDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chkGiuDangNhap.setBackground(new Color(0, 139, 128));
		chkGiuDangNhap.setBounds(175, 274, 149, 25);
		contentPane.add(chkGiuDangNhap);
		IOFile.docFileGiuDangNhap(txtEmail, txtPassword);
	}
}
