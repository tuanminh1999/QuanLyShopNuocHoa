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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import edu.sgu.bll.VaiTroBLL;
import edu.sgu.dto.VaiTro;

public class ThemSuaVaiTroGUI extends JFrame {

	private static final long serialVersionUID = 351392180282072332L;
	
	private JPanel contentPane;
	private JTextField txtTenVT;

	/**
	 * Create the frame.
	 */
	
	private int xMouse;
	private int yMouse;
	private JTextField txtMoTa;
	
	private VaiTroBLL vaiTroBLL = new VaiTroBLL();
	public ThemSuaVaiTroGUI(String type, JTable table, int idCapNhatVaiTro) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (type.equals("Sửa")) {
					List<VaiTro> lstVaiTro = vaiTroBLL.dsVaiTro();
					for (VaiTro vaiTroCapNhat : lstVaiTro) {
						if (vaiTroCapNhat.getId() == idCapNhatVaiTro) {
							txtTenVT.setText(vaiTroCapNhat.getTenVaiTro());
							txtMoTa.setText(vaiTroCapNhat.getMoTa());
						}
					}
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ThemSuaVaiTroGUI.class.getResource("/images/window_perfume.png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1050, 320);
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
		ImageIcon icon_window = new ImageIcon(ThemSuaVaiTroGUI.class.getResource("/images/window_perfume.png"));
		icon_window.setImage(icon_window.getImage().getScaledInstance(lblWindow.getWidth(), lblWindow.getHeight(),
				Image.SCALE_SMOOTH));
		lblWindow.setIcon(icon_window);
		panel.add(lblWindow);

		JLabel lblTitle = new JLabel("");
		if (type.equals("Sửa")) {
			lblTitle.setText("Sửa vai trò");
		} else if (type.equals("Thêm")) {
			lblTitle.setText("Thêm vai trò");
		}
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTitle.setBounds(70, 0, 255, 40);
		panel.add(lblTitle);

		JPanel pnlRestoreDown = new JPanel();
		pnlRestoreDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(ThemSuaVaiTroGUI.ICONIFIED);
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
		pnlContent.setBounds(12, 41, 1026, 266);
		contentPane.add(pnlContent);
		pnlContent.setLayout(null);

		JLabel lblTieuDeNoiDung = new JLabel("");
		if (type.equals("Sửa")) {
			lblTieuDeNoiDung.setText("SỬA VAI TRÒ");
		} else if (type.equals("Thêm")) {
			lblTieuDeNoiDung.setText("THÊM VAI TRÒ");
		}
		lblTieuDeNoiDung.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeNoiDung.setForeground(new Color(255, 20, 147));
		lblTieuDeNoiDung.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblTieuDeNoiDung.setBounds(10, 13, 1004, 40);
		pnlContent.add(lblTieuDeNoiDung);

		ImageIcon iconChonThuongHieu = new ImageIcon(ThemSuaVaiTroGUI.class.getResource("/images/folder.png"));
		iconChonThuongHieu.setImage(iconChonThuongHieu.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));
		ImageIcon iconChonHinh = new ImageIcon(ThemSuaVaiTroGUI.class.getResource("/images/folder.png"));
		iconChonHinh.setImage(iconChonHinh.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));

		JButton btnThemSua = new JButton("");
		btnThemSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type.equals("Thêm")) {
					String tenVT, moTa;

					tenVT = txtTenVT.getText();
					moTa = txtMoTa.getText();
					
					VaiTro vt = new VaiTro();
					vt.setTenVaiTro(tenVT);
					vt.setMoTa(moTa);

					VaiTroBLL vaiTroBLL = new VaiTroBLL();
					if (vaiTroBLL.themVaiTro(vt) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
						vaiTroBLL.refreshTable(table);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");
						System.out.println(vaiTroBLL.themVaiTro(vt));
					}
				}else if (type.equals("Sửa")) {
					String tenVT, moTa;

					tenVT = txtTenVT.getText();
					moTa = txtMoTa.getText();
					
					VaiTro vt = new VaiTro();
					vt.setTenVaiTro(tenVT);
					vt.setMoTa(moTa);
					vt.setId(idCapNhatVaiTro);

					VaiTroBLL vaiTroBLL = new VaiTroBLL();
					if (vaiTroBLL.suaVaiTro(vt) > 0) {
						JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
						vaiTroBLL.refreshTable(table);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại");
					}
				}
			}
		});
		if (type.equals("Sửa")) {
			ImageIcon iconThemSua = new ImageIcon(ThemSuaVaiTroGUI.class.getResource("/images/edit.png"));
			btnThemSua.setText("Cập nhật");
			iconThemSua.setImage(iconThemSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			btnThemSua.setIcon(iconThemSua);
		} else if (type.equals("Thêm")) {
			ImageIcon iconThemSua = new ImageIcon(ThemSuaVaiTroGUI.class.getResource("/images/plus.png"));
			btnThemSua.setText("Thêm");
			iconThemSua.setImage(iconThemSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			btnThemSua.setIcon(iconThemSua);
		}
		btnThemSua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThemSua.setBounds(338, 210, 137, 43);
		pnlContent.add(btnThemSua);

		JButton btnHuy = new JButton("Huỷ");
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHuy.setBounds(537, 210, 137, 43);
		ImageIcon iconHuy = new ImageIcon(ThemSuaVaiTroGUI.class.getResource("/images/cancel.png"));
		iconHuy.setImage(iconHuy.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnHuy.setIcon(iconHuy);
		pnlContent.add(btnHuy);

		JLabel lblTenVT = new JLabel("Tên vai trò");
		lblTenVT.setBounds(10, 80, 235, 40);
		pnlContent.add(lblTenVT);
		lblTenVT.setFont(new Font("Times New Roman", Font.BOLD, 23));

		txtTenVT = new JTextField();
		txtTenVT.setBounds(257, 80, 757, 40);
		pnlContent.add(txtTenVT);
		txtTenVT.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtTenVT.setColumns(10);
		
		JLabel lblMoTa = new JLabel("Mô tả vai trò");
		lblMoTa.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblMoTa.setBounds(10, 140, 235, 40);
		pnlContent.add(lblMoTa);
		
		txtMoTa = new JTextField();
		txtMoTa.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(258, 140, 757, 40);
		pnlContent.add(txtMoTa);

	}
}
