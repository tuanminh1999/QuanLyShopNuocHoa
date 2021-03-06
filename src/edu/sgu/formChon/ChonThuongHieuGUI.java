package edu.sgu.formChon;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import edu.sgu.bll.ThuongHieuBLL;
import edu.sgu.dto.ThuongHieu;
import edu.sgu.gui.formHienThi.MainGUI;
import edu.sgu.gui.formHienThi.VaiTroGUI;
import edu.sgu.gui.formThemSua.ThemSuaSanPhamGUI;



public class ChonThuongHieuGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtLoaiTimKiem;
	private JComboBox cboLoaiTimKiem;
	private JPanel pnlLoaiTimKiem;

	private int xMouse;
	private int yMouse;
	
	private ThuongHieuBLL thuongHieuBLL = new ThuongHieuBLL();

	private JTextField maThuongHieu;

	public ChonThuongHieuGUI(JTextField _maThuongHieu) {
		this.maThuongHieu = _maThuongHieu;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ChonThuongHieuGUI.class.getResource("/images/window_perfume.png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 715);
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
		panel.setBounds(2, 2, 796, 40);
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
		scrollPane.setBounds(12, 180, 778, 392);
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
				new String[] { "M?? th????ng hi???u", "T??n th????ng hi???u", "H??nh ???nh" }));

		refreshTable(table);
		
		// Hi???n th??? h??nh ???nh tr??n table
		table.getColumnModel().getColumn(2).setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel jlb = new JLabel();
				jlb.setHorizontalAlignment(jlb.CENTER);
				ImageIcon icon = new ImageIcon("src/images/thuonghieu/" + value.toString());
				icon.setImage(icon.getImage().getScaledInstance(table.getColumnModel().getColumn(2).getWidth() / 3,
						table.getRowHeight(), Image.SCALE_SMOOTH));
				jlb.setIcon(icon);
				return jlb;
			}
		});

		// ????? d??i c??c c???t
		table.getColumnModel().getColumn(2).setPreferredWidth(0);

		// canh gi???a c???t ?????u ti??n
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

		JTableHeader thd = table.getTableHeader();
		thd.setBackground(new Color(255, 204, 102));
		thd.setFont(new Font("Times New Roman", Font.BOLD, 25));
		thd.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1_1 = new JLabel("Ch???n th????ng hi???u");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(70, 0, 200, 40);
		panel.add(lblNewLabel_1_1);

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
		pnlRestoreDown.setBounds(656, 0, 70, 40);
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
				if (JOptionPane.showConfirmDialog(rootPane, "B???n c?? ch???n ch???n mu???n tho??t kh??ng?", "X??c nh???n",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)
					dispose();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlExit.setBackground(new Color(0, 139, 128));
			}
		});
		pnlExit.setBackground(new Color(0, 139, 128));
		pnlExit.setBounds(726, 0, 70, 40);
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
		pnlContent.setBounds(2, 41, 796, 658);
		contentPane.add(pnlContent);
		pnlContent.setLayout(null);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setLayout(null);
		pnlTimKiem.setBorder(new TitledBorder(null, "T??m ki???m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiem.setBackground(Color.WHITE);
		pnlTimKiem.setBounds(57, 22, 487, 87);
		pnlContent.add(pnlTimKiem);

		cboLoaiTimKiem = new JComboBox();
		cboLoaiTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlLoaiTimKiem.setBorder(new TitledBorder(null, cboLoaiTimKiem.getSelectedItem().toString(),
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				((javax.swing.border.TitledBorder) pnlLoaiTimKiem.getBorder())
						.setTitleFont(new Font("Times New Roman", Font.BOLD, 18));

			}
		});
		cboLoaiTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		cboLoaiTimKiem.setModel(
				new DefaultComboBoxModel(new String[] { "T???t c???", "M?? th????ng hi???u", "T??n th????ng hi???u", "H??nh ???nh" }));
		cboLoaiTimKiem.setBackground(Color.WHITE);
		cboLoaiTimKiem.setBounds(12, 30, 164, 30);
		pnlTimKiem.add(cboLoaiTimKiem);

		pnlLoaiTimKiem = new JPanel();
		pnlLoaiTimKiem.setBackground(Color.WHITE);
		pnlLoaiTimKiem.setBorder(new TitledBorder(null, "T???t c???", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLoaiTimKiem.setBounds(184, 13, 291, 61);
		((javax.swing.border.TitledBorder) pnlLoaiTimKiem.getBorder())
				.setTitleFont(new Font("Times New Roman", Font.BOLD, 18));
		pnlTimKiem.add(pnlLoaiTimKiem);
		pnlLoaiTimKiem.setLayout(null);

		txtLoaiTimKiem = new JTextField();
		txtLoaiTimKiem.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				timKiem(table);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				timKiem(table);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				timKiem(table);

			}
		});

		txtLoaiTimKiem.setBorder(null);
		txtLoaiTimKiem.setBounds(12, 26, 267, 24);
		txtLoaiTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtLoaiTimKiem.setColumns(10);
		pnlLoaiTimKiem.add(txtLoaiTimKiem);

		JButton btnLamMoi = new JButton("L??m m???i");
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLamMoi.setBounds(601, 43, 137, 43);
		ImageIcon iconRefresh = new ImageIcon(VaiTroGUI.class.getResource("/images/refresh.png"));
		iconRefresh.setImage(iconRefresh.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnLamMoi.setIcon(iconRefresh);
		pnlContent.add(btnLamMoi);

		JButton btnChon = new JButton("Ch???n");
		btnChon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String maTH = table.getValueAt(table.getSelectedRow(), 0).toString();
					if (maTH != null) {
						maThuongHieu.setText(maTH);
						dispose();
					}
				} catch (IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(rootPane, "Ch??a ch???n th????ng hi???u n??o !");
				}

			}
		});
		ImageIcon iconChon = new ImageIcon(ThemSuaSanPhamGUI.class.getResource("/images/ok.png"));
		iconChon.setImage(iconChon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnChon.setIcon(iconChon);
		btnChon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnChon.setBounds(226, 568, 137, 43);
		pnlContent.add(btnChon);

		JButton btnHuy = new JButton("Hu???");
		ImageIcon iconHuy = new ImageIcon(ChonThuongHieuGUI.class.getResource("/images/cancel.png"));
		iconHuy.setImage(iconHuy.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnHuy.setIcon(iconHuy);
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHuy.setBounds(420, 568, 137, 43);
		pnlContent.add(btnHuy);

		sapXep(table);

	}

	private void sapXep(JTable thuongHieuTable) {
		DefaultTableModel dtm = (DefaultTableModel) thuongHieuTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
		thuongHieuTable.setRowSorter(sorter);
	}

	public void refreshTable(JTable thuongHieuTable) {
		DefaultTableModel dtm = (DefaultTableModel) thuongHieuTable.getModel();
		dtm.setRowCount(0);
		// G???i l???i thuongHieu l??n ????? c???p nh???t
		ThuongHieuBLL thuongHieuNew = new ThuongHieuBLL();
		List<ThuongHieu> th = thuongHieuNew.dsThuongHieu();
		for (int i = 0; i < th.size(); i++) {
			dtm.addRow(new Object[] { th.get(i).getId(), th.get(i).getTenThuongHieu(), th.get(i).getHinhAnh() });
		}
		thuongHieuTable.setModel(dtm);
	}

	private void timKiem(JTable thuongHieuTable) {
		DefaultTableModel dtm = (DefaultTableModel) thuongHieuTable.getModel();

		dtm.setRowCount(0);

		String search = txtLoaiTimKiem.getText().toLowerCase();
		String type = cboLoaiTimKiem.getSelectedItem().toString();

		List<ThuongHieu> lstThuongHieu = thuongHieuBLL.timKiemThuongHieu(search, type);
		for (ThuongHieu th : lstThuongHieu) {
			dtm.addRow(new Object[] { th.getId(), th.getTenThuongHieu(), th.getHinhAnh() });
		}

	}
}
