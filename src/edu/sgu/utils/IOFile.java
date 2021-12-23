package edu.sgu.utils;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.sgu.bll.NguoiDungBLL;
import edu.sgu.dto.NguoiDung;
import edu.sgu.gui.formHienThi.DangNhapGUI;
import edu.sgu.gui.formHienThi.MainGUI;

public class IOFile {
	
	private static final File fileThongTinNguoiDung = new File("src/file/ThongTinNguoiDung.txt"); // file để lưu đối tượng
	private static final File fileTaiKhoan = new File("src/file/TaiKhoan.txt");
	
	private static NguoiDungBLL nguoiDungBLL = new NguoiDungBLL();
	private static NguoiDung nguoiDung = null;
		
	public static List<String> docFileThongTinNguoiDung() {
		// Hiển thị thông tin lên bảng
		List<String> lst = new ArrayList<>();

		try (DataInputStream dis = new DataInputStream(new FileInputStream(fileThongTinNguoiDung))) {
			String email = dis.readUTF();
			String pass = dis.readUTF();
			lst.add(email);
			lst.add(pass);

		} catch (EOFException e1) {
			System.out.println("Hết file!");
		} catch (FileNotFoundException e1) {
			System.out.println("Không tìm thấy file!");
		} catch (IOException e1) {
			System.out.println("Lỗi đọc file!");
		}
		return lst;
	}
	
	public static void ghiFileThongTinNguoiDung() {
		DataOutputStream output = null;
		try {
			fileThongTinNguoiDung.delete();
			if (!fileThongTinNguoiDung.exists()) {
				fileThongTinNguoiDung.createNewFile();
			}
			output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileThongTinNguoiDung, true)));
			output.writeUTF(nguoiDung.getEmail());
			output.writeUTF(nguoiDung.getPassword());
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy file");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Lỗi ghi file");
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e1) {
				System.out.println("Đóng file không công!");
			}
		}

	}
	
	public static void ghiFileGiuDangNhap() {
		DataOutputStream output = null;
		try {
			if (!fileTaiKhoan.exists()) {
				fileTaiKhoan.createNewFile();
			} else {
				fileTaiKhoan.delete();
				fileTaiKhoan.createNewFile();
			}
			output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileTaiKhoan, true)));
			output.writeUTF(nguoiDung.getEmail());
			output.writeUTF(nguoiDung.getPassword());
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy file");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Lỗi ghi file");
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e1) {
				System.out.println("Đóng file không công!");
			}
		}

	}
	
	public static void docFileGiuDangNhap(JTextField txtEmail, JPasswordField txtPassword) {
		// Hiển thị thông tin lên form đăng nhập
		if (fileTaiKhoan.exists()) {
			try (DataInputStream dis = new DataInputStream(new FileInputStream(fileTaiKhoan))) {
				txtEmail.setText(dis.readUTF());
				txtPassword.setText(dis.readUTF());
				txtPassword.setEchoChar('●');
			} catch (EOFException e1) {
				System.out.println("Hết file!");
			} catch (FileNotFoundException e1) {
				System.out.println("Không tìm thấy file!");
			} catch (IOException e1) {
				System.out.println("Lỗi đọc file!");
			}
		}
	}

	public static void lick_enterLogin(JTextField txtEmail, JPasswordField txtPassword, DangNhapGUI dangNhapGUI, JCheckBox chkGiuDangNhap) {
		String email = txtEmail.getText();
		String password = txtPassword.getText();
		nguoiDung = nguoiDungBLL.dangNhap(email, password);
		if (nguoiDung != null) {
			JOptionPane.showMessageDialog(null, "Đăng nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			ghiFileThongTinNguoiDung();
			new MainGUI().setVisible(true);
			dangNhapGUI.dispose();
			if (chkGiuDangNhap.isSelected())
				IOFile.ghiFileGiuDangNhap();
			else
				fileTaiKhoan.delete();
		} else if (email.equals("") || email.equals("Tài khoản")) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền tài khoản" ,"Thông báo", JOptionPane.INFORMATION_MESSAGE);
			txtEmail.grabFocus();
		} else if (password.equals("") || password.equals("Mật khẩu")) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền mật khẩu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			txtPassword.grabFocus();
		} else if (email.equals("") || email.equals("Tài khoản") || password.equals("")
				|| password.equals("Mật khẩu")) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Lưu ý", JOptionPane.WARNING_MESSAGE);
			txtEmail.grabFocus();
		} else
			JOptionPane.showMessageDialog(null, "Tài khoản và mật khẩu không khớp" ,"Lỗi", JOptionPane.ERROR_MESSAGE);
	}


}
