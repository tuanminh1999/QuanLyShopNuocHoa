package edu.sgu.utils;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import edu.sgu.bll.ChiTietDonHangBLL;
import edu.sgu.bll.DonHangBLL;
import edu.sgu.bll.LoaiSanPhamBLL;
import edu.sgu.bll.NguoiDungBLL;
import edu.sgu.bll.QuangCaoBLL;
import edu.sgu.bll.SanPhamBLL;
import edu.sgu.bll.ThuongHieuBLL;
import edu.sgu.bll.TrangThaiDonHangBLL;
import edu.sgu.bll.VaiTroBLL;
import edu.sgu.dto.ChiTietDonHang;
import edu.sgu.dto.DonHang;
import edu.sgu.dto.LoaiSanPham;
import edu.sgu.dto.NguoiDung;
import edu.sgu.dto.QuangCao;
import edu.sgu.dto.SanPham;
import edu.sgu.dto.ThuongHieu;
import edu.sgu.dto.VaiTro;


public class Excel {
	static FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    private static String getFile() {
        fd.setFile("untitled.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }

    public static void xuatFileExcelThuongHieu() {
        fd.setTitle("Xuất dữ liệu nhà cung cấp ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Thương hiệu");

            ThuongHieuBLL thuongHieuBLL = new ThuongHieuBLL();
            List<ThuongHieu> list = thuongHieuBLL.dsThuongHieu();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("Mã thương hiệu");
            row.createCell(1, CellType.STRING).setCellValue("Tên thương hiệu");
            row.createCell(2, CellType.STRING).setCellValue("Hình ảnh");

            for (ThuongHieu th : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(th.getId());
                row.createCell(1, CellType.STRING).setCellValue(th.getTenThuongHieu());
                row.createCell(2, CellType.STRING).setCellValue(th.getHinhAnh());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    public static void xuatFileExcelLoaiSanPham() {
        fd.setTitle("Xuất dữ liệu nhà cung cấp ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Loại sản phẩm");

            LoaiSanPhamBLL loaiSanPhamBLL = new LoaiSanPhamBLL();
            List<LoaiSanPham> list = loaiSanPhamBLL.dsLoaiSanPham();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("Mã loại sản phẩm");
            row.createCell(1, CellType.STRING).setCellValue("Tên loại sản phẩm");

            for (LoaiSanPham loai : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(loai.getId());
                row.createCell(1, CellType.STRING).setCellValue(loai.getTenLoai());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void xuatFileExcelVaiTro() {
        fd.setTitle("Xuất dữ liệu nhà cung cấp ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Vai trò");

            VaiTroBLL vaiTroBLL = new VaiTroBLL();
            List<VaiTro> list = vaiTroBLL.dsVaiTro();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("Mã vai trò");
            row.createCell(1, CellType.STRING).setCellValue("Tên tên vai trò");
            row.createCell(2, CellType.STRING).setCellValue("Mô tả");

            for (VaiTro th : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(th.getId());
                row.createCell(1, CellType.STRING).setCellValue(th.getTenVaiTro());
                row.createCell(2, CellType.STRING).setCellValue(th.getMoTa());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void xuatFileExcelSanPham() {
        fd.setTitle("Xuất dữ liệu nhà cung cấp ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Sản phẩm");

            SanPhamBLL sanPhamBLL = new SanPhamBLL();
            List<SanPham> list = sanPhamBLL.dsSanPham();
            
            LoaiSanPhamBLL loaiSanPhamBLL = new LoaiSanPhamBLL();
            ThuongHieuBLL thuongHieuBLL = new ThuongHieuBLL();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("Mã sản phẩm");
            row.createCell(1, CellType.STRING).setCellValue("Tên sản phẩm");
            row.createCell(2, CellType.STRING).setCellValue("Mô tả");
            row.createCell(3, CellType.STRING).setCellValue("Hình ảnh");
            row.createCell(4, CellType.NUMERIC).setCellValue("Đơn giá");
            row.createCell(5, CellType.NUMERIC).setCellValue("Đơn giá KH");
            row.createCell(6, CellType.NUMERIC).setCellValue("Số lượng");
            row.createCell(7, CellType.STRING).setCellValue("Ngày tạo");
            row.createCell(8, CellType.STRING).setCellValue("Hiển thị");
            row.createCell(9, CellType.STRING).setCellValue("Loại sản phẩm");
            row.createCell(10, CellType.STRING).setCellValue("Thương hiệu");

            for (SanPham sp : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(sp.getId());
                row.createCell(1, CellType.STRING).setCellValue(sp.getTenSanPham());
                row.createCell(2, CellType.STRING).setCellValue(sp.getMoTa());
                row.createCell(3, CellType.STRING).setCellValue(sp.getHinhAnh());
                row.createCell(4, CellType.NUMERIC).setCellValue(String.valueOf(sp.getDonGia()));
                row.createCell(5, CellType.NUMERIC).setCellValue(String.valueOf(sp.getDonGiaKhuyenMai()));
                row.createCell(6, CellType.NUMERIC).setCellValue(sp.getSoLuong());
                row.createCell(7, CellType.STRING).setCellValue(String.valueOf(sp.getNgayTao()));
                row.createCell(8, CellType.STRING).setCellValue(sp.getHienThi()==1?"Hiện":"Ẩn");
                row.createCell(9, CellType.STRING).setCellValue(loaiSanPhamBLL.hienThiTenLoai(sp.getId_loai()));
                row.createCell(10, CellType.STRING).setCellValue(thuongHieuBLL.hienThiTenThuongHieu(sp.getId_thuongHieu()));
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void xuatFileExcelQuangCao() {
        fd.setTitle("Xuất dữ liệu nhà cung cấp ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Quảng cáo");

            QuangCaoBLL quangCaoBLL = new QuangCaoBLL();
            List<QuangCao> list = quangCaoBLL.dsQuangCao();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("Mã quảng cáo");
            row.createCell(1, CellType.STRING).setCellValue("Hình ảnh");
            row.createCell(2, CellType.STRING).setCellValue("Thông điệp");
            row.createCell(3, CellType.STRING).setCellValue("Thông tin chi tiết");
            row.createCell(4, CellType.STRING).setCellValue("Ngày đăng");

            for (QuangCao th : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(th.getId());
                row.createCell(1, CellType.STRING).setCellValue(th.getHinhAnh());
                row.createCell(2, CellType.STRING).setCellValue(th.getThongDiep());
                row.createCell(3, CellType.STRING).setCellValue(th.getThongTinChiTiet());
                row.createCell(4, CellType.STRING).setCellValue(String.valueOf(th.getNgayDang()));
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void xuatFileExcelNguoiDung() {
        fd.setTitle("Xuất dữ liệu nhà cung cấp ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Người dùng");

            NguoiDungBLL nguoiDungBLL = new NguoiDungBLL();
            List<NguoiDung> list = nguoiDungBLL.dsNguoiDung();
            
            VaiTroBLL vaiTroBLL = new VaiTroBLL();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("Mã sản phẩm");
            row.createCell(1, CellType.STRING).setCellValue("Email");
            row.createCell(2, CellType.STRING).setCellValue("Họ tên");
            row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");
            row.createCell(4, CellType.STRING).setCellValue("Điện thoại");
            row.createCell(5, CellType.STRING).setCellValue("Vai trò");
            
            for (NguoiDung nd : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(nd.getId());
                row.createCell(1, CellType.STRING).setCellValue(nd.getEmail());
                row.createCell(2, CellType.STRING).setCellValue(nd.getHoTen());
                row.createCell(3, CellType.STRING).setCellValue(nd.getDiaChi());
                row.createCell(4, CellType.STRING).setCellValue(nd.getDiaChi());
                row.createCell(5, CellType.STRING).setCellValue(vaiTroBLL.hienThiTenVaiTro(nd.getVaiTro()));

            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void xuatFileExcelHoaDon() {
        fd.setTitle("Xuất dữ liệu nhà cung cấp ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Hoá đơn");

            DonHangBLL donHangBLL = new DonHangBLL();
            List<DonHang> list = donHangBLL.dsDonHang();
            
            ChiTietDonHangBLL chiTietDonHangBLL = new ChiTietDonHangBLL();
            
            TrangThaiDonHangBLL trangThaiDonHangBLL = new TrangThaiDonHangBLL();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("Mã hóa đơn");
            row.createCell(1, CellType.NUMERIC).setCellValue("Mã khách hàng");
            row.createCell(2, CellType.STRING).setCellValue("Ngày đặt");
            row.createCell(3, CellType.STRING).setCellValue("Người nhận");
            row.createCell(4, CellType.STRING).setCellValue("Điện thoại");
            row.createCell(5, CellType.STRING).setCellValue("Địa chỉ");
            row.createCell(6, CellType.STRING).setCellValue("Ghi chú");
            row.createCell(7, CellType.STRING).setCellValue("Thanh toán");
            row.createCell(8, CellType.STRING).setCellValue("Trạng thái");
            row.createCell(9, CellType.STRING).setCellValue("Tổng tiền");


            row.createCell(10, CellType.NUMERIC).setCellValue("Mã sản phẩm");
            row.createCell(11, CellType.STRING).setCellValue("Tên sản phẩm");
            row.createCell(12, CellType.NUMERIC).setCellValue("Số lượng");
            row.createCell(13, CellType.NUMERIC).setCellValue("Đơn giá");
            row.createCell(14, CellType.NUMERIC).setCellValue("Thành tiền");

            for (DonHang dh: list) {
            	rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(dh.getId());
                row.createCell(1, CellType.NUMERIC).setCellValue(dh.getId_khachHang());
                row.createCell(2, CellType.STRING).setCellValue(String.valueOf(dh.getNgayDatHang()));
                row.createCell(3, CellType.STRING).setCellValue(dh.getTenNguoiNhanHang());
                row.createCell(4, CellType.STRING).setCellValue(dh.getDienThoaiNguoiNhan());
                row.createCell(5, CellType.STRING).setCellValue(dh.getDiaChiGiaoHang());
                row.createCell(6, CellType.STRING).setCellValue(dh.getGhiChu());
                row.createCell(7, CellType.STRING).setCellValue(dh.getThanhToan());
                row.createCell(8, CellType.STRING).setCellValue(trangThaiDonHangBLL.hienThiTenTrangThai(dh.getTrangThai()));
                row.createCell(9, CellType.NUMERIC).setCellValue(chiTietDonHangBLL.tongTien(dh.getId()));

                for (ChiTietDonHang cthd : chiTietDonHangBLL.thongTinChiTiet(dh.getId())) {
                    rownum++;
                    row = sheet.createRow(rownum);

                    row.createCell(10, CellType.NUMERIC).setCellValue(cthd.getId());
                    row.createCell(11, CellType.STRING).setCellValue(cthd.getSanPham().getTenSanPham());
                    row.createCell(12, CellType.NUMERIC).setCellValue(cthd.getSoLuong());
                    row.createCell(13, CellType.NUMERIC).setCellValue(cthd.getDonGia());
                    row.createCell(14, CellType.NUMERIC).setCellValue(cthd.getThanhTien());
                }
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
