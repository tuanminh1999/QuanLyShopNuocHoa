package edu.sgu.gui.formHienThi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import edu.sgu.bll.NguoiDungBLL;
import edu.sgu.bll.VaiTroBLL;
import edu.sgu.dto.NguoiDung;
import edu.sgu.utils.IOFile;

public class GiaoDienChinhGUI extends JPanel {

	private static final long serialVersionUID = -5300924015697782901L;
	private List<String> lst = null;
	private NguoiDungBLL nguoiDungBLL = new NguoiDungBLL();
	private VaiTroBLL vaiTroBLL = new VaiTroBLL();

	public GiaoDienChinhGUI() {
		lst = IOFile.docFileThongTinNguoiDung();
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(1566, 1005);
		setLayout(null);
		setVisible(true);

		JLabel lblTitle = new JLabel("GIAO DIỆN CHÍNH");
		lblTitle.setForeground(new Color(255, 0, 102));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblTitle.setBounds(254, 0, 1057, 59);
		add(lblTitle);

		JLabel lblBG1 = new JLabel("");
		lblBG1.setBounds(12, 522, 774, 470);
		ImageIcon img_bg1 = new ImageIcon(GiaoDienChinhGUI.class.getResource("/images/background1.jpg"));
		img_bg1.setImage(
				img_bg1.getImage().getScaledInstance(lblBG1.getWidth(), lblBG1.getHeight(), Image.SCALE_SMOOTH));
		lblBG1.setIcon(img_bg1);
		add(lblBG1);

		JLabel lblBG2 = new JLabel("");
		lblBG2.setBounds(902, 422, 663, 570);
		ImageIcon img_bg2 = new ImageIcon(GiaoDienChinhGUI.class.getResource("/images/background2.jpg"));
		img_bg2.setImage(
				img_bg2.getImage().getScaledInstance(lblBG2.getWidth(), lblBG2.getHeight(), Image.SCALE_SMOOTH));
		lblBG2.setIcon(img_bg2);
		add(lblBG2);

		JPanel pnlThongTinND = new JPanel();
		pnlThongTinND.setBackground(Color.WHITE);
		pnlThongTinND.setBounds(12, 80, 890, 429);
		add(pnlThongTinND);
		pnlThongTinND.setLayout(null);

		JLabel lblChao = new JLabel("Xin chào");
		lblChao.setForeground(new Color(51, 51, 204));
		lblChao.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lblChao.setBounds(39, 29, 182, 50);
		pnlThongTinND.add(lblChao);

		JLabel lblTen = new JLabel("");
		lblTen.setForeground(new Color(51, 51, 204));
		lblTen.setFont(new Font("Times New Roman", Font.PLAIN, 45));
		lblTen.setBounds(227, 29, 540, 50);
		pnlThongTinND.add(lblTen);

		JLabel lbl2 = new JLabel("Email:");
		lbl2.setForeground(Color.DARK_GRAY);
		lbl2.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lbl2.setBounds(0, 108, 144, 50);
		pnlThongTinND.add(lbl2);

		JLabel lblEmail = new JLabel("");
		lblEmail.setForeground(Color.RED);
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblEmail.setBounds(156, 108, 734, 50);
		pnlThongTinND.add(lblEmail);

		JLabel lblVaiTr = new JLabel("Vai trò:");
		lblVaiTr.setForeground(Color.DARK_GRAY);
		lblVaiTr.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblVaiTr.setBounds(0, 187, 144, 50);
		pnlThongTinND.add(lblVaiTr);

		JLabel lblVaiTro = new JLabel("");
		lblVaiTro.setForeground(Color.RED);
		lblVaiTro.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblVaiTro.setBounds(156, 187, 734, 50);
		pnlThongTinND.add(lblVaiTro);

		JLabel lblSt = new JLabel("Số ĐT:");
		lblSt.setForeground(Color.DARK_GRAY);
		lblSt.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblSt.setBounds(0, 266, 144, 50);
		pnlThongTinND.add(lblSt);

		JLabel lblSdt = new JLabel("");
		lblSdt.setForeground(Color.RED);
		lblSdt.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblSdt.setBounds(156, 266, 734, 50);
		pnlThongTinND.add(lblSdt);

		JLabel lblDiaChi = new JLabel("");
		lblDiaChi.setForeground(Color.RED);
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblDiaChi.setBounds(156, 345, 734, 50);
		pnlThongTinND.add(lblDiaChi);

		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setForeground(Color.DARK_GRAY);
		lblaCh.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblaCh.setBounds(0, 345, 144, 50);
		pnlThongTinND.add(lblaCh);

		// đọc dữ liệu từ file
		NguoiDung nguoiDung = nguoiDungBLL.dangNhap(lst.get(0), lst.get(1));
		lblEmail.setText(nguoiDung.getEmail());
		lblTen.setText(nguoiDung.getHoTen() + "!");
		lblSdt.setText(nguoiDung.getDtdt());
		lblVaiTro.setText(vaiTroBLL.hienThiTenVaiTro(nguoiDung.getVaiTro()));
		lblDiaChi.setText(nguoiDung.getDiaChi());

		JLabel lblBG3 = new JLabel("");
		lblBG3.setBounds(902, 80, 663, 342);
		ImageIcon img_bg3 = new ImageIcon(GiaoDienChinhGUI.class.getResource("/images/background3.jpg"));
		img_bg3.setImage(
				img_bg3.getImage().getScaledInstance(lblBG3.getWidth(), lblBG3.getHeight(), Image.SCALE_SMOOTH));
		lblBG3.setIcon(img_bg3);
		add(lblBG3);

	}
}
