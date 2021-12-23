package edu.sgu.gui.formHienThi;

import java.awt.BorderLayout;
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
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import edu.sgu.gui.menu.MenuItem;


public class MainGUI extends JFrame {

	private JPanel contentPane;
	private int xMouse;
	private int yMouse;

	private JPanel menus;
	private JPanel pnlBody;

	/**
	 * Create the frame.
	 */

	public MainGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/images/window_perfume.png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1900, 1050);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(0, 139, 128));
		pnlHeader.setBounds(2, 2, 1895, 40);
		contentPane.add(pnlHeader);
		pnlHeader.setLayout(null);

		pnlHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		pnlHeader.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - xMouse, y - yMouse);
			}
		});

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblLogo.setBounds(0, 0, 50, 40);
		ImageIcon img_logo = new ImageIcon(MainGUI.class.getResource("/images/window_perfume.png"));
		img_logo.setImage(
				img_logo.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
		lblLogo.setIcon(img_logo);

		pnlHeader.add(lblLogo);

		JLabel lblTieuDe = new JLabel("Quản lý Perfume Shop");
		lblTieuDe.setHorizontalAlignment(SwingConstants.LEFT);
		lblTieuDe.setForeground(Color.WHITE);
		lblTieuDe.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTieuDe.setBounds(70, 0, 230, 40);
		pnlHeader.add(lblTieuDe);

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
		pnlRestoreDown.setBounds(1755, 0, 70, 40);
		pnlHeader.add(pnlRestoreDown);
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
		pnlExit.setBounds(1825, 0, 70, 40);
		pnlHeader.add(pnlExit);
		pnlExit.setLayout(null);

		JLabel lblExit = new JLabel("X");
		lblExit.setBounds(0, 0, 70, 40);
		pnlExit.add(lblExit);
		lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(47, 90, 90));
		pnlMenu.setBorder(null);
		pnlMenu.setBounds(2, 43, 330, 1005);
		contentPane.add(pnlMenu);

		JLabel lblShop = new JLabel("");
		lblShop.setBounds(0, 0, 330, 253);
		lblShop.setBorder(new LineBorder(new Color(0, 0, 0)));
		ImageIcon img_shop = new ImageIcon(MainGUI.class.getResource("/images/main.jpg"));
		img_shop.setImage(
				img_shop.getImage().getScaledInstance(lblShop.getWidth(), lblShop.getHeight(), Image.SCALE_SMOOTH));
		lblShop.setIcon(img_shop);
		lblShop.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 255, 330, 750);
		scrollPane.setBorder(null);

		menus = new JPanel();
		menus.setBackground(new Color(47, 90, 90));
		scrollPane.setViewportView(menus);
		menus.setLayout(new BoxLayout(menus, BoxLayout.Y_AXIS));
		pnlMenu.setLayout(null);
		pnlMenu.add(lblShop);
		pnlMenu.add(scrollPane);

		pnlBody = new JPanel();
		pnlBody.setBorder(null);
		pnlBody.setBounds(332, 43, 1566, 1005);
		contentPane.add(pnlBody);

		execute();
		pnlBody.setLayout(null);
		GiaoDienChinhGUI giaoDienChinhGUI = new GiaoDienChinhGUI();
		giaoDienChinhGUI.setBounds(0, 0, 1566, 1005);
		pnlBody.add(giaoDienChinhGUI);
	}

	private void execute() {
		// DANH SÁCH (dùng chung cho các mục quản lý)
		ImageIcon image_down = new ImageIcon(MainGUI.class.getResource("/images/chevron-down.png"));
		ImageIcon image_list = new ImageIcon(MainGUI.class.getResource("/images/list.png"));
		image_list.setImage(image_list.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		// GIAO DIỆN CHÍNH
		ImageIcon image_home = new ImageIcon(MainGUI.class.getResource("/images/home.png"));
		image_home.setImage(image_home.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		MenuItem giaoDienChinh = new MenuItem(image_home, "GIAO DIỆN CHÍNH", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showJpanel(new GiaoDienChinhGUI());
			}
		}, null);

		// THƯƠNG HIỆU
		ImageIcon image_thuongHieu = new ImageIcon(MainGUI.class.getResource("/images/trademark.png"));
		image_thuongHieu.setImage(image_thuongHieu.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		MenuItem thươngHieu = new MenuItem(image_thuongHieu, "Thương hiệu", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showJpanel(new ThuongHieuGUI());
			}
		}, null);
		// LOẠI SẢN PHẨM
		ImageIcon image_loaiSanPham = new ImageIcon(MainGUI.class.getResource("/images/product-type.png"));
		image_loaiSanPham.setImage(image_loaiSanPham.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		MenuItem loaiSanPham = new MenuItem(image_loaiSanPham, "Loại sản phẩm", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showJpanel(new LoaiSanPhamGUI());
			}
		}, null);
		// SẢN PHẨM
		ImageIcon image_sanPham = new ImageIcon(MainGUI.class.getResource("/images/product.png"));
		image_sanPham.setImage(image_sanPham.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		MenuItem sanPham = new MenuItem(image_sanPham, "Sản phẩm", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showJpanel(new SanPhamGUI());
			}
		}, null);
		// QUẢN LÝ SẢN PHẨM
		MenuItem quanLySanPham = new MenuItem(image_list, "QL SẢN PHẨM", null, image_down, thươngHieu, loaiSanPham, sanPham);

		// ĐƠN HÀNG
		ImageIcon image_donHang = new ImageIcon(MainGUI.class.getResource("/images/bill.png"));
		image_donHang.setImage(image_donHang.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		MenuItem donHang = new MenuItem(image_donHang, "Đơn hàng", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showJpanel(new DonHangGUI());
			}
		}, null);
		// THỐNG KÊ
		ImageIcon image_thongKe = new ImageIcon(MainGUI.class.getResource("/images/chart.png"));
		image_thongKe.setImage(image_thongKe.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		MenuItem thongKe = new MenuItem(image_thongKe, "Thống kê", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showJpanel(new ThongKeGUI());

			}
		}, null);
		// QUẢN LÝ ĐƠN HÀNG
		MenuItem quanLyDonHang = new MenuItem(image_list, "QL ĐƠN HÀNG", null, image_down, donHang, thongKe);

		// VAI TRÒ
		ImageIcon image_vaiTro = new ImageIcon(MainGUI.class.getResource("/images/permission.png"));
		image_vaiTro.setImage(image_vaiTro.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		MenuItem vaiTro = new MenuItem(image_vaiTro, "Vai trò", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showJpanel(new VaiTroGUI());

			}
		}, null);
		// NGƯỜI DÙNG
		ImageIcon image_nguoiDung = new ImageIcon(MainGUI.class.getResource("/images/user.png"));
		image_nguoiDung.setImage(image_nguoiDung.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		MenuItem nguoiDung = new MenuItem(image_nguoiDung, "Người dùng", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showJpanel(new NguoiDungGUI());

			}
		}, null);
		// QUẢN LÝ NGƯỜI DÙNG
		MenuItem quanLyNguoiDung = new MenuItem(image_list, "QL NGƯỜI DÙNG", null, image_down, vaiTro, nguoiDung);

		// QUẢNG CÁO
		ImageIcon image_quangCao = new ImageIcon(MainGUI.class.getResource("/images/advertisement.png"));
		image_quangCao.setImage(image_quangCao.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		MenuItem quangCao = new MenuItem(image_quangCao, "QUẢNG CÁO", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showJpanel(new QuangCaoGUI());
			}
		}, null);

		// ĐĂNG XUẤT
		ImageIcon image_dangXuat = new ImageIcon(MainGUI.class.getResource("/images/logout.png"));
		image_dangXuat.setImage(image_dangXuat.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		MenuItem dangXuat = new MenuItem(image_dangXuat, "ĐĂNG XUẤT", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn chắc chắn muốn đăng xuất không?", "Xác nhận",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
					DangNhapGUI login = new DangNhapGUI();
					login.setVisible(true);
					MainGUI.this.dispose();
				}
			}
		}, null);

		addMenu(giaoDienChinh, quanLySanPham, quanLyDonHang, quanLyNguoiDung, quangCao, dangXuat);
	}

	private void addMenu(MenuItem... menu) {
		for (int i = 0; i < menu.length; i++) {
			menus.add(menu[i]);
			ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
			for (MenuItem m : subMenu) {
				addMenu(m);
			}
		}
		menus.revalidate();
	}

	private JPanel createNewPanel(JPanel pnlBody) {
		if (pnlBody != null) {
			MainGUI.this.remove(pnlBody);
		}
		pnlBody = new JPanel();
		pnlBody.setBorder(null);
		pnlBody.setBounds(332, 43, 1566, 1005);
		contentPane.add(pnlBody);
		pnlBody.setLayout(new BorderLayout(0, 0));
		return pnlBody;
	}

	private void showJpanel(JPanel panel) {
		pnlBody = createNewPanel(pnlBody);
		pnlBody.add(panel);
		pnlBody.repaint();
		pnlBody.revalidate();

	}
}
